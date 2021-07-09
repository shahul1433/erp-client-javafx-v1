package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.common.cellfactory.*;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.container.tablewithnavigation.TopBar;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.icon.FontAwesomeManager;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.user.UserDTO;
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

public class StockReturnManagementDialog extends AbstractTableWithNavigationDialog<StockReturn> {

    protected StockReturnManagementTopBar stockReturnManagementTopBar;
    private StockReturnManagementService stockReturnManagementService;

    public StockReturnManagementDialog() {
        if (checkSecurity()) {
            Scene scene = new Scene(this, GuiUtility.maximumSize().getWidth(), GuiUtility.maximumSize().getHeight());
            getStage().setScene(scene);
            getStage().getIcons().add(new Image(getClass().getResourceAsStream("/image/inventory.png")));
            getStage().setTitle("Stock Return Management");
            getStage().initModality(Modality.APPLICATION_MODAL);
            getStage().show();

            refresh(); //Initial call.
        }
    }

    @Override
    public void init() {
        super.init();
        stockReturnManagementTopBar = new StockReturnManagementTopBar();
        stockReturnManagementService = new StockReturnManagementService(this);
        setTopBar(stockReturnManagementTopBar);
    }

    @Override
    public void refresh() {
        stockReturnManagementService.getAllStockReturn(null);
    }

    @Override
    protected void createTableColumns(ArrayList<TableColumnDataWrapper<StockReturn, ?>> tableColumns) {
        TableColumn<StockReturn, Void> index = new TableColumn<>("#");
        index.setStyle("-fx-alignment: CENTER;");
        index.setCellFactory(col -> {
            TableCell<StockReturn, Void> cell = new TableCell<>();
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

        TableColumnDataWrapper<StockReturn, StockInDTO> itemColumn = new TableColumnDataWrapper<>("Item", "stockIn", new StockItemCellFactory());
        TableColumnDataWrapper<StockReturn, String> reasonColumn = new TableColumnDataWrapper<>("Reason", "reason", new TextCellFactory());
        TableColumnDataWrapper<StockReturn, Double> returnQtyColumn = new TableColumnDataWrapper<>("Return Quantity", "returnQuantity", new NumberCellFactory());
        TableColumnDataWrapper<StockReturn, Double> refundAmountColumn = new TableColumnDataWrapper<>("Refund Amount", "refundAmount", new RupeesCellFactory());
        TableColumnDataWrapper<StockReturn, LocalDateTime> addedDateColumn = new TableColumnDataWrapper<>("Added On", "addedDate", new DateCellFactory());
        TableColumnDataWrapper<StockReturn, UserDTO> addedByColumn = new TableColumnDataWrapper<>("Added By", "addedBy", new UserCellFactory());

        tableColumns.add(itemColumn);
        tableColumns.add(reasonColumn);
        tableColumns.add(returnQtyColumn);
        tableColumns.add(refundAmountColumn);
        tableColumns.add(addedDateColumn);
        tableColumns.add(addedByColumn);

        getCenterPane().getTable().getColumns().addAll(tableColumns);
    }

    @Override
    protected void sortCall(SortMap sortMap) {
        stockReturnManagementService.getAllStockReturn(sortMap);
    }

    @Override
    protected void setFilterDialog() {
        this.filterDialog = new StockReturnFilterDialog(this);
    }

    @Override
    public boolean checkSecurity() {
        return AppSession.hasRole(UserRole.STOCK_RETURN);
    }

    class StockReturnManagementTopBar extends TopBar {

        private Button addStockReturn;

        @Override
        public void init() {
            super.init();

            addStockReturn = new Button("\uf067");
            addStockReturn.setFont(FontAwesomeManager.getSolidFontPlain(14));
            addStockReturn.setTooltip(new Tooltip("Add Stock return"));
        }

        @Override
        public void designGUI() {
            super.designGUI();

            HBox buttonPane = new HBox(5);
            buttonPane.setPadding(new Insets(10));
            buttonPane.getChildren().add(addStockReturn);

            this.setLeft(buttonPane);
        }

        @Override
        public void registerListeners() {
            super.registerListeners();
            addStockReturn.setOnAction(e -> {new AddStockReturnDialog(getStage(), StageMode.ADD, null);});
        }
    }
}
