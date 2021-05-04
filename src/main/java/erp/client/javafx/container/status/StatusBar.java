package erp.client.javafx.container.status;

import javafx.geometry.Insets;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StatusBar extends GridPane{

	private Rectangle rectangle;
	private ProgressBar progressBar;
	private Text label;

	public StatusBar() {
		setHgap(5);
		setVgap(5);
		setPadding(new Insets(3));
		
		ColumnConstraints emptyColumn = new ColumnConstraints();
		ColumnConstraints statusBarColumn = new ColumnConstraints(100, 150, Double.MAX_VALUE);
		statusBarColumn.setHgrow(Priority.ALWAYS);
		
		getColumnConstraints().add(emptyColumn);
		getColumnConstraints().add(statusBarColumn);
		getColumnConstraints().add(emptyColumn);
		
		init();
		
		setStatus(StatusBarStatus.READY);
		setStyle("-fx-border-color: black");
	}
	
	public ProgressBar getProgressBar() {
		return progressBar;
	}

	private void init() {
		rectangle = new Rectangle(13, 13);
		rectangle.setFill(Color.YELLOW);
		
		progressBar = new ProgressBar();
		progressBar.setPrefWidth(Double.MAX_VALUE);
		progressBar.setVisible(false);
		
		label = new Text(StatusBarStatus.READY.getStatus());
		
		designStatusBar();
	}
	
	private void designStatusBar() {
		GridPane.setConstraints(rectangle, 0, 0);
		GridPane.setConstraints(progressBar, 1, 0);
		GridPane.setConstraints(label, 2, 0);
		
		getChildren().addAll(rectangle, progressBar, label);
	}
	
	public void setStatus(StatusBarStatus status) {
		switch (status) {
		case WORKING:
			label.setText(StatusBarStatus.WORKING.getStatus());
			rectangle.setFill(Color.YELLOW);
			progressBar.setVisible(true);
			break;
		case READY:
			label.setText(StatusBarStatus.READY.getStatus());
			rectangle.setFill(Color.GREEN);
			progressBar.setVisible(false);
			break;
		case ERROR:
			label.setText(StatusBarStatus.ERROR.getStatus());
			rectangle.setFill(Color.RED);
			progressBar.setVisible(false);
			break;
		default:
			break;
		}
	}
}
