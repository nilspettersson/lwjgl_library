#version 120

uniform sampler2D sampler1;
uniform sampler2D sampler2;

varying vec2 tex_coords;

uniform vec4 color;
uniform float roughness;
uniform float bump;

uniform mat4[200] lights;  //light locations
uniform int size;

void main(){
	vec2 l = gl_FragCoord.xy;
	

	vec4 texture=texture2D(sampler1,tex_coords);
	vec4 texture2=texture2D(sampler2,tex_coords);
	
	//gl_FragColor=color+texture;
	
	float r=0;
	float g=0;
	float b=0;
	
	if(size!=0){
		
		float dis=0;
		for(int i=0;i<size;i++){
			float xdif=l.x-lights[i][0][0];
			float ydif=l.y-lights[i][0][1];
			dis=((lights[i][0][3]+roughness)/((bump/10)+1))/(sqrt((xdif*xdif)+(ydif*ydif)+(lights[i][0][2]*lights[i][0][2])+roughness*roughness));
			
			r+=lights[i][1][0]*dis;
			g+=lights[i][1][1]*dis;
			b+=lights[i][1][2]*dis;
			
		}
		/*r/=size;
		g/=size;
		b/=size;*/
		
		vec4 bumpTexture=vec4((texture2.x+texture2.y+texture2.y)/3,(texture2.x+texture2.y+texture2.y)/3,(texture2.x+texture2.y+texture2.y)/3,1);
		gl_FragColor=((vec4(r,g,b,1)*pow(bumpTexture+0.5,vec4(bump,bump,bump,bump))+texture-1));
		
		
		
		vec4 mix=((vec4(r,g,b,1)*pow(bumpTexture,vec4(bump,bump,bump,bump))+texture-1));
		//gl_FragColor=(pow(bumpTexture,vec4(bump,bump,bump,bump))*vec4(r,g,b,1))+(vec4(r,g,b,1)*texture);
		
		//gl_FragColor=vec4(r,g,b,1)*(texture);

	}
	else{
		gl_FragColor=texture;
	}
	
	
}