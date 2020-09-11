package com.zhumingwei.bilidiff;

import java.io.FileDescriptor;

/**
 * @author zhumingwei
 * @date 2020/9/10 20:34
 * @email zdf312192599@163.com
 */
public class BSUtil {
    static {
        System.loadLibrary("bspatch");
        System.loadLibrary("bsdiff");
    }

    public static native void bsPatchFile(FileDescriptor oldFile,FileDescriptor patchFile, FileDescriptor newFile);
    public static native void bsPatch(String oldFile,String patch,String output);
    public static native void bsDiff(String oldFile,String patch,String output);
}
