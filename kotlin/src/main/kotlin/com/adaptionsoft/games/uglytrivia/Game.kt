package com.adaptionsoft.games.uglytrivia

import java.util.*


class Game {
    private val players = arrayListOf<String>()
    private val places = IntArray(6)
    private val purses = IntArray(6)
    private val inPenaltyBox = BooleanArray(6)
    private val popQuestions = LinkedList<String>()
    private val scienceQuestions = LinkedList<String>()
    private val sportsQuestions = LinkedList<String>()
    private val rockQuestions = LinkedList<String>()

    private var currentPlayer = 0
    private var isGettingOutOfPenaltyBox = false

    init {
        for (i in 0..49) {
            popQuestions.addLast("Pop Question $i")
            scienceQuestions.addLast("Science Question $i")
            sportsQuestions.addLast("Sports Question $i")
            rockQuestions.addLast(createRockQuestion(i))
        }
    }

    fun createRockQuestion(index: Int): String = "Rock Question $index"

    val isPlayable: Boolean get() = (howManyPlayers >= 2)

    fun add(playerName: String): Boolean {
        players.add(playerName)
        places[howManyPlayers] = 0
        purses[howManyPlayers] = 0
        inPenaltyBox[howManyPlayers] = false
        println("$playerName was added")
        println("They are player number ${players.size}")
        return true
    }

    val howManyPlayers: Int get() = players.size

    fun roll(roll: Int): Unit {
        println(players[currentPlayer] + " is the current player")
        println("They have rolled a $roll")
        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true
                println(players[currentPlayer] + " is getting out of the penalty box")
                places[currentPlayer] = places[currentPlayer] + roll
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12
                println("${players[currentPlayer]}'s new location is ${places[currentPlayer]}")
                println("The category is " + currentCategory())
                askQuestion()
            } else {
                println(players[currentPlayer] + " is not getting out of the penalty box")
                isGettingOutOfPenaltyBox = false
            }
        } else {
            places[currentPlayer] = places[currentPlayer] + roll
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12
            println(players[currentPlayer] + "'s new location is " + places[currentPlayer])
            println("The category is ${currentCategory()}")
            askQuestion()
        }
    }

    private fun askQuestion(): Unit {
        when (currentCategory()) {
            "Pop" -> println(popQuestions.removeFirst())
            "Science" -> println(scienceQuestions.removeFirst())
            "Sports" -> println(sportsQuestions.removeFirst())
            "Rock" -> println(rockQuestions.removeFirst())
        }
    }

    private fun currentCategory(): String {
        return when (places[currentPlayer]) {
            0, 4, 8 -> "Pop"
            1, 5, 9 -> "Science"
            2, 6, 10 -> "Sports"
            else -> "Rock"
        }
    }

    fun wasCorrectlyAnswered(): Boolean {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                println("Answer was correct!!!!")
                purses[currentPlayer] += 1
                println(players[currentPlayer] + " now has " + purses[currentPlayer] + " Gold Coins.")
                val winner: Boolean = didPlayerWin
                currentPlayer += 1
                if (currentPlayer == players.size) currentPlayer = 0
                return winner
            } else {
                currentPlayer++
                if (currentPlayer == players.size) currentPlayer = 0
                return true
            }
        } else {
            println("Answer was corrent!!!!")
            purses[currentPlayer]++
            println("${players[currentPlayer]} now has ${purses[currentPlayer]} Gold Coins.")
            val winner = didPlayerWin
            currentPlayer++
            if (currentPlayer == players.size) currentPlayer = 0
            return winner
        }
    }

    fun wrongAnswer(): Boolean {
        println("Question was incorrectly answered")
        println(players[currentPlayer] + " was sent to the penalty box")
        inPenaltyBox[currentPlayer] = true
        currentPlayer += 1
        if (currentPlayer == players.size) currentPlayer = 0
        return true;
    }

    private val didPlayerWin: Boolean get() = !(purses[currentPlayer] == 6)
}