#include "gtest/gtest.h"
#include "Game.h"

class GameTest : public ::testing::Test {
};

TEST(GameTest, TestGame) {
    Game aGame;

    ASSERT_FALSE(aGame.isPlayable());

    aGame.add("Klaus");
    ASSERT_FALSE(aGame.isPlayable());

    aGame.add("Horst");
    ASSERT_TRUE(aGame.isPlayable());
}
