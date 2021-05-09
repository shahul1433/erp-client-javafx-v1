package erp.client.javafx.component.textfield.number;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class IntegerFilter implements ChangeListener<String> {

		private TextField field;
		
		public IntegerFilter(TextField field) {
			this.field = field;
		}
		
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			if(!newValue.isBlank()) {
				try {
					Integer.parseInt(newValue);
				} catch (NumberFormatException e) {
					field.setText(oldValue);
				}
			}
		}
		
	}