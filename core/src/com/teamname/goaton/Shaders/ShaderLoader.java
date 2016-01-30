package com.teamname.goaton.Shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Scanner;

public  class ShaderLoader {

    private static String readFile(File file, Charset encoding)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, encoding);
    }
    public static ShaderProgram LoadShader(String vert, String frag) throws ShaderException
    {
        String vertFile, fragFile;
        try
        {
            vertFile = readFile(Gdx.files.internal(vert).file(), StandardCharsets.UTF_8);
            fragFile = readFile(Gdx.files.internal(frag).file(), StandardCharsets.UTF_8);
        }
        catch(IOException e)
        {
            throw new ShaderException("Could not load shader files");
        }
        System.out.println("Vertfile reads:" + vertFile);
        ShaderProgram shader = new ShaderProgram(vertFile, fragFile);
        if (!shader.isCompiled())
        {
            throw new ShaderException("Error compiling shader: " + shader.getLog());
        }
        return shader;


    }

}
