
Jgles2
------

NB see porting at the end...


Why GLES 2.0?
-------------

it makes a very nice subset of the various versions of opengl, and its
basically everywhere from your phone to your browser (webgl)



Why not just use LWJGL or [ insert your binding of choice here ] ?
------------------------------------------------------------------

LWJGL is a fantastic library to use and it makes a great "driver"
layer for higher level libraries such as libGDX, however it's not "pure"
GLES 2.0.  Obviously if there is an identical function in GL 1.0 and 
GL 2.0 you're not going to duplicate.  So you end up having to use GL10 
and GL20 static classes (and sometimes you forget what lives where)
At the time of writing LWJGL's GLES 2.0 classes are not included in
the binary download. 



So is there a better way to organise a subset?
----------------------------------------------

I aim to provide (in the end) just three classes

Jgles2.util    some platform specific stuff, general utilities etc.

Jgles2.EGL     access to libEGL

Jgles2.GLES2   access to libGLES v2.0



Is it just like using GLES ?
----------------------------

Its fairly close but there are some slight differences for convenience



So is it ready to rock and roll ?
---------------------------------

...well close(ish) at the moment the test class shows a single triangle
rotating and moving in a 3d view and exits when the escape key is pressed
there is mouse handling too as well as support for window resizing



Porting - you can help
----------------------

If you want to port Jgles to your platform (it would certainly be
apreciated) I have marked any platform specific with #ifdef XORG

Currently there are just 3 critical sections

get_native_display() - returns the native handle used to create the EGL
display handle

make_native_window(

    native_display,     the native display handle
    
    egl_display         the EGL display handle
    
    config              the chosen EGL config
    
    x, y,               position on the screen
    
    width, height,      width/height of window 
    
    fullscreen)         should it be fullscreen without decoration
    

pumpEvents(native_Display,native_window)

this is resposible for collection keyboard and mouse events
and notifying the application that the screen needs resizing
its is responsible for modifying the following global variables
the jni wrapper uses.

        bool __keys[256];       keydown flags

        int __mouse[3];         x,y,buttons

        bool __resize=false;    resize needed

        int __width,__height;   when resized the dimensions are stored here.




I'd like to chat to talk about how I might help?
------------------------------------------------

my email account is codifies with googles mail service.
 
