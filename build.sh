#!/bin/bash

mkdir build
cd build
if [ ["$?" != 0] ]; then
    echo "没找到build目录"
fi
cmake ..
make

cp Bilibsdiff ../res/bin/Bilibsdiff
cp Bilibspatch ../res/bin/Bilibspatch
cd ..