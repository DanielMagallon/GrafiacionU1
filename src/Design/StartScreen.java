package Design;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static Loader.ImageLoader.itsmemario;
import static Loader.ImageLoader.startScreen;
//import static Loader.SoundLoader.itsme;
import static Loader.SoundLoader.startSoundTrack;

public class StartScreen extends JFrame
{
    private JPanel panelScreen;
    private ConfigLabel lblStartGame,lblHelp,lblAbout;
    private int xMiddle,yStart;

    public StartScreen(MainWindow mainWindow) throws HeadlessException {
        setExtendedState(MAXIMIZED_BOTH);

        yStart = 100+itsmemario.getHeight(this)+50;
        panelScreen = new JPanel(){


            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                xMiddle = this.getWidth()/2-(itsmemario.getWidth(this)/2);

                lblStartGame.setBounds(xMiddle-50,yStart,500,80);
                lblHelp.setBounds(xMiddle-50,yStart+85,500,80);
                lblAbout.setBounds(xMiddle-50,yStart+170,500,80);

                g.drawImage(startScreen,0,0,this);
                g.drawImage(itsmemario,xMiddle+50,100,this);
            }
        };
        panelScreen.setLayout(null);

        lblStartGame = new ConfigLabel("Iniciar juego",new Color(0xFCFCFC),new Color(0xF50606),
                ()->{
                    startSoundTrack.stop();
                    this.dispose();
                    mainWindow.loadResources();
                    mainWindow.setVisible(true);
                },42);

        lblHelp = new ConfigLabel("Ayuda",new Color(0xFAAB09),new Color(0x07630B),
                ()->{
                    startSoundTrack.stop();
                    mainWindow.helpDialog.showHelp(()->startSoundTrack.start());
                },42);

        lblAbout = new ConfigLabel("Acerca de",new Color(0x12C1C1),new Color(0xEF9200),
                ()-> JOptionPane.showMessageDialog(StartScreen.this,
                        "Programa hecho por: \nEdgar Daniel Magallon Villanueva - " +
                                "17420571\n Efrain Tovar Meza 17420619","Creditos",
                        JOptionPane.INFORMATION_MESSAGE),42);

        panelScreen.add(lblStartGame);
        panelScreen.add(lblHelp);
        panelScreen.add(lblAbout);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {

                startSoundTrack.setFramePosition(0);
                startSoundTrack.loop(Clip.LOOP_CONTINUOUSLY);
                startSoundTrack.start();
            }
        });

        getContentPane().add(panelScreen);
        setVisible(true);
    }
}
