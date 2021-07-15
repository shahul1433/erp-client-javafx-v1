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

public class TopPanel extends AbstractBorderPane {

    MenuBar menuBarLeft, menuBarRight;
    Menu configuration, inventory, loggedUser, stocks, dealer;
    MenuItem userConfiguration, account, logout, stockIn, stockReturn, stockTransactions, dealerManagement;
    HBox topBar;
    StringProperty loggedUserName;

    @Override
    public void init() {
        this.getStylesheets().add(TopPanel.class.getResource("home.css").toExternalForm());

        this.loggedUserName = new SimpleStringProperty();
        menuBarLeft = new MenuBar();
        menuBarRight = new MenuBar();
        menuBarLeft.getStyleClass().add("menubar");
        menuBarRight.getStyleClass().add("menubar");

        configuration = new Menu("Configuration");
        inventory = new Menu("Inventory");
        stocks = new Menu("Stocks");
        inventory.getItems().add(stocks);
        loggedUser = new Menu();
        loggedUser.textProperty().bind(loggedUserName);
        dealer = new Menu("Dealer");

        userConfiguration = new MenuItem("User Management");
        account = new MenuItem("Account");
        logout = new MenuItem("Logout");
        stockIn = new MenuItem("Stock In");
        stockReturn = new MenuItem("Stock Return");
        stockTransactions = new MenuItem("Stock Transactions");
        dealerManagement = new MenuItem("Dealer Management");

    }

    @Override
    public void designGUI() {
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();

        loggedUser.getItems().addAll(account, separatorMenuItem, logout);

        configuration.getItems().add(userConfiguration);
        stocks.getItems().addAll(stockIn, stockReturn, stockTransactions);
        dealer.getItems().add(dealerManagement);

        menuBarLeft.getMenus().addAll(configuration, inventory, dealer);
        menuBarRight.getMenus().add(loggedUser);

        HBox.setHgrow(menuBarLeft, Priority.ALWAYS);
        topBar = new HBox();
        topBar.getChildren().addAll(menuBarLeft, menuBarRight);

        this.setCenter(topBar);
    }

    @Override
    public void registerListeners() {
        userConfiguration.setOnAction(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new UserManagementDialog();
                }
            });
        });

        dealerManagement.setOnAction(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new DealerManagementDialog();
                }
            });
        });

        stockIn.setOnAction(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new StockInManagementDialog();
                }
            });
        });

        stockReturn.setOnAction(e -> {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    new StockReturnManagementDialog();
                }
            });
        });

//		stockTransactions.setOnAction(e -> {
//			Platform.runLater(new Runnable() {
//				@Override
//				public void run() {
//					new StockTransactionManagementDialog();
//				}
//			});
//		});

        logout.setOnAction(e -> {
            AppSession.clearSession();
            Stage primaryStage = (Stage) topBar.getScene().getWindow();
            primaryStage.setScene(new Scene(new LoginPane(), GuiUtility.getScreenWidth(), GuiUtility.getScreenHeight()));
            primaryStage.setMaximized(true);
        });
    }

    @Override
    public boolean checkSecurity() {
        return false;
    }
}
