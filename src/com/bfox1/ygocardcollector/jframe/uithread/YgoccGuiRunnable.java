package com.bfox1.ygocardcollector.jframe.uithread;

/**
 * Created by bfox1 on 12/26/2016.
 */
public class YgoccGuiRunnable implements Runnable
{
    private YgoccJFrame frame;

    public YgoccGuiRunnable()
    {

    }
    @Override
    public void run()
    {
        this.frame = new YgoccJFrame();
    }

    public YgoccJFrame getFrame()
    {
        return frame;
    }
}
