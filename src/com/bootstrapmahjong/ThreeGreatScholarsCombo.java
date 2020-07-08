package com.bootstrapmahjong;

public class ThreeGreatScholarsCombo extends MultiplierDecorator {

    public ThreeGreatScholarsCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Three Greater Scholars Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 10;
    }

}
