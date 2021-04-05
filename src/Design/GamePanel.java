package Design;

import Perfomance.Get;

import javax.swing.*;
import java.awt.*;

import static Loader.ImageLoader.ghostGIF;
import static Loader.ImageLoader.wallpaper;
import static Perfomance.Obstaculos.ghostPosition;

public class GamePanel extends JPanel {

    private final MainWindow mainWindow;
    public JLabel tablero[][] = new JLabel[7][7];
    private Get get;

    public GamePanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
//            setDoubleBuffered(true);
        setPreferredSize(new Dimension(1750, 1050));
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(false);

        int size = 93;
        int x, y = 0, j, inc;

        for (int i = 6; i >= 0; i--) {
            x = 0;
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

        mainWindow.dadoAnimacion.setBounds(840, 10, 400, 660);
        this.add(mainWindow.dadoAnimacion);

        MainWindow.meta.setBounds(660, 10, 150, 137);
        this.add(MainWindow.meta);

        mainWindow.configPanel.setBounds(1300, 10, 360, 300);
        this.add(mainWindow.configPanel);

        for (int ghostPoints[] : ghostPosition) {
            tablero[ghostPoints[0]][ghostPoints[1]].setIcon(ghostGIF);
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;

//            g2.drawImage(getNextGhostWalking(),x,200,this);
        g.drawImage(wallpaper, 0, 0, 650, 650, this);

    }
}
