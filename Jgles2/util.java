
package Jgles2;

import java.nio.FloatBuffer;

public class util {
    static {
        System.loadLibrary("Jgles2");
    }
    
    public static native long get_native_display();
    public static native long make_native_window(long native_display,
                            long egl_display, long config, int x, int y, 
                            int width, int height, boolean fullscreen);
                            

    // kazmath
    public static native FloatBuffer kmMat4Identity(FloatBuffer mat);
    public static native FloatBuffer kmVec3Fill(FloatBuffer v, float x, float y, float z);
    public static native FloatBuffer kmVec3Subtract(FloatBuffer out,FloatBuffer p1,FloatBuffer p2);
    public static native FloatBuffer kmVec3Normalize(FloatBuffer out,FloatBuffer in);
    public static native float kmVec3Length(FloatBuffer in);
    public static native FloatBuffer kmMat4LookAt(FloatBuffer view,FloatBuffer eye,FloatBuffer centre,FloatBuffer up);
    public static native FloatBuffer kmMat4PerspectiveProjection(FloatBuffer projection, float fov,
                                float aspect, float near, float far);
    public static native FloatBuffer kmMat4Multiply(FloatBuffer out,FloatBuffer mat1,FloatBuffer mat2);
    public static native FloatBuffer kmMat4Translation(FloatBuffer mat, float x, float y, float z);
    public static native FloatBuffer kmMat4RotationX(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationY(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationZ(FloatBuffer mat, float rad);
    public static native FloatBuffer kmMat4RotationPitchYawRoll(FloatBuffer mat, float x, float y, float z);
    
    
    util() {
        
    }
}
