package sample;


import com.bfox1.ygocardcollector.data.YgoCard;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import com.bfox1.ygocardcollector.CardScouter;
import com.bfox1.ygocardcollector.YugiohCardCollector;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.LinkedHashMap;
import java.util.List;


public class Controller
{

    @FXML
    private ListView<Text> monsterList;

    @FXML
    private ListView<Text> extraMonsterList;

    @FXML
    private ListView<Text> spellList;

    @FXML
    private ListView<Text> trapList;

    @FXML
    private TextField cardName;

    //Fields for Card Data//

    @FXML
    private TextField nameField;

    @FXML
    private TextField rarityField;

    @FXML
    private TextField seriesField, attributeField, typeField, attackField, defenseField;

    @FXML
    private TextField levelField, rankField, scaleField, linkField;

    @FXML
    private TextArea pendulumField, effectField;

    private LinkedHashMap<String, Object> mapData;

    private String fileSafeName = "";

    private YgoOpenController.YgoPrivateCard card;

    private int counter = 0;

    private static Controller instance;


    public Controller()
    {
        instance = this;
    }

    public static Controller getInstance() {
        return instance;
    }


    public void initialize()
    {

    }



    public void clickedSearchButton(ActionEvent event)
    {
        if(!cardName.getText().equalsIgnoreCase(""))
        {
            YugiohCardCollector collector = new YugiohCardCollector(this);
            try
            {
                collector.beginSearchQuery(new CardScouter(cardName.getText(), "rare", "all"));
            }
            catch(ConnectException e)
            {
                //TODO: Display Message saying that it couldnt connect with server!
                System.out.println("Couldnt connect to server. No Worries :)");

                try
                {
                    YgoCard.loadCardData(cardName.getText(), 1);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }


        }
    }


    /**
     * Listener for when the Search Card Text Field has information in it and ENTER key was pressed.
     * @param event
     */
    public void typeCardEnter(ActionEvent event)
    {
        TextField text = (TextField) event.getSource();

        if(!text.getText().equalsIgnoreCase(""))
        {
            YugiohCardCollector collector = new YugiohCardCollector(this);

            try
            {
                collector.beginSearchQuery(new CardScouter(text.getText(), "rare", "all"));
            }
            catch (ConnectException | SocketTimeoutException e)
            {
                //TODO: Display Message saying that it couldnt connect with server!
                System.out.println("Couldnt connect to server. No Worries :)");
                try {
                    YgoCard.loadCardData(cardName.getText(), 1);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
            catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }


    }

    public void saveCardData()
    {

       /* String amount = quantityField.getText();
        String edition = editionField.getText();
        String price = priceField.getText();

        if(!nameField.getText().equalsIgnoreCase(""))
        {
            YgoCard card = new YgoCard(this.mapData, fileSafeName);

            card.addQuantity(amount);
            card.addEdition(edition);
            card.addPrice(price);
            card.serializeToJson();
        }*/
    }

    public void openCardData()
    {
        Stage stage = new Stage();

        try
        {
            Parent root = FXMLLoader.load(getClass().getResource("YgoOpen.fxml"));

            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UNDECORATED);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }


    /**
     * When you select a card from the selection lists, it will clear selection on other Lists.
     * @param event
     */
    public void clearSelections(MouseEvent event)
    {
        PickResult result = (PickResult)event.getPickResult();
        try {
            Text t = (Text) result.getIntersectedNode();

            String id = ((ListView<String>) event.getSource()).getId();
            String cardName = t.getText();

            if (id.equalsIgnoreCase("extraMonsterList")) {
                monsterList.getSelectionModel().clearSelection();
                trapList.getSelectionModel().clearSelection();
                spellList.getSelectionModel().clearSelection();
            } else if (id.equalsIgnoreCase("monsterList")) {
                extraMonsterList.getSelectionModel().clearSelection();
                trapList.getSelectionModel().clearSelection();
                spellList.getSelectionModel().clearSelection();
            } else if (id.equalsIgnoreCase("trapList")) {
                monsterList.getSelectionModel().clearSelection();
                extraMonsterList.getSelectionModel().clearSelection();
                spellList.getSelectionModel().clearSelection();
            } else if (id.equalsIgnoreCase("spellList")) {
                monsterList.getSelectionModel().clearSelection();
                trapList.getSelectionModel().clearSelection();
                extraMonsterList.getSelectionModel().clearSelection();
            }

            if(this.fileSafeName.equalsIgnoreCase(cardName) && counter == 2)
            {
                YugiohCardCollector collector = new YugiohCardCollector(this);
                if(!collector.loadClientData(cardName))
                {
                    collector.beginSearchQuery(new CardScouter(cardName, "rarity", "pop"));
                }
                counter = 0;
            }
            counter++;
            this.fileSafeName = cardName;
        }catch(Exception e)
        {

        }
    }

    public ListView<Text> getExtraMonsterList() {
        return extraMonsterList;
    }

    public ListView<Text> getSpellList() {
        return spellList;
    }

    public ListView<Text> getTrapList() {
        return trapList;
    }

    public ListView<Text> getMonsterList()
    {
        return monsterList;
    }

    /**
     * This Method parses Data provided by the server to place it in the GUI for the Client to overlook
     *
     * @param data
     */
    public void displayData(LinkedHashMap<String, Object> data)
    {
        this.mapData = data;
        this.card = new YgoCard(data, fileSafeName).toPrivateCard();
        this.nameField.setText((String) data.get("CardName"));
        this.attributeField.setText((String) data.get("CardAttribute"));
        this.seriesField.setText((String) data.get("CardSeries"));
        this.effectField.setText((String) data.get("CardDetails"));
        //this.editionField.setText((String) data.get("CardEdition"));
        this.rarityField.setText((String) data.get("CardRarity"));

        StringBuilder types = new StringBuilder();
        List<String> string = (List<String>) data.get("CardTypes");
        int i = 0;
        for(String s : string)
        {
            if(string.size() == i)
            {
                types.append(s);
            }
            else
            {
                types.append(s + "/");

            }
            ++i;

        }

        this.typeField.setText(types.toString());

        if(!attributeField.getText().equalsIgnoreCase("Spell") && !attributeField.getText().equalsIgnoreCase("Trap"))
        {
            this.attackField.setText(((String) data.get("atk")));
            this.defenseField.setText((String) data.get("def"));

            String level = String.valueOf((int)(double)data.get("level"));
            if(!level.equalsIgnoreCase("-1"))
            {
                this.levelField.setText(level);
            }

        }
        else
        {
            this.attackField.setText("");
            this.defenseField.setText("");
            this.levelField.setText("");
        }


        if(data.get("ExtraType") != null)
        {
            this.rankField.setText(( data.get("ExtraType.rank")) == null  ? "" : String.valueOf((int)(double)data.get("ExtraType.rank")));
            this.linkField.setText(( data.get("ExtraType.link")) == null  ? "" : String.valueOf((int)(double) data.get("ExtraType.link")));
            this.scaleField.setText(( data.get("ExtraType.scale")) == null ? "" : String.valueOf((int)(double) data.get("ExtraType.scale")));
            this.pendulumField.setText(data.get("ExtraType.pendulumDesc") == null ? "" : (String)data.get("ExtraType.pendulumDesc"));

        }
        else
        {
            this.rankField.setText("");
            this.linkField.setText("");
            this.scaleField.setText("");
            this.pendulumField.setText("");
        }

        //this.quantityField.setText(data.get("CardQuantity") == null ? "" : (String) data.get("CardQuantity"));
        //this.priceField.setText(data.get("CardPrice") == null ? "" : (String) data.get("CardPrice"));
    }

    public YgoOpenController.YgoPrivateCard getCard() {
        return card;
    }
}
