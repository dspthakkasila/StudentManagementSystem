package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;

public class AdminDAOImpl {

    Connection con =
            DBConnection.getConnection();

    // GET ADMIN EMAIL

    public String getAdminEmail(
            String username){

        String email = null;

        try {

            String query =

            "SELECT email FROM admin " +

            "WHERE username=?";

            PreparedStatement ps =

                    con.prepareStatement(
                            query);

            ps.setString(1, username);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                email =
                rs.getString("email");
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return email;
    }

    // UPDATE PASSWORD

    public boolean updatePassword(

            String username,
            String password){

        boolean status = false;

        try {

            String query =

            "UPDATE admin " +

            "SET password=? " +

            "WHERE username=?";

            PreparedStatement ps =

                    con.prepareStatement(
                            query);

            ps.setString(1, password);

            ps.setString(2, username);

            int rows =
                    ps.executeUpdate();

            if(rows > 0) {

                status = true;
            }

        } catch(Exception e) {

            e.printStackTrace();
        }

        return status;
    }
}