import Jgles2.util;
import Jgles2.EGL;
import static Jgles2.GLES2.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.LongBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

import java.io.FileInputStream;


public final class Main {

    
    // initial window size
    static final int winWidth = 640;
    static final int winHeight = 480;

    private float width,height,aspect;
    private Mat4 perspective = new Mat4();
    private Mat4 view = new Mat4();
    private Mat4 vp = new Mat4();

	Vec3 eye    =   new Vec3(0,2f,0);
	Vec3 centre =   new Vec3(0,0,0);
	Vec3 up     =   new Vec3(0,1f,0);


	private Main() { }

	public static void main(String[] args) {

        Main main = new Main();
		main.run();

	}

	private void run() {
		
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
		
		updateProjection(640,480);


        int texture=0; 

        glActiveTexture(GL_TEXTURE0);

        texture = testUtils.loadTexture("data/wall.png",false);

        /*
         * vertex and fragment shaders
         * (you could load from file if you have a bunch of them!)
         */

        String fragShaderText =
			"precision mediump float;\n"+
            "uniform sampler2D u_texture;\n"+
            "varying vec4 v_colour;\n"+
            "varying vec2 v_frag_uv; \n"+
            "void main() {\n"+
            "	vec4 baseColour = texture2D(u_texture,v_frag_uv);\n"+
            "   gl_FragColor = v_colour * baseColour;\n"+
            "}\n";

        String vertShaderText =
            "uniform mat4 modelviewProjection;\n"+
            "attribute vec4 pos;\n"+
            "attribute vec2 uv_attrib;\n"+
            "attribute vec4 colour;\n"+
            "varying vec4 v_colour;\n"+
            "varying vec2 v_frag_uv;\n"+
            "void main() {\n"+
            "   gl_Position = modelviewProjection * pos ;\n"+
//            "   gl_Position = pos;\n"+
            "   v_colour = colour;\n"+
            "	v_frag_uv = uv_attrib;\n"+
            "}\n";

        
        int program = testUtils.createShaderProgram("main", vertShaderText,fragShaderText);
        glUseProgram(program);

        /*
         * 
         * get access to the shaders attibutes and uniforms
         *   
         */            
        int attr_pos=1,attr_colour=2,attr_uv=3,u_matrix,u_texture;
        
        glBindAttribLocation(program, attr_pos,     "pos");
        glBindAttribLocation(program, attr_colour,  "colour");
        glBindAttribLocation(program, attr_uv,      "uv_attrib");

        glLinkProgram(program);  /* needed to put attribs into effect */
        glGetProgramiv(program, GL_LINK_STATUS, val);
        
        if (val.get(0)==0) {
            System.out.println("Shader log:\n"+glGetProgramInfoLog(program));
            System.exit(-1);
        }

        u_matrix = glGetUniformLocation(program, "modelviewProjection");
        u_texture = glGetUniformLocation(program, "u_texture");


        /*
         * 
         * Normally you'd load your "model" from a binary file...
         * here they are just in arrays
         */    

        /*
         * load the pyramid vert arrays into nio buffer
         */
        FloatBuffer vertsBuffer;
        vertsBuffer = ByteBuffer.allocateDirect(Shapes.pyramidVerts.length * 4).order(ByteOrder.nativeOrder())
                                    .asFloatBuffer().put(Shapes.pyramidVerts);
        vertsBuffer.flip();

        // make GL vertex array object and buffer from the nio floatbuffer
        glGenBuffers(1,val);
        int vbo2 = val.get(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo2);
        glBufferData(GL_ARRAY_BUFFER, (long)vertsBuffer.capacity()*4 ,vertsBuffer , GL_STATIC_DRAW);

        /*
         * load the box vert arrays into nio buffer
         */
        vertsBuffer = ByteBuffer.allocateDirect(Shapes.boxVerts.length * 4).order(ByteOrder.nativeOrder())
                                    .asFloatBuffer().put(Shapes.boxVerts);
        vertsBuffer.flip();

        glGenBuffers(1,val);
        int vbo = val.get(0);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, (long)vertsBuffer.capacity()*4, vertsBuffer , GL_STATIC_DRAW);


        Mat4 mvp = new Mat4();		// Model View Projection
        Mat4 model = new Mat4();	// the objects rotation and position
        Mat4 mvpSave = new Mat4();	// for restoring mvp matrix (when rendering different bits with offsets)
        Mat4 tmpMat = new Mat4(); 	// temporary intermediate values

        /*
         * show the window and set the swap interval
         * as well as the background colour
         */

		glClearColor(0f,0f,0f, 0f);
                
        /*
         * xa,ya,za is used when rotating with keyboard
         * x,y,z is the models position
         */
        float xa=0,ya=0,za=0;
        float x=0,y=0,z=-3;
        long startTime = System.currentTimeMillis();
        double thisTime = (System.currentTimeMillis()-startTime)/1000;//glfwGetTime();
        double lastTime = 0;
        float delta = 0;

        Quat tmpRot = new Quat();
        Quat modelRot = new Quat();

        final float ANG_SPEED = 1.75f;
        final float MOVE_SPEED = .5f;

        glEnable(GL_DEPTH_TEST);
        
        
        int camoTex = testUtils.loadTexture("data/camo.png",true);
        int stripeTex = testUtils.loadTexture("data/font.png",true);
        // printers now share common vertex buffers etc
        Print.init();
        
        Print printer =	new Print(24,32);
        // 16x16 gives 40x30 chars on screen regardless of resolution
        Print smlPrint = new Print(16,16);        
        
        float a=0;
        float ty=300;
        
        boolean lastf=false;	// for fullscreen switching
        boolean fs=false;
        /*
         * The "main loop" for our demo
         */ 
		while(!util.keyDown(util.KEY_ESC)) {

			util.pumpEvents(native_display,native_win);
            
            if (lastf!=util.keyDown(util.KEY_F)) {
                if (util.keyDown(util.KEY_F)) {
                    fs=!fs;
                    util.setFullscreen(native_display,native_win,fs);
                }
            }
            lastf=util.keyDown(util.KEY_F);
            			
			if (util.resizeRequired()) { width=util.getWidth(); height=util.getHeight(); }
                    
            glViewport(0, 0, (int)width, (int)height);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

			xa=0;ya=0;za=0;
			
			a+=delta/2f;
			eye.x = ((float)Math.sin(a))*6f;
			eye.z = ((float)Math.cos(a))*8f;
			updateProjection(width,height);
        
			// angle is an increment value
			// translate is a cumulative value

			if (util.keyDown(util.KEY_Q)) xa =- ANG_SPEED * delta;
			if (util.keyDown(util.KEY_W)) xa =+ ANG_SPEED * delta;
			if (util.keyDown(util.KEY_A)) ya =- ANG_SPEED * delta;
			if (util.keyDown(util.KEY_S)) ya =+ ANG_SPEED * delta;
			if (util.keyDown(util.KEY_Z)) za =- ANG_SPEED * delta;
			if (util.keyDown(util.KEY_X)) za =+ ANG_SPEED * delta;

			if (util.keyDown(util.KEY_R)) x -= MOVE_SPEED * delta;
			if (util.keyDown(util.KEY_T)) x += MOVE_SPEED * delta;
			if (util.keyDown(util.KEY_F)) y -= MOVE_SPEED * delta;
			if (util.keyDown(util.KEY_G)) y += MOVE_SPEED * delta;
			if (util.keyDown(util.KEY_V)) z -= MOVE_SPEED * delta;
			if (util.keyDown(util.KEY_B)) z += MOVE_SPEED * delta;
                
			if (util.keyDown(util.KEY_J)) ty -= 32*delta;
			if (util.keyDown(util.KEY_M)) ty += 32*delta;
			
      
            /* the models position */
            model.translation( x, y, z );

            /* we take an angle increment and multiply it to our cumulative quaternion
             * as we are using an orientation (the quaternion) rather than 3 euler
             * rotation angles we avoid gimbal lock */
			
			// NB bacause xa,ya,za are delta values we can't just use 3 fixed
			// value quaternions here...  
			if (xa!=0) { tmpRot.axisAngle(1,0,0,xa); modelRot.multiply(tmpRot); }
			if (ya!=0) { tmpRot.axisAngle(0,1,0,ya); modelRot.multiply(tmpRot); }
			if (za!=0) { tmpRot.axisAngle(0,0,1,za); modelRot.multiply(tmpRot); }

            tmpMat.set(modelRot);
            model.multiply(tmpMat);
			
            /* the view/projection matrix is multiplied with the model matix
             * to give us the model, view, projection matrix for the shader */
            mvp.set(vp);
            mvp.multiply(model);

            //glBindVertexArray(vao);
            glBindBuffer(GL_ARRAY_BUFFER, vbo);
            
			glUseProgram(program);
			glBindTexture(GL_TEXTURE_2D, texture);
			
            glUniformMatrix4fv(u_matrix, 1, false , mvp.mat);
  
            glEnableVertexAttribArray(attr_pos);
            glEnableVertexAttribArray(attr_colour);
            glEnableVertexAttribArray(attr_uv);     

            // attrib, number items, type, normalised, byte stride, byte offset
            glVertexAttribPointer( attr_pos, 3, GL_FLOAT, false, 32, 0 );
            glVertexAttribPointer( attr_colour, 3, GL_FLOAT, false, 32, 12 );
            glVertexAttribPointer( attr_uv, 2, GL_FLOAT, false, 32, 24 );




            glDrawArrays(GL_TRIANGLES, 0, 36);
            
            mvpSave.set(mvp); // save current state

			// render another cube but unrotated
			tmpMat.translation(0f,0f,3f);
			mvp.set(vp);
            mvp.multiply(tmpMat);

			glUniformMatrix4fv(u_matrix, 1, false , mvp.mat);
			glDrawArrays(GL_TRIANGLES, 0, 36);
            
			mvp.set(mvpSave);


			// set up for the pyramid shape
            //glBindVertexArray(vao2);
            glBindBuffer(GL_ARRAY_BUFFER, vbo2);

            glEnableVertexAttribArray(attr_pos);
            glEnableVertexAttribArray(attr_colour);
            glEnableVertexAttribArray(attr_uv);     

            // attrib, number items, type, normalised, byte stride, byte offset
            glVertexAttribPointer( attr_pos, 3, GL_FLOAT, false, 32, 0 );
            glVertexAttribPointer( attr_colour, 3, GL_FLOAT, false, 32, 12 );
            glVertexAttribPointer( attr_uv, 2, GL_FLOAT, false, 32, 24 );


			// three pyramids one for each axis next to the rotating cube
            
            tmpMat.translation(0,0,1.2f); // (set to a translation NOT translated)
            mvp.multiply(tmpMat);
            tmpMat.rotationX(1.5707963268f); // 90 degrees
            mvp.multiply(tmpMat);
            glUniformMatrix4fv(u_matrix, 1, false , mvp.mat);
            glDrawArrays(GL_TRIANGLES, 0, 18);

            tmpMat.translation(1.2f,0,0);
            mvp.set(mvpSave);
            mvp.multiply(tmpMat);
            tmpMat.rotationZ(-1.5707963268f); // 90 degrees
            mvp.multiply(tmpMat);
            glUniformMatrix4fv(u_matrix, 1, false , mvp.mat);
            glDrawArrays(GL_TRIANGLES, 0, 18);

            tmpMat.translation(0,1.2f,0);
            mvp.set(mvpSave);
            mvp.multiply(tmpMat);
            glUniformMatrix4fv(u_matrix, 1, false , mvp.mat);
            glDrawArrays(GL_TRIANGLES, 0, 18);


            glDisableVertexAttribArray(attr_pos);
            glDisableVertexAttribArray(attr_colour);
            glDisableVertexAttribArray(attr_uv);
            
            
            // render the text 
            glDisable(GL_DEPTH_TEST);          
            glEnable(GL_BLEND); glBlendFunc (GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            
            printer.preRender(width,height);
            glBindTexture(GL_TEXTURE_2D, camoTex);
            printer.render(0,04,"Pitch:"+String.format("% .2f", modelRot.pitch()/0.01745329252f));
            printer.render(0,36,"Yaw  :"+String.format("% .2f", modelRot.yaw()/0.01745329252f));
            printer.render(0,68,"Roll :"+String.format("% .2f", modelRot.roll()/0.01745329252f));

			glBindTexture(GL_TEXTURE_2D, stripeTex);
            smlPrint.preRender(width,height);
            smlPrint.render(0,ty,"0123456789012345678901234567890123456789");
            smlPrint.render(0,ty+16,"          1         2         3 "+String.format("% .2f",ty));
            
            for (int my=0;my<4;my++) {
				for (int mx=0;mx<4;mx++) {
					smlPrint.render(20+(mx*96),200+(my*16),String.format("% .2f", mvpSave.mat.get(mx+my*4)));
				}
			}

            smlPrint.render(0,140,"Quat:"+
					String.format("% .2f", modelRot.x)+", "+
					String.format("% .2f", modelRot.y)+", "+
					String.format("% .2f", modelRot.z)+", "+
					String.format("% .2f", modelRot.w)
			);

			Print.postRender();
            
            glDisable (GL_BLEND);
			glEnable(GL_DEPTH_TEST);
			
			EGL.eglSwapBuffers(egl_display, egl_surface); 

            lastTime = thisTime;
            thisTime = ((float)(System.currentTimeMillis()-startTime)/1000f);//glfwGetTime();
            delta = (float)(thisTime-lastTime);
		}

		util.closeWindow(native_display,native_win);
	}


    private void updateProjection( float w, float h) {

		view.lookAt(eye, centre, up);

        width = w; height = h;
        aspect = w / h;

        perspective.perspectiveProjection(45f, aspect, .1f, 30f);

        vp.set(perspective);
        vp.multiply(view);
    }

}
