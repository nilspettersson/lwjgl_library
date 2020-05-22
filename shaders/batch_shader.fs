#version 120

uniform sampler2D sampler1;

varying vec2 tex_coords;


void main(){
	vec2 l = gl_FragCoord.xy;

	gl_FragColor=vec4(0,1,1,1);
	
	
}