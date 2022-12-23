package EntityBase;

import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{

	protected int radius;
	protected double x,y;
	protected boolean visible,destroyed;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	@Override
	public boolean isDestroyed(){
		return destroyed;
	}
	
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	@Override
	public boolean isVisible(){
		return visible;
	}
	
	public boolean collideWith(Entity other){
		return Math.hypot(this.x-other.x, this.y-other.y) <= this.radius+other.radius;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	
	
}
