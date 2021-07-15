package erp.client.javafx.home;

import erp.client.javafx.dealer.DealerManagementDialog;
import erp.client.javafx.layout.AbstractBorderPane;
import erp.client.javafx.login.LoginPane;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.stock.stockin.StockInManagementDialog;
import erp.client.javafx.stock.stockreturn.StockReturnManagementDialog;
import erp.client.javafx.user.UserManagementDialog;
import erp.client.javafx.utility.GuiUtility;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class HomeWindow extends AbstractBorderPane {

	TopPanel topPanel;
	BottomPanel bottomPanel;

	public HomeWindow(String loggedUserName) {
		super();
		this.topPanel.loggedUserName.set(loggedUserName);
	}
	
	@Override
	public void init() {
		this.getStylesheets().add(HomeWindow.class.getResource("home.css").toExternalForm());
		topPanel = new TopPanel();
		bottomPanel = new BottomPanel();
	}

	@Override
	public void designGUI() {
		this.setTop(topPanel);
		this.setBottom(bottomPanel);
	}

	@Override
	public void registerListeners() {
	}

	@Override
	public boolean checkSecurity() {
		return true;
	}

}
