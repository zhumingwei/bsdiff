#include <jni.h>
#include <AndroidLog.h>
//
// Created by zhumingwei on 2020/9/10.
//

extern "C" {
#include <bsdiff.h>
}


extern "C"
JNIEXPORT void JNICALL
Java_com_zhumingwei_bilidiff_BSUtil_bsDiff(
        JNIEnv *env,
        jclass clazz,
        jstring old_file,
        jstring patch,
        jstring output) {
    const char *oldfilePathString = env->GetStringUTFChars(old_file, NULL);
    const char *patchString = env->GetStringUTFChars(patch, NULL);
    const char *outputString = env->GetStringUTFChars(output, NULL);
    ALOGD("bsDiff oldfile%s", oldfilePathString);
    ALOGD("bsDiff patch%s", patchString);
    ALOGD("bsDiff output%s", outputString);
    bsdiffFile(oldfilePathString,outputString,patchString);
    env->ReleaseStringUTFChars(old_file,oldfilePathString);
    env->ReleaseStringUTFChars(patch,patchString);
    env->ReleaseStringUTFChars(output,outputString);


}