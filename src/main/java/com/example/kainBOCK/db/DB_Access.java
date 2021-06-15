package com.example.kainBOCK.db;

import com.example.kainBOCK.bl.BMICalc;
import com.example.kainBOCK.pojo.Goal;
import com.example.kainBOCK.pojo.TimeStamp;
import com.example.kainBOCK.pojo.UserAccount;
import com.example.kainBOCK.pojo.bmi;

import java.sql.*;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Access {

    private static DB_Access theInstance = null;
    private DB_Database db;

    private PreparedStatement insertBMIPrStat = null;
    private final String insertBMIValues = "INSERT INTO bmi (date, user_id, weight, height, value) "
            + "VALUES ( ? , ? , ?, ?, ?);";

    private PreparedStatement insertUserPrStat = null;
    private final String insertUserString = "INSERT INTO user_account (name, password, email, gender_id, goal_id, date_of_birth) "
            + "VALUES ( ? , ? , ?, ?, ?, ?);";

    private PreparedStatement createTimeStampPrStat = null;
    private final String createTimeStampString = "INSERT INTO timestamp (user_id, description, date) "
            + "VALUES ( ? , ? , ?);";

    private PreparedStatement getPwByMailPrStat = null;
    private final String getPwByMailString = "SELECT password FROM user_account WHERE email = ?;";

    /**
     * Returns the current Instance
     *
     * @return
     * @throws SQLException
     */
    public static DB_Access getInstance() throws SQLException {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }

    /**
     * Singelton for DB Connection
     *
     * @throws SQLException
     */
    private DB_Access() throws SQLException {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Database problem - possible cause: "+ex.toString());
        } catch (SQLException ex) {
            throw new RuntimeException("Database problem - possible cause: " + ex.toString());
        }
    }

    /**
     * Saves the BMI values in the DB
     *
     * @param BMI
     * @return
     * @throws SQLException
     */
    public boolean insertBMI(bmi BMI) throws SQLException {
        if (insertBMIPrStat == null) {
            insertBMIPrStat = db.getConnection().prepareStatement(insertBMIValues);
        }

        insertBMIPrStat.setDate(1, Date.valueOf(BMI.getDate()));
        insertBMIPrStat.setInt(2, BMI.getUser_id());
        insertBMIPrStat.setDouble(3, BMI.getWeight());
        insertBMIPrStat.setInt(4, BMI.getHeight());
        insertBMIPrStat.setDouble(5, BMI.getValue());
        int numDataSets = insertBMIPrStat.executeUpdate();
        return numDataSets > 0;
    }

    /**
     * Registration of new user
     *
     * @param user
     * @return
     * @throws SQLException
     */
    public boolean insertUser(UserAccount user) throws SQLException {
        if (insertUserPrStat == null) {
            insertUserPrStat = db.getConnection().prepareStatement(insertUserString);
        }

        insertUserPrStat.setString(1, user.getName());
        insertUserPrStat.setInt(2, user.getPassword().hashCode());
        insertUserPrStat.setString(3, user.getEmail());
        insertUserPrStat.setInt(4, user.getGenderID());
        insertUserPrStat.setInt(5, user.getGoalID());
        insertUserPrStat.setDate(6, Date.valueOf(user.getDateOfBirth()));
        int numDataSets = insertUserPrStat.executeUpdate();
        return numDataSets > 0;
    }

    /**
     * Gets the password to check if input password for login is correct
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public int getPasswordByEmail(String email) throws SQLException {
        String password = "";
        if (getPwByMailPrStat == null) {
            getPwByMailPrStat = db.getConnection().prepareStatement(getPwByMailString);
        }
        getPwByMailPrStat.setString(1, email);
        ResultSet rs = getPwByMailPrStat.executeQuery();
        while (rs.next()) {
            password = rs.getString("password");
        }
        return password != "" ? Integer.parseInt(password) : -1;
    }

    /**
     * Gets all goals from DB
     *
     * @return
     * @throws SQLException
     */
    public List<Goal> getAllGoals() throws SQLException {
        List goals = new ArrayList<>();
        String sql = "SELECT * FROM goal;";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("goal_id");
            String name = rs.getString("name");
            goals.add(new Goal(id, name));
        }
        return goals;
    }

    /**
     *
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public int getUserIDByEmail(String email) throws SQLException {
        int userID = 0;
        String sql = "SELECT user_id FROM user_account WHERE email = \'" + email + "\';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            userID = rs.getInt("user_id");
        }
        return userID;
    }

    /**
     *
     * @param email
     * @return
     * @throws SQLException
     */
    public int getAgeOfUser(String email) throws SQLException {
        LocalDate birthdate = null;
        String sql = "SELECT date_of_birth FROM user_account WHERE email = \'" + email + "\';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            birthdate = rs.getDate("date_of_birth").toLocalDate();
        }
        return birthdate == null ? -1 : Period.between(birthdate, LocalDate.now()).getYears();
    }

    /**
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    public List<TimeStamp> getTimeStampsForUser(int userID) throws SQLException {
        List<TimeStamp> timestamps = new ArrayList<>();
        String sql = "SELECT * FROM timestamp WHERE user_id = " + userID + ";";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(rs.getDate("date").getTime()), ZoneOffset.UTC) ;
            timestamps.add(new TimeStamp(id, description, date));
        }
        return timestamps;
    }

    /**
     *
     * @param userID
     * @param description
     * @param date
     * @return
     * @throws SQLException
     */
    public boolean createTimeStamp(int userID, String description, LocalDateTime date) throws SQLException {
        if (createTimeStampPrStat == null) {
            createTimeStampPrStat = db.getConnection().prepareStatement(createTimeStampString);
        }
        createTimeStampPrStat.setInt(1, userID);
        createTimeStampPrStat.setDate(3, (Date) Date.from(date.toInstant(ZoneOffset.UTC)));
        createTimeStampPrStat.setString(2, description);
        int numDataSets = createTimeStampPrStat.executeUpdate();
        return numDataSets > 0;
    }
}
