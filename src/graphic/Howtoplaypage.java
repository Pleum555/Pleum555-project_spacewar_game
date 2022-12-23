package graphic;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Howtoplaypage extends GridPane{
	public Button close;
	
	public Howtoplaypage() {
		super();
		setAlignment(Pos.CENTER);
		
		Label topic = new Label("How To Play");
		topic.setFont(new Font("Roboto", 50));
		topic.setTextFill(Color.DARKGOLDENROD);
		add(topic, 0, 0);
		
		Label description1 = new Label("Control movement with WASD button.");
		description1.setFont(new Font("Roboto", 20));
		description1.setTextFill(Color.CADETBLUE);
		add(description1, 0, 1);
		Label description2 = new Label("\"Enter\" for shoot laser to destroy the meteorite (only 1 time)");
		description2.setFont(new Font("Roboto", 20));
		description2.setTextFill(Color.CADETBLUE);
		add(description2, 0, 2);
		Label description3 = new Label("Do not hit the meteorite! Do your best.");
		description3.setFont(new Font("Roboto", 20));
		description3.setTextFill(Color.RED);
		add(description3, 0, 3);
		
		close = new Button("Close");
		close.setFont(new Font("Roboto",24));
		close.setPrefSize(120,40);
		
		HBox hbtn = new HBox();
		hbtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbtn.getChildren().add(close);
		add(hbtn, 5, 6);
	}
}
