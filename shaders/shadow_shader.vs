#version 330

layout (location = 0) in vec3 vertices;

uniform mat4 projection;


void main(){
	
	gl_Position = /*projection * */vec4(vertices,1);

}