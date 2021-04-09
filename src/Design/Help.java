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
    private ConfigLabel next,previous;
    private Timer timer;
    private String help[]=
            {
                    "Para jugar solo basta con iniciar el juego. Una vez hecho eso" +
                    " basta con hacer click sobre el boton 'Lanzar' para que se simule el lanzamiento" +
                    " de un dado, del cual, saldra un numero del 1 al 6." +
                    " El jugador que llegue a la estrella (giratoria) gana. En caso de salir" +
                    " un numero que se pase de la posicion estrella el jugador se le retornara a" +
                    " las posiciones anteriores \n Sobre las animaciones de avanzar, se puede" +
                    " disminuir o aumentar la velocidad de estas en el panel de configuraciones, para" +
                    " ello se debe de hacer click sobre el boton '>' para que se vea el menu.",

                    "Para cambiar la jugabilidad a 1 o 2 jugadores, basta con que de igual manera" +
                            " sea presiondo el boton '>' y en la ultima opcion aparecera una opcion que dice" +
                            " 1 jugador/2 jugadores, la cual cambia con solo presionar. \n " +
                            "Para que los efectos de 1-2 jugadores se apliquen, es necesario reiniciar el juego",

                    "Para hacer un lanzamiento de dado, se puede usar el atajo de teclado Ctrl+L, de manera" +
                            " se evita tener que presionar sobre el boton lanzar. \n " +
                            "Para acceder a las configuraciones del boton '>' se puede usar el atajo de " +
                            "teclado Ctrl + flecha_derecha",
                            "A continuacion se deja un link de video tutorial: https://youtu.be/MuaaTfnxT4o \n " +
                                    "Tambien se anexa el link del repositorio donde se encuentra alojado el codigo: https://github.com/DanielMagallon/GrafiacionU1/tree/master"
            };

    private int currentRow, currentIndex=0;
    private String[] info;
    JTextArea textArea = new JTextArea("");
    private Runnable onClose;

    public Help() throws HeadlessException
    {
        setModal(true);
        setSize(900,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

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

                next.setBounds(helpPanel.getWidth()-420,choicePanel.getHeight()+10,400,80);
                previous.setBounds(20,choicePanel.getHeight()+10,400,80);

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

        next = new ConfigLabel("Siguiente",new Color(0xffffff),new Color(0x096680),
                ()-> comeon(true)
                ,38);
        helpPanel.add(next);

        previous = new ConfigLabel("Anterior",new Color(0xffffff),new Color(0x096680),
                ()->comeon(false),38);
        helpPanel.add(previous);

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

    private void comeon(boolean inc){


        if(inc && currentRow==help.length-1 || !inc && currentRow==0)
            return;
        currentRow+=inc ? 1 : -1;
        currentIndex=0;
        timer.stop();
        textArea.setText("");
        info = help[currentRow].split(" ");
        timer.start();
    }

    public void showHelp(Runnable onClose){

        this.onClose=onClose;
        SoundLoader.helpTrack.setFramePosition(0);
        SoundLoader.helpTrack.loop(Clip.LOOP_CONTINUOUSLY);
        SoundLoader.helpTrack.start();
        currentRow=-1;
        comeon(true);
        timer.start();
        setVisible(true);
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(()->new Help().showHelp(System.out::println));
//    }
}
