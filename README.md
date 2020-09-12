# bsdiff used 
差异包生成与合成新包

# Overview
核心使用bsdiff算法，这个算法包括两个部分，
bsdiff：根据新包旧包生成差异包
bspatch：根据旧包和差异包生成新包
# Compiling
##### 使用cmake编译本地可执行文件
执行 ./build.sh 生成可执行文件
使用 vscode即可调试，bsdiff/bspatch
[img]
##### 编译android so
cd project/android
./build.sh
编译生成so到
project/android/bin目录下
# Examples
使用androidstudio打开project/android/bilidiff项目编译安装
[img]
# Usage
客户端使用：
粘贴so到我们的主目录libs目录中
粘贴BSUtil到项目中(包名不能改变),如果需要改变修改代码重新编译
然后就可以使用BSUtil提供的两个函数:
public static native void bsPatch(String oldFile,String patch,String output);
public static native void bsDiff(String oldFile,String patch,String output);
电脑端使用:
运行./build.sh，将会在res/bin目录下生成可执行文件。只需要粘贴到系统bin目录下或者将目录添加到环境变量中就可以使用了