package com.bootstrapmahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round {

    public Round(Game game){
        this.publicTiles = new ArrayList<Tile>();
        for (Tile tile : game.allTiles) {
            this.publicTiles.add(tile);
            this.tilesLeft++;
        }
        this.discardedTiles = new ArrayList<Tile>();

        players = new ArrayList<Player>();
        for (Player player : game.players) {
            this.players.add(player);
        }
        // Sort players by temporary wind
        Collections.sort(this.players, (Player p1, Player p2) -> p1.temporaryWindIndex - p2.temporaryWindIndex);

        this.isRoundOver = false;
        this.isFirstTurnOver = false;
        this.isActionMade = false;
    }

    public List<Tile> publicTiles;
    public List<Tile> discardedTiles;
    public int tilesLeft;
    protected List<Player> players;
    protected boolean isRoundOver;
    private boolean isFirstTurnOver;
    public Player currentTurnPlayer;
    protected boolean isActionMade;

    protected void playRound() {
        // Shuffle round tiles
        Collections.shuffle(this.publicTiles);

        // Distribute tiles to players
        for (Player player : this.players) {
            if (player.temporaryWindIndex == 1) {
                // Draw 14 tiles
                player.drawTile(this.publicTiles, 14);
            }
            else {
                // Draw 13 tiles
                player.drawTile(this.publicTiles, 13);
            }
            player.closeTiles = GameHelpers.sortTiles(player.closeTiles);
        }

        // Begin round
        int discardTileIndex;
        this.currentTurnPlayer = this.players.get(0);;
        while (!isRoundOver) {
            // Reinitialize potential action lists at start of each turn
            List<Action> potentialActions = new ArrayList<Action>();

            System.out.println(this.currentTurnPlayer.displayName + "'s turn");

            if (!isFirstTurnOver) {
                // Only discard tile if first turn
                System.out.println("Choose tile to discard:");
                this.currentTurnPlayer.showCloseTiles();
                discardTileIndex = selectTileToDiscard(this.currentTurnPlayer.closeTiles.size());
                this.currentTurnPlayer.discardTile(this, discardTileIndex, potentialActions);
                isFirstTurnOver = true; // End of first turn
            }
            else if (isActionMade) {
                // Only discard tile if action was made
                System.out.println("Choose tile to discard:");
                this.currentTurnPlayer.showCloseTiles();
                discardTileIndex = selectTileToDiscard(this.currentTurnPlayer.closeTiles.size());
                this.currentTurnPlayer.discardTile(this, discardTileIndex, potentialActions);
            }
            else {
                // Draw tile
                this.currentTurnPlayer.drawTile(this.publicTiles, 1);
                // Discard tile
                System.out.println("Choose tile to discard:");
                this.currentTurnPlayer.showCloseTiles();
                discardTileIndex = selectTileToDiscard(this.currentTurnPlayer.closeTiles.size());
                this.currentTurnPlayer.discardTile(this, discardTileIndex, potentialActions);
            }
        }
    }

    private static int selectTileToDiscard(int numTiles) {
        int x;
        try {
            System.out.println("Select tile to discard: ");
            x = Integer.parseInt(System.console().readLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            x = selectTileToDiscard(numTiles);
        }
        if (x < 0 || x >= numTiles) {
            System.out.println("Please enter an integer within range.");
            x = selectTileToDiscard(numTiles);
        }
        return x;
    }

    protected void setNextPlayer(Player currentTurnPlayer) {
        Player nextPlayer = null;
        for (Player player : this.players) {
            if (player.isNext(currentTurnPlayer)) {
                nextPlayer = player;
            }
        }
        this.currentTurnPlayer = nextPlayer;
    }
}
