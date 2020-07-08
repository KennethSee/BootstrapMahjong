package com.bootstrapmahjong;

public class ActionFactory {
    // Decides which action class to initialize

    // Constructors
    public ActionFactory(Player player, int begIndex, Tile hypoTile, String actionType, Round round) {
        this.player = player;
        this.hypoTile = hypoTile;
        this.begIndex = begIndex;
        this.actionType = actionType;
        this.round = round;
    }

    public ActionFactory(Player player, Tile hypoTile, String actionType, Round round) {
        this.player = player;
        this.hypoTile = hypoTile;
        this.actionType = actionType;
        this.begIndex = -1; // not necessary for win action
        this.round = round;
    }

    // Properties
    private Player player;
    private int begIndex;
    private Tile hypoTile;
    private String actionType;
    private Round round;

    // Methods
    public Action assignAction() {
        if (this.actionType.equals("Pong")) {
            return new PongAction(this.player, this.begIndex, this.hypoTile, this.round);
        }
        else if (this.actionType.equals("Gong")) {
            return new GongAction(this.player, this.begIndex, this.hypoTile, this.round);
        }
        else if (this.actionType.equals("Chow")) {
            return new ChowAction(this.player, this.begIndex, this.hypoTile, this.round);
        }
        else if (this.actionType.equals("Win")) {
            return new WinAction(this.player, this.hypoTile, this.round);
        }
        else {
            return null;
        }
    }
}
