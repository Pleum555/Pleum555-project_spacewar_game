package Entity;

import EntityBase.Entity;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import sharedObject.RenderableHolder;

public class Laser extends Entity {
	
	public Laser(Player player) {
		this.x = player.getX();
		this.y = player.getY();
		this.radius = 10;
		visible = false ;
	}
	
	public void meteor_hit(Meteorite meteorite) {
		RenderableHolder.explosionSound.play();
		meteorite.setDestroyed(true);
		this.setDestroyed(true);
		visible = false ;
	}
	
	public void movement() {
		this.x += 5;
	}
	
	public void update(Player player) {
		if(!isVisible()) {
			this.x = player.getX();
			this.y = player.getY();
		}
		if (InputUtility.getKeyPressed(KeyCode.ENTER)) {
			visible = true ;
		}
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		gc.drawImage(RenderableHolder.laser, x-radius, y-radius);
	}

}
