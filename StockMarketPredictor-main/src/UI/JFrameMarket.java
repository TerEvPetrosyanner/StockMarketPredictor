package UI;

import Market.Market;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class JFrameMarket extends JFrame implements WindowListener{
    Timer timer;
    int second;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Color backgroundColor = new Color(13, 19, 23);
    private Color itemColor= new Color(255, 178, 15);
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel toolBar = new JPanel();
    JPanel jPanel2 = new JPanel();
    JLabel jLabel3 = new JLabel("BREAKING NEWS", jPanel2.getHeight()/2);
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField  jTextField1 = new JTextField();

    JTabbedPane  jTabbedPane1 = new JTabbedPane();

    JScrollPane jScrollPane1 = new JScrollPane();

    Icon iconProfile;
    Icon iconSearch;

    JPanel StockPanel = new JPanel();


    public String newsLine;

    JTextArea newsArea=new JTextArea();


    public JFrameMarket() {
        setTitle("Bazzar");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(screenSize);
        setLayout(new BorderLayout());
        jTextField1.setToolTipText("Input");



        Container mainContainer = this.getContentPane();
        mainContainer.setBackground(backgroundColor);
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, backgroundColor));
        setLayout(new GridBagLayout());


        newsArea.setBackground(backgroundColor);
        newsArea.setForeground(itemColor);
        newsArea.setLineWrap(true);
        newsArea.setWrapStyleWord(true);



        iconProfile = new ImageIcon(new ImageIcon("./icons/profileIcon.png").getImage().getScaledInstance(18,18, Image.SCALE_AREA_AVERAGING));
         JButton profilePic = new JButton(iconProfile);
         profilePic.setOpaque(false);
         profilePic.setBorderPainted(false);
        profilePic.setContentAreaFilled(false);
        profilePic.setMargin(new Insets(0,16,0,0));


        iconSearch = new ImageIcon(new ImageIcon("./icons/search.png").getImage().getScaledInstance(18,18, Image.SCALE_AREA_AVERAGING));
        JButton searchButton = new JButton(iconSearch);
        searchButton.setOpaque(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setMargin(new Insets(0,16,0,0));


     jLabel1.setForeground(itemColor);
     jLabel2.setForeground(itemColor);
     jLabel3.setForeground(itemColor);



     jTabbedPane1.addTab("Stock",StockPanel);
        jTabbedPane1.addTab("Crypto", new JLabel("Crypto"));
        jTabbedPane1.addTab("Currency", new JLabel("Currency"));
        jTabbedPane1.addTab("Good", new JLabel("Good"));
        jTabbedPane1.addTab("Real Estate",new JLabel("Estate"));
        jTabbedPane1.addTab("Transactions", new JLabel("Transactions"));
        jTabbedPane1.setBackground(backgroundColor);
        jTabbedPane1.setForeground(itemColor);


        toolBar.setBackground(backgroundColor);
        jPanel2.setBackground(backgroundColor);

        toolBar.setBorder(new LineBorder(Color.white));
        jPanel2.setBorder(new LineBorder(Color.white));



        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1600, 820));

        profilePic
                .setText("profilePic" +
                        "");
        profilePic
                .setPreferredSize(new java.awt.Dimension(75, 24));
        profilePic
                .addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profilePicActionPerformed(evt);
            }
        });

        jLabel1.setText("Balance");

        jLabel2.setText("balanceData");


        searchButton.setText("profilePic" +
                "");
        searchButton.setPreferredSize(new java.awt.Dimension(75, 24));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        second =0;
        timeFlow();
        timer.start();



        GroupLayout toolBarLayout = new GroupLayout(toolBar);
        toolBar.setLayout(toolBarLayout);

        toolBarLayout.setHorizontalGroup(
                toolBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, toolBarLayout.createSequentialGroup()

                                .addContainerGap(100, Short.MAX_VALUE)
                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                .addGap(300, 300, 300)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(profilePic
                                        , GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        toolBarLayout.setVerticalGroup(
                toolBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolBarLayout.createSequentialGroup()
                                .addContainerGap(12, Short.MAX_VALUE)
                                .addGroup(toolBarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, toolBarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(profilePic
                                                        , GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel2))
                                        .addGroup(GroupLayout.Alignment.TRAILING, toolBarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
        );

        jLabel3.setFont(new Font("Arial", Font.BOLD,20));



        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(50,Short.MAX_VALUE)
                                .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addContainerGap(50,Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(newsArea, javax.swing.GroupLayout.PREFERRED_SIZE, 210,GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addContainerGap(0, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap(0, Short.MAX_VALUE)
                                        .addComponent(newsArea, javax.swing.GroupLayout.PREFERRED_SIZE, 600,GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(36, Short.MAX_VALUE)))
        );

        jTabbedPane1.setToolTipText("");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(toolBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTabbedPane1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jTabbedPane1)))
                                .addContainerGap())
        );
        pack();


    }


    private void searchButtonActionPerformed(ActionEvent evt) {
        //search functionality

    }

    private void profilePicActionPerformed(ActionEvent evt) {
        dispose();
        new JFrameProfile();
    }



    public void timeFlow()
    {
        timer = new Timer(1000, new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                Market.Event event = new Market.Event();
                newsLine = event.getDescription();
                JLabel temp= new JLabel(newsLine);
                newsArea.append(temp.getText() + " AFFECTS" + "\n");

           }
       });

    }

    public JTextArea createTradableObject()
    {
        JTextArea object = new JTextArea();

        return object;
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}

