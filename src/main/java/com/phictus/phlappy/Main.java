package com.phictus.phlappy;

import org.lwjgl.opengl.GL;

import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL33C.*;

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

        while (!glfwWindowShouldClose(window)) {
            glfwPollEvents();

            glClearColor(0.2f, 0.3f, 0.8f, 0.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(window);
        }

        glfwDestroyWindow(window);
        glfwTerminate();
    }
}
