package com.phictus.phlappy.graphics;

import static org.lwjgl.opengl.GL33C.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.phictus.phlappy.math.Mat4;

public class Shader {
    public static Shader texture;

    public static void init() {
        texture = new Shader();
        texture.createProgram("assets/texture_v.glsl", "assets/texture_f.glsl");
    }

    public static void destroy() {
        glDeleteProgram(texture.program);
    }

    public void use() {
        glUseProgram(program);
    }

    private Shader() { }

    private int program;

    private int createShader(int type, String source) {
        int shader = glCreateShader(type);
        glShaderSource(shader, source);
        glCompileShader(shader);

        return shader;
    }

    private void createProgram(String vShaderSource, String fShaderSource) {
        program = glCreateProgram();
        int vShader = 0, fShader = 0;
        try {
            vShader = createShader(GL_VERTEX_SHADER, Files.readString(Path.of(vShaderSource)));
            fShader = createShader(GL_FRAGMENT_SHADER, Files.readString(Path.of(fShaderSource)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        glAttachShader(program, vShader);
        glAttachShader(program, fShader);

        glLinkProgram(program);
        glValidateProgram(program);

        glDetachShader(program, vShader);
        glDetachShader(program, fShader);

        glDeleteShader(vShader);
        glDeleteShader(fShader);
    }

    public void setUniformMat4(String name, Mat4 value) {
        glUniformMatrix4fv(glGetUniformLocation(program, name), false, value.data);
    }

    public void setUniformInt(String name, int value) {
        glUniform1i(glGetUniformLocation(program, name), value);
    }
}
