package erp.client.javafx.component.filter.textfield;

import erp.client.javafx.component.filter.FilterField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;

public class TextFieldSearch extends TextField implements FilterField{

	private Label label;
	private ComboBox<SearchPattern> pattern;
	
	public TextFieldSearch(String attributeName) {
		label = new Label(attributeName);
		pattern = new ComboBox<>();
		
		pattern.getItems().add(SearchPattern.CONTAINS);
		pattern.getItems().add(SearchPattern.STARTS_WITH);
		pattern.getItems().add(SearchPattern.ENDS_WITH);
		pattern.getItems().add(SearchPattern.EQUALS);
		
		pattern.setCellFactory(sc -> createSearchPatternCell());
		pattern.setButtonCell(createSearchPatternCell());

		pattern.getSelectionModel().select(0);

		pattern.valueProperty().addListener(new ChangeListener<SearchPattern>() {
			
			@Override
			public void changed(ObservableValue<? extends SearchPattern> observable, SearchPattern oldValue,
					SearchPattern newValue) {
				pattern.getEditor().setText(newValue.getPattern());
			}
		});
	}

	private ListCell<SearchPattern> createSearchPatternCell(){
		return new ListCell<SearchPattern>() {
			@Override
			protected void updateItem(SearchPattern item, boolean empty) {
				super.updateItem(item, empty);
				
				if(empty || item == null) {
					setText(null);
					setGraphic(null);
				}else {
					setText(item.getPattern());
					setTooltip(new Tooltip(getPatternString(item)));
				}
			}
			
			private String getPatternString(SearchPattern pattern) {
				switch (pattern) {
				case CONTAINS:
					return "Contains";
				case STARTS_WITH:
					return "Starts With";
				case ENDS_WITH:
					return "Ends With";
				case EQUALS:
					return "Equals";
				default:
					return null;
				}
			}
		};
	}
	
	public Label getLabel() {
		return label;
	}

	public ComboBox<SearchPattern> getPattern() {
		return pattern;
	}
	
	public String getSearchString() {
		StringBuilder sb = new StringBuilder();
		
		String keyword = getText().trim();
		if(keyword.isEmpty())
			return null;
		SearchPattern searchPattern = pattern.getValue();
		switch (searchPattern) {
		case CONTAINS:
			sb.append("%").append(keyword).append("%");
			break;
		case STARTS_WITH:
			sb.append(keyword).append("%");
			break;
		case ENDS_WITH:
			sb.append("%").append(keyword);
			break;
		case EQUALS:
		default:
			sb.append(keyword);
			break;
		}
		
		return sb.toString();
	}
	
	public HBox getHBoxAllignedItems() {
		HBox box = new HBox(5);
		box.getChildren().addAll(label, pattern, this);
		return box;
	}
	
	public void clearSearch() {
		clear();
		pattern.getSelectionModel().selectFirst();
	}

	@Override
	public boolean isValidFilterField() throws Exception{
		return !getText().isBlank();
	}
}

enum SearchPattern {
	
	CONTAINS 	("..?.."),
	STARTS_WITH ("?.."),
	ENDS_WITH 	("..?"),
	EQUALS		("?");
	
	private String pattern;
	
	private SearchPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
	
}