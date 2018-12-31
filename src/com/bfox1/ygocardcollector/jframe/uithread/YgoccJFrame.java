/*
 * Created by JFormDesigner on Mon Dec 26 18:49:49 PST 2016
 */

package com.bfox1.ygocardcollector.jframe.uithread;

import com.bfox1.ygocardcollector.CardScouter;
import com.bfox1.ygocardcollector.YugiohCardCollector;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * @author Bobby Gamiz
 */
public class YgoccJFrame extends JFrame
{
    public String fileSafeName;
    public YgoccJFrame() {
        initComponents();
    }

    public JList getList5() {
        return list5;
    }

    public JList getList2() {
        return list2;
    }

    public JList getList3() {
        return list3;
    }

    public JList getList4() {
        return list4;
    }

    private void searchButtonMouseClicked(MouseEvent e)
    {

        if(e.getButton() == 1)
        {
            searchQuery();
        }
    }

    private void searchQuery()
    {
        String s = SearchQuery.getText();
        if (!s.equalsIgnoreCase(""))
        {
            // v1 // YugiohCardCollector collector = new YugiohCardCollector(this);

           /* try
            {
               //v1 // collector.beginSearchQuery(new CardScouter(s, "rare", "all"));
            } catch (IOException | ClassNotFoundException ef)
            {
                ef.printStackTrace();
            }*/

        }
    }

    private void grabCardInfo(MouseEvent e)
    {
        String s = "";

        if(!list2.isSelectionEmpty())
        {
            s= (String) list2.getSelectedValue();
        }
        else if(!list3.isSelectionEmpty())
        {
            s = (String) list3.getSelectedValue();
        }
        else if(!list4.isSelectionEmpty())
        {
            s = (String) list4.getSelectedValue();
        }
        else if(!list5.isSelectionEmpty())
        {
            s = (String) list5.getSelectedValue();
        }
        if(!s.equalsIgnoreCase(""))
        {
           /* YugiohCardCollector collector = new YugiohCardCollector(this);

            try {
                collector.beginSearchQuery(new CardScouter(s, "rarity", "pop"));
                this.fileSafeName = s;
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } V1*/
        }
    }

    private void monsterValueChange(ListSelectionEvent e)
    {
        if(!e.getValueIsAdjusting())
        {
            JList list = (JList) e.getSource();
            clearOthers(list);
        }

    }

    private void clearOthers(JList list)
    {
        if(list != this.list2)
        {
            this.list2.clearSelection();
        }
        if(list != list3)
        {
            this.list3.clearSelection();
        }
        if(list != list4)
        {
            this.list4.clearSelection();
        }
        if(list != list5)
        {
            this.list5.clearSelection();
        }
    }

    private void list2ValueChanged(ListSelectionEvent e)
    {
        if(!e.getValueIsAdjusting())
        {
            JList list = (JList) e.getSource();
            clearOthers(list);
        }
    }

    private void list3ValueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting())
        {
            JList list = (JList) e.getSource();
            clearOthers(list);
        }
    }

    private void list4ValueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting())
        {
            JList list = (JList) e.getSource();
            clearOthers(list);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Bobby Gamiz
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label1 = new JLabel();
        scrollPane5 = new JScrollPane();
        list5 = new JList();
        scrollPane2 = new JScrollPane();
        list2 = new JList();
        scrollPane3 = new JScrollPane();
        list3 = new JList();
        scrollPane4 = new JScrollPane();
        list4 = new JList();
        button2 = new JButton();
        label = new JLabel();
        SearchQuery = new JTextField();
        button1 = new JButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(new Dimension(100, 100));
        Container contentPane = getContentPane();

        //---- label2 ----
        label2.setText("Monster");


        //---- label3 ----
        label3.setText("Extra");


        //---- label4 ----
        label4.setText("Trap");


        //---- label5 ----
        label5.setText("Spell");


        //---- label1 ----
        label1.setText("Discovered Cards:");


        //======== scrollPane5 ========
        {

            //---- list5 ----
            list5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list5.addListSelectionListener(e -> monsterValueChange(e));
            scrollPane5.setViewportView(list5);
        }



        //======== scrollPane2 ========
        {

            //---- list2 ----
            list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list2.addListSelectionListener(e -> list2ValueChanged(e));
            scrollPane2.setViewportView(list2);
        }


        //======== scrollPane3 ========
        {

            //---- list3 ----
            list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list3.addListSelectionListener(e -> list3ValueChanged(e));
            scrollPane3.setViewportView(list3);
        }

        //======== scrollPane4 ========
        {

            //---- list4 ----
            list4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list4.addListSelectionListener(e -> list4ValueChanged(e));
            scrollPane4.setViewportView(list4);
        }


        //---- button2 ----
        button2.setText("Grab Card Information");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                grabCardInfo(e);
            }
        });


        //---- label ----
        label.setText("Card Name:");



        //---- button1 ----
        button1.setText("Search");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchButtonMouseClicked(e);
            }
        });

        pack();
        setLocationRelativeTo(getOwner());

        //---- SearchedQuery ----
        SearchQuery.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 10)
                {
                    searchQuery();
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Bobby Gamiz
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label1;
    private JScrollPane scrollPane5;
    private JList list5;
    private JScrollPane scrollPane2;
    private JList list2;
    private JScrollPane scrollPane3;
    private JList list3;
    private JScrollPane scrollPane4;
    private JList list4;
    private JButton button2;
    private JLabel label;
    private JTextField SearchQuery;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
