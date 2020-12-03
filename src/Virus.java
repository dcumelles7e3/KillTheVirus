import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Virus implements Runnable{
    private double x, y, dx, dy;
    private int radi;
    private Image imatge, imatgeMort;
    private boolean alive = true;

    private static JPanel panel;

    public static int getRandom(int min, int max) {
        return (int)Math.floor(Math.random() * (max - min + 1)) + min;
    }

    public Virus(JPanel pan) {
        panel = pan;
        x=getRandom(10,400);
        y=getRandom(10,300);
        radi=getRandom(30,60);

        dx=Math.random();
        if (getRandom(0,1)==0) dx=-dx;
        dy=Math.random();
        if (getRandom(0,1)==0) dy=-dy;

        try {
            BufferedImage bfViu = ImageIO.read(getClass().getResource("virus.png"));
            BufferedImage bfMort = ImageIO.read(getClass().getResource("deadvirus.png"));
            imatge = bfViu.getScaledInstance(radi,radi,Image.SCALE_DEFAULT);
            imatgeMort = bfMort.getScaledInstance(radi-10,radi-10,Image.SCALE_DEFAULT);
        }catch (IOException io){
            System.out.println("Resource d'imatge no trobat.");
        }

    }

    public void moure() {
        Rectangle2D limits=panel.getBounds();
        double width = limits.getWidth();
        double height = limits.getHeight();
        x += dx;
        y += dy;
        if (x + radi / 2 > width || x + radi / 2 < 0) dx = -dx;
        if (y + radi / 2 > height || y + radi / 2 < 0) dy = -dy;
    }

    @Override
    public void run() {
        do {
            moure();
            try{
                Thread.sleep(4);
            }catch (Exception e){}
            panel.repaint();
        }while(this.alive);
    }

    public void matar(){
        this.alive=false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getRadi() {
        return radi;
    }

    public Image getImatge() {
        return imatge;
    }

    public Image getImatgeMort() {
        return imatgeMort;
    }

    public boolean isAlive() {
        return alive;
    }
}
