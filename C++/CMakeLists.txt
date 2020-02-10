cmake_minimum_required(VERSION 3.5)
project (Trivia CXX)

set (CMAKE_CXX_STANDARD 17)

set(SOURCES
        src/GameRunner.cpp
        src/Game.cpp
        )

add_executable (Trivia ${SOURCES})

target_include_directories (Trivia
        PRIVATE
        ${PROJECT_SOURCE_DIR}/include
        )

set_target_properties(Trivia
        PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/out
        )

# OPTS = -std=c++17 -Wall -Wextra -Werror
# OUTOPTS = -o
# OUT = out/trivia
# TEST_OUT = out/trivia_tests