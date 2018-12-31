package com.bfox1.ygocardcollector.jframe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by bfox1 on 6/27/15.
 * In God we Trust.
 */
public class ButtonListener implements ActionListener {

    private JButton buttonOK;
    private YgoCardCollectorFrame frame;

    public ButtonListener(JButton buttonOK, YgoCardCollectorFrame frame)
    {
        this.buttonOK = buttonOK;
        this.frame = frame;

    }

    private JFrame dialogMessage()
    {
        JFrame frame = new JFrame();
        frame.setSize(325, 100);
        frame.setTitle("Error");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Invalid input"));
        frame.add(panel);
        JButton button = new JButton();
        button.setText("ok");

        return null;
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(actionEvent.getSource() == buttonOK)
        {

        }


    }
}
