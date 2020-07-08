package com.bootstrapmahjong;

public class AnimalCombo extends MultiplierDecorator {

    public AnimalCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Animal Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
