#include "Jgles2_GLES2.h"
#include <GLES2/gl2.h>
#include <assert.h>

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBindFramebuffer
  (JNIEnv *e, jclass c, jint target, jint framebuffer)
{
    glBindFramebuffer(target,framebuffer);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGenFramebuffers
  (JNIEnv *e, jclass c, jint n, jobject jbuffs)
{
    void* buffers = (void*)((*e)->GetDirectBufferAddress(e, jbuffs));
    glGenFramebuffers(n,buffers);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glRenderbufferStorage
  (JNIEnv *e, jclass c, jint target, jint intFormat, jint width, jint height)
{
    glRenderbufferStorage(target,intFormat,width,height);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGenRenderbuffers
  (JNIEnv *e, jclass c, jint n, jobject jrbuffs)
{
    void* rbuffs = (void*)((*e)->GetDirectBufferAddress(e, jrbuffs));
    glGenRenderbuffers(n,rbuffs);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBindRenderbuffer
  (JNIEnv *e, jclass c, jint target, jint renderbuffer)
{
    glBindRenderbuffer(target,renderbuffer);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glFramebufferTexture2D(JNIEnv *e, jclass c, jint target,
        jint attachment, jint textarget, jint texture, jint level)
{
    glFramebufferTexture2D(target,attachment,textarget,texture,level);
}
  
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glActiveTexture
  (JNIEnv *e, jclass c, jint t)
{
    glActiveTexture(t);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGenTextures
  (JNIEnv *e, jclass c, jint num, jobject jnames)
{
    int* names = (int*)((*e)->GetDirectBufferAddress(e, jnames));
    glGenTextures(num,names);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBindTexture
  (JNIEnv *e, jclass c, jint target, jint texture)
{
    glBindTexture(target,texture);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glTexParameterf
  (JNIEnv *e, jclass c, jint target, jint pname, jfloat param)
{
    glTexParameterf(target,pname,param);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glTexParameteri
  (JNIEnv *e, jclass c, jint target, jint pname, jint param)
{
    glTexParameteri(target,pname,param);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glTexImage2D
  (JNIEnv *e, jclass c, jint target, jint level, jint internalformat, 
        jint width, jint height, jint border, jint format, jint type, jobject jpixels)
{
    void* pixels = (void*)((*e)->GetDirectBufferAddress(e, jpixels));
    glTexImage2D(target,level,internalformat,width,height,border,format,type,pixels);
}

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

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDrawArrays
  (JNIEnv *e, jclass c, jint mode, jint first, jint count)
{
    glDrawArrays(mode,first,count);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttribPointer
  (JNIEnv *e, jclass c, jint index, jint size, jint type, jint normalized, jint stride, jobject pointer)
{
    GLvoid* ptr = (GLvoid*)(*e)->GetDirectBufferAddress(e, pointer);
    glVertexAttribPointer(index,size,type,normalized,stride,ptr);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniformMatrix4fv
  (JNIEnv *e, jclass c, jint location, jint count, jint transpose, jobject pointer)
{
    GLvoid* ptr = (GLvoid*)(*e)->GetDirectBufferAddress(e, pointer);
    glUniformMatrix4fv(location,count,transpose,ptr);        
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glClear
  (JNIEnv *e, jclass c, jint buffs)
{
    glClear(buffs);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBlendColor
  (JNIEnv *e, jclass c, jfloat r, jfloat g, jfloat b, jfloat a)
{
    glBlendColor(r,g,b,a);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBlendEquation
  (JNIEnv *e, jclass c, jint m)
{
    glBlendEquation(m);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBlendEquationSeparate
  (JNIEnv *e, jclass c, jint m1, jint m2)
{
    glBlendEquationSeparate(m1,m2);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glEnable
  (JNIEnv *e, jclass cls, jint c)
{
    glEnable(c);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBlendFunc
  (JNIEnv *e, jclass c, jint s, jint d)
{
    glBlendFunc(s,d);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBlendFuncSeparate
  (JNIEnv *e, jclass c, jint sc, jint dc, jint sa, jint da)
{
    glBlendFuncSeparate(sc,dc,sa,da);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBufferData
  (JNIEnv *e, jclass c, jint t, jlong s, jobject jd, jint u)
{
    GLvoid* d = (GLvoid*)(*e)->GetDirectBufferAddress(e, jd);
    glBufferData(t,s,d,u);    
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glBufferSubData
  (JNIEnv *e, jclass c, jint t, jlong o, jlong s, jobject jd)
{
    GLvoid* d = (GLvoid*)(*e)->GetDirectBufferAddress(e, jd);
    glBufferSubData(t,o,s,d);
}

JNIEXPORT jint JNICALL Java_Jgles2_GLES2_glCheckFramebufferStatus
  (JNIEnv *e, jclass c, jint n)
 {
     return glCheckFramebufferStatus(n);
 } 

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glClearDepthf
  (JNIEnv *e, jclass c, jfloat d)
{
    glClearDepthf(d);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glClearStencil
  (JNIEnv *e, jclass c, jint s)
{
    glClearStencil(s);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glColorMask
  (JNIEnv *e, jclass c, jboolean r, jboolean g, jboolean b, jboolean a)
{
    glColorMask(r,g,b,a);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glCompressedTexImage2D
  (JNIEnv *e, jclass c, jint t, jint l, jint i, jint w, jint h, jint b, jint s, jobject jd)
{
    GLvoid* d = (GLvoid*)(*e)->GetDirectBufferAddress(e, jd);
    glCompressedTexImage2D(t,l,i,w,h,b,s,d) ;    
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glCompressedTexSubImage2D
  (JNIEnv *e, jclass c, jint t, jint l, jint x, jint y, jint w, jint h, jint f, jint i, jobject jd)
{
    GLvoid* d = (GLvoid*)(*e)->GetDirectBufferAddress(e, jd);
    glCompressedTexSubImage2D(t,l,x,y,w,h,f,i,d);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glCopyTexImage2D
  (JNIEnv *e, jclass c, jint t, jint l, jint i, jint x, jint y, jint w, jint h, jint b)
{
    glCopyTexImage2D(t,l,i,x,y,w,h,b);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glCopyTexSubImage2D
  (JNIEnv * e, jclass c, jint t, jint l, jint xo, jint yo, jint x, jint y, jint w, jint h)
{
    glCopyTexSubImage2D(t,l,xo,yo,x,y,w,h);
}  

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glCullFace
  (JNIEnv *e, jclass c, jint m)
{
    glCullFace(m);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDeleteBuffers
  (JNIEnv *e, jclass c, jint n, jobject jnm)
{
    GLvoid* nm = (GLvoid*)(*e)->GetDirectBufferAddress(e, jnm);
    glDeleteBuffers(n,nm);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDeleteFramebuffers
  (JNIEnv *e, jclass c, jint n, jobject jnm) 
{
    GLvoid* nm = (GLvoid*)(*e)->GetDirectBufferAddress(e, jnm);
    glDeleteFramebuffers(n,nm);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDeleteProgram
  (JNIEnv *e, jclass c, jint n)
{
    glDeleteProgram(n);
}
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDeleteRenderbuffers
  (JNIEnv *e, jclass c, jint n, jobject jnm)
{
    GLvoid* nm = (GLvoid*)(*e)->GetDirectBufferAddress(e, jnm);
    glDeleteRenderbuffers(n,nm);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDeleteShader
  (JNIEnv *e, jclass c, jint n)
{
    glDeleteShader(n);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDeleteTextures
  (JNIEnv *e, jclass c, jint n, jobject jnm) 
{
    GLvoid* nm = (GLvoid*)(*e)->GetDirectBufferAddress(e, jnm);
    glDeleteTextures(n,nm);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDepthFunc
  (JNIEnv *e, jclass c, jint f)
{
    glDepthFunc(f);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDepthMask
  (JNIEnv *e, jclass c, jboolean f)
{
    glDepthMask(f);
}



JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDepthRangef
  (JNIEnv *e, jclass c, jfloat f, jfloat t) 
{
      glDepthRangef(f,t);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDetachShader
  (JNIEnv *e, jclass c, jint p, jint s)
{
    glDetachShader(p,s);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDrawElements
  (JNIEnv *e, jclass c, jint m, jint n, jint t, jobject ji)
{
    GLvoid* i = (GLvoid*)(*e)->GetDirectBufferAddress(e, ji);    
    glDrawElements(m,n,t,i);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glDisable
  (JNIEnv *e, jclass c, jint m)
{
    glDisable(m);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glFinish
  (JNIEnv *e, jclass c)
{
    glFinish();
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glFlush
  (JNIEnv *e, jclass c)
{
    glFlush();
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glFramebufferRenderbuffer
  (JNIEnv *e, jclass c, jint t, jint a, jint rt, jint rb)
{
    glFramebufferRenderbuffer(t,a,rt,rb);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glFrontFace
  (JNIEnv *e, jclass c, jint w)
{
    glFrontFace(w);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGenBuffers
  (JNIEnv *e, jclass c, jint n, jobject jb)
{
    GLvoid* b = (GLvoid*)(*e)->GetDirectBufferAddress(e, jb);
    glGenBuffers(n,b);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGenerateMipmap
  (JNIEnv *e, jclass c, jint l)
{
    glGenerateMipmap(l);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetBooleanv
  (JNIEnv *e, jclass c, jint p, jobject jb)
{
    GLvoid* b = (GLvoid*)(*e)->GetDirectBufferAddress(e, jb);
    glGetBooleanv(p,b);   
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetFloatv
  (JNIEnv *e, jclass c, jint p, jobject jb)
{
    GLvoid* b = (GLvoid*)(*e)->GetDirectBufferAddress(e, jb);
    glGetFloatv(p,b);   
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetIntegerv
  (JNIEnv *e, jclass c, jint p, jobject jb)
{
    GLvoid* b = (GLvoid*)(*e)->GetDirectBufferAddress(e, jb);
    glGetIntagerv(p,b);   
}

JNIEXPORT jint JNICALL Java_Jgles2_GLES2_glGetAttribLocation
  (JNIEnv *e, jclass c, jint prog, jstring jname)
{
    const char *name = (*e)->GetStringUTFChars(e, jname, 0);
    int r=glGetAttribLocation(prog,name);
    (*e)->ReleaseStringUTFChars(e, jname, name);
    return r;    
}
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetBufferParameteriv
  (JNIEnv *e, jclass c, jint t, jint v, jobject jd)
{
    GLvoid* d = (GLvoid*)(*e)->GetDirectBufferAddress(e, jd);
    glGetBufferParameteriv(t,v,d);
}

JNIEXPORT jint JNICALL Java_Jgles2_GLES2_glGetError
  (JNIEnv *e, jclass c)
{
    return glGetError();
}

//    public static native void glGetFramebufferAttachmentParameteriv(int target,
//                    int attachment,int pname,IntBuffer params);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetFramebufferAttachmentParameteriv
  (JNIEnv *e, jclass c, jint t, jint a, jint n, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetFramebufferAttachmentParameteriv(t,a,n,p);
}

//    public static native void glGetRenderbufferParameteriv(int target,int pname,IntBuffer params);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetRenderbufferParameteriv
  (JNIEnv *e, jclass c, jint t, jint n, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetRenderbufferParameteriv(t,n,p);
}

//    public static native void glGetShaderPrecisionFormat(	int shaderType,int precisionType,
//                    IntBuffer range,IntBuffer precision) ;
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetShaderPrecisionFormat
  (JNIEnv *e, jclass c, jint st, jint pt, jobject jr, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    GLvoid* r = (GLvoid*)(*e)->GetDirectBufferAddress(e, jr);
    glGetShaderPrecisionFormat(st,pt,r,p);
}

//    public static native void glGetShaderSource(int shader,int bufSize,
//                    IntBuffer length,ByteBuffer source);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetShaderSource
  (JNIEnv *e, jclass c, jint s, jint sz, jobject jl, jobject jsrc)
{
    GLvoid* l = (GLvoid*)(*e)->GetDirectBufferAddress(e, jl);
    GLvoid* src = (GLvoid*)(*e)->GetDirectBufferAddress(e, jsrc);
    glGetShaderSource(s,sz,l,src);
}

//    public static native void glGetTexParameterfv(int target,int pname,FloatBuffer params);
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetTexParameterfv
  (JNIEnv *e, jclass c, jint t, jint n, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetTexParameterfv(t,n,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetTexParameteriv
  (JNIEnv *e, jclass c, jint t, jint n, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetTexParameteriv(t,n,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetUniformfv
  (JNIEnv *e, jclass c, jint pr, jint l, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetUniformfv(pr,l,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetUniformiv
  (JNIEnv *e, jclass c, jint pr, jint l, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetUniformiv(pr,l,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetVertexAttribfv
  (JNIEnv *e, jclass c, jint pr, jint l, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetVertexAttribfv(pr,l,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glGetVertexAttribiv
  (JNIEnv *e, jclass c, jint pr, jint l, jobject jp)
{
    GLvoid* p = (GLvoid*)(*e)->GetDirectBufferAddress(e, jp);
    glGetVertexAttribiv(pr,l,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glHint
  (JNIEnv *e, jclass c, jint t, jint m)
{
    glHint(t,m);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsBuffer
  (JNIEnv *e, jclass c, jint i)
{
    return glIsBuffer(i);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsEnabled
  (JNIEnv *e, jclass c, jint i)
{
    return glIsEnabled(i);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsFramebuffer
  (JNIEnv *e, jclass c, jint i)
{
    return glIsFramebuffer(i);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsProgram
  (JNIEnv *e, jclass c, jint i)
{
    return glIsProgram(i);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsRenderbuffer
  (JNIEnv *e, jclass c, jint i)
{
    return glIsRenderbuffer(i);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsShader
  (JNIEnv *e, jclass c, jint i)
{
    return glIsShader(i);
}

JNIEXPORT jboolean JNICALL Java_Jgles2_GLES2_glIsTexture
  (JNIEnv *e, jclass c, jint i)
{
    return glIsTexture(i);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glLineWidth
  (JNIEnv *e, jclass c, jfloat f)
{
    glLineWidth(f);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glPixelStorei
  (JNIEnv *e, jclass c, jint pn, jint p)
{
    glPixelStori(pn,p);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glPolygonOffset
  (JNIEnv *e, jclass c, jfloat f, jfloat u)
{
    glPolygonOffset(f,u);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glReleaseShaderCompiler
  (JNIEnv *e, jclass c)
{
    glReleaseShaderCompiler();
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glSampleCoverage
  (JNIEnv *e, jclass c, jfloat f, jboolean i)
{
    glSampleCoverage(f,i);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glScissor
  (JNIEnv *e, jclass c, jint x, jint y, jint w, jint h)
{
    glScissor(x,y,w,h);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glShaderBinary
  (JNIEnv *env, jclass cls, jint a, jobject b, jint c, jobject d, jint e)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glStencilFunc
  (JNIEnv *e, jclass c, jint f, jint r, jint m)
{
    glStencilFunc(f,r,m);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glStencilFuncSeparate
  (JNIEnv *e, jclass c, jint fc, jint f, jint r, jint m)
{
    glStencilFuncSeperate(fc,f,r,m);
}
/*
JNIEXPORT void JNICALL Java_Jgles2_GLES2_glStencilMask
  (JNIEnv *e, jclass c, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glStencilMaskSeparate
  (JNIEnv *e, jclass c, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glStencilOp
  (JNIEnv *e, jclass c, jint, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glStencilOpSeparate
  (JNIEnv *e, jclass c, jint, jint, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glTexParameterfv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glTexParameteriv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform1f
  (JNIEnv *e, jclass c, jint, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform2f
  (JNIEnv *e, jclass c, jint, jfloat, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform3f
  (JNIEnv *e, jclass c, jint, jfloat, jfloat, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform4f
  (JNIEnv *e, jclass c, jint, jfloat, jfloat, jfloat, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform1i
  (JNIEnv *e, jclass c, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform2i
  (JNIEnv *e, jclass c, jint, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform3i
  (JNIEnv *e, jclass c, jint, jint, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform4i
  (JNIEnv *e, jclass c, jint, jint, jint, jint, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform1fv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform2fv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform3fv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform4fv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform1iv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform2iv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform3iv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniform4iv
  (JNIEnv *e, jclass c, jint, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniformMatrix2fv
  (JNIEnv *e, jclass c, jint, jint, jboolean, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glUniformMatrix3fv
  (JNIEnv *e, jclass c, jint, jint, jboolean, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glValidateProgram
  (JNIEnv *e, jclass c, jint)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib1f
  (JNIEnv *e, jclass c, jint, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib2f
  (JNIEnv *e, jclass c, jint, jfloat, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib3f
  (JNIEnv *e, jclass c, jint, jfloat, jfloat, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib4f
  (JNIEnv *e, jclass c, jint, jfloat, jfloat, jfloat, jfloat)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib1fv
  (JNIEnv *e, jclass c, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib2fv
  (JNIEnv *e, jclass c, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib3fv
  (JNIEnv *e, jclass c, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}

JNIEXPORT void JNICALL Java_Jgles2_GLES2_glVertexAttrib4fv
  (JNIEnv *e, jclass c, jint, jobject)
{
    assert("dont waste time debugging your code this call isnt implemented! send patch or ask for implementation." && 0);
}
*/
