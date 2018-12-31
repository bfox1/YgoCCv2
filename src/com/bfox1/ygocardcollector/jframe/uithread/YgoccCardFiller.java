/*
 * Created by JFormDesigner on Mon Dec 26 20:54:29 PST 2016
 */

package com.bfox1.ygocardcollector.jframe.uithread;

import com.bfox1.ygocardcollector.data.YgoCard;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;

/**
 * @author Bobby Gamiz
 */
public class YgoccCardFiller extends JFrame
{

    private String fileSafeName;
    private final LinkedHashMap<String, Object> mapData;
    public YgoccCardFiller(LinkedHashMap<String, Object> map, String fileSafeName)
    {
        initComponents();
        initMapData(map);
        this.mapData = map;
        this.fileSafeName = fileSafeName;
    }

    public YgoccCardFiller(YgoCard card, String fileSafeName)
    {
        initComponents();
        initMapData(card.getCardData());
        this.mapData = card.getCardData();
        this.fileSafeName = fileSafeName;
        this.spinner1.setValue(card.getCardData().get("quantity"));
    }

    public void initMapData(LinkedHashMap<String, Object> map)
    {
        this.getCName().setText((String) map.get("CardName"));
        this.getAttribute().setText((String) map.get("CardAttribute"));
        this.getSeries().setText((String) map.get("CardSeries"));
        this.textArea1.setText((String) map.get("CardDetails"));
        this.getCType().setText((String) map.get("CardTypes").toString());
        this.getRarity().setText(map.get("CardRarity").toString());
        this.getAtk().setText("N/A");
        this.getDef().setText("N/A");
        this.getLvl().setText("N/A");
        this.getScale().setText("N/A");

        if(map.containsKey("atk") && map.containsKey("def"))
        {
            System.out.println(map.get("atk"));
            this.getAtk().setText((String) map.get("atk"));
            this.getDef().setText((String) map.get("def"));

            if(map.containsKey("level"))
            {
                int lvl = (int) (double)map.get("level");
                if(lvl == 0)
                {
                    this.getLvl().setText(Integer.toString((int)(double)map.get("rank")));
                }
                else
                {
                    this.getLvl().setText(Integer.toString(lvl));
                }
            }

            if(map.containsKey("BlueScale"))
            {
                this.getScale().setText(Integer.toString((int)map.get("BlueScale")));
            }

            if(map.containsKey("penEffect"))
            {
                this.textArea2.setText((String) map.get("penEffect"));
            }


        }


    }

    public JTextField getAttribute() {
        return attribute;
    }

    public JTextField getLvl() {
        return lvl;
    }

    public JTextField getAtk() {
        return atk;
    }

    public JTextField getDef() {
        return def;
    }

    public JSpinner getSpinner1() {
        return spinner1;
    }

    public JScrollPane getPendulumEffect() {
        return pendulumEffect;
    }

    public JScrollPane getDescription() {
        return description;
    }

    public JTextField getScale() {
        return scale;
    }

    public JTextField getRarity() {
        return rarity;
    }

    public JTextField getSeries() {
        return series;
    }

    public JTextField getCName() {
        return cName;
    }

    public JTextField getCType() {
        return cType;
    }

    private void saveButtomClick(MouseEvent e)
    {
        /*YgoCard card = new YgoCard(this.mapData, fileSafeName);

        System.out.println(this.spinner1.getValue());
        card.addQuantity((Integer) this.spinner1.getValue());
        card.serializeToJson();
        SavedDialog dialog = new SavedDialog(this);
        */
        // TODO add your code here
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bobby Gamiz
        cardName = new JLabel();
        cName = new JTextField();
        packSeries = new JLabel();
        series = new JTextField();
        cardAttribute = new JLabel();
        attribute = new JTextField();
        cardRarity = new JLabel();
        rarity = new JTextField();
        cardType = new JLabel();
        cType = new JTextField();
        penScale = new JLabel();
        scale = new JTextField();
        level = new JLabel();
        lvl = new JTextField();
        cardDescription = new JLabel();
        description = new JScrollPane();
        textArea1 = new JTextArea();
        attack = new JLabel();
        atk = new JTextField();
        penDescription = new JLabel();
        pendulumEffect = new JScrollPane();
        textArea2 = new JTextArea();
        defense = new JLabel();
        def = new JTextField();
        quantity = new JLabel();
        spinner1 = new JSpinner();
        button2 = new JButton();

        //======== this ========
        setVisible(true);
        setMinimumSize(new Dimension(750, 750));
        Container contentPane = getContentPane();

        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bobby Gamiz
    private JLabel cardName;
    private JTextField cName;
    private JLabel packSeries;
    private JTextField series;
    private JLabel cardAttribute;
    private JTextField attribute;
    private JLabel cardRarity;
    private JTextField rarity;
    private JLabel cardType;
    private JTextField cType;
    private JLabel penScale;
    private JTextField scale;
    private JLabel level;
    private JTextField lvl;
    private JLabel cardDescription;
    private JScrollPane description;
    private JTextArea textArea1;
    private JLabel attack;
    private JTextField atk;
    private JLabel penDescription;
    private JScrollPane pendulumEffect;
    private JTextArea textArea2;
    private JLabel defense;
    private JTextField def;
    private JLabel quantity;
    private JSpinner spinner1;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
