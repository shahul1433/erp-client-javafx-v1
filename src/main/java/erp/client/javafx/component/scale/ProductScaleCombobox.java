package erp.client.javafx.component.scale;

import erp.client.javafx.component.enums.ProductScale;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class ProductScaleCombobox extends ComboBox<ProductScale>{

	private Label label;
	
	public ProductScaleCombobox() {
		label = new Label("Scale");
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
