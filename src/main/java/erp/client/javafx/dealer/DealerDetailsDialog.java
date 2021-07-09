package erp.client.javafx.dealer;

import erp.client.javafx.component.combobox.PageNoCombobox;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.currency.IndianRupeesField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.icon.FontAwesomeManager;
import erp.client.javafx.layout.AbstractBorderPane;
import erp.client.javafx.layout.AbstractGridPane;
import erp.client.javafx.layout.AbstractVBoxPane;
import erp.client.javafx.session.AppSession;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.time.Month;

public class DealerDetailsDialog extends AbstractDialog {

    private LeftSidePanel leftSidePanel;
    private CenterPanel centerPanel;
    private DealerDTO dealerDTO;
    private LedgerDTO ledgerDTO;
    private DealerDetailsService dealerDetailsService;

    public DealerDetailsDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
        getStage().setTitle("Dealer Details | "+ (dealerDTO != null ? dealerDTO.getName() : "--"));
    }

    @Override
    protected void init() {
        this.getStylesheets().add(DealerDetailsDialog.class.getResource("style.css").toExternalForm());
        dealerDetailsService = new DealerDetailsService(this);
        leftSidePanel = new LeftSidePanel();
        centerPanel = new CenterPanel();
        dealerDTO = getArgument("dealer", DealerDTO.class);
        ledgerDTO = null;
        dealerDetailsService.getLedgerYearsByDealer(dealerDTO);
        leftSidePanel.dealerInfoPanel.populateFields();
        leftSidePanel.balanceAmountPanel.populateFields();
    }

    @Override
    protected Pane designContentGUI() {
        BorderPane pane = new BorderPane();
        pane.setLeft(leftSidePanel);
        pane.setCenter(centerPanel);
        return pane;
    }

    public LeftSidePanel getLeftSidePanel() {
        return leftSidePanel;
    }

    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    public DealerDTO getDealerDTO() {
        return dealerDTO;
    }

    @Override
    protected Pane designButtonGUI() {
        HBox hBox = new HBox();
        return hBox;
    }

    @Override
    protected void registerListeners() {

    }

    @Override
    protected boolean checkSecurity() {
        return AppSession.hasRole(UserRole.DEALER);
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/User.png");
    }

    @Override
    protected void adjustViewByStageMode() {

    }

    public LedgerDTO getLedgerDTO() {
        return ledgerDTO;
    }

    public void setLedgerDTO(LedgerDTO ledgerDTO) {
        this.ledgerDTO = ledgerDTO;
    }

    class CenterPanel extends AbstractBorderPane {

        TopBar topBar;
        ContentPane contentPane;
        BottomBar bottomBar;

        @Override
        public void init() {
            topBar = new TopBar();
            contentPane = new ContentPane();
            bottomBar = new BottomBar();
        }

        @Override
        public void designGUI() {
            this.setPadding(new Insets(10));
            setTop(topBar);
            setCenter(contentPane);
            setBottom(bottomBar);
        }

        @Override
        public void registerListeners() {

        }

        @Override
        public boolean checkSecurity() {
            return false;
        }

        class TopBar extends AbstractBorderPane {

            Label label, imgLabel;
            ComboBox<Integer> year;
            ComboBox<Month> month;
            IndianRupeesField openBalance, closedBalance;

            @Override
            public void init() {
                this.setId("dealer-ledger-top-bar");
                imgLabel = new Label("\uf03a");
                imgLabel.setFont(FontAwesomeManager.getSolidFontPlain(48));
                label = new Label("Ledger");
                label.setId("ledger-heading-label");
                year = new ComboBox<>();
                month = new ComboBox<>();
                openBalance = new IndianRupeesField("Open Balance", false);
                openBalance.setEditable(false);
                closedBalance = new IndianRupeesField("Closed Balance", false);
                closedBalance.setEditable(false);
            }

            public void setOpenBalance(Double amount) {
                openBalance.setCashAmount(amount);
                if(amount < 0) {
                    openBalance.setStyle("-fx-text-fill: red");
                } else {
                    openBalance.setStyle("-fx-text-fill: #00e600");
                }
            }

            public void setClosedBalance(Double amount) {
                closedBalance.setCashAmount(amount);
                if(amount < 0) {
                    closedBalance.setStyle("-fx-text-fill: red");
                } else {
                    closedBalance.setStyle("-fx-text-fill: #00e600");
                }
            }

            @Override
            public void designGUI() {
                HBox labelPane = new HBox(2);
                labelPane.getChildren().addAll(imgLabel, label);
                labelPane.setAlignment(Pos.CENTER);
                labelPane.setPadding(new Insets(5));
                setLeft(labelPane);
                BorderPane.setAlignment(label, Pos.CENTER);

                setCenter(designToolBar());

                setRight(designLedgerBalancePane());

            }
            private HBox designToolBar() {
                HBox hBox = new HBox(10);
                hBox.setAlignment(Pos.CENTER);
                hBox.setPadding(new Insets(10));

                Label yearLabel = new Label("Year");
                yearLabel.setStyle("-fx-text-fill: #b3b3cc");

                Label monthLabel = new Label("Month");
                monthLabel.setStyle("-fx-text-fill: #b3b3cc");

                hBox.getChildren().addAll(yearLabel, this.year, monthLabel, this.month);
                return hBox;
            }
            private GridPane designLedgerBalancePane() {
                GridPane pane = new GridPane();
                pane.setId("ledger-balance-pane");
                pane.setHgap(10);
                pane.setVgap(10);
                pane.setAlignment(Pos.CENTER);

                int col = 0, row = 0;

                pane.add(openBalance.getLabel(), col++, row);
                pane.add(openBalance, col--, row++);

                pane.add(closedBalance.getLabel(), col++, row);
                pane.add(closedBalance, col--, row++);
                return pane;
            }

            @Override
            public void registerListeners() {

                year.valueProperty().addListener(new ChangeListener<Integer>() {
                    @Override
                    public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                        dealerDetailsService.getLedgerMonthsByYearAndDealer();
                    }
                });

                month.valueProperty().addListener(new ChangeListener<Month>() {
                    @Override
                    public void changed(ObservableValue<? extends Month> observableValue, Month month, Month t1) {
                        dealerDetailsService.getLedgerByMonthAndYearAndDealer();
                    }
                });
            }

            @Override
            public boolean checkSecurity() {
                return false;
            }
        }

        class ContentPane extends AbstractBorderPane {

            private VBox contentBox;

            @Override
            public void init() {
                this.setId("dealer-ledger-content");
                contentBox = new VBox(10);
                contentBox.setPadding(new Insets(10));
                showEmptyContentInfo();
            }

            @Override
            public void designGUI() {
                ScrollPane pane = new ScrollPane(contentBox);
                pane.setPrefHeight(400);
                pane.setFitToWidth(true);
                setCenter(pane);
            }

            public void showEmptyContentInfo() {
                Label noContentLabel = new Label("There is no transactions yet");
                noContentLabel.setId("no-content-label");
                noContentLabel.setAlignment(Pos.CENTER);

                BorderPane pane = new BorderPane();
                pane.setCenter(noContentLabel);
                contentBox.getChildren().add(pane);
            }

            public VBox getContentBox() {
                return contentBox;
            }

            @Override
            public void registerListeners() {

            }

            @Override
            public boolean checkSecurity() {
                return false;
            }

        }

        class BottomBar extends AbstractBorderPane {

            Button prev, next;
            Label info;
            PageNoCombobox pageNo;

            @Override
            public void init() {
                this.setId("dealer-ledger-bottom-bar");
                prev = new Button();
                prev.setId("prev-button");
                prev.setDisable(true);
                next = new Button();
                next.setId("next-button");
                next.setDisable(true);

                info = new Label("Showing 0 - 0 0f 0 transactions");
                info.setId("ledger-info-text");
                pageNo = new PageNoCombobox();
            }

            @Override
            public void designGUI() {

                VBox vBox = new VBox(10);
                vBox.getChildren().addAll(pageNo, info);
                vBox.setAlignment(Pos.CENTER);
                setCenter(vBox);

                setLeft(prev);
                BorderPane.setAlignment(prev, Pos.CENTER);

                setRight(next);
                BorderPane.setAlignment(next, Pos.CENTER);
            }

            @Override
            public void registerListeners() {
                next.setOnAction(e -> {
                    pageNo.getSelectionModel().selectNext();
                    dealerDetailsService.getLedgerTransactions();
                });

                prev.setOnAction(e -> {
                    pageNo.getSelectionModel().selectPrevious();
                    dealerDetailsService.getLedgerTransactions();
                });

                pageNo.setOnAction(e -> dealerDetailsService.getLedgerTransactions());
            }

            @Override
            public boolean checkSecurity() {
                return false;
            }
        }
    }

    class LeftSidePanel extends AbstractVBoxPane {

        DealerInfoPanel dealerInfoPanel;
        BalanceAmountPanel balanceAmountPanel;

        @Override
        public void init() {
            dealerInfoPanel = new DealerInfoPanel();
            balanceAmountPanel = new BalanceAmountPanel();
        }

        @Override
        public void designGUI() {
            this.setSpacing(10);
            this.setPadding(new Insets(10));
            this.getChildren().addAll(dealerInfoPanel, balanceAmountPanel);
        }

        @Override
        public void registerListeners() {

        }

        @Override
        public boolean checkSecurity() {
            return false;
        }

        class DealerInfoPanel extends AbstractGridPane {

            private ImageView icon;
            private CTextField name, shop, email, phone, gstin, gstStateCode;
            private CTextArea address;

            @Override
            public void init() {
                this.setId("dealer-info-panel");
                icon = new ImageView(new Image(getClass().getResourceAsStream("/image/person-man.png"), 200, 200, true, true));
                name = new CTextField("Name", false, -1);
                shop = new CTextField("Shop", false, -1);
                email = new CTextField("Email", false, -1);
                phone = new CTextField("Phone", false, -1);
                gstin = new CTextField("GSTIN", false, -1);
                gstStateCode = new CTextField("GST State Code", false, -1);
                address = new CTextArea("Address", false, -1);
                address.setPrefWidth(200);

                name.setEditable(false);
                shop.setEditable(false);
                email.setEditable(false);
                phone.setEditable(false);
                gstin.setEditable(false);
                gstStateCode.setEditable(false);
                address.setEditable(false);
            }

            @Override
            public void designGUI() {
                this.setHgap(10);
                this.setVgap(10);
                this.setAlignment(Pos.TOP_CENTER);
                this.setPadding(new Insets(25));
                int col = 0, row = 0;

                add(icon, col, row++, 2, 1);

                add(name.getLabel(), col++, row);
                add(name, col--, row++);

                add(shop.getLabel(), col++, row);
                add(shop, col--, row++);

                add(email.getLabel(), col++, row);
                add(email, col--, row++);

                add(phone.getLabel(), col++, row);
                add(phone, col--, row++);

                add(gstin.getLabel(), col++, row);
                add(gstin, col--, row++);

                add(gstStateCode.getLabel(), col++, row);
                add(gstStateCode, col--, row++);

                add(address.getLabel(), col++, row);
                add(address, col--, row++);

                GridPane.setValignment(address.getLabel(), VPos.TOP);
                GridPane.setHalignment(icon, HPos.CENTER);
            }

            @Override
            public void registerListeners() {

            }

            public void populateFields() {
                if(dealerDTO != null) {
                    name.setText(dealerDTO.getName().isEmpty()? "--" : dealerDTO.getName());
                    shop.setText(dealerDTO.getShop().isEmpty() ? "--" : dealerDTO.getShop());
                    email.setText(dealerDTO.getEmail().isEmpty() ? "--" : dealerDTO.getEmail());
                    phone.setText(dealerDTO.getPhone().isEmpty() ? "--" : dealerDTO.getPhone());
                    gstin.setText(dealerDTO.getGstin().isEmpty() ? "--" : dealerDTO.getGstin());
                    gstStateCode.setText(dealerDTO.getGstStateCode() != null ? dealerDTO.getGstStateCode().getCode() + " - " + dealerDTO.getGstStateCode().getState() : "--");
                    address.setText(dealerDTO.getAddress().isEmpty() ? "--" : dealerDTO.getAddress());
                }
            }

            @Override
            public boolean checkSecurity() {
                return false;
            }
        }

        class BalanceAmountPanel extends AbstractBorderPane {

            private IndianRupeesField balanceField;

            @Override
            public void init() {
                this.setId("dealer-balance-panel");
                balanceField = new IndianRupeesField("Balance", false);
                balanceField.setEditable(false);
            }

            @Override
            public void designGUI() {
                setLeft(balanceField.getLabel());
                setCenter(balanceField);
                BorderPane.setAlignment(balanceField.getLabel(), Pos.CENTER);
                BorderPane.setMargin(balanceField, new Insets(10));
            }

            public void populateFields() {
                if(dealerDTO != null) {
                    balanceField.setCashAmount(dealerDTO.getBalance());
                    if(dealerDTO.getBalance() >= 0) {
                        balanceField.setStyle("-fx-text-fill: #00e600");
                    }
                    else {
                        balanceField.setStyle("-fx-text-fill: red");
                    }
                }else {
                    balanceField.setCashAmount(0.0);
                    balanceField.setStyle("-fx-text-fill: #00e600");
                }
            }

            @Override
            public void registerListeners() {

            }

            @Override
            public boolean checkSecurity() {
                return false;
            }
        }
    }
}
