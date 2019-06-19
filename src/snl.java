import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class snl extends JFrame {
    JButton b[] = new JButton[100];
    int i, j, pos = 0, pos2 = 1, v, ff;
    JPanel p1, p2;
    JLabel l, st, chance;
    Icon ii, p, pp2;
    JButton r;
    int k = 0;
    boolean ch = true;
    java.util.Random rr = new java.util.Random();

    snl() {
        setTitle("SNAKES N LADDER");
        Font f = new Font("Arial", Font.BOLD, 30);
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);
        p1 = new JPanel();
        p1.setLayout(null);
        r = new JButton("ROLL DICE");
        r.setBounds(610, 20, 100, 50);
        st = new JLabel(" 0 ");
        chance = new JLabel("P1");
        st.setBounds(610, 100, 100, 50);
        chance.setBounds(610, 200, 100, 50);
        st.setForeground(Color.WHITE);
        chance.setForeground(Color.WHITE);
        st.setFont(f);
        chance.setFont(f);
        c.add(r);
        c.add(st);
        c.add(chance);
        c.add(p1);
        for (i = 0; i < 100; i++)
            b[i] = new JButton();
        p1.setBounds(0, 0, 600, 600);
        l = new JLabel();
        ii = new ImageIcon(getClass().getResource("images/snl_board.png"));
        p = new ImageIcon(getClass().getResource("images/snl_player1.png"));
        pp2 = new ImageIcon(getClass().getResource("images/snl_player2.png"));
        b[0].setIcon(p);
        b[1].setIcon(pp2);
        l.setIcon(ii);
        l.setBounds(0, 0, 600, 600);

        for (i = 9; i >= 0; i--) {
            for (j = 0; j < 10; j++) {
                b[k].setBounds(j * 60, i * 60, 60, 60);
                b[k].setOpaque(false);
                b[k].setContentAreaFilled(false);
                b[k].setBorderPainted(false);
                p1.add(b[k]);
                k++;
            }
            i--;
            for (j = 9; j >= 0; j--) {
                b[k].setBounds(j * 60, i * 60, 60, 60);
                b[k].setOpaque(false);
                b[k].setContentAreaFilled(false);
                b[k].setBorderPainted(false);
                p1.add(b[k]);
                k++;
            }
        }

        r.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                v = 1 + rr.nextInt(6);
                st.setText("" + v);
                if (ch == true)
                    ff = pos + v;
                else
                    ff = pos2 + v;
                if (ff >= 99 || ff + 1 == 80) {
                    if (ch == true)
                        JOptionPane.showMessageDialog(null, String.format("BLUE WIN"));
                    else
                        JOptionPane.showMessageDialog(null, String.format("RED WIN"));
                    dispose();
                } else if (ff + 1 == 4) {
                    ff = 14;
                    ff--;
                } else if (ff + 1 == 9) {
                    ff = 31;
                    ff--;
                } else if (ff + 1 == 17) {
                    ff = 7;
                    ff--;
                } else if (ff + 1 == 21) {
                    ff = 42;
                    ff--;
                } else if (ff + 1 == 28) {
                    ff = 84;
                    ff--;
                } else if (ff + 1 == 51) {
                    ff = 67;
                    ff--;
                } else if (ff + 1 == 54) {
                    ff = 34;
                    ff--;
                } else if (ff + 1 == 62) {
                    ff = 19;
                    ff--;
                } else if (ff + 1 == 64) {
                    ff = 60;
                    ff--;
                } else if (ff + 1 == 71) {
                    ff = 91;
                    ff--;
                } else if (ff + 1 == 87) {
                    ff = 24;
                    ff--;
                } else if (ff + 1 == 93) {
                    ff = 73;
                    ff--;
                } else if (ff + 1 == 95) {
                    ff = 75;
                    ff--;
                } else if (ff + 1 == 98) {
                    ff = 79;
                    ff--;
                }
                if (ch == true) {
                    b[ff].setIcon(p);
                    if (pos2 != pos)
                        b[pos].setIcon(null);
                    else
                        b[pos].setIcon(pp2);
                    pos = ff;
                    if (v != 6) {
                        ch = false;
                        chance.setText("P2");
                    }
                } else {
                    b[ff].setIcon(pp2);
                    if (pos2 != pos)
                        b[pos2].setIcon(null);
                    else
                        b[pos2].setIcon(p);
                    pos2 = ff;
                    if (v != 6) {
                        ch = true;
                        chance.setText("P1");
                    }
                }
            }
        });
        p1.add(l);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 365;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 317;
        setBounds(xx, yy, 730, 635);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent we) {
                new home();
            }
        });
    }
}
