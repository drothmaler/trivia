#include "gtest/gtest.h"
#include "Game.h"

class GameTest : public ::testing::Test {
};

TEST(GameTest, TestGame) {
    Game aGame;

    ASSERT_TRUE(aGame.isPlayable());
}