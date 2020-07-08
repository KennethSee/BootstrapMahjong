package com.bootstrapmahjong;

import java.util.Comparator;

public class ActionTypeSorter implements Comparator<Action> {

    @Override
    public int compare(Action a1, Action a2) {
        if (a1.getPriority() < a2.getPriority()) {
            return -1;
        }
        else if (a1.getPriority() > a2.getPriority()) {
            return 1;
        }
        else return 0;
    }

}
