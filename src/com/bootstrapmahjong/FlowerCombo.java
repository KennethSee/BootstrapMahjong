package com.bootstrapmahjong;

public class FlowerCombo extends MultiplierDecorator {

    public FlowerCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Flower Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
