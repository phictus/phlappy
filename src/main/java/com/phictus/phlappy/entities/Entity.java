package com.phictus.phlappy.entities;

import com.phictus.phlappy.graphics.Mesh;
import com.phictus.phlappy.graphics.Shader;
import com.phictus.phlappy.math.Mat4;
import com.phictus.phlappy.math.Vec3;

public abstract class Entity {
    public Vec3 position = Vec3.zero();
    public float rotation = 0.0f;
    public Vec3 scale = Vec3.one();

    private Mat4 transform = Mat4.identity();

    protected Mesh mesh;
    protected Shader shader;

    protected abstract void onStart();
    protected abstract void onUpdate(final float deltaTime);

    public void init() {
        mesh = new Mesh();
        onStart();
    }

    public void destroy() {
        mesh.destroy();
    }

    public void update(final float deltaTime) {
        onUpdate(deltaTime);
        transform = Mat4.position(position).multiply(Mat4.rotation(rotation).multiply(Mat4.scale(scale)));
    }

    public void render() {
        shader.use();
        shader.setUniformMat4("u_Transform", transform);
        mesh.draw();
    }
}
