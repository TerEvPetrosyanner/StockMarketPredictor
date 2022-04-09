package Owner;
import Tradable.Tradable;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

public class Owner{
    private String name;
    private Tradable[] assets;
    private Double netWorth;
    private UUID uuid;
    private SecureRandom secureRandom = new SecureRandom();


    public Owner(){
        this("John Doe", null, 0.0);
    }
    public Owner(String name, Tradable[] assets, Double netWorth){
        this.name = name;
        if(assets != null) {
            this.assets = Arrays.copyOf(assets, assets.length);
        }
        this.netWorth = netWorth;
        this.uuid = UUID.randomUUID();
    }

    public UUID getId() {
        return uuid;
    }
}
