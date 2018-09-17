//
// Created by 许 on 2018/9/10.
//

#ifndef ADDUPDATE_ADD_UPDATE_H
#define ADDUPDATE_ADD_UPDATE_H
#include <malloc.h>
#include <jni.h>

#endif //ADDUPDATE_ADD_UPDATE_H

int mydiff(int argc,char *argv[]);
int mypatch(int argc,char * argv[]);

JNIEXPORT void JNICALL
Java_com_home_update_add_addupdate_MainActivity_diff(JNIEnv *env, jobject instance,
                                                     jstring oldApkPath_, jstring newApkPath_,
                                                     jstring patchPath_);

JNIEXPORT void JNICALL
Java_com_home_update_add_addupdate_MainActivity_patch(JNIEnv *env, jobject instance,
                                                      jstring oldApkPath_, jstring patchPath_,
                                                      jstring newApkPath_);