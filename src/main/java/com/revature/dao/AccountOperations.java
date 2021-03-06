package com.revature.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.dao.interfaces.AccountDAO;
import com.revature.models.Account;

public class AccountOperations implements AccountDAO {

    private Connection conn;

    public AccountOperations(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insertAccount(Account acc) {
        try {
            String sql = "INSERT INTO accounts (attachedusers, joint, balance, pendingstatus) VALUES (?, ?, ?, ?) RETURNING accounts.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            Array preparedArray = conn.createArrayOf("VARCHAR", acc.getAttachedUsernames().toArray());

            stmt.setArray(1, preparedArray);
            stmt.setBoolean(2, acc.isJoint());
            stmt.setDouble(3, acc.getBalance());
            stmt.setBoolean(4, acc.isPending());

            ResultSet res = stmt.executeQuery();
            if(res != null) {
                res.next();
                int id = res.getInt("id");
                res.close();
                stmt.close();
                return id;
            }
            stmt.close();
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Account> fetchAllAccounts() {
        List<Account> result = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM accounts";

            ResultSet res = stmt.executeQuery(sql);

            while(res.next()) {
                int id = res.getInt("id");
                Array fetched = res.getArray("attachedusers");
                String[] attachednames = (String[]) fetched.getArray();
                boolean joint = res.getBoolean("joint");
                boolean pending = res.getBoolean("pendingstatus");
                double balance = res.getDouble("balance");
                Account acc = new Account(joint, balance);
                acc.setId(id);
                acc.setPending(pending);
                acc.setAttachedUsernames(new ArrayList<String>(Arrays.asList(attachednames)));
                result.add(acc);
            }
            res.close();
            stmt.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void updateAccount(Account acc) {
        try {
            
            String sql = "UPDATE accounts SET attachedusers = ?, joint = ?, balance = ?, pendingstatus = ? WHERE id = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            Array preparedArray = conn.createArrayOf("VARCHAR", acc.getAttachedUsernames().toArray());

            stmt.setArray(1, preparedArray);
            stmt.setBoolean(2, acc.isJoint());
            stmt.setDouble(3, acc.getBalance());
            stmt.setBoolean(4, acc.isPending());
            stmt.setInt(5, acc.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(int accId) {
        try {
            String sql = "call delete_account(?)";
            CallableStatement stmt = conn.prepareCall(sql);

            stmt.setInt(1, accId);
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    
}
