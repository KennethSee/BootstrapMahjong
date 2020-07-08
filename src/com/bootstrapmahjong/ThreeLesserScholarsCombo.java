package com.bootstrapmahjong;

public class ThreeLesserScholarsCombo extends MultiplierDecorator {

    public ThreeLesserScholarsCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Three Lesser Scholars Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
