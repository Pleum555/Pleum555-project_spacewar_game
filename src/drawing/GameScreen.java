package drawing;

import java.util.ConcurrentModificationException;

import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.input.KeyEvent;


public class GameScreen extends Canvas {

	public GameScreen(double width, double height) {
		super(width, height);
		addListener();
	}

	public void addListener() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});
		this.setOnKeyReleased((KeyEvent event) -> {
			
			InputUtility.setKeyPressed(event.getCode(), false);
		});
	}

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.clearRect(0, 0,900, 360);
		gc.setFill(Color.RED);
		gc.setFont(Font.font("Loboto", FontWeight.LIGHT, 20));
		gc.fillText("My score : " + GameLogic.getScore(), 0, 20);
		try {
			for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
				if (entity.isVisible() && !entity.isDestroyed()) {
					entity.draw(gc);
				}
			}
		} catch(ConcurrentModificationException e) {}
	}

}
