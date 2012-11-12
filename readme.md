
Jgles2

Why GLES 2.0?

it makes a very nice subset of the various versions of opengl, and its
basically everywhere from your phone to your browser (webgl)



Why not just use LWJGL or <insert your binding of choice here> ?

LWJGL is a fantastic library to use and it makes a great "driver"
layer for higher level libraries such as libGDX, however it's not "pure"
GLES 2.0.  Obviously if there is an identical function in GL 1.0 and 
GL 2.0 you're not going to duplicate.  So you end up having to use GL10 
and GL20 static classes (and sometimes you forget what lives where)
At the time of writing LWJGL's GLES 2.0 classes are not included in
the binary download. 



So is there a better way to organise a subset?

I aim to provide (in the end) just three classes

Jgles2.util    some platform specific stuff, general utilities etc.
Jgles2.EGL     access to libEGL
Jgles2.GLES2   access to libGLES v2.0



Is it just like using GLES ?

Its fairly close but there are some slight differences for convenience



So is it ready to rock and roll ?

No! well close(ish) at the moment the only thing the test class does
is flash up a window with a coloured trangle in it for a few seconds.
 


a Makefile, REALLY??? whats wrong with ant?

yeah yeah I know, I should have an ant build file, but this all started
off as a simple C example that gradually morphed into a jni library
theres tonnes of stuff still to do and it depends both on my time and
the level of contributions



I'd like to chat to talk about how I might help?

my email account is codifies with googles mail service.
 
