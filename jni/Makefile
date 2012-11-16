CC=gcc

JAVA_HOME=`readlink -f /usr/bin/javac | sed "s:bin/javac::"`

LIBS = `pkg-config egl --libs` `pkg-config glesv2 --libs` -lm -lc -lX11 
CFLAGS = -fPIC `pkg-config egl --cflags` `pkg-config glesv2 --cflags` -I$(JAVA_HOME)/include/ -Ikazmath/
OBJ = jni/util.o jni/egl.o jni/gles2.o jni/kazmath.o

#kazmath
KAZ=$(shell find kazmath/*.c | sed 's/\(.*\.\)c/\1o/g' | sed 's/kazmath\//kazmath\//g')


libJgles2.so: $(OBJ) kazmath/libkazmath.a jni/Jgles2_util.h jni/Jgles2_util.h jni/Jgles2_GLES2.h
	$(CC) -fPIC -shared -Wl,-soname,libJgles2.so $(OBJ) kazmath/libkazmath.a $(LIBS) -o $@ 

kazmath/%.o: kazmath/%.c
	gcc -c -shared -std=c99 $(CFLAGS) $< -o $@
	
kazmath/libkazmath.a: $(KAZ)
	ar -cvq kazmath/libkazmath.a $(KAZ) 
	
jni/util.o: jni/util.c jni/Jgles2_util.h
	$(CC) $(CFLAGS) -c $< -o $@
	
jni/egl.o: jni/egl.c jni/Jgles2_EGL.h
	$(CC) $(CFLAGS) -c $< -o $@

jni/gles2.o: jni/gles2.c jni/Jgles2_GLES2.h
	$(CC) $(CFLAGS) -c $< -o $@

jni/kazmath.o: jni/kazmath.c jni/Jgles2_kazmath.h
	$(CC) $(CFLAGS) -c $< -o $@

jni/Jgles2_util.h: Jgles2/util.java
	javac Jgles2/util.java
	javah -d jni Jgles2.util
	
jni/Jgles2_EGL.h: Jgles2/EGL.java
	javac Jgles2/EGL.java
	javah -d jni Jgles2.EGL
	
jni/Jgles2_GLES2.h: Jgles2/GLES2.java
	javac Jgles2/GLES2.java
	javah -d jni Jgles2.GLES2

jni/Jgles2_kazmath.h: Jgles2/kazmath.java
	javac Jgles2/kazmath.java
	javah -d jni Jgles2.kazmath


