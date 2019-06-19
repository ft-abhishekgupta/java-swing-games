import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;

class home extends JFrame {
    JLabel t;
    JButton snakes, tetris, snl, chess, pairup, sudoku, ttt;

    home() {
        Font f = new Font("Arial", Font.BOLD, 30);
        Font f2 = new Font("Arial", Font.ITALIC, 20);
        t = new JLabel("Java Games");
        t.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        t.setFont(f);

        snakes = new JButton("SNAKES");
        tetris = new JButton("TETRIS");
        snl = new JButton("SNAKES AND LADDER");
        chess = new JButton("CHESS");
        pairup = new JButton("PAIR UP");
        sudoku = new JButton("SUDOKU");
        ttt = new JButton("TIC TAC TOE");
        snakes.setFont(f2);
        tetris.setFont(f2);
        snl.setFont(f2);
        chess.setFont(f2);
        pairup.setFont(f2);
        sudoku.setFont(f2);
        ttt.setFont(f2);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridLayout(8, 1));
        c.add(t);
        c.add(snakes);
        c.add(tetris);
        c.add(snl);
        c.add(chess);
        c.add(pairup);
        c.add(sudoku);
        c.add(ttt);

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 200;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 300;
        setBounds(xx, yy, 400, 600);
        setVisible(true);

        thehandler h = new thehandler();
        snakes.addActionListener(h);
        tetris.addActionListener(h);
        snl.addActionListener(h);
        chess.addActionListener(h);
        pairup.addActionListener(h);
        sudoku.addActionListener(h);
        ttt.addActionListener(h);
    }

    private class thehandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == snakes) {
                new snakes();
                dispose();
            } else if (event.getSource() == tetris) {
                new tetris();
                dispose();
            } else if (event.getSource() == snl) {
                new snl();
                dispose();
            } else if (event.getSource() == chess) {
                new chess();
                dispose();
            } else if (event.getSource() == pairup) {
                new pairup();
                dispose();
            } else if (event.getSource() == sudoku) {
                new sudoku();
                dispose();
            } else if (event.getSource() == ttt) {
                new ttt();
                dispose();
            }
        }
    }
}