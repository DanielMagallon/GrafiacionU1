package Perfomance;

import Design.DadoAnimacion;
import Design.GamePanel;
import Design.MainWindow;
import Loader.ImageLoader;

import javax.sound.sampled.Clip;
import javax.swing.*;

import java.util.Arrays;

import static Loader.SoundLoader.ghostSound;
import static Loader.SoundLoader.pipeIn;

public class Player
{
    private ImageIcon gifLeftPlayer,giftRightPlayer;

    public ImageIcon giftActual;
    private int rowPost,colPost;
    public String playerName;
    private Timer timer;
    private GamePanel gamePanel;
    public Clip clipWalking;

    private Player rivalPlayer;

    public Player(ImageIcon imageLIcon, ImageIcon imageRIcon, String name, GamePanel gamePanel,Clip clipWalking)
    {
        this.clipWalking=clipWalking;
        gifLeftPlayer = imageLIcon;
        giftRightPlayer = imageRIcon;
        playerName = name;
        this.gamePanel = gamePanel;
        timer = new Timer(500,a->{
            if(times==0) {
                timer.stop();
                int newPos[]  = Obstaculos.check(getPosition());

                if(newPos!=null) {
                    if (newPos[0] == -1) {
                        //Cayo en un fantasmas
                        gamePanel.tablero[rowPost][colPost].setIcon(ImageLoader.ghostGIF);
                        ghostSound.setFramePosition(0);
                        ghostSound.start();
                        //marioPlayer.clear(gamePanel.tablero);
                        update(newPos[1], newPos[2]);
                        gamePanel.tablero[rowPost][colPost].setIcon(giftActual);
                    } else {
                        //Cayo en tubo
                        clear();
                        pipeIn.setFramePosition(0);
                        pipeIn.start();
                        // marioPlayer.clear(gamePanel.tablero);
                        update(newPos[1], newPos[2]);
                        gamePanel.tablero[newPos[1]][newPos[2]].setIcon(giftActual);
                    }
                }
                isOnRival();
                DadoAnimacion.jb.setEnabled(true);
            }
            else
            {
                if (Obstaculos.onGhost(rowPost,colPost))
                    gamePanel.tablero[rowPost][colPost].setIcon(ImageLoader.ghostGIF);

                else clear();
                colPost++;
                if(colPost>6){
                    colPost = (colPost-7);
                    rowPost++;
                    giftActual =  rowPost%2==0 ? gifLeftPlayer : giftRightPlayer;
                }
                gamePanel.tablero[rowPost][colPost].setIcon(giftActual);
                times--;
            }
        });
    }

    private void isOnRival(){
        if(Arrays.equals(getPosition(),rivalPlayer.getPosition()))
        {
            gamePanel.tablero[rowPost][colPost].setIcon(ImageLoader.marioBrowser);
        }
    }

    public void setRivalPlayer(Player player){
        rivalPlayer=player;
    }

    public Player reset(){
        rowPost=0;
        giftActual = gifLeftPlayer;
        colPost=-1;
        return this;
    }

    public void update(int row,int col){
        rowPost = row;
        colPost = col;
        giftActual =  rowPost%2==0 ? gifLeftPlayer : giftRightPlayer;
    }

    private int times;

    public Player walk(int times){

       this.times = times;
       timer.start();
        DadoAnimacion.jb.setEnabled(false);
       return this;
    }

    public Player clear(){

        if(colPost!=-1)
        {
            if(Arrays.equals(getPosition(),rivalPlayer.getPosition()))
            {
                gamePanel.tablero[rowPost][colPost].setIcon(rivalPlayer.giftActual);
            }
            else gamePanel.tablero[rowPost][colPost].setIcon(null);
        }

        return this;
    }

    public int[] getPosition(){ return new int[]{rowPost, colPost}; }

    @Override
    public String toString() {
        return "Player{" +
                "rowPost=" + rowPost +
                ", colPost=" + colPost +
                ", playerName='" + playerName + '\'' +
                '}';
    }

    /*
            1-4 1-3 1-2 1-1 1-0
            0-0 0-1 0-2 0-3 0-4

            2+5 = 7
            7-4 = 3
     */
}
