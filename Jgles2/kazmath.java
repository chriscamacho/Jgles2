
package Jgles2;

import java.nio.FloatBuffer;

public class kazmath {

    static {
        System.loadLibrary("Jgles2");
    }
    
    public static int KM_PLANE_LEFT     =   0;
    public static int KM_PLANE_RIGHT    =   1;
    public static int KM_PLANE_BOTTOM   =   2;
    public static int KM_PLANE_TOP      =   3;
    public static int KM_PLANE_NEAR     =   4;
    public static int KM_PLANE_FAR      =   5;
    
    // mat4
    
    public static native FloatBuffer kmMat4Fill(FloatBuffer pOut, FloatBuffer pMat);
    public static native FloatBuffer kmMat4Identity(FloatBuffer mat);
    public static native FloatBuffer kmMat4Inverse(FloatBuffer pOut, FloatBuffer pM);
    public static native  int kmMat4IsIdentity(FloatBuffer pIn);
    public static native FloatBuffer kmMat4Transpose(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer kmMat4Multiply(FloatBuffer out,FloatBuffer mat1,FloatBuffer mat2);
    public static native FloatBuffer kmMat4Assign(FloatBuffer pOut, FloatBuffer pIn);
    public static native int kmMat4AreEqual(FloatBuffer pM1, FloatBuffer pM2);
    public static native FloatBuffer kmMat4RotationX(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationY(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationZ(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationPitchYawRoll(FloatBuffer mat, float x, float y, float z);
    public static native FloatBuffer kmMat4RotationQuaternion(FloatBuffer pOut, FloatBuffer pQ);
    public static native FloatBuffer kmMat4RotationTranslation(FloatBuffer pOut, FloatBuffer rotation,  FloatBuffer translation);
    public static native FloatBuffer kmMat4Scaling(FloatBuffer pOut, float  x, float  y, float  z);
    public static native FloatBuffer kmMat4Translation(FloatBuffer mat, float x, float y, float z);
    public static native FloatBuffer  kmMat4GetUpVec3(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer  kmMat4GetRightVec3(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer  kmMat4GetForwardVec3(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer kmMat4PerspectiveProjection(FloatBuffer projection, float fov,
                                float aspect, float near, float far);
    public static native FloatBuffer kmMat4OrthographicProjection(FloatBuffer pOut, float left, float right, float bottom, float top, float nearVal, float farVal);
    public static native FloatBuffer kmMat4LookAt(FloatBuffer view,FloatBuffer eye,FloatBuffer centre,FloatBuffer up);
    public static native FloatBuffer kmMat4RotationAxisAngle(FloatBuffer pOut,  FloatBuffer axis, float radians);
    public static native FloatBuffer kmMat4ExtractRotation(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer kmMat4ExtractPlane(FloatBuffer pOut, FloatBuffer pIn, int plane);
    public static native FloatBuffer  kmMat4RotationToAxisAngle(FloatBuffer pAxis, float radians, FloatBuffer pIn);



    // vec3
    public static native FloatBuffer kmVec3Fill(FloatBuffer v, float x, float y, float z);
    public static native float kmVec3Length(FloatBuffer in);
    public static native float kmVec3LengthSq(FloatBuffer in);
    public static native FloatBuffer kmVec3Normalize(FloatBuffer out,FloatBuffer in);
    public static native FloatBuffer kmVec3Cross(FloatBuffer out,FloatBuffer p1,FloatBuffer p2);
    public static native float kmVec3Dot(FloatBuffer pV1, FloatBuffer pV2);
    public static native FloatBuffer kmVec3Add(FloatBuffer pOut, FloatBuffer pV1, FloatBuffer pV2);
    public static native FloatBuffer kmVec3Subtract(FloatBuffer out,FloatBuffer p1,FloatBuffer p2);
    public static native FloatBuffer kmVec3Transform(FloatBuffer pOut, FloatBuffer pV1, FloatBuffer pM);
    public static native FloatBuffer kmVec3TransformNormal(FloatBuffer pOut, FloatBuffer pV, FloatBuffer pM);
    public static native FloatBuffer kmVec3TransformCoord(FloatBuffer pOut, FloatBuffer pV, FloatBuffer pM);
    public static native FloatBuffer kmVec3Scale(FloatBuffer pOut, FloatBuffer pIn, float s); 
    public static native int kmVec3AreEqual(FloatBuffer p1, FloatBuffer p2);
    public static native FloatBuffer kmVec3InverseTransform(FloatBuffer pOut, FloatBuffer pV, FloatBuffer pM);
    public static native FloatBuffer kmVec3InverseTransformNormal(FloatBuffer pOut, FloatBuffer pVect, FloatBuffer pM);
    public static native FloatBuffer kmVec3Assign(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer kmVec3Zero(FloatBuffer pOut);
    public static native FloatBuffer kmVec3GetHorizontalAngle(FloatBuffer pOut, FloatBuffer pIn);
    public static native FloatBuffer kmVec3RotationToDirection(FloatBuffer pOut, FloatBuffer pIn, FloatBuffer forwards);


    

    
    kazmath() {
        
    }
}
