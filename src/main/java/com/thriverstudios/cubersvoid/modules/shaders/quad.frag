#version 330 core

in vec2 TexCoord;
out vec4 FragColor;

uniform uTexture;

void main()
{
    FragColor = texture(uTexture, TexCoord);
}