package UI;

import DataReading.DataReader;
import Market.Market;
import Tradable.RealEstate;
import Tradable.Tradable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class JFrameMarket extends JFrame {


    Timer timer;
    int second;
    public JPanel EstatePanel = new JPanel();
    public JPanel CryptoPanel = new JPanel();
    public JPanel CurrencyPanel = new JPanel();
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
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        jTextField1.setToolTipText("Input");


        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);


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

        jTabbedPane1.addTab("Stock", innerStockPanel);
        jTabbedPane1.addTab("Crypto", innerCryptoPanel);
        jTabbedPane1.addTab("Good", innerGoodPanel);
        jTabbedPane1.addTab("Real Estate", innerEstatePanel);
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

        jLabel1.setText("Balance");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 14));


        jLabel2.setText("23");
        jLabel1.setFont(new Font("Arial", Font.BOLD, 14));


        searchButton.setText("profilePic" +
                "");
        searchButton.setPreferredSize(new java.awt.Dimension(75, 24));
        searchButton.addActionListener(this::searchButtonActionPerformed);

        second = 0;
        timeFlow();
        timer.start();
//        BoxLayout newsLayout = new BoxLayout(newsTextPanel,BoxLayout.Y_AXIS);
//
//        newsScroll.setViewportView(newsTextPanel);
//        newsTextPanel.setLayout(newsLayout);
//        newsScroll.setBackground(backgroundColor);


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

//        jLabel3.setFont(new Font("Arial", Font.BOLD,20));
//
//        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel2);
//        jPanel2.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
//                                .addContainerGap())
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addGroup(jPanel1Layout.createSequentialGroup()
//                                        .addContainerGap()
//                                        .addComponent(newsScroll)
//                                        .addContainerGap()))
//        );
//        jPanel1Layout.setVerticalGroup(
//                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(jPanel1Layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap(417, Short.MAX_VALUE))
//                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
//                                        .addContainerGap(78, Short.MAX_VALUE)
//                                        .addComponent(newsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
//                                        .addContainerGap(20, Short.MAX_VALUE)))
//        );
//
//        javax.swing.GroupLayout layout2 = new javax.swing.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout2);
//        layout2.setHorizontalGroup(
//                layout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 281, Short.MAX_VALUE)
//                        .addGroup(layout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addGroup(layout2.createSequentialGroup()
//                                        .addContainerGap()
//                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                        .addContainerGap()))
//        );
//        layout2.setVerticalGroup(
//                layout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGap(0, 495, Short.MAX_VALUE)
//                        .addGroup(layout2.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                                .addGroup(layout2.createSequentialGroup()
//                                        .addContainerGap()
//                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                        .addContainerGap()))
//        );


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
//        boolean alreadyExecuted=false;
//        if(!alreadyExecuted)
//        {
//            try{
//                constructTradeableObject();
//                alreadyExecuted=true;
//            }catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//
//            pack();



        //}
    }


    private void searchButtonActionPerformed(ActionEvent evt) {
        //search functionality

    }

    private void profilePicActionPerformed(ActionEvent evt) {
        dispose();
        new JFrameProfile();
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
                            addTradeableObject(StockPanel, t.getType());
                            break;
                        case "Crypto":
                            addTradeableObject(CryptoPanel, t.getType());
                            break;
                        case "Good":
                            addTradeableObject(GoodPanel, t.getType());
                            break;
                        case "RealEstate":
                            addTradeableObject(EstatePanel, t.getType());
                            break;
                    }
                }
            }
        });


    }



   public JLabel tradableInfo = new JLabel("");

    public void constructTradeableObject(){
        ArrayList<Tradable> tradables = market.getAssets();
        for (int i = 0; i < tradables.size(); i++) {
            tradableInfo.setText(tradables.get(i).getType());
            switch (tradables.get(i).getType()) {
                case "Stock":
                    addTradeableObject(StockPanel,tradableInfo.getText());
                    break;

                case "Crypto":

                    addTradeableObject(CryptoPanel,tradableInfo.getText());
                    break;

                case "Good":
                    addTradeableObject(GoodPanel,tradableInfo.getText());
                    break;

                case "RealEstate":
                    addTradeableObject(EstatePanel,tradableInfo.getText());
                    break;
            }

        }
    }



    public void addTradeableObject(JPanel panel, String text) {
        JPanel tradeablePanel = new JPanel();


         JButton buyBut = new JButton("Buy");
        tradableInfo.setText("Suren");
        buyBut.setForeground(itemColor);
        tradeablePanel.setBackground(backgroundColor);
        tradableInfo.setForeground(itemColor);
        tradableInfo.setBorder(BorderFactory.createLineBorder(itemColor, 1));

        tradeablePanel.setLayout(new BorderLayout());
        tradeablePanel.add(tradableInfo,BorderLayout.NORTH);

        panel.add(tradeablePanel);


        tradableInfo.setText("");
//        GroupLayout tablePanel = new GroupLayout(tradeablePanel);
//        tradeablePanel.setLayout(tablePanel);
//        tablePanel.setHorizontalGroup(
//                tablePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(tablePanel.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(tradableInfo, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
//                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(buyBut, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
//                                .addContainerGap())
//        );
//        tablePanel.setVerticalGroup(
//                tablePanel.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(tablePanel.createSequentialGroup()
//                                .addContainerGap()
//                                .addGroup(tablePanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                        .addComponent(tradableInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                                        .addComponent(buyBut))
//                                .addContainerGap())
//        );


    }


}

