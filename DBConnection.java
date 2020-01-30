package sample;

import java.sql.*;

public class DBConnection {
    private static String url;
    private static Connection conn;

    DBConnection(){
        try {
            this.url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaHang;user=sa;password=quanghieu12345";
            this.conn = DriverManager.getConnection(url);
            if(conn != null){
                System.out.println("Connected!!!");
            }
        } catch (SQLException e) {
            System.out.println("Can't connect to server");
        }
    }

    public static boolean KiemTraDangNhap(String Username, String Password){
        int count = 0;
        var sql = "EXEC KiemTraDangNhap '" + Username + "','" + Password + "'";
        try{
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) count++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count == 1;
    }

    public static boolean ThemNhanVienDB(String MaNhanVien, String Username, String password, String TenNhanVien) throws SQLException {
        var sql1 = "SELECT * FROM NhanVien WHERE MaNhanVien = '" + MaNhanVien + "'";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet rs1 = preparedStatement1.executeQuery();
        if(rs1.next()) return false;
        var sql = "INSERT INTO NhanVien(MaNhanVien, Username, password, TenNhanVien)" +
                "VALUES ('" + MaNhanVien + "','" + Username + "','" + password + "','" + TenNhanVien + "')";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }

}
