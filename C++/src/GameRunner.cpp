#include <stdlib.h>
#include "Game.h"



void runGame() {
    Game aGame;

    aGame.add("Chet");
    aGame.add("Pat");
    aGame.add("Sue");

    bool notAWinner;

    do
    {

        aGame.roll(rand() % 5 + 1);

        if (rand() % 9 == 7)
        {
            notAWinner = aGame.wrongAnswer();
        }
        else
        {
            notAWinner = aGame.wasCorrectlyAnswered();
        }
    } while (notAWinner);
}
