import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class chess extends JFrame {
    JButton b[][] = new JButton[8][8];
    static int p = 0, i, c = 0, j, x, y, aa, bbb, cc, flag = 0;
    boolean turn = true;
    Icon bb, bw, kb, kw, qb, qw, nb, nw, rw, rb, pb, pw, tt;

    chess() {
        setTitle("CHESS - WHITE'S TURN");
        Container c = getContentPane();
        c.setLayout(new GridLayout(8, 8));
        bb = new ImageIcon(getClass().getResource("images/bishop black.png"));
        bw = new ImageIcon(getClass().getResource("images/bishop white.png"));
        kb = new ImageIcon(getClass().getResource("images/king black.png"));
        kw = new ImageIcon(getClass().getResource("images/king white.png"));
        qb = new ImageIcon(getClass().getResource("images/queen black.png"));
        qw = new ImageIcon(getClass().getResource("images/queen white.png"));
        nb = new ImageIcon(getClass().getResource("images/knight black.png"));
        nw = new ImageIcon(getClass().getResource("images/knight white.png"));
        rw = new ImageIcon(getClass().getResource("images/rook white.png"));
        rb = new ImageIcon(getClass().getResource("images/rook black.png"));
        pb = new ImageIcon(getClass().getResource("images/pawn black.png"));
        pw = new ImageIcon(getClass().getResource("images/pawn white.png"));
        tt = new ImageIcon();
        thehandler h = new thehandler();
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                b[i][j] = new JButton("");
                b[i][j].setSize(100, 100);
                add(b[i][j]);
                p++;
                if (p % 2 == 0)
                    b[i][j].setBackground(Color.DARK_GRAY);
                else
                    b[i][j].setBackground(Color.WHITE);
                b[i][j].addActionListener(h);
            }
            p++;
        }
        for (i = 0; i < 8; i++) {
            b[i][1].setIcon(pw);
            b[i][6].setIcon(pb);
        }
        b[0][0].setIcon(rw);
        b[7][0].setIcon(rw);
        b[0][7].setIcon(rb);
        b[7][7].setIcon(rb);
        b[1][0].setIcon(nw);
        b[6][0].setIcon(nw);
        b[1][7].setIcon(nb);
        b[6][7].setIcon(nb);
        b[2][0].setIcon(bw);
        b[5][0].setIcon(bw);
        b[2][7].setIcon(bb);
        b[5][7].setIcon(bb);
        b[3][0].setIcon(kw);
        b[4][0].setIcon(qw);
        b[3][7].setIcon(qb);
        b[4][7].setIcon(kb);
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
            c++;
            if (c == 1) {
                tt = ((JButton) (e.getSource())).getIcon();
                for (i = 0; i < 8; i++) {
                    for (j = 0; j < 8; j++) {
                        if (b[i][j] == (JButton) (e.getSource())) {
                            x = i;
                            y = j;
                            break;
                        }
                    }
                }
                if (tt == null)
                    c = 0;
            }
            if (c != 1) {
                // PAWNS
                if (tt == pw && turn == true) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if ((x - i == 1 || i - x == 1) && j - y == 1
                                        && (b[i][j].getIcon() == pb || b[i][j].getIcon() == rb
                                                || b[i][j].getIcon() == kb || b[i][j].getIcon() == qb
                                                || b[i][j].getIcon() == nb || b[i][j].getIcon() == bb)) {
                                    if (b[i][j].getIcon() == kb) {
                                        JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                        dispose();
                                    }
                                    b[i][j].setIcon(pw);
                                    if (j == 7)
                                        b[i][j].setIcon(qw);
                                    b[x][y].setIcon(null);
                                    turn = false;
                                    setTitle("CHESS - BLACK'S TURN");
                                }
                                if (x == i && j - y == 1 && b[i][j].getIcon() == null) {
                                    b[i][j].setIcon(pw);
                                    if (j == 7)
                                        b[i][j].setIcon(qw);
                                    b[x][y].setIcon(null);
                                    turn = false;
                                    setTitle("CHESS - BLACK'S TURN");
                                }
                            }
                        }
                    }
                }
                if (tt == pb && turn == false) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if ((x - i == 1 || i - x == 1) && y - j == 1
                                        && (b[i][j].getIcon() == pw || b[i][j].getIcon() == rw
                                                || b[i][j].getIcon() == kw || b[i][j].getIcon() == qw
                                                || b[i][j].getIcon() == nw || b[i][j].getIcon() == bw)) {
                                    if (b[i][j].getIcon() == kw) {
                                        JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                        dispose();
                                    }
                                    b[i][j].setIcon(pb);
                                    if (j == 0)
                                        b[i][j].setIcon(qb);
                                    b[x][y].setIcon(null);
                                    setTitle("CHESS - WHITE'S TURN");
                                    turn = true;
                                }
                                if (x == i && y - j == 1 && b[i][j].getIcon() == null) {
                                    b[i][j].setIcon(pb);
                                    if (j == 0)
                                        b[i][j].setIcon(qb);
                                    b[x][y].setIcon(null);
                                    setTitle("CHESS - WHITE'S TURN");
                                    turn = true;
                                }
                            }
                        }
                    }
                }
                // KING
                if (tt == kw && turn == true) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if (Math.abs(x - i) < 2 && Math.abs(y - j) < 2 && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pb
                                                || b[i][j].getIcon() == rb || b[i][j].getIcon() == kb
                                                || b[i][j].getIcon() == qb || b[i][j].getIcon() == nb
                                                || b[i][j].getIcon() == bb)) {
                                    if (b[i][j].getIcon() == kb) {
                                        JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                        dispose();
                                    }
                                    b[i][j].setIcon(kw);
                                    b[x][y].setIcon(null);
                                    turn = false;
                                    setTitle("CHESS - BLACK'S TURN");
                                }
                            }
                        }
                    }
                }
                if (tt == kb && turn == false) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if (Math.abs(x - i) < 2 && Math.abs(y - j) < 2 && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pw
                                                || b[i][j].getIcon() == rw || b[i][j].getIcon() == kw
                                                || b[i][j].getIcon() == qw || b[i][j].getIcon() == nw
                                                || b[i][j].getIcon() == bw)) {
                                    if (b[i][j].getIcon() == kw) {
                                        JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                        dispose();
                                    }
                                    b[i][j].setIcon(kb);
                                    b[x][y].setIcon(null);
                                    setTitle("CHESS - WHITE'S TURN");
                                    turn = true;
                                }
                            }
                        }
                    }
                }

                // KNIGHTS
                if (tt == nw && turn == true) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if ((Math.abs(x - i) == 2 && Math.abs(y - j) == 1)
                                        || (Math.abs(x - i) == 1 && Math.abs(y - j) == 2) && (b[i][j].getIcon() == null
                                                || b[i][j].getIcon() == pb || b[i][j].getIcon() == rb
                                                || b[i][j].getIcon() == kb || b[i][j].getIcon() == qb
                                                || b[i][j].getIcon() == nb || b[i][j].getIcon() == bb)) {
                                    if (b[i][j].getIcon() == kb) {
                                        JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                        dispose();
                                    }
                                    b[i][j].setIcon(nw);
                                    b[x][y].setIcon(null);
                                    turn = false;
                                    setTitle("CHESS - BLACK'S TURN");
                                }
                            }
                        }
                    }
                }
                if (tt == nb && turn == false) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if ((Math.abs(x - i) == 2 && Math.abs(y - j) == 1)
                                        || (Math.abs(x - i) == 1 && Math.abs(y - j) == 2) && (b[i][j].getIcon() == null
                                                || b[i][j].getIcon() == pw || b[i][j].getIcon() == rw
                                                || b[i][j].getIcon() == kw || b[i][j].getIcon() == qw
                                                || b[i][j].getIcon() == nw || b[i][j].getIcon() == bw)) {
                                    if (b[i][j].getIcon() == kw) {
                                        JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                        dispose();
                                    }
                                    b[i][j].setIcon(nb);
                                    b[x][y].setIcon(null);
                                    setTitle("CHESS - WHITE'S TURN");
                                    turn = true;
                                }
                            }
                        }
                    }
                }
                // ROOK
                if (tt == rw && turn == true) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if ((x == i || y == j) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pb
                                                || b[i][j].getIcon() == rb || b[i][j].getIcon() == kb
                                                || b[i][j].getIcon() == qb || b[i][j].getIcon() == nb
                                                || b[i][j].getIcon() == bb)) {
                                    if (x == i) {
                                        aa = x;
                                        if (y < j) {
                                            bbb = y;
                                            cc = j;
                                        } else {
                                            cc = y;
                                            bbb = j;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        aa = y;
                                        if (x < i) {
                                            bbb = x;
                                            cc = i;
                                        } else {
                                            cc = x;
                                            bbb = i;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[bbb][aa].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kb) {
                                            JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(rw);
                                        b[x][y].setIcon(null);
                                        turn = false;
                                        setTitle("CHESS - BLACK'S TURN");
                                    }
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
                if (tt == rb && turn == false) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if ((x == i || y == j) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pw
                                                || b[i][j].getIcon() == rw || b[i][j].getIcon() == kw
                                                || b[i][j].getIcon() == qw || b[i][j].getIcon() == nw
                                                || b[i][j].getIcon() == bw)) {
                                    if (x == i) {
                                        aa = x;
                                        if (y < j) {
                                            bbb = y;
                                            cc = j;
                                        } else {
                                            cc = y;
                                            bbb = j;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        aa = y;
                                        if (x < i) {
                                            bbb = x;
                                            cc = i;
                                        } else {
                                            cc = x;
                                            bbb = i;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[bbb][aa].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kw) {
                                            JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(rb);
                                        b[x][y].setIcon(null);
                                        setTitle("CHESS - WHITE'S TURN");
                                        turn = true;
                                    }
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
                // BISHOP
                if (tt == bw && turn == true) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if (Math.abs(x - i) == Math.abs(j - y) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pb
                                                || b[i][j].getIcon() == rb || b[i][j].getIcon() == kb
                                                || b[i][j].getIcon() == qb || b[i][j].getIcon() == nb
                                                || b[i][j].getIcon() == bb)) {
                                    if ((x - i) / (y - j) == -1) {
                                        if (x < i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (--aa, ++bbb; aa > cc; aa--, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        if (x > i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (++aa, ++bbb; aa < cc; aa++, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kb) {
                                            JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(bw);
                                        b[x][y].setIcon(null);
                                        turn = false;
                                        setTitle("CHESS - BLACK'S TURN");
                                    }
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
                if (tt == bb && turn == false) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if (Math.abs(x - i) == Math.abs(j - y) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pw
                                                || b[i][j].getIcon() == rw || b[i][j].getIcon() == kw
                                                || b[i][j].getIcon() == qw || b[i][j].getIcon() == nw
                                                || b[i][j].getIcon() == bw)) {
                                    if ((x - i) / (y - j) == -1) {
                                        if (x < i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (--aa, ++bbb; aa > cc; aa--, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        if (x > i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (++aa, ++bbb; aa < cc; aa++, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kw) {
                                            JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(bb);
                                        b[x][y].setIcon(null);
                                        setTitle("CHESS - WHITE'S TURN");
                                        turn = true;
                                    }
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
                // QUEEN
                if (tt == qw && turn == true) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if (Math.abs(x - i) == Math.abs(j - y) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pb
                                                || b[i][j].getIcon() == rb || b[i][j].getIcon() == kb
                                                || b[i][j].getIcon() == qb || b[i][j].getIcon() == nb
                                                || b[i][j].getIcon() == bb)) {
                                    if ((x - i) / (y - j) == -1) {
                                        if (x < i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (--aa, ++bbb; aa > cc; aa--, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        if (x > i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (++aa, ++bbb; aa < cc; aa++, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kb) {
                                            JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(qw);
                                        b[x][y].setIcon(null);
                                        turn = false;
                                        setTitle("CHESS - BLACK'S TURN");
                                    }
                                    flag = 0;
                                }
                                if ((x == i || y == j) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pb
                                                || b[i][j].getIcon() == rb || b[i][j].getIcon() == kb
                                                || b[i][j].getIcon() == qb || b[i][j].getIcon() == nb
                                                || b[i][j].getIcon() == bb)) {
                                    if (x == i) {
                                        aa = x;
                                        if (y < j) {
                                            bbb = y;
                                            cc = j;
                                        } else {
                                            cc = y;
                                            bbb = j;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        aa = y;
                                        if (x < i) {
                                            bbb = x;
                                            cc = i;
                                        } else {
                                            cc = x;
                                            bbb = i;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[bbb][aa].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kb) {
                                            JOptionPane.showMessageDialog(null, String.format("WHITE WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(qw);
                                        b[x][y].setIcon(null);
                                        turn = false;
                                        setTitle("CHESS - BLACK'S TURN");
                                    }
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
                if (tt == qb && turn == false) {
                    for (i = 0; i < 8; i++) {
                        for (j = 0; j < 8; j++) {
                            if (b[i][j] == (JButton) (e.getSource())) {
                                if (Math.abs(x - i) == Math.abs(j - y) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pw
                                                || b[i][j].getIcon() == rw || b[i][j].getIcon() == kw
                                                || b[i][j].getIcon() == qw || b[i][j].getIcon() == nw
                                                || b[i][j].getIcon() == bw)) {
                                    if ((x - i) / (y - j) == -1) {
                                        if (x < i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (--aa, ++bbb; aa > cc; aa--, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        if (x > i) {
                                            aa = i;
                                            bbb = j;
                                            cc = x;
                                        } else {
                                            aa = x;
                                            bbb = y;
                                            cc = i;
                                        }
                                        for (++aa, ++bbb; aa < cc; aa++, bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kw) {
                                            JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(qb);
                                        b[x][y].setIcon(null);
                                        setTitle("CHESS - WHITE'S TURN");
                                        turn = true;
                                    }
                                    flag = 0;
                                }
                                if ((x == i || y == j) && (x != i || y != j)
                                        && (b[i][j].getIcon() == null || b[i][j].getIcon() == pw
                                                || b[i][j].getIcon() == rw || b[i][j].getIcon() == kw
                                                || b[i][j].getIcon() == qw || b[i][j].getIcon() == nw
                                                || b[i][j].getIcon() == bw)) {
                                    if (x == i) {
                                        aa = x;
                                        if (y < j) {
                                            bbb = y;
                                            cc = j;
                                        } else {
                                            cc = y;
                                            bbb = j;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[aa][bbb].getIcon() != null)
                                                flag = 1;
                                    } else {
                                        aa = y;
                                        if (x < i) {
                                            bbb = x;
                                            cc = i;
                                        } else {
                                            cc = x;
                                            bbb = i;
                                        }
                                        for (++bbb; bbb < cc; bbb++)
                                            if (b[bbb][aa].getIcon() != null)
                                                flag = 1;
                                    }
                                    if (flag == 0) {
                                        if (b[i][j].getIcon() == kw) {
                                            JOptionPane.showMessageDialog(null, String.format("BLACK WIN"));
                                            dispose();
                                        }
                                        b[i][j].setIcon(qb);
                                        b[x][y].setIcon(null);
                                        setTitle("CHESS - WHITE'S TURN");
                                        turn = true;
                                    }
                                    flag = 0;
                                }
                            }
                        }
                    }
                }
                c = 0;
            }
        }
    }
}
