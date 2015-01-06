import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static Jgles2.GLES2.*;
import Jgles2.util;

public class Print {

//	private int texture;
	private float charWidth; // width of a single char on the screen
	
	private float xs=1;
	private float ys=1;


	// this quad is used by the string printer
	// 16 columns of 6 rows
    public static float quadVerts[] = {
        // vert                     // UV
          0.0f,   0.0f,  0.0f,		0f,			0f,
         16.0f,   0.0f,  0.0f,      0.0625f,	0f,
         16.0f,  16.0f,  0.0f,      0.0625f,	0.1640625f,
         16.0f,  16.0f,  0.0f,      0.0625f,	0.1640625f,
          0.0f,  16.0f,  0.0f,      0f,			0.1640625f,
          0.0f,   0.0f,  0.0f,      0f,			0f
	};
	
	private static int program;
	
	// shader attribs and uniforms
	private static int attr_pos=1;
	private static int attr_uv=3;
	private static int u_matrix,u_texture,u_offset,u_uvoffset;
	
	private static FloatBuffer vertsBuffer;
	private static int vbo,vao;
	
	
	private static String fragShaderText =
		"precision mediump float;\n"+
		"uniform sampler2D u_texture;\n"+
		"uniform vec2 u_uvoffset; \n"+
		"varying vec2 v_frag_uv; \n"+
		"void main() {\n"+
		"	vec2 uv =  v_frag_uv;\n"+
		"	uv.x = uv.x + u_uvoffset.x;\n"+
		"	uv.y = uv.y + u_uvoffset.y;\n"+
		"   gl_FragColor = texture2D(u_texture, uv);\n"+
		"}\n";

	private static String vertShaderText =
		"uniform mat4 u_matrix;\n"+
		"uniform vec2 u_offset;\n"+
		"attribute vec4 a_pos;\n"+
		"attribute vec2 a_uv;\n"+
		"varying vec2 v_frag_uv;\n"+
		"\n"+
		"void main() {\n"+
		"   vec4 p=a_pos;\n"+
		"   p.x=p.x+u_offset.x;\n"+
		"   p.y=p.y+u_offset.y;\n"+
		"   gl_Position = u_matrix * p ;\n"+
		"	v_frag_uv = a_uv;\n"+
		"}\n";
	
	public static void init() {
		program = util.createShaderProgram("font",vertShaderText,fragShaderText);
        glUseProgram(program);
           

        IntBuffer val = ByteBuffer.allocateDirect(4).asIntBuffer();
        
        glBindAttribLocation(program, attr_pos,     "a_pos");
        glBindAttribLocation(program, attr_uv,      "a_uv");

        glLinkProgram(program);  /* needed to put attribs into effect */
        glGetProgramiv(program, GL_LINK_STATUS, val);
        
        if (val.get(0)==0) {
            System.out.println("(font) shader log:\n"+glGetProgramInfoLog(program));
            System.exit(-1);
        }

        u_matrix = glGetUniformLocation(program, 	"u_matrix");
        u_texture = glGetUniformLocation(program, 	"u_texture");		
        u_offset = glGetUniformLocation(program, 	"u_offset");		
        u_uvoffset = glGetUniformLocation(program,	"u_uvoffset");		
        
        
        vertsBuffer = ByteBuffer.allocateDirect(quadVerts.length * 4).order(ByteOrder.nativeOrder())
                                    .asFloatBuffer().put(quadVerts);
        vertsBuffer.flip();

		glGenBuffers(1,val);
        vbo = val.get(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, (long)vertsBuffer.capacity()*4 ,util.getFloatBufferPtr(vertsBuffer) , GL_STATIC_DRAW);

		
	}
	
	Print(float cWidth, float charHeight) {

		charWidth = cWidth;
		xs=charWidth/16f;
		ys=charHeight/16f;
		
	}
	
	private static Mat4 tmpMat = new Mat4();
	
	public void preRender(float screenWidth, float screenHeight) {
		
		tmpMat.identity(); // screw with matrix to make it Cartesian 40x30 for 16x16char
		tmpMat.mat.put( 0, ((2f/screenWidth)/(640/screenWidth))*(xs));
		tmpMat.mat.put( 5,((-2f/screenHeight)/(480/screenHeight))*(ys));
		tmpMat.mat.put(12,-1f);
		tmpMat.mat.put(13,1f);
		
		glUseProgram(program);
		
		glUniformMatrix4fv(u_matrix, 1, false , tmpMat.mat);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);

		glEnableVertexAttribArray(attr_pos);
		glEnableVertexAttribArray(attr_uv);

		// attrib, number items, type, normalised, byte stride, byte offset
		glVertexAttribPointer( attr_pos, 3, GL_FLOAT, false, 20, 0 );
		glVertexAttribPointer( attr_uv, 2, GL_FLOAT, false, 20, 12 );

	}
	
	void render(float x, float y, String message) {
		
		int l = message.length();
		int c;
		float u,v;
		for (int n=0;n<l;n++) {
			c = message.charAt(n)-32;
			u = ((float)(c % 16)) * 0.0625f;
			v = (c/16) *0.1640625f;
			glUniform2f(u_offset, (x+(n*charWidth))/xs, y/ys);
			glUniform2f(u_uvoffset, u, v ); 
			glDrawArrays(GL_TRIANGLES, 0, 6);
		}
	}
	
	public static void postRender() {
		glDisableVertexAttribArray(attr_pos);
		glDisableVertexAttribArray(attr_uv);		
	}
	
}
