package com.bootstrapmahjong;

public class NineGatesCombo extends MultiplierDecorator {

    public NineGatesCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Nine Gates Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 5;
    }

}
