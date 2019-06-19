import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class sudoku extends JFrame {
    JTextField b[] = new JTextField[81];
    JPanel pp = new JPanel();
    static int p = -1, i, c = 0;
    int arr[] = { 0, 7, 1, 0, 9, 0, 8, 0, 0, 0, 0, 0, 3, 0, 6, 0, 0, 0, 4, 9, 0, 0, 0, 0, 7, 0, 5, 0, 1, 0, 9, 0, 0, 0,
            0, 0, 9, 0, 2, 0, 0, 0, 6, 0, 3, 0, 0, 0, 0, 0, 8, 0, 2, 0, 8, 0, 5, 0, 0, 0, 0, 7, 6, 0, 0, 0, 6, 0, 7, 0,
            0, 0, 0, 0, 7, 0, 4, 0, 3, 5, 0 };
    int ans[] = { 3, 7, 1, 5, 9, 4, 8, 6, 2, 5, 2, 8, 3, 7, 6, 1, 9, 4, 4, 9, 6, 2, 8, 1, 7, 3, 5, 6, 1, 4, 9, 2, 3, 5,
            8, 7, 9, 8, 2, 7, 1, 5, 6, 4, 3, 7, 5, 3, 4, 6, 8, 9, 2, 1, 8, 4, 5, 1, 3, 9, 2, 7, 6, 2, 3, 9, 6, 5, 7, 4,
            1, 8, 1, 6, 7, 8, 4, 2, 3, 5, 9 };
    JButton s = new JButton("SUBMIT");
    Font f = new Font("Arial", Font.BOLD, 30);

    sudoku() {
        setTitle("SUDOKU");
        Container c = getContentPane();
        c.setLayout(new GridLayout(10, 9, 5, 5));
        c.setBackground(Color.GRAY);
        thehandler h = new thehandler();
        for (i = 0; i < 81; i++) {
            if (arr[i] == 0) {
                b[i] = new JTextField("", 2);
                b[i].setFocusable(true);
            } else {
                b[i] = new JTextField(arr[i] + "", 2);
                b[i].setEditable(false);
            }
            add(b[i]);
            b[i].setHorizontalAlignment((int) CENTER_ALIGNMENT);
            b[i].setFont(f);
        }
        s.setSize(200, 100);
        s.addActionListener(h);
        c.add(s);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 350;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 350;
        setBounds(xx, yy, 700, 700);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent we) {
                new home();
            }
        });
    }

    private class thehandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            c = 0;
            for (int j = 0; j < 81; j++) {
                if (b[j].getText().trim().equals(ans[j] + ""))
                    c++;
            }
            if (c == 81) {
                JOptionPane.showMessageDialog(null, String.format("YOU WIN"));
                dispose();
            } else
                JOptionPane.showMessageDialog(null, String.format("TRY HARDER"));
        }
    }
}