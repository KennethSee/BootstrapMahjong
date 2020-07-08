package com.bootstrapmahjong;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GongAction implements Action {

    // Constructor
    public GongAction(Player player, int begIndex, Tile hypoTile, Round round) {
        this.player = player;
        this.begIndex = begIndex;
        this.hypoTile = hypoTile;
        this.tile1 = player.closeTiles.get(begIndex);
        this.tile2 = player.closeTiles.get(begIndex + 1);
        this.tile3 = player.closeTiles.get(begIndex + 2);
        this.round = round;
    }

    // Properties
    private Player player;
    private int begIndex;
    private Tile hypoTile;
    private Tile tile1;
    private Tile tile2;
    private Tile tile3;
    private Round round;

    // Methods
    @Override
    public String display() {
        List<Tile> tiles = new ArrayList<>(Arrays.asList(tile1, tile2, tile3, hypoTile));
        return "Gong: " + tiles.get(0).fullName + " " + tiles.get(1).fullName + " "
                + tiles.get(2).fullName + " " + tiles.get(3).fullName;
    }

    @Override
    public void execute() {
        // Make actionable tiles a tile set and move set into player's open tiles
        player.closeTiles.remove(begIndex + 2);
        player.closeTiles.remove(begIndex + 1);
        player.closeTiles.remove(begIndex);

        List<Tile> tiles = new ArrayList<>(Arrays.asList(tile1, tile2, tile3, hypoTile));

        TileSet tempTileSet = new TileSet(tiles, "Gong");
        this.player.openTiles.add(tempTileSet);

        // Draw special
        this.player.drawTileSpecial(round.publicTiles);
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
