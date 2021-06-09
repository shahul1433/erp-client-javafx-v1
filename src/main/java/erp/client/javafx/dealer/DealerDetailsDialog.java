package erp.client.javafx.dealer;

import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.currency.IndianRupeesField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.layout.AbstractBorderPane;
import erp.client.javafx.layout.AbstractGridPane;
import erp.client.javafx.layout.AbstractVBoxPane;
import erp.client.javafx.session.AppSession;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.time.Month;

public class DealerDetailsDialog extends AbstractDialog {

    private LeftSidePanel leftSidePanel;
    private CenterPanel centerPanel;
    private DealerDTO dealer;
    private DealerDetailsService dealerDetailsService;

    public DealerDetailsDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
        getStage().setTitle("Dealer Details | "+ (dealer != null ? dealer.getName() : "--"));
    }

    @Override
    protected void init() {
        this.getStylesheets().add(DealerDetailsDialog.class.getResource("style.css").toExternalForm());
        dealerDetailsService = new DealerDetailsService(this);
        leftSidePanel = new LeftSidePanel();
        centerPanel = new CenterPanel();
        dealer = getArgument("dealer", DealerDTO.class);
        dealerDetailsService.getLedgerYearsByDealer(dealer);
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

    public DealerDTO getDealer() {
        return dealer;
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

            Label label;
            ComboBox<Integer> year;
            ComboBox<Month> month;
            IndianRupeesField openBalance, closedBalance;

            @Override
            public void init() {
                this.setId("dealer-ledger-top-bar");
                label = new Label("Ledger");
                label.setId("ledger-heading-label");
                year = new ComboBox<>();
                month = new ComboBox<>();
                openBalance = new IndianRupeesField("Open Balance", false);
                openBalance.setStyle("-fx-text-fill: #00e600");
//                openBalance.setEditable(false);
                closedBalance = new IndianRupeesField("Closed Balance", false);
//                closedBalance.setEditable(false);
                closedBalance.setStyle("-fx-text-fill: red");
            }

            @Override
            public void designGUI() {
                setLeft(label);
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

            }

            @Override
            public boolean checkSecurity() {
                return false;
            }
        }

        class ContentPane extends AbstractGridPane {

            @Override
            public void init() {
                this.setId("dealer-ledger-content");
            }

            @Override
            public void designGUI() {

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

            @Override
            public void init() {
                this.setId("dealer-ledger-bottom-bar");
                prev = new Button();
                prev.setId("prev-button");
                next = new Button();
                next.setId("next-button");
            }

            @Override
            public void designGUI() {
                setLeft(prev);
                BorderPane.setAlignment(prev, Pos.CENTER);

                setRight(next);
                BorderPane.setAlignment(next, Pos.CENTER);
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
                if(dealer != null) {
                    name.setText(dealer.getName().isEmpty()? "--" : dealer.getName());
                    shop.setText(dealer.getShop().isEmpty() ? "--" : dealer.getShop());
                    email.setText(dealer.getEmail().isEmpty() ? "--" : dealer.getEmail());
                    phone.setText(dealer.getPhone().isEmpty() ? "--" : dealer.getPhone());
                    gstin.setText(dealer.getGstin().isEmpty() ? "--" : dealer.getGstin());
                    gstStateCode.setText(dealer.getGstStateCode() != null ? dealer.getGstStateCode().getCode() + " - " + dealer.getGstStateCode().getState() : "--");
                    address.setText(dealer.getAddress().isEmpty() ? "--" : dealer.getAddress());
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
                if(dealer != null) {
                    balanceField.setCashAmount(dealer.getBalance());
                    if(dealer.getBalance() >= 0) {
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
