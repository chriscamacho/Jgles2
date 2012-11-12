
package Jgles2;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;

public class GLES2 {
    
/* StringName */
    public static final int GL_VENDOR       =   0x1F00;
    public static final int GL_RENDERER     =   0x1F01;
    public static final int GL_VERSION      =   0x1F02;
    public static final int GL_EXTENSIONS   =   0x1F03;
    
/* Shaders */
    public static final int GL_FRAGMENT_SHADER                  =    0x8B30;
    public static final int GL_VERTEX_SHADER                    =    0x8B31;
    public static final int GL_MAX_VERTEX_ATTRIBS               =    0x8869;
    public static final int GL_MAX_VERTEX_UNIFORM_VECTORS       =    0x8DFB;
    public static final int GL_MAX_VARYING_VECTORS              =    0x8DFC;
    public static final int GL_MAX_COMBINED_TEXTURE_IMAGE_UNITS =    0x8B4D;
    public static final int GL_MAX_VERTEX_TEXTURE_IMAGE_UNITS   =    0x8B4C;
    public static final int GL_MAX_TEXTURE_IMAGE_UNITS          =    0x8872;
    public static final int GL_MAX_FRAGMENT_UNIFORM_VECTORS     =    0x8DFD;
    public static final int GL_SHADER_TYPE                      =    0x8B4F;
    public static final int GL_DELETE_STATUS                    =    0x8B80;
    public static final int GL_LINK_STATUS                      =    0x8B82;
    public static final int GL_VALIDATE_STATUS                  =    0x8B83;
    public static final int GL_ATTACHED_SHADERS                 =    0x8B85;
    public static final int GL_ACTIVE_UNIFORMS                  =    0x8B86;
    public static final int GL_ACTIVE_UNIFORM_MAX_LENGTH        =    0x8B87;
    public static final int GL_ACTIVE_ATTRIBUTES                =    0x8B89;
    public static final int GL_ACTIVE_ATTRIBUTE_MAX_LENGTH      =    0x8B8A;
    public static final int GL_SHADING_LANGUAGE_VERSION         =    0x8B8C;
    public static final int GL_CURRENT_PROGRAM                  =    0x8B8D;   


/* Shader Source */
    public static final int GL_COMPILE_STATUS       =    0x8B81;
    public static final int GL_INFO_LOG_LENGTH      =    0x8B84;
    public static final int GL_SHADER_SOURCE_LENGTH =    0x8B88;
    public static final int GL_SHADER_COMPILER      =    0x8DFA;
/* Boolean */
    public static final int GL_FALSE    =   0;
    public static final int GL_TRUE     =   1;    
/* ClearBufferMask */
    public static final int GL_DEPTH_BUFFER_BIT     =   0x00000100;
    public static final int GL_STENCIL_BUFFER_BIT   =   0x00000400;
    public static final int GL_COLOR_BUFFER_BIT     =   0x00004000;
/* DataType */
    public static final int GL_BYTE             =    0x1400;
    public static final int GL_UNSIGNED_BYTE    =    0x1401;
    public static final int GL_SHORT            =    0x1402;
    public static final int GL_UNSIGNED_SHORT   =    0x1403;
    public static final int GL_INT              =    0x1404;
    public static final int GL_UNSIGNED_INT     =    0x1405;
    public static final int GL_FLOAT            =    0x1406;
    public static final int GL_FIXED            =    0x140C;
/* BeginMode */
    public static final int GL_POINTS           =    0x0000;
    public static final int GL_LINES            =    0x0001;
    public static final int GL_LINE_LOOP        =    0x0002;
    public static final int GL_LINE_STRIP       =    0x0003;
    public static final int GL_TRIANGLES        =    0x0004;
    public static final int GL_TRIANGLE_STRIP   =    0x0005;
    public static final int GL_TRIANGLE_FAN     =    0x0006;   
       
    
    
    public static native String glGetString(int attrib); 
    public static native void glClearColor(float r, float g, float b, float a);
    public static native int glCreateShader(int type);
    
    // differs as only accepts 1 shader
    public static native void glShaderSource(int fragShader, String fragShaderText);
    
    public static native void glCompileShader(int shader);
    public static native void glGetShaderiv(int shader, int attrib, IntBuffer val);
    public static native int glCreateProgram();
    public static native void glAttachShader(int program, int fragShader);
    public static native void glLinkProgram(int program);
    public static native void glGetProgramiv(int prog, int attrib, IntBuffer val);
    public static native String glGetProgramInfoLog(int program);
    public static native String glGetShaderInfoLog(int program);
    public static native void glBindAttribLocation(int program, int loc, String name);
    public static native int glGetUniformLocation(int program, String name);
    public static native void glUseProgram(int program);
    public static native void glViewport(int x, int y, int w, int h);
    public static native void glDisableVertexAttribArray(int index);    
    public static native void glEnableVertexAttribArray(int index);    
    public static native void glDrawArrays(int mode,int first,int count);
    public static native void glVertexAttribPointer(int index, int size,int type,
                    int normalized, int stride, FloatBuffer pointer);
    public static native void glUniformMatrix4fv(int location, int count,
                    int transpose,FloatBuffer value);
    public static native void glClear(int buffers);    
    
    
    
    GLES2() {
        
    }
}
