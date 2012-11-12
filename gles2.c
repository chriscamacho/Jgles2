#include "Jgles2_GLES2.h"
#include <GLES2/gl2.h>

JNIEXPORT jstring JNICALL Java_Jgles2_GLES2_glGetString
  (JNIEnv *e, jclass c, jint attrib)
{
    char const* GetString;
    GetString = glGetString((GLenum)attrib);
    return (*e)->NewStringUTF(e, GetString); 
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glClearColor
  (JNIEnv *e, jclass c, jfloat r, jfloat g, jfloat b, jfloat a)
{
    glClearColor(r,g,b,a);
}

JNIEXPORT jint JNICALL Java_Jgles2_GLES2_glCreateShader
  (JNIEnv *e, jclass c, jint type)
{
    return glCreateShader(type);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glShaderSource
  (JNIEnv *e, jclass c, jint shader, jstring jsource)
{
    const char *source = (*e)->GetStringUTFChars(e, jsource, 0);
    glShaderSource(shader, 1, (const char **)&source, NULL);
    (*e)->ReleaseStringUTFChars(e, jsource, source);    
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glCompileShader
  (JNIEnv *e, jclass c, jint shader)
{
    glCompileShader(shader);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetShaderiv
  (JNIEnv *e, jclass c, jint shader, jint attrib, jobject val)
{
    int v=0;
    glGetShaderiv(shader,attrib,&v);
    int* ret = (int*)((*e)->GetDirectBufferAddress(e, val));
    ret[0] = v;
}

JNIEXPORT jint JNICALL Java_Jgles2_GLES2_glCreateProgram
  (JNIEnv *e, jclass c) 
{
    return glCreateProgram();
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glAttachShader
  (JNIEnv *e, jclass c, jint prg, jint sdr) 
{
    glAttachShader(prg,sdr);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glLinkProgram
  (JNIEnv *e, jclass c, jint p)
{
    glLinkProgram(p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetProgramiv
  (JNIEnv *e, jclass c, jint p, jint a, jobject val)
{
    int v=0;
    glGetProgramiv(p,a,&v);
    int* ret = (int*)((*e)->GetDirectBufferAddress(e, val));
    ret[0] = v;
}

JNIEXPORT jstring JNICALL Java_Jgles2_GLES2_glGetProgramInfoLog
  (JNIEnv *e, jclass c, jint program)
{
    char log[1024];
    GLsizei len;
    glGetProgramInfoLog(program, 1024, &len, log);
    return (*e)->NewStringUTF(e, &log[0]); 
}

JNIEXPORT jstring JNICALL Java_Jgles2_GLES2_glGetShaderInfoLog
  (JNIEnv *e, jclass c, jint shader)
{
    char log[1024];
    GLsizei len;
    glGetShaderInfoLog(shader, 1024, &len, log);
    return (*e)->NewStringUTF(e, &log[0]); 
}

//    public static native void glBindAttribLocation(int program, int loc, String name);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBindAttribLocation
  (JNIEnv *e, jclass c, jint prog, jint loc, jstring jname)
{
    const char *name = (*e)->GetStringUTFChars(e, jname, 0);
    glBindAttribLocation(prog,loc,name);
    (*e)->ReleaseStringUTFChars(e, jname, name);        
}

//    public static native int glGetUniformLocation(int program, String name);
JNIEXPORT jint JNICALL Java_Jgles2_GLES2_glGetUniformLocation
  (JNIEnv *e, jclass c, jint prog, jstring jname)
{
    const char *name = (*e)->GetStringUTFChars(e, jname, 0);
    int r=glGetUniformLocation(prog,name);
    (*e)->ReleaseStringUTFChars(e, jname, name);
    return r;
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUseProgram
  (JNIEnv *e, jclass c, jint p)
{
    glUseProgram(p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glViewport
  (JNIEnv *e, jclass c, jint x, jint y, jint w, jint h)
{
    glViewport(x,y,w,h);
}

//    public static native void glDisableVertexAttribArray(int index);    
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDisableVertexAttribArray
  (JNIEnv *e, jclass c, jint index)
{
    glDisableVertexAttribArray(index);
}

//    public static native void glEnableVertexAttribArray(int index);    
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glEnableVertexAttribArray
  (JNIEnv *e, jclass c, jint index)
{
    glEnableVertexAttribArray(index);
}

//    public static native void glDrawArrays(int mode,int first,int count);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDrawArrays
  (JNIEnv *e, jclass c, jint mode, jint first, jint count)
{
    glDrawArrays(mode,first,count);
}

//    public static native void glVertexAttribPointer(int index, int size,int type,
//                    int normalized, int stride, FloatBuffer pointer);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttribPointer
  (JNIEnv *e, jclass c, jint index, jint size, jint type, jint normalized, jint stride, jobject pointer)
{
    GLvoid* ptr = (GLvoid*)(*e)->GetDirectBufferAddress(e, pointer);
    glVertexAttribPointer(index,size,type,normalized,stride,ptr);
}

//    public static native void glUniformMatrix4fv(int location, int count,
//                    int transpose,FloatBuffer value);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniformMatrix4fv
  (JNIEnv *e, jclass c, jint location, jint count, jint transpose, jobject pointer)
{
    GLvoid* ptr = (GLvoid*)(*e)->GetDirectBufferAddress(e, pointer);
    glUniformMatrix4fv(location,count,transpose,ptr);        
}

//    public static native void glClear(int buffers); 
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glClear
  (JNIEnv *e, jclass c, jint buffs)
{
    glClear(buffs);
}
