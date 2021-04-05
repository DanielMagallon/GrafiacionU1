package Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader
{

    public static Image wallpaper = getSprite("tablerocuadros7x7.jpg");

    public static Image cursorMario = getSprite("cursorMario.png");


    public static Image[] numbers = {
            getSprite("uno.png"),
            getSprite("dos.png"),
            getSprite("tres.png"),
            getSprite("cuatro.png"),
            getSprite("cinco.png"),
            getSprite("seis.png")
    };

    public static ImageIcon start = getImageIcon("start.png");
    public static ImageIcon ghostGIF = getImageIcon("lfantasma2.gif");
    public static ImageIcon startGIF = getImageIcon("estrella300px.gif");
    public static ImageIcon marioBrowser = getImageIcon("mariobrowser.gif");

    public static Image dado = getImageIcon("ldado.gif").getImage();
    public static Image marioDado = getImageIcon("mariodado200px.gif").getImage();

    public static ImageIcon getImageIcon(String path)
    {
        return new ImageIcon(ImageLoader.class.getResource("/rsc/").getPath()+ path);
    }
    public static Image getSprite(String path)
    {
        try {
            File file = new File(ImageLoader.class.getResource("/rsc/").getPath()+path);
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
