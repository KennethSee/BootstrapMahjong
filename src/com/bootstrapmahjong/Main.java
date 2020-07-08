package com.bootstrapmahjong;

import java.sql.*;
import java.util.*;

import static java.util.Arrays.*;

public class Main {

    public static void main(String[] args) {
	    Player p1 = new Player(1, "p1");
        Player p2 = new Player(2, "p2");
        Player p3 = new Player(3, "p3");
        Player p4 = new Player(4, "p4");

        List<Tile> allTiles = new ArrayList<Tile>();

        // Connect to DB
        Connection db = createConnection("C:\\Users\\ksee\\Documents\\Personal Documents\\BootstrapMahjong\\src\\db\\mahjong.db");

        // Query all relevant tiles
        String sql = "SELECT ID, Value, Suit FROM Tiles WHERE Suit NOT IN ('Animal', 'Flower')";
        try (Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                // Add four of each normal tile
                for (int i = 0; i < 4; i++) {
                    allTiles.add(new Tile(rs.getString("Value"), rs.getString("Suit")));
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        sql = "SELECT ID, Value, Suit FROM Tiles WHERE Suit IN ('Flower', 'Animal')";
        try (Statement stmt2 = db.createStatement();
             ResultSet rs2 = stmt2.executeQuery(sql)) {
            while (rs2.next()) {
                // Add one of each special tile
                allTiles.add(new Tile(rs2.getString("Value"), rs2.getString("Suit")));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Initialize new game
        List<Player> players= new ArrayList<Player>(asList(p1, p2, p3, p4));
        Game game = new Game(players, allTiles);
//        game.startGame();
//        System.out.println("Game End!");
//        Tile t1 = new Tile("1", "Dot");
//        Tile t2 = new Tile("1", "Dot");
//        Tile t3 = new Tile("3", "Dot");
//        Tile t4 = new Tile("4", "Dot");
//        Tile t5 = new Tile("5", "Dot");
//        Tile t6 = new Tile("7", "Dot");
//        Tile t7 = new Tile("7", "Dot");
//        Tile t8 = new Tile("1", "Bamboo");
//        Tile t9 = new Tile("4", "Bamboo");
//        Tile t10 = new Tile("1", "Character");
//        Tile t11 = new Tile("3", "Character");
//        Tile t12 = new Tile("9", "Character");
//        Tile t13 = new Tile("West", "Wind");
//        Tile t14 = new Tile("2", "Dot");
//        List<Tile> test = new ArrayList<>(Arrays.asList(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13));
////
//        List<Action> potentialActions = new ArrayList<Action>();
        Player player = new Player(1, "p1");
//        Player player2 = new Player(2, "p2");
//        Player player3 = new Player(3, "p3");
//        player.temporaryWindIndex = 1;
//        player2.temporaryWindIndex = 4;
//        player3.temporaryWindIndex = 2;
//        player.closeTiles = test;
//        player2.closeTiles = test;
//        player3.closeTiles= test;
//        player.checkPlayerPotentialActions(potentialActions, t14, player2);
//        for (Action action : potentialActions) {
//            System.out.println(action.display());
//        }

    }

    static Connection createConnection(String path) {
        Connection conn = null;
        path = "jdbc:sqlite:" + path;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(path);
        }
        catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
