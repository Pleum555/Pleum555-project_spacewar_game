package Entity;

import EntityBase.Enemy;
import EntityBase.Entity;
import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Meteorite extends Entity implements Enemy{
	
	public Meteorite() {
		this.x = 900;
		this.y = (int)(Math.random()*7)*60;
		this.radius = 20;
	}
	
	public void damage(Player player){
		player.hit();
		RenderableHolder.explosionSound.play();
		setDestroyed(true);
	}
	
	public void movement(double d) {
		this.x -= 1*d;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.meteorite, x-radius, y-radius);
	}

}