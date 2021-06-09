package erp.client.javafx.dealer;

import erp.client.javafx.component.combobox.GSTStateCodeCombobox;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.email.EmailField;
import erp.client.javafx.component.textfield.phone.Country;
import erp.client.javafx.component.textfield.phone.PhoneField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.session.AppSession;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;

public class AddEditDealerDialog extends AbstractDialog {

    private CTextField name, shop, gstin;
    private EmailField email;
    private PhoneField phone;
    private GSTStateCodeCombobox gstStateCodeCombobox;
    private CTextArea address;

    private Button addUpdate, clear;

    private DealerDTO dealer;
    private AddEditDealerService addEditDealerService;

    public AddEditDealerDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
        populateFields();
    }

    @Override
    protected void init() {
        this.dealer = getArgument("dealer", DealerDTO.class);
        this.addEditDealerService = new AddEditDealerService(this);
        name = new CTextField("Name", true, 100);
        shop = new CTextField("Shop", false, 100);
        email = new EmailField(false, 150);
        phone = new PhoneField("Phone", true, Country.INDIA);
        gstin = new CTextField("GSTIN", false, 20);
        gstStateCodeCombobox = new GSTStateCodeCombobox("GST State Code", false);
        gstStateCodeCombobox.setPrefWidth(Double.MAX_VALUE);
        address = new CTextArea("Address", true, 500);

        addUpdate = new Button("Add");
        addUpdate.setStyle("-fx-base: green");
        clear = new Button("Clear");
        clear.setStyle("-fx-base: red");
        stage.setWidth(400);
    }

    @Override
    protected Pane designContentGUI() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25));

        ColumnConstraints empty = new ColumnConstraints();
        ColumnConstraints stretched = new ColumnConstraints(100, 150, Double.MAX_VALUE);
        stretched.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().addAll(empty, stretched);

        int col = 0, row = 0;

        pane.add(name.getLabel(), col++, row);
        pane.add(name, col--, row++);

        pane.add(shop.getLabel(), col++, row);
        pane.add(shop, col--, row++);

        pane.add(email.getLabel(), col++, row);
        pane.add(email, col--, row++);

        pane.add(phone.getLabel(), col++, row);
        pane.add(phone.getHBoxComboPhoneField(), col--, row++);

        pane.add(gstin.getLabel(), col++, row);
        pane.add(gstin, col--, row++);

        pane.add(gstStateCodeCombobox.getLabel(), col++, row);
        pane.add(gstStateCodeCombobox, col--, row++);

        pane.add(address.getLabel(), col++, row);
        pane.add(address.getTextAreaWithProgressBar(), col--, row++);

        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(10));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(addUpdate, clear);
        return buttonPane;
    }

    @Override
    protected void registerListeners() {
        clear.setOnAction(e -> clearFields());

        addUpdate.setOnAction(e -> {
            if(
                    name.validateField() &&
                            shop.validateField() &&
                            gstin.validateField() &&
                            email.validateField() &&
                            phone.validateField() &&
                            gstStateCodeCombobox.validateField() &&
                            address.validateField()
            ) {
                addEditDealerService.saveDealer();
            }
        });
    }

    private void populateFields() {
        if(stageMode == StageMode.EDIT && dealer != null) {
            name.setText(dealer.getName());
            shop.setText(dealer.getShop());
            email.setText(dealer.getEmail());
            phone.setPhoneNo(dealer.getPhone());
            gstin.setText(dealer.getGstin());
            gstStateCodeCombobox.setSelectedGstStateCode(dealer.getGstStateCode());
            address.setString(dealer.getAddress());
        }
    }

    private void clearFields() {
        name.clearField();
        shop.clearField();
        email.clearField();
        phone.clearField();
        gstin.clearField();
        gstStateCodeCombobox.clearField();
        address.clearField();
    }

    @Override
    protected boolean checkSecurity() {
        return AppSession.hasRole(UserRole.DEALER);
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/User.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        switch (stageMode) {
            case ADD:
                getStage().setTitle("Add Dealer");
                break;
            case EDIT:
                getStage().setTitle("Edit Dealer - " + (dealer != null ? dealer.getName() : ""));
                addUpdate.setText("Update");
                clear.setDisable(true);
                break;
            case VIEW:
            default:
                break;
        }
    }

    public CTextField getName() {
        return name;
    }

    public CTextField getShop() {
        return shop;
    }

    public CTextField getGstin() {
        return gstin;
    }

    public EmailField getEmail() {
        return email;
    }

    public PhoneField getPhone() {
        return phone;
    }

    public GSTStateCodeCombobox getGstStateCodeCombobox() {
        return gstStateCodeCombobox;
    }

    public CTextArea getAddress() {
        return address;
    }

    public DealerDTO getDealer() {
        return dealer;
    }
}
