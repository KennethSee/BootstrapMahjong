package com.bootstrapmahjong;

public class DragonComboMultiplier extends MultiplierDecorator {

    public DragonComboMultiplier(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Dragon Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }
}
