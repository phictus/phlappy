package com.phictus.phlappy.graphics;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL33C.*;

public class Texture2D {
    public static Texture2D bird;
    public static Texture2D background;

    public static void init() {
        bird = new Texture2D();
        bird.createTexture("assets/bird.png");

        background = new Texture2D();
        background.createTexture("assets/background.png");
    }

    public static void destroy() {
        glDeleteTextures(bird.texture);
        glDeleteTextures(background.texture);
    }

    public void use() {
        glActiveTexture(0);
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    private Texture2D() { }

    private int texture;

    private void createTexture(String path) {
        int[] pixels = null;
        int width = 0, height = 0;
        try {
            BufferedImage image = ImageIO.read(new FileInputStream(path));
            width = image.getWidth();
            height = image.getHeight();
            pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] data = new int[width * height];
        for (int i = 0; i < width * height; i++) {
            int a = (pixels[i] & 0xff000000) >> 24;
            int r = (pixels[i] & 0xff0000) >> 16;
            int g = (pixels[i] & 0xff00) >> 8;
            int b = (pixels[i] & 0xff);
            data[i] = a << 24 | b << 16 | g << 8 | r;
        }

        texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, data);
    }
}
