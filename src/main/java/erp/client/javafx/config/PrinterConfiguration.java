package erp.client.javafx.config;

public class PrinterConfiguration {

	private String receiptPrinter;
	private String reportPrinter;
	/**
	 * @return the receiptPrinter
	 */
	public String getReceiptPrinter() {
		return receiptPrinter;
	}
	/**
	 * @param receiptPrinter the receiptPrinter to set
	 */
	public void setReceiptPrinter(String receiptPrinter) {
		this.receiptPrinter = receiptPrinter;
	}
	/**
	 * @return the reportPrinter
	 */
	public String getReportPrinter() {
		return reportPrinter;
	}
	/**
	 * @param reportPrinter the reportPrinter to set
	 */
	public void setReportPrinter(String reportPrinter) {
		this.reportPrinter = reportPrinter;
	}
	
	public void createDefaultPrinterConfiguration() {
		setReceiptPrinter("-select-");
		setReportPrinter("-select-");
	}
}
