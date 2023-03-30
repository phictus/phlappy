package com.phictus.phlappy;

import org.lwjgl.opengl.GL;

import com.phictus.phlappy.entities.Entity;
import com.phictus.phlappy.graphics.Shader;
import com.phictus.phlappy.math.Mat4;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33C.*;

class Player extends Entity {
    @Override
    protected void onStart() {
        float[] vboData = new float[] {
            0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f,
            1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f
        };
        int[] eboData = new int[] { 0, 2, 1, 0, 2, 3 };

        mesh.create(vboData, eboData, 6);
        mesh.addAttrib(3);
        mesh.addAttrib(3);
        shader = Shader.color;
        position.x = 1280.0f / 2.0f;
        position.y = 720.0f / 2.0f;
        position.z = 0.9f;
        scale.x = 10.0f;
        scale.y = 10.0f;
    }

    @Override
    protected void onUpdate(final float deltaTime) {
        rotation += Math.toRadians(90.0f) * deltaTime;
        scale.x += 10.0f * deltaTime;
        scale.y += 10.0f * deltaTime;
    }
}

public class Main {
    public static void main(String[] args) {
        glfwInit();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        long window = glfwCreateWindow(1280, 720, "phlappy", NULL, NULL);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);

        Shader.init();
        Player player = new Player();
        player.init();

        Mat4 viewProjection = Mat4.orthographic(0.0f, 1280.0f, 0.0f, 720.0f, -1.0f, 1.0f);
        Shader.color.use();
        Shader.color.setUniformMat4("u_ViewProjection", viewProjection);

        double lastFrameTime = 0.0;
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            final double time = glfwGetTime();
            final float deltaTime = (float)(time - lastFrameTime);
            lastFrameTime = time;

            player.update(deltaTime);

            glClear(GL_COLOR_BUFFER_BIT);

            player.render();

            glfwSwapBuffers(window);
        }

        player.destroy();
        Shader.destroy();

        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
