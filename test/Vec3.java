
public class Vec3 {
    public float x,y,z;

    Vec3() {
        set(0f,0f,0f);
    }

    Vec3(float xi, float yi, float zi) {
        set(xi,yi,zi);
    }

    Vec3(Vec3 v2) {
        set(v2);
    }

    void set(float xi, float yi, float zi) {
        x=xi;   y=yi;   z=zi;
    }

    void set(Vec3 v2) {
        x=v2.x; y=v2.y; z=v2.z;
    }

    void subtract(Vec3 v2) {
        x-=v2.x;    y-=v2.y;    z-=v2.z;    
    }

    void add(Vec3 v2) {
        x+=v2.x;    y+=v2.y;    z+=v2.z;    
    }

    float length() {
        return (float)( Math.sqrt( (x*x) + (y*y) + (z*z) ) );
    }

    void normalise() {
        float l = 1f / length();
        x = x * l;
        y = y * l;
        z = z * l;
    }

    void cross(Vec3 v2) {
        float tx = (y * v2.z) - (z * v2.y);
        float ty = (z * v2.x) - (x * v2.z);
        float tz = (x * v2.y) - (y * v2.x);

        x=tx;y=ty;z=tz;
    }
}
