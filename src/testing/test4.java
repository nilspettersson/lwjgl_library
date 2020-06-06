package testing;

import org.joml.Vector4f;

import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Mouse;

public class test4 extends Game{

	public static void main(String[] args) {
		new test4();
	}

	@Override
	public void setup() {
		addLayer(100, true);
		addLight(0, 0, 30, 40f, new Vector4f(1));
		
		getLayer(0).addTexture(new Texture("res/floor.png"));
		for(int i = 0; i < 100; i++) {
			getLayer(0).addEntity((float)(Math.random()*600)-800, (float)(Math.random()*600)+500, 1000, 1000, new Vector4f(1, 1, 1, 1), 0);
		}
		
		getWindow().setVSync(false);
	}

	@Override
	public void update() {
		Mouse.isVisible(getWindow(), false);
		Mouse.moveCamera(getWindow(), getCamera(), 1);
		getLayer(0).getBatch().bindTextures();
		render(0, true);
		
		System.out.println(getFps());
		setFpsCap(120);
	}

}
