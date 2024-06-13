/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Category;
import model.Course;

public class DAO extends DBContext {

    public Account checkLogin(String email, String pass) {
        String sql = "SELECT [uID]\n"
                + "      ,[user]\n"
                + "      ,[pass]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[role]\n"
                + "      ,[isAdmin]\n"
                + "      ,[avatar]\n"
                + "  FROM [projectSWP].[dbo].[Account]"
                + " where [email] = ? and [pass] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        (rs.getString(8) == null) ? "" : rs.getString(8));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void SignUp(String user, String email, String pass) {
        try {
            String sql = "INSERT INTO [dbo].[Account]\n"
                    + "           ([user]\n"
                    + "           ,[email]\n"
                    + "           ,[pass]\n"
                    + "           ,[role]\n"
                    + "           ,[isAdmin])\n"
                    + "           VALUES(?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, email);
            st.setString(3, pass);
            st.setString(4, "5");
            st.setString(5, "0");
            // vcl chiu luon
            st.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public boolean checkEmailExist(String email) {
        String sql = "SELECT [uID]\n"
                + "      ,[user]\n"
                + "      ,[pass]\n"
                + "      ,[fullname]\n"
                + "      ,[email]\n"
                + "      ,[role]\n"
                + "      ,[isAdmin]\n"
                + "      ,[avatar]\n"
                + "  FROM [projectSWP].[dbo].[Account]"
                + " where [email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void getNewPass(String email, String pass) {
        String sql = "UPDATE [dbo].[Account]\n"
                + "   SET [pass] = ?\n"
                + " WHERE [email] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, email);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT  [id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[category_id]\n"
                + "      ,[image]\n"
                + "      ,[title]\n"
                + "  FROM [projectSWP].[dbo].[Course]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Category> getAllCaregories() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT [cid]\n"
                + "      ,[cname]\n"
                + "  FROM [projectSWP].[dbo].[Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Course> getCourseByCid(int cid) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[category_id]\n"
                + "      ,[image]\n"
                + "      ,[title]\n"
                + "  FROM [projectSWP].[dbo].[Course]"
                + "where 1=1 ";
        if (cid != 0) {
            sql += " and category_id = " + cid;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Course> searchByCheck(int[] cid) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[category_id]\n"
                + "      ,[image]\n"
                + "      ,[title]\n"
                + "  FROM [projectSWP].[dbo].[Course]"
                + "where 1=1 ";
        if (cid != null && cid[0] != 0) {
            sql += " and category_id in(";
            for (int i = 0; i < cid.length; i++) {
                sql += cid[i] + ",";
            }
            if (sql.endsWith(",")) {
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += ")";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public List<Course> getListCourseSearch(String txtSearch) {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "       ,[category_id]"
                + "      ,[image]\n"
                + "      ,[title]\n"
                + "  FROM [projectSWP].[dbo].[Course] where [name] like ? or [title] like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + txtSearch + "%");
            st.setString(2, "%" + txtSearch + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public int getNumberPage() {
        String sql = "SELECT COUNT(*)\n"
                + "  FROM [projectSWP].[dbo].[Course]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int total = rs.getInt(1);
                int countPage = 0;
                countPage = total / 6;
                if (total % 6 != 0) {
                    countPage++;
                }
                return countPage;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public Course getNewCourse() {
        String sql = "Select top 1 * from Course\n"
                + "order by id desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    public List<Course> getPaging(int index){
        String sql = "SELECT * FROM [dbo].[Course]\n"
                + "order by id\n"
                + "OFFSET ? ROWS\n"
                + "FETCH FIRST 6 ROWS ONLY";
        List<Course> list = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                list.add(new Course(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
            return list;
        }catch(SQLException ex){
            System.out.println(ex);
        }
        return null;
    }
}
