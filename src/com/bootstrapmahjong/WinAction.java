package com.bootstrapmahjong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WinAction implements Action {

    // Constructor
    public WinAction(Player player, Tile hypoTile, Round round) {
        this.player = player;
        this.hypoTile = hypoTile;
        this.unsortedTiles = new ArrayList<Tile>(player.closeTiles);
        this.unsortedTiles.add(hypoTile);
        this.sortedTiles = GameHelpers.sortTiles(unsortedTiles);
        this.round = round;
    }

    // Properties
    private Player player;
    private Tile hypoTile;
    private List<Tile> unsortedTiles;
    private List<Tile> sortedTiles;
    private Round round;

    // Methods
    @Override
    public String display() {
        String output = "Win: ";
        for (Tile tile : this.sortedTiles) {
            output = output + tile.fullName + " ";
        }
        return output;
    }

    @Override
    public void execute() {
        // Add tile to close tiles and sort
        this.player.closeTiles.add(this.hypoTile);
        this.player.closeTiles = GameHelpers.sortTiles(this.player.closeTiles);

        // Break close tiles into tile sets and add sets to open tiles
        int numTiles = this.player.closeTiles.size();
        int n = 0;
        List<Tile> tempTiles;
        while (n < numTiles) {
            if (numTiles - n == 2) {
                // Check for eyes
                if (this.player.closeTiles.get(n).value.equals(this.player.closeTiles.get(n+1).value)
                        && this.player.closeTiles.get(n).suit.equals(this.player.closeTiles.get(n+1).suit)) {
                    // Add eye set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Eyes"));
                    n = n + 2;
                }
                else {
                    System.out.println("Set is not a winning set!");
                }
            }
            else if (numTiles - n == 3) {
                // Check for pong and chow
                if (GameHelpers.isPong(this.player.closeTiles.get(n), this.player.closeTiles.get(n+1),
                        this.player.closeTiles.get(n+2))) {
                    // Add pong set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1), this.player.closeTiles.get(n+2)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Pong"));
                    n = n + 3;
                }
                else if (GameHelpers.isChow(this.player.closeTiles.get(n), this.player.closeTiles.get(n+1),
                        this.player.closeTiles.get(n+2))) {
                    // Add chow set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1), this.player.closeTiles.get(n+2)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Chow"));
                    n = n + 3;
                }
                else {
                    System.out.println("Set is not a winning set!");
                }
            }
            else {
                // Check for all set types
                if (GameHelpers.isGong(this.player.closeTiles.get(n), this.player.closeTiles.get(n+1),
                        this.player.closeTiles.get(n+2), this.player.closeTiles.get(n+3))) {
                    // Add gong set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1), this.player.closeTiles.get(n+2),
                            this.player.closeTiles.get(n+3)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Gong"));
                    n = n + 4;
                }
                else if (GameHelpers.isPong(this.player.closeTiles.get(n), this.player.closeTiles.get(n+1),
                        this.player.closeTiles.get(n+2))) {
                    // Add pong set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1), this.player.closeTiles.get(n+2)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Pong"));
                    n = n + 3;
                }
                else if (GameHelpers.isChow(this.player.closeTiles.get(n), this.player.closeTiles.get(n+1),
                        this.player.closeTiles.get(n+2))) {
                    // Add chow set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1), this.player.closeTiles.get(n+2)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Chow"));
                    n = n + 3;
                }
                else if (this.player.closeTiles.get(n).value.equals(this.player.closeTiles.get(n+1).value)
                        && this.player.closeTiles.get(n).suit.equals(this.player.closeTiles.get(n+1).suit)) {
                    // Add eye set to open tiles
                    tempTiles = new ArrayList<Tile>(Arrays.asList(this.player.closeTiles.get(n),
                            this.player.closeTiles.get(n+1)));
                    this.player.openTiles.add(new TileSet(tempTiles, "Eyes"));
                    n = n + 2;
                }
            }
        }

        // Clear close tiles
        this.player.closeTiles.clear();
        this.round.isRoundOver = true;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public Player getPlayer() {
        return this.player;
    }
}
