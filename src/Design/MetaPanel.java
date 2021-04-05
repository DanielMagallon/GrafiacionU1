package Design;

import javax.swing.*;
import java.awt.*;

import static Loader.ImageLoader.startGIF;

public class MetaPanel extends JPanel
{

    private JLabel lblPlayer;

    public MetaPanel() {
        setDoubleBuffered(true);
        setOpaque(false);
        lblPlayer = new JLabel();
        lblPlayer.setBounds(20,150,100,100);
        add(lblPlayer);
        lblPlayer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setPlayer(ImageIcon icon){lblPlayer.setIcon(icon);}

    public boolean isWinner(){
        return lblPlayer.getIcon()!=null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(startGIF.getImage(),0,0,this);
    }
}
