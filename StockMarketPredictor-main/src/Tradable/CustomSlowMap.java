package Tradable;

import java.math.BigDecimal;

public class CustomSlowMap {
    private final String[] keys;
    private final BigDecimal[] values;

    public CustomSlowMap(String[] keys, BigDecimal[] values) {
        this.keys = keys;
        this.values = values;
    }

    public BigDecimal getValue(String key) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void updateValue(String key, BigDecimal newValue) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals(key)) {
               values[i] = newValue;
            }
        }
    }
}
