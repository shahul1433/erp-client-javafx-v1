package erp.client.javafx.component.filter.combobox;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.filter.FilterField;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.label.CLabel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

public class ProductScaleCombobox extends ComboBox<ProductScale> implements FilterField{
	
	private CLabel label;
	private Font roboto;
	
	public ProductScaleCombobox() {
		roboto = new CustomFontManager().getRobotoFont(12);
		label = new CLabel("Scale");
		
		for(ProductScale scale : ProductScale.values()) {
			this.getItems().add(scale);
		}
		this.setCellFactory(ps -> createProductScaleCell());
		this.setButtonCell(createProductScaleCell());
		
		this.getSelectionModel().selectFirst();
	}
	
	private ListCell<ProductScale> createProductScaleCell() {
		return new ListCell<ProductScale>() {
			@Override
			protected void updateItem(ProductScale item, boolean empty) {
				super.updateItem(item, empty);
				setFont(roboto);
				if(item == null || empty)
					setGraphic(null);
				else {
					if(item == ProductScale.ALL)
						setText(item.getName());
					else
						setText(item.getName() + " (" + item.getRepresentation() +")");
				}
			}
		};
	}

	public Label getLabel() {
		return label;
	}

	@Override
	public boolean isValidFilterField() throws Exception {
		return (getSelectionModel().getSelectedItem() != ProductScale.ALL);
	}

	@Override
	public void clearSearch() {
		this.getSelectionModel().selectFirst();
	}
	
	public ProductScale getSelectedScale() {
		return this.getSelectionModel().getSelectedItem();
	}
	
}
