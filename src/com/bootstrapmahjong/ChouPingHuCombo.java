package com.bootstrapmahjong;

public class ChouPingHuCombo extends MultiplierDecorator {

    public ChouPingHuCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Chou Ping Hu Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
