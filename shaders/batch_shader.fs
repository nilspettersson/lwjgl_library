#version 120

uniform sampler2D sampler1;

varying vec2 tex_coords;
varying vec4 color;

void main(){
	vec2 l = gl_FragCoord.xy;

	gl_FragColor = color;
	
	
}