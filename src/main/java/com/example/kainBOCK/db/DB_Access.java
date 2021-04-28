package com.example.kainBOCK.db;

import com.example.kainBOCK.bl.BMICalc;
import com.example.kainBOCK.pojo.Goal;
import com.example.kainBOCK.pojo.UserAccount;
import com.example.kainBOCK.pojo.bmi;

import java.sql.*;
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

    public static DB_Access getInstance() throws SQLException {
        if (theInstance == null) {
            theInstance = new DB_Access();
        }
        return theInstance;
    }

    private DB_Access() throws SQLException {
        try {
            db = new DB_Database();
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Database problem - possible cause: DB-Driver not found");
        } catch (SQLException ex) {
            throw new RuntimeException("Database problem - possible cause: " + ex.toString());
        }
    }

    public boolean insertBMI(bmi BMI) throws SQLException  {
        if (insertBMIPrStat == null) {
            insertBMIPrStat = db.getConnection().prepareStatement(insertBMIValues);
        }

        insertBMIPrStat.setDate(1, BMI.getBirthdate());
        insertBMIPrStat.setInt(2, BMI.getUser_id());
        insertBMIPrStat.setDouble(3, BMI.getWeight());
        insertBMIPrStat.setDouble(4, BMI.getHeight());
        insertBMIPrStat.setDouble(5, BMI.getValue());
        int numDataSets = insertBMIPrStat.executeUpdate();
        return numDataSets > 0;
    }

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

    public int getPasswordByEmail(String email) throws SQLException {
        String password = "";
        String sql = "SELECT password FROM user_account WHERE email = \'" + email + "\';";
        Statement prep = db.getStatement();
        ResultSet rs = prep.executeQuery(sql);
        while (rs.next()) {
            password = rs.getString("password");
        }
        return password != "" ? Integer.parseInt(password) : -1;
    }

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
}
