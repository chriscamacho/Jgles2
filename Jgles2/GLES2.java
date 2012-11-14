
package Jgles2;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.ByteBuffer;

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

/* TextureMagFilter */
    public static final int GL_NEAREST                        =   0x2600;
    public static final int GL_LINEAR                         =   0x2601;

/* TextureMinFilter */
/*      GL_NEAREST */
/*      GL_LINEAR */
    public static final int GL_NEAREST_MIPMAP_NEAREST         =   0x2700;
    public static final int GL_LINEAR_MIPMAP_NEAREST          =   0x2701;
    public static final int GL_NEAREST_MIPMAP_LINEAR          =   0x2702;
    public static final int GL_LINEAR_MIPMAP_LINEAR           =   0x2703;

/* TextureParameterName */
    public static final int GL_TEXTURE_MAG_FILTER             =   0x2800;
    public static final int GL_TEXTURE_MIN_FILTER             =   0x2801;
    public static final int GL_TEXTURE_WRAP_S                 =   0x2802;
    public static final int GL_TEXTURE_WRAP_T                 =   0x2803;

/* TextureTarget */
/*      GL_TEXTURE_2D */
    public static final int GL_TEXTURE                        =   0x1702;

    public static final int GL_TEXTURE_CUBE_MAP               =   0x8513;
    public static final int GL_TEXTURE_BINDING_CUBE_MAP       =   0x8514;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X    =   0x8515;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X    =   0x8516;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y    =   0x8517;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y    =   0x8518;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z    =   0x8519;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z    =   0x851A;
    public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE      =   0x851C;

/* TextureUnit */
    public static final int GL_TEXTURE0                       =   0x84C0;
    public static final int GL_TEXTURE1                       =   0x84C1;
    public static final int GL_TEXTURE2                       =   0x84C2;
    public static final int GL_TEXTURE3                       =   0x84C3;
    public static final int GL_TEXTURE4                       =   0x84C4;
    public static final int GL_TEXTURE5                       =   0x84C5;
    public static final int GL_TEXTURE6                       =   0x84C6;
    public static final int GL_TEXTURE7                       =   0x84C7;
    public static final int GL_TEXTURE8                       =   0x84C8;
    public static final int GL_TEXTURE9                       =   0x84C9;
    public static final int GL_TEXTURE10                      =   0x84CA;
    public static final int GL_TEXTURE11                      =   0x84CB;
    public static final int GL_TEXTURE12                      =   0x84CC;
    public static final int GL_TEXTURE13                      =   0x84CD;
    public static final int GL_TEXTURE14                      =   0x84CE;
    public static final int GL_TEXTURE15                      =   0x84CF;
    public static final int GL_TEXTURE16                      =   0x84D0;
    public static final int GL_TEXTURE17                      =   0x84D1;
    public static final int GL_TEXTURE18                      =   0x84D2;
    public static final int GL_TEXTURE19                      =   0x84D3;
    public static final int GL_TEXTURE20                      =   0x84D4;
    public static final int GL_TEXTURE21                      =   0x84D5;
    public static final int GL_TEXTURE22                      =   0x84D6;
    public static final int GL_TEXTURE23                      =   0x84D7;
    public static final int GL_TEXTURE24                      =   0x84D8;
    public static final int GL_TEXTURE25                      =   0x84D9;
    public static final int GL_TEXTURE26                      =   0x84DA;
    public static final int GL_TEXTURE27                      =   0x84DB;
    public static final int GL_TEXTURE28                      =   0x84DC;
    public static final int GL_TEXTURE29                      =   0x84DD;
    public static final int GL_TEXTURE30                      =   0x84DE;
    public static final int GL_TEXTURE31                      =   0x84DF;
    public static final int GL_ACTIVE_TEXTURE                 =   0x84E0;

/* TextureWrapMode */
    public static final int GL_REPEAT                         =   0x2901;
    public static final int GL_CLAMP_TO_EDGE                  =   0x812F;
    public static final int GL_MIRRORED_REPEAT                =   0x8370;

/* PixelFormat */
    public static final int GL_DEPTH_COMPONENT       =         0x1902;
    public static final int GL_ALPHA                 =         0x1906;
    public static final int GL_RGB                   =         0x1907;
    public static final int GL_RGBA                  =         0x1908;
    public static final int GL_LUMINANCE             =         0x1909;
    public static final int GL_LUMINANCE_ALPHA       =         0x190A;

/* EnableCap */
    public static final int GL_TEXTURE_2D                 =    0x0DE1;
    public static final int GL_CULL_FACE                  =    0x0B44;
    public static final int GL_BLEND                      =    0x0BE2;
    public static final int GL_DITHER                     =    0x0BD0;
    public static final int GL_STENCIL_TEST               =    0x0B90;
    public static final int GL_DEPTH_TEST                 =    0x0B71;
    public static final int GL_SCISSOR_TEST               =    0x0C11;
    public static final int GL_POLYGON_OFFSET_FILL        =    0x8037;
    public static final int GL_SAMPLE_ALPHA_TO_COVERAGE   =    0x809E;
    public static final int GL_SAMPLE_COVERAGE            =    0x80A0;
       
/* Framebuffer Object. */
    public static final int GL_FRAMEBUFFER                    =   0x8D40;
    public static final int GL_RENDERBUFFER                   =   0x8D41;

    public static final int GL_RGBA4                          =   0x8056;
    public static final int GL_RGB5_A1                        =   0x8057;
    public static final int GL_RGB565                         =   0x8D62;
    public static final int GL_DEPTH_COMPONENT16              =   0x81A5;
    public static final int GL_STENCIL_INDEX                  =   0x1901;
    public static final int GL_STENCIL_INDEX8                 =   0x8D48;

    public static final int GL_RENDERBUFFER_WIDTH             =   0x8D42;
    public static final int GL_RENDERBUFFER_HEIGHT            =   0x8D43;
    public static final int GL_RENDERBUFFER_INTERNAL_FORMAT   =   0x8D44;
    public static final int GL_RENDERBUFFER_RED_SIZE          =   0x8D50;
    public static final int GL_RENDERBUFFER_GREEN_SIZE        =   0x8D51;
    public static final int GL_RENDERBUFFER_BLUE_SIZE         =   0x8D52;
    public static final int GL_RENDERBUFFER_ALPHA_SIZE        =   0x8D53;
    public static final int GL_RENDERBUFFER_DEPTH_SIZE        =   0x8D54;
    public static final int GL_RENDERBUFFER_STENCIL_SIZE      =   0x8D55;

    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE           =   0x8CD0;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME           =   0x8CD1;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL         =   0x8CD2;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE =   0x8CD3;

    public static final int GL_COLOR_ATTACHMENT0              =   0x8CE0;
    public static final int GL_DEPTH_ATTACHMENT               =   0x8D00;
    public static final int GL_STENCIL_ATTACHMENT             =   0x8D20;

    public static final int GL_NONE                           =   0;

    public static final int GL_FRAMEBUFFER_COMPLETE                      =   0x8CD5;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT         =   0x8CD6;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT =   0x8CD7;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_DIMENSIONS         =   0x8CD9;
    public static final int GL_FRAMEBUFFER_UNSUPPORTED                   =   0x8CDD;

    public static final int GL_FRAMEBUFFER_BINDING            =   0x8CA6;
    public static final int GL_RENDERBUFFER_BINDING           =   0x8CA7;
    public static final int GL_MAX_RENDERBUFFER_SIZE          =   0x84E8;

    public static final int GL_INVALID_FRAMEBUFFER_OPERATION  =   0x0506;









    public static native void glBindFramebuffer(int target, int framebuffer);
    public static native void glGenFramebuffers(int n, IntBuffer framebuffers);
    public static native void glRenderbufferStorage(int target,int internalformat,int width,int height);
    public static native void glGenRenderbuffers(int n, IntBuffer renderbuffers);
    public static native void glBindRenderbuffer(int target, int renderbuffer);
    public static native void glFramebufferTexture2D(int target,int attachment,int textarget,int texture,int level);

    public static native void glActiveTexture(int texture);
    public static native void glAttachShader(int program, int fragShader);
    
    public static native void glGenTextures(int num, IntBuffer names);
    public static native void glBindTexture(int type, int texname);

    public static native void glTexParameterf(int target,int pname,float param);
    public static native void glTexParameteri(int target,int pname,int param);         
    public static native void glTexImage2D(int target,int level,int internalformat,
                                    int width,int height,int border,int format,
                                    int type,ByteBuffer data) ;

    
    public static native String glGetString(int attrib); 
    public static native void glClearColor(float r, float g, float b, float a);
    public static native int glCreateShader(int type);
    
    // differs as only accepts 1 shader
    public static native void glShaderSource(int shader, String shaderText);
    
    public static native void glCompileShader(int shader);
    public static native void glGetShaderiv(int shader, int attrib, IntBuffer val);
    public static native int glCreateProgram();
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
