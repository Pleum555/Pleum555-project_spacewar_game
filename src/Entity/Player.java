package Entity;

import EntityBase.Entity;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Player extends Entity {

//--------------------------------------------
	private static final int speed = 5;
	
	private boolean flashing = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;

	public Player(double x, double y) {
		this.x = x;
		this.y = y;
		this.radius = 40;
	}

	private void move(int angle) {
		if(x < 0 ) {
			x = 0 ;
		} else if(x > 900) {
			x = 900 ;
		} else if(y < 0) {
			y = 0 ;
		} else if(y > 360) {
			y = 360 ;
		}
			this.x += Math.cos(Math.toRadians(angle)) * speed;
			this.y += Math.sin(Math.toRadians(angle)) * speed;
	}

	public void hit() {
		flashing = true;
		flashCounter = 5;
		flashDurationCounter = 10;
	}

	public void update() {
		if (flashing) {
			if (flashCounter == 0) {
				this.visible = true;
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					this.visible = false;
					flashDurationCounter--;
				} else {
					this.visible = true;
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		}
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			move(270);
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			move(180);
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			move(90);
		}if (InputUtility.getKeyPressed(KeyCode.D)) {
			move(0);
		}
	}

	
	@Override
	public void draw(GraphicsContext gc) {
		if(GameLogic.isShield()) {
			gc.drawImage(RenderableHolder.shield, x-radius-12, y-radius-16);
		}
		gc.drawImage(RenderableHolder.plane, x-radius, y-radius);
	}

}
