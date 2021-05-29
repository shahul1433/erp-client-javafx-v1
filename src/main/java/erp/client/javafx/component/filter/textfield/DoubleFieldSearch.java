package erp.client.javafx.component.filter.textfield;

import erp.client.javafx.component.filter.FilterField;
import erp.client.javafx.component.textfield.number.DoubleField;

public class DoubleFieldSearch extends DoubleField implements FilterField {

    public DoubleFieldSearch(String attributeName) {
        super(attributeName, false);
    }

    @Override
    public boolean isValidFilterField() throws Exception {
        return !getText().isBlank();
    }

    @Override
    public void clearSearch() {
        clear();
    }

    public Double getSearchValue() {
        if(getText().trim().isEmpty())
            return null;
        else
            return Double.parseDouble(getText().trim());
    }
}
