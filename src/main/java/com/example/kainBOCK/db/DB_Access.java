package com.example.kainBOCK.db;

import com.example.kainBOCK.pojo.*;

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

    private PreparedStatement deleteTimeStampPrStat = null;
    private final String deleteTimeStampString = "DELETE FROM public.\"timestamp\"" +
            "WHERE id = ?;";

    private PreparedStatement getVideoListPrStat = null;
    private final String getVideoListString = "SELECT * FROM video WHERE goal_id = ?";

    private PreparedStatement getGoalForUserPrStat = null;
    private final String getGoalForUserString = "SELECT goal_id FROM user_account WHERE user_id = ?";

    private PreparedStatement changeGoal = null;
    private final String changeGoalStr = "UPDATE public.user_account\n" +
            "SET goal_id=?\n" +
            "WHERE user_id = ?;";

    private PreparedStatement changePasswordPrStat = null;
    private final String changePasswordStr = "UPDATE public.user_account\n" +
            "SET password = ?\n" +
            "WHERE user_id = ?;";
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

        insertBMIPrStat.setTimestamp(1, Timestamp.valueOf(BMI.getDate()));
        insertBMIPrStat.setInt(2, BMI.getUser_id());
        insertBMIPrStat.setDouble(3, BMI.getWeight());
        insertBMIPrStat.setInt(4, BMI.getHeight());
        insertBMIPrStat.setDouble(5, BMI.getValue());
        int numDataSets = insertBMIPrStat.executeUpdate();
        return numDataSets > 0;
    }

    /**
     * inserts User into DB after Registration
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
     * Returns the password to check if input password for login is correct
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
     * Returns a list of all goals available in the DB
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
     * Returns the UserID with the email
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
     * returns the Age (in years) of an User
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
     * returns a list of all timestamps for a UserID
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
            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(rs.getTimestamp("date").getTime()), ZoneOffset.UTC);
            timestamps.add(new TimeStamp(id, description, date));
        }
        return timestamps;
    }

    /**
     * Creates a Timsetamp in DB
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
        createTimeStampPrStat.setTimestamp(3, Timestamp.valueOf(date.plusHours(2)));
        createTimeStampPrStat.setString(2, description);
        int numDataSets = createTimeStampPrStat.executeUpdate();
        return numDataSets > 0;
    }

    /**
     * Deletes a timestamp in DB
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public boolean deleteTimeStamp(int id) throws SQLException {
        if (deleteTimeStampPrStat == null) {
            deleteTimeStampPrStat = db.getConnection().prepareStatement(deleteTimeStampString);
        }
        deleteTimeStampPrStat.setInt(1, id);
        int numDataSets = deleteTimeStampPrStat.executeUpdate();
        return numDataSets > 0;
    }

    /**
     * returns a list of all videos with the specific goal of the User
     *
     * @param goalID
     * @return
     * @throws SQLException
     */
    public List<Video> getVideos(int goalID) throws SQLException {
        List<Video> videos = new ArrayList<>();
        if (getVideoListPrStat == null) {
            getVideoListPrStat = db.getConnection().prepareStatement(getVideoListString);
        }
        getVideoListPrStat.setInt(1, goalID);
        ResultSet rs = getVideoListPrStat.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String link = rs.getString("link");
            String name = rs.getString("name");
            videos.add(new Video(id, name, link));
        }
        return videos;
    }

    /**
     * returns the ID of the selected goal
     *
     * @param userID
     * @return
     * @throws SQLException
     */
    public int getGoalIdForUser(int userID) throws SQLException {
        int goalID = -1;
        if (getGoalForUserPrStat == null) {
            getGoalForUserPrStat = db.getConnection().prepareStatement(getGoalForUserString);
        }
        getGoalForUserPrStat.setInt(1, userID);
        ResultSet rs = getGoalForUserPrStat.executeQuery();
        while (rs.next()) {
            goalID = rs.getInt("goal_id");
        }
        return goalID;
    }

    /**
     * Update the Goal of a specific User in DB
     *
     * @param userID
     * @param goalID
     * @return
     * @throws SQLException
     */
    public boolean changeGoal(int userID, int goalID) throws SQLException {
        if (changeGoal == null) {
            changeGoal = db.getConnection().prepareStatement(changeGoalStr);
        }
        changeGoal.setInt(1, goalID);
        changeGoal.setInt(2,userID);
        int numDataSets = changeGoal.executeUpdate();
        return numDataSets > 0;
    }

    /**
     * Update Password of Specific user in DB
     *
     * @param userID
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean changePassword(int userID, String password) throws SQLException {
        if (changePasswordPrStat == null) {
            changePasswordPrStat = db.getConnection().prepareStatement(changePasswordStr);
        }
        changePasswordPrStat.setInt(1, password.hashCode());
        changePasswordPrStat.setInt(2,userID);
        int numDataSets = changePasswordPrStat.executeUpdate();
        return numDataSets > 0;
    }
}
