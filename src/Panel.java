import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Panel extends JPanel implements MouseListener {
    private int x, y;
    private ArrayList<Virus> virus = new ArrayList<>();
    private int morts = 0;
    private JLabel label = new JLabel();

    public void add(Virus v) {
        virus.add(v);
    }

    public Panel() {
        addMouseListener(this);
        add(label, BorderLayout.NORTH);
    }

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

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
