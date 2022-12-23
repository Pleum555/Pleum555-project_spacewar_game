package graphic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Homepage extends GridPane {
	public Button start;
	public Button how;
	public Button exit;
	
	public Homepage() {
		super();
		setAlignment(Pos.CENTER);
		setPadding(new Insets(25, 25, 25, 25));
		setHgap(10);
		setVgap(10);
		
		Label topic = new Label("SPACE WAR");
		topic.setTextFill(Color.SKYBLUE);
		topic.setFont(new Font("Roboto", 60));
		add(topic, 0, 0);
		
		start = new Button("Start");
		start.setFont(new Font("Roboto",24));
		start.setPrefSize(120,40);
		add(start, 0, 2);
		
		how = new Button("How to play");
		how.setFont(new Font("Roboto",24));
		how.setPrefSize(200,40);
		add(how, 0, 1); 
		
		exit = new Button("Exit");
		exit.setFont(new Font("Roboto",24));
		exit.setPrefSize(120,40);
		add(exit, 1, 2);
		
	}
}
