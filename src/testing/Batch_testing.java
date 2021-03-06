package testing;

import org.joml.Vector4f;
import static org.lwjgl.opengl.GL15.*;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.batch.ParticleSystem;
import niles.lwjgl.batch.Rect;
import niles.lwjgl.batch.Vao;
import niles.lwjgl.entites.Light;
import niles.lwjgl.loop.Game;
import niles.lwjgl.rendering.BatchRenderer;
import niles.lwjgl.util.Shader;
import niles.lwjgl.util.Texture;
import niles.lwjgl.world.Mouse;

public class Batch_testing extends Game{

	public static void main(String[] args) {
		new Batch_testing();
	}
	
	Batch objects;
	
	BatchRenderer renderer;
	
	Light lights;
	
	ParticleSystem system;
	
	
	@Override
	public void setup() {
		/*objects = new Batch(150000);
		
		for(int i = 0; i<150000; i++) {
			objects.addRect((float)(Math.random()*800*1)-400*1, (float)(Math.random()*800*1)-400*1, 4f, 4f, new Vector4f(1, 1, 1, 1), 0);
			objects.setColor(i, new Vector4f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()),1));
		}*/
		
		
		/*objects.addTexture(new Texture("res/wood_planks_old_0087_01.jpg"));
		objects.bindTextures();*/
		
		
		
		system = new ParticleSystem(0, 0, 0f, (float)(Math.PI/2), 1f, 60000);
		system.setLifeTime(30000);
		system.setStartColor(new Vector4f(0.4f, 0.4f, 0.7f, 0.4f));
		system.setEndColor(new Vector4f(1f, 0.8f, 0.6f, 0f));
		
		system.setStartSize(16);
		system.setEndSize(40);
		
		renderer = new BatchRenderer();
		
		lights = new Light();
		lights.addLight(400, -300, 10, 100, new Vector4f(1f,0.4f,0.4f,1));
		lights.addLight(100, 200, 40, 90, new Vector4f(0.3f,1f,1f,1));
		lights.addLight(-400, 600, 40, 90, new Vector4f(0.6f,0.6f,1f,1));
		lights.addLight(100, -1000, 80, 100, new Vector4f(0.4f,1f,0.2f,1));
		lights.addLight(-200, 500, 40, 120, new Vector4f(1f,0.3f,0.1f,1));
		
		
		
		getWindow().setVSync(false);
	}
	
	float angle = 0;
	
	@Override
	public void update() {
		//Mouse.moveCamera(getWindow(), getCamera(), 1);
		//Mouse.isVisible(getWindow(), false);
		renderer.bindShader();
		
		/*for(int i = 0; i<objects.size(); i++) {
			//objects.setColor(i, new Vector4f((float)(Math.random()),(float)(Math.random()),(float)(Math.random()),1));
			objects.setX(i, objects.getX(i) + (float)(Math.random()*8)-4);
			objects.setY(i, objects.getY(i) + (float)(Math.random()*8)-4);
		}*/
		
		/*objects.updateMax();
		
		renderer.useLights(getCamera(), lights);
		renderer.renderBatch(getCamera(), objects);*/
		
		system.applyForce((float)0, 0f, 0.2f);
		system.applypull(20000f, Mouse.getMousePosition(getWindow(), 1).x, -Mouse.getMousePosition(getWindow(), 1).y, 0.92, 10, false);
		system.update(600);
		
		system.mapColorToSpeed(new Vector4f(0.3f, 0.3f, 0.4f, 0.1f), new Vector4f(1f, 0.9f, 0.7f, 0.3f), 8f);
		renderer.renderParticles(getCamera(), system);
		
		
		
		setFpsCap(120);
		System.out.println(getWindow().getFps());
		
	}

}
