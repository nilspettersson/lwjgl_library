package testing;

import org.joml.Vector2f;
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
		addLight(0, 0, 30, 40f, new Vector4f(1));
		
		addLayer(10000, true);
		addLayer(1, true);
		
		getLayer(0).addTexture(new Texture("res/floor.png"));
		for(int i = 0; i < 10; i++) {
			getLayer(0).addEntity((float)(Math.random()*1000)-500, (float)(Math.random()*1000)-500, 100, 100, new Vector4f(0, 0, 0, 0), 0);
		}
		generateShadows(0, new Vector2f(0), 40);
		
		getLayer(1).addEntity(0-1000, +1000, 2000, 2000, new Vector4f(0.6f, 0.6f, 0.6f, 1), 1);
		
		getWindow().setVSync(false);
	}

	@Override
	public void update() {
		//Mouse.isVisible(getWindow(), false);
		//Mouse.moveCamera(getWindow(), getCamera(), 1);
		
		
		render(1, true);
		renderShadows(0);
		getLayer(0).bindTextures();
		render(0, true);
		
		
		System.out.println(getFps());
		setFpsCap(120);
	}

}
