package UI;

import DataReading.DataReader;
import Market.Market;
import Owner.Owner;
import Tradable.Tradable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



public class JFrameMarket extends JFrame {

    Timer timer;
    int second;

    public JPanel GlobalPanel = new JPanel();
    public JPanel EstatePanel = new JPanel();
    public JPanel CryptoPanel = new JPanel();
    public JPanel GoodPanel = new JPanel();
    public Color backgroundColor = new Color(13, 19, 23);
    public Color itemColor = new Color(255, 178, 15);


    JPanel toolBar = new JPanel();
    JPanel jPanel2 = new JPanel();
    JLabel jLabel3 = new JLabel("BREAKING NEWS", jPanel2.getHeight() / 2);
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JTextField jTextField1 = new JTextField();

    JTabbedPane jTabbedPane1 = new JTabbedPane();
    JScrollPane innerStockPanel = new JScrollPane();
    JPanel StockPanel = new JPanel();


    Icon iconProfile;
    Icon iconSearch;


    public String newsLine;

    JTextArea newsArea = new JTextArea();

    private Market market;



    public JFrameMarket() {
        market = new Market();

        constructTradeableObject();
        setTitle("Bazzar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(1600,820);



        Container mainContainer = this.getContentPane();
        mainContainer.setBackground(backgroundColor);



        newsArea.setBackground(backgroundColor);
        newsArea.setForeground(itemColor);
        newsArea.setLineWrap(true);
        newsArea.setWrapStyleWord(true);


        iconProfile = new ImageIcon(new ImageIcon("./icons/profileIcon.png").getImage().getScaledInstance(18, 18, Image.SCALE_AREA_AVERAGING));
        JButton profilePic = new JButton(iconProfile);
        profilePic.setOpaque(false);
        profilePic.setBorderPainted(false);
        profilePic.setContentAreaFilled(false);
        profilePic.setMargin(new Insets(0, 16, 0, 0));


        iconSearch = new ImageIcon(new ImageIcon("./icons/search.png").getImage().getScaledInstance(18, 18, Image.SCALE_AREA_AVERAGING));
        JButton searchButton = new JButton(iconSearch);
        searchButton.setOpaque(false);
        searchButton.setBorderPainted(false);
        searchButton.setContentAreaFilled(false);
        searchButton.setMargin(new Insets(0, 16, 0, 0));


        jLabel1.setForeground(itemColor);
        jLabel2.setForeground(itemColor);
        jLabel3.setForeground(itemColor);


        BoxLayout tradableLayout = new BoxLayout(StockPanel, BoxLayout.Y_AXIS);
//stock
        jTabbedPane1.add(innerStockPanel);

        innerStockPanel.setViewportView(StockPanel);
        StockPanel.setBackground(backgroundColor);
        StockPanel.setLayout(tradableLayout);


        //crypto
        BoxLayout ctradableLayout = new BoxLayout(CryptoPanel, BoxLayout.Y_AXIS);

        JScrollPane innerCryptoPanel = new JScrollPane();
        jTabbedPane1.add(innerCryptoPanel);

        innerCryptoPanel.setViewportView(CryptoPanel);
        CryptoPanel.setBackground(backgroundColor);
        CryptoPanel.setLayout(ctradableLayout);


//Good
        BoxLayout gtradableLayout = new BoxLayout(GoodPanel, BoxLayout.Y_AXIS);

        JScrollPane innerGoodPanel = new JScrollPane();
        jTabbedPane1.add(innerGoodPanel);
        innerGoodPanel.setViewportView(GoodPanel);


        GoodPanel.setBackground(backgroundColor);
        GoodPanel.setLayout(gtradableLayout);
//Real Estate
        BoxLayout rtradableLayout = new BoxLayout(EstatePanel, BoxLayout.Y_AXIS);

        JScrollPane innerEstatePanel = new JScrollPane();
        jTabbedPane1.add(innerEstatePanel);
        innerEstatePanel.setViewportView(EstatePanel);
        EstatePanel.setBackground(backgroundColor);
        EstatePanel.setLayout(rtradableLayout);
        //Global Search

        BoxLayout globalLayout = new BoxLayout(GlobalPanel, BoxLayout.Y_AXIS);
        JScrollPane innerGlobalPanel = new JScrollPane();
        jTabbedPane1.add(innerGlobalPanel);

        innerGlobalPanel.setViewportView(GlobalPanel);
        GlobalPanel.setBackground(backgroundColor);
        GlobalPanel.setLayout(globalLayout);

        //Transactions
//        BoxLayout gtradableLayout = new BoxLayout(GoodPanel, BoxLayout.Y_AXIS);
//
//        JScrollPane innerGoodPanel = new JScrollPane();
//        jTabbedPane1.add(innerGoodPanel);
//
//        innerGoodPanel.setViewportView(GoodPanel);
//        TransactionPanel.setBackground(backgroundColor);
//        GoodPanel.setLayout(gtradableLayout);

        jTabbedPane1.addTab("Stock", innerStockPanel);
        jTabbedPane1.addTab("Crypto", innerCryptoPanel);
        jTabbedPane1.addTab("Good", innerGoodPanel);
        jTabbedPane1.addTab("Real Estate", innerEstatePanel);
        jTabbedPane1.addTab("Search", innerGlobalPanel);

        jTabbedPane1.addTab("Transactions", new JLabel("Transactions"));
        jTabbedPane1.setBackground(backgroundColor);
        jTabbedPane1.setForeground(itemColor);


        toolBar.setBackground(backgroundColor);
        jPanel2.setBackground(backgroundColor);

        toolBar.setBorder(new LineBorder(Color.white));
        jPanel2.setBorder(new LineBorder(Color.white));


        profilePic
                .setText("profilePic" +
                        "");
        profilePic
                .setPreferredSize(new java.awt.Dimension(75, 24));
        profilePic
                .addActionListener(this::profilePicActionPerformed);

        jLabel1.setText("Balance: " + Market.getOwnerProfile().getNetWorth());
        jLabel1.setFont(new Font("Arial", Font.BOLD, 14));

        jLabel1.setFont(new Font("Arial", Font.BOLD, 14));


        searchButton.setText("profilePic" +
                "");


        searchButton.setPreferredSize(new java.awt.Dimension(75, 24));
        searchButton.addActionListener(this::searchButtonActionPerformed);



        second = 0;
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
        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(50, Short.MAX_VALUE)
                                .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                .addContainerGap(50, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(newsArea, javax.swing.GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(newsArea, javax.swing.GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
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

    }


    private void searchButtonActionPerformed(ActionEvent evt) {
        delete(GlobalPanel);
        searchTradeableObject(GlobalPanel);
    }

    private JFrameProfile profile;

    private void profilePicActionPerformed(ActionEvent evt) {
        profile = new JFrameProfile(Market.getOwnerProfile());
    }

    private int day = 1;

    public void timeFlow() {
        timer = new Timer(2000, e -> {
            day++;
            if(day%2 == 0) {
                Market.Event event = new Market.Event();
                //event.affect();
                newsLine = event.getDescription();
                JLabel temp = new JLabel(newsLine);
                newsArea.setEditable(false);
                newsArea.setFont(new Font("Arial", Font.BOLD, 17));
                newsArea.append("\n" + temp.getText() + "\n");
            }
            if(day%10 == 0) {
                System.out.println("New");
                Tradable t = DataReader.getTradable();
                if (t != null) {
                    market.addAsset(t);
                    tradableInfo.setText(t.getType());
                    switch (t.getType()) {
                        case "Stock":
                            addTradeableObject(StockPanel, t.toString(),t.getMyID());
                            break;
                        case "Crypto":
                            addTradeableObject(CryptoPanel, t.toString(),t.getMyID());
                            break;
                        case "Good":
                            addTradeableObject(GoodPanel, t.toString(),t.getMyID());
                            break;
                        case "RealEstate":
                            addTradeableObject(EstatePanel, t.toString(),t.getMyID());
                            break;
                    }
                }
            }
        });


    }



    public JLabel tradableInfo = new JLabel("");
    public void constructNews(String text)
    {

    }


    public void constructTradeableObject(){
        ArrayList<Tradable> tradables = market.getAssets();
        for (int i = 0; i < tradables.size(); i++) {
            int id = tradables.get(i).getMyID();

            String labelText=tradables.get(i).toString();
            tradableInfo.setText(labelText);
            switch (tradables.get(i).getType()) {
                case "Stock":
                    addTradeableObject(StockPanel,tradableInfo.getText(),id);
                    break;

                case "Crypto":

                    addTradeableObject(CryptoPanel,tradableInfo.getText(),id);
                    break;

                case "Good":
                    addTradeableObject(GoodPanel,tradableInfo.getText(),id);
                    break;

                case "RealEstate":
                    addTradeableObject(EstatePanel,tradableInfo.getText(),id);
                    break;
            }

        }
    }
    public  void searchTradeableObject(JPanel panel)
    {

        JPanel otherPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        String text= jTextField1.getText();
        ArrayList<Tradable> a = market.search(text);


        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;

        otherPanel.setBorder(BorderFactory.createLineBorder(backgroundColor,1));
        otherPanel.setBackground(backgroundColor);

        for(int i = 0; i< a.size();i++)
        {
            int id = a.get(i).getMyID();

            JPanel tempPanel = new JPanel(){
                @Override
                public Dimension getPreferredSize()
                {
                    return new Dimension(1350,45);
                }
            };
            tempPanel.setLayout(new FlowLayout());
            JLabel text1 = new JLabel(a.get(i).toString());
            text1.setForeground(itemColor);
            tempPanel.add(text1);
            JButton buyBut = new JButton("BUY");
            buyBut.addActionListener(e -> buyButtonActionPerformed(panel,id));
            tempPanel.add(buyBut);
            tempPanel.setBorder(BorderFactory.createLineBorder(itemColor,2));
            tempPanel.setBackground(backgroundColor);

            otherPanel.add(tempPanel, gbc);
        }
        panel.add(otherPanel);

    }


    public void addTradeableObject(JPanel panel, String text, int id) {
        ArrayList<Tradable> tradables = market.getAssets();
        JPanel otherPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weighty = 1.0;
        otherPanel.setBorder(BorderFactory.createLineBorder(backgroundColor, 1));
        otherPanel.setBackground(backgroundColor);

        JPanel tempPanel = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(1200, 45);
            }
        };

        tempPanel.setLayout(new FlowLayout());
        JLabel text1 = new JLabel(text);
        text1.setForeground(itemColor);

        tempPanel.add(text1);
        JButton buyBut = new JButton("BUY");
        buyBut.addActionListener(e -> buyButtonActionPerformed(panel,id));
        tempPanel.add(buyBut);
        tempPanel.setBorder(BorderFactory.createLineBorder(itemColor,2));
        tempPanel.setBackground(backgroundColor);

        otherPanel.add(tempPanel, gbc);

        panel.add(otherPanel);



    }
    private void update(JPanel panel)
    {
        delete(panel);
        constructTradeableObject();
    }
    private void delete(JPanel panel)
    {
        panel.removeAll();
    }


    private void buyButtonActionPerformed(JPanel panel,int ID)
    {
        Tradable a = Market.findTradableByID(ID);
        try {
            System.out.println(Market.findTradableByID(ID));
            market.buy(ID,"DAY " + day);
            System.out.println("bought");
            update(panel);
            jLabel1.setText("Balance: " + Market.getOwnerProfile().getNetWorth());
             }
        catch(Exception t){}
    }

}