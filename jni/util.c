#include "Jgles2_util.h"
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <EGL/egl.h>

#include <string.h> // memset
#include <stdbool.h>

#include <vec3.h>
#include <mat4.h>

// only one platform sould be defined!
#define XORG 1      // native mesa gles
//#define MACOS 1     // use subset of opengl 2.0 ?
//#define MSWIN 1     // angle project



// TODO when someone contributes code for other platforms
// what to do about key values? java ifdef ???
bool __keys[256];
int __mouse[3]; // TODO implement java access routines for this...
bool __resize=false;
int __width,__height;

JNIEXPORT jint JNICALL Java_Jgles2_util_getMouseButtons
  (JNIEnv *e, jclass c)
{
    return __mouse[2];
}

JNIEXPORT jint JNICALL Java_Jgles2_util_getMouseX
  (JNIEnv *e, jclass c) 
{
    return __mouse[0];
}

JNIEXPORT jint JNICALL Java_Jgles2_util_getMouseY
  (JNIEnv *e, jclass c) 
{
    return __mouse[1];
}


JNIEXPORT jlong JNICALL Java_Jgles2_util_get_1native_1display(JNIEnv *e, jobject o) {
    #ifdef XORG
    return (unsigned long)XOpenDisplay(NULL);
    #endif // XORG
}

JNIEXPORT jlong JNICALL Java_Jgles2_util_make_1native_1window
  (JNIEnv *e, jclass c, jlong jnative_dpy, jlong jegl_dpy, jlong jconf,
        jint x, jint y,jint width, jint height, jboolean fullscreen) {
    
    __width = width;    // needed on all platforms ensures get w/h
    __height = height;  // works even when screen hasn't been resized...
    
    #ifdef XORG
    
    NativeDisplayType native_dpy = (NativeDisplayType)jnative_dpy;
    EGLDisplay egl_dpy = (EGLDisplay)jegl_dpy;
    EGLConfig config = (EGLConfig)jconf;
    
    int scrnum;
    XSetWindowAttributes attr;
    unsigned long mask;
    Window root,native_win;
    XVisualInfo *visInfo, visTemplate;
    int num_visuals;
    EGLint vid;

    scrnum = DefaultScreen( native_dpy );
    root = RootWindow( native_dpy, scrnum );

    if (!eglGetConfigAttrib(egl_dpy, config, EGL_NATIVE_VISUAL_ID, &vid)) {
        printf("Error: eglGetConfigAttrib() failed\n");
        return 0;
    }

    /* The X window visual must match the EGL config */
    visTemplate.visualid = vid;
    visInfo = XGetVisualInfo(native_dpy, VisualIDMask, &visTemplate, &num_visuals);
    if (!visInfo) {
        printf("Error: couldn't get X visual\n");
        return 0;
    }

    /* window attributes */
    attr.background_pixel = 0;
    attr.border_pixel = 0;
    attr.colormap = XCreateColormap( native_dpy, root, visInfo->visual, AllocNone);
    attr.event_mask = StructureNotifyMask | KeyPressMask | KeyReleaseMask |
                 ButtonPressMask | ButtonReleaseMask | PointerMotionMask;
                 
    mask = CWBackPixel | CWBorderPixel | CWColormap | CWEventMask;

    native_win = XCreateWindow( native_dpy, root, 0, 0, width, height,
                         0, visInfo->depth, InputOutput,
                         visInfo->visual, mask, &attr );
    XFree(visInfo);
    
    /* set hints and properties */
    XSizeHints sizehints;
    sizehints.x = x;
    sizehints.y = y;
    sizehints.width  = width;
    sizehints.height = height;
    sizehints.flags = USSize | USPosition;
    XSetNormalHints(native_dpy, native_win, &sizehints);
    XSetStandardProperties(native_dpy, native_win, "", "",
                           None, (char **)NULL, 0, &sizehints);

    XMapWindow(native_dpy, native_win); // might as well show it as we made it ;)

    if (fullscreen!=0) fullscreen = 1;

    XEvent xev;
    Atom wm_state = XInternAtom(native_dpy, "_NET_WM_STATE", False);
    Atom fullscreenA = XInternAtom(native_dpy, "_NET_WM_STATE_FULLSCREEN", False);

    memset(&xev, 0, sizeof(xev));
    xev.type = ClientMessage;
    xev.xclient.window = native_win;
    xev.xclient.message_type = wm_state;
    xev.xclient.format = 32;
    xev.xclient.data.l[0] = fullscreen;
    xev.xclient.data.l[1] = fullscreenA;
    xev.xclient.data.l[2] = None;
    xev.xclient.data.l[3] = 1;
    xev.xclient.data.l[4] = 0;

    XSendEvent(native_dpy, DefaultRootWindow(native_dpy), False,
        SubstructureRedirectMask | SubstructureNotifyMask, &xev);    

    
    return native_win;
    #endif // XORG
}

JNIEXPORT void JNICALL Java_Jgles2_util_closeWindow
  (JNIEnv *e, jclass c, jlong d, jlong w) 
{
      XDestroyWindow((Display*)d, (NativeWindowType)w);
}


JNIEXPORT void JNICALL Java_Jgles2_util_pumpEvents
  (JNIEnv *e, jclass c, jlong xd,jlong w)
{
    #ifdef XORG
    Display* xdisplay=(Display*)xd;
    NativeWindowType win=(NativeWindowType)w;
    XEvent event;


    while (XEventsQueued(xdisplay, QueuedAfterReading)) {
        XNextEvent(xdisplay, &event);
        switch (event.type) {

        case KeyPress:
            __keys[event.xkey.keycode & 0xff] = true;
            //printf("key=%i\n",event.xkey.keycode & 0xff);
            break;

        case KeyRelease:
            __keys[event.xkey.keycode & 0xff] = false;
            break;

        case MotionNotify:
            __mouse[0] = event.xbutton.x;
            __mouse[1] = event.xbutton.y;
            //printf("mouse %i,%i\n",__mouse[0],__mouse[1]);
            break;

        case ButtonPress:
            __mouse[2] =
                __mouse[2] | (int)pow(2, event.xbutton.button - 1);
            break;
        case ButtonRelease:
            __mouse[2] =
                __mouse[2] & (int)(255 -
                                   pow(2,
                                       event.xbutton.button - 1));
            break;
        case ConfigureNotify:
            __resize=true;
            Window root_return;
            int x_return, y_return;
            unsigned int border_width_return;
            unsigned int depth_return;
            XGetGeometry(xdisplay, (Drawable)win, &root_return,
                        &x_return, &y_return,
                        &__width, &__height,
                        &border_width_return, &depth_return);

            break;
        }

    }
    #endif // XORG
}

JNIEXPORT jint JNICALL Java_Jgles2_util_getHeight
  (JNIEnv *e, jclass c)
{
    return __height;
}

JNIEXPORT jint JNICALL Java_Jgles2_util_getWidth
  (JNIEnv *e, jclass c)
{
    return __width;
}

JNIEXPORT jboolean JNICALL Java_Jgles2_util_keyDown
  (JNIEnv *e, jclass c, jint k)
{
    return __keys[k];
}

JNIEXPORT jboolean JNICALL Java_Jgles2_util_resizeRequired
  (JNIEnv *e, jclass c)
{
    bool r = __resize;
    __resize = false;
    return r;
}



//    public static native FloatBuffer kmMat4Identity(FloatBuffer mat);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4Identity
  (JNIEnv * e, jclass c, jobject m)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Identity(mat);
    return m;
}

//    public static native FloatBuffer kmVec3Fill(FloatBuffer v, float x, float y, float z);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmVec3Fill
  (JNIEnv *e, jclass c, jobject v, jfloat x, jfloat y, jfloat z)
{
    kmVec3* vec = (kmVec3*)(*e)->GetDirectBufferAddress(e, v);
    kmVec3Fill(vec,(float)x,(float)y,(float)z);
    return v;
}

//    public static native FloatBuffer kmVec3Subtract(FloatBuffer out,FloatBuffer p1,FloatBuffer p2);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmVec3Subtract
  (JNIEnv *e, jclass c, jobject o, jobject a, jobject b)
{
    kmVec3* out = (kmVec3*)(*e)->GetDirectBufferAddress(e, o);
    kmVec3* p1 = (kmVec3*)(*e)->GetDirectBufferAddress(e, a);
    kmVec3* p2 = (kmVec3*)(*e)->GetDirectBufferAddress(e, b);
    kmVec3Subtract(out,p1,p2);
    return o;
}

//     public static native FloatBuffer kmVec3Normalize(FloatBuffer out,FloatBuffer in);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmVec3Normalize
  (JNIEnv *e, jclass c, jobject o, jobject i)
{
    kmVec3* out = (kmVec3*)(*e)->GetDirectBufferAddress(e, o);
    kmVec3* in = (kmVec3*)(*e)->GetDirectBufferAddress(e, i);
    kmVec3Normalize(out,in);
    return o;    
}

//     public static native Float kmVec3Length(FloatBuffer in);
JNIEXPORT jfloat JNICALL Java_Jgles2_util_kmVec3Length
  (JNIEnv *e, jclass c, jobject i)
{
    kmVec3* in = (kmVec3*)(*e)->GetDirectBufferAddress(e, i);
    return (jfloat)kmVec3Length(in);
}

//    public static native FloatBuffer kmMat4LookAt(FloatBuffer view,FloatBuffer eye,FloatBuffer centre,FloatBuffer up);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4LookAt
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
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4PerspectiveProjection
  (JNIEnv *e, jclass c, jobject p, jfloat fov, jfloat aspect, jfloat near, jfloat far)
{
    kmMat4* proj = (kmMat4*)(*e)->GetDirectBufferAddress(e, p);
    kmMat4PerspectiveProjection(proj,fov,aspect,near,far);
    return p;
}

//     public static native FloatBuffer kmMat4Multiply(FloatBuffer out,FloatBuffer mat1,FloatBuffer mat2);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4Multiply
  (JNIEnv *e, jclass c, jobject o, jobject m1, jobject m2)
{
    kmMat4* out = (kmMat4*)(*e)->GetDirectBufferAddress(e, o);
    kmMat4* mat1 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m1);
    kmMat4* mat2 = (kmMat4*)(*e)->GetDirectBufferAddress(e, m2);
    kmMat4Multiply(out,mat1,mat2);
    return o;    
}

//    public static native FloatBuffer kmMat4Translation(FloatBuffer mat, float x, float y, float z);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4Translation
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4Translation(mat,x,y,z);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4RotationX
  (JNIEnv *e, jclass c, jobject m, jfloat rad)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationX(mat,rad);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4RotationY
  (JNIEnv *e, jclass c, jobject m, jfloat rad)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationY(mat,rad);
    return m;
}

JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4RotationZ
  (JNIEnv *e, jclass c, jobject m, jfloat rad)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationZ(mat,rad);
    return m;
}

//    public static native FloatBuffer kmMat4RotationPitchYawRoll(FloatBuffer mat, float x, float y, float z);
JNIEXPORT jobject JNICALL Java_Jgles2_util_kmMat4RotationPitchYawRoll
  (JNIEnv *e, jclass c, jobject m, jfloat x, jfloat y, jfloat z)
{
    kmMat4* mat = (kmMat4*)(*e)->GetDirectBufferAddress(e, m);
    kmMat4RotationPitchYawRoll(mat,x,y,z);
    return m;
}
