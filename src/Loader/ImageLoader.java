package Loader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader
{

    public static Image wallpaper = getSprite("tablerocuadros7x7.jpg");
    public static Image bigWallpapaer = getSprite("wallpaper.jpg");
    public static Image startScreen = getSprite("startscreen.jpg");


    public static Image cursorMario = getSprite("cursorMario.png");


    public static Image[] numbers = {
            getSprite("uno.png"),
            getSprite("dos.png"),
            getSprite("tres.png"),
            getSprite("cuatro.png"),
            getSprite("cinco.png"),
            getSprite("seis.png")
    };

//    public static Image start = getSprite("start.png");
    public static ImageIcon ghostGIF = getImageIcon("lfantasma2.gif");
    public static ImageIcon startGIF = getImageIcon("estrella300px.gif");
    public static ImageIcon marioBrowser = getImageIcon("mariobrowser.gif");

    public static Image itsmemario = getImageIcon("marioitsme.gif").getImage();


    public static Image dado = getImageIcon("ldado.gif").getImage();
    public static Image toadHelp = getImageIcon("toadhelp.png").getImage();
    public static Image marioDado = getImageIcon("mariodado200px.gif").getImage();
    public static Image browserDado = getImageIcon("browser200.gif").getImage();

    public static ImageIcon getImageIcon(String path)
    {
        return new ImageIcon(ImageLoader.class.getResource("/rsc/"+path));
    }
    public static Image getSprite(String path)
    {
        try {

            return ImageIO.read(ImageLoader.class.getResource("/rsc/"+path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
