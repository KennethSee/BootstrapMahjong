package com.bootstrapmahjong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChowAction implements Action {

    // Constructor
    public ChowAction(Player player, int begIndex, Tile hypoTile, Round round) {
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
        List<Tile> unsortedTiles = new ArrayList<>(Arrays.asList(tile1, tile2, hypoTile));
        List<Tile> sortedTiles = GameHelpers.sortTiles(unsortedTiles);
        return "Chow: " + sortedTiles.get(0).fullName + " " + sortedTiles.get(1).fullName + " "
                + sortedTiles.get(2).fullName;
    }

    @Override
    public void execute() {
        // Make actionable tiles a tile set and move set into player's open tiles
        player.closeTiles.remove(begIndex + 1);
        player.closeTiles.remove(begIndex);

        List<Tile> unsortedTiles = new ArrayList<>(Arrays.asList(tile1, tile2, hypoTile));
        List<Tile> sortedTiles = GameHelpers.sortTiles(unsortedTiles);

        TileSet tempTileSet = new TileSet(sortedTiles, "Chow");
        this.player.openTiles.add(tempTileSet);
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
