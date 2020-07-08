package com.bootstrapmahjong;

import java.util.Collections;
import java.util.List;

public class Game {

    // Constructor
    public Game(List<Player> players, List<Tile> allTiles) {
        this.players = players;
        this.allTiles = allTiles;
        this.windIndex = 1;
        this.windChangeCounter = 1;
        this.isGame = false;
    }

    // Properties
    public List<Player> players;
    public int windIndex;
    public int windChangeCounter;
    protected List<Tile> allTiles;
    public boolean isGame;
    public Round currentRound;

    // Methods
    public void startGame() {
        // Shuffle players and assign initial temporary winds
        Collections.shuffle(this.players);
        for (int i = 0; i < 4; i++) {
            this.players.get(i).temporaryWindIndex = i + 1;
        }
        this.startRound();
    }

    public void startRound() {
        // Distribute starting tiles to players

        // Wipe round records and reinitialize round
        this.currentRound = new Round(this);
        this.currentRound.playRound();
    }

    public void nextRound(Player winner, boolean isDraw) {
        /*
        Prepares table for next round. If winner was the temporary East or the round ended in a draw, temporary winds
        do not change. Else, rotate the temporary winds counter clockwise.
         */
        if (isDraw == false && winner.temporaryWindIndex != 1) {
            this.increaseWindChangeCounter();
            for (Player player : this.players) {
                player.reset();
                // Rotate temporary winds if the temporary East is not the winner or round is not draw.
                player.rotateTemporaryWind();
            }
        }
        else {
            for (Player player : this.players) {
                player.reset();
            }
        }
    }

    private void increaseWindIndex() {
        if (this.windIndex == 4) {
            // Game is complete when the South wind is complete
            this.isGame = true;
        }
        else {
            this.windIndex++;
        }
    }

    private void increaseWindChangeCounter() {
        /*
        Increase the wind change counter by 1. If the wind change counter exceeds 4, it will be reset to 1 and the
        game moves to the next wind index.
         */
        if (this.windChangeCounter == 4) {
            this.windChangeCounter = 1;
            this.increaseWindIndex();
        }
        else {
            this.windChangeCounter++;
        }
    }
}
