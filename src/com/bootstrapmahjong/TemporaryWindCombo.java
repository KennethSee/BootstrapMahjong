package com.bootstrapmahjong;

public class TemporaryWindCombo extends  MultiplierDecorator {

    public TemporaryWindCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Temporary Wind Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 1;
    }

}
