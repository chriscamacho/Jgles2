import Jgles2.util;
import Jgles2.EGL;
import Jgles2.GLES2;
import Jgles2.BufferUtils;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.ByteBuffer;

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
        
        // val is a single integer intBuffer which is reused for various single int return values
        IntBuffer val = BufferUtils.createIntBuffer(1);
        
        int config_size=1; // wouldn't normally check that many - just for testing!
        LongBuffer configsBuffer = BufferUtils.createLongBuffer(config_size);
        
        if (!EGL.eglChooseConfig(egl_display, attribsBuffer,
                                    configsBuffer, config_size, val)) {
            System.out.println("failed to get an EGL config");
            System.exit(-1);            
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
        
        if (!EGL.eglMakeCurrent(egl_display, egl_surface, egl_surface, egl_context)) {
            System.out.println("eglMakeCurrent failed");
            System.out.println("error code " + Integer.toHexString(EGL.eglGetError()));
            System.exit(-1);
        }
        

        
        String fragShaderText =
            "uniform sampler2D u_texture;\n"+
            "varying vec4 v_color;\n"+
            "varying vec2 v_frag_uv; \n"+
            "void main() {\n"+
            "	vec4 baseColour = texture2D(u_texture,v_frag_uv);\n"+
            "   gl_FragColor = v_color * baseColour;\n"+
            //"   gl_FragColor = baseColour;\n"+
            "}\n";

        String vertShaderText =
            "uniform mat4 modelviewProjection;\n"+
            "attribute vec4 pos;\n"+
            "attribute vec2 uv_attrib;\n"+
            "attribute vec4 color;\n"+
            "varying vec4 v_color;\n"+
            "varying vec2 v_frag_uv;\n"+
            "void main() {\n"+
            "   gl_Position = modelviewProjection * pos;\n"+
            "   v_color = color;\n"+
            "	v_frag_uv = uv_attrib;\n"+
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

        int attr_pos=0,attr_color=1,attr_uv=2,u_matrix,u_texture;
        
        GLES2.glBindAttribLocation(program, attr_pos, "pos");
        GLES2.glBindAttribLocation(program, attr_color, "color");
        GLES2.glBindAttribLocation(program, attr_uv, "uv_attrib");
        GLES2.glLinkProgram(program);  /* needed to put attribs into effect */

        u_matrix = GLES2.glGetUniformLocation(program, "modelviewProjection");
        u_texture = GLES2.glGetUniformLocation(program, "u_texture");
        
        GLES2.glViewport(0, 0, winWidth, winHeight);

        float verts[] = {
            -1, -1,
             1, -1,
             0,  1
        };
        
        float uvs[] = {
            0.0f,0.0f,
            0.0f,2.0f,
            2.0f,2.0f
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
        FloatBuffer uvBuffer = BufferUtils.createFloatBuffer(uvs.length);
        uvBuffer.put(uvs);
        
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


        
        int texData[] = {
0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0x00,0x00, 0x00,0x00,0xff, 0x00,0x00,0xff, 0x00,0x00,0xff, 0x00,0x00,0xff, 0xff,0x00,0x00, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0x00,0x00, 0x00,0x00,0xff, 0x00,0xff,0x00, 0x00,0xff,0x00, 0x00,0x00,0xff, 0xff,0x00,0x00, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0x00,0x00, 0x00,0x00,0xff, 0x00,0xff,0x00, 0x00,0xff,0x00, 0x00,0x00,0xff, 0xff,0x00,0x00, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0x00,0x00, 0x00,0x00,0xff, 0x00,0x00,0xff, 0x00,0x00,0xff, 0x00,0x00,0xff, 0xff,0x00,0x00, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0x00,0x00, 0xff,0xff,0xff, 
0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 0xff,0xff,0xff, 
        };
        
        ByteBuffer textureBuffer = BufferUtils.createByteBuffer(texData.length);
        for (int d:texData) textureBuffer.put((byte)d);

        GLES2.glActiveTexture(GLES2.GL_TEXTURE0);

        int texture;
        GLES2.glGenTextures(1, val);
        texture=val.get(0);
        GLES2.glBindTexture(GLES2.GL_TEXTURE_2D, texture);
        GLES2.glTexParameteri(GLES2.GL_TEXTURE_2D, GLES2.GL_TEXTURE_MIN_FILTER, GLES2.GL_LINEAR);
        GLES2.glTexParameteri(GLES2.GL_TEXTURE_2D, GLES2.GL_TEXTURE_MAG_FILTER, GLES2.GL_LINEAR);
        GLES2.glTexImage2D(GLES2.GL_TEXTURE_2D, 0, GLES2.GL_RGB, 8, 8, 0, GLES2.GL_RGB,
                     GLES2.GL_UNSIGNED_BYTE, textureBuffer);
                     
        // set up for RTT
            ByteBuffer rttBB = BufferUtils.createByteBuffer(128*128*3);
            GLES2.glGenFramebuffers(1, val);    int rttframebuff = val.get(0);
            GLES2.glGenTextures(1, val);        int rtt = val.get(0);
            GLES2.glBindTexture(GLES2.GL_TEXTURE_2D, rtt);
            GLES2.glTexParameteri(GLES2.GL_TEXTURE_2D, GLES2.GL_TEXTURE_MIN_FILTER, GLES2.GL_LINEAR);
            GLES2.glTexParameteri(GLES2.GL_TEXTURE_2D, GLES2.GL_TEXTURE_MAG_FILTER, GLES2.GL_LINEAR);
            GLES2.glTexImage2D(GLES2.GL_TEXTURE_2D, 0, GLES2.GL_RGB, 128, 128, 0, GLES2.GL_RGB,
                    GLES2.GL_UNSIGNED_BYTE, rttBB);
             
            GLES2.glGenRenderbuffers(1, val);    int rttrendbuff = val.get(0);
            GLES2.glBindRenderbuffer(GLES2.GL_RENDERBUFFER, rttrendbuff);
            GLES2.glBindFramebuffer(GLES2.GL_FRAMEBUFFER, rttframebuff);
            GLES2.glFramebufferTexture2D(GLES2.GL_FRAMEBUFFER,GLES2.GL_COLOR_ATTACHMENT0,GLES2.GL_TEXTURE_2D,rtt,0);            

            GLES2.glRenderbufferStorage(GLES2.GL_RENDERBUFFER, GLES2.GL_DEPTH_COMPONENT16, 64, 64);


        
        
        float frame=0;
        while( util.keyDown(9) ) {
            frame++;


                // render to texture 
                GLES2.glClearColor(0.4f, 0.8f, 0.2f, 0.0f);
                GLES2.glBindRenderbuffer(GLES2.GL_RENDERBUFFER, rttrendbuff);
                GLES2.glBindFramebuffer(GLES2.GL_FRAMEBUFFER, rttframebuff);
                GLES2.glBindTexture(GLES2.GL_TEXTURE_2D, texture);             
                GLES2.glViewport(0, 0, 128,128);
                util.kmMat4Identity(mvp);
                util.kmMat4RotationPitchYawRoll(mvp,0,0,frame/30f);
                GLES2.glUniformMatrix4fv(u_matrix, 1, GLES2.GL_FALSE, mvp);
                GLES2.glClear(GLES2.GL_COLOR_BUFFER_BIT|GLES2.GL_DEPTH_BUFFER_BIT);

                GLES2.glVertexAttribPointer(attr_pos, 2, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, vertsBuffer);
                GLES2.glVertexAttribPointer(attr_color, 3, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, coloursBuffer);
                GLES2.glVertexAttribPointer(attr_uv, 2, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, uvBuffer);
                GLES2.glEnableVertexAttribArray(attr_pos);
                GLES2.glEnableVertexAttribArray(attr_color);
                GLES2.glEnableVertexAttribArray(attr_uv);

                GLES2.glDrawArrays(GLES2.GL_TRIANGLES, 0, 3);

                GLES2.glDisableVertexAttribArray(attr_pos);
                GLES2.glDisableVertexAttribArray(attr_color);
                GLES2.glDisableVertexAttribArray(attr_uv);

            // render to the backbuffer
            GLES2.glBindTexture(GLES2.GL_TEXTURE_2D, rtt);
            GLES2.glBindFramebuffer(GLES2.GL_FRAMEBUFFER, 0);
            GLES2.glBindRenderbuffer(GLES2.GL_FRAMEBUFFER, 0);
            GLES2.glViewport(0, 0, util.getWidth(), util.getHeight());
            
            util.kmMat4Identity(model);

            util.kmMat4Translation(model,0,0,-4+((float)Math.sin(frame/30f)*2f));                       
            util.kmMat4RotationPitchYawRoll(model,  frame/30f,
                                                    frame/40f,
                                                    frame/50f);
            util.kmMat4Multiply(mvp,vp,model);
            GLES2.glUniformMatrix4fv(u_matrix, 1, GLES2.GL_FALSE, mvp);

            GLES2.glClearColor(0.8f, 0.4f, 0.2f, 0.0f);
            GLES2.glClear(GLES2.GL_COLOR_BUFFER_BIT | GLES2.GL_DEPTH_BUFFER_BIT);
            
            GLES2.glVertexAttribPointer(attr_pos, 2, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, vertsBuffer);
            GLES2.glVertexAttribPointer(attr_color, 3, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, coloursBuffer);
            GLES2.glVertexAttribPointer(attr_uv, 2, GLES2.GL_FLOAT, GLES2.GL_FALSE, 0, uvBuffer);
            GLES2.glEnableVertexAttribArray(attr_pos);
            GLES2.glEnableVertexAttribArray(attr_color);
            GLES2.glEnableVertexAttribArray(attr_uv);

            GLES2.glDrawArrays(GLES2.GL_TRIANGLES, 0, 3);

            GLES2.glDisableVertexAttribArray(attr_pos);
            GLES2.glDisableVertexAttribArray(attr_color);
            GLES2.glDisableVertexAttribArray(attr_uv);
            
            // flip
            EGL.eglSwapBuffers(egl_display, egl_surface);    

            try {
                Thread.sleep(20);
            } catch (Exception e) {
                // nada
            }
            
            util.pumpEvents(native_display,native_win);
            
            if (util.resizeRequired()) {
                int w=util.getWidth();
                int h=util.getHeight();
                GLES2.glViewport(0, 0, w, h);
                util.kmMat4PerspectiveProjection(projection, 45f,
                                        (float)w / h, 0.1f, 10); 
                util.kmMat4Identity(vp);
                util.kmMat4Multiply(vp,view,projection);                
            }
        }
        
        EGL.eglDestroySurface( egl_display, egl_surface);
        EGL.eglDestroyContext( egl_display, egl_context);
        util.closeWindow(native_display,native_win);

    }

    public static void main(String[] args) {
        test t = new test();
        t.run();
    }

}
