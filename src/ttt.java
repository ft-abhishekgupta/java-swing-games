import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ttt extends JFrame {
    static int n = 2;
    static int c = 0;
    private JButton b11, b12, b13, b21, b22, b23, b31, b32, b33;
    private JButton r;
    private JLabel i;
    final Icon b = new ImageIcon(getClass().getResource("images/ttt_blank.png"));
    final Icon x = new ImageIcon(getClass().getResource("images/ttt_X.png"));
    final Icon o = new ImageIcon(getClass().getResource("images/ttt_O.png"));
    Icon si = o;
    Icon ssi = x;

    public ttt() {
        super("TIC TAC TOE");
        setLayout(new FlowLayout());
        b11 = new JButton("  ", b);
        add(b11);
        b12 = new JButton("  ", b);
        add(b12);
        b13 = new JButton("  ", b);
        add(b13);
        b21 = new JButton("  ", b);
        add(b21);
        b22 = new JButton("  ", b);
        add(b22);
        b23 = new JButton("  ", b);
        add(b23);
        b31 = new JButton("  ", b);
        add(b31);
        b32 = new JButton("  ", b);
        add(b32);
        b33 = new JButton("  ", b);
        add(b33);
        r = new JButton("reset");
        add(r);
        r.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                b11.setIcon(b);
                b12.setIcon(b);
                b13.setIcon(b);
                b21.setIcon(b);
                b22.setIcon(b);
                b23.setIcon(b);
                b31.setIcon(b);
                b32.setIcon(b);
                b33.setIcon(b);
                si = o;
                ssi = x;
                i.setText(String.format("PLAYER 1 TURN"));
                n = 2;
                c = 0;
            }
        });
        i = new JLabel("PLAYER 1 TURN");
        add(i);
        thehandler h = new thehandler();
        b11.addActionListener(h);
        b12.addActionListener(h);
        b13.addActionListener(h);
        b21.addActionListener(h);
        b22.addActionListener(h);
        b23.addActionListener(h);
        b31.addActionListener(h);
        b32.addActionListener(h);
        b33.addActionListener(h);
        setResizable(false);
        setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 250;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 225;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(xx, yy, 500, 450);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent we) {
                new home();
            }
        });
    }

    private class thehandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (((JButton) (event.getSource())).getIcon() == b) {
                ((JButton) (event.getSource())).setIcon(si);
                if (si == x) {
                    si = o;
                    ssi = x;
                    n = 1;
                    i.setText(String.format("PLAYER " + n + " TURN"));
                    c++;
                } else {
                    si = x;
                    ssi = o;
                    n = 2;
                    i.setText(String.format("PLAYER " + n + " TURN"));
                    c++;
                }
            }
            if (b11.getIcon() == ssi && b12.getIcon() == ssi && b13.getIcon() == ssi
                    || b21.getIcon() == ssi && b22.getIcon() == ssi && b23.getIcon() == ssi
                    || b31.getIcon() == ssi && b32.getIcon() == ssi && b33.getIcon() == ssi
                    || b11.getIcon() == ssi && b21.getIcon() == ssi && b31.getIcon() == ssi
                    || b12.getIcon() == ssi && b22.getIcon() == ssi && b32.getIcon() == ssi
                    || b13.getIcon() == ssi && b23.getIcon() == ssi && b33.getIcon() == ssi
                    || b11.getIcon() == ssi && b22.getIcon() == ssi && b33.getIcon() == ssi
                    || b31.getIcon() == ssi && b22.getIcon() == ssi && b13.getIcon() == ssi) {
                if (((JButton) (event.getSource())).getIcon() == x) {
                    JOptionPane.showMessageDialog(null, String.format("Player 2 wins"));
                    r.doClick();
                } else if (((JButton) (event.getSource())).getIcon() == o) {
                    JOptionPane.showMessageDialog(null, String.format("Player 1 wins"));
                    r.doClick();
                }
            }
            if (c == 9) {
                JOptionPane.showMessageDialog(null, String.format("Match draw"));
                r.doClick();
            }

        }
    }
}