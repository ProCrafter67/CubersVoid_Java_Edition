package com.thriverstudios.cubersvoid.game.modules.shader;

import org.joml.*;
import org.lwjgl.BufferUtils;

import java.io.*;
import java.nio.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private final int program;

    public Shader(String vertexPath, String fragmentPath) {
        program = CreateProgram(vertexPath, fragmentPath);
    }

    private int CreateProgram(String vertexPath, String fragmentPath) {
        int vertexShader = LoadShader(vertexPath, GL_VERTEX_SHADER);
        int fragmentShader = LoadShader(fragmentPath, GL_FRAGMENT_SHADER);

        CheckCompileErrors(vertexShader, "vertex");
        CheckCompileErrors(fragmentShader, "fragment");

        int program = glCreateProgram();

        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);
        glLinkProgram(program);

        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);

        CheckCompileErrors(program, "program");

        glUseProgram(program);
        return program;
    }

    private int LoadShader(String path, int type) {
        StringBuilder code;

        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            code = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                code.append(line).append("\n");
            }
            reader.close();
        } catch(IOException e) {
            e.printStackTrace();
            return 0;
        }

        int shader = glCreateShader(type);
        glShaderSource(shader, code.toString());
        glCompileShader(shader);
        return shader;
    }

    private void CheckCompileErrors(int shader, String type) {
        IntBuffer success = BufferUtils.createIntBuffer(32);
        CharSequence infoLog;

        if(type.equals("Vertex") || type.equals("Fragment")) {
            glGetShaderiv(shader, GL_COMPILE_STATUS, success);
            if(success.get(0) == GL_FALSE) {
                infoLog = glGetShaderInfoLog(shader);
                System.out.println("Error creating " + type + " shader: " + infoLog);
            }
        } else if(type.equals("Program")) {
            glGetProgramiv(shader, GL_LINK_STATUS, success);
            if(success.get(0) == GL_FALSE) {
                infoLog = glGetShaderInfoLog(shader);
                System.out.println("Error linking " + type + ": " + infoLog);
            }
        }
    }

    public void Use() {
        glUseProgram(program);
    }

    public void PutBool(String name, boolean value) {
        glUniform1i(glGetUniformLocation(program, name), value ? 1 : 0);
    }

    public void PutInt(String name, int value) {
        glUniform1i(glGetUniformLocation(program, name), value);
    }

    public void PutFloat(String name, float value) {
        glUniform1f(glGetUniformLocation(program, name), value);
    }

    public void PutVec2(String name, Vector2f value) {
        glUniform2f(glGetUniformLocation(program, name), value.x, value.y);
    }

    public void PutVec3(String name, Vector3f value) {
        glUniform3f(glGetUniformLocation(program, name), value.x, value.y, value.z);
    }

    public void PutVec4(String name, Vector4f value) {
        glUniform4f(glGetUniformLocation(program, name), value.x, value.y, value.z, value.w);
    }

    public void PutMat2(String name, Matrix2f value) {
        float[] buffer = new float[]{};
        value.get(buffer);
        glUniformMatrix2fv(glGetUniformLocation(program, name), false, buffer);
    }

    public void PutMat3(String name, Matrix3f value) {
        float[] buffer = new float[]{};
        value.get(buffer);
        glUniformMatrix3fv(glGetUniformLocation(program, name), false, buffer);
    }

    public void PutMat4(String name, Matrix4f value) {
        float[] buffer = new float[]{};
        value.get(buffer);
        glUniformMatrix4fv(glGetUniformLocation(program, name), false, buffer);
    }

    public void Delete() {
        glDeleteProgram(program);
    }
}
