package erp.client.javafx.component.textfield.percentage;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

import erp.client.javafx.component.textfield.number.DoubleField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

public class GstPercentageField extends DoubleField{

	private NumberFormat percentageFormat;
	private PercentageConverter percentageConverter;
	private final String percentageSymbol = "%";
	
	public GstPercentageField(String name, boolean isMandatoryField) {
		super(name, isMandatoryField);
		
		this.percentageFormat = NumberFormat.getPercentInstance();
		this.percentageFormat.setMaximumFractionDigits(2);
		this.percentageConverter = new PercentageConverter();
		this.setAlignment(Pos.CENTER_RIGHT);
		this.setTextFormatter(new TextFormatter<>(percentageConverter));
		this.textProperty().addListener(new PercentageFilter());
		this.setOnAction(e ->{
			super.validateField();
		});
	}

	public Double getGst() {
		Double value = percentageConverter.fromString(getText().trim());
		return value == null ? 0.0 : value;
	}
	
	public BigDecimal getGstBigDecimal() {
		return new BigDecimal(getGst());
	}
	
	public void setGst(Double gst) {
		if(gst == null) {
			gst = 0.0;
		}
		setText(percentageFormat.format(gst));
	}
	
	class PercentageConverter extends StringConverter<Double> {

		@Override
		public String toString(Double object) {
			return object != null ? percentageFormat.format(object) : "";
		}

		@Override
		public Double fromString(String string) {
			if(string != null && !string.isEmpty() && !string.equals(percentageSymbol)) {
				if(!string.contains(percentageSymbol)) {
					string = string + percentageSymbol;
				}
				try {
					Number number = percentageFormat.parse(string);
					return number.doubleValue();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
	}
	
	class PercentageFilter implements ChangeListener<String> {

		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(!newValue.replace(percentageSymbol, "").isEmpty()) {
				double value = Double.parseDouble(newValue.replace(percentageSymbol, ""));
				if(value < 0 || value > 100) {
					setText(oldValue);
				}
			}
		}
		
	}
}
