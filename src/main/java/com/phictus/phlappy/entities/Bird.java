package com.phictus.phlappy.entities;

import com.phictus.phlappy.graphics.Shader;
import com.phictus.phlappy.graphics.Texture2D;

public class Bird extends Entity {
    @Override
    protected void onStart() {
        float[] vboData = new float[] {
            0.0f, 0.0f, 0.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f
        };
        int[] eboData = new int[] { 0, 2, 1, 0, 2, 3 };

        mesh.create(vboData, eboData, 5);
        mesh.addAttrib(3);
        mesh.addAttrib(2);
        shader = Shader.texture;
        texture = Texture2D.bird;
        position.x = 0.0f;
        position.y = 0.0f;
        position.z = 0.9f;
        scale.x = 85.0f;
        scale.y = 60.0f;
    }

    @Override
    protected void onUpdate(final float deltaTime) {

    }
}
