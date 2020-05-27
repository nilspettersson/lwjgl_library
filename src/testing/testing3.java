package testing;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;

import org.joml.Vector2f;
import org.joml.Vector4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.loop.Game;
import niles.lwjgl.rendering.BatchRenderer;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.util.Shader;

public class testing3 extends Game {

	public static void main(String[] args) {
		new testing3();
	}
	
	ShadowModel shadow;

	int[] indices;
	float[] vertices;
	
	
	BatchRenderer renderer;
	Batch rect;
	
	
	
	@Override
	public void setup() {
		
		renderer = new BatchRenderer();
		
		rect = new Batch(1);
		rect.addRect(-400, 0, 100, 100, new Vector4f(1, 0, 0, 1), 0);
		
		
		
		
		
		/*vertices=new float[] {
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
				
		};*/
		
		vertices = new float[100];
		//indices = new int[100];
		
		indices=new int[] {
				/*0,1,2,
				2,3,0
				*/
				0, 2, 3,
				3, 1, 0
				
		};
		
		int index = 0;
		for(int i = 0; i < rect.size(); i++) {
			float[] temp = rect.getShadowFromPoint(i, new Vector2f(0, 0));
			vertices[index + 0] = temp[0];
			vertices[index + 1] = temp[1];
			vertices[index + 2] = temp[2];
			
			vertices[index + 3] = temp[3];
			vertices[index + 4] = temp[4];
			vertices[index + 5] = temp[5];
			
			vertices[index + 6] = temp[6];
			vertices[index + 7] = temp[7];
			vertices[index + 8] = temp[8];
			
			vertices[index + 9] = temp[9];
			vertices[index + 10] = temp[10];
			vertices[index + 11] = temp[11];
			index += 12;
			
		}
		
		

		
		shadow = new ShadowModel(vertices, indices);
		
		
		
		
		glBindBuffer(GL_ARRAY_BUFFER, shadow.getV_id());
		glBufferData(GL_ARRAY_BUFFER, vertices, GL_DYNAMIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, shadow.getI_id());
		glBufferData(GL_ARRAY_BUFFER, indices, GL_DYNAMIC_DRAW);
		
		
	}

	@Override
	public void update() {
		
		renderer.bindShader();
		
		rect.updateMax();
		
		renderer.renderBatch(getCamera(), rect);
		
		
		renderer.renderShadow(getCamera(), shadow);
		
		
	}

}



