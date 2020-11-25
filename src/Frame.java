import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {
    private Panel panel = new Panel();

    public Frame() {
        setBounds(600, 300, 400, 350);
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

    class ClickSortir implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    class ClickStart implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Virus v = new Virus(panel);
            Thread t = new Thread(v);
            t.start();
            panel.add(v);
        }
    }
}
