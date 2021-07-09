package erp.client.javafx.component;

public interface FormField {

	public boolean validateField();
	public void clearField();
	public void setReadOnly(boolean isReadOnly);
}
