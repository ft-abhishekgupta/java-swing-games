import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;

class tetris extends JFrame {
    int j, f = 1, n = 0, wx, wy, max = 10000, hh = 0, nb1 = 0, nb2 = 1, nb3 = 2, nb4 = 3, i, d = 0, o = 1, m, l;
    JLabel score;
    JPanel pnl, pnl2;
    Timer tmr;
    JButton[] b = new JButton[max];
    boolean flag = false, finish = false;
    JButton res = new JButton("RESET");
    int arr[][] = new int[25][20];
    java.util.Random rr = new java.util.Random();

    tetris() {
        Container container = this.getContentPane();
        container.setLayout(null);
        this.setDefaultCloseOperation(2);
        pnl = new JPanel();
        pnl.setBounds(0, 0, 400, 500);
        pnl.setLayout(null);
        container.add(pnl);
        pnl.setBackground(Color.DARK_GRAY);
        pnl2 = new JPanel();
        pnl2.setBounds(400, 0, 100, 500);
        pnl2.setLayout(null);
        pnl.setBackground(Color.DARK_GRAY);
        score = new JLabel("Score = 0");
        score.setBounds(10, 30, 100, 50);
        pnl2.add(score);
        res.setBounds(10, 0, 100, 30);
        res.setOpaque(false);
        res.setContentAreaFilled(false);
        pnl2.add(res);
        container.add(pnl2);
        for (j = 0; j < max; j++) {
            b[j] = new JButton("");
            b[j].setSize(20, 20);
            pnl.add(b[j]);
            b[j].setLocation(-100, -20);
        }
        setTitle("TETRIS - (MOVE - Arrow Keys ROTATE - Space)");
        pnl.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent keyEvent) {
            }

            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 32)
                    rotate();
            }

            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 37) {
                    boolean bb = true;
                    if (b[nb1].getLocation().getX() >= 20 && b[nb2].getLocation().getX() >= 20
                            && b[nb3].getLocation().getX() >= 20 && b[nb4].getLocation().getX() >= 20)
                        bb = true;
                    else
                        bb = false;
                    for (j = nb1; j <= nb4; j++) {
                        for (i = 0; i <= nb4 - 4; i++) {
                            if (b[i].getLocation().getX() == b[j].getLocation().getX() - 20
                                    && b[i].getLocation().getY() == b[j].getLocation().getY()
                                    || b[j].getLocation().getY() >= 480)
                                bb = false;
                        }
                    }
                    if (bb == true) {
                        b[nb1].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY());
                        b[nb2].setLocation((int) b[nb2].getLocation().getX() - 20, (int) b[nb2].getLocation().getY());
                        b[nb3].setLocation((int) b[nb3].getLocation().getX() - 20, (int) b[nb3].getLocation().getY());
                        b[nb4].setLocation((int) b[nb4].getLocation().getX() - 20, (int) b[nb4].getLocation().getY());
                    }
                }
                if (keyEvent.getKeyCode() == 39) {
                    boolean bb = true;
                    if (b[nb1].getLocation().getX() < 380 && b[nb2].getLocation().getX() < 380
                            && b[nb3].getLocation().getX() < 380 && b[nb4].getLocation().getX() < 380)
                        bb = true;
                    else
                        bb = false;
                    for (j = nb1; j <= nb4; j++) {
                        for (i = 0; i <= nb4 - 4; i++) {
                            if (b[i].getLocation().getX() == b[j].getLocation().getX() + 20
                                    && b[i].getLocation().getY() == b[j].getLocation().getY()
                                    || b[j].getLocation().getY() >= 480)
                                bb = false;
                        }
                    }
                    if (bb == true) {
                        b[nb1].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
                        b[nb2].setLocation((int) b[nb2].getLocation().getX() + 20, (int) b[nb2].getLocation().getY());
                        b[nb3].setLocation((int) b[nb3].getLocation().getX() + 20, (int) b[nb3].getLocation().getY());
                        b[nb4].setLocation((int) b[nb4].getLocation().getX() + 20, (int) b[nb4].getLocation().getY());
                    }
                }
                if (keyEvent.getKeyCode() == 40) {
                    boolean bb = true;
                    if (b[nb1].getLocation().getY() < 480 && b[nb2].getLocation().getY() < 480
                            && b[nb3].getLocation().getY() < 480 && b[nb4].getLocation().getY() < 480)
                        bb = true;
                    else
                        bb = false;
                    for (j = nb1; j <= nb4; j++) {
                        for (i = 0; i <= nb4 - 4; i++) {
                            if (b[i].getLocation().getX() == b[j].getLocation().getX()
                                    && b[i].getLocation().getY() == b[j].getLocation().getY() + 20
                                    || b[j].getLocation().getY() >= 480)
                                bb = false;
                        }
                    }
                    if (bb == true) {
                        b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                        b[nb2].setLocation((int) b[nb2].getLocation().getX(), (int) b[nb2].getLocation().getY() + 20);
                        b[nb3].setLocation((int) b[nb3].getLocation().getX(), (int) b[nb3].getLocation().getY() + 20);
                        b[nb4].setLocation((int) b[nb4].getLocation().getX(), (int) b[nb4].getLocation().getY() + 20);
                    }
                }

            }
        });
        pnl.requestFocusInWindow();
        pnl.setFocusable(true);
        b[nb1].setLocation(200, 0);
        b[nb2].setLocation(220, 0);
        b[nb3].setLocation(240, 0);
        b[nb4].setLocation(260, 0);
        tmr = new Timer(300, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (finish == true) {
                    tmr.stop();
                    setTitle("GAME OVER");
                }
                if (nb1 == 0) {
                    if (b[nb4].getLocation().getY() >= 480)
                        nextt();
                } else {
                    for (j = nb1; j <= nb4; j++) {
                        for (i = 0; i <= nb4 - 4; i++) {
                            if (b[i].getLocation().getX() == b[j].getLocation().getX()
                                    && b[i].getLocation().getY() - 20 == b[j].getLocation().getY()
                                    || b[j].getLocation().getY() >= 480) {
                                nextt();
                                j = nb4 + 4;
                                break;
                            }
                        }
                    }
                }
                if (flag == true && nb1 != 0) {
                    flag = false;
                    rotate();
                }
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb2].setLocation((int) b[nb2].getLocation().getX(), (int) b[nb2].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb3].getLocation().getX(), (int) b[nb3].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb4].getLocation().getX(), (int) b[nb4].getLocation().getY() + 20);
                for (i = 0; i < 25; i++) {
                    for (j = 0; j < 20; j++) {
                        arr[i][j] = -10;
                    }
                }
                for (i = 0; i < nb4; i++) {
                    if (b[i].getLocation().getX() != -100)
                        arr[(int) b[i].getLocation().getY() / 20][(int) b[i].getLocation().getX() / 20] = i;
                }
                int count = 0;
                for (i = 0; i < 25; i++) {
                    for (j = 0; j < 20; j++) {
                        if (arr[i][j] != -10)
                            count++;
                    }
                    if (count == 20) {
                        n++;
                        score.setText("Score = " + (n * 100));
                        flag = true;
                        for (m = 0; m < 20; m++) {
                            b[arr[i][m]].setLocation(-100, -20);
                        }
                        for (l = 0; l < i; l++) {
                            for (m = 0; m < 20; m++) {
                                if (arr[l][m] != -10)
                                    b[arr[l][m]].setLocation((int) b[arr[l][m]].getLocation().getX(),
                                            (int) b[arr[l][m]].getLocation().getY() + 20);
                            }
                        }
                    }
                    count = 0;
                }
            }
        });
        res.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tmr.stop();
                dispose();
                new tetris();
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent we) {
                tmr.start();
            }

            public void windowClosing(WindowEvent we) {
                tmr.stop();
                new home();
            }
        });
        setVisible(true);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 250;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 250;
        setBounds(xx, yy, 515, 540);
    }

    void nextt() {
        if (b[nb1].getLocation().getX() == 200 && b[nb1].getLocation().getY() == 20
                || b[nb1].getLocation().getX() == 220 && b[nb1].getLocation().getY() == 20
                || b[nb1].getLocation().getX() == 240 && b[nb1].getLocation().getY() == 20)
            finish = true;
        nb1 += 4;
        nb2 += 4;
        nb3 += 4;
        nb4 += 4;
        d = rr.nextInt(6);
        switch (d) {
        case 0:
            b[nb1].setLocation(200, 0);
            b[nb2].setLocation(220, 0);
            b[nb3].setLocation(240, 0);
            b[nb4].setLocation(260, 0);
            o = 1;
            break;
        case 1:
            b[nb1].setLocation(200, 0);
            b[nb2].setLocation(220, 0);
            b[nb3].setLocation(200, 20);
            b[nb4].setLocation(220, 20);
            o = 1;
            break;
        case 2:
            b[nb1].setLocation(200, 0);
            b[nb2].setLocation(220, 0);
            b[nb3].setLocation(240, 0);
            b[nb4].setLocation(240, 20);
            o = 1;
            break;
        case 3:
            b[nb1].setLocation(200, 0);
            b[nb2].setLocation(220, 0);
            b[nb3].setLocation(220, 20);
            b[nb4].setLocation(240, 20);
            o = 1;
            break;
        case 4:
            b[nb1].setLocation(200, 0);
            b[nb2].setLocation(220, 0);
            b[nb3].setLocation(240, 0);
            b[nb4].setLocation(200, 20);
            o = 1;
            break;
        case 5:
            b[nb1].setLocation(220, 0);
            b[nb2].setLocation(240, 0);
            b[nb3].setLocation(220, 20);
            b[nb4].setLocation(200, 20);
            o = 1;
            break;
        }
        int col = rr.nextInt(6);
        switch (col) {
        case 0:
            b[nb1].setBackground(Color.BLACK);
            b[nb2].setBackground(Color.BLACK);
            b[nb3].setBackground(Color.BLACK);
            b[nb4].setBackground(Color.BLACK);
            break;
        case 1:
            b[nb1].setBackground(Color.blue);
            b[nb2].setBackground(Color.blue);
            b[nb3].setBackground(Color.blue);
            b[nb4].setBackground(Color.blue);
            break;
        case 2:
            b[nb1].setBackground(Color.YELLOW);
            b[nb2].setBackground(Color.YELLOW);
            b[nb3].setBackground(Color.YELLOW);
            b[nb4].setBackground(Color.YELLOW);
            break;
        case 3:
            b[nb1].setBackground(Color.GREEN);
            b[nb2].setBackground(Color.GREEN);
            b[nb3].setBackground(Color.GREEN);
            b[nb4].setBackground(Color.GREEN);
            break;
        case 4:
            b[nb1].setBackground(Color.CYAN);
            b[nb2].setBackground(Color.CYAN);
            b[nb3].setBackground(Color.CYAN);
            b[nb4].setBackground(Color.CYAN);
            break;
        case 5:
            b[nb1].setBackground(Color.red);
            b[nb2].setBackground(Color.red);
            b[nb3].setBackground(Color.red);
            b[nb4].setBackground(Color.red);
            break;
        }
    }

    void rotate() {
        if (d == 0) {
            if (o == 1) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 40);
                b[nb4].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 60);
                o = 2;
            } else {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
                b[nb3].setLocation((int) b[nb1].getLocation().getX() + 40, (int) b[nb1].getLocation().getY());
                b[nb4].setLocation((int) b[nb1].getLocation().getX() + 60, (int) b[nb1].getLocation().getY());
                o = 1;
            }
        } else if (d == 1) {
            b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
            b[nb2].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
            b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
            b[nb4].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY() + 20);

        } else if (d == 2) {
            if (o == 1) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 40);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY() + 40);
                o = 2;
            } else if (o == 2) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() + 40, (int) b[nb1].getLocation().getY() + 20);
                o = 3;
            } else if (o == 3) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 40);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
                o = 4;
            } else {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY());
                b[nb3].setLocation((int) b[nb1].getLocation().getX() - 40, (int) b[nb1].getLocation().getY());
                b[nb4].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                o = 1;
            }
        } else if (d == 3) {
            if (o == 1) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY() + 40);
                o = 2;
            } else {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
                b[nb3].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() + 40, (int) b[nb1].getLocation().getY() + 20);
                o = 1;
            }
        } else if (d == 4) {
            if (o == 1) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 40);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY());
                o = 2;
            } else if (o == 2) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() - 40, (int) b[nb1].getLocation().getY() + 20);
                o = 3;
            } else if (o == 3) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 40);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY() + 40);
                o = 4;
            } else {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
                b[nb3].setLocation((int) b[nb1].getLocation().getX() + 40, (int) b[nb1].getLocation().getY());
                b[nb4].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                o = 1;
            }
        } else if (d == 5) {
            if (o == 1) {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb3].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY() + 40);
                o = 2;
            } else {
                b[nb1].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY());
                b[nb2].setLocation((int) b[nb1].getLocation().getX() + 20, (int) b[nb1].getLocation().getY());
                b[nb3].setLocation((int) b[nb1].getLocation().getX(), (int) b[nb1].getLocation().getY() + 20);
                b[nb4].setLocation((int) b[nb1].getLocation().getX() - 20, (int) b[nb1].getLocation().getY() + 20);
                o = 1;
            }
        }
        int min = 0, max = 380;
        for (int i = nb1; i <= nb4; i++) {
            if (b[i].getLocation().getX() < min) {
                min = (int) b[i].getLocation().getX();
            }
            if (b[i].getLocation().getX() > max) {
                max = (int) b[i].getLocation().getX();
            }
        }
        if (min != 0) {
            b[nb1].setLocation((int) b[nb1].getLocation().getX() - min, (int) b[nb1].getLocation().getY());
            b[nb2].setLocation((int) b[nb2].getLocation().getX() - min, (int) b[nb2].getLocation().getY());
            b[nb3].setLocation((int) b[nb3].getLocation().getX() - min, (int) b[nb3].getLocation().getY());
            b[nb4].setLocation((int) b[nb4].getLocation().getX() - min, (int) b[nb4].getLocation().getY());
        }
        if (max != 380) {
            b[nb1].setLocation((int) b[nb1].getLocation().getX() - (max - 380), (int) b[nb1].getLocation().getY());
            b[nb2].setLocation((int) b[nb2].getLocation().getX() - (max - 380), (int) b[nb2].getLocation().getY());
            b[nb3].setLocation((int) b[nb3].getLocation().getX() - (max - 380), (int) b[nb3].getLocation().getY());
            b[nb4].setLocation((int) b[nb4].getLocation().getX() - (max - 380), (int) b[nb4].getLocation().getY());
        }
    }
}