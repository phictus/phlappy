package com.phictus.phlappy.math;

public class Vec3 {
    public float x, y, z;

    public Vec3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vec3 zero() {
        return new Vec3(0.0f, 0.0f, 0.0f);
    }

    public static Vec3 one() {
        return new Vec3(1.0f, 1.0f, 1.0f);
    }
}
