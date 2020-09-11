# 设置cmake最低版本
cmake_minimum_required(VERSION 3.4.1)
SET(SRC_PATH_ROOT   "${CMAKE_SOURCE_DIR}/../../../../../../..")
SET(SRC_PATH_ROOT_BZLIB   "${CMAKE_SOURCE_DIR}/../../../../../../../bzlib")
SET(
        SRC_FILE_BSPATCH
        ${SRC_PATH_ROOT}/bspatch.c
)
SET(
        SRC_FILE_BSDIFF
        ${SRC_PATH_ROOT}/bsdiff.c
)
SET(
        SRC_FILE_BZLIB
        ${SRC_PATH_ROOT_BZLIB}/blocksort.c
        ${SRC_PATH_ROOT_BZLIB}/bzlib.c
        ${SRC_PATH_ROOT_BZLIB}/compress.c
        ${SRC_PATH_ROOT_BZLIB}/crctable.c
        ${SRC_PATH_ROOT_BZLIB}/decompress.c
        ${SRC_PATH_ROOT_BZLIB}/huffman.c
        ${SRC_PATH_ROOT_BZLIB}/randtable.c
)
include_directories(
        common
        ${SRC_PATH_ROOT}
        ${SRC_PATH_ROOT}/bzlib
        include
)

MESSAGE( STATUS "this var path = ${CMAKE_SOURCE_DIR}.")



# 添加log
find_library(
        log-libs
        log
)

add_library(
        bspatch#name
        SHARED
        bspatch.cpp
        ${SRC_FILE_BZLIB}
        ${SRC_FILE_BSPATCH}
)

target_link_libraries(
        bspatch
        ${log-libs}
)


add_library(
        bsdiff#name
        SHARED
        bsdiff.cpp
        ${SRC_FILE_BZLIB}
        ${SRC_FILE_BSDIFF}
)

target_link_libraries(
        bsdiff
        ${log-libs}
)