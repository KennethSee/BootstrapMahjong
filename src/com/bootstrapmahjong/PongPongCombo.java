package com.bootstrapmahjong;

public class PongPongCombo extends MultiplierDecorator {

    public PongPongCombo(MultiplierCombination base) {
        super(base);
    }

    @Override
    public String getDescription() {
        return base.getDescription() + ", Pong Pong Combo";
    }

    @Override
    public int getMultiplier() {
        return base.getMultiplier() + 2;
    }

}
