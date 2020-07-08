package com.bootstrapmahjong;

public class FourLesserBlessingsCombo extends MultiplierDecorator {

    public FourLesserBlessingsCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Four Lesser Blessings Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 2;
    }

}
