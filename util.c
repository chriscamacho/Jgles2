#include "Jgles2_util.h"
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <EGL/egl.h>

#include <string.h> // memset

// only one platform sould be defined!
#define XORG 1      // native mesa gles
#define MACOS 1     // use subset of opengl 2.0 ?
#define MSWIN 1     // 

JNIEXPORT jlong JNICALL Java_Jgles2_util_get_1native_1display(JNIEnv *e, jobject o) {
    #ifdef XORG
    return (unsigned long)XOpenDisplay(NULL);
    #endif // XORG
}

JNIEXPORT jlong JNICALL Java_Jgles2_util_make_1native_1window
  (JNIEnv *e, jclass c, jlong jnative_dpy, jlong jegl_dpy, jlong jconf,
        jint x, jint y,jint width, jint height, jboolean fullscreen) {
    
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
    attr.event_mask = StructureNotifyMask | ExposureMask | KeyPressMask;
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
