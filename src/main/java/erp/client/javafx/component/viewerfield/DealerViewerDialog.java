package erp.client.javafx.component.viewerfield;

import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.dealer.DealerDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class DealerViewerDialog extends AbstractDialog {

    private CTextField name, shop, email, phone, gstin, gstStateCode;
    private CTextArea address;
    private DealerDTO dealerDTO;

    public DealerViewerDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
    }

    @Override
    protected void init() {
        dealerDTO = args.getArgument("dealer", DealerDTO.class);
        name = new CTextField("Name", false, -1);
        name.setEditable(false);
        shop = new CTextField("Shop", false, -1);
        shop.setEditable(false);
        email = new CTextField("Email", false, -1);
        email.setEditable(false);
        phone = new CTextField("Phone", false, -1);
        phone.setEditable(false);
        gstin = new CTextField("GSTIN", false, -1);
        gstin.setEditable(false);
        gstStateCode = new CTextField("GST State Code", false, -1);
        gstStateCode.setEditable(false);

        address = new CTextArea("Address", false, -1);
        address.setEditable(false);

        populateFields();
    }

    private void populateFields() {
        if (dealerDTO != null) {
            name.setText(dealerDTO.getName());
            shop.setText(dealerDTO.getShop());
            email.setText(dealerDTO.getEmail());
            phone.setText(dealerDTO.getPhone());
            gstin.setText(dealerDTO.getGstin());
            gstStateCode.setText(dealerDTO.getGstStateCode().getCode() + " - " + dealerDTO.getGstStateCode().getState());
            address.setText(dealerDTO.getAddress());
        }
    }

    @Override
    protected Pane designContentGUI() {
        GridPane pane = new GridPane();
        pane.setVgap(10);
        pane.setHgap(10);
        pane.setPadding(new Insets(25));
        pane.setAlignment(Pos.CENTER);

        int col = 0, row = 0;

        pane.add(name.getLabel(), col++, row);
        pane.add(name, col--, row++);

        pane.add(shop.getLabel(), col++, row);
        pane.add(shop, col--, row++);

        pane.add(email.getLabel(), col++, row);
        pane.add(email, col--, row++);

        pane.add(phone.getLabel(), col++, row);
        pane.add(phone, col--, row++);

        pane.add(gstin.getLabel(), col++, row);
        pane.add(gstin, col--, row++);

        pane.add(gstStateCode.getLabel(), col++, row);
        pane.add(gstStateCode, col--, row++);

        pane.add(address.getLabel(), col++, row);
        pane.add(address, col--, row++);

        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        return new HBox();
    }

    @Override
    protected void registerListeners() {

    }

    @Override
    protected boolean checkSecurity() {
        return true;
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/User.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        stage.setTitle("View Dealer");
    }
}
