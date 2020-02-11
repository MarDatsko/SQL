package dao;

import model.Account;
import model.Model;
import model.Profile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbUtil {
    private final String URL = "jdbc:postgresql://localhost:5432/dev_profiles_db";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "root";
    private final String DRIVER = "org.postgresql.Driver";

    private Connection getDbConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public List<Model> selectAllItem(Model model) {
        ResultSet resultSet;
        List<Model> modelList = new ArrayList<>();
        String sql;
        if (model instanceof Account) {
            sql = "SELECT * FROM " + Const.ACCOUNTS_TABLE;
            resultSet = getResultSet(sql);
            try {
                while (resultSet.next()) {
                    Account account = new Account();
                    account.setId(resultSet.getInt(Const.ACCOUNTS_ID));
                    account.setUserName(resultSet.getString(Const.ACCOUNTS_USERNAME));
                    account.setFirstName(resultSet.getString(Const.ACCOUNTS_FIRST_NAME));
                    account.setLastName(resultSet.getString(Const.ACCOUNTS_LAST_NAME));
                    account.setCity(resultSet.getString(Const.ACCOUNTS_CITY));
                    account.setGender(resultSet.getString(Const.ACCOUNTS_GENDER));

                    modelList.add(account);
                }
            } catch (SQLException t) {
                t.getStackTrace();
            }
        } else {
            sql = "SELECT * FROM " + Const.PROFILES_TABLE;
            resultSet = getResultSet(sql);
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
        }

        return modelList;
    }

    public void deleteItem(Model model, Integer id) {
        String sql;
        if (model instanceof Account) {
            sql = "DELETE FROM " + Const.ACCOUNTS_TABLE + " WHERE " + Const.ACCOUNTS_ID + "=?";
        } else {
            sql = "DELETE FROM " + Const.PROFILES_TABLE + " WHERE " + Const.PROFILES_ID + "=?";
        }

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setInt(1, id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Model model, Integer id) {
        String sql;
        if (model instanceof Account) {
            Account account = (Account) model;
            sql = "UPDATE " + Const.ACCOUNTS_TABLE + " SET " + Const.ACCOUNTS_FIRST_NAME + "=?," + Const.ACCOUNTS_LAST_NAME + "=?,"
                    + Const.ACCOUNTS_CITY + "=?," + Const.ACCOUNTS_GENDER + "=?," + Const.ACCOUNTS_USERNAME + "=? WHERE" + Const.ACCOUNTS_ID + "=?";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(sql);
                prSt.setString(1, account.getFirstName());
                prSt.setString(2, account.getLastName());
                prSt.setString(3, account.getCity());
                prSt.setString(4, account.getGender());
                prSt.setString(5, account.getUserName());
                prSt.setString(6, String.valueOf(id));

                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Profile profile = (Profile) model;
            sql = "UPDATE " + Const.PROFILES_TABLE + " SET " + Const.PROFILES_USERNAME + "=?," + Const.PROFILES_JOB_TITLE + "=?,"
                    + Const.PROFILES_DEPARTMENT + "=?," + Const.PROFILES_COMPANY + "=?," + Const.PROFILES_SKILL + "=? WHERE" + Const.PROFILES_ID + "=?";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(sql);
                prSt.setString(1, profile.getUserName());
                prSt.setString(2, profile.getJobTitle());
                prSt.setString(3, profile.getDepartment());
                prSt.setString(4, profile.getCompany());
                prSt.setString(5, profile.getSkill());
                prSt.setString(6, String.valueOf(id));

                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertItem(Model model) {
        String sql;
        if (model instanceof Account) {
            Account account = (Account) model;
            sql = "INSERT INTO " + Const.ACCOUNTS_TABLE + " (" + Const.ACCOUNTS_ID + "," + Const.ACCOUNTS_FIRST_NAME + "," + Const.ACCOUNTS_LAST_NAME + ","
                    + Const.ACCOUNTS_CITY + "," + Const.ACCOUNTS_GENDER + "," + Const.ACCOUNTS_USERNAME + ")"
                    + " VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(sql);
                prSt.setString(1, String.valueOf(account.getId()));
                prSt.setString(2, account.getFirstName());
                prSt.setString(3, account.getLastName());
                prSt.setString(4, account.getCity());
                prSt.setString(5, account.getGender());
                prSt.setString(6, account.getUserName());

                prSt.executeUpdate();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Profile profile = (Profile) model;
            sql = "INSERT INTO " + Const.PROFILES_TABLE + " (" + Const.PROFILES_ID + "," + Const.PROFILES_USERNAME + "," + Const.PROFILES_JOB_TITLE + ","
                    + Const.PROFILES_DEPARTMENT + "," + Const.PROFILES_COMPANY + "," + Const.PROFILES_SKILL + ")"
                    + " VALUES(?,?,?,?,?,?)";
            try {
                PreparedStatement prSt = getDbConnection().prepareStatement(sql);
                prSt.setString(1, String.valueOf(profile.getId()));
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
