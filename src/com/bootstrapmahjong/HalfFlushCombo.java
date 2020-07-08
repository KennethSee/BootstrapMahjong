package com.bootstrapmahjong;

public class HalfFlushCombo extends MultiplierDecorator {

    public HalfFlushCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Half Flush Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 2;
    }

}
