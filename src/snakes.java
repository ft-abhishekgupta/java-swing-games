import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.event.*;

class snakes extends JFrame {
    int j, x, y, tx, ty, f = 1, ttx, tty, cx, cy, n = 5, wx, wy, max = 1000, hh = 0;
    JLabel score, high;
    JPanel pnl;
    Timer tmr;
    JButton[] b = new JButton[max];
    JButton res = new JButton("RESET");
    boolean r = true;
    java.util.Random rr = new java.util.Random();
    snakes() {
        Container container = this.getContentPane();
        container.setLayout(null);
        pnl = new JPanel();
        pnl.setSize(500, 500);
        pnl.setLayout(null);
        container.add(pnl);
        pnl.setBackground(Color.GRAY);

        score = new JLabel("Score = " + (n - 5));
        score.setForeground(Color.WHITE);
        score.setBounds(380, 10, 100, 50);
        pnl.add(score);
        high = new JLabel("HighScore = 0");
        high.setForeground(Color.WHITE);
        high.setBounds(380, 25, 100, 50);
        pnl.add(high);

        try {
            FileReader fr = new FileReader("highscore_snakes.txt");
            BufferedReader br = new BufferedReader(fr);
            hh = Integer.parseInt(br.readLine());
            high.setText("HighScore = " + hh);
            br.close();
            fr.close();
        } 
        catch (Exception e) {
        }

        res.setForeground(Color.WHITE);
        res.setBounds(380, 0, 100, 30);
        res.setOpaque(false);
        res.setContentAreaFilled(false);
        pnl.add(res);
        for (j = 0; j < max; j++) {
            b[j] = new JButton("");
            b[j].setSize(20, 20);
            pnl.add(b[j]);
            b[j].setLocation(-20, -20);
            if (j % 2 == 0) {
                b[j].setBackground(Color.BLACK);
            } else {
                b[j].setBackground(Color.BLACK);
            }
        }

        for (j = 0; j < n; j++) {
            b[j].setLocation(200 - 20 * j, 20);
        }
        b[0].setBackground(Color.RED);
        b[0].setText(".");
        x = 200;
        y = 20;
        wx = rr.nextInt(25) * 20;
        wy = rr.nextInt(25) * 20;
        b[n].setLocation(wx, wy);

        tmr = new Timer(150, new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                pnl.addKeyListener(new KeyListener() {
                    public void keyTyped(KeyEvent keyEvent) {}
                    public void keyReleased(KeyEvent keyEvent) {
                        if (keyEvent.getKeyCode() == 37 && f != 1) {
                            f = 3;
                        }
                        if (keyEvent.getKeyCode() == 38 && f != 2) {
                            f = 4;
                        }
                        if (keyEvent.getKeyCode() == 39 && f != 3) {
                            f = 1;
                        }
                        if (keyEvent.getKeyCode() == 40 && f != 4) {
                            f = 2;
                        }
                    }
                    public void keyPressed(KeyEvent keyEvent) {}
                });
                pnl.requestFocusInWindow();
                pnl.setFocusable(true);

                x = (int) b[0].getLocation().getX();
                y = (int) b[0].getLocation().getY();
                tx = x;
                ty = y;
                if (f == 1) {
                    x += 20;
                } else if (f == 2) {
                    y += 20;
                } else if (f == 3) {
                    x -= 20;
                } else if (f == 4) {
                    y -= 20;
                }
                if (x > 480) {
                    x -= 500;
                }
                if (y > 440) {
                    y -= 500;
                }
                if (x < 0) {
                    x += 500;
                }
                if (y < 0) {
                    y += 500;
                }
                for (j = 1; j < n; j++) {
                    cx = (int) b[j].getLocation().getX();
                    cy = (int) b[j].getLocation().getY();
                    if (cx == x && cy == y) {
                        tmr.stop();
                    }
                }
                if (x == wx && y == wy && n < max) {
                    ++n;
                    score.setText("Score = " + (n - 5) * 20);
                    if (hh < (n - 5) * 20) {
                        hh = (n - 5) * 20;
                        high.setText("HighScore = " + hh);
                        try {
                            FileWriter fw = new FileWriter("highscore_snakes.txt");
                            fw.write(hh + "");
                            fw.close();
                        } catch (Exception ex) {
                        }
                    }
                    wx = rr.nextInt(25) * 20;
                    wy = rr.nextInt(25) * 20;
                    for (j = 1; j < n; j++) {
                        cx = (int) b[j].getLocation().getX();
                        cy = (int) b[j].getLocation().getY();
                        if (wx == cx && wy == cy) {
                            wx = rr.nextInt(25) * 20;
                            wy = rr.nextInt(25) * 20;
                            j = 0;
                        }
                    }
                    b[n].setLocation(wx, wy);
                }
                b[0].setLocation(x, y);
                for (j = 1; j < n; j++) {
                    ttx = (int) b[j].getLocation().getX();
                    tty = (int) b[j].getLocation().getY();
                    b[j].setLocation(tx, ty);
                    tx = ttx;
                    ty = tty;
                }
            }
        });

        res.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                tmr.stop();
                r = false;
                dispose();
                new snakes();
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent we) {
                tmr.start();
            }
            public void windowClosing(WindowEvent we) {
                tmr.stop();
            }
        });

        setVisible(true);
        setTitle("SNAKES - Use arrow keys to turn");
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 250;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 250;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(xx, yy, 515, 540);
        addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent we) {
                if (r == true)
                    new home();
            }
        });
    }
}