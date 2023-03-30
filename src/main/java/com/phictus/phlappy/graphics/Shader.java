package com.phictus.phlappy.graphics;

import static org.lwjgl.opengl.GL33C.*;

import com.phictus.phlappy.math.Mat4;

public class Shader {
    public static Shader color;

    public static void init() {
        String vColor =
            """
            #version 330 core

            layout(location = 0) in vec3 a_Position;
            layout(location = 1) in vec3 a_Color;

            uniform mat4 u_ViewProjection;
            uniform mat4 u_Transform;

            out vec3 v_Color;

            void main()
            {
                v_Color = a_Color;
                gl_Position = u_ViewProjection * u_Transform * vec4(a_Position, 1.0);
            }
            """;
        String fColor =
            """
            #version 330 core

            layout(location = 0) out vec4 o_Color;

            in vec3 v_Color;

            void main()
            {
                o_Color = vec4(v_Color, 1.0);
            }
            """;

        color = new Shader();
        color.createProgram(vColor, fColor);
    }

    public static void destroy() {
        glDeleteProgram(color.program);
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
        int vShader = createShader(GL_VERTEX_SHADER, vShaderSource);
        int fShader = createShader(GL_FRAGMENT_SHADER, fShaderSource);

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
}
