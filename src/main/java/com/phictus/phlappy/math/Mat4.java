package com.phictus.phlappy.math;

public class Mat4 {
    public float[] data = new float[16];

    public static Mat4 identity() {
        Mat4 matrix = new Mat4();

        matrix.data[0 + 0 * 4] = 1.0f;
        matrix.data[1 + 1 * 4] = 1.0f;
        matrix.data[2 + 2 * 4] = 1.0f;
        matrix.data[3 + 3 * 4] = 1.0f;

        matrix.data[1 + 0 * 4] = 0.0f;
        matrix.data[2 + 0 * 4] = 0.0f;
        matrix.data[3 + 0 * 4] = 0.0f;
        matrix.data[0 + 1 * 4] = 0.0f;
        matrix.data[2 + 1 * 4] = 0.0f;
        matrix.data[3 + 1 * 4] = 0.0f;
        matrix.data[0 + 2 * 4] = 0.0f;
        matrix.data[1 + 2 * 4] = 0.0f;
        matrix.data[3 + 2 * 4] = 0.0f;
        matrix.data[0 + 3 * 4] = 0.0f;
        matrix.data[1 + 3 * 4] = 0.0f;
        matrix.data[2 + 3 * 4] = 0.0f;

        return matrix;
    }

    public static Mat4 orthographic(float left, float right, float bottom, float top, float near, float far) {
        Mat4 matrix = identity();

        matrix.data[0 + 0 * 4] = 2.0f / (right - left);
        matrix.data[1 + 1 * 4] = 2.0f / (top - bottom);
        matrix.data[2 + 2 * 4] = 2.0f / (near - far);

        matrix.data[0 + 3 * 4] = (left + right) / (left - right);
        matrix.data[1 + 3 * 4] = (bottom + top) / (bottom - top);
        matrix.data[2 + 3 * 4] = (far + near) / (far - near);

        return matrix;
    }

    public static Mat4 position(Vec3 vector) {
        Mat4 matrix = identity();

        matrix.data[0 + 3 * 4] = vector.x;
        matrix.data[1 + 3 * 4] = vector.y;
        matrix.data[2 + 3 * 4] = vector.z;

        return matrix;
    }

    public static Mat4 rotation(float angle) {
        Mat4 matrix = identity();

        float sin = (float)Math.sin((double)angle);
        float cos = (float)Math.cos((double)angle);

        matrix.data[0 + 0 * 4] = cos;
        matrix.data[1 + 0 * 4] = sin;

        matrix.data[0 + 1 * 4] = -sin;
        matrix.data[1 + 1 * 4] = cos;

        return matrix;

    }

    public static Mat4 scale(Vec3 vector) {
        Mat4 matrix = identity();

        matrix.data[0 + 0 * 4] = vector.x;
        matrix.data[1 + 1 * 4] = vector.y;
        matrix.data[2 + 2 * 4] = vector.z;

        return matrix;
    }

    public Mat4 multiply(Mat4 matrix) {
        Mat4 result = new Mat4();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                float sum = 0.0f;
                for (int e = 0; e < 4; e++) {
                    sum += data[x + e * 4] * matrix.data[e + y * 4]; 
                }
                result.data[x + y * 4] = sum;
            }
        }

        return result;
    }
}
