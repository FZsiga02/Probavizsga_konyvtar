package com.example.konyvtarasztali;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Statisztika {
    private static List<Konyv> konyvek;

    public static void main(String[] args) {
        try {
            beolvas();
        } catch (SQLException e) {
            System.out.println("Hiba történt az adatbázis kapcsolat kialakításakor");
        }
    }

    private static void beolvas() throws SQLException {
        Adatbazis db = new Adatbazis();
        konyvek = db.readAllBook();

    }
}
