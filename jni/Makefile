CC=gcc

JAVA_HOME=`readlink -f /usr/bin/javac | sed "s:bin/javac::"`

LIBS = `pkg-config egl --libs` `pkg-config glesv2 --libs` -lm -lc -lX11 
CFLAGS = -fPIC `pkg-config egl --cflags` `pkg-config glesv2 --cflags` -I$(JAVA_HOME)/include/ 
OBJ = jni/util.o jni/egl.o jni/gles2.o

	
#run: test.class libJgles2.so
#	java -Djava.library.path=. test

libJgles2.so: $(OBJ) jni/Jgles2_util.h jni/Jgles2_util.h jni/Jgles2_GLES2.h
	$(CC) -fPIC -shared -Wl,-soname,libJgles2.so $(OBJ) $(LIBS) -o $@ 
	
util.o: jni/util.c jni/Jgles2_util.h Jgles2/BufferUtils.class
	$(CC) $(CFLAGS) -c $< -o jni/$@
	
egl.o: jni/egl.c jni/Jgles2_EGL.h
	$(CC) $(CFLAGS) -c $< -o jni/$@

gles2.o: jni/gles2.c jni/Jgles2_GLES2.h
	$(CC) $(CFLAGS) -c $< -o jni/$@

jni/Jgles2_util.h: Jgles2/util.java
	javac Jgles2/util.java
	javah -d jni Jgles2.util
	
jni/Jgles2_EGL.h: Jgles2/EGL.java
	javac Jgles2/EGL.java
	javah -d jni Jgles2.EGL
	
jni/Jgles2_GLES2.h: Jgles2/GLES2.java
	javac Jgles2/GLES2.java
	javah -d jni Jgles2.GLES2

Jgles2/BufferUtils.class: Jgles2/BufferUtils.java
	javac Jgles2/BufferUtils.java

clean:
	rm -rf Jgles2/*.class
	rm -rf jni/*.o
	rm -rf libJgles2.so
	rm -rf jni/Jgles2_util.h
	rm -rf jni/Jgles2_EGL.h
	rm -rf jni/Jgles2_GLES2.h
	rm -rf hs_err_pid*
	
#test.class: test.java
#	javac test.java


