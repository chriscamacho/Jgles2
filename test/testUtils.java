import java.nio.ByteBuffer;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.IntBuffer;

import static Jgles2.GLES2.*;

public class testUtils {

    testUtils() {    }

    public static int loadTexture(String fileName, boolean withAlpha) {
        int texture=0;
        try {
            FileInputStream in = new FileInputStream(fileName);
            PNGDecoder decoder = new PNGDecoder(in);

            int sz=3;
            int gf=GL_RGB;
            PNGDecoder.Format pf=PNGDecoder.RGB;
            if (withAlpha) {
                sz=4;
                gf=GL_RGBA;
                pf=PNGDecoder.RGBA;
            }
            ByteBuffer buf = ByteBuffer.allocateDirect(sz*decoder.getWidth()*decoder.getHeight());
            decoder.decode(buf, decoder.getWidth()*sz, pf);
            buf.flip();

            IntBuffer val = ByteBuffer.allocateDirect(4).asIntBuffer();
            glGenTextures(1,val);
            texture=val.get(0);
            
            glBindTexture(GL_TEXTURE_2D, texture);
            glTexImage2D(GL_TEXTURE_2D, 0, gf, decoder.getWidth(), decoder.getHeight(), 0,
                    gf, GL_UNSIGNED_BYTE, buf);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();   
        }
        return texture;
    }



    public static int createShaderProgram(String name, String vertShaderText, String fragShaderText) {
        int fragShader, vertShader, program=0;
        int stat;
         // val is a byte buffer which is reused for various (single) int return values
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

        /*
         * link the shaders into a shader "program"
         */
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
            //if (err==GL_STACK_OVERFLOW) System.out.println("stack overflow\n");
            //if (err==GL_STACK_UNDERFLOW) System.out.println("stack underflow\n");
            if (err==GL_OUT_OF_MEMORY) System.out.println("out of memory\n");
            System.exit(-1);
        }
    }
}
