#!/bin/sh

# paths relative to project root
PROJECT_DIR=${PWD}
BUILD_DIR=${PROJECT_DIR}/cmake-build-shell

function cleanAndPrepare() {
    echo "------------------------------------"
    echo "-------- cleanAndPrepare: start build (${START_TIME}) "
    echo "------------------------------------"

    rm -rf "${BUILD_DIR}"
    mkdir "${BUILD_DIR}" || exit 1
}

function runCmake() {
    echo "------------------------------------"
    echo "-------- cmake: start build (${START_TIME}) "
    echo "------------------------------------"

    cd "${BUILD_DIR}" || exit 1
    cmake --version
    cmake "${PROJECT_DIR}" || exit 1
    cd "${PROJECT_DIR}" || exit 1
}

function runMake() {
    echo "------------------------------------"
    echo "-------- buildAllModules: start build (${START_TIME}) "
    echo "------------------------------------"

    cd "${BUILD_DIR}" || exit 1
    make || exit 1
    cd "${PROJECT_DIR}" || exit 1
}

function runAllTests() {
    echo "------------------------------------"
    echo "-------- runAllTests: start build (${START_TIME}) "
    echo "------------------------------------"

    cd "${BUILD_DIR}" || exit 1
    ctest "${PROJECT_DIR}" || exit 1
    cd "${PROJECT_DIR}" || exit 1
}

function runPCLint() {
    echo "------------------------------------"
    echo "-------- runPCLint: start build (${START_TIME}) "
    echo "------------------------------------"

    cd "${BUILD_DIR}" || exit 1
    make Trivia_lint || exit 1
    cd "${PROJECT_DIR}" || exit 1
}

function buildAllModules() {
    cleanAndPrepare
    runCmake
    runMake
}


function defaultBuild() {
    cleanAndPrepare
    runCmake
    runMake
    runAllTests
    runPCLint
}

echo "build with directories..."
echo "    PROJECT_DIR: ${PROJECT_DIR}"
echo "    BUILD_DIR: ${BUILD_DIR}"

case "$1" in
    clean)
        cleanAndPrepare
        ;;
    cmake)
        runCmake
        ;;
    make)
        runMake
        ;;
    build)
        buildAllModules
        ;;
    test)
        runMake
        runAllTests
        ;;
    lint)
        runPCLint
        ;;
    *)
        defaultBuild
        ;;
esac
