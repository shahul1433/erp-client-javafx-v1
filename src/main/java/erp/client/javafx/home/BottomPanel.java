package erp.client.javafx.home;

import erp.client.javafx.component.label.CLabel;
import erp.client.javafx.layout.AbstractBorderPane;

public class BottomPanel extends AbstractBorderPane {

    CLabel copyright;

    @Override
    public void init() {
        this.getStylesheets().add(BottomPanel.class.getResource("home.css").toExternalForm());
        this.setId("bottom-panel");
        copyright = new CLabel("© 2021, all rights are reserved. The product is activated with valid license.");
    }

    @Override
    public void designGUI() {
        this.setCenter(copyright);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public boolean checkSecurity() {
        return false;
    }
}
