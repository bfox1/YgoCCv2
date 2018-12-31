package com.bfox1.ygocardcollector.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bfox1 on 6/27/15.
 * In God we Trust.
 */
public class YgoCardCollectorFrame extends JFrame
{

     JTextField cardName, cardID, quantity, cardLocation;
     JRadioButton spell, trap, monster, first, unlimited, limited;
     JComboBox rarity;

     JButton save = new JButton("Save"), search = new JButton("Search"), remove = new JButton("Remove");

    private String[] rarityList =
            {
              "Normal",
                    "Rare",
                    "Super Rare",
                    "Ultra Rare",
                    "Ultimate Rare",
                    "Secret Rare",
                    "Ghost Rare",
                    "Black Rare",
                    "Mosaic Rare",
                    "Star Foil",
                    "Duel Terminal",
                    "Parallel Rare",
                    "Gold Rare"
            };

    public YgoCardCollectorFrame()
    {
        super("YGOCC");
        this.rarity = new JComboBox(rarityList);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        YgoCCPanel panel = new YgoCCPanel();
        panel.setLayout(new GridBagLayout());


        addItem(panel, new JLabel("Card Name:"), 0,0,1,1,GridBagConstraints.EAST);
        addItem(panel, new JLabel("Card Series:"), 0,1,1,1, GridBagConstraints.EAST);
        addItem(panel, new JLabel("Quantity:"),1,0, 1,1,GridBagConstraints.EAST);
        addItem(panel, new JLabel("Card Location"), 1,1, 1,1,GridBagConstraints.EAST);

        this.cardName = new JTextField(15);
        this.cardID = new JTextField(15);
        this.quantity = new JTextField(10);
        this.cardLocation = new JTextField(10);

        addItem(panel, cardName, 1,0, 2, 1, GridBagConstraints.WEST);
        addItem(panel, cardID, 1,1,1,1, GridBagConstraints.WEST);
        addItem(panel, quantity,2,0, 1,1,GridBagConstraints.EAST);
        addItem(panel, cardLocation,2,1, 1,1,GridBagConstraints.EAST);

        this.monster = new JRadioButton("Monster");
        this.spell = new JRadioButton("Spell");
        this.trap = new JRadioButton("Trap");

        buildRadioButtonBox(panel,"Card Type",Box.createVerticalBox(),GridBagConstraints.NORTHWEST, 1,2,1,1,
                monster, spell, trap);

        this.first = new JRadioButton("1st Edition");
        this.limited = new JRadioButton("Limited");
        this.unlimited = new JRadioButton("Unlimited");

        buildRadioButtonBox(panel, "Edition",Box.createVerticalBox(),GridBagConstraints.NORTHEAST, 1,2, 1,1,
                first, limited, unlimited);

        addItem(panel, rarity, 2, 2, 1,1,GridBagConstraints.NORTHWEST);

        ButtonListener listener = new ButtonListener();
        this.save.addActionListener(listener);
        this.search.addActionListener(listener);
        this.remove.addActionListener(listener);

        buildBoxBorderless(panel, Box.createHorizontalBox(),GridBagConstraints.NORTHWEST, 1, 4, 1, 1, save, search, remove);

        this.add(panel);
        this.pack();

        this.setVisible(true);

    }


    private void buildRadioButtonBox(JPanel panel, String borderName,Box box, int align, int x, int y,
                                     int width, int height, JComponent... buttons)
    {


        ButtonGroup buttonGroup = new ButtonGroup();

        for(int i = 0; i < buttons.length; i++)
        {
            if(buttons[i] instanceof JRadioButton)
            {
                buttonGroup.add((JRadioButton)buttons[i]);
                box.add(buttons[i]);
            }
            if(buttons[i] instanceof JButton)
            {
                buttonGroup.add((JButton)buttons[i]);
                box.add(buttons[i]);
            }
        }
        box.setBorder(BorderFactory.createTitledBorder(borderName));
        addItem(panel, box, x, y, width, height, align);
    }
    private void buildBoxBorderless(JPanel panel,Box box,int align, int x, int y,
                                     int width, int height, JComponent... buttons)
    {


        for(int i = 0; i < buttons.length; i++)
        {
            if(buttons[i] instanceof JRadioButton)
            {
                box.add(buttons[i]);
            }
            if(buttons[i] instanceof JButton)
            {
                box.add(buttons[i]);
                box.add(Box.createHorizontalStrut(20));

            }
        }
        addItem(panel, box, x, y, width, height, align);
    }

    private void addItem(JPanel p, JComponent c, int x, int y, int width, int height, int align)
    {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = height;
        gc.weightx = 100;
        gc.weighty = 100;
        gc.insets = new Insets(5,5,5,5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        p.add(c, gc);

    }

    private class ButtonListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == save)
            {
                String raritySelectedItem = (String)rarity.getSelectedItem();
                String cardType = "";
                String cardEdition = "";

                if(monster.isSelected())
                {
                    cardType = monster.getText();
                }
                else if(spell.isSelected())
                {
                    cardType = spell.getText();
                }
                else if(trap.isSelected())
                {
                    cardType = trap.getText();
                }
                else
                {
                    JOptionPane.showMessageDialog(save, "Please select a card Type!","Invalid selection", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(first.isSelected())
                {
                    cardEdition = first.getText();
                }
                else if(limited.isSelected())
                {
                    cardEdition = limited.getText();
                }
                else if(unlimited.isSelected())
                {
                    cardEdition = unlimited.getText();
                }
                else
                {
                    JOptionPane.showMessageDialog(save, "No card Edition was selected!","Invalid selection", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(cardName.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(save, "Please input a Card Name! \n IE: Blue Eyes White Dragon","Input not met", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(cardID.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(save, "Please input a Card ID! \n IE: <REDU-EN024>", "Input not met", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(cardLocation.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(save, "Please input the Card Location! \n" +
                            " See Instructions for determining location", "Input not met", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else if(quantity.getText().length() != 0)
                {
                    try
                    {
                        Integer.parseInt(quantity.getText());
                    }catch (NumberFormatException ef)
                    {
                        JOptionPane.showMessageDialog(save, "Please input a Number in quantity!","Input is invalid", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if(quantity.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(save, "Please input a card quantity! \n " +
                            "IE: <3>", "Input not met", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("Test");
                /**
                 * Creates Card here in this location
                 * or Finds the Card and adds the info.
                 *
                 *
                 */

                //TODO - Implement saving and updating


            }
            //TODO - Split button actions into methods
            if(e.getSource() == search)
            {

                String cardType = "";
                if(monster.isSelected())
                {
                    cardType = monster.getText();
                }
                else if(spell.isSelected())
                {
                    cardType = spell.getText();
                }
                else if(trap.isSelected())
                {
                    cardType = trap.getText();
                }
                else
                {
                    JOptionPane.showMessageDialog(save, "Please select a card Type!","Invalid selection", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(cardName.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(save, "Please input a Card Name! \n IE: Blue Eyes White Dragon","Input not met", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                /**
                 * Location Card information and find it
                 */

                //TODO - search for the card specified

                   // JOptionPane.showMessageDialog(search,
                          //  list, "Card List", JOptionPane.PLAIN_MESSAGE);

            }
            if(e.getSource() == remove)
            {
                String cardType = "";
                if(monster.isSelected())
                {
                    cardType = monster.getText();
                }
                else if(spell.isSelected())
                {
                    cardType = spell.getText();
                }
                else if(trap.isSelected())
                {
                    cardType = trap.getText();
                }
                else
                {
                    JOptionPane.showMessageDialog(save, "Please check your selection again!","Invalid selection", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                /**
                 * Location Card information and find it
                 */

                //TODO - search for the card specified


                                //JOptionPane.showMessageDialog(search,
                                  //      "Card has been successfully removed!", "Card Info", JOptionPane.PLAIN_MESSAGE);
            }



        }
        private void performSearchAction(String series, String Monster)
        {}
    }



}
