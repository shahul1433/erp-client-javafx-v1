package erp.client.javafx.component.viewerfield;

import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.icon.FontAwsomeManager;
import javafx.scene.control.Tooltip;

public class DealerViewerField extends ViewerField {

    private DealerDTO dealerDTO;

    public DealerViewerField(String attributeName, DealerDTO dealerDTO, Arguments arguments) {
        super(arguments);
        label.setText(attributeName);
        this.dealerDTO = dealerDTO;
    }

    @Override
    public void init() {
        super.init();
        button.setText("\uf508");
        button.setFont(FontAwsomeManager.getSolidFontPlain(14));
        button.setTooltip(new Tooltip("View Dealer"));
    }

    @Override
    protected void onButtonClick() {
        Arguments args = new Arguments();
        args.setArgument("dealer", dealerDTO);
        new DealerViewerDialog(null, StageMode.VIEW, args);
    }

    public void setDealerDTO(DealerDTO dealerDTO) {
        this.dealerDTO = dealerDTO;
        textField.setText(dealerDTO.getName());
    }

}
