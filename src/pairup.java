import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class pairup extends JFrame {
    JButton b[] = new JButton[20];
    static int p = -1, i, c = 0;
    int arr[] = { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9 };
    Font f = new Font("Arial", Font.BOLD, 60);

    pairup() {
        setTitle("PAIR UP - FIND THE PAIRS");
        shuffleArray(arr);
        Container c = getContentPane();
        c.setLayout(new GridLayout(4, 5));
        thehandler h = new thehandler();
        for (i = 0; i < 20; i++) {
            b[i] = new JButton(" ");
            b[i].setSize(20, 20);
            add(b[i]);
            b[i].addActionListener(h);
            b[i].setFont(f);
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 300;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 300;

        setBounds(xx, yy, 600, 600);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent we) {
                new home();
            }
        });
    }

    private class thehandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (int j = 0; j < 20; j++) {
                if (e.getSource() == b[j] && arr[j] != -1 && b[j].getText().equals(" ")) {
                    if (p == -1) {
                        b[j].setText(arr[j] + "");
                        p = j;
                    } else {
                        if ((arr[j] + "").equals(b[p].getText())) {
                            b[j].setText(arr[j] + "");
                            arr[j] = -1;
                            arr[p] = -1;
                            p = -1;
                        } else {
                            b[p].setText(" ");
                            p = -1;
                        }
                    }
                }
            }
            c = 0;
            for (int j = 0; j < 20; j++) {
                if (b[j].getText().equals(" ") == false)
                    c++;
            }
            if (c == 20) {
                JOptionPane.showMessageDialog(null, String.format("YOU WIN"));
                dispose();
            }
        }
    }

    static void shuffleArray(int[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
}
