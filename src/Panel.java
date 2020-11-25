import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Panel extends JPanel implements MouseListener {

    private int x,y;
    private ArrayList<Virus> virus = new ArrayList<Virus>();
    public void add(Virus v){
        virus.add(v);
    }

    public Panel(){
        addMouseListener(this);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2 = (Graphics2D) g;
        for (Virus v : virus) {
            g2.fill(v.dibuixar());
        }

        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
        //printa per consola les coordenades del mouse en format (x,y)
        System.out.println("(" + x + "," + y + ")");

        double incx=x+15;
        double decx=x-15;
        double incy=y+15;
        double decy=y-15;
        for (Virus v : virus) {
            if (v.getX()<=incx&&v.getX()>=decx&&v.getY()<=incy&&v.getY()>=decy){
                v.matar();
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
