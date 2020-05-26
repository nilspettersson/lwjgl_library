package testing;

import niles.lwjgl.loop.Game;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.util.Shader;

public class testing3 extends Game {

	public static void main(String[] args) {
		new testing3();
	}
	
	Shader shader;
	ShadowModel model;

	@Override
	public void setup() {
		shader = new Shader("shadow_shader");
		
		float[] vertices=new float[] {
				0f,0f,0,
				
				-0.1f,0.6f,0,
				0.1f,0.6f,0,
				
				-0.1f,-0.6f,0,
				0.1f,-0.6f,0,
				
				0.2f,0.5f,0,
				
		};		
		
		int[] indices=new int[] {
				0,1,2,
				
				0,3,4,
				
				0,2,5
				
		};
		
		model = new ShadowModel(vertices, indices);
	}

	@Override
	public void update() {
		
		shader.bind();
		
		model.render();
		
		
	}

}



