CC=gcc

#JAVA_HOME=`readlink -f /usr/bin/javac | sed "s:bin/javac::"`
# gentoo is a little less KISS !
JAVA_HOME=/usr/lib/jvm/icedtea-7

LIBS=$(shell pkg-config egl --libs) $(shell pkg-config glesv2 --libs) -lm -lc -lX11 

#CFLAGS = -fPIC `pkg-config egl --cflags` `pkg-config glesv2 --cflags` -I$(JAVA_HOME)/include/ -Ikazmath/
# iced tea has some additional headers in include/linux
CFLAGS=-fPIC -I$(JAVA_HOME)/include/ -I$(JAVA_HOME)/include/linux $(shell pkg-config egl --cflags)
OBJ = jni/util.o jni/egl.o jni/gles2.o



libJgles2.so: $(OBJ) jni/Jgles2_util.h jni/Jgles2_util.h jni/Jgles2_GLES2.h
	$(CC) -fPIC -shared -Wl,-soname,libJgles2.so $(OBJ) $(LIBS) -o $@ 

jni/util.o: jni/util.c jni/Jgles2_util.h
	$(CC) $(CFLAGS) -c $< -o $@
	
jni/egl.o: jni/egl.c jni/Jgles2_EGL.h
	$(CC) $(CFLAGS) -c $< -o $@

jni/gles2.o: jni/gles2.c jni/Jgles2_GLES2.h
	$(CC) $(CFLAGS) -c $< -o $@

jni/Jgles2_util.h: src/Jgles2/util.java
	javac src/Jgles2/util.java
	javah -d jni -classpath src Jgles2.util
	
jni/Jgles2_EGL.h: src/Jgles2/EGL.java
	javac src/Jgles2/EGL.java
	javah -d jni -classpath src Jgles2.EGL
	
jni/Jgles2_GLES2.h: src/Jgles2/GLES2.java
	javac src/Jgles2/GLES2.java
	javah -d jni -classpath src Jgles2.GLES2



