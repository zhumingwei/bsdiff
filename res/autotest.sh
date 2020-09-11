#!/bin/bash
clean(){
    rm patch/libapp.so
    rm target/newlibapp.so
}

check(){
  oldsum=$(shasum -a 256 origin/libapp.so)
  oldsum=${oldsum:0:64}
  newsum=$(shasum -a 256  target/libapp.so)
  newsum=${newsum:0:64}
if [ "$oldsum" == "$newsum" ]; then
    echo -e "原始文件文件相同"
    echo "oldsum : ${oldsum}"
    echo "newsum : ${newsum}"
    exit 1
fi
  return 0;
}
clean
check
echo "生成patch......."
# bsdiff origin/libapp.so target/libapp.so patch/libapp.so
bin/Bilibsdiff origin/libapp.so target/libapp.so patch/libapp.so
if [ ! "$?" -eq 0 ]; then
    echo "生成patch失败"
    exit 1;
fi

echo "patchsize:"
du -h patch/libapp.so
echo "生成new file......."
# bspatch origin/libapp.so target/newlibapp.so patch/libapp.so
bin/Bilibspatch origin/libapp.so target/newlibapp.so patch/libapp.so
if [ ! "$?" -eq 0 ]; then
    echo "生成new file失败"
    exit 1;
fi
echo "checking......."
oldsum=$(shasum -a 256 target/libapp.so)
oldsum=${oldsum:0:64}
echo "oldsum : ${oldsum}"

newsum=$(shasum -a 256  target/newlibapp.so)
newsum=${newsum:0:64}
echo "newsum : ${newsum}"

if [ "$oldsum" == "$newsum" ]; then
    echo "success"
    exit 0
else
    echo "failed"
    exit 1
fi