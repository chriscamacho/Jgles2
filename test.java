import Jgles2.util;
import Jgles2.EGL;
import static Jgles2.GLES2.*;
import Jgles2.kazmath;

import java.nio.IntBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class test {
    
    test() {
            
    }
    
    // initial window size
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
        // buffers for EGL attributs 
        IntBuffer attribsBuffer = util.createIntBuffer(attribs.length);
        attribsBuffer.put(attribs);
        
        IntBuffer ctx_attribsBuffer = util.createIntBuffer(ctx_attribs.length);
        ctx_attribsBuffer.put(ctx_attribs);
        
        long native_display = util.get_native_display();
        long egl_display = EGL.eglGetDisplay( native_display );
        
        if (!EGL.eglInitialize(egl_display)) {
            System.out.println("EGL failed to initialise");
            System.exit(-1);
        }

        // val is a integer intBuffer which is reused for various int return values
        IntBuffer val = util.createIntBuffer(2);
        
        int config_size=1;
        LongBuffer configsBuffer = util.createLongBuffer(config_size);
        
        if (!EGL.eglChooseConfig(egl_display, attribsBuffer,
                                    configsBuffer, config_size, val)) {
            System.out.println("failed to get an EGL config");
            System.exit(-1);            
        }

        long config=configsBuffer.get(0); 
        // last 5 parameters of make_native_window are
        // coordinates of top left corner of window, window width and height
        // and finally a flag for fullscreen
        long native_win = util.make_native_window(native_display, egl_display, config,
                    0, 0, winWidth, winHeight, false);
                    
        long egl_context = EGL.eglCreateContext(egl_display, config, EGL.EGL_NO_CONTEXT, ctx_attribsBuffer );
        if (egl_context==0) {
            System.out.println("failed to get an EGL context");
            System.exit(-1);
        }
        
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
        

        // the shader is shared by both renderings
        // combines vertex colour with a texture
        String fragShaderText =
			"precision mediump float;\n"+
            "uniform sampler2D u_texture;\n"+
            "varying vec4 v_color;\n"+
            "varying vec2 v_frag_uv; \n"+
            "void main() {\n"+
            "	vec4 baseColour = texture2D(u_texture,v_frag_uv);\n"+
//            "   gl_FragColor = v_color/2f + baseColour/2f;\n"+
            "   gl_FragColor = v_color * baseColour;\n"+
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
        
        fragShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragShader, fragShaderText);
        glCompileShader(fragShader);
        glGetShaderiv(fragShader, GL_COMPILE_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Error: fragment shader did not compile!\n");
            System.out.println("Shader log:\n"+glGetShaderInfoLog(fragShader));
            System.exit(-1);
        }
        
        vertShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertShader, vertShaderText);
        glCompileShader(vertShader);
        glGetShaderiv(vertShader, GL_COMPILE_STATUS, val);
        if (val.get(0)==0) {
            System.out.println("Error: vertex shader did not compile!\n");
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

        glUseProgram(program);

        int attr_pos=0,attr_color=1,attr_uv=2,u_matrix,u_texture;
        
        glBindAttribLocation(program, attr_pos, "pos");
        glBindAttribLocation(program, attr_color, "color");
        glBindAttribLocation(program, attr_uv, "uv_attrib");
        glLinkProgram(program);  /* needed to put attribs into effect */

        u_matrix = glGetUniformLocation(program, "modelviewProjection");
        u_texture = glGetUniformLocation(program, "u_texture");
        

        // its better to load these values directly from a file into
        // a buffer, so theres no duplicates
        // dont use buffer.wrap() as according to jvm spec arrays need
        // not be contigious :o
        float verts[] = {
            -1, -1,
             1, -1,
             1,  1,
             
            -1, -1,
             1,  1,
            -1,  1
        };
        
        float uvs[] = {
            0.0f,0.0f,
            2.0f,0.0f,
            2.0f,2.0f,
            
            0.0f,0.0f,
            2.0f,2.0f,
            0.0f,2.0f
        };
        
        float colours[] = {
            1.0f, 0.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            
            1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 1.0f
        };

        float coloursT[] = {
            1, 1, 1,
            1, 1, 1,
            1, 1, 1,
            
            1,1,1,
            1,1,1,
            1,1,1 
        };

        FloatBuffer vertsBuffer = util.createFloatBuffer(verts.length);
        vertsBuffer.put(verts);
        FloatBuffer coloursBuffer = util.createFloatBuffer(colours.length);
        coloursBuffer.put(colours);
        FloatBuffer coloursTBuffer = util.createFloatBuffer(coloursT.length);
        coloursTBuffer.put(coloursT);
        FloatBuffer uvBuffer = util.createFloatBuffer(uvs.length);
        uvBuffer.put(uvs);
        
        // kmMat4 struct is 16 floats (love the KISS principle)
        FloatBuffer view = util.createFloatBuffer(16);
        kazmath.kmMat4Identity(view);

        FloatBuffer eye = util.createFloatBuffer(3);
        FloatBuffer centre = util.createFloatBuffer(3);
        FloatBuffer up = util.createFloatBuffer(3);
        
        kazmath.kmVec3Fill(eye,    0, 0, 0);
        kazmath.kmVec3Fill(centre, 0, 0,-5);
        kazmath.kmVec3Fill(up,     0, 1, 0);

        kazmath.kmMat4LookAt(view, eye, centre, up);

        FloatBuffer projection = util.createFloatBuffer(16);
        kazmath.kmMat4Identity(projection);
        
        kazmath.kmMat4PerspectiveProjection(projection, 45f,
                                (float)winWidth / winWidth, 0.1f, 10);

        // combine view and projection matrix as we dont need to
        // calculate them each frame unless something changes
        FloatBuffer vp = util.createFloatBuffer(16);
        kazmath.kmMat4Identity(vp);
        kazmath.kmMat4Multiply(vp,view,projection);  
        
        // model matrix is the position/orientation for an individual
        // model, the mvp matrix is the final matrix used by the shader
        FloatBuffer model = util.createFloatBuffer(16);
        FloatBuffer mvp = util.createFloatBuffer(16);



        glActiveTexture(GL_TEXTURE0);
        
        // TODO this section should turn into a routine in util class
        // that takes a png file name and returns a GLES texture name...
                BufferedImage image=null;
                try {
                    image = ImageIO.read(new File("data/parrot.png"));
                } catch(Exception e) {
                    System.out.println(e);
                }

                int[] pixels = new int[image.getWidth() * image.getHeight()];
                image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

                ByteBuffer textureBuffer = util.createByteBuffer(image.getWidth()*image.getHeight()*4);
                int i=0;
                for(int y = 0; y < image.getHeight(); y++){
                    for(int x = 0; x < image.getWidth(); x++){
                        int pixel = pixels[i++];
                        textureBuffer.put((byte) ((pixel >> 16) & 0xFF)); 
                        textureBuffer.put((byte) ((pixel >> 8) & 0xFF)); 
                        textureBuffer.put((byte) (pixel & 0xFF));     
                        textureBuffer.put((byte) ((pixel >> 24) & 0xFF));
                    }
                }

                textureBuffer.flip(); 

                // this texture is the image on the triangle thats rendered onto
                // the texture thats used to render the triangle on the screen
                int texture;
                glGenTextures(1, val);
                texture=val.get(0);
                glBindTexture(GL_TEXTURE_2D, texture);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
                glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, image.getWidth(), image.getHeight(), 0, GL_RGBA,
                             GL_UNSIGNED_BYTE, textureBuffer);
                image=null;
                textureBuffer=null;
                pixels=null;
        
        
        
        
                     
        // set up for RTT
        ByteBuffer rttBB = util.createByteBuffer(128*128*3);
        glGenFramebuffers(1, val);    int rttframebuff = val.get(0);
        glGenTextures(1, val);        int rtt = val.get(0);
        glBindTexture(GL_TEXTURE_2D, rtt);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, 128, 128, 0, GL_RGB,
                GL_UNSIGNED_BYTE, rttBB);
         
        glGenRenderbuffers(1, val);    int rttrendbuff = val.get(0);
        glBindRenderbuffer(GL_RENDERBUFFER, rttrendbuff);
        glBindFramebuffer(GL_FRAMEBUFFER, rttframebuff);
        glFramebufferTexture2D(GL_FRAMEBUFFER,GL_COLOR_ATTACHMENT0,GL_TEXTURE_2D,rtt,0);            

        glRenderbufferStorage(GL_RENDERBUFFER, GL_DEPTH_COMPONENT16, 64, 64);

        EGL.eglSwapInterval(egl_display,1);
 
 
        float frame=0;
        boolean lastf=false;
        boolean fs=false;
        while( !util.keyDown(util.KEY_ESC) ) {
            frame++;

            // render to texture 
            glClearColor(0f, 0f, 0f, 0.0f);
            glBindRenderbuffer(GL_RENDERBUFFER, rttrendbuff);
            glBindFramebuffer(GL_FRAMEBUFFER, rttframebuff);
            glBindTexture(GL_TEXTURE_2D, texture);             
            glViewport(0, 0, 128,128);
            kazmath.kmMat4Identity(mvp);
            kazmath.kmMat4RotationPitchYawRoll(mvp,0,0,frame/30f);
            glUniformMatrix4fv(u_matrix, 1, GL_FALSE, mvp);
            glClear(GL_COLOR_BUFFER_BIT|GL_DEPTH_BUFFER_BIT);

            glVertexAttribPointer(attr_pos, 2, GL_FLOAT, GL_FALSE, 0, vertsBuffer);
            glVertexAttribPointer(attr_color, 3, GL_FLOAT, GL_FALSE, 0, coloursTBuffer);
            glVertexAttribPointer(attr_uv, 2, GL_FLOAT, GL_FALSE, 0, uvBuffer);
            glEnableVertexAttribArray(attr_pos);
            glEnableVertexAttribArray(attr_color);
            glEnableVertexAttribArray(attr_uv);

            glDrawArrays(GL_TRIANGLES, 0, 6);

            glDisableVertexAttribArray(attr_pos);
            glDisableVertexAttribArray(attr_color);
            glDisableVertexAttribArray(attr_uv);

            // render to the backbuffer
            glBindTexture(GL_TEXTURE_2D, rtt);
            
            glBindFramebuffer(GL_FRAMEBUFFER, 0);
            glBindRenderbuffer(GL_FRAMEBUFFER, 0);
            glViewport(0, 0, util.getWidth(), util.getHeight());
            
            kazmath.kmMat4Identity(model);
            if (util.getMouseButtons()!=0) {
                kazmath.kmMat4Translation(model,
                        ((float)util.getMouseX()/(float)util.getWidth()-0.5f)*3,
                        -((float)util.getMouseY()/(float)util.getHeight()-0.5f)*3,
                        -4+((float)Math.sin(frame/80f)*2f));  
            } else {
                kazmath.kmMat4Translation(model,0,0,-4+((float)Math.sin(frame/80f)*2f));  
            }
            if (!util.keyDown(util.KEY_SPACE)) {                     
                kazmath.kmMat4RotationPitchYawRoll(model,  frame/100f,
                                                        frame/120f,
                                                        frame/130f);
            }
            kazmath.kmMat4Multiply(mvp,vp,model);
            glUniformMatrix4fv(u_matrix, 1, GL_FALSE, mvp);

            glClearColor(0.8f, 0.4f, 0.2f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            
            glVertexAttribPointer(attr_pos, 2, GL_FLOAT, GL_FALSE, 0, vertsBuffer);
            glVertexAttribPointer(attr_color, 3, GL_FLOAT, GL_FALSE, 0, coloursBuffer);
            glVertexAttribPointer(attr_uv, 2, GL_FLOAT, GL_FALSE, 0, uvBuffer);
            glEnableVertexAttribArray(attr_pos);
            glEnableVertexAttribArray(attr_color);
            glEnableVertexAttribArray(attr_uv);

            glDrawArrays(GL_TRIANGLES, 0, 6);

            glDisableVertexAttribArray(attr_pos);
            glDisableVertexAttribArray(attr_color);
            glDisableVertexAttribArray(attr_uv);
            
            // flip
            EGL.eglSwapBuffers(egl_display, egl_surface);    

            // updates the keyboard and services window events
            util.pumpEvents(native_display,native_win);
            
            if (lastf!=util.keyDown(util.KEY_F)) {
                if (util.keyDown(util.KEY_F)) {
                    fs=!fs;
                    util.setFullscreen(native_display,native_win,fs);
                }
            }
            lastf=util.keyDown(util.KEY_F);
            
            if (util.resizeRequired()) {
                // if the screen is resized then the projection
                // and view/projection matrices need updating
                int w=util.getWidth();
                int h=util.getHeight();
                glViewport(0, 0, w, h);
                kazmath.kmMat4PerspectiveProjection(projection, 45f,
                                        (float)w / h, 0.1f, 10); 
                kazmath.kmMat4Identity(vp);
                kazmath.kmMat4Multiply(vp,view,projection);                
            }
        }


        val.put(0,rttframebuff);        glDeleteFramebuffers(1,val);
        val.put(0,rttrendbuff);         glDeleteRenderbuffers(1,val);
        glDeleteShader(fragShader);
        glDeleteShader(vertShader);
        glDeleteProgram(program);
        val.put(0,texture);val.put(1,rtt);  glDeleteTextures (2,val);
        
        EGL.eglDestroySurface( egl_display, egl_surface);
        EGL.eglDestroyContext( egl_display, egl_context);
        util.closeWindow(native_display,native_win);

    }

    public static void main(String[] args) {
        test t = new test();
        t.run();
    }

}
