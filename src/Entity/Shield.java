package Entity;

import EntityBase.Entity;
import EntityBase.PowerUp;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Shield extends Entity implements PowerUp{
	
	
	public Shield() {
		this.x = 900;
		this.y = (int)(Math.random()*7)*60;
		this.radius = 20;
	}
	
	public void movement() {
		this.x -= 1.5;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.shield_item, x-radius, y-radius);
	}

	@Override
	public void powerup(Player player) {
		// TODO Auto-generated method stub
		player.hit();
		GameLogic.setSheid(true);
		this.destroyed = true; ;
	}

}
