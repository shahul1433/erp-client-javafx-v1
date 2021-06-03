package erp.client.javafx.dealer;

import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.layout.AbstractGridPane;
import erp.client.javafx.layout.AbstractVBoxPane;
import erp.client.javafx.session.AppSession;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class DealerDetailsDialog extends AbstractDialog {

    private LeftSidePanel leftSidePanel;
    private TDealer dealer;

    public DealerDetailsDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
        getStage().setTitle("Dealer Details | "+ (dealer != null ? dealer.getName() : "--"));
    }

    @Override
    protected void init() {
        this.getStylesheets().add(DealerDetailsDialog.class.getResource("style.css").toExternalForm());
        leftSidePanel = new LeftSidePanel();
        dealer = getArgument("dealer", TDealer.class);
        leftSidePanel.dealerInfoPanel.populateFields();
    }

    @Override
    protected Pane designContentGUI() {
        BorderPane pane = new BorderPane();
        GridPane center = new GridPane();
        center.setStyle("-fx-background-color: green");
        pane.setLeft(leftSidePanel);
        pane.setCenter(center);
        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        HBox hBox = new HBox();
        return hBox;
    }

    @Override
    protected void registerListeners() {

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

    }

    class LeftSidePanel extends AbstractVBoxPane {

        DealerInfoPanel dealerInfoPanel;

        @Override
        public void init() {
            dealerInfoPanel = new DealerInfoPanel();
        }

        @Override
        public void designGUI() {
            this.setSpacing(10);
            this.setPadding(new Insets(10));
            this.getChildren().add(dealerInfoPanel);
        }

        @Override
        public void registerListeners() {

        }

        @Override
        public boolean checkSecurity() {
            return false;
        }

        class DealerInfoPanel extends AbstractGridPane {

            private ImageView icon;
            private CTextField name, shop, email, phone, gstin, gstStateCode;
            private CTextArea address;

            @Override
            public void init() {
                this.setId("dealer-info-panel");
                icon = new ImageView(new Image(getClass().getResourceAsStream("/image/person-man.png"), 200, 200, true, true));
                name = new CTextField("Name", false, -1);
                shop = new CTextField("Shop", false, -1);
                email = new CTextField("Email", false, -1);
                phone = new CTextField("Phone", false, -1);
                gstin = new CTextField("GSTIN", false, -1);
                gstStateCode = new CTextField("GST State Code", false, -1);
                address = new CTextArea("Address", false, -1);
                address.setPrefWidth(200);

                name.setEditable(false);
                shop.setEditable(false);
                email.setEditable(false);
                phone.setEditable(false);
                gstin.setEditable(false);
                gstStateCode.setEditable(false);
                address.setEditable(false);
            }

            @Override
            public void designGUI() {
                this.setHgap(10);
                this.setVgap(10);
                this.setAlignment(Pos.TOP_CENTER);
                this.setPadding(new Insets(25));
                int col = 0, row = 0;

                add(icon, col, row++, 2, 1);

                add(name.getLabel(), col++, row);
                add(name, col--, row++);

                add(shop.getLabel(), col++, row);
                add(shop, col--, row++);

                add(email.getLabel(), col++, row);
                add(email, col--, row++);

                add(phone.getLabel(), col++, row);
                add(phone, col--, row++);

                add(gstin.getLabel(), col++, row);
                add(gstin, col--, row++);

                add(gstStateCode.getLabel(), col++, row);
                add(gstStateCode, col--, row++);

                add(address.getLabel(), col++, row);
                add(address, col--, row++);

                GridPane.setValignment(address.getLabel(), VPos.TOP);
                GridPane.setHalignment(icon, HPos.CENTER);
            }

            @Override
            public void registerListeners() {

            }

            public void populateFields() {
                if(dealer != null) {
                    name.setText(dealer.getName().isEmpty()? "--" : dealer.getName());
                    shop.setText(dealer.getShop().isEmpty() ? "--" : dealer.getShop());
                    email.setText(dealer.getEmail().isEmpty() ? "--" : dealer.getEmail());
                    phone.setText(dealer.getPhone().isEmpty() ? "--" : dealer.getPhone());
                    gstin.setText(dealer.getGstin().isEmpty() ? "--" : dealer.getGstin());
                    gstStateCode.setText(dealer.getGstStateCode() != null ? dealer.getGstStateCode().getCode() + " - " + dealer.getGstStateCode().getState() : "--");
                    address.setText(dealer.getAddress().isEmpty() ? "--" : dealer.getAddress());
                }
            }

            @Override
            public boolean checkSecurity() {
                return false;
            }
        }
    }
}
