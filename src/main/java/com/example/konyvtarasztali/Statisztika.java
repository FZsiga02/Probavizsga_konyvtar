package com.example.konyvtarasztali;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statisztika {
    private static List<Konyv> konyvek;

    public static void main(String[] args) {
        try {
            beolvas();
            System.out.printf("500 oldalnál hosszabb könyvek száma: %d\n", get500oldalnalHosszabbKonyvekSzama());
            System.out.printf("%s 1950-nél régebbi könyv\n", is1950nelRegebbi()? "Van" : "Nincs");
            System.out.printf("A leghosszabb könyv:\n%s\n", getLeghosszabbKonyv());;
            System.out.printf("A legtöbb könyvvel rendelkező szerző: %s\n", getLegtobbKonyvvelRendelkezoSzerzo());
        } catch (SQLException e) {
            System.out.println("Hiba történt az adatbázis kapcsolat kialakításakor");
        }
    }

    private static String getLegtobbKonyvvelRendelkezoSzerzo() {
        return konyvek.stream()
                // Map objektumot készítek, melynek kulcsa a szerző, értéke, hogy hány könyvvel rendelkezik a szerző
                .collect(Collectors.groupingBy(Konyv::getAuthor, Collectors.counting()))
                // A map bejegyzéseiből stream-et készítek
                .entrySet().stream()
                // Maximum kiválasztás a könyvek száma alapján
                .max(Comparator.comparingLong(Map.Entry::getValue))
                // Maximális érték kulcsát (szerző) adom vissza
                .get().getKey();
    }

    private static Konyv getLeghosszabbKonyv() {
        return konyvek.stream().max(Comparator.comparingInt(Konyv::getPage_count)).get();
    }

    private static boolean is1950nelRegebbi() {
        return konyvek.stream().anyMatch(konyv -> konyv.getPublish_year() < 1950);
    }

    private static long get500oldalnalHosszabbKonyvekSzama() {
       return konyvek.stream().filter(konyv -> konyv.getPage_count() > 500.).count();
    }

    private static void beolvas() throws SQLException {
        Adatbazis db = new Adatbazis();
        konyvek = db.readAllBook();

    }
}
