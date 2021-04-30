package erp.client.javafx.icon;

import java.io.InputStream;

import javafx.scene.text.Font;

public class FontAwsomeManager {

	/**
	 * @return the solidFont
	 */
	public static Font getSolidFontPlain(double size) {
		Font font = null;
		try {
			InputStream inputStreamSolid = FontAwsomeManager.class.getResourceAsStream("/font/fa-solid-900.ttf");
			font = Font.loadFont(inputStreamSolid, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}

	/**
	 * @return the regularFont
	 */
	public static Font getRegularFontPlain(double size) {
		Font font = null;
		try {
			InputStream inputStreamRegular = FontAwsomeManager.class.getResourceAsStream("/font/fa-regular-400.ttf");
			font = Font.loadFont(inputStreamRegular, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}

	/**
	 * @return the brandsFont
	 */
	public static Font getBrandsFontPlain(double size) {
		Font font = null;
		try {
			InputStream inputStreamBrands = FontAwsomeManager.class.getResourceAsStream("/font/fa-brands-400.ttf");
			font = Font.loadFont(inputStreamBrands, size);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return font;
	}
	
}
