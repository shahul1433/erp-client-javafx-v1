package erp.client.javafx.component.textfield.quantity;

import erp.client.javafx.component.FormField;
import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.number.DoubleFilter;
import erp.client.javafx.component.textfield.number.IntegerFilter;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.math.BigDecimal;

public class QuantityField extends HBox implements FormField{

	private CTextField field;
	private TextField scale;
	private DoubleFilter doubleFilter = null;
	private IntegerFilter integerFilter = null;
	private ProductScale productScale = null;
	
	public QuantityField(String name, boolean isMandatoryField, ProductScale productScale) {
		this.field = new CTextField(name, isMandatoryField, -1);
		this.field.setAlignment(Pos.CENTER_RIGHT);
		this.scale = new TextField();
		this.scale.setEditable(false);
		this.scale.setStyle("-fx-background-color: #80ccff;");
		this.scale.setAlignment(Pos.CENTER);
		this.doubleFilter = new DoubleFilter(field);
		this.integerFilter = new IntegerFilter(field);
		setScale(productScale);
		HBox.setHgrow(field, Priority.ALWAYS);
		this.setSpacing(5);
		getChildren().addAll(field, scale);
	}

	public void setScale(ProductScale scale) {
		this.productScale = scale;
		this.scale.setText(this.productScale.getRepresentation());
		if(productScale == ProductScale.COUNT) {
			field.textProperty().addListener(integerFilter);
			field.textProperty().removeListener(doubleFilter);
		}else {
			field.textProperty().addListener(doubleFilter);
			field.textProperty().removeListener(integerFilter);
		}
	}

	public ProductScale getProductScale() {
		return productScale;
	}

	public CTextField getField() {
		return field;
	}
	
	public Double getQuantity() {
		String data = field.getText().trim();
		if(data == null || data.isEmpty()) {
			return Double.valueOf(0);
		}
		try {
			return Double.parseDouble(data);
		} catch (NumberFormatException e) {
			return Double.valueOf(0);
		}
	}

	public BigDecimal getQuantityBigDecimal() {
		return new BigDecimal(getQuantity());
	}
	
	public void setQuantity(Double value, ProductScale scale) {
		if(value != null && scale != null) {
			setScale(scale);
			if(productScale == ProductScale.COUNT) {
				field.setText(String.valueOf(value.intValue()));
			}else {
				field.setText(String.valueOf(value.doubleValue()));
			}
		}
	}
	
	@Override
	public boolean validateField() {
		return field.validateField();
	}

	@Override
	public void clearField() {
		field.clearField();
	}
}
