//
// Created by lzb on 2021/4/21.
//
JNIEXPORT jstring

jstring
Java_com_cyl_wifi_view_CallUtils_callSimpleInfo(JNIEnv *env, jclass clazz) {
    // TODO: implement callSimpleInfo()
    return (*env) -> NewStringUTF(env,"Hello, I'm an info come from use ndk-build");
}
