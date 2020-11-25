import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.net.URL;

public class Virus implements Runnable{
    private double x = 0; //perquè la funció ellipse utilitza double
    private double y = 0;
    private double dx = 0.1;
    private double dy = 0.1;
    private int radi = 15;;
    private Image imatge;
    private boolean alive = true;

    private static JPanel panel;

    public Virus(JPanel pan) {
        panel = pan;
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

    public Ellipse2D dibuixar() {
        return new Ellipse2D.Double(x, y, radi, radi);
    }

    public void matar(){
        this.alive=false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getRadi() {
        return radi;
    }

    public void setRadi(int radi) {
        this.radi = radi;
    }

    public Image getImatge() {
        return imatge;
    }

    public void setImatge(Image imatge) {
        this.imatge = imatge;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }



}
