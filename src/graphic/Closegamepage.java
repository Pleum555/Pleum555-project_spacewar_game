package graphic;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Closegamepage extends GridPane {
	public Button exit;
	
	public Closegamepage() {
		super();
		setAlignment(Pos.BOTTOM_RIGHT);
		setPadding(new Insets(25, 25, 25, 25));
		
		exit = new Button("Exit");
		exit.setFont(new Font("Roboto",24));
		exit.setPrefSize(120,40);
		exit.setVisible(false);
		
		add(exit, 0, 0);
	}
}
