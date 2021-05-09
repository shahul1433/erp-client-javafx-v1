package erp.client.javafx.login;

import erp.client.javafx.component.event.popup.PopupEvent;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.icon.FontAwsomeManager;
import erp.client.javafx.layout.AbstractGridPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoginPane extends AbstractGridPane {

    //Components
    Label banner;
    ImageView logo;
    Label usernameLb, passwordLb;
    TextField username;
    PasswordField password;
    Button login;
    ProgressIndicator progressIndicator;

    LoginService loginService;

    public LoginPane() {
        super();
        this.loginService = new LoginService(this);
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25));
    }

    @Override
    public void init() {
        this.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        banner = new Label("Login");
        banner.setId("banner");

        logo = new ImageView(new Image(getClass().getResourceAsStream("/image/dolphin.png")));
        logo.setFitHeight(70);
        logo.setFitWidth(70);
        Reflection reflection = new Reflection();
        logo.setEffect(reflection);

        usernameLb = new Label("Username");
        usernameLb.setId("usernameLb");

        passwordLb = new Label("Password");
        passwordLb.setId("passwordLb");

        username = new TextField();
        username.setId("username");

        password = new PasswordField();
        password.setId("password");

        login = new Button("\uf2f6");
        login.setFont(FontAwsomeManager.getSolidFontPlain(16));
        login.setTooltip(new Tooltip("Login"));

        progressIndicator = new ProgressIndicator();
        progressIndicator.setPrefSize(50, 50);
        Reflection reflection1 = new Reflection();
        progressIndicator.setEffect(reflection1);
        progressIndicator.setVisible(false);
    }

    @Override
    public void designGUI() {
        HBox box1 = new HBox(10);
        box1.getChildren().addAll(banner, logo);
        box1.setAlignment(Pos.CENTER);
        box1.setPadding(new Insets(0, 0, 50, 0));

        this.add(box1, 0, 1, 2, 1);
        this.add(usernameLb, 0, 2);
        this.add(username, 1, 2);
        this.add(passwordLb, 0, 3);
        this.add(password, 1, 3);

        HBox box = new HBox(10);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        box.getChildren().add(login);
        this.add(box, 1, 5);
        this.add(progressIndicator, 0, 6, 2, 1);
        GridPane.setHalignment(progressIndicator, HPos.CENTER);
    }

    @Override
    public void registerListeners() {
        login.setOnAction(e ->{
            try {
                loginService.login();
            } catch (FormValidationException ex) {
                handleException(ex);
            }
        });

        username.setOnAction(e -> {
            String text = username.getText().trim();
            if(text.isEmpty()) {
                username.fireEvent(new PopupEvent(Alert.AlertType.WARNING, "Please enter username !"));
            }else {
                password.requestFocus();
            }
        });

        password.setOnAction(e -> {
            String pass = password.getText().trim();
            if(pass.isEmpty()) {
                password.fireEvent(new PopupEvent(Alert.AlertType.WARNING, "Please enter password !"));
            }else {
                login.requestFocus();
            }

        });
    }

    @Override
    public boolean checkSecurity() {
        return true;
    }

}
