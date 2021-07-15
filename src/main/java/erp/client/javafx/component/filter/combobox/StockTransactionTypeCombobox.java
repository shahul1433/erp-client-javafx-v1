package erp.client.javafx.component.filter.combobox;

import erp.client.javafx.component.enums.StockTransactionType;
import erp.client.javafx.component.filter.FilterField;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.label.CLabel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

public class StockTransactionTypeCombobox extends ComboBox<StockTransactionType> implements FilterField{

	private CLabel label;
	private Font roboto;
	
	public StockTransactionTypeCombobox() {
		roboto = new CustomFontManager().getRobotoFont(12);
		label = new CLabel("Stock Transaction Type");
		
		for(StockTransactionType type : StockTransactionType.values()) {
			this.getItems().add(type);
		}
		this.setCellFactory(st -> createStockTransactionTypeCell());
		this.setButtonCell(createStockTransactionTypeCell());
		
		this.getSelectionModel().selectFirst();
	}
	
	private ListCell<StockTransactionType> createStockTransactionTypeCell() {
		return new ListCell<StockTransactionType>() {
			@Override
			protected void updateItem(StockTransactionType item, boolean empty) {
				super.updateItem(item, empty);
				setFont(roboto);
				if(item == null || empty)
					setGraphic(null);
				else
					setText(item.getType());
			}
		};
	}
	
	public Label getLabel() {
		return label;
	}

	@Override
	public boolean isValidFilterField() throws Exception {
		return (this.getSelectionModel().getSelectedItem() != StockTransactionType.ALL);
	}

	@Override
	public void clearSearch() {
		this.getSelectionModel().selectFirst();
	}
	
	public StockTransactionType getSelectedStockTransactionType() {
		return this.getSelectionModel().getSelectedItem();
	}

}
