package com.bootstrapmahjong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PongAction implements Action {

    // Constructor
    public PongAction(Player player, int begIndex, Tile hypoTile, Round round) {
        this.player = player;
        this.begIndex = begIndex;
        this.hypoTile = hypoTile;
        this.tile1 = player.closeTiles.get(begIndex);
        this.tile2 = player.closeTiles.get(begIndex + 1);
        this.round = round;
    }

    // Properties
    private Player player;
    private int begIndex;
    private Tile hypoTile;
    private Tile tile1;
    private Tile tile2;
    private Round round;

    // Methods
    @Override
    public String display() {
        List<Tile> tiles = new ArrayList<>(Arrays.asList(tile1, tile2, hypoTile));
        return "Pong: " + tiles.get(0).fullName + " " + tiles.get(1).fullName + " "
                + tiles.get(2).fullName;
    }

    @Override
    public void execute() {
        // Make actionable tiles a tile set and move set into player's open tiles
        player.closeTiles.remove(begIndex + 1);
        player.closeTiles.remove(begIndex);

        List<Tile> tiles = new ArrayList<>(Arrays.asList(tile1, tile2, hypoTile));

        TileSet tempTileSet = new TileSet(tiles, "Pong");
        this.player.openTiles.add(tempTileSet);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
