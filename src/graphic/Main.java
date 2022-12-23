package graphic;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class Main extends Application {
	public void start(Stage primaryStage) {
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 900, 360);
		
		Canvas canvas = new Canvas(900, 360);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		gc.drawImage(RenderableHolder.home, 0, -50);
		
		Homepage firstpage = new Homepage();
		root.getChildren().add(firstpage);
		
		Howtoplaypage htoplay = new Howtoplaypage();
		
		firstpage.start.setOnMouseClicked((MouseEvent e) -> {
			
			Closegamepage closepage = new Closegamepage();
			closepage.exit.setOnMouseClicked((MouseEvent f) -> {
				primaryStage.close();
			});
			
			GameScreen gameScreen = new GameScreen(900, 360);
			GameLogic logic = new GameLogic();
			gc.setFill(Color.BLACK);
			gc.fillRect(0, 0, 900, 360);
			gc.drawImage(RenderableHolder.background, 0, 0);
			logic.WaveUpdate();
			logic.scoreUpdate();
			root.getChildren().remove(firstpage) ;
			root.getChildren().addAll(closepage, gameScreen);
			gameScreen.requestFocus();
			
			AnimationTimer animation = new AnimationTimer() {
				public void handle(long now) {
					if(!logic.isLose() && !RenderableHolder.bgSound.isPlaying()) RenderableHolder.bgSound.play();
					gameScreen.paintComponent();
					logic.logicUpdate();
					RenderableHolder.getInstance().update();
					if(logic.isLose() && !closepage.exit.isVisible()) {
						root.getChildren().remove(gameScreen);
						gc.setFill(Color.ANTIQUEWHITE);
						gc.setFont(Font.font("Loboto", FontWeight.LIGHT, 40));
						gc.fillText("My score : " + GameLogic.getScore(), 0, 40);
						gc.setFont(Font.font("Loboto", FontWeight.LIGHT, 80));
						gc.fillText("You have lose", 200, 220);
						closepage.exit.setVisible(true);
						RenderableHolder.bgSound.stop() ;
						
					} 
				}
			};
			animation.start();
			
		});
		
		firstpage.how.setOnMouseClicked((MouseEvent e) -> {
			root.getChildren().remove(firstpage);
			gc.setFill(Color.WHITESMOKE);
			gc.fillRect(80, 40, 740, 280);
			root.getChildren().add(htoplay);
			
			htoplay.close.setOnMouseClicked((MouseEvent f)-> {
				root.getChildren().remove(htoplay);
				gc.drawImage(RenderableHolder.home, 0, -50);
				root.getChildren().add(firstpage);
				
			});
		});
		
		firstpage.exit.setOnMouseClicked((MouseEvent e) -> {
			primaryStage.close();
		});
		
		primaryStage.setTitle("Space War");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
