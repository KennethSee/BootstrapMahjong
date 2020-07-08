package com.bootstrapmahjong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameHelpers {

    public static List<Tile> sortTiles(List<Tile> tiles) {
        List<Tile> dotsList = new ArrayList<Tile>();
        List<Tile> bamboosList = new ArrayList<Tile>();
        List<Tile> charactersList = new ArrayList<Tile>();
        List<Tile> windsList = new ArrayList<Tile>();
        List<Tile> dragonsList = new ArrayList<Tile>();
        List<Tile> finalList = new ArrayList<Tile>();

        // Group tiles by suit
        for (Tile tile : tiles)
        {
            if (tile.suit.equals("Dot"))
            {
                dotsList.add(tile);
            }
            else if (tile.suit.equals("Bamboo"))
            {
                bamboosList.add(tile);
            }
            else if (tile.suit.equals("Character"))
            {
                charactersList.add(tile);
            }
            else if (tile.suit.equals("Wind"))
            {
                windsList.add(tile);
            }
            else if (tile.suit.equals("Dragon"))
            {
                dragonsList.add(tile);
            }
        }

        // Sort each group
        Collections.sort(dotsList, (Tile t1, Tile t2) -> t1.value.compareTo(t2.value));
        Collections.sort(bamboosList, (Tile t1, Tile t2) -> t1.value.compareTo(t2.value));
        Collections.sort(charactersList, (Tile t1, Tile t2) -> t1.value.compareTo(t2.value));
        Collections.sort(windsList, (Tile t1, Tile t2) -> t1.value.compareTo(t2.value));
        Collections.sort(dragonsList, (Tile t1, Tile t2) -> t1.value.compareTo(t2.value));

        // Recombine tiles
        List<List<Tile>> groupList = new ArrayList<List<Tile>>(
                Arrays.asList(dotsList, bamboosList, charactersList, windsList, dragonsList));
        for (List<Tile> group : groupList)
        {
            for (Tile orderedTile : group)
            {
                finalList.add(orderedTile);
            }
        }

        // Set player's open tiles to the ordered list
        return finalList;
    }

    public static String getWindName(int windIndex)
    {
        /// Receives input of wind index and return wind name.
        if (windIndex == 1)
        {
            return "East";
        }
        else if (windIndex == 2)
        {
            return "South";
        }
        else if (windIndex == 3)
        {
            return "West";
        }
        else
        {
            return "North";
        }
    }

    public static boolean isPong(Tile t1, Tile t2, Tile t3) {

        if (t1.value.equals(t2.value) && t1.value.equals(t3.value)
            && t1.suit.equals(t2.suit) && t1.suit.equals(t3.suit)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isGong(Tile t1, Tile t2, Tile t3, Tile t4) {
        if (t1.value.equals(t2.value) && t1.value.equals(t3.value) && t1.value.equals(t4.value)
            && t1.suit.equals(t2.suit) && t1.suit.equals(t3.suit) && t1.suit.equals(t4.suit)) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean isChow(Tile t1, Tile t2, Tile t3) {
        if (t1.suit.equals(t2.suit) && t1.suit.equals(t3.suit)) {
            List<Tile> tempList = new ArrayList<Tile>(Arrays.asList(t1, t2, t3));
            tempList = sortTiles(tempList);
            try {
                int i = Integer.parseInt(tempList.get(0).value);
                int j = Integer.parseInt(tempList.get(1).value);
                int k = Integer.parseInt(tempList.get(2).value);
                if (j - i != 1 || k - j != 1) {
                    // Not consecutive
                    return false;
                }
                return true;
            }
            catch (NumberFormatException e) {
                // Not numerical values
                return false;
            }

        }
        else {
            // Not same suit
            return false;
        }
    }

    public static boolean isWin(List<Tile> tiles) {
        // Check if tiles can be used to end the game
        // Assumes sorted tiles
        int numTiles = tiles.size();
        int n = 0;
        while (n < numTiles) {
            if (numTiles - n == 2) {
                // Check for eyes
                if (tiles.get(n).value.equals(tiles.get(n+1).value) && tiles.get(n).suit.equals(tiles.get(n+1).suit)) {
                    n = n + 2;
                }
                else {
                    return false;
                }
            }
            else if (numTiles - n == 3) {
                // Check for pong and chow
                if (isPong(tiles.get(n), tiles.get(n+1), tiles.get(n+2))) {
                    n = n + 3;
                }
                else if (isChow(tiles.get(n), tiles.get(n+1), tiles.get(n+2))) {
                    n = n + 3;
                }
                else {
                    return false;
                }
            }
            else {
                // Check for all set types
                if (isGong(tiles.get(n), tiles.get(n+1), tiles.get(n+2), tiles.get(n+3))) {
                    n = n + 4;
                }
                else if (isPong(tiles.get(n), tiles.get(n+1), tiles.get(n+2))) {
                    n = n + 3;
                }
                else if (isChow(tiles.get(n), tiles.get(n+1), tiles.get(n+2))) {
                    n = n + 3;
                }
                else if (tiles.get(n).value.equals(tiles.get(n+1).value) &&
                        tiles.get(n).suit.equals(tiles.get(n+1).suit)) {
                    n = n + 2;
                }
                else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void displayActions(List<Action> actionList) {
        int numAction = actionList.size();
        for (int i = 0; i < numAction; i++) {
            System.out.println(i + ": " + actionList.get(i).display());
        }
    }
}
