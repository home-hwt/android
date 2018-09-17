//
// Created by 许 on 2018/9/10.
//

#include "add-update.h"


JNIEXPORT void JNICALL
Java_com_home_update_add_addupdate_MainActivity_diff(JNIEnv *env, jobject instance,
                                                     jstring oldApkPath_, jstring newApkPath_,
                                                     jstring patchPath_) {
    const char *argv[4];
    argv[0] = "bsdiff";

    argv[1] = (*env)->GetStringUTFChars(env, oldApkPath_, 0);
    argv[2] = (*env)->GetStringUTFChars(env, newApkPath_, 0);
    argv[3] = (*env)->GetStringUTFChars(env, patchPath_, 0);

    mydiff(4, argv);

    (*env)->GetStringUTFChars(env, oldApkPath_, argv[1]);
    (*env)->GetStringUTFChars(env, newApkPath_, argv[2]);
    (*env)->GetStringUTFChars(env, patchPath_, argv[3]);
}

JNIEXPORT void JNICALL
Java_com_home_update_add_addupdate_MainActivity_patch(JNIEnv *env, jobject instance,
                                                      jstring oldApkPath_, jstring patchPath_,
                                                      jstring newApkPath_) {

    const char *argv[4];
    argv[0] = "bspatch";

    argv[1] = (*env)->GetStringUTFChars(env, oldApkPath_, 0);
    argv[3] = (*env)->GetStringUTFChars(env, patchPath_, 0);
    argv[2] = (*env)->GetStringUTFChars(env, newApkPath_, 0);

    mypatch(4, argv);
    // TODO

    (*env)->GetStringUTFChars(env, oldApkPath_, argv[1]);
    (*env)->GetStringUTFChars(env, patchPath_, argv[2]);
    (*env)->GetStringUTFChars(env, newApkPath_, argv[3]);
}