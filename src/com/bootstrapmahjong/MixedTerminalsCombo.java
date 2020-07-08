package com.bootstrapmahjong;

public class MixedTerminalsCombo extends MultiplierDecorator {

    public MixedTerminalsCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Mixed Terminals Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 2;
    }

}
