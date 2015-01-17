
Jgles2
------

NB see please porting at the end...


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



How is Jgles2 organised ?
-------------------------

There are just two classes

Jgles2.GLES2   access to libGLES v2.0

Jgles2.util    some native context creation stuff, general utilities etc.

There used to be a C math library wrapped as well, but there was little
to be gained from this and there are various Math routines (matrix, 
quaternion etc) in the Java demo. Further development has lead to the C 
side using GLFW3 for creating a native context instead of X11
this *should*! make it trivial to port to other platforms


Is it just like using GLES ?
----------------------------

Its close but there are a few slight differences for convenience



So is it ready to rock and roll ?
---------------------------------

Basically yes - there are a small handful of routines unwrapped that
I don't use (contributions welcome) There is a full application
demonstrating gimbaless rotation, the library has also been tested using
techniques like render to texture.


Porting - you can help (yes! you!)
----------------------------------

If you want to port Jgles to your platform (it would certainly be
apreciated) You just need to be sure that GLFW3 can create and use a
GLES2.0 context - porting should be easy! (famous last words!)




I'd like to chat to talk about how I might help?
------------------------------------------------

my email account is codifies with a co uk domain of bedroomcoders, what will we do when
spam bots can work that one out...
 
