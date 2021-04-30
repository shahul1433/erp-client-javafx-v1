package erp.client.javafx.utility;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GuiUtility {

	public static double MAXIMUM = 0.8;
	public static double MEDIUM = 0.7;
	public static double MINIMUM = 0.4;
	
	public static Rectangle getBoundsForComponent(Dimension containerDimension) {
		Dimension deviceDim = Toolkit.getDefaultToolkit().getScreenSize();
		int fromX;
		int fromY;
		if(deviceDim.width > containerDimension.width)
			fromX = (deviceDim.width/2) - (containerDimension.width/2);
		else
			fromX = 0;
		if(deviceDim.height > containerDimension.height)
			fromY = (deviceDim.height/2) - (containerDimension.height/2);
		else
			fromY = 0;
		return new Rectangle(fromX, fromY, containerDimension.width, containerDimension.height);
	}
	
	public static Dimension setSize(double size) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.height * size);
		int width = (int) (screenSize.width * size);
		return new Dimension(width, height);
	}
	
	public static Dimension setExtendedState() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = screenSize.height;
		int width =  screenSize.width;
		return new Dimension(width, height);
	}
	
	public static Dimension minimumSize() {
		return setSize(MINIMUM);
	}
	
	public static Dimension mediumSize() {
		return setSize(MEDIUM);
	}
	
	public static Dimension maximumSize() {
		return setSize(MAXIMUM);
	}
	
	public static Double getScreenWidth() {
		Dimension dimension = setExtendedState();
		return dimension.getWidth();
	}
	
	public static Double getScreenHeight() {
		Dimension dimension = setExtendedState();
		return dimension.getHeight();
	}
	
	public static void alignDialogToCenter(Stage stage) {
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primaryScreenBounds.getWidth() - stage.getWidth())/2);
		stage.setY((primaryScreenBounds.getHeight() - stage.getHeight())/2);
	}
	
}
