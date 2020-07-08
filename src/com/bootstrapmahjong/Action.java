package com.bootstrapmahjong;

public interface Action {

    public String display();
    public void execute();
    public int getPriority();
    public Player getPlayer();

}
