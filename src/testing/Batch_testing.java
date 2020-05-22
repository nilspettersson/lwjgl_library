package testing;

import org.joml.Vector4f;

import niles.lwjgl.batch.Vao;
import niles.lwjgl.entites.Entity;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.entites.Rect;
import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Shader;

public class Batch_testing extends Game{

	public static void main(String[] args) {
		new Batch_testing();
	}
	Shader shader;
	Vao batch;
	
	@Override
	public void setup() {
		shader = new Shader("batch_shader");
		batch = new Vao(null, null, null);
	}

	@Override
	public void update() {
		shader.bind();
		
		
		batch.render();
		
		
	}

}
