
public class Quat {

    public float x,y,z,w;

    Quat() {
        identity();
    }
    
    public void identity() {
		x=0;y=0;z=0;w=1;
	}

    public void rotationXYZ(float xi, float yi, float zi) {

        final float hx = xi / 2f;
        final float hy = yi / 2f;
        final float hz = zi / 2f;

        final float shx = (float)Math.sin(hx);
        final float chx = (float)Math.cos(hx);

        final float shy = (float)Math.sin(hy);
        final float chy = (float)Math.cos(hy);
        final float shz = (float)Math.sin(hz);
        final float chz = (float)Math.cos(hz);

        final float chy_shx = chy * shx;
        final float shy_chx = shy * chx;
        final float chy_chx = chy * chx;
        final float shy_shx = shy * shx;

        x = (chy_shx * chz) + (shy_chx * shz); // cos(yaw/2) * sin(pitch/2) * cos(roll/2) + sin(yaw/2) * cos(pitch/2) * sin(roll/2)
        y = (shy_chx * chz) - (chy_shx * shz); // sin(yaw/2) * cos(pitch/2) * cos(roll/2) - cos(yaw/2) * sin(pitch/2) * sin(roll/2)
        z = (chy_chx * shz) - (shy_shx * chz); // cos(yaw/2) * cos(pitch/2) * sin(roll/2) - sin(yaw/2) * sin(pitch/2) * cos(roll/2)
        w = (chy_chx * chz) + (shy_shx * shz); // cos(yaw/2) * cos(pitch/2) * cos(roll/2) + sin(yaw/2) * sin(pitch/2) * sin(roll/2)

        normalise(); // needed???
    }




    private final float PI2 = (float)(Math.PI * 2f);
    
    public void axisAngle(float xa, float ya, float za, float a) {

        float d = (float)Math.sqrt(xa * xa + ya * ya + za * za);
        if (d==0) {
            x=0;y=0;z=0;w=1;
            return;
        }
        d=1f/d;
        float la = a < 0 ? PI2 - (-a % PI2) : a % PI2;
        float ls = (float)Math.sin(la/2);
        float lc = (float)Math.cos(la/2);
        x = d * xa * ls;
        y = d * ya * ls;
        z = d * za * ls;
        w = lc;
        normalise();
    }

    public float length() {
        return (float)Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public void normalise() {
        float il = 1f/length();
        x=x * il;
        y=y * il;
        z=z * il;
        w=w * il;
    }

    public void multiply(Quat q2) {

        float tx = x * q2.w + w * q2.x + y * q2.z - z * q2.y;
        float ty = y * q2.w + w * q2.y + z * q2.x - x * q2.z;
        float tz = z * q2.w + w * q2.z + x * q2.y - y * q2.x;
        float tw = w * q2.w - x * q2.x - y * q2.y - z * q2.z;

        x = tx; y = ty; z = tz; w = tw; 
    }
    
    float pitch() {
		return (float)Math.atan2(2 * (y*z + w*x), w*w - x*x - y*y + z*z);
	}

	float yaw() {
		return (float)Math.asin(-2 * (x*z - w*y));
	}

	float roll() {
		return (float)Math.atan2(2 * (x*y + w*z), w*w + x*x - y*y - z*z);
	}
}
