package Design;

import Perfomance.Get;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import static Loader.SoundLoader.coinSound;
import static Loader.ImageLoader.*;

public class DadoAnimacion extends JPanel
{
    private boolean lanzar;

    private Timer timer;
    private int yNum,times,rnd;

    private Random lb = new Random();

    public static JLabel jb;

//    public int test[] = {5,2,5};
//    public int index=0;

    public DadoAnimacion(Get clickHandler)
    {
        setLayout(null);
        timer = new Timer(50,a->{

            times--;
            if(yNum>=70)
                yNum-=30;
            if(times==0){
                lanzar=false;
                timer.stop();
            }
                repaint();

        });

        jb = new JLabel("Lanzar");
        jb.setFont(new Font(Font.MONOSPACED,Font.BOLD|Font.ITALIC,48));
        jb.setForeground(Color.red);
        jb.setBackground(Color.white);
        jb.setHorizontalAlignment(SwingConstants.CENTER);
        jb.setOpaque(true);
        jb.setCursor(MainWindow.customCursor);
        jb.setBounds(10,550,350,80);
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(jb.isEnabled()) {
                    lanzar = true;
                    rnd = lb.nextInt(6);
//                    rnd = test[index++];
                    clickHandler.get(rnd + 1);
                    yNum = 200;
                    times = 20;
                    coinSound.setFramePosition(0);
                    coinSound.start();
                    timer.start();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb.setForeground(Color.white);
                jb.setBackground(Color.red);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb.setForeground(Color.red);
                jb.setBackground(Color.white);
            }
        });
        add(jb);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(dado,80,150,this);
        if(lanzar)
        {
            g.drawImage(numbers[rnd],120,yNum,this);
        }
        g.drawImage(marioDado,80,340,this);
    }
}
