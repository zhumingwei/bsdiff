//
// Created by zhumingwei on 2020/9/10.
//

#include <jni.h>
#include <AndroidLog.h>
#include <string.h>


extern "C" {
    #include <bspatch.h>
}

extern "C"
JNIEXPORT void JNICALL
Java_com_zhumingwei_bilidiff_BSUtil_bsPatch(
        JNIEnv *env,
        jclass clazz,
        jstring old_file,
        jstring patch,
        jstring output) {

    const char *oldfilePathString = env->GetStringUTFChars(old_file, NULL);
    const char *patchString = env->GetStringUTFChars(patch, NULL);
    const char *outputString = env->GetStringUTFChars(output, NULL);
    ALOGD("bsPatch oldfile%s", oldfilePathString);
    ALOGD("bsPatch patch%s", patchString);
    ALOGD("bsPatch output%s", outputString);
    bsPatchFile(oldfilePathString,outputString,patchString);
    env->ReleaseStringUTFChars(old_file, oldfilePathString);
    env->ReleaseStringUTFChars(patch, patchString);
    env->ReleaseStringUTFChars(output, outputString);

}




extern "C"
JNIEXPORT void JNICALL
Java_com_zhumingwei_bilidiff_BSUtil_bsPatchFile(JNIEnv *env, jclass clazz, jobject old_file,
                                                jobject patch_file, jobject new_file) {
    //todo
    jclass class_fdesc =env->FindClass("java/io/FileDescriptor");
    if(class_fdesc == NULL){
        ALOGE("Can't find java/io/FileDescriptor");
        return;
    }
}