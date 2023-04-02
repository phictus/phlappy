package com.phictus.phlappy.entities;

import com.phictus.phlappy.Game;
import com.phictus.phlappy.graphics.Shader;
import com.phictus.phlappy.graphics.Texture2D;

public class Bird extends Entity {
    @Override
    protected void onStart() {
        float[] vboData = new float[] {
            -0.5f, -0.5f, 0.0f, 0.0f, 1.0f,
             0.5f, -0.5f, 0.0f, 1.0f, 1.0f,
             0.5f,  0.5f, 0.0f, 1.0f, 0.0f,
            -0.5f,  0.5f, 0.0f, 0.0f, 0.0f
        };
        int[] eboData = new int[] { 0, 2, 1, 0, 2, 3 };

        mesh.create(vboData, eboData, 5);
        mesh.addAttrib(3);
        mesh.addAttrib(2);
        shader = Shader.texture;
        texture = Texture2D.bird;
        scale.x = 85.0f;
        scale.y = 60.0f;
        position.x = 1280.0f / 2.0f;
        position.y = 720.0f / 2.0f;
        position.z = 0.1f;
    }

    @Override
    protected void onUpdate(final float deltaTime) {
        if (Game.isSpaceDown()) {
            if (isFlapAvailable) {
                velocity = 500.0f;
                isFlapAvailable = false;
            }
        }
        else
            isFlapAvailable = true;

        if (Game.isRunning()) {
            rotation = velocity / 500.0f + 1.5f;
            rotation = Math.max((float)Math.toRadians(MIN_ROTATION), Math.min((float)Math.toRadians(MAX_ROTATION), rotation));

            velocity += ACCELERATION * deltaTime;
            position.y += velocity * deltaTime;
        }
    }

    private static final float ACCELERATION = -2000.0f;
    private static final double MAX_ROTATION = 30.0f;
    private static final double MIN_ROTATION = -30.0f;

    private boolean isFlapAvailable = true;
    private float velocity = 0.0f;
}
