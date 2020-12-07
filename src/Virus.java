import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


/** Classe Runnable que representa un Virus i conté coordenades, mida, velocitat i les imatges
 * @version 3/12/2020
 * @author Dídac Cumelles Cenzano
 */
public class Virus implements Runnable{
    private double x, y, dx, dy;
    private int radi;
    private Image imatge, imatgeMort;
    private boolean alive = true;

    private static JPanel panel;

    /** Funció per a obtenir un int aleatori comprés entre dos (inclosos)
     * @param min Número minim (inclós)
     * @param max Número màxim (inclós)
     * @return Número aleatori
     */
    public static int getRandom(int min, int max) {
        return (int)Math.floor(Math.random() * (max - min + 1)) + min;
    }

    /** El constructor posiciona el Virus a unes coordenades random del Panel, amb un radi i velocitat aleatoris.
     * També carrega les imatges.
     * @param pan Panel static del Frame
     * @see Frame
     */
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

    /** Funció que crida constantment el run que canvia les coordenades amb control de límits
     */
    public void moure() {
        Rectangle2D limits=panel.getBounds();
        double width = limits.getWidth();
        double height = limits.getHeight();
        x += dx;
        y += dy;
        if (x + radi / 2 > width || x + radi / 2 < 0) dx = -dx;
        if (y + radi / 2 > height || y + radi / 2 < 0) dy = -dy;
    }

    /** Loop que mou el Virus i repinta el Panel mentre el Virus estigui viu
     */
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

    /** Funció per a establir que un Virus ha mort
     */
    public void matar(){
        this.alive=false;
    }

    /** Getter de x
     * @return x
     */
    public double getX() {
        return x;
    }

    /** Getter de y
     * @return y
     */
    public double getY() {
        return y;
    }

    /** Getter de radi
     * @return radi
     */
    public double getRadi() {
        return radi;
    }

    /** Getter d'imatge viu
     * @return imatge
     */
    public Image getImatge() {
        return imatge;
    }

    /** Getter d'imatge mort
     * @return imatge
     */
    public Image getImatgeMort() {
        return imatgeMort;
    }

    /** Per a comprovar si el Virus encara està viu
     * @return estat alive
     */
    public boolean isAlive() {
        return alive;
    }
}
