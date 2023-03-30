package com.phictus.phlappy.graphics;

import static org.lwjgl.opengl.GL33C.*;

public class Mesh {
    private int vao, vbo, ebo;
    private int count, stride, offset = 0, index = 0;

    public void create(float[] vboData, int[] eboData, int stride) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vboData,  GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, eboData, GL_STATIC_DRAW);

        this.count = eboData.length;
        this.stride = stride * Float.SIZE / 8;
    }

    public void destroy() {
        glDeleteBuffers(ebo);
        glDeleteBuffers(vbo);
        glDeleteVertexArrays(vao);
    }

    public void addAttrib(int size) {
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);

        glEnableVertexAttribArray(index);
        glVertexAttribPointer(index, size, GL_FLOAT, false, stride, offset);

        offset += size * Float.SIZE / 8;
        index++;
    }

    public void draw() {
        glBindVertexArray(vao);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glDrawElements(GL_TRIANGLES, count, GL_UNSIGNED_INT, 0);
    }
}
