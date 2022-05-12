package UI;

import Market.Market;
import Owner.Owner;
import Tradable.Tradable;
import com.sun.nio.sctp.NotificationHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class JFrameProfile extends JFrame {
    private JPanel panel1 = new JPanel();
    private Color backgroundColor = new Color(13, 19, 23);
    private Color itemColor = new Color(255, 178, 15);

    public JFrameProfile(Owner user) {
        Container mainCont = this.getContentPane();
        mainCont.setBackground(backgroundColor);
        setTitle("Bazzar");
        setSize(1580,820);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setVisible(true);


        mainCont.setLayout(new BorderLayout());
        JPanel assetPanel = new JPanel();
        assetPanel.setBorder(BorderFactory.createLineBorder(itemColor,1));
        assetPanel.setBackground(backgroundColor);
        assetPanel.setPreferredSize(new Dimension(1200,400));

        JPanel walletPanel = new JPanel();
        walletPanel.setBorder(BorderFactory.createLineBorder(itemColor,1));
        walletPanel.setBackground(backgroundColor);

        mainCont.add(assetPanel,BorderLayout.WEST);
        mainCont.add(walletPanel,BorderLayout.CENTER);

        walletPanel.setLayout(new BorderLayout());
        JLabel walletN = new JLabel("Your wallet state:");
        walletN.setForeground(itemColor);

        walletN.setFont(new Font("Arial", Font.BOLD,16));
        JPanel walletPan = new JPanel();
        walletPan.add(walletN);


        walletPanel.add(walletPan,BorderLayout.NORTH);
        walletPan.setBackground(backgroundColor);
        walletPan.setBorder(BorderFactory.createLineBorder(itemColor,1));

        JPanel predictPan = new JPanel(new FlowLayout());
        predictPan.setBackground(backgroundColor);
        predictPan.setBorder(BorderFactory.createLineBorder(itemColor,1));
        JButton lab = new JButton("Predict");
        JTextField textfield = new JTextField("This is text field");
        predictPan.add(lab);
        predictPan.add(textfield);
        walletPanel.add(predictPan,BorderLayout.CENTER);

        JPanel namePanel = new JPanel();
        namePanel.setBackground(backgroundColor);
        namePanel.setBorder(BorderFactory.createLineBorder(itemColor,1));
        assetPanel.setLayout(new BorderLayout());
        JLabel a = new JLabel("Hello, John Doe");
        a.setFont(new Font("Arial", Font.BOLD,16));
        a.setForeground(itemColor);
        namePanel.add(a);
        assetPanel.add(namePanel,BorderLayout.NORTH);

        JPanel assetMainPanel = new JPanel();
        BoxLayout boxLay = new BoxLayout(assetMainPanel,BoxLayout.Y_AXIS);
        assetMainPanel.setLayout(boxLay);
        assetMainPanel.setBackground(backgroundColor);
        assetMainPanel.setBorder(BorderFactory.createLineBorder(itemColor,1));

        assetPanel.add(assetMainPanel,BorderLayout.CENTER);

            JPanel otherPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();



            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weighty = 1.0;

            otherPanel.setBorder(BorderFactory.createLineBorder(backgroundColor,1));
            otherPanel.setBackground(backgroundColor);

            for(int i = 0 ;i< user.getAssets().size();i++){

                String text= user.getAssets().get(i).toString();
                int id = user.getAssets().get(i).getMyID();

                JPanel tempPanel = new JPanel(){
                    @Override
                    public Dimension getPreferredSize()
                    {
                        return new Dimension(1350,45);
                    }
                };
                tempPanel.setLayout(new FlowLayout());
                JLabel text1 = new JLabel(text);
                text1.setForeground(itemColor);
                tempPanel.add(text1);
                JButton sellBut = new JButton("SELL");
                sellBut.addActionListener(e->destructButton(user,id));

                tempPanel.add(sellBut);
                tempPanel.setBorder(BorderFactory.createLineBorder(itemColor,2));
                tempPanel.setBackground(backgroundColor);

                otherPanel.add(tempPanel, gbc);


             }
            assetMainPanel.add(otherPanel);


    }
    public  void destructButton(Owner user,int id)
    {
        Tradable a = Market.findTradableByID(id);

        try {

            user.sell(id, "DAY");
            System.out.println("sold");

        }
        catch (Exception t){}
    }





}

