package com.phictus.phlappy.entities;

import com.phictus.phlappy.graphics.Shader;
import com.phictus.phlappy.graphics.Texture2D;

public class Background extends Entity {
    @Override
    protected void onStart() {
        final float deltaX = 0.04f;
        float[] vboData = new float[] {
            0.0f - 0 * deltaX, 0.0f, 0.0f, 0.0f, 1.0f,
            1.0f - 0 * deltaX, 0.0f, 0.0f, 1.0f, 1.0f,
            1.0f - 0 * deltaX, 1.0f, 0.0f, 1.0f, 0.0f,
            0.0f - 0 * deltaX, 1.0f, 0.0f, 0.0f, 0.0f,

            1.0f - 1 * deltaX, 0.0f, 0.0f, 0.0f, 1.0f,
            2.0f - 1 * deltaX, 0.0f, 0.0f, 1.0f, 1.0f,
            2.0f - 1 * deltaX, 1.0f, 0.0f, 1.0f, 0.0f,
            1.0f - 1 * deltaX, 1.0f, 0.0f, 0.0f, 0.0f,

            2.0f - 2 * deltaX, 0.0f, 0.0f, 0.0f, 1.0f,
            3.0f - 2 * deltaX, 0.0f, 0.0f, 1.0f, 1.0f,
            3.0f - 2 * deltaX, 1.0f, 0.0f, 1.0f, 0.0f,
            2.0f - 2 * deltaX, 1.0f, 0.0f, 0.0f, 0.0f,

            3.0f - 3 * deltaX, 0.0f, 0.0f, 0.0f, 1.0f,
            4.0f - 3 * deltaX, 0.0f, 0.0f, 1.0f, 1.0f,
            4.0f - 3 * deltaX, 1.0f, 0.0f, 1.0f, 0.0f,
            3.0f - 3 * deltaX, 1.0f, 0.0f, 0.0f, 0.0f
        };
        int[] eboData = new int[] {
             0,  2,  1,  0,  2,  3,
             4,  6,  5,  4,  6,  7,
             8, 10,  9,  8, 10, 11,
            12, 14, 13, 12, 14, 15
        };

        mesh.create(vboData, eboData, 5);
        mesh.addAttrib(3);
        mesh.addAttrib(2);
        shader = Shader.texture;
        texture = Texture2D.background;
        scale.x = 600.0f;
        scale.y = 720.0f;
        position.x = 0.0f;
        position.z = 0.9f;
    }

    @Override
    protected void onUpdate(final float deltaTime) {
        position.x += VELOCITY * deltaTime;
        if (position.x <= CHANGE_POSITION)
            position.x = 0.0f;

    }

    private static final float CHANGE_POSITION = -576.0f;
    private static final float VELOCITY = -100.0f;
}
