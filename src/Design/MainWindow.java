package Design;

import Perfomance.Player;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static Loader.ImageLoader.*;
import static Loader.SoundLoader.*;

public class MainWindow extends JFrame
{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            new StartScreen(new MainWindow());
        });
    }

    private GamePanel gamePanel;
    public Help helpDialog;
    public static DadoAnimacion dadoAnimacion;
    public static ConfigPanel configPanel;

    private boolean twoPlayers;

    private Player marioPlayer,browserPlayer;
    private boolean marioTurn=true;

    public static Cursor customCursor;
    static{
        customCursor = Toolkit.getDefaultToolkit().createCustomCursor
                (cursorMario, new Point(10, 10), "customCursor");
    }



    public static MetaPanel meta;

    public MainWindow()
    {
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);




        helpDialog = new Help();
        meta = new MetaPanel();

        configPanel = new ConfigPanel(this::reset,this);
        twoPlayers = configPanel.twoPlayers;
        dadoAnimacion = new DadoAnimacion( number ->{
            if(twoPlayers) {
                updatePlayer(marioTurn ? marioPlayer : browserPlayer, number);
                marioTurn = !marioTurn;
            }else updatePlayer(marioPlayer, number);
            return true;
        } );

        this.getRootPane().registerKeyboardAction(a->dadoAnimacion.execute(),
                KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        gamePanel = new GamePanel();

        this.getRootPane().registerKeyboardAction(a->gamePanel.swapPanels(),
                KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);


        marioPlayer = new Player(getImageIcon("marioplayer.gif"),
                getImageIcon("rmarioplayer.gif"),"Mario",gamePanel).reset();

        browserPlayer = new Player(getImageIcon("browser2.gif"),
                getImageIcon("rbrowser2.gif"),"Browser",gamePanel).reset();


        marioPlayer.setRivalPlayer(browserPlayer);
        browserPlayer.setRivalPlayer(marioPlayer);


        getContentPane().add(new JScrollPane(gamePanel));


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                theme1.setFramePosition(0);
                theme1.loop(Clip.LOOP_CONTINUOUSLY);
                theme1.start();
            }
        });


        finished.addLineListener(e -> {
            if (e.getType() == LineEvent.Type.STOP) {
                configPanel.lblReset.setEnabled(true);
            }else if(e.getType() == LineEvent.Type.START){
                configPanel.lblReset.setEnabled(false);
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    public void loadResources(){

        ghostSound.start();
        coinSound.start();
        pipeIn.start();
    }

    public void updateDelay(){
        marioPlayer.updateDelay();
        browserPlayer.updateDelay();
    }

    public void updatePlayer(Player player,int number)
    {
//        int pos[] = player.clear().walk(number).getPosition();
            player.clear().walk(number);
    }

    public void reset(){
        marioPlayer.clear().reset();
        browserPlayer.clear().reset();
        marioTurn=true;
        twoPlayers=configPanel.twoPlayers;
        theme1.stop();
        theme1.setFramePosition(0);
        theme1.loop(Clip.LOOP_CONTINUOUSLY);
        theme1.start();
        meta.setPlayer(null);
        DadoAnimacion.jb.setEnabled(true);
    }

}
