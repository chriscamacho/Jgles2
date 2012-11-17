#include "Jgles2_kazmath.h"

#include <aabb.h>
#include <vec3.h>
#include <vec4.h>
#include <mat3.h>
#include <mat4.h>
#include <quaternion.h>
#include <plane.h>

#include <assert.h>

//const kmScalar kmPlaneDot(const kmPlane* pP, const struct kmVec4* pV);
JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmPlaneDot
  (JNIEnv *e, jclass c, jobject jp, jobject jv)
{
    kmPlane* p = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp);
    kmVec4* v = (kmVec4*)(*e)->GetDirectBufferAddress(e, jv);
    return kmPlaneDot(p,v);
}

JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmPlaneDotCoord
  (JNIEnv *e, jclass c, jobject jp, jobject jv)
{
    kmPlane* p = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp);
    kmVec3* v = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv);
    return kmPlaneDotCoord(p,v);
}

JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmPlaneDotNormal
  (JNIEnv *e, jclass c, jobject jp, jobject jv)
{
    kmPlane* p = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp);
    kmVec3* v = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv);
    return kmPlaneDotNormal(p,v);
}

//kmPlane* const kmPlaneFromPointNormal(kmPlane* pOut, const struct kmVec3* pPoint, const struct kmVec3* pNormal);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneFromPointNormal
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jn)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    kmVec3* n = (kmVec3*)(*e)->GetDirectBufferAddress(e, jn);
    kmPlaneFromPointNormal(o,p,n);
    return jo;
}

//kmPlane* const kmPlaneFromPoints(kmPlane* pOut, const struct kmVec3* p1, const struct kmVec3* p2, const struct kmVec3* p3);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneFromPoints
  (JNIEnv *e, jclass c, jobject jo, jobject jp1, jobject jp2, jobject jp3)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp1);    
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp2);  
    kmVec3* p3 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp3);
    kmPlaneFromPoints(o,p1,p2,p3);
    return jo;  
}

//kmVec3*  const kmPlaneIntersectLine(struct kmVec3* pOut, const kmPlane* pP, const struct kmVec3* pV1, const struct kmVec3* pV2);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneIntersectLine
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jobject jv1, jobject jv2)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmPlane* p = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp);
    kmVec3* v1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv1);
    kmVec3* v2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv2);
    kmPlaneIntersectLine(o,p,v1,v2);
    return jo;
}

//kmPlane* const kmPlaneNormalize(kmPlane* pOut, const kmPlane* pP);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneNormalize
  (JNIEnv *e, jclass c, jobject jo, jobject jp)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmPlane* p = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp);    
    kmPlaneNormalize(o,p);
    return jo;
}

//kmPlane* const kmPlaneScale(kmPlane* pOut, const kmPlane* pP, kmScalar s);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneScale
  (JNIEnv *e, jclass c, jobject jo, jobject jp, jfloat s)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmPlane* p = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp);
    kmPlaneScale(o,p,s);
    return jo;
}

//const POINT_CLASSIFICATION kmPlaneClassifyPoint(const kmPlane* pIn, const kmVec3* pP);
JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmPlaneClassifyPoint
  (JNIEnv *e, jclass c, jobject jo, jobject jv)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* v = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv);
    return kmPlaneClassifyPoint(o,v);
}

//kmPlane* kmPlaneExtractFromMat4(kmPlane* pOut, const struct kmMat4* pIn, kmInt row);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneExtractFromMat4
  (JNIEnv *e, jclass c, jobject jo, jobject ji, jint r)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat4* i = (kmMat4*)(*e)->GetDirectBufferAddress(e, ji);
    kmPlaneExtractFromMat4(o,i,r);
    return jo;
}

//kmVec3* kmPlaneGetIntersection(kmVec3* pOut, const kmPlane* p1, const kmPlane* p2, const kmPlane* p3);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmPlaneGetIntersection
  (JNIEnv *e, jclass c, jobject jo, jobject jp1, jobject jp2, jobject jp3)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmPlane* p1 = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp1);
    kmPlane* p2 = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp2);
    kmPlane* p3 = (kmPlane*)(*e)->GetDirectBufferAddress(e, jp3);
    kmPlaneGetIntersection(o,p1,p2,p3);
    return jo;
}





//kmMat3* const kmMat3Fill(kmMat3* pOut, const kmScalar* pMat);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Fill
  (JNIEnv *e, jclass c, jobject jo, jobject jm)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmScalar* m = (kmScalar*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat3Fill(o,m);
    return jo;
}

//kmMat3* const kmMat3Adjugate(kmMat3* pOut, const kmMat3* pIn);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Adjugate
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* i = (kmMat3*)(*e)->GetDirectBufferAddress(e, ji);
    kmMat3Adjugate(o,i);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Identity
  (JNIEnv *e, jclass c, jobject jm)
{
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat3Identity(m);
    return jm;
}

//kmMat3* const kmMat3Inverse(kmMat3* pOut, const kmScalar pDeterminate, const kmMat3* pM);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Inverse
  (JNIEnv *e, jclass c, jobject jo, jfloat d, jobject jm)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);    
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);  
    kmMat3Inverse(o,d,m);
    return jo;  
}

//const int  kmMat3IsIdentity(const kmMat3* pIn);
JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmMat3IsIdentity
  (JNIEnv *e, jclass c, jobject jm)
{
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);
    return kmMat3IsIdentity(m);
}

//kmMat3* const kmMat3Transpose(kmMat3* pOut, const kmMat3* pIn);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Transpose
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* i = (kmMat3*)(*e)->GetDirectBufferAddress(e, ji);
    kmMat3Transpose(o,i);
    return jo;    
}

//const kmScalar kmMat3Determinant(const kmMat3* pIn);
JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmMat3Determinant
  (JNIEnv *e, jclass c, jobject ji)
{
    kmMat3* i = (kmMat3*)(*e)->GetDirectBufferAddress(e, ji);
    return kmMat3Determinant(i);
}

//kmMat3* const kmMat3Multiply(kmMat3* pOut, const kmMat3* pM1, const kmMat3* pM2);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Multiply
  (JNIEnv *e, jclass c, jobject jo, jobject jm1, jobject jm2)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* m1 = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm1);    
    kmMat3* m2 = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm2);
    kmMat3Multiply(o,m1,m2);
    return jo;
}

// kmMat3* const kmMat3ScalarMultiply(kmMat3* pOut, const kmMat3* pM, const kmScalar pFactor);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3ScalarMultiply
  (JNIEnv *e, jclass c, jobject jo, jobject jm, jfloat f)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat3ScalarMultiply(o,m,f);
    return jo;
}

//kmMat3* const kmMat3RotationAxisAngle(kmMat3* pOut, const struct kmVec3* axis, kmScalar radians);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3RotationAxisAngle
  (JNIEnv *e, jclass c, jobject jo, jobject ja, jfloat r)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmVec3* a = (kmVec3*)(*e)->GetDirectBufferAddress(e, ja);
    kmMat3RotationAxisAngle(o,a,r);
    return jo;    
}

//struct kmVec3* const kmMat3RotationToAxisAngle(struct kmVec3* pAxis, kmScalar* radians, const kmMat3* pIn);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3RotationToAxisAngle
  (JNIEnv *e, jclass c, jobject ja, jobject jr, jobject ji)
{
    kmVec3* a = (kmVec3*)(*e)->GetDirectBufferAddress(e, ja);
    kmScalar* r = (kmScalar*)(*e)->GetDirectBufferAddress(e, jr);
    kmMat3* i = (kmMat3*)(*e)->GetDirectBufferAddress(e, ji);
    kmMat3RotationToAxisAngle(a,r,i);
    return ja;
}

// kmMat3* const kmMat3Assign(kmMat3* pOut, const kmMat3* pIn);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Assign
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* i = (kmMat3*)(*e)->GetDirectBufferAddress(e, ji);
    kmMat3Assign(o,i);
    return jo;    
}

//const int  kmMat3AreEqual(const kmMat3* pM1, const kmMat3* pM2);
JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmMat3AreEqual
  (JNIEnv *e, jclass c, jobject jm1, jobject jm2)
{
    kmMat3* m1 = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm1);
    kmMat3* m2 = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm2);
    return kmMat3AreEqual(m1,m2);
}

//struct kmVec3* const kmMat3GetUpVec3(struct kmVec3* pOut, const kmMat3* pIn);
JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3GetUpVec3
  (JNIEnv *e, jclass c, jobject jo, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat3GetUpVec3(o,m);
    return jo; 
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3GetRightVec3
  (JNIEnv *e, jclass c, jobject jo, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat3GetRightVec3(o,m);
    return jo;     
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3GetForwardVec3
  (JNIEnv *e, jclass c, jobject jo, jobject jm)
{
    kmVec3* o = (kmVec3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3* m = (kmMat3*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat3GetForwardVec3(o,m);
    return jo;     
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3RotationX
  (JNIEnv *e, jclass c, jobject jo, jfloat r)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3RotationX(o,r);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3RotationY
  (JNIEnv *e, jclass c, jobject jo, jfloat r)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3RotationY(o,r);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3RotationZ
  (JNIEnv *e, jclass c, jobject jo, jfloat r)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3RotationZ(o,r);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Rotation
  (JNIEnv *e, jclass c, jobject jo, jfloat r)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3Rotation(o,r);
    return jo;    
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Scaling
  (JNIEnv *e, jclass c, jobject jo, jfloat x, jfloat y)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3Scaling(o,x,y);
    return jo;    
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3Translation
  (JNIEnv *e, jclass c, jobject jo, jfloat x, jfloat y)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat3Translation(o,x,y);
    return jo;        
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat3RotationQuaternion
  (JNIEnv *e, jclass c, jobject jo, jobject jq)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmQuaternion* q = (kmQuaternion*)(*e)->GetDirectBufferAddress(e, jq);
    kmMat3Quaternion(o,q);
    return jo;        
}




JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmAABBContainsPoint
  (JNIEnv *e, jclass c, jobject jb, jobject jp)
{
    kmAABB* b = (kmAABB*)(*e)->GetDirectBufferAddress(e, jb);    
    kmVec3* p = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp);
    return kmAABBContainsPoint(b,p);
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmAABBAssign
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmAABB* o = (kmAABB*)(*e)->GetDirectBufferAddress(e, jo);  
    kmAABB* i = (kmAABB*)(*e)->GetDirectBufferAddress(e, ji);
    kmAABBAssign(o,i);
    return jo;  
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmAABBScale
  (JNIEnv *e, jclass c, jobject jo, jobject ji, jfloat s)
{
    kmAABB* o = (kmAABB*)(*e)->GetDirectBufferAddress(e, jo);  
    kmAABB* i = (kmAABB*)(*e)->GetDirectBufferAddress(e, ji);
    kmAABBScale(o,i,s);
    return jo;      
}

// kmBool kmAABBIntersectsTriangle(kmAABB* box, const kmVec3* p1, const kmVec3* p2, const kmVec3* p3);
JNIEXPORT jboolean JNICALL Java_Jgles2_kazmath_kmAABBIntersectsTriangle
  (JNIEnv *e, jclass c, jobject jb, jobject jp1, jobject jp2, jobject jp3)
{
    kmAABB* b = (kmAABB*)(*e)->GetDirectBufferAddress(e, jb);
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp1);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp2);
    kmVec3* p3 = (kmVec3*)(*e)->GetDirectBufferAddress(e, jp3);
    return kmAABBIntersectsTriangle(b,p1,p2,p3);
}

//kmEnum kmAABBContainsAABB(const kmAABB* container, const kmAABB* to_check);
JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmAABBContainsAABB
  (JNIEnv *e, jclass c, jobject ja, jobject jb)
{
    kmAABB* a = (kmAABB*)(*e)->GetDirectBufferAddress(e, ja);
    kmAABB* b = (kmAABB*)(*e)->GetDirectBufferAddress(e, jb);
    return kmAABBContainsAABB(a,b);
}

// kmScalar kmAABBDiameterX(const kmAABB* aabb);
JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmAABBDiameterX
  (JNIEnv *e, jclass c, jobject jb)
{
    kmAABB* b = (kmAABB*)(*e)->GetDirectBufferAddress(e, jb);
    return kmAABBDiameterX(b);    
}

JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmAABBDiameterY
  (JNIEnv *e, jclass c, jobject jb)
{
    kmAABB* b = (kmAABB*)(*e)->GetDirectBufferAddress(e, jb);
    return kmAABBDiameterY(b);    
}

JNIEXPORT jfloat JNICALL Java_Jgles2_kazmath_kmAABBDiameterZ
  (JNIEnv *e, jclass c, jobject jb)
{
    kmAABB* b = (kmAABB*)(*e)->GetDirectBufferAddress(e, jb);
    return kmAABBDiameterZ(b);    
}



JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmVec3Fill
  (JNIEnv *e, jclass c, jobject v, jfloat x, jfloat y, jfloat z)
{
    kmVec3* vec = (kmVec3*)(*e)->GetDirectBufferAddress(e, v);
    kmVec3Fill(vec,(float)x,(float)y,(float)z);
    return v;
}

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





// kmMat4

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Fill
  (JNIEnv *e, jclass c, jobject jo, jobject jm)
{
    kmMat4* o = (kmMat4*)(*e)->GetDirectBufferAddress(e, jo);
    kmScalar* m = (kmScalar*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat4Fill(o, m);
    return jm;    
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Identity
  (JNIEnv * e, jclass c, jobject m)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Identity(mat);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Inverse
  (JNIEnv *e, jclass c, jobject jo, jobject jm)
{
    kmMat4* o = (kmMat4*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat4Inverse(o,m);
    return jo;
}

JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmMat4IsIdentity
  (JNIEnv *e, jclass c, jobject ji)
{
    kmMat4* i = (kmMat4*)(*e)->GetDirectBufferAddress(e, ji);
    return kmMat4IsIdentity(i);
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Transpose
  (JNIEnv *e,jclass c, jobject jo, jobject jm)
{
    kmMat4* o = (kmMat4*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat4* m = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);
    kmMat4Transpose(o,m);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Multiply
  (JNIEnv *e, jclass c, jobject o, jobject m1, jobject m2)
{
    kmMat4* out = (kmMat4*)(*e)->GetDirectBufferAddress(e, o);
    kmMat4* mat1 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m1);
    kmMat4* mat2 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m2);
    kmMat4Multiply(out,mat1,mat2);
    return o;    
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Assign
  (JNIEnv *e, jclass c, jobject m1, jobject m2)
{
    kmMat4* mat1 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m1);
    kmMat4* mat2 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m2);   
    kmMat4Assign(mat1,mat2);
    return m1;
}

JNIEXPORT jint JNICALL Java_Jgles2_kazmath_kmMat4AreEqual
  (JNIEnv *e, jclass c, jobject m1, jobject m2)
{
    kmMat4* mat1 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m1);
    kmMat4* mat2 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m2);   
    return kmMat4AreEqual(mat1,mat2);
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

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationPitchYawRoll
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationPitchYawRoll(mat,x,y,z);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationQuaternion
  (JNIEnv *e, jclass c, jobject ji, jobject jq)
{
    kmMat4* i = (kmMat4*)(*e)->GetDirectBufferAddress(e, ji);
    kmQuaternion* q = (kmQuaternion*)(*e)->GetDirectBufferAddress(e, jq);
    kmMat4RotationQuaternion(i,q);
    return ji;    
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Translation
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Translation(mat,x,y,z);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4Scaling
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Scaling(mat,x,y,z);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4GetUpVec3
  (JNIEnv *e, jclass c, jobject jv, jobject jm)
{
    kmVec3* v = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv);
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);   
    kmMat4GetUpVec3(v,mat);
    return jv;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4GetRightVec3
  (JNIEnv *e, jclass c, jobject jv, jobject jm)
{
    kmVec3* v = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv);
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);   
    kmMat4GetRightVec3(v,mat);
    return jv;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4GetForwardVec3
  (JNIEnv *e, jclass c, jobject jv, jobject jm)
{
    kmVec3* v = (kmVec3*)(*e)->GetDirectBufferAddress(e, jv);
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);   
    kmMat4GetForwardVec3(v,mat);
    return jv;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4PerspectiveProjection
  (JNIEnv *e, jclass c, jobject p, jfloat fov, jfloat aspect, jfloat near, jfloat far)
{
    kmMat4* proj = (kmMat4*)(*e)->GetDirectBufferAddress(e, p);
    kmMat4PerspectiveProjection(proj,fov,aspect,near,far);
    return p;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4OrthographicProjection
  (JNIEnv *e, jclass c, jobject jm, jfloat l, jfloat r, jfloat b, jfloat t, jfloat n, jfloat f)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);       
    kmMat4OrthographicProjection(mat,l,r,b,t,n,f);
    return jm;
}

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

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4RotationAxisAngle
  (JNIEnv *e, jclass c, jobject jm, jobject ja, jfloat r)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, jm);       
    kmVec3* a = (kmVec3*)(*e)->GetDirectBufferAddress(e, ja);           
    kmMat4RotationAxisAngle(mat,a,r);
    return jm;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4ExtractRotation
  (JNIEnv *e, jclass c, jobject jo, jobject ji)
{
    kmMat3* o = (kmMat3*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat4* i = (kmMat4*)(*e)->GetDirectBufferAddress(e, ji);   
    kmMat4ExtractRotation(o,i);
    return jo;
}

JNIEXPORT jobject JNICALL Java_Jgles2_kazmath_kmMat4ExtractPlane
  (JNIEnv *e, jclass c, jobject jo, jobject ji, jint p)
{
    kmPlane* o = (kmPlane*)(*e)->GetDirectBufferAddress(e, jo);
    kmMat4* i = (kmMat4*)(*e)->GetDirectBufferAddress(e, ji);       
    kmMat4ExtractPlane(o, i, p);
    return jo;
}


