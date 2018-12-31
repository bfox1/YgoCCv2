package com.bfox1.ygocardcollector.jframe.uithread;

import javax.swing.*;

/**
 * Created by bfox1 on 12/26/2016.
 */
public class YgoccMainGui
{
    private final YgoccJFrame frame;

    public YgoccMainGui()
    {
        YgoccGuiRunnable gui = new YgoccGuiRunnable();
        SwingUtilities.invokeLater(gui);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        this.frame = gui.getFrame();
    }

    public YgoccJFrame getFrame()
    {
        return frame;
    }
}
