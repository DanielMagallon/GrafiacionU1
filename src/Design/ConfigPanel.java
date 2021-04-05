package Design;

import Perfomance.Player;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel
{

    public ConfigLabel lblReset,lblUpVelocity,lblDownVelocity,lblTextVel,lblAbout,lblHelp;

    public ConfigPanel(Runnable reset,MainWindow mainWindow) {
        setLayout(null);

        setOpaque(false);
        lblReset = new ConfigLabel("Reiniciar", Color.green, Color.white, reset);
        lblReset.setBounds(10,10,350,80);

        JPanel velPanel = new JPanel();
        velPanel.setBounds(10,100,350,80);
        velPanel.setLayout(null);

        lblDownVelocity = new ConfigLabel("-", Color.red, Color.white, ()->{
            if(Player.delay>100)
                Player.delay-=100;
            else if(Player.delay>30)
                Player.delay-=10;

            mainWindow.updateDelay();
            lblTextVel.setText(Player.delay+"ms");
        });
        lblDownVelocity.setBounds(0,0,50,80);
        velPanel.add(lblDownVelocity);


        lblUpVelocity = new ConfigLabel("+", new Color(0x584EA7) , Color.white, ()->{
            if(Player.delay>=1000)
                Player.delay+=500;
            else if(Player.delay>=100)
                Player.delay+=100;
            else
                Player.delay+=10;
            mainWindow.updateDelay();
            lblTextVel.setText(Player.delay+"ms");
        });
        lblUpVelocity.setBounds(300,0,50,80);
        velPanel.add(lblUpVelocity);

        lblTextVel = new ConfigLabel(Player.delay+"ms", new Color(0xF7D610) , Color.white,
                ()->{
                    Player.delay=500;
                    mainWindow.updateDelay();
                    lblTextVel.setText(Player.delay+"ms");
                });
        lblTextVel.setBounds(50,0,250,80);
        velPanel.add(lblTextVel);

        lblHelp = new ConfigLabel("Ayuda", new Color(0xEA50DA) , Color.white, ()->{

        });
        lblHelp.setBounds(10,200,350,80);

        lblAbout = new ConfigLabel("Acerca de", new Color(0x28AEBB) , Color.white, ()->{

        });
        lblAbout.setBounds(10,300,350,80);

        this.add(lblReset);
        this.add(lblHelp);
        this.add(lblAbout);
        this.add(velPanel);
    }
}
