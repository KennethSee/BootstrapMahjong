package com.bootstrapmahjong;
import java.util.List;

public class TileSet {

    // Constructor
    public TileSet(List<Tile> tiles, String setType) {
        this.tiles = tiles;
        this.setType = setType;
    }

    // Properties
    public List<Tile> tiles;
    public String setType;
}
