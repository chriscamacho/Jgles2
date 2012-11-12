#include "Jgles2_util.h"
#include <EGL/egl.h>

JNIEXPORT jlong JNICALL Java_Jgles2_EGL_eglGetDisplay
                                (JNIEnv *e, jclass c, jlong d) 
{
    return (jlong)eglGetDisplay((NativeDisplayType)d);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_EGL_eglInitialize
                                (JNIEnv *e, jclass c, jlong d ) 
{
    return (jboolean)eglInitialize((EGLDisplay)d,NULL,NULL);
}


JNIEXPORT jstring JNICALL Java_Jgles2_EGL_eglQueryString
                                (JNIEnv *e, jclass c, jlong d, jint n) 
{
    char const* QueryString;
    QueryString = eglQueryString((EGLDisplay)d, (EGLint)n);
    return (*e)->NewStringUTF(e, QueryString); 
}

JNIEXPORT jboolean JNICALL Java_Jgles2_EGL_eglChooseConfig
  (JNIEnv *env, jclass c, jlong display, jobject jattribs, jobject jconfigs, jint size, jobject jnum)
{
    int num=0;
    EGLint const* attribs = (EGLint const*)(*env)->GetDirectBufferAddress(env, jattribs);
    void* configs = (void*)(*env)->GetDirectBufferAddress(env, jconfigs);
    
    int r = eglChooseConfig((EGLDisplay)display,attribs,configs,size,&num);
    EGLint* ret = (EGLint*)((*env)->GetDirectBufferAddress(env, jnum));
    ret[0] = num;
   
    return r;
}

JNIEXPORT jlong JNICALL Java_Jgles2_EGL_eglCreateContext
        (JNIEnv *env, jclass c, jlong disp, jlong conf, jlong share, jobject jattrib) 
{
    EGLint const* attribs = (EGLint const*)(*env)->GetDirectBufferAddress(env, jattrib);
    return (jlong)eglCreateContext((EGLDisplay)disp, (EGLConfig)conf, (EGLContext)share, attribs);
}

JNIEXPORT jint JNICALL Java_Jgles2_EGL_eglQueryContext
        (JNIEnv *env, jclass c, jlong disp, jlong ctx, jint atrib, jobject val) 
{
    int v=0;
    int r=eglQueryContext((EGLDisplay)disp,(EGLContext)ctx, atrib, &v);
    EGLint* ret = (EGLint*)((*env)->GetDirectBufferAddress(env, val));
    ret[0] = v;

    return r;
}

JNIEXPORT jlong JNICALL Java_Jgles2_EGL_eglCreateWindowSurface
  (JNIEnv *env, jclass c, jlong jdisp, jlong jconf, jlong jwin, jobject jattribs)
{
    EGLint* attribs = 0;
    if (jattribs!=0) attribs = (EGLint*)(*env)->GetDirectBufferAddress(env, jattribs);
    return (jlong)eglCreateWindowSurface((EGLDisplay)jdisp,(EGLConfig)jconf,(NativeWindowType)jwin,attribs);
}

JNIEXPORT void JNICALL Java_Jgles2_EGL_eglQuerySurface
  (JNIEnv *env, jclass c, jlong disp, jlong surf, jint atrib, jobject val)
{
    int v=0;
    eglQuerySurface((EGLDisplay)disp,(EGLSurface)surf,atrib,&v);
    EGLint* ret = (EGLint*)((*env)->GetDirectBufferAddress(env, val));
    ret[0] = v;    
}

JNIEXPORT void JNICALL Java_Jgles2_EGL_eglGetConfigAttrib
  (JNIEnv *env, jclass c, jlong disp, jlong conf, jint atrib, jobject val)
{
    int v=0;
    eglGetConfigAttrib((EGLDisplay)disp, (EGLConfig)conf, atrib, &v);
    EGLint* ret = (EGLint*)((*env)->GetDirectBufferAddress(env, val));
    ret[0] = v;    
}

JNIEXPORT jboolean JNICALL Java_Jgles2_EGL_eglMakeCurrent
  (JNIEnv *e, jclass c, jlong disp, jlong draw, jlong read, jlong context)
{
    return eglMakeCurrent((EGLDisplay)disp, (EGLSurface)draw, (EGLSurface)read, (EGLContext)context);
}

JNIEXPORT jint JNICALL Java_Jgles2_EGL_eglGetError
  (JNIEnv *e, jclass c)
{
    return eglGetError();
}

JNIEXPORT void JNICALL Java_Jgles2_EGL_eglSwapBuffers
  (JNIEnv *e, jclass c, jlong disp, jlong surf)
{
    eglSwapBuffers((EGLDisplay)disp,(EGLSurface)surf);
}
