package erp.client.javafx.component.textfield.number;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class DoubleFilter implements ChangeListener<String> {

		private TextField field;
		
		public DoubleFilter(TextField field) {
			this.field = field;
		}
		
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(!newValue.isEmpty()) {
				try {
					Double.parseDouble(newValue);
				} catch (NumberFormatException e) {
					field.setText(oldValue);
				}
			}
		}
		
	}