package Design;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConfigLabel extends JLabel implements MouseListener {

    private final Color primaryColor, secondColor;
    private final Runnable clickHandler;
    private boolean withBackground;

    public ConfigLabel(String text, Color primaryColor, Color secondaryColor, Runnable clickHandler) {
        super(text);
        withBackground=true;
        this.primaryColor = primaryColor;
        this.secondColor = secondaryColor;
        this.clickHandler = clickHandler;
        setFont(new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, 48));
        setOpaque(true);
        setForeground(secondColor);
        setBackground(primaryColor);
        setCursor(MainWindow.customCursor);
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
    }


    public ConfigLabel(String text, Color fgPrim, Color fgSec, Runnable clickHandler,int size) {
        super(text);
        withBackground=false;
        this.primaryColor = fgPrim;
        this.secondColor = fgSec;
        this.clickHandler = clickHandler;
        setFont(new Font(Font.MONOSPACED, Font.BOLD | Font.ITALIC, size));
        setOpaque(false);
        setForeground(secondColor);
        setBackground(primaryColor);
        setCursor(MainWindow.customCursor);
        setHorizontalAlignment(SwingConstants.CENTER);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (this.isEnabled() && clickHandler != null) {
            clickHandler.run();
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

        if (withBackground) {
            setForeground(primaryColor);
            setBackground(secondColor);
        }else{
            setForeground(primaryColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

        if (withBackground) {
            setForeground(secondColor);
            setBackground(primaryColor);
        }else{
            setForeground(secondColor);
        }
    }
}
