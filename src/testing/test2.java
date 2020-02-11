package testing;

import org.joml.Vector4f;

import niles.lwjgl.loop.Game;

public class test2 extends Game{

	public test2(int width, int height, boolean fullsceen, Vector4f backgroundColor, int fps) {
		super(width, height, fullsceen, backgroundColor, fps);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new test2(1920, 1080, true, new Vector4f(0,0,0,1), 120);
	}

	
	Rect rect;
	
	@Override
	public void setup() {
		rect=new Rect();
		
	}

	@Override
	public void update() {
		rect.getGeometry().bindShader();
		rect.getGeometry().bindTexture();
		rect.getGeometry().render(getCamera(), rect.getPosition().getPosition());
	}

}
