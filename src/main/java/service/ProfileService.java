package service;

import dao.Const;
import model.Model;
import model.Profile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfileService {

    public List<Model> selectAllItem() {
        List<Model> modelList = new ArrayList<>();
        String sql = "SELECT * FROM " + Const.PROFILES_TABLE;
        ResultSet resultSet = getResultSet(sql);
        try {
            while (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getInt(Const.PROFILES_ID));
                profile.setUserName(resultSet.getString(Const.PROFILES_USERNAME));
                profile.setJobTitle(resultSet.getString(Const.PROFILES_JOB_TITLE));
                profile.setDepartment(resultSet.getString(Const.PROFILES_DEPARTMENT));
                profile.setCompany(resultSet.getString(Const.PROFILES_COMPANY));
                profile.setSkill(resultSet.getString(Const.PROFILES_SKILL));

                modelList.add(profile);
            }
        } catch (SQLException t) {
            t.getStackTrace();
        }

        return modelList;
    }

    public void deleteItem(long id) {
        String sql = "DELETE FROM " + Const.PROFILES_TABLE + " WHERE " + Const.PROFILES_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setLong(1, id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Model model, long id) {
        Profile profile = (Profile) model;
        String sql = "UPDATE " + Const.PROFILES_TABLE + " SET " + Const.PROFILES_USERNAME + "=?," + Const.PROFILES_JOB_TITLE + "=?,"
                + Const.PROFILES_DEPARTMENT + "=?," + Const.PROFILES_COMPANY + "=?," + Const.PROFILES_SKILL + "=? WHERE " + Const.PROFILES_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, profile.getUserName());
            prSt.setString(2, profile.getJobTitle());
            prSt.setString(3, profile.getDepartment());
            prSt.setString(4, profile.getCompany());
            prSt.setString(5, profile.getSkill());
            prSt.setLong(6, id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void insertItem(Model model) {
        Profile profile = (Profile) model;
        String sql = "INSERT INTO " + Const.PROFILES_TABLE + " (" + Const.PROFILES_ID + "," + Const.PROFILES_USERNAME + "," + Const.PROFILES_JOB_TITLE + ","
                + Const.PROFILES_DEPARTMENT + "," + Const.PROFILES_COMPANY + "," + Const.PROFILES_SKILL + ")"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setLong(1, profile.getId());
            prSt.setString(2, profile.getUserName());
            prSt.setString(3, profile.getJobTitle());
            prSt.setString(4, profile.getDepartment());
            prSt.setString(5, profile.getCompany());
            prSt.setString(6, profile.getSkill());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String DRIVER = "org.postgresql.Driver";
        String PASSWORD = "root";
        String USERNAME = "postgres";
        String URL = "jdbc:postgresql://localhost:5432/dev_profiles_db";
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    private ResultSet getResultSet(String sql) {
        ResultSet resultSet = null;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            resultSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
