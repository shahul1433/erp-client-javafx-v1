package erp.client.javafx.common;

import erp.client.javafx.container.tablewithnavigation.TopBar;
import erp.client.javafx.icon.FontAwsomeManager;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public abstract class AddEditRemoveTopBar extends TopBar {

    private Button add, edit, remove;

    protected abstract void setOnAction();

    @Override
    public void init() {
        super.init();

        add = new Button("\uf234");
        add.setFont(FontAwsomeManager.getSolidFontPlain(14));
        add.setTooltip(new Tooltip("Add User"));

        edit = new Button("\uf4ff");
        edit.setFont(FontAwsomeManager.getSolidFontPlain(14));
        edit.setTooltip(new Tooltip("Edit User"));
        edit.setDisable(true);

        remove = new Button("\uf1f8");
        remove.setFont(FontAwsomeManager.getSolidFontPlain(14));
        remove.setTooltip(new Tooltip("Remove User"));
        remove.setDisable(true);
    }

    @Override
    public void designGUI() {
        super.designGUI();

        HBox buttonPane = new HBox(5);
        buttonPane.getChildren().addAll(add, edit, remove);
        buttonPane.setPadding(new Insets(10));

        this.setLeft(buttonPane);
    }

    @Override
    public void registerListeners() {
        super.registerListeners();
        setOnAction();
    }

    public Button getAdd() {
        return add;
    }

    public Button getEdit() {
        return edit;
    }

    public Button getRemove() {
        return remove;
    }
}
