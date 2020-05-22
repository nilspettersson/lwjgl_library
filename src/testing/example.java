package testing;

import org.joml.Vector4f;

import niles.lwjgl.entites.Entity;
import niles.lwjgl.entites.Material;
import niles.lwjgl.entites.Position;
import niles.lwjgl.entites.Rect;
import niles.lwjgl.loop.Game;
import niles.lwjgl.world.Mouse;

public class example extends Game {

	public static void main(String[] args) {
		new example();
	}
	
	Rect rect;
	Mouse mouse;
	@Override
	public void setup() {
		mouse=new Mouse();
		
		setBackgroundColor(new Vector4f(1));
		
		rect=new Rect(new Position(0, 0, 100), new Material(new Vector4f(0,0,0,1), 1, 1));
	}

	
	@Override
	public void update() {
		mouse.moveCamera(getWindow(), getCamera(), 1);
		getRenderer().bindShader();
		getRenderer().init();
		getRenderer().render(getCamera(), rect);
		
		System.out.println(getWindow().getFps());
	}

}
