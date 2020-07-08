package com.bootstrapmahjong;

import java.util.Comparator;

public class ActionPlayerSorter implements Comparator<Action> {

    public ActionPlayerSorter(Player referencePlayer) {
        this.referencePlayer = referencePlayer;
    }

    public Player referencePlayer;

    @Override
    public int compare(Action a1, Action a2) {
        int relativeWind1 = a1.getPlayer().temporaryWindIndex - referencePlayer.temporaryWindIndex;
        int relativeWind2 = a2.getPlayer().temporaryWindIndex - referencePlayer.temporaryWindIndex;

        // If difference is negative, add 4 to push to back of queue
        if (relativeWind1 < 0) {
            relativeWind1 = relativeWind1 + 4;
        }
        if (relativeWind2 < 0) {
            relativeWind2 = relativeWind2 + 4;
        }

        if (relativeWind1 < relativeWind2) {
            return -1;
        }
        else if (relativeWind1 > relativeWind2) {
            return 1;
        }
        else return 0;
    }

}
