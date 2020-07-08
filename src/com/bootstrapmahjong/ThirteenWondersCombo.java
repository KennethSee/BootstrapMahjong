package com.bootstrapmahjong;

public class ThirteenWondersCombo extends MultiplierDecorator {

    public ThirteenWondersCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Thirteen Wonders Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 5;
    }

}
