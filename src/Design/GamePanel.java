package Design;

import Perfomance.Get;

import javax.swing.*;
import java.awt.*;

import static Loader.ImageLoader.*;
import static Perfomance.Obstaculos.ghostPosition;

public class GamePanel extends JPanel {

    public JLabel[][] tablero = new JLabel[7][7];

    private ConfigLabel lblSwap;

    public GamePanel() {
        //            setDoubleBuffered(true);
        setPreferredSize(new Dimension(1850, 1050));
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(false);

        int size = 93;
        int x, y = 0, j, inc;

        for (int i = 6; i >= 0; i--) {
            x = 0;
            Get get;
            if (i % 2 != 0) {
                j = 6;
                get = v -> v >= 0;
                inc = -1;
            } else {
                j = 0;
                get = v -> v < 7;
                inc = 1;
            }

            for (; get.get(j); j += inc) {
                tablero[i][j] = new JLabel();
//                tablero[i][j].setOpaque(false);
                tablero[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                tablero[i][j].setVerticalAlignment(SwingConstants.CENTER);
                tablero[i][j].setBounds(x, y, size, size);
//                tablero[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                this.add(tablero[i][j]);
                x += size;
            }
            y += size;
        }

        MainWindow.meta.setBounds(660, 10, 150, 137);

        this.add(MainWindow.meta);

        swapPanels();

        this.add(MainWindow.dadoAnimacion);
        this.add(MainWindow.configPanel);

        lblSwap = new ConfigLabel(">",new Color(0x2B9AB8),Color.white,this::swapPanels);
        lblSwap.setBounds(700,250,50,50);
        this.add(lblSwap);

        this.setBorder(BorderFactory.createLineBorder(Color.black));

        for (int ghostPoints[] : ghostPosition) {
            tablero[ghostPoints[0]][ghostPoints[1]].setIcon(ghostGIF);
        }

    }

    public boolean swaped;

    public void swapPanels(){

        if(swaped){
            MainWindow.configPanel.setBounds(840, 200, 380, 400);
            MainWindow.dadoAnimacion.setBounds(1350, 10, 400, 660);
        }
        else {
            MainWindow.dadoAnimacion.setBounds(840, 10, 400, 660);
            MainWindow.configPanel.setBounds(1350, 200, 380, 400);
        }

        swaped=!swaped;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bigWallpapaer,0,0,this);
        g.drawImage(wallpaper, 0, 0, 650, 650, this);

    }
}
