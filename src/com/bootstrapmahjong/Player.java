package com.bootstrapmahjong;
import java.util.*;

public class Player {

    // Constructor
    public Player(int userID, String displayName) {
        this.userID = userID;
        this.displayName = displayName;
        this.closeTiles = new ArrayList<Tile>();
        this.openTiles = new ArrayList<TileSet>();
        this.roundMultiplier = new BaseMultiplier();
    }

    // Properties
    public int userID;
    public String displayName;
    public int temporaryWindIndex;
    public double money;
    public List<Tile> closeTiles;
    public List<TileSet> openTiles;
    public MultiplierCombination roundMultiplier;

    // Methods
    protected void rotateTemporaryWind() {
        // Moves the assignment of temporary winds counter clockwise
        if (this.temporaryWindIndex == 4) {
            this.temporaryWindIndex = 1;
        }
        else {
            this.temporaryWindIndex++;
        }
    }

    protected void reset() {
        // Clear tiles and multiplier
        this.closeTiles = new ArrayList<Tile>();
        this.openTiles = new ArrayList<TileSet>();
        this.roundMultiplier = new BaseMultiplier();
    }

    public void drawTile(List<Tile> publicTiles, int n) {
        // Draw n number of tiles from public tiles
        Tile lastTile;
        List<Tile> t1;
        String suitName;
        for (int i = 0; i < n; i++) {
            lastTile = publicTiles.get(publicTiles.size() - 1);
            if (lastTile.suit.equals("Flower") || lastTile.suit.equals("Animal")) {
                // Immediately open tile and draw from the beginning of public tiles if a special tile is drawn
                t1 = new ArrayList<>(Arrays.asList(lastTile));
                suitName = lastTile.suit;
                this.openTiles.add(new TileSet(t1, suitName));
                publicTiles.remove(publicTiles.size() - 1);
                this.drawTileSpecial(publicTiles); // Draw special tile
            }
            else {
                this.closeTiles.add(lastTile);
                publicTiles.remove(publicTiles.size() - 1);
            }
        }
    }

    protected void drawTileSpecial(List<Tile> publicTiles) {
        // Take one tile from the beginning
        Tile begTile = publicTiles.get(0);
        List<Tile> t1;
        String suitName;
        if (begTile.suit.equals("Flower") || begTile.suit.equals("Animal")) {
            t1 = new ArrayList<>(Arrays.asList(begTile));
            suitName = begTile.suit;
            this.openTiles.add(new TileSet(t1, suitName));
            publicTiles.remove(0);
            this.drawTileSpecial(publicTiles);
        }
        else {
            this.closeTiles.add(begTile);
            publicTiles.remove(0);
        }
    }

    protected void showCloseTiles() {
        for (int i = 0; i < this.closeTiles.size(); i++) {
            System.out.println(i + ": " + this.closeTiles.get(i).fullName);
        }
    }

    protected void discardTile(Round round, int discardIndex, List<Action> potentialActions) {
        Tile tileToDiscard = this.closeTiles.get(discardIndex);
        round.discardedTiles.add(tileToDiscard);
        this.closeTiles.remove(discardIndex);

        // Sort player's close tiles
        this.closeTiles = GameHelpers.sortTiles(this.closeTiles);

        // Check every other player for potential actions
        for (Player player : round.players) {
            if (player != this) {
                // Check for potential actions and add to list
                player.checkPlayerPotentialActions(potentialActions, tileToDiscard, this, round);
            }
        }

        // Arrange potential actions
        Collections.sort(potentialActions, new ActionTypeSorter()
                                            .thenComparing(new ActionPlayerSorter(this)));

        // Group adjacent action with same players
        List<List<Action>> actionsGroupedByPlayer = new ArrayList<List<Action>>();
        for (Action action : potentialActions) {
            if (actionsGroupedByPlayer.size() == 0) {
                actionsGroupedByPlayer.add(new ArrayList<Action>(Arrays.asList(action)));
            }
            else {
                // Check if this action's player is the same as the last grouped actions' player
                if (action.getPlayer() == actionsGroupedByPlayer.get(actionsGroupedByPlayer.size() - 1).
                        get(0).getPlayer()) {
                    // Add action to last grouped action list
                    actionsGroupedByPlayer.get(actionsGroupedByPlayer.size() - 1).add(action);
                }
                else {
                    // Create new grouped action list
                    actionsGroupedByPlayer.add(new ArrayList<Action>(Arrays.asList(action)));
                }
            }
        }

        // Ask each player if they would like to act on action
        int actionIndex;
        for (List<Action> actionList : actionsGroupedByPlayer) {
            round.currentTurnPlayer = actionList.get(0).getPlayer();
            System.out.println(round.currentTurnPlayer.displayName + ", choose action (enter -1 if none):");
            GameHelpers.displayActions(actionList);
            actionIndex = actOnAction(actionList);
            if (actionIndex != 1) {
                // Execute action
                actionList.get(actionIndex).execute();
                round.isActionMade = true;
                return;
            }
        }

        // If no player executes on any potential actions, move turn to next player
        round.setNextPlayer(this);
        round.isActionMade = false;
    }

    private static int actOnAction(List<Action> actionList) {
        int numAction = actionList.size();
        int x;
        try {
            x = Integer.parseInt(System.console().readLine());
        }
        catch (NumberFormatException e) {
            System.out.println("Please enter an integer.");
            x = actOnAction(actionList);
        }
        if (x < -1 || x >= numAction) {
            System.out.println("Please enter an integer within range.");
            x = actOnAction(actionList);
        }
        return x;
    }

    protected void checkPlayerPotentialActions(List<Action> potentialActions, Tile discardedTile,
                                               Player currentTurnPlayer, Round round) {
        ActionFactory potentialActionFactory;
        int numTiles = this.closeTiles.size();

        // Check if winning is possible
        List<Tile> checkWin = new ArrayList<Tile>();
        for (Tile tile : this.closeTiles) {
            checkWin.add(tile);
        }
        checkWin.add(discardedTile);
        checkWin = GameHelpers.sortTiles(checkWin);
        if (GameHelpers.isWin(checkWin)) {
            potentialActionFactory = new ActionFactory(this, discardedTile, "Win", round);
            potentialActions.add(potentialActionFactory.assignAction());
        }

        // Check for other actions
        for (int i = 0; i < numTiles; i++) {
            if (numTiles - i > 2) {
                // Check for gong
                if (GameHelpers.isGong(this.closeTiles.get(i), this.closeTiles.get(i+1), this.closeTiles.get(i+2),
                        discardedTile)) {
                    potentialActionFactory = new ActionFactory(this, i, discardedTile, "Gong", round);
                    potentialActions.add(potentialActionFactory.assignAction());
                }
                // Check for pong
                if (GameHelpers.isPong(this.closeTiles.get(i), this.closeTiles.get(i+1), discardedTile)) {
                    potentialActionFactory = new ActionFactory(this, i, discardedTile, "Pong", round);
                    potentialActions.add(potentialActionFactory.assignAction());
                }
                // Check for chow if is next player
                if (GameHelpers.isChow(this.closeTiles.get(i), this.closeTiles.get(i+1), discardedTile) &&
                    isNext(currentTurnPlayer)) {
                    potentialActionFactory = new ActionFactory(this, i, discardedTile, "Chow", round);
                    potentialActions.add(potentialActionFactory.assignAction());
                }
            }
            else if (numTiles - i > 1) {
                // Check for pong
                if (GameHelpers.isPong(this.closeTiles.get(i), this.closeTiles.get(i+1), discardedTile)) {
                    potentialActionFactory = new ActionFactory(this, i, discardedTile, "Pong", round);
                    potentialActions.add(potentialActionFactory.assignAction());
                }
                // Check for chow if is next player
                if (GameHelpers.isChow(this.closeTiles.get(i), this.closeTiles.get(i+1), discardedTile) &&
                        isNext(currentTurnPlayer)) {
                    potentialActionFactory = new ActionFactory(this, i, discardedTile, "Chow", round);
                    potentialActions.add(potentialActionFactory.assignAction());
                }
            }
        }
    }

    protected boolean isNext(Player referencePlayer) {
        // Check if this player is right after the reference player
        int referenceWindIndex = referencePlayer.temporaryWindIndex;
        int thisWindIndex = this.temporaryWindIndex;

        if (thisWindIndex == 1 && referenceWindIndex == 4) {
            return true;
        }
        else if (thisWindIndex - referenceWindIndex == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
