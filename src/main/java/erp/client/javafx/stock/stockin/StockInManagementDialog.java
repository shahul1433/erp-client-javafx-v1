package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.container.tablewithnavigation.TopBar;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.entity.TStockIn;
import erp.client.javafx.entity.TUser;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.icon.FontAwsomeManager;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.GuiUtility;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StockInManagementDialog extends AbstractTableWithNavigationDialog<StockIn> {

    private StockInManagementService stockInManagementService = new StockInManagementService(this);
    private StockInManagementTopBar stockInManagementTopBar;

    public StockInManagementDialog() {
        if (checkSecurity()) {
            Scene scene = new Scene(this, GuiUtility.maximumSize().getWidth(), GuiUtility.maximumSize().getHeight());
            getStage().setScene(scene);
            getStage().getIcons().add(new Image(getClass().getResourceAsStream("/image/inventory.png")));
            getStage().setTitle("Stock In Management");
            getStage().initModality(Modality.APPLICATION_MODAL);
            getStage().show();

            refresh(); //Initial call.
        }
    }

    @Override
    public void init() {
        super.init();
        stockInManagementTopBar = new StockInManagementTopBar();
        setTopBar(stockInManagementTopBar);
    }

    @Override
    public void refresh() {
        stockInManagementService.getAllStockIn(null);
    }

    @Override
    protected void createTableColumns(ArrayList<TableColumnDataWrapper<StockIn, ?>> tableColumns) {
        TableColumn<StockIn, Void> index = new TableColumn<>("#");
        index.setStyle("-fx-alignment: CENTER;");
        index.setCellFactory(col -> {
            TableCell<StockIn, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    return Integer.toString((getBottomBar().getPageNo().getSelectionModel().getSelectedIndex()*getBottomBar().getItemsPerPage().getValue()) + (cell.getIndex()+1));
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell ;
        });
        getCenterPane().getTable().getColumns().add(index);

        TableColumnDataWrapper<StockIn, String> nameColumn = new TableColumnDataWrapper<StockIn, String>("Name", "name");
        nameColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, String> modelColumn = new TableColumnDataWrapper<StockIn, String>("Model", "model");
        modelColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, String> categoryColumn = new TableColumnDataWrapper<>("Category", "category");
        categoryColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, String> companyColumn = new TableColumnDataWrapper<>("Company", "company");
        companyColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, String> warrantyColumn = new TableColumnDataWrapper<>("Warranty", "warranty");
        warrantyColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, String> guaranteeColumn = new TableColumnDataWrapper<>("Guarantee", "guarantee");
        guaranteeColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, Double> stockQuantityColumn = new TableColumnDataWrapper<>("Stock Quantity", "stockQuantity");
        stockQuantityColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> currentQuantityColumn = new TableColumnDataWrapper<>("Current Quantity", "currentQuantity");
        currentQuantityColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> reorderLimitColumn = new TableColumnDataWrapper<>("Reorder Limit", "reorderLimit");
        reorderLimitColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> stockPriceColumn = new TableColumnDataWrapper<>("Stock Price", "stockPrice");
        stockPriceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> customerPriceColumn = new TableColumnDataWrapper<>("Customer Price", "customerPrice");
        customerPriceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> gstColumn = new TableColumnDataWrapper<>("GST (%)", "gst");
        gstColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> gstAmountColumn = new TableColumnDataWrapper<>("GST Amount", "gstAmount");
        gstAmountColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, Double> netAmountColumn = new TableColumnDataWrapper<>("Net Amount", "netAmount");
        netAmountColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        TableColumnDataWrapper<StockIn, ProductScale> scaleColumn = new TableColumnDataWrapper<>("Scale", "scale");
        scaleColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, String> specificationsColumn = new TableColumnDataWrapper<>("Specifications", "specifications");
        specificationsColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, TDealer> dealerColumn = new TableColumnDataWrapper<>("Dealer", "dealer");
        dealerColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, TUser> addedByColumn = new TableColumnDataWrapper<>("Added By", "addedBy");
        addedByColumn.setStyle("-fx-alignment: CENTER-LEFT;");
        TableColumnDataWrapper<StockIn, LocalDateTime> addedDateColumn = new TableColumnDataWrapper<>("Added Date", "addedDate");
        addedDateColumn.setStyle("-fx-alignment: CENTER-LEFT;");

        tableColumns.add(nameColumn);
        tableColumns.add(modelColumn);
        tableColumns.add(categoryColumn);
        tableColumns.add(companyColumn);
        tableColumns.add(warrantyColumn);
        tableColumns.add(guaranteeColumn);
        tableColumns.add(stockQuantityColumn);
        tableColumns.add(currentQuantityColumn);
        tableColumns.add(reorderLimitColumn);
        tableColumns.add(stockPriceColumn);
        tableColumns.add(customerPriceColumn);
        tableColumns.add(gstColumn);
        tableColumns.add(gstAmountColumn);
        tableColumns.add(netAmountColumn);
        tableColumns.add(scaleColumn);
        tableColumns.add(specificationsColumn);
        tableColumns.add(dealerColumn);
        tableColumns.add(addedByColumn);
        tableColumns.add(addedDateColumn);

        getCenterPane().getTable().getColumns().addAll(tableColumns);
    }

    @Override
    protected void sortCall(SortMap sortMap) {
        stockInManagementService.getAllStockIn(sortMap);
    }

    @Override
    protected void setFilterDialog() {
        this.filterDialog = new StockInFilterDialog(this);
    }

    @Override
    public boolean checkSecurity() {
        return AppSession.hasRole(UserRole.STOCK_IN);
    }

    class StockInManagementTopBar extends TopBar {

        private Button addStock;

        @Override
        public void init() {
            super.init();

            addStock = new Button("\uf067");
            addStock.setFont(FontAwsomeManager.getSolidFontPlain(14));
            addStock.setTooltip(new Tooltip("Add Stock"));
        }

        @Override
        public void designGUI() {
            super.designGUI();

            HBox buttonPane = new HBox(5);
            buttonPane.getChildren().addAll(addStock);
            buttonPane.setPadding(new Insets(10));

            this.setLeft(buttonPane);
        }

        @Override
        public void registerListeners() {
            super.registerListeners();
            Arguments args = new Arguments();
            args.setArgument("stockIn", new TStockIn());
            addStock.setOnAction(e -> new AddStockDialog(getStage(), StageMode.ADD, args));
        }
    }
}
