package niles.lwjgl.batch;

import org.joml.Vector4f;

public class ParticleSystem {
	
	private float x;
	private float y;
	private int particleAmount;
	
	private float initSpeed;
	private float initAngle;
	private float initRandVel;
	
	private int lifeTime;
	
	private Batch particles;
	
	private Vector4f[] particleData;
	
	public ParticleSystem(float x, float y, float initSpeed, float initAngle, float initRandVel, int particleAmount) {
		this.x = x;
		this.y = y;
		this.particleAmount = particleAmount;
		
		
		this.initSpeed = initSpeed;
		this.initAngle = initAngle;
		this.initRandVel = initRandVel;
		
		lifeTime = 500;
		
		
		particles = new Batch(particleAmount);
		
		particleData = new Vector4f[particleAmount];
		
		for(int i = 0; i < particleAmount; i++) {
			particles.addRect(x, y, 1, 1, new Vector4f(0), 0);
			
			//double angle = (double)(Math.random() * Math.PI * 2);
			//float speed = (float)(((Math.random() * initRandVel)-initRandVel / 2));
			//float tx = (float) ((Math.cos(initAngle) * initSpeed) + Math.cos(angle) * speed);
			//float ty = (float) ((Math.sin(initAngle) * initSpeed) + Math.sin(angle) * speed);
			
			/*float tx = initXVel + (float)(Math.random() * initRandVel)-initRandVel / 2;
			float ty = initYVel + (float)(Math.random() * initRandVel)-initRandVel / 2;*/
			particleData[i] = new Vector4f(0, 0, 0, 0);
			
		}

	}
	
	
	public void update(int spawnRate) {
		
		if(spawnRate > particleAmount) {
			spawnRate = particleAmount;
		}
		
		int created = 0;
		for(int i = 0; i < particleAmount; i++) {
			
			
			if(particleData[i].w == 0) {
				
				double angle = (double)(Math.random() * Math.PI * 2);
				float speed = (float)(((Math.random() * initRandVel)-initRandVel / 2));
				float tx = (float) ((Math.cos(initAngle) * initSpeed) + Math.cos(angle) * speed);
				float ty = (float) ((Math.sin(initAngle) * initSpeed) + Math.sin(angle) * speed);
				
				particleData[i] = new Vector4f(tx, ty, 0, 0);
				
				
				
				particles.setX(i, x);
				particles.setY(i, y);
				particles.setColor(i, new Vector4f(1));
				particleData[i].w += 1;
				i++;
				created++;
			}
			
			if(created == spawnRate) {
				break;
			}
			
		}
		for(int i = 0; i < particleAmount; i++) {
			
			if(particleData[i].w != 0) {
				particleData[i].w++;
				particles.setX(i, particles.getX(i) + particleData[i].x);
				particles.setY(i, particles.getY(i) + particleData[i].y);
			}
			if(particleData[i].w > lifeTime) {
				particleData[i].w = 0;
			}
		}
		
		
	}
	
	
	public void applyForce(float angle, float speed, float random) {
		for(int i = 0; i < particleData.length; i++) {
			if(particleData[i].w != 0) {
				float tx = (float) ((Math.cos(angle) * speed));
				float ty = (float) ((Math.sin(angle) * speed));
				particleData[i].x +=tx + (float)(((Math.random() * random)-random / 2));
				particleData[i].y +=ty + (float)(((Math.random() * random)-random / 2));
			}
		}
	}

	
	
	public float getInitRandVel() {
		return initRandVel;
	}

	public void setInitRandVel(float initRandVel) {
		this.initRandVel = initRandVel;
	}


	public Batch getParticles() {
		return particles;
	}


	public void setParticles(Batch particles) {
		this.particles = particles;
	}


	public int getLifeTime() {
		return lifeTime;
	}


	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}


	public float getInitSpeed() {
		return initSpeed;
	}


	public void setInitSpeed(float initSpeed) {
		this.initSpeed = initSpeed;
	}


	public float getInitAngle() {
		return initAngle;
	}


	public void setInitAngle(float initAngle) {
		this.initAngle = initAngle;
	}
	
	
	
	
	

}
