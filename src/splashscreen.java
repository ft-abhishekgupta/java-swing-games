import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class splashscreen extends JFrame {
    JLabel label, picture;
    Timer timer;
    JWindow window;
    JPanel panel;
    int c = 0;

    public splashscreen() {
        window = new JWindow(this);
        picture = new JLabel(new ImageIcon(getClass().getResource("images/Loading.gif")));
        window.add(picture);
        window.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent we) {
                timer.start();
            }
        });
        timer = new Timer(50, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c++;
                if (c == 50) {
                    dispose();
                    new home();
                }
            }
        });
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xx = (int) tk.getScreenSize().getWidth() / 2 - 150;
        int yy = (int) tk.getScreenSize().getHeight() / 2 - 150;
        window.setBounds(xx, yy, 260, 260);
        picture.setBackground(new Color(0, 0, 0, 0));
        window.setBackground(new Color(0, 0, 0, 0));
        window.setForeground(new Color(0, 0, 0, 0));
        picture.setForeground(new Color(0, 0, 0, 0));
        window.setVisible(true);
    }
}