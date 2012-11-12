CC=gcc

JAVA_HOME=`readlink -f /usr/bin/javac | sed "s:bin/javac::"`

LIBS = `pkg-config egl --libs` `pkg-config glesv2 --libs` -lm -lc -lX11 
CFLAGS = -fPIC `pkg-config egl --cflags` `pkg-config glesv2 --cflags` -I$(JAVA_HOME)/include/ 
OBJ = util.o egl.o gles2.o

	
run: test.class libJgles2.so
	java -Djava.library.path=. test

libJgles2.so: $(OBJ) Jgles2_util.h Jgles2_util.h Jgles2_GLES2.h
	$(CC) -fPIC -shared -Wl,-soname,libJgles2.so $(OBJ) $(LIBS) -o $@ 
	
util.o: util.c Jgles2_util.h Jgles2/BufferUtils.class
	$(CC) $(CFLAGS) -c $< -o $@
	
egl.o: egl.c Jgles2_EGL.h
	$(CC) $(CFLAGS) -c $< -o $@

gles2.o: gles2.c Jgles2_GLES2.h
	$(CC) $(CFLAGS) -c $< -o $@

Jgles2_util.h: Jgles2/util.java
	javac Jgles2/util.java
	javah Jgles2.util
	
Jgles2_EGL.h: Jgles2/EGL.java
	javac Jgles2/EGL.java
	javah Jgles2.EGL
	
Jgles2_GLES2.h: Jgles2/GLES2.java
	javac Jgles2/GLES2.java
	javah Jgles2.GLES2

Jgles2/BufferUtils.class: Jgles2/BufferUtils.java
	javac Jgles2/BufferUtils.java

clean:
	rm -rf Jgles2/*.class
	rm -rf *.class
	rm -rf *.o
	rm -rf libJgles2.so
	rm -rf Jgles2_util.h
	rm -rf Jgles2_EGL.h
	rm -rf Jgles2_GLES2.h
	rm -rf hs_err_pid*
	
test.class: test.java
	javac test.java


