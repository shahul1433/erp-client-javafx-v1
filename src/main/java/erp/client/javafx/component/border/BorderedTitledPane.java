package erp.client.javafx.component.border;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class BorderedTitledPane extends StackPane{

	public BorderedTitledPane(String titleString, Pos titlePosition, Node content) {
		
		this.getStylesheets().add(BorderedTitledPane.class.getResource("style.css").toExternalForm());
		
		Label title = new Label(" " + titleString + " ");
		title.getStyleClass().add("bordered-titled-title");
		StackPane.setAlignment(title, titlePosition);
		
		StackPane contentPane = new StackPane();
	    content.getStyleClass().add("bordered-titled-content");
	    contentPane.getChildren().add(content);

	    getStyleClass().add("bordered-titled-border");
	    getChildren().addAll(title, contentPane);

	}
}
