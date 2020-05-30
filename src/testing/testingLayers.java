package testing;

import org.joml.Vector4f;

import niles.lwjgl.loop.Game;

public class testingLayers extends Game{
	
	public static void main(String[] args) {
		new testingLayers();
	}

	@Override
	public void setup() {
		addLayer(100);
		getLayer(0).addEntity(0, 0, 100, 100, new Vector4f(1), 0);
		
		addLayer(100);
		getLayer(1).addEntity(-200, 0, 60, 60, new Vector4f(1), 0);
		
	}

	@Override
	public void update() {
		render(0, true);
		render(1, true);
		
		System.out.println(getFps());
	}

}
