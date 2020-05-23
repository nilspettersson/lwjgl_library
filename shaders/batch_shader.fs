#version 120

uniform sampler2D sampler1;

varying vec2 tex_coords;
varying vec4 color;


//light locations
uniform mat4[200] lights;  
uniform int size;

void main(){
	vec2 l = gl_FragCoord.xy;
	
	
	float r=0;
	float g=0;
	float b=0;
	if(size!=0){
		
		float dis=0;
		for(int i=0;i<size;i++){
			float xdif=l.x-lights[i][0][0];
			float ydif=l.y-lights[i][0][1];
			dis=(lights[i][0][3]+1)/(sqrt((xdif*xdif)+(ydif*ydif)+(lights[i][0][2]*lights[i][0][2])));
			
			r+=lights[i][1][0]*dis;
			g+=lights[i][1][1]*dis;
			b+=lights[i][1][2]*dis;
			
		}
		gl_FragColor=((vec4(r,g,b,1)+color-1));
		

	}
	else{
		gl_FragColor = color;
	}
	

	
	
	
}