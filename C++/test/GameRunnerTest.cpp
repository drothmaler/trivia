#include <iostream>
#include <fstream>
#include <string>
#include <stdio.h>

#include "gtest/gtest.h"
#include "GameRunner.h"

using namespace std;

class GameRunnerTest : public ::testing::Test {
};

TEST(GameRunnerTest, TestGoldenMaster) {
    auto newfile = fopen("../test/golden_master.txt", "r");

    if (newfile == NULL) {
        FAIL();
    }

    string goldenMaster = testing::internal::ReadEntireFile(newfile);

    fclose(newfile);

    testing::internal::CaptureStdout();

    srand(0);
    for (int i = 0; i < 20000; ++i) {
        runGame();
    }

    string output = testing::internal::GetCapturedStdout();

    ASSERT_EQ(output, goldenMaster);
}