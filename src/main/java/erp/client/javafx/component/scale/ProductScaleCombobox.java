package erp.client.javafx.component.scale;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.label.CLabel;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

public class ProductScaleCombobox extends ComboBox<ProductScale>{

	private CLabel label;
	private Font roboto;
	
	public ProductScaleCombobox() {
		roboto = new CustomFontManager().getRobotoFont(12);
		label = new CLabel("Scale");
		for(ProductScale scale : ProductScale.values()) {
			if(scale != ProductScale.ALL)
				this.getItems().add(scale);
		}
		this.setCellFactory(ps -> createProductScaleCell());
		this.setButtonCell(createProductScaleButtonCell());
		
		this.getSelectionModel().selectFirst();
	}
	
	public Label getLabel() {
		return label;
	}

	private ListCell<ProductScale> createProductScaleButtonCell() {
		return new ListCell<ProductScale>() {
			@Override
			protected void updateItem(ProductScale item, boolean empty) {
				super.updateItem(item, empty);
				if(item == null || empty)
					setGraphic(null);
				else {
					setText(item.getRepresentation());
				}
			}
		};
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
					setText(item.getName() + " (" + item.getRepresentation() +")");
				}
			}
		};
	}

	public ProductScale getSelectedScale() {
		return this.getSelectionModel().getSelectedItem();
	}
	
	public void setScale(ProductScale scale) {
		this.getSelectionModel().select(scale);
	}
}
