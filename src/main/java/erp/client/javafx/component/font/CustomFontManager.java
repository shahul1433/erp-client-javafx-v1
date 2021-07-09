package erp.client.javafx.component.font;

import erp.client.javafx.utility.PopupUtility;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import org.apache.log4j.Logger;

import java.io.InputStream;

public class CustomFontManager {

    private static final Logger logger = Logger.getLogger(CustomFontManager.class);

    /**
     *
     * @param size
     * @return Roboto font with given size
     */
    public Font getRobotoFont(double size) {
        Font font = null;
        try {
            InputStream inputStream = CustomFontManager.class.getResourceAsStream("/font/Roboto-Regular.ttf");
            font = Font.loadFont(inputStream, size);
        } catch (Exception e) {
            logger.error(e.getMessage());
            PopupUtility.showMessage(Alert.AlertType.ERROR, e.getMessage());
            e.printStackTrace();
        }
        return font;
    }
}
