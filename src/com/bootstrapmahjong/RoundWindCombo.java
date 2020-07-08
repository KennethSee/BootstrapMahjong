package com.bootstrapmahjong;

public class RoundWindCombo extends MultiplierDecorator {

    public RoundWindCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Round Wind Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
