package com.bootstrapmahjong;

public class PureTerminalsCombo extends MultiplierDecorator {

    public PureTerminalsCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Pure Terminals Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 5;
    }

}
