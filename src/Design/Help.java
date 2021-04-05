package Design;

import Loader.SoundLoader;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static Loader.ImageLoader.toadHelp;

public class Help extends JDialog
{
    private JPanel helpPanel,choicePanel;
    private ConfigLabel lblHow2Win;
    private Timer timer;
    private String help="Para jugar solo basta con iniciar el juego. Una vez hecho eso" +
            " basta con hacer click sobre el boton 'Lanzar' para que se simule el lanzamiento" +
            " de un dado, del cual, saldra un numero del 1 al 6." +
            " El jugador que llegue a la estrella (giratoria) gana. En caso de salir" +
            " un numero que se pase de la posicion estrella el jugador se le retornara a" +
            " las posiciones anteriores \n Sobre las animaciones de avanzar, se puede" +
            " disminuir o aumentar la velocidad de estas en el panel de configuraciones, pare" +
            " ello se debe de hacer click sobre el boton '>'.";
    private int currentIndex=0;
    private String[] info;
    JTextArea textArea = new JTextArea("");
    private Runnable onClose;

    public Help() throws HeadlessException
    {
        setSize(900,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        info = help.split(" ");


        textArea.setForeground(Color.white);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        textArea.setOpaque(false);

        textArea.setBackground(new Color(0, 0, 0, 0.6f));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);

        helpPanel = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                choicePanel.setBounds(0,0,Help.this.getWidth()-20,200);
                choicePanel.repaint();
                g.drawImage(toadHelp,
                        Help.this.getWidth()/2-(toadHelp.getWidth(this)/2),
                        Help.this.getHeight()-toadHelp.getHeight(this)-50,this);
            }
        };

        helpPanel.setBackground(new Color(0x00AA82));
        helpPanel.setLayout(null);

        choicePanel = new JPanel();
        choicePanel.setOpaque(false);
        choicePanel.setLayout(new BorderLayout());
        choicePanel.add(scrollPane);

//        lblHow2Win = new ConfigLabel("Como ganar?",new Color(0xffffff),new Color(0x096680),
//                ()->{},28);
//        lblHow2Win.setBounds(0,0,400,80);
//        choicePanel.add(lblHow2Win);

        timer = new Timer(70,a->{
            if(currentIndex==info.length)
                timer.stop();

            else{
                textArea.append(info[currentIndex++]+" ");
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                currentIndex=0;
                textArea.setText("");
                timer.stop();
                SoundLoader.helpTrack.stop();
                onClose.run();
            }
        });

        helpPanel.add(choicePanel);
        getContentPane().add(helpPanel);
    }

    public void showHelp(Runnable onClose){
        this.onClose=onClose;
        SoundLoader.helpTrack.setFramePosition(0);
        SoundLoader.helpTrack.loop(Clip.LOOP_CONTINUOUSLY);
        SoundLoader.helpTrack.start();
        timer.start();
        setVisible(true);
    }
}
