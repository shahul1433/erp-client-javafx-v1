package erp.client.javafx.component.textfield.phone;

import java.util.Comparator;

import erp.client.javafx.component.font.CustomFontManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class CountryListCombobox extends ComboBox<Country>{

	private StringProperty selectedExtension;
	private IntegerProperty maxLength;
	private Font roboto;
	
	public CountryListCombobox() {
		roboto = new CustomFontManager().getRobotoFont(12);
		this.setCellFactory(c -> new CountryListCell());
		this.setButtonCell(new CountryListCell());
		ObservableList<Country> items = FXCollections.observableArrayList();
		for(Country c : Country.values()) {
			items.add(c);
		}
		items.sort(new Comparator<Country>() {
			@Override
			public int compare(Country o1, Country o2) {
				return o1.getCountryName().compareTo(o2.getCountryName());
			}
		});
		this.setItems(items);
		this.selectedExtension = new SimpleStringProperty();
		this.maxLength = new SimpleIntegerProperty();
		
		this.setOnAction(e -> {
			Country selectedItem = getSelectionModel().getSelectedItem();
			if(selectedItem != null) {
				selectedExtension.set(selectedItem.getExtension());
				maxLength.set(selectedItem.getLength());
				this.setTooltip(new Tooltip(selectedItem.getCountryName()));
			}
		});
		
		this.setPrefHeight(50);
	}
	
	class CountryListCell extends ListCell<Country> {
		@Override
		protected void updateItem(Country item, boolean empty) {
			super.updateItem(item, empty);
			setFont(roboto);
			if(item == null || empty) {
				setGraphic(null);
				setText(null);
			}else {
				ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream(item.getIcon())));
				imageView.setFitWidth(50);
				imageView.setFitHeight(40);
				setGraphic(imageView);
				setText(item.getExtension());
				setTooltip(new Tooltip(item.getCountryName()));
			}
		}
	}

	public StringProperty getSelectedExtension() {
		return selectedExtension;
	}

	public IntegerProperty getMaxLength() {
		return maxLength;
	}
	
	public void selectCountry(Country country) {
		this.getSelectionModel().select(country);
		maxLength.set(country != null ? country.getLength() : 10 /*Default length*/);
	}
	
	public Country getSelectedCountry() {
		return this.getSelectionModel().getSelectedItem();
	}
}
