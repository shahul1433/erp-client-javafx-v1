package erp.client.javafx.component.label;

import erp.client.javafx.component.font.CustomFontManager;
import javafx.scene.control.Label;

public class CLabel extends Label {

    public CLabel() {
        this(12);
    }

    public CLabel(String name) {
        this(12);
        setText(name);
    }

    public CLabel(double size) {
        setFont(new CustomFontManager().getRobotoFont(size));
    }

    public CLabel(String name, double size) {
        this(size);
        setText(name);
    }
}
