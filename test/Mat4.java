
import java.nio.FloatBuffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Mat4 {

    public  FloatBuffer mat = ByteBuffer.allocateDirect(16*4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    private FloatBuffer tmp = ByteBuffer.allocateDirect(16*4).order(ByteOrder.nativeOrder()).asFloatBuffer();

    Mat4() {    // by default give a new matix as identity
        identity();
    }

    public void identity() {    // who'd have thunk, clear doesn't actually clear values...
        mat.put( 0,1f);    mat.put( 1,0f);    mat.put( 2,0f);    mat.put( 3,0f);
        mat.put( 4,0f);    mat.put( 5,1f);    mat.put( 6,0f);    mat.put( 7,0f);
        mat.put( 8,0f);    mat.put( 9,0f);    mat.put(10,1f);    mat.put(11,0f);
        mat.put(12,0f);    mat.put(13,0f);    mat.put(14,0f);    mat.put(15,1f);
    }

    public void set(Mat4 m2) {
        // TODO find out why/what bulk put is doing and why it messes this up...
        mat.put( 0, m2.mat.get( 0));    mat.put( 1, m2.mat.get( 1));    mat.put( 2, m2.mat.get( 2));    mat.put( 3, m2.mat.get( 3));
        mat.put( 4, m2.mat.get( 4));    mat.put( 5, m2.mat.get( 5));    mat.put( 6, m2.mat.get( 6));    mat.put( 7, m2.mat.get( 7));
        mat.put( 8, m2.mat.get( 8));    mat.put( 9, m2.mat.get( 9));    mat.put(10, m2.mat.get(10));    mat.put(11, m2.mat.get(11));
        mat.put(12, m2.mat.get(12));    mat.put(13, m2.mat.get(13));    mat.put(14, m2.mat.get(14));    mat.put(15, m2.mat.get(15));
    }

    public void set(float m0 , float m1 , float m2 , float m3 ,
                    float m4 , float m5 , float m6 , float m7 ,
                    float m8 , float m9 , float m10, float m11,
                    float m12, float m13, float m14, float m15) {
        mat.put( 0, m0 );   mat.put( 1, m1 );   mat.put( 2, m2 );   mat.put( 3, m3 );
        mat.put( 4, m4 );   mat.put( 5, m5 );   mat.put( 6, m6 );   mat.put( 7, m7 );
        mat.put( 8, m8 );   mat.put( 9, m9 );   mat.put(10, m10);   mat.put(11, m11);
        mat.put(12, m12);   mat.put(13, m13);   mat.put(14, m14);   mat.put(15, m15);
    }

    // use a quaternion to turn the matrix into a rotational matrix
    public void set(Quat q) {
        final float xx = q.x * q.x;
        final float xy = q.x * q.y;
        final float xz = q.x * q.z;
        final float xw = q.x * q.w;
        final float yy = q.y * q.y;
        final float yz = q.y * q.z;
        final float yw = q.y * q.w;
        final float zz = q.z * q.z;
        final float zw = q.z * q.w;

        mat.put( 0, 1 - 2 * (yy + zz));
        mat.put( 1, 2 * (xy + zw));
        mat.put( 2, 2 * (xz - yw));
        mat.put( 3, 0);

        mat.put( 4, 2 * (xy - zw));
        mat.put( 5, 1 - 2 * (xx + zz));
        mat.put( 6, 2 * (yz + xw));
        mat.put( 7, 0);

        mat.put( 8, 2 * (xz + yw));
        mat.put( 9, 2 * (yz - xw));
        mat.put(10, 1 - 2 * (xx + yy));
        mat.put(11, 0);

        mat.put(12, 0);
        mat.put(13, 0);
        mat.put(14, 0);
        mat.put(15, 1);
    }

    
    public void lookAt(Vec3 eye, Vec3 centre, Vec3 up) {

        final Vec3 f = new Vec3(), s = new Vec3(), u = new Vec3();
        final Mat4 t = new Mat4();

        f.set(centre);
        f.subtract(eye);
        f.normalise();

        up.normalise();

        s.set(f);
        s.cross(up);
        s.normalise();

        u.set(s);
        u.cross(f);
        u.normalise();

        identity();
        mat.put( 0,  s.x);
        mat.put( 1,  u.x);
        mat.put( 2, -f.x);

        mat.put( 4,  s.y);
        mat.put( 5,  u.y);
        mat.put( 6, -f.y);

        mat.put( 8,  s.z);
        mat.put( 9,  u.z);
        mat.put(10, -f.z);

        t.translation(-eye.x, -eye.y, -eye.z);
        multiply(t);
    }

    public void translation(Vec3 v) { // makes a translation matrix destroying previous content (ie doesnt translate the existing matrix)
        identity();
        mat.put(12, v.x);
        mat.put(13, v.y);
        mat.put(14, v.z);
    }

    public void translation(float xi, float yi, float zi) {
        identity();
        mat.put(12, xi);
        mat.put(13, yi);
        mat.put(14, zi);
    }

    public void perspectiveProjection(float fov, float aspect, float near, float far) {
        float cotan = 0;
        fov = (float) ( (fov * Math.PI) / 180.0 );
        float r = (float)(fov / 2.0);
        float delta = far - near;
        float s = (float)Math.sin(r);

        if (s == 0 || aspect == 0 || delta == 0) {
            //System.out.println("perspective fail");
            return;
        }
        cotan = (float)Math.cos(r) / s;

        identity();
        mat.put( 0, cotan / aspect);
        mat.put( 5, cotan);
        mat.put(10, -(far + near) / delta);
        mat.put(11, -1);
        mat.put(14, -2 * near * far / delta);
        mat.put(15, 0); 
    }

    void rotationX(float rad) {
        mat.put( 0, 1f);
        mat.put( 1, 0f);
        mat.put( 2, 0f);
        mat.put( 3, 0f);

        mat.put( 4, 0f);
        mat.put( 5, (float)Math.cos(rad));
        mat.put( 6, (float)Math.sin(rad));
        mat.put( 7, 0f);

        mat.put( 8, 0f);
        mat.put( 9, -(float)Math.sin(rad));
        mat.put(10, (float)Math.cos(rad));
        mat.put(11, 0f);

        mat.put(12, 0f);
        mat.put(13, 0f);
        mat.put(14, 0f);
        mat.put(15, 1f);
    }

    void rotationY(float rad) {
        mat.put( 0, (float)Math.cos(rad));
        mat.put( 1, 0f);
        mat.put( 2, -(float)Math.sin(rad));
        mat.put( 3, 0f);

        mat.put( 4, 0f);
        mat.put( 5, 1f);
        mat.put( 6, 0f);
        mat.put( 7, 0f);

        mat.put( 8, (float)Math.sin(rad));
        mat.put( 9, 0f);
        mat.put(10, (float)Math.cos(rad));
        mat.put(11, 0f);

        mat.put(12, 0f);
        mat.put(13, 0f);
        mat.put(14, 0f);
        mat.put(15, 1f);
    }
    
    void rotationZ(float rad) {
        mat.put( 0, (float)Math.cos(rad));
        mat.put( 1, (float)Math.sin(rad));
        mat.put( 2, 0f);
        mat.put( 3, 0f);

        mat.put( 4, -(float)Math.sin(rad));
        mat.put( 5, (float)Math.cos(rad));
        mat.put( 6, 0f);
        mat.put( 7, 0f);

        mat.put( 8, 0f);
        mat.put( 9, 0f);
        mat.put(10, 1f);
        mat.put(11, 0f);

        mat.put(12, 0f);
        mat.put(13, 0f);
        mat.put(14, 0f);
        mat.put(15, 1f);
    }

    public void rotationXYZ(float xRotation, float yRotation, float zRotation)
    {

        float cx = (float)Math.cos(xRotation);
        float cy = (float)Math.cos(yRotation);
        float cz = (float)Math.cos(zRotation);
        float sx = (float)Math.sin(xRotation);
        float sy = (float)Math.sin(yRotation);
        float sz = (float)Math.sin(zRotation);

        mat.put( 0,  cy *  cz);
        mat.put( 1, (sx * sy * cz) + (cx * sz));
        mat.put( 2, -(cx * sy * cz) + (sx * sz));
        mat.put( 3, 0f);

        mat.put( 4, -cy *  sz);
        mat.put( 5, -(sx * sy * sz) + (cx * cz));
        mat.put( 6, (cx * sy * sz) + (sx * cz));
        mat.put( 7, 0f);

        mat.put( 8,  sy);
        mat.put( 9, -sx * cy);
        mat.put(10, cx * cy);
        mat.put(11, 0f);

        mat.put(12, 0f);
        mat.put(13, 0f);
        mat.put(14, 0f);
        mat.put(15, 1f);

    }



    public void multiply(Mat4 m2) {   // this = this * m2
        tmp.put( 0, mat.get( 0)*m2.mat.get( 0)+mat.get( 4)*m2.mat.get( 1)+mat.get( 8)*m2.mat.get( 2)+mat.get(12)*m2.mat.get( 3));
        tmp.put( 1, mat.get( 1)*m2.mat.get( 0)+mat.get( 5)*m2.mat.get( 1)+mat.get( 9)*m2.mat.get( 2)+mat.get(13)*m2.mat.get( 3));
        tmp.put( 2, mat.get( 2)*m2.mat.get( 0)+mat.get( 6)*m2.mat.get( 1)+mat.get(10)*m2.mat.get( 2)+mat.get(14)*m2.mat.get( 3));
        tmp.put( 3, mat.get( 3)*m2.mat.get( 0)+mat.get( 7)*m2.mat.get( 1)+mat.get(11)*m2.mat.get( 2)+mat.get(15)*m2.mat.get( 3));

        tmp.put( 4, mat.get( 0)*m2.mat.get( 4)+mat.get( 4)*m2.mat.get( 5)+mat.get( 8)*m2.mat.get( 6)+mat.get(12)*m2.mat.get( 7));
        tmp.put( 5, mat.get( 1)*m2.mat.get( 4)+mat.get( 5)*m2.mat.get( 5)+mat.get( 9)*m2.mat.get( 6)+mat.get(13)*m2.mat.get( 7));
        tmp.put( 6, mat.get( 2)*m2.mat.get( 4)+mat.get( 6)*m2.mat.get( 5)+mat.get(10)*m2.mat.get( 6)+mat.get(14)*m2.mat.get( 7));
        tmp.put( 7, mat.get( 3)*m2.mat.get( 4)+mat.get( 7)*m2.mat.get( 5)+mat.get(11)*m2.mat.get( 6)+mat.get(15)*m2.mat.get( 7));

        tmp.put( 8, mat.get( 0)*m2.mat.get( 8)+mat.get( 4)*m2.mat.get( 9)+mat.get( 8)*m2.mat.get(10)+mat.get(12)*m2.mat.get(11));
        tmp.put( 9, mat.get( 1)*m2.mat.get( 8)+mat.get( 5)*m2.mat.get( 9)+mat.get( 9)*m2.mat.get(10)+mat.get(13)*m2.mat.get(11));
        tmp.put(10, mat.get( 2)*m2.mat.get( 8)+mat.get( 6)*m2.mat.get( 9)+mat.get(10)*m2.mat.get(10)+mat.get(14)*m2.mat.get(11));
        tmp.put(11, mat.get( 3)*m2.mat.get( 8)+mat.get( 7)*m2.mat.get( 9)+mat.get(11)*m2.mat.get(10)+mat.get(15)*m2.mat.get(11));

        tmp.put(12, mat.get( 0)*m2.mat.get(12)+mat.get( 4)*m2.mat.get(13)+mat.get( 8)*m2.mat.get(14)+mat.get(12)*m2.mat.get(15));
        tmp.put(13, mat.get( 1)*m2.mat.get(12)+mat.get( 5)*m2.mat.get(13)+mat.get( 9)*m2.mat.get(14)+mat.get(13)*m2.mat.get(15));
        tmp.put(14, mat.get( 2)*m2.mat.get(12)+mat.get( 6)*m2.mat.get(13)+mat.get(10)*m2.mat.get(14)+mat.get(14)*m2.mat.get(15));
        tmp.put(15, mat.get( 3)*m2.mat.get(12)+mat.get( 7)*m2.mat.get(13)+mat.get(11)*m2.mat.get(14)+mat.get(15)*m2.mat.get(15));

        // TODO find out why/what bulk put is doing and why it messes this up...
        mat.put( 0, tmp.get( 0));    mat.put( 1, tmp.get( 1));    mat.put( 2, tmp.get( 2));    mat.put( 3, tmp.get( 3));
        mat.put( 4, tmp.get( 4));    mat.put( 5, tmp.get( 5));    mat.put( 6, tmp.get( 6));    mat.put( 7, tmp.get( 7));
        mat.put( 8, tmp.get( 8));    mat.put( 9, tmp.get( 9));    mat.put(10, tmp.get(10));    mat.put(11, tmp.get(11));
        mat.put(12, tmp.get(12));    mat.put(13, tmp.get(13));    mat.put(14, tmp.get(14));    mat.put(15, tmp.get(15));

    }
}
