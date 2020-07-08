package com.bootstrapmahjong;

public class AllHonourCombo extends MultiplierDecorator {

    public AllHonourCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", All Honour Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 5;
    }

}
