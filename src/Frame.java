import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** JFrame que conté el Panel i botons
 * @version 3/12/2020
 * @author Dídac Cumelles Cenzano
 */
public class Frame extends JFrame {
    private Panel panel = new Panel();

    /** El constructor afegeix el Panel i el botons al frame
     * @see Panel
     */
    public Frame() {
        setBounds(600, 300, 600, 450);
        setTitle("Kill The Virus");
        add(panel, BorderLayout.CENTER);
        JPanel botonera = new JPanel();
        JButton boto1 = new JButton("Start pandemic");
        JButton boto2 = new JButton("Exit");
        botonera.add(boto1);
        botonera.add(boto2);
        boto1.addActionListener(new ClickStart());
        boto2.addActionListener(new ClickSortir());
        add(botonera, BorderLayout.SOUTH);
    }

    /** Implementa el Listener del botó Sortir
     */
    class ClickSortir implements ActionListener {
        /** Acaba el programa
         * @param actionEvent Clic al botó
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    /** Implementa el Listener del botó Start
     */
    class ClickStart implements ActionListener {
        /** Crea entre 3 i 7 virus
         * @param actionEvent Clic al botó
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            for (int i = 0; i<Virus.getRandom(3,7);i++){
                Runnable crear = () -> crearVirus();
                crear.run();
            }
        }

        /** Comença un Thread amb un Virus(Runnable) i l'afegeix al Panel
         * @see Virus
         */
        public void crearVirus(){
            Virus v = new Virus(panel);
            Thread t = new Thread(v);
            t.start();
            panel.add(v);
        }
    }
}
