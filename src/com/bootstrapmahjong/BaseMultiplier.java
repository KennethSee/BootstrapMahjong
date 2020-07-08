package com.bootstrapmahjong;

public class BaseMultiplier implements MultiplierCombination {

//    public BaseMultiplier(Round round, Player player) {
//        this.round = round;
//        this.player = player;
//    }
//
//    protected Round round;
//    protected Player player;

    @Override
    public String getDescription() {
        return "Base multiplier";
    }

    @Override
    public int getMultiplier() {
        return 0;
    }
}
