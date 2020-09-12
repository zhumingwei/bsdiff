echo "start build"
export NDK=~/Library/Android/sdk/ndk-bundle
export MINSDKVERSION=19
cmake_exec=$ANDROID_HOME/cmake/3.6.4111459/bin/cmake
ninja_exec=$ANDROID_HOME/cmake/3.6.4111459/bin/ninja
export CMAKE_GENERATOR="Ninja"
export BUILD_TYPE=Debug #Release、Debug
export OUTPUT_DIR="../bin"
H_dir="bilidiff/app/src/main/cpp/"
B_dir="build"

build(){
    echo "start build $1"
    pre=$(pwd)
    export ANDROID_ABI=$1 # armeabi-v7a、arm64-v8a、x86、x86_64，默认armeabi
    $cmake_exec \
    -H$H_dir \
    -B$B_dir \
    -DANDROID_NDK=${NDK} \
    -DCMAKE_LIBRARY_OUTPUT_DIRECTORY=${OUTPUT_DIR}/${ANDROID_ABI} \
    -DCMAKE_MAKE_PROGRAM=${ANDROID_HOME}/cmake/3.6.4111459/bin/ninja \
    -DCMAKE_TOOLCHAIN_FILE=$NDK/build/cmake/android.toolchain.cmake \
    -DANDROID_ABI=$ANDROID_ABI \
    -DCMAKE_BUILD_TYPE=${BUILD_TYPE} \
    -DANDROID_NATIVE_API_LEVEL=$MINSDKVERSION \
    -DANDROID_TOOLCHAIN=clang \
    -DCMAKE_GENERATOR=$CMAKE_GENERATOR\ 
    cd $B_dir
    $ninja_exec
    cd $pre
}

build "x86"
build "armeabi-v7a"
build "arm64-v8a"
build "x86_64"