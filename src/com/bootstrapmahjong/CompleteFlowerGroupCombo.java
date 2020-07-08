package com.bootstrapmahjong;

public class CompleteFlowerGroupCombo extends MultiplierDecorator {

    public CompleteFlowerGroupCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Complete Flower Group Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
