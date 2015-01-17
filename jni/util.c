#include "Jgles2_util.h"

#define GLFW_INCLUDE_ES2
#include <GLFW/glfw3.h>

#include <string.h> // memset
#include <stdbool.h>

#include <math.h>



int __mouse[3]; 
bool __resize=false;
int __width,__height;

GLFWwindow* __window;


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


JNIEXPORT jboolean JNICALL Java_Jgles2_util_resizeRequired
  (JNIEnv *e, jclass c)
{
    bool r = __resize;
    __resize = false;
    return r;
}

// these are identical but are needed so that they can be
// called from java with different types of buffer.
JNIEXPORT jlong JNICALL Java_Jgles2_util_getFloatBufferPtr
  (JNIEnv *e, jclass c, jobject buff)
{
	void* ptr = (void*)(*e)->GetDirectBufferAddress(e, buff);
	return (jlong)ptr;
}

JNIEXPORT jlong JNICALL Java_Jgles2_util_getIntBufferPtr
  (JNIEnv *e, jclass c, jobject buff)
{
	void* ptr = (void*)(*e)->GetDirectBufferAddress(e, buff);
	return (jlong)ptr;
}

JNIEXPORT jlong JNICALL Java_Jgles2_util_getByteBufferPtr
  (JNIEnv *e, jclass c, jobject buff)
{
	void* ptr = (void*)(*e)->GetDirectBufferAddress(e, buff);
	return (jlong)ptr;
}


void window_size_callback(GLFWwindow* window, int width, int height)
{

	if (width != __width || height != __height) {
		__width = width;
		__height = height;
		__resize = true;
	}

}

void mouse_button_callback(GLFWwindow *window, int button, int action, int mods)
{
	if (window=__window) {
		if (action==GLFW_PRESS) {
			__mouse[2] |= 1 << button;
		} else {
			__mouse[2] &= ~(1 << button);
		}
	}
}    
    

JNIEXPORT jint JNICALL Java_Jgles2_util_createWindow
  (JNIEnv *e, jclass c, jint w, jint h, jstring jtitle, jboolean fullscreen) 
{
	if (!glfwInit()) return 0;
	
	const char *title=NULL;
	
	if (jtitle!=NULL) title = (*e)->GetStringUTFChars(e, jtitle, 0);    
    
	glfwWindowHint( GLFW_CLIENT_API, GLFW_OPENGL_ES_API );
	glfwWindowHint( GLFW_CONTEXT_VERSION_MAJOR, 2 );
	glfwWindowHint( GLFW_CONTEXT_VERSION_MINOR, 0 );
	
	if (!fullscreen) {	
		__window = glfwCreateWindow(w, h, title, NULL, NULL);
	} else {
		__window = glfwCreateWindow(w, h, title, glfwGetPrimaryMonitor(), NULL);
	}

    if (jtitle!=NULL) (*e)->ReleaseStringUTFChars(e, jtitle, title);

	if (__window) {    
		glfwMakeContextCurrent(__window);
		glfwSetFramebufferSizeCallback(__window, window_size_callback); 	
		glfwSetMouseButtonCallback(__window, mouse_button_callback);
		return 1;
	} else {
		return 0;
	}
}

JNIEXPORT jboolean JNICALL Java_Jgles2_util_shouldClose
  (JNIEnv *e, jclass c)
{
	return glfwWindowShouldClose(__window);
}

JNIEXPORT jdouble JNICALL Java_Jgles2_util_getTime
  (JNIEnv *e, jclass c)
{
	return glfwGetTime();
}

JNIEXPORT void JNICALL Java_Jgles2_util_swapBuffer
  (JNIEnv *e, jclass c)
{
	glfwSwapBuffers(__window);
}

JNIEXPORT void JNICALL Java_Jgles2_util_pollEvents
  (JNIEnv *e, jclass c)
{
	double x,y;
	
	glfwPollEvents();
	glfwGetCursorPos( __window, &x, &y);
	__mouse[0] = (int) x;
	__mouse[1] = (int) y;
	
}

JNIEXPORT void JNICALL Java_Jgles2_util_terminate
  (JNIEnv *e, jclass c)
{
	glfwDestroyWindow(__window);
	glfwTerminate();
}

JNIEXPORT jboolean JNICALL Java_Jgles2_util_keyDown
  (JNIEnv *e, jclass c, jint key)
{
	if (glfwGetKey(__window, key)==GLFW_PRESS) {
		return true;
	} else {
		return false;
	}
}
