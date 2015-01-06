
package Jgles2;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.FloatBuffer;

import static Jgles2.GLES2.*;

public class util {

    static {
        System.loadLibrary("Jgles2");
    }

    // other platforms will need to translate their key values
    // into these values
    
    public static final int     KEY_ESC			=9;
    public static final int     KEY_ONE			=10;
    public static final int     KEY_TWO			=11;
    public static final int     KEY_THREE		=12;
    public static final int     KEY_FOUR		=13;
    public static final int     KEY_FIVE		=14;
    public static final int     KEY_SIX			=15;
    public static final int     KEY_SEVEN		=16;
    public static final int     KEY_EIGHT		=17;
    public static final int     KEY_NINE		=18;
    public static final int     KEY_ZERO		=19;
    public static final int     KEY_MINUS		=20;

    public static final int     KEY_TAB 		=23;
    public static final int     KEY_Q			=24;
    public static final int     KEY_W			=25;
    public static final int     KEY_E			=26;
    public static final int     KEY_R			=27;
    public static final int     KEY_T			=28;
    public static final int     KEY_Y			=29;
    public static final int     KEY_U			=30;
    public static final int     KEY_I			=31;
    public static final int     KEY_O			=32;
    public static final int     KEY_P			=33;
    public static final int     KEY_LBRACKET	=34;
    public static final int     KEY_RBRACKET	=35;
    public static final int     KEY_RETURN		=36;
    public static final int     KEY_LCTRL		=37;
    public static final int     KEY_A			=38;
    public static final int     KEY_S			=39;
    public static final int     KEY_D			=40;
    public static final int     KEY_F			=41;
    public static final int     KEY_G			=42;
    public static final int     KEY_H			=43;
    public static final int     KEY_J			=44;
    public static final int     KEY_K			=45;
    public static final int     KEY_L			=46;
    public static final int     KEY_SEMICOLON	=47;
    public static final int     KEY_APOST		=48;
    public static final int     KEY_BACKTICK	=49;
    public static final int     KEY_LSHIFT		=50;
    public static final int     KEY_HASH		=51;
    public static final int     KEY_Z			=52;
    public static final int     KEY_X			=53;
    public static final int     KEY_C			=54;
    public static final int     KEY_V			=55;
    public static final int     KEY_B			=56;
    public static final int     KEY_N			=57;
    public static final int     KEY_M			=58;
    public static final int     KEY_COMMA		=59;
    public static final int     KEY_PERIOD		=60;
    public static final int     KEY_BSLASH		=61;
    public static final int     KEY_RSHIFT		=62;
    public static final int     KEY_NUMMULT		=63;
    public static final int     KEY_LALT		=64;
    public static final int     KEY_SPACE		=65;
    public static final int     KEY_CAPS		=66;
    public static final int     KEY_F1			=67;
    public static final int     KEY_F2			=68;
    public static final int     KEY_F3			=69;
    public static final int     KEY_F4			=70;
    public static final int     KEY_F5			=71;
    public static final int     KEY_F6			=72;
    public static final int     KEY_F7			=73;
    public static final int     KEY_F8			=74;
    public static final int     KEY_F9			=75;
    public static final int     KEY_F10			=76;
    public static final int     KEY_NUMLOCK		=77;
    public static final int     KEY_SCLOCK		=78;
    public static final int     KEY_NUM7		=79;
    public static final int     KEY_NUM8		=80;
    public static final int     KEY_NUM9		=81;
    public static final int     KEY_NUMMINUS	=82;
    public static final int     KEY_NUM4		=83;
    public static final int     KEY_NUM5		=84;
    public static final int     KEY_NUM6		=85;
    public static final int     KEY_NUMPLUS		=86;
    public static final int     KEY_NUM1		=87;
    public static final int     KEY_NUM2		=88;
    public static final int     KEY_NUM3		=89;
    public static final int     KEY_NUMZERO		=90;
    public static final int     KEY_NUMPERIOD	=91;

    public static final int     KEY_FSLASH		=94;
    public static final int     KEY_F11			=95;
    public static final int     KEY_F12			=96;

    public static final int     KEY_NUMENTER	=104;
    public static final int     KEY_RCTRL		=105;
    public static final int     KEY_NUMSLASH	=106;
    public static final int     KEY_SYSRQ		=107;
    public static final int     KEY_ALTGR		=108;

    public static final int     KEY_HOME		=110;

    public static final int     KEY_PGUP		=112;

    public static final int     KEY_END			=115;

    public static final int     KEY_PGDOWN		=117;
    public static final int     KEY_INSERT		=118;
    public static final int     KEY_DELETE		=119;

    public static final int     KEY_BREAK		=127;

    public static final int     KEY_LMETA		=133;
    public static final int     KEY_RMETA		=134;
    public static final int     KEY_MENU		=135;

    public static final int     KEY_CURSL		=113;
    public static final int     KEY_CURSR		=114;
    public static final int     KEY_CURSU		=111;
    public static final int     KEY_CURSD		=116;



    public static native void setFullscreen(long native_display,long native_window,boolean full);
    public static native long get_native_display();
    public static native long make_native_window(long native_display,
                            long egl_display, long config, int x, int y, 
                            int width, int height, boolean fullscreen);
    public static native void closeWindow(long disp,long win);
    public static native void pumpEvents(long xDisplay,long win);
    public static native boolean resizeRequired();
    public static native int getWidth();
    public static native int getHeight();
    public static native boolean keyDown(int k);
    public static native int getMouseButtons();
    public static native int getMouseX();
    public static native int getMouseY();
    
    public static native long getFloatBufferPtr(FloatBuffer buffer);
    public static native long getIntBufferPtr(IntBuffer buffer);
    public static native long getByteBufferPtr(ByteBuffer buffer);
    
    // as seen in Lwjgl (*4) - licence omitted by kind permission Brian Matzon
	public static ByteBuffer createByteBuffer(int size) { return ByteBuffer.allocateDirect(size).order(ByteOrder.nativeOrder()); }
	public static IntBuffer createIntBuffer(int size) { return createByteBuffer(size << 2).asIntBuffer(); }
	public static LongBuffer createLongBuffer(int size) { return createByteBuffer(size << 3).asLongBuffer(); }
	public static FloatBuffer createFloatBuffer(int size) { return createByteBuffer(size << 2).asFloatBuffer();	}
    //





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

    public static void checkError(String tag) {
        int err = glGetError();
        if (err!=GL_NO_ERROR) {
            System.out.print("tag "+tag+" error:");
            if (err==GL_INVALID_ENUM) System.out.println("Invalid enum\n");
            if (err==GL_INVALID_VALUE) System.out.println("invalid value\n");
            if (err==GL_INVALID_OPERATION) System.out.println("invalid operation\n");
            if (err==GL_OUT_OF_MEMORY) System.out.println("out of memory\n");
            System.exit(-1);
        }
    }
    
       
    util() {
        System.out.println("NB do not instance me!");
    }
}
