package com.bootstrapmahjong;

public class MultiplierDecorator implements MultiplierCombination {

    public MultiplierDecorator(MultiplierCombination base) {
        this.base = base;
    }

    protected MultiplierCombination base;

    @Override
    public String getDescription() {
        return base.getDescription();
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier();
    }
}
