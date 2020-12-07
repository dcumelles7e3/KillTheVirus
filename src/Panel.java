import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/** JPanel que implementa MouseListener on es dibuixen els Virus
 * @version 3/12/2020
 * @author Dídac Cumelles Cenzano
 */
public class Panel extends JPanel implements MouseListener {
    private int x, y;
    private ArrayList<Virus> virus = new ArrayList<>();
    private int morts = 0;
    private JLabel label = new JLabel();

    /** El constructor activa el MouseListener i el Label comptador
     */
    public Panel() {
        addMouseListener(this);
        add(label, BorderLayout.NORTH);
    }

    /** Afegeix un Virus a l'Array de Virus
     * @param v Virus
     * @see Virus
     */
    public void add(Virus v) {
        virus.add(v);
    }

    /** Métode que es crida cada cop que es repinta el panell.
     * Pinta els virus segons el seu estat i el label
     * @param g El que serà dibuixat al Panel
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        label.setText("Virus morts: " + morts);

        Graphics2D g2 = (Graphics2D) g;
        for (Virus v : virus) {
            if (v.isAlive()) {
                g2.drawImage(v.getImatge(), (int) v.getX(), (int) v.getY(), this);
            } else {
                g2.drawImage(v.getImatgeMort(), (int) v.getX(), (int) v.getY(), this);
            }
        }
        Toolkit.getDefaultToolkit().sync();
    }

    /** Listener que rep les coordenades on hem fet clic i crea un hitbox per a facilitat de coincidir amb Virus
     * @param mouseEvent Clic de mouse
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();

        for (Virus v : virus) {
            if (v.isAlive()) {
                //Creem un "hitbox" del virus
                double radi = v.getRadi();
                double vx = v.getX();
                double vy = v.getY();
                double border = radi / 4 + 8;

                if (x < vx + radi + border / 3 && x > vx - border && y < vy + radi + border / 3 && y > vy - border) {
                    v.matar();
                    morts++;
                }
            }
        }
        repaint(); //es crida a paint()
    }

    /** Funció d'implementació obligatòria del MouseListener
     * @param mouseEvent Event amb el mouse
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    /** Funció d'implementació obligatòria del MouseListener
     * @param mouseEvent Event amb el mouse
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    /** Funció d'implementació obligatòria del MouseListener
     * @param mouseEvent Event amb el mouse
     */
    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    /** Funció d'implementació obligatòria del MouseListener
     * @param mouseEvent Event amb el mouse
     */
    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
