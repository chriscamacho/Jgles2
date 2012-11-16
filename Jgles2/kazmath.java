
package Jgles2;

import java.nio.FloatBuffer;

public class kazmath {

    static {
        System.loadLibrary("Jgles2");
    }

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


    // mat4
    public static native FloatBuffer kmMat4Identity(FloatBuffer mat);
    public static native FloatBuffer kmMat4LookAt(FloatBuffer view,FloatBuffer eye,FloatBuffer centre,FloatBuffer up);
    public static native FloatBuffer kmMat4PerspectiveProjection(FloatBuffer projection, float fov,
                                float aspect, float near, float far);
    public static native FloatBuffer kmMat4Multiply(FloatBuffer out,FloatBuffer mat1,FloatBuffer mat2);
    public static native FloatBuffer kmMat4Translation(FloatBuffer mat, float x, float y, float z);
    public static native FloatBuffer kmMat4RotationX(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationY(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationZ(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationPitchYawRoll(FloatBuffer mat, float x, float y, float z);
    

    
    kazmath() {
        
    }
}
