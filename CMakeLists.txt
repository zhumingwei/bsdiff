cmake_minimum_required(VERSION 3.17)
project(Bilibsdiff C)
project(Bilibspatch C)

set(CMAKE_C_STANDARD 99)
set( CMAKE_CXX_FLAGS "-std=c++11" )
SET(CMAKE_BUILD_TYPE "Debug")

add_subdirectory(${CMAKE_SOURCE_DIR}/bzlib)
add_executable(Bilibsdiff bsdiff.c bsdiff.h)
TARGET_LINK_LIBRARIES(Bilibsdiff bzip2)

add_executable(Bilibspatch bspatch.c bspatch.h)
TARGET_LINK_LIBRARIES(Bilibspatch bzip2)