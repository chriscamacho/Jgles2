
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

/* BlendingFactorDest */
    public static final int GL_ZERO                           =   0;
    public static final int GL_ONE                            =   1;
    public static final int GL_SRC_COLOR                      =   0x0300;
    public static final int GL_ONE_MINUS_SRC_COLOR            =   0x0301;
    public static final int GL_SRC_ALPHA                      =   0x0302;
    public static final int GL_ONE_MINUS_SRC_ALPHA            =   0x0303;
    public static final int GL_DST_ALPHA                      =   0x0304;
    public static final int GL_ONE_MINUS_DST_ALPHA            =   0x0305;

/* BlendingFactorSrc */
/*      GL_ZERO */
/*      GL_ONE */
    public static final int GL_DST_COLOR                      =   0x0306;
    public static final int GL_ONE_MINUS_DST_COLOR            =   0x0307;
    public static final int GL_SRC_ALPHA_SATURATE             =   0x0308;
/*      GL_SRC_ALPHA */
/*      GL_ONE_MINUS_SRC_ALPHA */
/*      GL_DST_ALPHA */
/*      GL_ONE_MINUS_DST_ALPHA */

/* BlendEquationSeparate */
    public static final int GL_FUNC_ADD                       =   0x8006;
    public static final int GL_BLEND_EQUATION                 =   0x8009;
    public static final int GL_BLEND_EQUATION_RGB             =   0x8009;    /* same as BLEND_EQUATION */
    public static final int GL_BLEND_EQUATION_ALPHA           =   0x883D;

/* BlendSubtract */
    public static final int GL_FUNC_SUBTRACT                  =   0x800A;
    public static final int GL_FUNC_REVERSE_SUBTRACT          =   0x800B;

/* Separate Blend Functions */
    public static final int GL_BLEND_DST_RGB                  =   0x80C8;
    public static final int GL_BLEND_SRC_RGB                  =   0x80C9;
    public static final int GL_BLEND_DST_ALPHA                =   0x80CA;
    public static final int GL_BLEND_SRC_ALPHA                =   0x80CB;
    public static final int GL_CONSTANT_COLOR                 =   0x8001;
    public static final int GL_ONE_MINUS_CONSTANT_COLOR       =   0x8002;
    public static final int GL_CONSTANT_ALPHA                 =   0x8003;
    public static final int GL_ONE_MINUS_CONSTANT_ALPHA       =   0x8004;
    public static final int GL_BLEND_COLOR                    =   0x8005;

/* CullFaceMode */
    public static final int GL_FRONT                          =     0x0404;
    public static final int GL_BACK                           =     0x0405;
    public static final int GL_FRONT_AND_BACK                 =     0x0408;

/* StencilFunction */ // also depth/alpha func
    public static final int GL_NEVER                          =     0x0200;
    public static final int GL_LESS                           =     0x0201;
    public static final int GL_EQUAL                          =     0x0202;
    public static final int GL_LEQUAL                         =     0x0203;
    public static final int GL_GREATER                        =     0x0204;
    public static final int GL_NOTEQUAL                       =     0x0205;
    public static final int GL_GEQUAL                         =     0x0206;
    public static final int GL_ALWAYS                         =     0x0207;

/* GetPName */
    public static final int GL_LINE_WIDTH                     =   0x0B21;
    public static final int GL_ALIASED_POINT_SIZE_RANGE       =   0x846D;
    public static final int GL_ALIASED_LINE_WIDTH_RANGE       =   0x846E;
    public static final int GL_CULL_FACE_MODE                 =   0x0B45;
    public static final int GL_FRONT_FACE                     =   0x0B46;
    public static final int GL_DEPTH_RANGE                    =   0x0B70;
    public static final int GL_DEPTH_WRITEMASK                =   0x0B72;
    public static final int GL_DEPTH_CLEAR_VALUE              =   0x0B73;
    public static final int GL_DEPTH_FUNC                     =   0x0B74;
    public static final int GL_STENCIL_CLEAR_VALUE            =   0x0B91;
    public static final int GL_STENCIL_FUNC                   =   0x0B92;
    public static final int GL_STENCIL_FAIL                   =   0x0B94;
    public static final int GL_STENCIL_PASS_DEPTH_FAIL        =   0x0B95;
    public static final int GL_STENCIL_PASS_DEPTH_PASS        =   0x0B96;
    public static final int GL_STENCIL_REF                    =   0x0B97;
    public static final int GL_STENCIL_VALUE_MASK             =   0x0B93;
    public static final int GL_STENCIL_WRITEMASK              =   0x0B98;
    public static final int GL_STENCIL_BACK_FUNC              =   0x8800;
    public static final int GL_STENCIL_BACK_FAIL              =   0x8801;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_FAIL   =   0x8802;
    public static final int GL_STENCIL_BACK_PASS_DEPTH_PASS   =   0x8803;
    public static final int GL_STENCIL_BACK_REF               =   0x8CA3;
    public static final int GL_STENCIL_BACK_VALUE_MASK        =   0x8CA4;
    public static final int GL_STENCIL_BACK_WRITEMASK         =   0x8CA5;
    public static final int GL_VIEWPORT                       =   0x0BA2;
    public static final int GL_SCISSOR_BOX                    =   0x0C10;
/*      GL_SCISSOR_TEST */
    public static final int GL_COLOR_CLEAR_VALUE              =   0x0C22;
    public static final int GL_COLOR_WRITEMASK                =   0x0C23;
    public static final int GL_UNPACK_ALIGNMENT               =   0x0CF5;
    public static final int GL_PACK_ALIGNMENT                 =   0x0D05;
    public static final int GL_MAX_TEXTURE_SIZE               =   0x0D33;
    public static final int GL_MAX_VIEWPORT_DIMS              =   0x0D3A;
    public static final int GL_SUBPIXEL_BITS                  =   0x0D50;
    public static final int GL_RED_BITS                       =   0x0D52;
    public static final int GL_GREEN_BITS                     =   0x0D53;
    public static final int GL_BLUE_BITS                      =   0x0D54;
    public static final int GL_ALPHA_BITS                     =   0x0D55;
    public static final int GL_DEPTH_BITS                     =   0x0D56;
    public static final int GL_STENCIL_BITS                   =   0x0D57;
    public static final int GL_POLYGON_OFFSET_UNITS           =   0x2A00;
/*      GL_POLYGON_OFFSET_FILL */
    public static final int GL_POLYGON_OFFSET_FACTOR          =   0x8038;
    public static final int GL_TEXTURE_BINDING_2D             =   0x8069;
    public static final int GL_SAMPLE_BUFFERS                 =   0x80A8;
    public static final int GL_SAMPLES                        =   0x80A9;
    public static final int GL_SAMPLE_COVERAGE_VALUE          =   0x80AA;
    public static final int GL_SAMPLE_COVERAGE_INVERT         =   0x80AB;

/* Buffer Objects */
    public static final int GL_ARRAY_BUFFER                   =     0x8892;
    public static final int GL_ELEMENT_ARRAY_BUFFER           =     0x8893;
    public static final int GL_ARRAY_BUFFER_BINDING           =     0x8894;
    public static final int GL_ELEMENT_ARRAY_BUFFER_BINDING   =     0x8895;

    public static final int GL_STREAM_DRAW                    =     0x88E0;
    public static final int GL_STATIC_DRAW                    =     0x88E4;
    public static final int GL_DYNAMIC_DRAW                   =     0x88E8;

    public static final int GL_BUFFER_SIZE                    =     0x8764;
    public static final int GL_BUFFER_USAGE                   =     0x8765;

    public static final int GL_CURRENT_VERTEX_ATTRIB          =     0x8626;

/* ErrorCode */
    public static final int GL_NO_ERROR                       =     0;
    public static final int GL_INVALID_ENUM                   =     0x0500;
    public static final int GL_INVALID_VALUE                  =     0x0501;
    public static final int GL_INVALID_OPERATION              =     0x0502;
    public static final int GL_OUT_OF_MEMORY                  =     0x0505;

/* FrontFaceDirection */
    public static final int GL_CW                             =     0x0900;
    public static final int GL_CCW                            =     0x0901;



/*
 * 
 *          these need special treatment and will possibly not be
 *          exactly like their C counter parts
 * 
 * 
    void glGetActiveAttrib(int program, int index,int bufSize, 
                    IntBuffer length, IntBuffer size, int *type, GLchar *name);

    void glGetActiveUniform (	int program,int index,int bufSize,IntBuffer length,
                    IntBuffer size,int *type,GLchar *name) ;

void glGetAttachedShaders(	int program,int maxCount,IntBuffer count,IntBuffer shaders) ;

void glGetVertexAttribPointerv(	int index,
int pname,
void **pointer) ; // see how this works in C

void glReadPixels(	int x,
int y,
int width,
int height,
int format,
int type,
void* data) ;  TODO best way to handle this
*/


    public static native int glGetAttribLocation(int program,String name);
    public static native void glGetBufferParameteriv(int target,int value,IntBuffer data);
    public static native int glGetError();
    public static native void glGetFramebufferAttachmentParameteriv(int target,
                    int attachment,int pname,IntBuffer params);
    public static native void glGetRenderbufferParameteriv(int target,int pname,IntBuffer params);
    public static native void glGetShaderPrecisionFormat(	int shaderType,int precisionType,
                    IntBuffer range,IntBuffer precision) ;
    public static native void glGetShaderSource(int shader,int bufSize,
                    IntBuffer length,ByteBuffer source);
    public static native void glGetTexParameterfv(int target,int pname,FloatBuffer params);
    public static native void glGetTexParameteriv(int target,int pname,IntBuffer params);
    public static native void glGetUniformfv(int program,int location,FloatBuffer params);
    public static native void glGetUniformiv(int program,int location,IntBuffer params);
    public static native void glGetVertexAttribfv(int index,int pname,FloatBuffer params);
    public static native void glGetVertexAttribiv(int index,int pname,IntBuffer params);
    public static native void glHint(int target,int mode);
    public static native boolean glIsBuffer (int buffer);
    public static native boolean glIsEnabled (int cap);
    public static native boolean glIsFramebuffer(int framebuffer);
    public static native boolean glIsProgram (int program) ;
    public static native boolean glIsRenderbuffer(int renderbuffer);
    public static native boolean glIsShader (int shader);
    public static native boolean glIsTexture (int texture) ;
    public static native void glLineWidth(float width);
    public static native void glPixelStorei(int pname,int param);
    public static native void glPolygonOffset (float factor,float units) ;
    public static native void glReleaseShaderCompiler();
    public static native void glSampleCoverage (float value,boolean invert) ;
    public static native void glScissor(int x,int y,int width,int height) ;
    public static native void glShaderBinary(int n,IntBuffer shaders,int binaryformat,
                                    ByteBuffer binary,int length);
    public static native void glStencilFunc(int func,int ref,int mask);
    public static native void glStencilFuncSeparate(int face,int func,int ref,int mask);
    public static native void glStencilMask(int mask);
    public static native void glStencilMaskSeparate(int face,int mask);
    public static native void glStencilOp(int sfail,int dpfail,int dppass);
    public static native void glStencilOpSeparate (int face,int sfail,int dpfail,int dppass);
    public static native void glTexParameterfv (int target,int pname,FloatBuffer  params);
    public static native void glTexParameteriv (int target,int pname,IntBuffer params);
    public static native void glUniform1f(int location,float v0);
    public static native void glUniform2f(int location,float v0,float v1);
    public static native void glUniform3f(int location,float v0,float v1,float v2);
    public static native void glUniform4f(int location,float v0,float v1,float v2,float v3);
    public static native void glUniform1i(int location,int v0);
    public static native void glUniform2i(int location,int v0,int v1);
    public static native void glUniform3i(int location,int v0,int v1,int v2);
    public static native void glUniform4i(int location,int v0,int v1,int v2,int v3);
    public static native void glUniform1fv(int location,int count,FloatBuffer value);
    public static native void glUniform2fv(int location,int count,FloatBuffer value);
    public static native void glUniform3fv(int location,int count,FloatBuffer value);
    public static native void glUniform4fv(int location,int count,FloatBuffer value);
    public static native void glUniform1iv(int location,int count,IntBuffer value);
    public static native void glUniform2iv(int location,int count,IntBuffer value);
    public static native void glUniform3iv(int location,int count,IntBuffer value);
    public static native void glUniform4iv(int location,int count,IntBuffer value);
    public static native void glUniformMatrix2fv(int location,int count,boolean transpose,FloatBuffer value);
    public static native void glUniformMatrix3fv(int location,int count,boolean transpose,FloatBuffer value);
    public static native void glValidateProgram(int program) ;
    public static native void glVertexAttrib1f(int index,float v0);
    public static native void glVertexAttrib2f(int index,float v0,float v1);
    public static native void glVertexAttrib3f(int index,float v0,float v1,float v2);
    public static native void glVertexAttrib4f(int index,float v0,float v1,float v2,float v3);
    public static native void glVertexAttrib1fv(int index,FloatBuffer v);
    public static native void glVertexAttrib2fv(int index,FloatBuffer v);
    public static native void glVertexAttrib3fv(int index,FloatBuffer v);
    public static native void glVertexAttrib4fv(int index,FloatBuffer v);
    public static native void glDepthRangef(float nearVal,float farVal) ;
    public static native void glDetachShader(int program,int shader) ;
    public static native void glDrawElements(int mode,int count,int type,IntBuffer ind);
    public static native void glDisable(int cap);
    public static native void glFinish();
    public static native void glFlush();
    public static native void glFramebufferRenderbuffer(int target,int attachment,int renderbuffertarget,int renderbuffer) ;
    public static native void glFrontFace(int mode);
    public static native void glGenBuffers(int n,IntBuffer buffers) ;
    public static native void glGenerateMipmap(int target);
    public static native void glGetBooleanv(int pname,ByteBuffer params);
    public static native void glGetFloatv(int pname,FloatBuffer params);
    public static native void glGetIntegerv(int pname,IntBuffer params);
    public static native void glDepthMask(boolean flag);
    public static native void glDepthFunc(int func);
    public static native void glDeleteTextures(int n ,IntBuffer names);
    public static native void glDeleteShader(int sname);
    public static native void glDeleteRenderbuffers(int n ,IntBuffer names);
    public static native void glDeleteProgram(int name);
    public static native void glDeleteFramebuffers(int n ,IntBuffer names);
    public static native void glDeleteBuffers(int num,IntBuffer names);
    public static native void glCullFace(int mode);
    public static native void glCopyTexSubImage2D(int target,int level,int xoffset,
                int yoffset,int x,int y,int width,int height);
    public static native void glCopyTexImage2D(int target,int level,int internalformat,
                int x,int y,int width,int height,int border);
    public static native void glCompressedTexSubImage2D(int target,int level,int xoffset,
                int yoffset,int width,int height,int format,int imageSize,ByteBuffer data);
    public static native void glCompressedTexImage2D(int target, int level, int internalformat,
                                    int width,int height,int border,int imageSize,ByteBuffer data);
    public static native void glColorMask(boolean red, boolean green, boolean blue, boolean alpha);
    public static native void glClearStencil(int s);
    public static native void glClearDepthf(float d);
    public static native int glCheckFramebufferStatus(int target);
    public static native void glBufferSubData(int target,long offset,long size,ByteBuffer data);
    public static native void glBufferData(int target,long size,ByteBuffer data,int usage);
    public static native void glBlendFuncSeparate(int srcRGB,int dstRGB,int srcAlpha,int dstAlpha);
    public static native void glBlendFunc(int sfactor, int dfactor);
    public static native void glEnable(int cap);
    public static native void glBlendEquationSeparate(int modeRGB, int modeAlpha) ;
    public static native void glBlendEquation(int mode);
    public static native void glBlendColor(float red, float green,float blue,float alpha);
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
    
    // differs as only accepts 1 shader TODO - worth providing alternitive that
    // takes multiples like the C version?
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
    
    static {
        System.loadLibrary("Jgles2");
    }    
    
    GLES2() {
        
    }
}
