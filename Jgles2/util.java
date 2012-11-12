
package Jgles2;

public class util {
    static {
        System.loadLibrary("Jgles2");
    }
    
    public static native long get_native_display();
    public static native long make_native_window(long native_display,
                            long egl_display, long config, int x, int y, 
                            int width, int height, boolean fullscreen);
    
    util() {
        
    }
}
