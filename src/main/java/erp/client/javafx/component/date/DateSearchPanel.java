package erp.client.javafx.component.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import erp.client.javafx.component.filter.FilterField;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.label.CLabel;
import erp.client.javafx.exception.FormValidationException;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class DateSearchPanel extends StackPane implements FilterField {

	private String attributeName;
	private DatePicker fromDate, toDate;
	private DateSearchable dateSearchable;

	public DateSearchPanel(String attributeName) {
		this.attributeName = attributeName;
		this.dateSearchable = new DateSearchable();
		// Load style sheet
		getStylesheets().add(getClass().getResource("style.css").toExternalForm());

		init();

		CLabel title = new CLabel(attributeName);
		title.getStyleClass().add("bordered-titled-title");
		StackPane.setAlignment(title, Pos.TOP_LEFT);

		GridPane contentPane = new GridPane();
		contentPane.getStyleClass().add("bordered-titled-content");
		contentPane.setHgap(10);
		contentPane.setVgap(5);

		contentPane.add(new CLabel("From"), 0, 0);
		contentPane.add(fromDate, 0, 1);

		contentPane.add(new CLabel("To"), 1, 0);
		contentPane.add(toDate, 1, 1);

		getStyleClass().add("bordered-titled-border");
		getChildren().addAll(title, contentPane);
	}

	private void init() {
		fromDate = new DatePicker();
		fromDate.getEditor().setFont(new CustomFontManager().getRobotoFont(12));
		toDate = new DatePicker();
		toDate.getEditor().setFont(new CustomFontManager().getRobotoFont(12));
	}

	public LocalDateTime getFromDate() {
		LocalDate date = fromDate.getValue();
		if (date != null)
			return LocalDateTime.of(date, LocalTime.MIN);
		return null;
	}

	public LocalDateTime getToDate() {
		LocalDate date = toDate.getValue();
		if (date != null)
			return LocalDateTime.of(date, LocalTime.MAX);
		return null;
	}

	public void clearSearch() {
		fromDate.setValue(null);
		toDate.setValue(null);
	}

	public void validateDateFields() throws FormValidationException {

		LocalDate from = fromDate.getValue();
		LocalDate to = toDate.getValue();

		if (from != null && to == null) {
			throw new FormValidationException(Alert.AlertType.WARNING, "Please choose \"To\" date of " + attributeName);
		}
		if (from == null && to != null) {
			throw new FormValidationException(Alert.AlertType.WARNING, "Please choose \"From\" date of " + attributeName);
		}

		if (from != null && to != null) {
			if (from.isAfter(to)) {
				throw new FormValidationException(Alert.AlertType.WARNING,
						"\"To\" date should be greater than or equal to \"From\" date of " + attributeName);
			}
		}
	}

	public DateSearchable getDateSearchable() {
		dateSearchable.setFromDate(getFromDate());
		dateSearchable.setToDate(getToDate());
		return dateSearchable;
	}

	@Override
	public boolean isValidFilterField() throws Exception {
		validateDateFields();
		return (fromDate.getValue() != null && toDate.getValue() != null);
	}

}
