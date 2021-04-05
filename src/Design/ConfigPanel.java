package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Loader.SoundLoader.coinSound;

public class ConfigPanel extends JPanel
{
    public ConfigPanel(Runnable reset) {
        setLayout(null);

        JLabel jb = new JLabel("Reiniciar");
        jb.setFont(new Font(Font.MONOSPACED,Font.BOLD|Font.ITALIC,48));
        jb.setForeground(Color.green);
        jb.setBackground(Color.white);
        jb.setHorizontalAlignment(SwingConstants.CENTER);
        jb.setOpaque(true);
        jb.setCursor(MainWindow.customCursor);
        jb.setBounds(10,10,350,80);
        jb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                reset.run();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jb.setForeground(Color.white);
                jb.setBackground(Color.green);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jb.setForeground(Color.green);
                jb.setBackground(Color.white);
            }
        });

        this.add(jb);
    }
}
