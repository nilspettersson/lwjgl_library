package niles.lwjgl.rendering;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1iv;

import java.util.ArrayList;

import org.joml.Matrix4f;

import niles.lwjgl.batch.Batch;
import niles.lwjgl.batch.ParticleSystem;
import niles.lwjgl.entites.Light;
import niles.lwjgl.shadows.ShadowModel;
import niles.lwjgl.util.Shader;
import niles.lwjgl.world.Camera;

public class BatchRenderer {
	
	Shader shader;
	
	Shader shadowShader;
	
	public BatchRenderer() {
		shader = new Shader("batch_shader");
		shadowShader = new Shader("shadow_shader");
	}
	
	public void bindShader() {
		shader.bind();
		glUniform1iv(glGetUniformLocation(shader.getProgram(), "sampler"), new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
	}
	
	public void bindShadowShader() {
		shadowShader.bind();
		glUniform1iv(glGetUniformLocation(shader.getProgram(), "sampler"), new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
	}
	
	
	
	public void renderBatch(Camera camera, Batch batch) {
		
		shader.setUniform("projection", camera.getProjection());
		batch.getVao().render();
	}
	
	public void renderShadow(Camera camera, ShadowModel shadow) {
		bindShadowShader();
		
		shadowShader.setUniform("projection", camera.getProjection());
		shadow.render();
	}
	
	public void renderParticles(Camera camera, ParticleSystem particles) {
		shader.setUniform("projection", camera.getProjection());
		particles.getParticles().updateMax();
		particles.getParticles().getVao().render();
	}
	
	public void useLights(Camera camera,Light lights) {
		ArrayList<Matrix4f>positionsRelative=new ArrayList<Matrix4f>();
		
		for(int i=0;i<lights.getPositions().size();i++) {
			positionsRelative.add(new Matrix4f(lights.getPositions().get(i).m00, lights.getPositions().get(i).m01, lights.getPositions().get(i).m02, lights.getPositions().get(i).m03, lights.getPositions().get(i).m10, lights.getPositions().get(i).m11, lights.getPositions().get(i).m12, lights.getPositions().get(i).m13, lights.getPositions().get(i).m20, lights.getPositions().get(i).m21, lights.getPositions().get(i).m22, lights.getPositions().get(i).m23, lights.getPositions().get(i).m30, lights.getPositions().get(i).m31, lights.getPositions().get(i).m32, lights.getPositions().get(i).m33));
			positionsRelative.get(i).m00+=camera.getProjection().m30*1920/2 + 1920 / 2;
			positionsRelative.get(i).m01+=camera.getProjection().m31*1080/2 + 1080 / 2;
		}
		
		shader.setUniform("lights", positionsRelative);
		shader.setUniform("size", lights.getPositions().size());
		
	}
	
	public void resetLights() {
		shader.setUniform("size", 0);
	}

}
