package com.bootstrapmahjong;

public class PingHuCombo extends MultiplierDecorator {

    public PingHuCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Ping Hu Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 4;
    }

}
