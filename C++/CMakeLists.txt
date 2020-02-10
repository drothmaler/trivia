cmake_minimum_required(VERSION 3.5)
project (Trivia CXX)

set (CMAKE_CXX_STANDARD 17)

## Trivia Project ##

set(SOURCES
        src/Game.cpp
)

set(MAIN
        src/GameRunner.cpp
)

set(INCLUDE
        ${PROJECT_SOURCE_DIR}/include
)

add_executable (Trivia ${SOURCES} ${MAIN})
target_include_directories (Trivia PRIVATE ${INCLUDE})
set_target_properties(Trivia
        PROPERTIES
        RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/out
)

# enable CTest testing
enable_testing()
include(${CMAKE_SOURCE_DIR}/cmake/googletest-download.cmake)
include(GoogleTest)

## Unit Tests ##

set(TEST_SOURCES
        test/GameTest.cpp)

add_executable(TriviaTest ${TEST_SOURCES} ${SOURCES})
target_link_libraries(TriviaTest gtest_main)
target_include_directories (TriviaTest PRIVATE ${INCLUDE})

gtest_add_tests(TARGET TriviaTest
        TEST_SUFFIX .noArgs
        TEST_LIST   noArgsTests
)
