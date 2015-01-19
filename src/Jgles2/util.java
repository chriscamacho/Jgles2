
package Jgles2;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.FloatBuffer;

import static Jgles2.GLES2.*;

/** routines to aid use of Jgles2 mainly for native window handling
 * but a couple of simple short cut routines too */

public class util {

    static {
        System.loadLibrary("Jgles2");
    }

	// same as GLFW values but shortened to easy typing 
    public static final int     KEY_SPACE              =	32;
    public static final int     KEY_APOSTROPHE         =	39;  /* ' */
    public static final int     KEY_COMMA              =	44;  /* , */
    public static final int     KEY_MINUS              =	45;  /* - */
    public static final int     KEY_PERIOD             =	46;  /* . */
    public static final int     KEY_SLASH              =	47;  /* / */
    public static final int     KEY_0                  =	48;
    public static final int     KEY_1                  =	49;
    public static final int     KEY_2                  =	50;
    public static final int     KEY_3                  =	51;
    public static final int     KEY_4                  =	52;
    public static final int     KEY_5                  =	53;
    public static final int     KEY_6                  =	54;
    public static final int     KEY_7                  =	55;
    public static final int     KEY_8                  =	56;
    public static final int     KEY_9                  =	57;
    public static final int     KEY_SEMICOLON          =	59;  /* ; */
    public static final int     KEY_EQUAL              =	61;  /* = */
    public static final int     KEY_A                  =	65;
    public static final int     KEY_B                  =	66;
    public static final int     KEY_C                  =	67;
    public static final int     KEY_D                  =	68;
    public static final int     KEY_E                  =	69;
    public static final int     KEY_F                  =	70;
    public static final int     KEY_G                  =	71;
    public static final int     KEY_H                  =	72;
    public static final int     KEY_I                  =	73;
    public static final int     KEY_J                  =	74;
    public static final int     KEY_K                  =	75;
    public static final int     KEY_L                  =	76;
    public static final int     KEY_M                  =	77;
    public static final int     KEY_N                  =	78;
    public static final int     KEY_O                  =	79;
    public static final int     KEY_P                  =	80;
    public static final int     KEY_Q                  =	81;
    public static final int     KEY_R                  =	82;
    public static final int     KEY_S                  =	83;
    public static final int     KEY_T                  =	84;
    public static final int     KEY_U                  =	85;
    public static final int     KEY_V                  =	86;
    public static final int     KEY_W                  =	87;
    public static final int     KEY_X                  =	88;
    public static final int     KEY_Y                  =	89;
    public static final int     KEY_Z                  =	90;
    public static final int     KEY_LEFT_BRACKET       =	91;  /* [ */
    public static final int     KEY_BACKSLASH          =	92;  /* \ */
    public static final int     KEY_RIGHT_BRACKET      =	93;  /* ] */
    public static final int     KEY_GRAVE_ACCENT       =	96;  /* ` */
    public static final int     KEY_WORLD_1            =	161; /* non-US #1 */
    public static final int     KEY_WORLD_2            =	162; /* non-US #2 */

/* Function keys */
    public static final int     KEY_ESCAPE             =	256;
    public static final int     KEY_ENTER              =	257;
    public static final int     KEY_TAB                =	258;
    public static final int     KEY_BACKSPACE          =	259;
    public static final int     KEY_INSERT             =	260;
    public static final int     KEY_DELETE             =	261;
    public static final int     KEY_RIGHT              =	262;
    public static final int     KEY_LEFT               =	263;
    public static final int     KEY_DOWN               =	264;
    public static final int     KEY_UP                 =	265;
    public static final int     KEY_PAGE_UP            =	266;
    public static final int     KEY_PAGE_DOWN          =	267;
    public static final int     KEY_HOME               =	268;
    public static final int     KEY_END                =	269;
    public static final int     KEY_CAPS_LOCK          =	280;
    public static final int     KEY_SCROLL_LOCK        =	281;
    public static final int     KEY_NUM_LOCK           =	282;
    public static final int     KEY_PRINT_SCREEN       =	283;
    public static final int     KEY_PAUSE              =	284;
    public static final int     KEY_F1                 =	290;
    public static final int     KEY_F2                 =	291;
    public static final int     KEY_F3                 =	292;
    public static final int     KEY_F4                 =	293;
    public static final int     KEY_F5                 =	294;
    public static final int     KEY_F6                 =	295;
    public static final int     KEY_F7                 =	296;
    public static final int     KEY_F8                 =	297;
    public static final int     KEY_F9                 =	298;
    public static final int     KEY_F10                =	299;
    public static final int     KEY_F11                =	300;
    public static final int     KEY_F12                =	301;
    public static final int     KEY_F13                =	302;
    public static final int     KEY_F14                =	303;
    public static final int     KEY_F15                =	304;
    public static final int     KEY_F16                =	305;
    public static final int     KEY_F17                =	306;
    public static final int     KEY_F18                =	307;
    public static final int     KEY_F19                =	308;
    public static final int     KEY_F20                =	309;
    public static final int     KEY_F21                =	310;
    public static final int     KEY_F22                =	311;
    public static final int     KEY_F23                =	312;
    public static final int     KEY_F24                =	313;
    public static final int     KEY_F25                =	314;
    public static final int     KEY_KP_0               =	320;
    public static final int     KEY_KP_1               =	321;
    public static final int     KEY_KP_2               =	322;
    public static final int     KEY_KP_3               =	323;
    public static final int     KEY_KP_4               =	324;
    public static final int     KEY_KP_5               =	325;
    public static final int     KEY_KP_6               =	326;
    public static final int     KEY_KP_7               =	327;
    public static final int     KEY_KP_8               =	328;
    public static final int     KEY_KP_9               =	329;
    public static final int     KEY_KP_DECIMAL         =	330;
    public static final int     KEY_KP_DIVIDE          =	331;
    public static final int     KEY_KP_MULTIPLY        =	332;
    public static final int     KEY_KP_SUBTRACT        =	333;
    public static final int     KEY_KP_ADD             =	334;
    public static final int     KEY_KP_ENTER           =	335;
    public static final int     KEY_KP_EQUAL           =	336;
    public static final int     KEY_LEFT_SHIFT         =	340;
    public static final int     KEY_LEFT_CONTROL       =	341;
    public static final int     KEY_LEFT_ALT           =	342;
    public static final int     KEY_LEFT_SUPER         =	343;
    public static final int     KEY_RIGHT_SHIFT        =	344;
    public static final int     KEY_RIGHT_CONTROL      =	345;
    public static final int     KEY_RIGHT_ALT          =	346;
    public static final int     KEY_RIGHT_SUPER        =	347;
    public static final int     KEY_MENU               =	348;
   
    
    /** get a native pointer to a FloatBuffer */ 
    public static native long getFloatBufferPtr(FloatBuffer buffer);
    /** get a native pointer to a IntBuffer */ 
    public static native long getIntBufferPtr(IntBuffer buffer);
    /** get a native pointer to a ByteBuffer */ 
    public static native long getByteBufferPtr(ByteBuffer buffer);
    
    // as seen in Lwjgl (*4) - licence omitted by kind permission Brian Matzon
    /** create a direct byte buffer in native order */
	public static ByteBuffer createByteBuffer(int size) { return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder()); }
    /** create a direct Int buffer backed by a byte buffer in native order */
	public static IntBuffer createIntBuffer(int size) { return createByteBuffer(size << 2).asIntBuffer(); }
    /** create a direct Long buffer backed by a byte buffer in native order */
	public static LongBuffer createLongBuffer(int size) { return createByteBuffer(size << 3).asLongBuffer(); }
    /** create a direct Float buffer backed by a byte buffer in native order */
	public static FloatBuffer createFloatBuffer(int size) { return createByteBuffer(size << 2).asFloatBuffer();	}
    //



    
    /** query this to determine if the screen has been resized */
    public static native boolean resizeRequired();
    
    /** we currently assume only one window TODO handle per window */
    public static native int getWidth();

    /** we currently assume only one window TODO handle per window */
    public static native int getHeight();
    
    /** returns the state of the mouse buttons */
    public static native int getMouseButtons();
    public static native int getMouseX();
    public static native int getMouseY();

	/** initialise the native context support */
	public static native int createWindow(int w, int h, String title, boolean fullscreen);
	
	/** has the window been closed */
	public static native boolean shouldClose();
	
	/** time since native context started */
	public static native double getTime();
	
	/** swap display buffers */
	public static native void swapBuffer();
	
	/** update native input system */
	public static native void pollEvents();
	
	/** is a key currently down */
	public static native boolean keyDown(int key);
	
	/** this does not interfere with keyDown results and is optional */
	public static native void setKeyCallback(Object instance, String method);
	
	/** close down all the native context support */
	public static native void terminate();

	

	/** simply returns a handle to a shader program
	 * @param name a simple identifier to enable easy recognition of error messages
	 * @param vertShaderText the source code for the vertex shader
	 * @param fragShaderText the source code for the fragment shader */
    public static int createShaderProgram(String name, String vertShaderText, String fragShaderText) {
        int fragShader, vertShader, program=0;
        int stat;

        final IntBuffer val = ByteBuffer.allocateDirect(4).asIntBuffer(); 
               
        fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, fragShaderText);
        glCompileShader(fragShader);
        glGetShaderiv(fragShader, GL_COMPILE_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Error: "+name+" fragment shader did not compile!\n");
            System.out.println("Shader log:\n"+glGetShaderInfoLog(fragShader));
            System.exit(-1);
        }
        
        vertShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertShader, vertShaderText);
        glCompileShader(vertShader);
        glGetShaderiv(vertShader, GL_COMPILE_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Error: "+name+" vertex shader did not compile!\n");
            System.out.println("Shader log:\n"+glGetShaderInfoLog(vertShader));
            System.exit(-1);
        }       

        program = glCreateProgram();
        glAttachShader(program, fragShader);
        glAttachShader(program, vertShader);
        glLinkProgram(program);
        
        glGetProgramiv(program, GL_LINK_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Shader log:\n"+glGetProgramInfoLog(program));
            System.exit(-1);
        }

        return program;
    }

	/** calls glGetError and returns a description of the error code
	 * @return the error description string */
    public String checkError() {
        int err = glGetError();
        String es="" ;
        if (err!=GL_NO_ERROR) {
            es = "unknown error code "+err;
            if (err==GL_INVALID_ENUM) es = "Invalid enum\n";
            if (err==GL_INVALID_VALUE) es = "invalid value\n";
            if (err==GL_INVALID_OPERATION) es = "invalid operation\n";
            if (err==GL_OUT_OF_MEMORY) es = "out of memory\n";
        }
        return es;
    }
    
       
    util() {
        System.out.println("NB do not instance util singleton class!");
    }
}
