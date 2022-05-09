package UI;

import javax.swing.*;
import java.awt.*;

public class JFrameProfile {
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Color backgroundColor = new Color(13, 19, 23);
    private Color itemColor= new Color(255, 178, 15);
    GridBagConstraints gbc = new GridBagConstraints();
    JFrame frame = new JFrame("Bazzar");


    public JFrameProfile() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
    }
}
