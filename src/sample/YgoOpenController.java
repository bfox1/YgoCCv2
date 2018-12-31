package sample;

import com.bfox1.ygocardcollector.data.YgoCard;
import com.sun.javafx.scene.control.skin.ContextMenuContent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class YgoOpenController
{

    private ObservableList<YgoPrivateCard> cardList;

    private YgoPrivateCard cardSafe;

    @FXML
    private TableView<YgoPrivateCard> infoTable;

    @FXML
    private TableColumn cardNameColumn, cardQuantityColumn, cardPriceColumn, cardLocationColumn, cardEditionColumn, cardConditionColumn;

    @FXML
    private TextField priceField, quantityField, locationField, conditionField, editionField;


    /**
     * Gets Initialized when GUI Opens, this will load the data of the card being Opened.
     */
    public void initialize()
    {
        this.cardList = FXCollections.observableArrayList();

        this.cardList.add(Controller.getInstance().getCard());
        this.cardSafe = Controller.getInstance().getCard();
        System.out.println(Controller.getInstance().getCard());
        infoTable.setItems(cardList);

        cardNameColumn.setCellValueFactory(new PropertyValueFactory<>("cardName"));

        cardQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cardPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        YgoPrivateCard card = Controller.getInstance().getCard();




    }

    /**
     * Action gets called when the Add button gets pressed on.
     */
    public void addButton()
    {
        String amount = quantityField.getText();
        String edition = editionField.getText();
        String price = priceField.getText();
        String condition = conditionField.getText();
    }
    /**
     * Action gets called when the Save Button gets pressed on.
     */
    public void saveButton()
    {
        String amount = quantityField.getText();
        String edition = editionField.getText();
        String price = priceField.getText();
        String condition = conditionField.getText();


            /*YgoCard card = new YgoCard(this.mapData, fileSafeName);

            card.addQuantity(amount);
            card.addEdition(edition);
            card.addPrice(price);
            card.serializeToJson();*/

    }

    /**
     * Actions gets called when the Delete Button gets Pressed On.
     */
    public void deleteButton()
    {

    }

    public void getMenuContext()
    {
        System.out.println("this is a test");
    }


    /**
     * Pretty Self Explanitory, this method is static so it can be called anywhere, to which you can
     * convert a YgoCard into CardData stored within the {@link YgoPrivateCard}.
     * @param card The YgoCard.
     * @return {@link YgoPrivateCard}
     */
    public static YgoPrivateCard createPrivateCard(YgoCard card)
    {
        return new YgoPrivateCard(card);
    }


    public static class YgoPrivateCard
    {
        private  SimpleStringProperty cardName;

        private  SimpleStringProperty price;

        private  SimpleStringProperty quantity;

        private SimpleStringProperty location;

        private SimpleStringProperty edition;

        private SimpleStringProperty cardCondition;

        private YgoCard card;
        public YgoPrivateCard(YgoCard card)
        {
            LinkedHashMap<String, Object> map = card.getCardData();

            this.cardName = toProperty((String) map.get("CardName"));
            this.price = toProperty((String) map.get("CardPrice") != null ? (String) map.get("CardPrice") : "");
            this.quantity = toProperty((String) map.get("CardQuantity") != null ? (String) map.get("CardQuantity") : "");
            this.location = toProperty((String) map.get("CardLocation") != null ? (String) map.get("CardLocation") : "");
            this.edition = toProperty((String) map.get("CardEdition") != null ? (String) map.get("CardEdition") : "");
            this.cardCondition = toProperty((String) map.get("CardCondition") != null ? (String) map.get("CardCondition") : "");

            System.out.println(this.price.getName());
            System.out.println(map.get("CardPrice"));
            System.out.println(this.cardName.getName());
            System.out.println(this.cardName.get());
            System.out.println(this.cardName.getValue());


        }

        public YgoCard getCardData()
        {
            return card;
        }


        public SimpleStringProperty toProperty(String n)
        {
            return new SimpleStringProperty(n);
        }

        public String getCardName() {
            return cardName.get();
        }

        public SimpleStringProperty cardNameProperty() {
            return cardName;
        }

        public String getPrice() {
            return price.get();
        }

        public SimpleStringProperty priceProperty() {
            return price;
        }

        public void setPrice(String price) {
            this.price.set(price);
        }

        public String getQuantity() {
            return quantity.get();
        }

        public SimpleStringProperty quantityProperty() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity.set(quantity);
        }

        public String getLocation() {
            return location.get();
        }

        public SimpleStringProperty locationProperty() {
            return location;
        }

        public void setLocation(String location) {
            this.location.set(location);
        }

        public String getEdition() {
            return edition.get();
        }

        public SimpleStringProperty editionProperty() {
            return edition;
        }

        public void setEdition(String edition) {
            this.edition.set(edition);
        }

        public String getCardCondition() {
            return cardCondition.get();
        }

        public SimpleStringProperty cardConditionProperty() {
            return cardCondition;
        }

        public void setCardCondition(String cardCondition) {
            this.cardCondition.set(cardCondition);
        }
    }
}
