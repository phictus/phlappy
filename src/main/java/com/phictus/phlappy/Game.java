package com.phictus.phlappy;

import org.lwjgl.opengl.GL;
import com.phictus.phlappy.entities.Bird;
import com.phictus.phlappy.graphics.Shader;
import com.phictus.phlappy.graphics.Texture2D;
import com.phictus.phlappy.math.Mat4;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33C.*;

public class Game {

    public static void run() {
        glfwInit();
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        window = glfwCreateWindow(1280, 720, "phlappy", NULL, NULL);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        glfwSwapInterval(1);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        Shader.init();
        Texture2D.init();

        Bird bird = new Bird();
        bird.init();

        Mat4 viewProjection = Mat4.orthographic(0.0f, 1280.0f, 0.0f, 720.0f, -1.0f, 1.0f);
        Shader.texture.use();
        Shader.texture.setUniformMat4("u_ViewProjection", viewProjection);

        double lastFrameTime = 0.0;
        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();
            final double time = glfwGetTime();
            final float deltaTime = (float)(time - lastFrameTime);
            lastFrameTime = time;

            if (!running && isSpaceDown())
                running = true;

            bird.update(deltaTime);

            glClear(GL_COLOR_BUFFER_BIT);

            bird.render();

            glfwSwapBuffers(window);
        }

        bird.destroy();

        Texture2D.destroy();
        Shader.destroy();

        glfwDestroyWindow(window);
        glfwTerminate();
    }

    public static boolean isSpaceDown() {
        return glfwGetKey(window, GLFW_KEY_SPACE) == GLFW_PRESS;
    }

    public static boolean isRunning() {
        return running;
    }

    private static long window;
    private static boolean running = false;
}
