package Entity;

import EntityBase.Entity;
import EntityBase.PowerUp;
import javafx.scene.canvas.GraphicsContext;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Point extends Entity implements PowerUp {
	
	private boolean turn ;
	
	public Point() {
		turn = false ;
		this.x = 900;
		this.y = (int)(Math.random()*7)*60;
		this.radius = 20;
	}

	public void movement() {
		if(!isTurn()) {
			this.x -= 2;
			this.y -= 2;
		} else {
			this.x -= 2;
			this.y += 2;
		}
		if(y < 0) {
			setTurn(true);
		} else if(y > 360) {
			setTurn(false);
		}
		
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(RenderableHolder.point_item, x-radius, y-radius);
	}

	@Override
	public void powerup(Player player) {
		// TODO Auto-generated method stub
		this.destroyed = true;
		GameLogic.addScore(50);
	}
	public boolean isTurn() {
		return turn;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}

	
	
}
