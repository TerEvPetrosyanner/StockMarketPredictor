package Owner;
import Market.Market;
import Tradable.CustomSlowMap;
import Tradable.Tradable;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;


public class Owner{
    private String name;
    private Tradable[] assets;
    private BigDecimal netWorth;
    private UUID uuid;
    private CustomSlowMap<String, BigDecimal> wallet;


    public Owner(){
        this("John Doe", null, new BigDecimal(10000));
    }
    public Owner(String name, Tradable[] assets, BigDecimal netWorth){
        this.name = name;
        if(assets != null) {
            this.assets = Arrays.copyOf(assets, assets.length);
        }
        this.netWorth = netWorth;
        this.uuid = UUID.randomUUID();
        this.wallet = new CustomSlowMap<String, BigDecimal>(new String[]{"USD", "EUR", "CHF", "JPY", "GBP"},
                new BigDecimal[]{BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO});
    }

    public UUID getId() {
        return uuid;
    }

    public BigDecimal predict(int year){
        BigDecimal res = netWorth;
        //Make event a separate class? Or how to make static
        for(int i = 0; i<year; i++){
            if(i%3 == 0) {
                Market.Event event = new Market.Event();
                event.affect();
            }
            res = res.add(res.multiply(BigDecimal.valueOf(5.0 / 100)));
        }
        return res;
    }

    public String getName(){
        return this.name;
    }

    public CustomSlowMap<String, BigDecimal> getWallet(){
        //Privacy leak
        return wallet;
    }
}
