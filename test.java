import Jgles2.util;
import Jgles2.EGL;
import Jgles2.GLES2;
import Jgles2.BufferUtils;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;

public class test {
    
    test() {
            
    }
    
    static final int winWidth = 640;
    static final int winHeight = 480;
    
    public void run() {
        
        int attribs[] = {
            EGL.EGL_RED_SIZE, 1, 
            EGL.EGL_GREEN_SIZE, 1,
            EGL.EGL_BLUE_SIZE, 1,
            EGL.EGL_ALPHA_SIZE, 1,
            EGL.EGL_DEPTH_SIZE, 1,
            EGL.EGL_RENDERABLE_TYPE, EGL.EGL_OPENGL_ES2_BIT,
            EGL.EGL_NONE // end of list
        };
        
        int ctx_attribs[] = {
            EGL.EGL_CONTEXT_CLIENT_VERSION, 2,
            EGL.EGL_NONE // end of list
        };
        
        IntBuffer attribsBuffer = BufferUtils.createIntBuffer(attribs.length);
        attribsBuffer.put(attribs);
        
        IntBuffer ctx_attribsBuffer = BufferUtils.createIntBuffer(ctx_attribs.length);
        ctx_attribsBuffer.put(ctx_attribs);
        
        long native_display = util.get_native_display();
        long egl_display = EGL.eglGetDisplay( native_display );
        
        if (!EGL.eglInitialize(egl_display)) {
            System.out.println("EGL failed to initialise");
            System.exit(-1);
        }
        
        String s = EGL.eglQueryString(egl_display, EGL.EGL_VERSION);
        System.out.println("EGL_VERSION = "+s);
        s = EGL.eglQueryString(egl_display, EGL.EGL_VENDOR);
        System.out.println("EGL_VENDOR = "+s);
        s = EGL.eglQueryString(egl_display, EGL.EGL_EXTENSIONS);
        System.out.println("EGL_EXTENSIONS = "+s);
        s = EGL.eglQueryString(egl_display, EGL.EGL_CLIENT_APIS);
        System.out.println("EGL_CLIENT_APIS = "+s);

        // val is a single integer intBuffer which is reused for various single int return values
        IntBuffer val = BufferUtils.createIntBuffer(1);
        
        int config_size=32; // wouldn't normally check that many - just for testing!
        LongBuffer configsBuffer = BufferUtils.createLongBuffer(config_size);
        
        if (!EGL.eglChooseConfig(egl_display, attribsBuffer,
                                    configsBuffer, config_size, val)) {
            System.out.println("failed to get an EGL config");
            System.exit(-1);            
        }
        int num=val.get(0);
        System.out.println("found "+num+ " configs");
        for (int i=0;i<num;i++) {
            long cfg = configsBuffer.get(i);
            EGL.eglGetConfigAttrib(egl_display, cfg , EGL.EGL_RED_SIZE, val);           int r = val.get(0);
            EGL.eglGetConfigAttrib(egl_display, cfg , EGL.EGL_DEPTH_SIZE, val);         int d = val.get(0);
            EGL.eglGetConfigAttrib(egl_display, cfg , EGL.EGL_ALPHA_SIZE, val);         int a = val.get(0);
            EGL.eglGetConfigAttrib(egl_display, cfg , EGL.EGL_CONFIG_ID, val);          int id = val.get(0);
            EGL.eglGetConfigAttrib(egl_display, cfg , EGL.EGL_NATIVE_RENDERABLE, val);  int nr = val.get(0);
            System.out.println("config #"+i+" ID"+id+" RED"+r+" DEPTH"+d+" ALPHA"+a+" NR"+nr);
        }
        
        long config=configsBuffer.get(0); 
        
        long native_win = util.make_native_window(native_display, egl_display, config,
                    0, 0, winWidth, winHeight, false);
                    
        long egl_context = EGL.eglCreateContext(egl_display, config, EGL.EGL_NO_CONTEXT, ctx_attribsBuffer );
        if (egl_context==0) {
            System.out.println("failed to get an EGL context");
            System.exit(-1);
        }
        
        // test query context
        EGL.eglQueryContext(egl_display, egl_context, EGL.EGL_CONTEXT_CLIENT_VERSION, val);
        System.out.println("context client version "+val.get(0));
        
        long egl_surface = EGL.eglCreateWindowSurface(egl_display, config, native_win, null);
        if (egl_surface == 0) {
            System.out.println("failed to create a windowed surface");
            System.exit(-1);
        }
        
        EGL.eglQuerySurface(egl_display, egl_surface, EGL.EGL_WIDTH, val);
        System.out.println("surface width "+val.get(0));
        EGL.eglQuerySurface(egl_display, egl_surface, EGL.EGL_HEIGHT, val);
        System.out.println("surface height "+val.get(0));
        EGL.eglGetConfigAttrib(egl_display, config, EGL.EGL_SURFACE_TYPE, val);
        System.out.println("window bit "+(val.get(0) & EGL.EGL_WINDOW_BIT));        
        
        if (!EGL.eglMakeCurrent(egl_display, egl_surface, egl_surface, egl_context)) {
            System.out.println("eglMakeCurrent failed");
            System.out.println("error code " + Integer.toHexString(EGL.eglGetError()));
            System.exit(-1);
        }
        
        System.out.println("GL_RENDERER   = " + GLES2.glGetString(GLES2.GL_RENDERER));
        System.out.println("GL_VERSION    = " + GLES2.glGetString(GLES2.GL_VERSION));
        System.out.println("GL_VENDOR     = " + GLES2.glGetString(GLES2.GL_VENDOR));
        
        GLES2.glClearColor(0.8f, 0.4f, 0.2f, 0.0f);
        
        String fragShaderText =
            "varying vec4 v_color;\n"+
            "void main() {\n"+
            "   gl_FragColor = v_color;\n"+
            "}\n";

        String vertShaderText =
            "uniform mat4 modelviewProjection;\n"+
            "attribute vec4 pos;\n"+
            "attribute vec4 color;\n"+
            "varying vec4 v_color;\n"+
            "void main() {\n"+
            "   gl_Position = modelviewProjection * pos;\n"+
            "   v_color = color;\n"+
            "}\n";
        
        int fragShader, vertShader, program;
        int stat;
        
        fragShader = GLES2.glCreateShader(GLES2.GL_FRAGMENT_SHADER);
        GLES2.glShaderSource(fragShader, fragShaderText);
        GLES2.glCompileShader(fragShader);
        GLES2.glGetShaderiv(fragShader, GLES2.GL_COMPILE_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Error: fragment shader did not compile!\n");
            System.out.println("Shader log:\n"+GLES2.glGetShaderInfoLog(fragShader));
            System.exit(-1);
        }
        
        vertShader = GLES2.glCreateShader(GLES2.GL_VERTEX_SHADER);
        GLES2.glShaderSource(vertShader, vertShaderText);
        GLES2.glCompileShader(vertShader);
        GLES2.glGetShaderiv(vertShader, GLES2.GL_COMPILE_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Error: vertex shader did not compile!\n");
            System.out.println("Shader log:\n"+GLES2.glGetShaderInfoLog(vertShader));
            System.exit(-1);
        }       
        
        program = GLES2.glCreateProgram();
        GLES2.glAttachShader(program, fragShader);
        GLES2.glAttachShader(program, vertShader);
        GLES2.glLinkProgram(program);
        
        GLES2.glGetProgramiv(program, GLES2.GL_LINK_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Shader log:\n"+GLES2.glGetProgramInfoLog(program));
            System.exit(-1);
        }

        GLES2.glUseProgram(program);

        int attr_pos=0,attr_color=1,u_matrix;
        
        GLES2.glBindAttribLocation(program, attr_pos, "pos");
        GLES2.glBindAttribLocation(program, attr_color, "color");
        GLES2.glLinkProgram(program);  /* needed to put attribs into effect */

        u_matrix = GLES2.glGetUniformLocation(program, "modelviewProjection");
        System.out.println("Uniform modelviewProjection at " + u_matrix);
        System.out.println("Attrib pos at " + attr_pos);
        System.out.println("Attrib color at " + attr_color);

        GLES2.glViewport(0, 0, winWidth, winHeight);

        float verts[] = {
            -1, -1,
             1, -1,
             0,  1
        };
        
        float colours[] = {
            1, 0, 0 ,
            0, 1, 0 ,
            0, 0, 1 
        };

        FloatBuffer vertsBuffer = BufferUtils.createFloatBuffer(verts.length);
        vertsBuffer.put(verts);
        FloatBuffer coloursBuffer = BufferUtils.createFloatBuffer(colours.length);
        coloursBuffer.put(colours);
        
        // kmMat4 struct is 16 floats (love the KISS principle)
        FloatBuffer view = BufferUtils.createFloatBuffer(16);
        util.kmMat4Identity(view);

        FloatBuffer eye = BufferUtils.createFloatBuffer(3);
        FloatBuffer centre = BufferUtils.createFloatBuffer(3);
        FloatBuffer up = BufferUtils.createFloatBuffer(3);
        
        util.kmVec3Fill(eye,    0, 0, 0);
        util.kmVec3Fill(centre, 0, 0,-5);
        util.kmVec3Fill(up,     0, 1, 0);

        util.kmMat4LookAt(view, eye, centre, up);

        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        util.kmMat4Identity(projection);
        
        util.kmMat4PerspectiveProjection(projection, 45f,
                                (float)winWidth / winWidth, 0.1f, 10);

        FloatBuffer vp = BufferUtils.createFloatBuffer(16);
        util.kmMat4Identity(vp);
        util.kmMat4Multiply(vp,view,projection);
        
        FloatBuffer model = BufferUtils.createFloatBuffer(16);
        FloatBuffer mvp = BufferUtils.createFloatBuffer(16);
        util.kmMat4Identity(mvp);
        
        for(float frame=0;frame<240;frame++) {
            
            util.kmMat4Identity(model);

            util.kmMat4Translation(model,0,0,-5+((float)Math.sin(frame/20f)*2f));            
            util.kmMat4RotationPitchYawRoll(model,  frame/10f,
                                                    frame/20f,
                                                    frame/30f);
            util.kmMat4Multiply(mvp,vp,model);

            GLES2.glUniformMatrix4fv(u_matrix, 1, GLES2.GL_FALSE, mvp);

            GLES2.glClear(GLES2.GL_COLOR_BUFFER_BIT | GLES2.GL_DEPTH_BUFFER_BIT);
            
            GLES2.glVertexAttribPointer(attr_pos, 2, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, vertsBuffer);
            GLES2.glVertexAttribPointer(attr_color, 3, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, coloursBuffer);
            GLES2.glEnableVertexAttribArray(attr_pos);
            GLES2.glEnableVertexAttribArray(attr_color);

            GLES2.glDrawArrays(GLES2.GL_TRIANGLES, 0, 3);

            GLES2.glDisableVertexAttribArray(attr_pos);
            GLES2.glDisableVertexAttribArray(attr_color);
                    
            EGL.eglSwapBuffers(egl_display, egl_surface);    

            try {
                Thread.sleep(20);
            } catch (Exception e) {
                // nada
            }
        }

    }

    public static void main(String[] args) {
        test t = new test();
        t.run();
    }

}
