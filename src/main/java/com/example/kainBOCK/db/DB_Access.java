package com.example.kainBOCK.db;

import com.example.kainBOCK.bl.BMICalc;
import com.example.kainBOCK.pojo.bmi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB_Access {

    private static DB_Access theInstance = null;
    private DB_Database db;

    private PreparedStatement insertBMIPrStat = null;
    private final String insertBMIValues = "INSERT INTO bmi (date, user_id, weight, height, value) "
            + "VALUES ( ? , ? , ?, ?, ?);";

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
}
