package testing;

import org.joml.Vector2f;
import org.joml.Vector4f;

import niles.lwjgl.loop.Game;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Input;
import niles.lwjgl.world.Mouse;

public class test4 extends Game{

	public static void main(String[] args) {
		new test4();
	}

	@Override
	public void setup() {
		addLight(0, 0, 60, 400f, new Vector4f(1));
		
		addLayer(100000, true);
		addLayer(1, true);
		
		getLayer(0).addTexture(new Texture("res/floor.png"));
		for(int i = 0; i < 100; i++) {
			getLayer(0).addEntity((float)(Math.random()*1000)-500, (float)(Math.random()*1000)-500, 20, 20, new Vector4f(0, 0, 0, 0), 0);
		}
		
		
		getLayer(1).addEntity(0-1000, +1000, 2000, 2000, new Vector4f(0.6f, 0.6f, 0.6f, 1), 1);
		
		
		addLayer(100, false);
		CreateParticleSystem(2, 0, 0, 1, (float)(Math.random()*Math.PI * 2), 1, 50000);
		getLayer(2).getParticleSystem().setLifeTime(180);
		getLayer(2).getParticleSystem().setStartSize(10);
		getLayer(2).getParticleSystem().setEndSize(0.1f);
		getLayer(2).getParticleSystem().setStartColor(new Vector4f(0.6f,0.6f,1,0.8f));
		getLayer(2).getParticleSystem().setEndColor(new Vector4f(1,0.2f,0.2f,0.4f));
		
		
		getWindow().setVSync(false);
	}

	@Override
	public void update() {
		//Mouse.isVisible(getWindow(), false);
		//Mouse.moveCamera(getWindow(), getCamera(), 1);
		Vector2f p = Mouse.getMousePosition(getWindow(), 1);
		getLights().setX(0, p.x);
		getLights().setY(0, -p.y);
		
		render(1, true);
		
		generateShadows(0, new Vector2f(getLights().getX(0), getLights().getY(0)), 0.08f);
		renderShadows(0);
		
		getLayer(0).bindTextures();
		render(0, true);
		
		
		getLayer(2).getParticleSystem().applyForce(0, 0, 0.4f);
		getLayer(2).getParticleSystem().setInitAngle((float)(Math.random()*Math.PI * 2));
		getLayer(2).getParticleSystem().update(200);
		renderParticleSystem(2);
		
		System.out.println(getFps());
		setFpsCap(120);
	}

}
