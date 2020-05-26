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
	
	private Vector4f startColor;
	private Vector4f endColor;
	
	private float startSize;
	private float endSize;
	
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
		
		startColor = new Vector4f(1);
		endColor = new Vector4f(1);
		
		startSize = 4;
		endSize = 4;
		
		for(int i = 0; i < particleAmount; i++) {
			particles.addRect(x, y, startSize, startSize, new Vector4f(0), 0);
			
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
				particles.setColor(i, startColor);
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
				
				setColor(i);
				setSize(i);

			}
			if(particleData[i].w > lifeTime) {
				particleData[i].w = 0;
			}
		}
		
		
	}
	
	private void setColor(int i) {
		float life = particleData[i].w / lifeTime;

		float r;
		float g;
		float b;
		float a;
		if(startColor.x < endColor.x) {
			float rate = Math.max(startColor.x, endColor.x) - Math.min(startColor.x, endColor.x);
			r =Math.min(startColor.x, endColor.x) + life * rate;
		}
		else {
			float rate = Math.max(startColor.x, endColor.x) - Math.min(startColor.x, endColor.x);
			r =Math.min(startColor.x, endColor.x) + (1 - life ) * rate;
		}
		if(startColor.y < endColor.y) {
			float rate = Math.max(startColor.y, endColor.y) - Math.min(startColor.y, endColor.y);
			g =Math.min(startColor.y, endColor.y) + life * rate;
		}
		else {
			float rate = Math.max(startColor.y, endColor.y) - Math.min(startColor.y, endColor.y);
			g =Math.min(startColor.y, endColor.y) + (1 - life ) * rate;
		}
		if(startColor.z < endColor.z) {
			float rate = Math.max(startColor.z, endColor.z) - Math.min(startColor.z, endColor.z);
			b =Math.min(startColor.z, endColor.z) + life * rate;
		}
		else {
			float rate = Math.max(startColor.z, endColor.z) - Math.min(startColor.z, endColor.z);
			b =Math.min(startColor.z, endColor.z) + (1 - life ) * rate;
		}
		if(startColor.w < endColor.w) {
			float rate = Math.max(startColor.w, endColor.w) - Math.min(startColor.w, endColor.w);
			a =Math.min(startColor.w, endColor.w) + life * rate;
		}
		else {
			float rate = Math.max(startColor.w, endColor.w) - Math.min(startColor.w, endColor.w);
			a =Math.min(startColor.w, endColor.w) + (1 - life ) * rate;
		}
		
		
		particles.setColor(i, new Vector4f(r, g, b, a));
	}
	
	private void setSize(int i) {
		//System.out.println(particles.getWidth(i));
		float life = particleData[i].w / lifeTime;

		float size;
		if(startSize < endSize) {
			float rate = Math.max(startSize, endSize) - Math.min(startSize, endSize);
			size =Math.min(startSize, endSize) + life * rate;
		}
		else {
			float rate = Math.max(startSize, endSize) - Math.min(startSize, endSize);
			size =Math.min(startSize, endSize) + (1 - life ) * rate;
		}

		particles.setWidth(i, size);
		particles.setHeight(i, size);
	}
	
	
	public void mapColorToSpeed(Vector4f startColor, Vector4f endColor, float maxSpeed) {
		for(int i = 0; i < particleData.length; i++) {
			if(particleData[i].w != 0) {
				
				double dX = particleData[i].x;
				double dY = particleData[i].y;
				double dis = Math.sqrt((dX * dX) + (dY * dY));
				
				float speed = (float) (dis / maxSpeed);

				float r;
				float g;
				float b;
				float a;
				if(startColor.x < endColor.x) {
					float rate = Math.max(startColor.x, endColor.x) - Math.min(startColor.x, endColor.x);
					r =Math.min(startColor.x, endColor.x) + speed * rate;
				}
				else {
					float rate = Math.max(startColor.x, endColor.x) - Math.min(startColor.x, endColor.x);
					r =Math.min(startColor.x, endColor.x) + (1 - speed ) * rate;
				}
				if(startColor.y < endColor.y) {
					float rate = Math.max(startColor.y, endColor.y) - Math.min(startColor.y, endColor.y);
					g =Math.min(startColor.y, endColor.y) + speed * rate;
				}
				else {
					float rate = Math.max(startColor.y, endColor.y) - Math.min(startColor.y, endColor.y);
					g =Math.min(startColor.y, endColor.y) + (1 - speed ) * rate;
				}
				if(startColor.z < endColor.z) {
					float rate = Math.max(startColor.z, endColor.z) - Math.min(startColor.z, endColor.z);
					b =Math.min(startColor.z, endColor.z) + speed * rate;
				}
				else {
					float rate = Math.max(startColor.z, endColor.z) - Math.min(startColor.z, endColor.z);
					b =Math.min(startColor.z, endColor.z) + (1 - speed ) * rate;
				}
				if(startColor.w < endColor.w) {
					float rate = Math.max(startColor.w, endColor.w) - Math.min(startColor.w, endColor.w);
					a =Math.min(startColor.w, endColor.w) + speed * rate;
				}
				else {
					float rate = Math.max(startColor.w, endColor.w) - Math.min(startColor.w, endColor.w);
					a =Math.min(startColor.w, endColor.w) + (1 - speed ) * rate;
				}
				
				
				particles.setColor(i, new Vector4f(r, g, b, a));
				
				
				
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
	
	public void applypull(double speed1 , double x, double y, double drag, double maxSpeed, boolean constant) {
		for(int i = 0; i < particleData.length; i++) {
			if(particleData[i].w != 0) {
				double difX = particles.getX(i) - x;
				double difY = particles.getY(i) - y;
				double dis = Math.sqrt((difX * difX) + (difY * difY));
				
				double angle = Math.atan(difY / difX);
				
				/*if(dis <= minDis) {
					continue;
				}*/
				float speed;
				if(constant) {
					speed =(float) (speed1 / (40));
				}
				else {
					speed =(float) (speed1 / (dis * dis));
				}
				
				if(x < particles.getX(i)) {
					angle = angle + Math.PI;
				}
				
				if(speed > maxSpeed) {
					speed = (float) maxSpeed;
				}
				
				float tx = (float) ((Math.cos(angle) * speed));
				float ty = (float) ((Math.sin(angle) * speed));
				
				particleData[i].x += tx;
				particleData[i].y += ty;
				
				particleData[i].x *= drag;
				particleData[i].y *= drag;
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


	public Vector4f getStartColor() {
		return startColor;
	}


	public void setStartColor(Vector4f startColor) {
		this.startColor = startColor;
	}


	public Vector4f getEndColor() {
		return endColor;
	}


	public void setEndColor(Vector4f endColor) {
		this.endColor = endColor;
	}


	public float getStartSize() {
		return startSize;
	}


	public void setStartSize(float startSize) {
		this.startSize = startSize;
	}


	public float getEndSize() {
		return endSize;
	}


	public void setEndSize(float endSize) {
		this.endSize = endSize;
	}


	public float getX() {
		return x;
	}


	public void setX(float x) {
		this.x = x;
	}


	public float getY() {
		return y;
	}


	public void setY(float y) {
		this.y = y;
	}
	
	
	
	
	

}
