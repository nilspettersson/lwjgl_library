package testing;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;

import niles.lwjgl.loop.Game;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.util.Shader;

public class testing3 extends Game {

	public static void main(String[] args) {
		new testing3();
	}
	
	Shader shader;
	ShadowModel model;

	int[] indices;
	float[] vertices;
	
	@Override
	public void setup() {
		shader = new Shader("shadow_shader");
		
		vertices=new float[] {
				0f,0f,0,
				
				-0.1f,0.6f,0,
				0.1f,0.6f,0,
				
				-0.1f,-0.6f,0,
				0.1f,-0.6f,0,
				
				0.2f,0.5f,0,
				
		};		
		
		indices=new int[] {
				0,1,2,
				
				0,3,4,
				
				0,2,5
				
		};
		

		
		model = new ShadowModel(vertices, indices);
		
		
		
		
		glBindBuffer(GL_ARRAY_BUFFER, model.getV_id());
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, model.getI_id());
		glBufferData(GL_ARRAY_BUFFER, indices, GL_DYNAMIC_DRAW);
		
		
	}

	@Override
	public void update() {
		
		
		
		shader.bind();
		
		model.render();
		
		
	}

}



