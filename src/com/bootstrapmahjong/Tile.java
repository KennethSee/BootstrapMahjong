package com.bootstrapmahjong;

public class Tile {

    // Constructor
    public Tile(String value, String suit) {
        this.value = value;
        this.suit = suit;
        this.fullName = value + " " + suit;
    }

    // Properties
    public String value;
    public String suit;
    public int index;
    public String fullName;
}
