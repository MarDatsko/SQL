package service;

import dao.Const;
import model.Account;
import model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    public List<Model> selectAllItem() {
        List<Model> modelList = new ArrayList<>();
        String sql = "SELECT * FROM " + Const.ACCOUNTS_TABLE;
        ResultSet resultSet = getResultSet(sql);
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
        return modelList;
    }


    public void deleteItem(long id) {
        String sql = "DELETE FROM " + Const.ACCOUNTS_TABLE + " WHERE " + Const.ACCOUNTS_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setLong(1, id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void updateItem(Model model, long id) {
        String sql;
        Account account = (Account) model;
        sql = "UPDATE " + Const.ACCOUNTS_TABLE + " SET " + Const.ACCOUNTS_FIRST_NAME + "=?," + Const.ACCOUNTS_LAST_NAME + "=?,"
                + Const.ACCOUNTS_CITY + "=?," + Const.ACCOUNTS_GENDER + "=?," + Const.ACCOUNTS_USERNAME + "=? WHERE " + Const.ACCOUNTS_ID + "=?";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setString(1, account.getFirstName());
            prSt.setString(2, account.getLastName());
            prSt.setString(3, account.getCity());
            prSt.setString(4, account.getGender());
            prSt.setString(5, account.getUserName());
            prSt.setLong(6, id);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void insertItem(Model model) {
        String sql;
        Account account = (Account) model;
        sql = "INSERT INTO " + Const.ACCOUNTS_TABLE + " (" + Const.ACCOUNTS_ID + "," + Const.ACCOUNTS_FIRST_NAME + "," + Const.ACCOUNTS_LAST_NAME + ","
                + Const.ACCOUNTS_CITY + "," + Const.ACCOUNTS_GENDER + "," + Const.ACCOUNTS_USERNAME + ")"
                + " VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(sql);
            prSt.setLong(1, account.getId());
            prSt.setString(2, account.getFirstName());
            prSt.setString(3, account.getLastName());
            prSt.setString(4, account.getCity());
            prSt.setString(5, account.getGender());
            prSt.setString(6, account.getUserName());

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
