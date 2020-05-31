package testing;

import org.joml.Vector4f;

import niles.lwjgl.loop.Game;

public class testingLayers extends Game{
	
	public static void main(String[] args) {
		new testingLayers();
	}

	@Override
	public void setup() {
		addLayer(100, true);
		getLayer(0).addEntity(-100, 200, 300, 300, new Vector4f(1), 0);
		
		addLayer(100, false);
		getLayer(0).addEntity(-600, 200, 200, 200, new Vector4f(1), 0);
		
		addLight(400, 0, 100, 50, new Vector4f(1));
	}

	@Override
	public void update() {
		
		moveLightRelative(0, -1, 0, 0);
		
		
		
		render(0, true);
		
		
		render(1, true);
		
	}

}
