package com.bootstrapmahjong;

public class FullFlushCombo extends MultiplierDecorator {

    public FullFlushCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Full Flush Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 4;
    }

}
