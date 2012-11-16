#include "Jgles2_kazmath.h"

#include <vec3.h>
#include <mat4.h>

#include <assert.h>

//    public static native FloatBuffer kmVec3Fill(FloatBuffer v, float x, float y, float z);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Fill
  (JNIEnv *e, jclass c, jobject v, jfloat x, jfloat y, jfloat z)
{
    kmVec3* vec = (kmVec3*)(*e)->GetDirectBufferAddress(e, v);
    kmVec3Fill(vec,(float)x,(float)y,(float)z);
    return v;
}

//     public static native Float kmVec3Length(FloatBuffer in);
JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmVec3Length
  (JNIEnv *e, jclass c, jobject i)
{
    kmVec3* in = (kmVec3*)(*e)->GetDirectBufferAddress(e, i);
    return (jfloat)kmVec3Length(in);
}

JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmVec3LengthSq
  (JNIEnv *e, jclass c, jobject i)
{
    kmVec3* in = (kmVec3*)(*e)->GetDirectBufferAddress(e, i);
    return (jfloat)kmVec3LengthSq(in);
}

//     public static native FloatBuffer kmVec3Normalize(FloatBuffer out,FloatBuffer in);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Normalize
  (JNIEnv *e, jclass c, jobject o, jobject i)
{
    kmVec3* out = (kmVec3*)(*e)->GetDirectBufferAddress(e, o);
    kmVec3* in = (kmVec3*)(*e)->GetDirectBufferAddress(e, i);
    kmVec3Normalize(out,in);
    return o;    
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Cross
  (JNIEnv *e, jclass c, jobject jo, jobject jp1, jobject jp2)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp1);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp2);
    kmVec3Cross(o,p1,p2);
    return jo;
}

JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmVec3Dot
  (JNIEnv *e, jclass c, jobject jp1, jobject jp2)
{
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp1);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp2);
    return kmVec3Dot(p1,p2);
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Add
  (JNIEnv *e, jclass c, jobject jo, jobject a, jobject b)
{
    kmVec3* out = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, a);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, b);
    kmVec3Add(out,p1,p2);
    return jo;
}

//    public static native FloatBuffer kmVec3Subtract(FloatBuffer out,FloatBuffer p1,FloatBuffer p2);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Subtract
  (JNIEnv *e, jclass c, jobject o, jobject a, jobject b)
{
    kmVec3* out = (kmVec3*)(*e)->GetDirectBufferAddress(e, o);
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, a);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, b);
    kmVec3Subtract(out,p1,p2);
    return o;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Transform
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmVec3Transform(o, p, m);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3TransformNormal
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmVec3TransformNormal(o,p,m);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3TransformCoord
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmVec3TransformCoord(o,p,m);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Scale
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jfloat s)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmVec3Scale(o, p, s);
    return jo;
}

JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmVec3AreEqual
  (JNIEnv *e, jclass c, jobject jp1, jobject jp2)
{
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp1);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp2);
    return kmVec3AreEqual(p1,p2);
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3InverseTransform
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmVec3InverseTransform(o, p, m);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3InverseTransformNormal
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmVec3InverseTransformNormal(o, p, m);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Assign
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* i = (kmVec3*)(*e)->GetDirectBufferAddress(e, ji);
    kmVec3Assign(o,i);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Zero
  (JNIEnv *e, jclass c, jobject jo)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3Zero(o);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3GetHorizontalAngle
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* i = (kmVec3*)(*e)->GetDirectBufferAddress(e, ji);
    kmVec3GetHorizontalAngle(o,i);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3RotationToDirection
  (JNIEnv *e, jclass c, jobject jo, jobject ji, jobject jf)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* i = (kmVec3*)(*e)->GetDirectBufferAddress(e, ji);
    kmVec3* f = (kmVec3*)(*e)->GetDirectBufferAddress(e, jf);
    kmVec3RotationToDirection(o,i,f);
    return jo;
}






//    public static native FloatBuffer kmMat4Identity(FloatBuffer mat);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Identity
  (JNIEnv * e, jclass c, jobject m)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Identity(mat);
    return m;
}


//    public static native FloatBuffer kmMat4LookAt(FloatBuffer view,FloatBuffer eye,FloatBuffer centre,FloatBuffer up);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4LookAt
  (JNIEnv *env, jclass cls, jobject v, jobject e, jobject c, jobject u)
{
    kmMat4* view = (kmMat4*)(*env)->GetDirectBufferAddress(env, v);
    kmVec3* eye = (kmVec3*)(*env)->GetDirectBufferAddress(env, e);    
    kmVec3* centre = (kmVec3*)(*env)->GetDirectBufferAddress(env, c);    
    kmVec3* up = (kmVec3*)(*env)->GetDirectBufferAddress(env, u);
    kmMat4LookAt(view,eye,centre,up);
    return v;    
}

//    public static native FloatBuffer kmMat4PerspectiveProjection(FloatBuffer projection, float fov,
//                                float aspect, float near, float far);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4PerspectiveProjection
  (JNIEnv *e, jclass c, jobject p, jfloat fov, jfloat aspect, jfloat near, jfloat far)
{
    kmMat4* proj = (kmMat4*)(*e)->GetDirectBufferAddress(e, p);
    kmMat4PerspectiveProjection(proj,fov,aspect,near,far);
    return p;
}

//     public static native FloatBuffer kmMat4Multiply(FloatBuffer out,FloatBuffer mat1,FloatBuffer mat2);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Multiply
  (JNIEnv *e, jclass c, jobject o, jobject m1, jobject m2)
{
    kmMat4* out = (kmMat4*)(*e)->GetDirectBufferAddress(e, o);
    kmMat4* mat1 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m1);
    kmMat4* mat2 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m2);
    kmMat4Multiply(out,mat1,mat2);
    return o;    
}

//    public static native FloatBuffer kmMat4Translation(FloatBuffer mat, float x, float y, float z);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Translation
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Translation(mat,x,y,z);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationX
  (JNIEnv *e, jclass c, jobject m, jfloat rad)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationX(mat,rad);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationY
  (JNIEnv *e, jclass c, jobject m, jfloat rad)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationY(mat,rad);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationZ
  (JNIEnv *e, jclass c, jobject m, jfloat rad)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationZ(mat,rad);
    return m;
}

//    public static native FloatBuffer kmMat4RotationPitchYawRoll(FloatBuffer mat, float x, float y, float z);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationPitchYawRoll
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationPitchYawRoll(mat,x,y,z);
    return m;
}
