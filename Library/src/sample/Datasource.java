package sample;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Datasource {

    //    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\databases\\" + DB_NAME;
    public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\Databases\\Library.db";
    public static final String TABLE_NAME_BOOKS = "Books";
    public static final String COLUMN_BOOK_TITLE = "BookTitle";

    public static final String TABLE_NAME_EMPLOYEES = "Employees";
    public static final String COLUMN_EMP_FIRST_NAME = "firstName";
    public static final String COLUMN_EMP_LAST_NAME = "lastName";
    public static final String COLUMN_EMP_ID = "employeeId";
    public static final String COLUMN_EMP_USERNAME = "userName";
    public static final String COLUMN_EMP_PASSWORD = "Password";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_DESC = 3;
    public static final int ORDER_BY_ASC = 2;

    public static final int INDEX_BOOK_ID = 1;
    public static final int INDEX_BOOK_TITLE = 2;
    public static final int INDEX_AUTHOR_ID = 3;


    private Connection conn;

    private PreparedStatement queryBookId;

    private PreparedStatement insertIntoArtists;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;

    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;


    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);

            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }

    public List<Books> queryBooks(int sortOrder) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_NAME_BOOKS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_BOOK_TITLE);
            sb.append(" COLLATE NOCASE ");
            //System.out.println(sb.toString());
            if (sortOrder == ORDER_BY_DESC) {
                sb.append("DESC");
            } else {
                sb.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {

            List<Books> books = new ArrayList<>();
            while (results.next()) {
                Books bookObject = new Books();
                bookObject.setBookId(results.getInt(INDEX_BOOK_ID));
                bookObject.setBookTitle(results.getString(INDEX_BOOK_TITLE));
                bookObject.setAuthorId(results.getInt(INDEX_AUTHOR_ID));
                books.add(bookObject);

            }

            return books;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Books> queryBookTitle(String bookTitle) {

        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_NAME_BOOKS);
        sb.append("WHERE");
        sb.append(COLUMN_BOOK_TITLE);
        sb.append(" = ?");

        System.out.println("SQL statement = " + sb.toString());

        try {
            PreparedStatement statement = conn.prepareStatement(sb.toString());
            ResultSet results;
            statement.setString(1, bookTitle);
            results = statement.executeQuery();
            List<Books> books = new ArrayList<>();
            while (results.next()) {
                Books bookObject = new Books();
                bookObject.setBookId(results.getInt(1));
                bookObject.setBookTitle(results.getString(2));
                bookObject.setAuthorId(results.getInt(3));
                books.add(bookObject);

            }
            System.out.println("Author :" + results.getInt(3));

            return books;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ResultSet returnResultSet() {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_NAME_BOOKS);
        sb.append(" ORDER BY ");
        sb.append(COLUMN_BOOK_TITLE);
        sb.append(" COLLATE NOCASE ");
        //System.out.println(sb.toString());
        sb.append("ASC");

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(sb.toString())) {
            System.out.println(results.getString(2));
            return results;
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Users> queryUserInfo(String username) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_NAME_EMPLOYEES);
        sb.append("WHERE");
        sb.append(COLUMN_EMP_USERNAME);
        sb.append(" = ?");

        System.out.println("SQL statement = " + sb.toString());

        try {
            PreparedStatement statement = conn.prepareStatement(sb.toString());
            ResultSet results;
            statement.setString(1, username);
            results = statement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (results.next()) {
                Users userObject = new Users();
                userObject.setUsername(results.getString(1));
                userObject.setPassword(results.getString(2));
                userObject.setFirstName(results.getString(3));
                users.add(userObject);
            }
            return users;

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public String queryUserPassword(String username) {
        StringBuilder sb = new StringBuilder("SELECT password FROM ");
        sb.append(TABLE_NAME_EMPLOYEES);
        sb.append("WHERE");
        sb.append(COLUMN_EMP_USERNAME);
        sb.append(" = ?");

        System.out.println("SQL statement = " + sb.toString());

        try {
            PreparedStatement statement = conn.prepareStatement(sb.toString());
            ResultSet results;
            statement.setString(1, username);
            results = statement.executeQuery();
            if (results != null) {
                results = statement.executeQuery();
                return results.getString(1);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }

    }
}

