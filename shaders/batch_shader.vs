#version 330

layout (location = 0) in vec3 vertices;
layout (location = 1) in vec4 a_color;
layout (location = 2) in vec2 textures;

uniform mat4 projection;

varying vec2 tex_coords;
varying vec4 color;

void main(){
	color = a_color;
	tex_coords = textures;
	
	gl_Position = projection * vec4(vertices,1);

}