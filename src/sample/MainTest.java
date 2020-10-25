package sample;

import java.sql.SQLException;

public class MainTest {
    public static void main(String[] args) throws SQLException {

//        Datasource datasource = new Datasource();
//        if (!datasource.open()) {
//            System.out.println("Can't open datasource");
//            return;
//        }

//        datasource.insertIntoTurInfo("Bø");
//        datasource.insertIntoTurInfo("Selfors");
//        datasource.insertIntoTurInfo("Mo Fjellet");
//        datasource.insertIntoTurInfo("Hemsedal");

//        datasource.insertMedlem("Sandra","Lia 12", "12312323", "salamander@epost.com");

//        List<Medlem> medlemmer = datasource.queryMedlem(SortOrder.NONE);
//        if (medlemmer == null) {
//            System.out.println("Ingen medlemmer!");
//            return;
//        }
//
//        for (Medlem medlem : medlemmer) {
//            System.out.println(
//                    "Navn: " + medlem.getName() +
//                    ", Adr: " + medlem.getAdresse() + ", Tlf: " + medlem.getTelefon() +
//                    ", Epost: " + medlem.getEpost());
//        }
//
//        System.out.println("\n****************\n");
//
//        String s = "2020-10-09";
//        Date d = Date.valueOf(s);


//        datasource.insertKontigent(d, "Bj@epost.com");


//        String ss = "2020-20-04";
//        d = Date.valueOf(ss);



//        medlemmer = datasource.queryKontigentAAr(d ,SortOrder.NONE);
//        if (medlemmer == null) {
//            System.out.println("Ingen medlemmer!");
//            return;
//        }
//
//        System.out.println("Ikke betalt pr dato " + s);
//        for (Medlem medlem : medlemmer) {
//            System.out.println("Navn: " + medlem.getName() + " Epost: " + medlem.getEpost());
//        }
//
//        System.out.println("\n****************\n");

//        datasource.insertDeltagelse("salamander@epost.com", 1);

//        System.out.println("\n****************\n");
//
//
//        int ant = 1;
//        medlemmer = datasource.queryDeltagleseAnt(ant);
//        if (medlemmer == null) {
//            return;
//        }
//
//        for (Medlem medlem : medlemmer) {
//            System.out.println("Navn: " + medlem.getName() + " Epost: " + medlem.getEpost());
//        }
//
//
//        medlemmer = datasource.queryDeltagelseMax();
//        if (medlemmer == null) {
//            return;
//        }
//
//        for (Medlem medlem : medlemmer) {
//            System.out.println("Navn: " + medlem.getName() + " Epost: " + medlem.getEpost());
//        }
//
//
//
//        datasource.close();
    }
}
