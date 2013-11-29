package com.adaptionsoft.games.trivia.runner

import com.adaptionsoft.games.uglytrivia.Game
import java.util.Random


fun main(vararg args: String) {
    var notAWinner: Boolean;
    val aGame = Game()
    aGame.add("Chet")
    aGame.add("Pat")
    aGame.add("Sue")

    val rand = Random()

    do {
        aGame.roll(rand.nextInt(5) + 1)
        if (rand.nextInt(9) == 7) {
            notAWinner = aGame.wrongAnswer()
        } else {
            notAWinner = aGame.wasCorrectlyAnswered()
        }
    } while (notAWinner)
}
