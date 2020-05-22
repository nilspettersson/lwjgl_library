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

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		
		Shader shader = new Shader("batch_shader");
		shader.bind();
		
		Vao batch = new Vao(null, null, null);
		batch.render();
		
		
	}

}
