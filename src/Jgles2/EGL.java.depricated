
package Jgles2;

import java.nio.IntBuffer;
import java.nio.LongBuffer;

/**
 * a very thin wrapper around libEGL use the functions as you would from C
 */
public class EGL {
    
    /* QueryString targets */

    public static final int EGL_VERSION     =   0x3054;
    public static final int EGL_EXTENSIONS  =   0x3055;
    public static final int EGL_CLIENT_APIS =   0x308D;

	/* Config attributes */
    public static final int EGL_BUFFER_SIZE			    =    0x3020;
    public static final int EGL_ALPHA_SIZE			    =    0x3021;
    public static final int EGL_BLUE_SIZE			    =    0x3022;
    public static final int EGL_GREEN_SIZE			    =    0x3023;
    public static final int EGL_RED_SIZE			    =    0x3024;
    public static final int EGL_DEPTH_SIZE			    =    0x3025;
    public static final int EGL_STENCIL_SIZE		    =    0x3026;
    public static final int EGL_CONFIG_CAVEAT		    =    0x3027;
    public static final int EGL_CONFIG_ID			    =    0x3028;
    public static final int EGL_LEVEL			        =    0x3029;
    public static final int EGL_MAX_PBUFFER_HEIGHT		=    0x302A;
    public static final int EGL_MAX_PBUFFER_PIXELS		=    0x302B;
    public static final int EGL_MAX_PBUFFER_WIDTH		=    0x302C;
    public static final int EGL_NATIVE_RENDERABLE		=    0x302D;
    public static final int EGL_NATIVE_VISUAL_ID		=    0x302E;
    public static final int EGL_NATIVE_VISUAL_TYPE		=    0x302F;
    public static final int EGL_SAMPLES			        =    0x3031;
    public static final int EGL_SAMPLE_BUFFERS		    =    0x3032;
    public static final int EGL_SURFACE_TYPE		    =    0x3033;
    public static final int EGL_TRANSPARENT_TYPE		=    0x3034;
    public static final int EGL_TRANSPARENT_BLUE_VALUE	=    0x3035;
    public static final int EGL_TRANSPARENT_GREEN_VALUE	=    0x3036;
    public static final int EGL_TRANSPARENT_RED_VALUE	=    0x3037;
    public static final int EGL_NONE			        =    0x3038;	/* Attrib list terminator */
    public static final int EGL_BIND_TO_TEXTURE_RGB		=    0x3039;
    public static final int EGL_BIND_TO_TEXTURE_RGBA	=    0x303A;
    public static final int EGL_MIN_SWAP_INTERVAL		=    0x303B;
    public static final int EGL_MAX_SWAP_INTERVAL		=    0x303C;
    public static final int EGL_LUMINANCE_SIZE		    =    0x303D;
    public static final int EGL_ALPHA_MASK_SIZE		    =    0x303E;
    public static final int EGL_COLOR_BUFFER_TYPE		=    0x303F;
    public static final int EGL_RENDERABLE_TYPE		    =    0x3040;
    public static final int EGL_MATCH_NATIVE_PIXMAP		=    0x3041;	/* Pseudo-attribute (not queryable) */
    public static final int EGL_CONFORMANT			    =    0x3042;

	/* Config attribute values */
    public static final int EGL_SLOW_CONFIG			    =    0x3050;	/* EGL_CONFIG_CAVEAT value */
    public static final int EGL_NON_CONFORMANT_CONFIG	=    0x3051;	/* EGL_CONFIG_CAVEAT value */
    public static final int EGL_TRANSPARENT_RGB		    =    0x3052;	/* EGL_TRANSPARENT_TYPE value */
    public static final int EGL_RGB_BUFFER			    =    0x308E;	/* EGL_COLOR_BUFFER_TYPE value */
    public static final int EGL_LUMINANCE_BUFFER		=    0x308F;	/* EGL_COLOR_BUFFER_TYPE value */

	/* More config attribute values, for EGL_TEXTURE_FORMAT */
    public static final int EGL_NO_TEXTURE			=    0x305C;
    public static final int EGL_TEXTURE_RGB			=    0x305D;
    public static final int EGL_TEXTURE_RGBA		=    0x305E;
    public static final int EGL_TEXTURE_2D			=    0x305F;

	/* Config attribute mask bits */
    public static final int EGL_PBUFFER_BIT			        =    0x0001;	/* EGL_SURFACE_TYPE mask bits */
    public static final int EGL_PIXMAP_BIT			        =    0x0002;	/* EGL_SURFACE_TYPE mask bits */
    public static final int EGL_WINDOW_BIT			        =    0x0004;	/* EGL_SURFACE_TYPE mask bits */
    public static final int EGL_VG_COLORSPACE_LINEAR_BIT	=    0x0020;	/* EGL_SURFACE_TYPE mask bits */
    public static final int EGL_VG_ALPHA_FORMAT_PRE_BIT	    =    0x0040;	/* EGL_SURFACE_TYPE mask bits */
    public static final int EGL_MULTISAMPLE_RESOLVE_BOX_BIT =    0x0200;	/* EGL_SURFACE_TYPE mask bits */
    public static final int EGL_SWAP_BEHAVIOR_PRESERVED_BIT =    0x0400;	/* EGL_SURFACE_TYPE mask bits */

    public static final int EGL_OPENGL_ES_BIT		=    0x0001;	/* EGL_RENDERABLE_TYPE mask bits */
    public static final int EGL_OPENVG_BIT			=    0x0002;	/* EGL_RENDERABLE_TYPE mask bits */
    public static final int EGL_OPENGL_ES2_BIT		=    0x0004;	/* EGL_RENDERABLE_TYPE mask bits */
    public static final int EGL_OPENGL_BIT			=    0x0008;	/* EGL_RENDERABLE_TYPE mask bits */

	/* Out-of-band handle values */
    public static final long EGL_DEFAULT_DISPLAY    =   0;
    public static final long EGL_NO_CONTEXT			=   0;
    public static final long EGL_NO_DISPLAY			=   0;
    public static final long EGL_NO_SURFACE			=   0;

	/* CreateContext attributes */
    public static final int EGL_CONTEXT_CLIENT_VERSION  =   0x3098;


	/* QuerySurface / SurfaceAttrib / CreatePbufferSurface targets */
    public static final int EGL_HEIGHT			        =    0x3056;
    public static final int EGL_WIDTH			        =    0x3057;
    public static final int EGL_LARGEST_PBUFFER		    =    0x3058;
    public static final int EGL_TEXTURE_FORMAT		    =    0x3080;
    public static final int EGL_TEXTURE_TARGET		    =    0x3081;
    public static final int EGL_MIPMAP_TEXTURE		    =    0x3082;
    public static final int EGL_MIPMAP_LEVEL		    =    0x3083;
    public static final int EGL_RENDER_BUFFER		    =    0x3086;
    public static final int EGL_VG_COLORSPACE		    =    0x3087;
    public static final int EGL_VG_ALPHA_FORMAT		    =    0x3088;
    public static final int EGL_HORIZONTAL_RESOLUTION	=    0x3090;
    public static final int EGL_VERTICAL_RESOLUTION		=    0x3091;
    public static final int EGL_PIXEL_ASPECT_RATIO		=    0x3092;
    public static final int EGL_SWAP_BEHAVIOR		    =    0x3093;
    public static final int EGL_MULTISAMPLE_RESOLVE		=    0x3099;



    
    static {
        System.loadLibrary("Jgles2");
    }
    
    static public native long eglGetDisplay(long native_display);
    static public native boolean eglInitialize(long egl_display);
    static public native String eglQueryString(long egl_display, int name);
    static public native boolean eglChooseConfig(long display, IntBuffer attribs,
                                        LongBuffer configs, int config_size, IntBuffer num_config);
        
    static public native long eglCreateContext(long egl_display, long config, long share, IntBuffer ctx_attribs );    
    static public native int eglQueryContext(long egl_display, long context, int attrib, IntBuffer val);
    static public native long eglCreateWindowSurface(long egl_display, long config, long native_win, IntBuffer attribs);
    static public native void eglQuerySurface(long egl_display, long egl_surface, int attrib, IntBuffer val);
    static public native void eglGetConfigAttrib(long egl_display, long config, int attrib, IntBuffer val);
    static public native boolean eglMakeCurrent(long egl_display, long draw_surface, long read_surface, long egl_context);
    static public native int eglGetError();
    static public native void eglSwapBuffers(long egl_display, long egl_surface);
    static public native boolean eglDestroySurface( long egl_display, long egl_surface);
    static public native boolean eglDestroyContext( long egl_display, long egl_context);        
    static public native boolean eglSwapInterval(long display, int interval);
    
    EGL() {
        System.out.println("NB do not instance me!");
    }
}
