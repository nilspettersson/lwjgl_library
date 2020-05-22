#version 120

attribute vec3 vertices;
attribute vec4 a_color;
attribute vec2 textures;

varying vec2 tex_coords;
varying vec4 color;

void main(){
	color = a_color;
	tex_coords = textures;
	
	gl_Position = vec4(vertices,1);

}