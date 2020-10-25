package sample.datasource;

import sample.model.Medlem;
import sample.model.SortOrder;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "plattfotDB.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databasesUdemy\\" + DB_NAME;

    public static final String TABLE_MEDLEM = "medlem";
    public static final String COLUMN_MEDLEM_NAVN = "navn";
    public static final String COLUMN_MEDLEM_ADRESSE = "adresse";
    public static final String COLUMN_MEDLEM_TELEFON = "telefon";
    public static final String COLUMN_MEDLEM_EPOST = "epost";


    public static final int INDEX_MEDLEM_EPOST = 1;
    public static final int INDEX_MEDLEM_NAVN = 2;
    public static final int INDEX_MEDLEM_ADRESSE = 3;
    public static final int INDEX_MEDLEM_TELEFON = 4;


    public static final String TABLE_KONTIGENT = "kontigent";
    public static final String COLUMN_KONTIGENT_ID = "idKontigent";
    public static final String COLUMN_KONTIGENT_BETALT_DATO = "BetaltDato";
    public static final String COLUMN_KONTIGENT_MEDLEMFK = "medlemEpostFK";

    public static final String TABLE_TUR_INFO = "turInfo";
    public static final String COLUMN_TUR_IDTUR = "idTur";
    public static final String COLUMN_TUR_TURRUTE = "turrute";

    public static final String TABLE_TURDELTAGELSE = "turdeltagelse";
    public static final String COLUMN_TURDELTAGELSE_IDTUR = "idTur";
    public static final String COLUMN_TURDELTAGELSE_MEDLEMFK = "medlemEpostFK";

    // DB TABLES
    public static final String CREATE_TABLE_MEDLEM = "CREATE TABLE IF NOT EXISTS " + TABLE_MEDLEM + " (\n"
            +  COLUMN_MEDLEM_EPOST + " TEXT PRIMARY KEY, \n"
            +  COLUMN_MEDLEM_NAVN + " TEXT, \n"
            +  COLUMN_MEDLEM_ADRESSE + " TEXT, \n"
            +  COLUMN_MEDLEM_TELEFON + " TEXT \n"
            + ");";

    public static final String CREATE_TABLE_KONTIGENT = "CREATE TABLE IF NOT EXISTS " + TABLE_KONTIGENT + " (\n"
            +  COLUMN_KONTIGENT_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n"
            +  COLUMN_KONTIGENT_BETALT_DATO + " TEXT, \n"
            +  COLUMN_KONTIGENT_MEDLEMFK + " TEXT, \n"
            + " FOREIGN KEY(" + COLUMN_KONTIGENT_MEDLEMFK + ") REFERENCES " + TABLE_MEDLEM + '(' + COLUMN_MEDLEM_EPOST + "));";

    public static final String CREATE_TABLE_TURDELTAGELSE = "CREATE TABLE IF NOT EXISTS " + TABLE_TURDELTAGELSE +" (\n"
            +  COLUMN_TURDELTAGELSE_IDTUR + " INTEGER, \n"
            +  COLUMN_TURDELTAGELSE_MEDLEMFK + " TEXT, \n"
            + " PRIMARY KEY(" + COLUMN_TURDELTAGELSE_IDTUR + ", " + COLUMN_TURDELTAGELSE_MEDLEMFK + "), \n"
            + " FOREIGN KEY(" + COLUMN_TURDELTAGELSE_MEDLEMFK + ") REFERENCES " + TABLE_MEDLEM + '(' + COLUMN_MEDLEM_EPOST + "), \n"
            + " FOREIGN KEY (" + COLUMN_TURDELTAGELSE_IDTUR + ") REFERENCES " + TABLE_TUR_INFO + '(' + COLUMN_TUR_IDTUR + "));";

    public static final String CREATE_TABLE_TUR_INFO = "CREATE TABLE IF NOT EXISTS " + TABLE_TUR_INFO + " (\n"
            + COLUMN_TUR_IDTUR + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, \n"
            + COLUMN_TUR_TURRUTE + " TEXT \n"
            + ");";



    // QUERIES MEDLEM
    public static final String QUERY_ALL_MEDLEM =
            "SELECT * FROM " + TABLE_MEDLEM;

    public static final String QUERY_ALL_MEDLEM_SORT =
            " ORDER BY " + TABLE_MEDLEM + "." + COLUMN_MEDLEM_NAVN + " COLLATE NOCASE ";

    public static final String QUERY_MEDLEM_NAVN =
            "SELECT * FROM " + TABLE_MEDLEM + " WHERE " + TABLE_MEDLEM + "." + COLUMN_MEDLEM_NAVN + " = ?";

    // QUERIES KONTIGENT
    public static final String QUERY_KONTIGENT = "SELECT * FROM " +
            TABLE_KONTIGENT + " WHERE " + COLUMN_KONTIGENT_MEDLEMFK + " = ? " + " AND " + COLUMN_KONTIGENT_BETALT_DATO + " = ?";

    public static final String QUERY_KONTIGENT_AAR = "SELECT "
            + TABLE_MEDLEM + "." + COLUMN_MEDLEM_NAVN + ", " + TABLE_MEDLEM + "." + COLUMN_MEDLEM_EPOST
            + " FROM " + TABLE_MEDLEM + " INNER JOIN "
            + TABLE_KONTIGENT + " ON " + TABLE_MEDLEM + "." + COLUMN_MEDLEM_EPOST
            + " = " + TABLE_KONTIGENT + "." + COLUMN_KONTIGENT_MEDLEMFK
            + " WHERE " + TABLE_KONTIGENT + "." + COLUMN_KONTIGENT_BETALT_DATO + " > \"";

    public static final String QUERY_KONTIGENT_AAR_SORT =
            " ORDER BY " + TABLE_KONTIGENT + "." + COLUMN_KONTIGENT_BETALT_DATO + " COLLATE NOCASE ";


    // QUERIES DELTAGELSE
    public static final String QUERY_DELTAGELSE = "SELECT * FROM " + TABLE_TURDELTAGELSE +
            " WHERE " + COLUMN_TURDELTAGELSE_MEDLEMFK + " = ? " + " AND "
            + COLUMN_TURDELTAGELSE_IDTUR + " = ?";


    public static final String QUERY_DELTAGELSE_ANT = "SELECT "
            + TABLE_MEDLEM + "." + COLUMN_MEDLEM_NAVN + ", " + TABLE_MEDLEM + "." + COLUMN_MEDLEM_EPOST
            + " FROM " + TABLE_MEDLEM + " INNER JOIN " + TABLE_TURDELTAGELSE + " ON "
            + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + " = "
            + TABLE_MEDLEM + "." + COLUMN_MEDLEM_EPOST
            + " GROUP BY " + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK
            + " HAVING COUNT " + '(' + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + ")" + " = ?";

    // QUERIES TUR
    public static final String QUERY_TUR_INFO = "SELECT * FROM " + TABLE_TUR_INFO
            + " WHERE " + COLUMN_TUR_TURRUTE + " = ?";

    public static final String QUERY_TUR_INFO_ID = "SELECT * FROM " + TABLE_TUR_INFO
            + " WHERE " + COLUMN_TUR_IDTUR + " = ?";


    public static final String QUERY_DELTAGELSE_MAX = "SELECT " + TABLE_MEDLEM + ".*"
            + " FROM " + TABLE_MEDLEM + " INNER JOIN " + TABLE_TURDELTAGELSE + " ON "
            + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + " = "
            + TABLE_MEDLEM + "." + COLUMN_MEDLEM_EPOST + " GROUP BY "
            + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK
            + " HAVING COUNT(" + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + ')'
            + " = (SELECT MAX(mycount) FROM (SELECT "
            + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + ", "
            + "COUNT (" + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + ") mycount"
            + " FROM " + TABLE_TURDELTAGELSE
            + " GROUP BY " + TABLE_TURDELTAGELSE + "." + COLUMN_TURDELTAGELSE_MEDLEMFK + "));";




    // PREPARED STATEMENT -> INSERT MEDLEM..
    public static final String INSERT_MEDLEM = "INSERT INTO " + TABLE_MEDLEM +
            '(' + COLUMN_MEDLEM_EPOST + ", " + COLUMN_MEDLEM_NAVN + ", " + COLUMN_MEDLEM_ADRESSE +
            ", " + COLUMN_MEDLEM_TELEFON + ") VALUES(?, ?, ?, ?)";

    public static final String MEDLEM_CHECK = "SELECT * FROM " + TABLE_MEDLEM +
            " WHERE " + COLUMN_MEDLEM_EPOST + " = ?";


    // PREPARED STATEMENT -> INSERT KONTIGENT
    public static final String INSERT_KONTIGENT = "INSERT INTO " + TABLE_KONTIGENT +
            '(' + COLUMN_KONTIGENT_BETALT_DATO + ", " + COLUMN_KONTIGENT_MEDLEMFK +
            ") VALUES(?, ?)";

    // PREPARED STATEMENT -> INSERT DELTAGELSER
    public static final String INSERT_DELTAGELSE = "INSERT INTO " + TABLE_TURDELTAGELSE +
            '(' + COLUMN_TURDELTAGELSE_IDTUR + ", " + COLUMN_TURDELTAGELSE_MEDLEMFK
            + ") VALUES(?, ?)";

    // INSERT -> TURINFO
    public static final String INSERT_TUR_INFO = "INSERT INTO " + TABLE_TUR_INFO
            + '(' + COLUMN_TUR_TURRUTE + ") VALUES(?)";


    // UPDATES -> MEDLEM
    public static final String UPDATE_MEDLEM = "UPDATE " + TABLE_MEDLEM + " SET "
            + COLUMN_MEDLEM_EPOST  + " = ?, "
            + COLUMN_MEDLEM_NAVN + " = ?, "
            + COLUMN_MEDLEM_ADRESSE + " = ?, "
            + COLUMN_MEDLEM_TELEFON + " = ? WHERE " + COLUMN_MEDLEM_EPOST + " = ?";



    // DELETE -> MEDLEM
    public static final String DELETE_MEDLEM = "DELETE FROM " + TABLE_MEDLEM
            + " WHERE " + COLUMN_MEDLEM_EPOST + " = ?";


    private Connection conn;



    private PreparedStatement queryMedlem;
    private PreparedStatement updateMedlem;
    private PreparedStatement deleteMedlem;
    private PreparedStatement queryMedlemNavn;

    private PreparedStatement queryDeltagelse;
    private PreparedStatement queryDeltagelseAnt;
    private PreparedStatement queryMedlemCheck;

    private PreparedStatement queryKontigent;
    private PreparedStatement queryKontigentAAr;

    private PreparedStatement insertIntoMedlem;
    private PreparedStatement insertIntoKontigent;
    private PreparedStatement insertIntoDeltagelse;

    private PreparedStatement insertIntoTurInfo;
    private PreparedStatement queryTurInfo;
    private PreparedStatement queryTurInfoId;




    private static Datasource instance = new Datasource();

    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }


    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);


            Statement statement = conn.createStatement();
            statement.execute(CREATE_TABLE_MEDLEM);

            statement = conn.createStatement();
            statement.execute(CREATE_TABLE_KONTIGENT);

            statement = conn.createStatement();
            statement.execute(CREATE_TABLE_TUR_INFO);

            statement = conn.createStatement();
            statement.execute(CREATE_TABLE_TURDELTAGELSE);


            // Medlem
            insertIntoMedlem = conn.prepareStatement(INSERT_MEDLEM);
            queryMedlem = conn.prepareStatement(QUERY_ALL_MEDLEM);
            updateMedlem = conn.prepareStatement(UPDATE_MEDLEM);
            queryMedlemCheck = conn.prepareStatement(MEDLEM_CHECK);
            deleteMedlem = conn.prepareStatement(DELETE_MEDLEM);
            queryMedlemNavn = conn.prepareStatement(QUERY_MEDLEM_NAVN);

            // Kontigent
            queryKontigent = conn.prepareStatement(QUERY_KONTIGENT);
            insertIntoKontigent = conn.prepareStatement(INSERT_KONTIGENT);
            insertIntoDeltagelse = conn.prepareStatement(INSERT_DELTAGELSE);

            // Deltagelse
            queryDeltagelse = conn.prepareStatement(QUERY_DELTAGELSE);
            queryDeltagelseAnt = conn.prepareStatement(QUERY_DELTAGELSE_ANT);


            // Tur
            insertIntoTurInfo = conn.prepareStatement(INSERT_TUR_INFO);
            queryTurInfo = conn.prepareStatement(QUERY_TUR_INFO);
            queryTurInfoId = conn.prepareStatement(QUERY_TUR_INFO_ID);


            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to DB " + e.getMessage());
            e.printStackTrace();
            return true;
        }
    }

    public void close() {
        try {

            if (queryMedlem != null) {
                queryMedlem.close();
            }

            if (queryDeltagelse != null) {
                queryDeltagelse.close();
            }

            if (queryKontigent != null) {
                queryKontigent.close();
            }

            if (queryKontigentAAr != null) {
                queryKontigentAAr.close();
            }

            if (insertIntoMedlem != null) {
                insertIntoMedlem.close();
            }

            if (insertIntoKontigent != null) {
                insertIntoKontigent.close();
            }

            if (queryDeltagelseAnt != null) {
                queryDeltagelseAnt.close();
            }

            if (queryTurInfo != null) {
                queryTurInfo.close();
            }

            if (insertIntoTurInfo != null) {
                insertIntoTurInfo.close();
            }


            if (conn != null) {
                conn.close();
            }

        }catch (SQLException e) {
            System.out.println("Couldn't close connection " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void insertIntoTurInfo(String tur) throws SQLException {
        queryTurInfo.setString(1, tur);
        ResultSet results = queryTurInfo.executeQuery();
        if (results.next()) {
            System.out.println("Tur rute finnes fra før..");
            return;

        } else {
            insertIntoTurInfo.setString(1, tur);

            int affectedRows = insertIntoTurInfo.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Feil med INSERT i turinfo");
            }
        }
    }


    public void insertDeltagelse(String epost, int id) throws SQLException{

        if (!medlemCheck(epost)) {
            System.out.println("Finner ikke medlem i DB.. Opprett ny bruker (Deltagelse)");
            return;
        }

        ResultSet result = null;
        queryTurInfoId.setInt(1, id);
        result = queryTurInfoId.executeQuery();

        if (!result.next()) {
            System.out.println("Finner ikke turId..");
            return;
        }

        queryDeltagelse.setString(1, epost);
        queryDeltagelse.setInt(2, id);
        result = queryDeltagelse.executeQuery();
        if (result.next()) {
            System.out.println("Person er allerede påmeldt!");
        } else {

            insertIntoDeltagelse.setInt(1, id);
            insertIntoDeltagelse.setString(2, epost);

            int affectedRows = insertIntoDeltagelse.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Feil med INSERT i deltagelse!");
            }
        }

    }

    public void insertKontigent(Date date, String epost) throws SQLException{
        if (!medlemCheck(epost)) {
            System.out.println("Finner ikke medlem i DB.. Opprett ny bruker");
            return;
        }

        queryKontigent.setString(1, epost);
        queryKontigent.setString(2, String.valueOf(date)); // Sjekker om dato + epost finnes fra før..
        ResultSet result = queryKontigent.executeQuery();
        if (result.next()) {
            System.out.println("Dato + epost finnes fra før..(Kontigent)");
        } else {

            insertIntoKontigent.setString(1, String.valueOf(date));
            insertIntoKontigent.setString(2, epost);

            int affectedRows = insertIntoKontigent.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Kan ikke INSERT i kontigent");
            }

        }


    }

    public List<Medlem> queryDeltagleseAnt(int ant) {
        try {
            queryDeltagelseAnt.setInt(1, ant); // Notat: Første (?) i spørringen
            ResultSet results = queryDeltagelseAnt.executeQuery();

            if (!results.next()) {
                System.out.println("Ingen deltagere med " + ant + " turer");
                return null;
            } else {
                queryDeltagelseAnt.executeQuery();
                List<Medlem> medlemmer = new ArrayList<>();
                while (results.next()) {
                    Medlem medlem = new Medlem();
                    medlem.setName(results.getString(1));
                    medlem.setEpost(results.getString(2));

                    medlemmer.add(medlem);
                }

                return medlemmer;
            }

        } catch (SQLException e) {
            System.out.println("Query failed(deltagelseAnt) " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public List<Medlem> queryDeltagelseMax() {
        StringBuilder sb = new StringBuilder(QUERY_DELTAGELSE_MAX);

        System.out.println(sb.toString());

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(sb.toString())) {

            List<Medlem> medlemmer = new ArrayList<>();
            while (results.next()) {
                Medlem medlem = new Medlem();
                medlem.setName(results.getString(1));
                medlem.setEpost(results.getString(2));

                medlemmer.add(medlem);
            }
            return medlemmer;


        }  catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }



    public List<Medlem> queryKontigentAAr(Date dato, SortOrder sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_KONTIGENT_AAR);
        sb.append(dato);
        sb.append("\"");

        if (sortOrder != SortOrder.NONE) {
            sb.append(QUERY_KONTIGENT_AAR_SORT);
            if (sortOrder == SortOrder.DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        System.out.println("SQL Statement = " + sb.toString());

        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sb.toString())) {

            List<Medlem> medlemmer = new ArrayList<>();

            while (result.next()) {
                Medlem medlem = new Medlem();
                medlem.setName(result.getString(1));
                medlem.setEpost(result.getString(2));

                medlemmer.add(medlem);
            }

            return medlemmer;

        } catch (SQLException e) {
            System.out.println("KontigentÅr Query failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public List<Medlem> queryMedlem(SortOrder sortOrder) {

        StringBuilder sb = new StringBuilder(QUERY_ALL_MEDLEM);
        if (sortOrder != SortOrder.NONE) {
            sb.append(QUERY_ALL_MEDLEM_SORT);
            if (sortOrder == SortOrder.DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sb.toString())){

            List<Medlem> medlemmer = new ArrayList<>();


            while (result.next()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored){}

                Medlem medlem = new Medlem();
                medlem.setEpost(result.getString(INDEX_MEDLEM_EPOST));
                medlem.setName(result.getString(INDEX_MEDLEM_NAVN));
                medlem.setAdresse(result.getString(INDEX_MEDLEM_ADRESSE));
                medlem.setTelefon(result.getString(INDEX_MEDLEM_TELEFON));

                medlemmer.add(medlem);
            }
            return medlemmer;

        }  catch (SQLException e) {
            System.out.println("Query failed " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public void insertMedlem(String navn, String adresse, String telefon, String epost) {

        try {
            if (medlemCheck(epost)) {
                System.out.println("Medlem allerede registert (epost)");
                return;
            }

        } catch (SQLException e) {
            System.out.println("Finnes exception..? " + e.getMessage());
            e.printStackTrace();
        }

        try {

            conn.setAutoCommit(false);

            insertIntoMedlem.setString(1, epost);
            insertIntoMedlem.setString(2, navn);
            insertIntoMedlem.setString(3, adresse);
            insertIntoMedlem.setString(4, telefon);

            int affectedRows = insertIntoMedlem.executeUpdate();
            if (affectedRows == 1) {
                conn.commit();
            } else {
                throw new SQLException("Medlem insert failed!");
            }


        } catch (Exception e) {
            System.out.println("Insert medlem exception: " + e.getMessage());
            try {
                System.out.println("Performing rollback");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("Oh boy! Things are bad.." + e2.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit! " + e.getMessage());
            }
        }

    }

    public boolean updateMedlem(String epost, String nyEpost, String nyNavn, String nyAdresse, String nyTelefon) {
        try {
            updateMedlem.setString(1, nyEpost);
            updateMedlem.setString(2, nyNavn);
            updateMedlem.setString(3, nyAdresse);
            updateMedlem.setString(4, nyTelefon);
            updateMedlem.setString(5, epost);

            int affectedRows = updateMedlem.executeUpdate();

            return affectedRows == 1;

        } catch (SQLException e) {
            System.out.println("UPDATE FAILED (medlem) " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteMedlem(String epost) {
        try {
            deleteMedlem.setString(1, epost);

            int affectedRows = deleteMedlem.executeUpdate();

            return affectedRows == 1;

        } catch (SQLException e) {
            System.out.println("DELETE Failed " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Medlem> queryMedlemNavn(String navn) {
        try {
            queryMedlemNavn.setString(1, navn.trim());
            ResultSet result = queryMedlemNavn.executeQuery();

            List<Medlem> medlemmer = new ArrayList<>();
            while (result.next()) {
                Medlem medlem = new Medlem();
                medlem.setEpost(result.getString(1));
                medlem.setName(result.getString(2));
                medlem.setAdresse(result.getString(3));
                medlem.setTelefon(result.getString(4));

                medlemmer.add(medlem);
            }
            return medlemmer;

        } catch (SQLException e) {
            System.out.println("QUERY epost failed" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }



    private boolean medlemCheck(String epost) throws SQLException {

        queryMedlemCheck.setString(1, epost);
        ResultSet results = queryMedlemCheck.executeQuery();

        if (results.next()) {
            return true;
        } else {
            return false;
        }

    }


}
