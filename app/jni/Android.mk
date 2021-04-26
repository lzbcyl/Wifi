LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
# 导出的so库名字
LOCAL_MODULE :=t
# 对应的c代码
LOCAL_SRC_FILES :=jni/t.c
include $(BUILD_SHARED_LIBRARY)
