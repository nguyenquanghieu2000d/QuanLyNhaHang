package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;

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

    public static boolean ThemNhanVienDB( String Username, String password, String TenNhanVien, String GioiTinh, String DiaChi, String SoDienThoai, String NgaySinh, Character MaChucVu) throws SQLException {
        if (MaChucVu != '1')  if ( MaChucVu != '2') if ( MaChucVu != '3' ) return false;
        System.out.println("Ma chuc vu dung");
        var sql = "INSERT INTO dbo.NhanVien ( MaNhanVien ,Username ,password ,TenNhanVien ,GioiTinh ,DiaChi ,SoDienThoai ,NgaySinh ,MaChucVu) " +
                "VALUES (  dbo.SinhMaTuDong_NhanVien() ,'"  + Username + "','" + password + "','" + TenNhanVien + "','" + GioiTinh +  "','" + DiaChi +  "','" + SoDienThoai +  "','" + NgaySinh +  "','" + MaChucVu + "')";
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }

    public static ObservableList<NhanVien> XemNhanVienDB() throws SQLException {
        var sql = "SELECT * FROM NhanVien";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<NhanVien> hello = FXCollections.observableArrayList();
        while (resultSet.next()){
            String MaNhanVien = resultSet.getString("MaNhanVien");
            String Username = resultSet.getString("Username");
            String password = resultSet.getString("password");
            String TenNhanVien = resultSet.getString("TenNhanVien");
            String GioiTinh = resultSet.getString("GioiTinh");
            String DiaChi = resultSet.getString("DiaChi");
            String SoDienThoai = resultSet.getString("SoDienThoai");
            String NgaySinh = resultSet.getString("NgaySinh");
            String Tuoi = resultSet.getString("Tuoi");
            String MaChucVu = resultSet.getString("MaChucVu");
            System.out.printf("%s%s%s%s%s%s%s%s  %s  %s",MaNhanVien,Username,password,TenNhanVien,GioiTinh,DiaChi,SoDienThoai,MaChucVu, NgaySinh, Tuoi);
            NhanVien e = new NhanVien(MaNhanVien,Username,password,TenNhanVien,GioiTinh,DiaChi,SoDienThoai,NgaySinh,Tuoi,MaChucVu);
            hello.add(e);
        }
        return hello;
    }

    public static boolean XoaNhanVienDB(String MaNhanVien) throws SQLException {
        var sql = "DELETE FROM NhanVien WHERE MaNhanVien = '" + MaNhanVien + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        var sql1 = "SELECT * FROM NhanVien WHERE MaNhanVien = '" + MaNhanVien +"'";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        return !resultSet.next();
    }

    public static NhanVien TaiNhanVien(String maNhanVien) throws SQLException {
        var sql = "SELECT * FROM NhanVien WHERE MaNhanVien = '" + maNhanVien +"'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        else {
            String MaNhanVien = resultSet.getString("MaNhanVien");
            String Username = resultSet.getString("Username");
            String password = resultSet.getString("password");
            String TenNhanVien = resultSet.getString("TenNhanVien");
            String GioiTinh = resultSet.getString("GioiTinh");
            String DiaChi = resultSet.getString("DiaChi");
            String SoDienThoai = resultSet.getString("SoDienThoai");
            String NgaySinh = resultSet.getString("NgaySinh");
            String Tuoi = resultSet.getString("Tuoi");
            String MaChucVu = resultSet.getString("MaChucVu");
            System.out.println(NgaySinh);
            System.out.printf("%s%s%s%s%s%s%s%s\n",MaNhanVien,Username,password,TenNhanVien,GioiTinh,DiaChi,SoDienThoai,MaChucVu);
            return new NhanVien(MaNhanVien,Username,password,TenNhanVien,GioiTinh,DiaChi,SoDienThoai,NgaySinh,Tuoi,MaChucVu);
        }
    }

    public static boolean SuaNhanVienDB(String MaNhanVien, String Username, String password, String TenNhanVien, String GioiTinh, String DiaChi, String SoDienThoai, String NgaySinh, Character MaChucVu) throws SQLException {
        if (MaChucVu != '1')  if ( MaChucVu != '2') if ( MaChucVu != '3' ) return false;
        var sql = "UPDATE dbo.NhanVien SET " +
                "Username = '" + Username + "'," +
                "password = '" + password + "'," +
                "TenNhanVien = '" + TenNhanVien + "'," +
                "GioiTinh = '" + GioiTinh + "'," +
                "DiaChi = '" + DiaChi + "'," +
                "SoDienThoai = '" + SoDienThoai + "'," +
                "NgaySinh = '" + NgaySinh + "'," +
                "MaChucVu = '" + MaChucVu + "'" + "WHERE MaNhanVien = '" + MaNhanVien + "'";
        System.out.println(sql);
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }

    public static ObservableList<KhachHang> XemKhachHangDB() throws SQLException {
        var sql = "SELECT * FROM KhachHang";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<KhachHang> hello = FXCollections.observableArrayList();
        while (resultSet.next()){
            String MaKhachHang = resultSet.getString("MaKhachHang");
            String TenKhachHang = resultSet.getString("TenKhachHang");
            String GioiTinh = resultSet.getString("GioiTinh");
            String DiaChi = resultSet.getString("DiaChi");
            String SoDienThoai = resultSet.getString("SoDienThoai");
            String NgaySinh = resultSet.getString("NgaySinh");
            String Tuoi = resultSet.getString("Tuoi");
            System.out.println(SoDienThoai);
            KhachHang e = new KhachHang(MaKhachHang,TenKhachHang,GioiTinh,DiaChi,SoDienThoai,NgaySinh,Tuoi);
            hello.add(e);
        }
        return hello;
    }



    public static boolean ThemKhachHangDB(String TenKhachHang, String GioiTinh, String DiaChi, String SoDienThoai, String NgaySinh) throws SQLException {
        var sql = "INSERT INTO dbo.KhachHang ( MaKhachHang ,TenKhachHang ,GioiTinh ,DiaChi ,SoDienThoai ,NgaySinh) " +
                "VALUES (  dbo.SinhMaTuDong_KhachHang() ,'" + TenKhachHang + "','" + GioiTinh +  "','" + DiaChi +  "','" + SoDienThoai +  "','" + NgaySinh + "')";
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }


    public static KhachHang TaiKhachHangDB(String maKhachHang) throws SQLException {
        System.out.println("Ok1");
        var sql = "SELECT * FROM KhachHang WHERE MaKhachHang = '" + maKhachHang +"'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Ok2");
        if (!resultSet.next()) return null;
        else {
            System.out.println("OK");
            String MaKhachHang = resultSet.getString("MaKhachHang");
            String TenKhachHang = resultSet.getString("TenKhachHang");
            String GioiTinh = resultSet.getString("GioiTinh");
            String DiaChi = resultSet.getString("DiaChi");
            String SoDienThoai = resultSet.getString("SoDienThoai");
            String NgaySinh = resultSet.getString("NgaySinh");
            String Tuoi = resultSet.getString("Tuoi");
            System.out.println(MaKhachHang);
            return new KhachHang(MaKhachHang,TenKhachHang,GioiTinh,DiaChi,SoDienThoai,NgaySinh,Tuoi);
        }
    }

    public static boolean SuaKhachHangDB(String MaKhachHang, String TenKhachHang, String GioiTinh, String DiaChi, String SoDienThoai, String NgaySinh) throws SQLException {
        var sql = "UPDATE dbo.KhachHang SET " +
                "TenKhachHang = '" + TenKhachHang + "'," +
                "GioiTinh = '" + GioiTinh + "'," +
                "DiaChi = '" + DiaChi + "'," +
                "SoDienThoai = '" + SoDienThoai + "'," +
                "NgaySinh = '" + NgaySinh + "'" + " WHERE MaKhachHang = '" + MaKhachHang + "'";
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }

    public static boolean XoaKhachHangDB(String MaKhachHang) throws SQLException {
        var sql = "DELETE FROM KhachHang WHERE MaKhachHang = '" + MaKhachHang + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        var sql1 = "SELECT * FROM KhachHang WHERE MaKhachHang = '" + MaKhachHang +"'";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        return !resultSet.next();
    }

    public static ObservableList<KhoNguyenLieu> XemKhoNguyenLieuDB() throws SQLException {
        var sql = "SELECT * FROM KhoNguyenLieu";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<KhoNguyenLieu> hello = FXCollections.observableArrayList();
        while (resultSet.next()){
            String MaNguyenLieu = resultSet.getString("MaNguyenLieu");
            String TenNguyenLieu = resultSet.getString("TenNguyenLieu");
            String SoLuong = resultSet.getString("SoLuong");
            String DonViTinh = resultSet.getString("DonViTinh");
            String TinhTrangKhoNguyenLieu = resultSet.getString("TinhTrang");
            KhoNguyenLieu e = new KhoNguyenLieu(MaNguyenLieu,TenNguyenLieu,SoLuong,DonViTinh,TinhTrangKhoNguyenLieu);
            hello.add(e);
        }
        return hello;
    }

    public static boolean ThemNguyenLieuDB(String TenNguyenLieu, String DonViTinh) throws SQLException {
        var sql = "INSERT INTO dbo.KhoNguyenLieu ( MaNguyenLieu ,TenNguyenLieu ,SoLuong ,DonViTinh ,TinhTrang) " +
                "VALUES (  dbo.SinhMaTuDong_KhoNguyenLieu() ,'" + TenNguyenLieu + "','" + "0" +  "','" + DonViTinh +  "','" + "No" +  "')";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }


    public static boolean SuaNguyenLieuDB(String MaNguyenLieu, String TenNguyenLieu, String DonViTinh) throws SQLException {
        var sql = "UPDATE dbo.KhoNguyenLieu SET " +
                "TenNguyenLieu = '" + TenNguyenLieu + "'," +
                "DonViTinh = '" + DonViTinh + "'" +
                " WHERE MaNguyenLieu = '" + MaNguyenLieu + "'";
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        return true;
    }


    public static KhoNguyenLieu TaiNguyenLieuDB(String maNguyenLieu) throws SQLException {
        var sql = "SELECT * FROM KhoNguyenLieu WHERE MaNguyenLieu = '" + maNguyenLieu +"'";
        System.out.println(sql);
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) return null;
        else {
            String MaNguyenLieu = resultSet.getString("MaNguyenLieu");
            String TenNguyenLieu = resultSet.getString("TenNguyenLieu");
            String SoLuong = resultSet.getString("SoLuong");
            String DonViTinh = resultSet.getString("DonViTinh");
            String TinhTrang = resultSet.getString("TinhTrang");
            return new KhoNguyenLieu(MaNguyenLieu,TenNguyenLieu,SoLuong,DonViTinh,TinhTrang);
        }
    }

    public static boolean XoaNguyenLieuDB(String MaNguyenLieu) throws SQLException {
        var sql = "DELETE FROM KhoNguyenLieu WHERE MaNguyenLieu = '" + MaNguyenLieu + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        var sql1 = "SELECT * FROM KhoNguyenLieu WHERE MaNguyenLieu = '" + MaNguyenLieu +"'";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        return !resultSet.next();
    }

    public static ObservableList<MonAn> XemMonAnDB() throws SQLException {
        var sql = "SELECT * FROM MonAn";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<MonAn> hello = FXCollections.observableArrayList();
        while (resultSet.next()){
            String MaMonAn = resultSet.getString("MaMonAn");
            String TenMonAn = resultSet.getString("TenMonAn");
            String DonViTinh = resultSet.getString("DonViTinh");
            String TinhTrang = resultSet.getString("TinhTrang");

            var sql1 = "SELECT TOP 1 DonGia FROM dbo.DonGiaMonAn\n" +
                    "WHERE MaMonAn = '" + MaMonAn + "'\n" +
                    "ORDER BY NgayCapNhat DESC";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            String DonGia = "Không có đơn giá";
            if(resultSet1.next()) DonGia = resultSet1.getString("DonGia");
            MonAn e = new MonAn(MaMonAn,TenMonAn,DonViTinh,TinhTrang,DonGia);
            hello.add(e);
        }
        return hello;
    }

    public static ObservableList<DanhSachNguyenLieuCuaMonAn> XemNguyenLieuCanDB(String MaMonAn) throws SQLException {
        var sql = "SELECT CongThucMonAn.MaNguyenLieu, TenNguyenLieu, CongThucMonAn.SoLuong, DonViTinh, dbo.KhoNguyenLieu.SoLuong, TinhTrang\n" +
                "FROM dbo.CongThucMonAn, dbo.KhoNguyenLieu \n" +
                "WHERE dbo.CongThucMonAn.MaNguyenLieu = dbo.KhoNguyenLieu.MaNguyenLieu\n" +
                "AND dbo.CongThucMonAn.MaMonAn = '" + MaMonAn +"'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ObservableList<DanhSachNguyenLieuCuaMonAn> hello = FXCollections.observableArrayList();
        while (resultSet.next()){
            String MaNguyenLieu = resultSet.getString("MaNguyenLieu");
            String TenNguyenLieu = resultSet.getString("TenNguyenLieu");
            String SoLuongCan = resultSet.getString("SoLuong");
            String DonViTinh = resultSet.getString("DonViTinh");
            String SoLuong = resultSet.getString("SoLuong");
            String TinhTrangKhoNguyenLieu = resultSet.getString("TinhTrang");
            DanhSachNguyenLieuCuaMonAn e = new DanhSachNguyenLieuCuaMonAn(MaNguyenLieu,TenNguyenLieu,SoLuong,DonViTinh,TinhTrangKhoNguyenLieu,SoLuongCan);
            hello.add(e);
            System.out.printf("%s %s %s %s %s %s\n", e.getMaNguyenLieu(),e.getTenNguyenLieu(),e.getSoLuong(),e.getDonViTinh(),e.getSoLuongCan(),e.getTinhTrang());
        }
        return hello;
    }

    public static boolean ThemMonAnDB(String TenMonAn, String DonViTinh, String DonGia, ArrayList<DanhSachNguyenLieuCuaMonAn> temp) throws SQLException {
        var sql = "INSERT INTO dbo.MonAn (MaMonAn, TenMonAn ,DonViTinh) " +
                "VALUES (  dbo.SinhMaTuDong_MonAn() ,'" + TenMonAn +  "','" + DonViTinh + "')";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        var sql1 = "SELECT TOP 1 * FROM dbo.MonAn ORDER BY dbo.MonAn.MaMonAn DESC";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        ResultSet resultSet = preparedStatement1.executeQuery();
        String MaMonAn = null;
        if(resultSet.next()){
            MaMonAn = resultSet.getString("MaMonAn");
        }
        for(int i=0;i<temp.size();i++){
            var sql2 = "INSERT INTO dbo.CongThucMonAn ( MaMonAn, MaNguyenLieu, SoLuong, GiaNhap, ThanhTien)\n" +
                       "VALUES ( '"+ MaMonAn +"', '" + temp.get(i).getMaNguyenLieu() + "', "+ temp.get(i).getSoLuongCan() +", 0.0, 0.0)";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
        }
        var sql3 = "INSERT INTO dbo.DonGiaMonAn (ID, MaMonAn, MaNhanVien, DonGia, NgayCapNhat)\n" +
                "VALUES( dbo.SinhMaTuDong_DonGia() ,N'" + MaMonAn + "', N'NV0001', "+ DonGia +", GETDATE())";
        PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
        preparedStatement3.executeUpdate();
        return true;
    }

    public static boolean SuaMonAnDB(String MaMonAn, String TenMonAn, String DonViTinh, String DonGia, ArrayList<DanhSachNguyenLieuCuaMonAn> temp) throws SQLException {
        var sql = "UPDATE dbo.MonAn\n" +
                "SET TenMonAn = '" + TenMonAn + "',\n" +
                "DonViTinh = '" + DonViTinh + "'\n" +
                "WHERE MaMonAn = '"+ MaMonAn +"'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        var sql1 = "DELETE FROM dbo.CongThucMonAn WHERE MaMonAn = '" + MaMonAn + "'";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        preparedStatement1.executeUpdate();
        for(int i=0;i<temp.size();i++){
            var sql2 = "INSERT INTO dbo.CongThucMonAn ( MaMonAn, MaNguyenLieu, SoLuong, GiaNhap, ThanhTien)\n" +
                    "VALUES ( '"+ MaMonAn +"', '" + temp.get(i).getMaNguyenLieu() + "', "+ temp.get(i).getSoLuongCan() +", 0.0, 0.0)";
            PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
        }
        var sql2 = "\n" +
                "INSERT INTO dbo.DonGiaMonAn (ID, MaMonAn, MaNhanVien, DonGia, NgayCapNhat)\n" +
                "VALUES( dbo.SinhMaTuDong_DonGia() ,N'"+ MaMonAn +"', N'NV0001', "+ DonGia +", GETDATE())";
        PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
        preparedStatement2.executeUpdate();
        return true;
    }


    public static MonAn TaiMonAnDB(String maMonAn) throws SQLException {
        var sql = "SELECT * FROM dbo.MonAn WHERE MaMonAn = '" + maMonAn + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            String MaMonAn = resultSet.getString("MaMonAn");
            String TenMonAn = resultSet.getString("TenMonAn");
            String DonViTinh = resultSet.getString("DonViTinh");
            String TinhTrang = resultSet.getString("TinhTrang");
            var sql1 = "SELECT TOP 1 DonGia, NgayCapNhat FROM dbo.DonGiaMonAn\n" +
                    "WHERE MaMonAn = '"+ maMonAn +"'\n" +
                    "ORDER BY NgayCapNhat DESC";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            String DonGia = null;
            if(resultSet1.next()) DonGia = resultSet1.getString("DonGia");
            return new MonAn(MaMonAn,TenMonAn,DonViTinh,TinhTrang,DonGia);
        }
        return null;
    }

    public static ArrayList<DanhSachNguyenLieuCuaMonAn> TaiNguyenLieuMonAnDB(String maMonAn) throws SQLException {
        var sql = "SELECT CongThucMonAn.MaNguyenLieu, TenNguyenLieu, CongThucMonAn.SoLuong,DonViTinh,TinhTrang\n" +
                "FROM dbo.CongThucMonAn, dbo.KhoNguyenLieu \n" +
                "WHERE dbo.CongThucMonAn.MaNguyenLieu = dbo.KhoNguyenLieu.MaNguyenLieu \n" +
                "AND MaMonAn = '" + maMonAn + "'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<DanhSachNguyenLieuCuaMonAn> hello = new ArrayList<DanhSachNguyenLieuCuaMonAn>();
        while (resultSet.next()){
            String MaNguyenLieu = resultSet.getString("MaNguyenLieu");
            String TenNguyenLieu = resultSet.getString("TenNguyenLieu");
            String SoLuong = resultSet.getString("SoLuong");
            String DonViTinh = resultSet.getString("DonViTinh");
            String TinhTrang = resultSet.getString("TinhTrang");
            System.out.printf("%s %s %s %s %s\n", MaNguyenLieu, TenNguyenLieu, SoLuong, DonViTinh, TinhTrang);
            DanhSachNguyenLieuCuaMonAn e = new DanhSachNguyenLieuCuaMonAn(MaNguyenLieu,TenNguyenLieu,"0",DonViTinh,TinhTrang,SoLuong);
            hello.add(e);
        }
        return hello;
    }

    public static boolean XoaMonAnDB(String MaMonAn) throws SQLException {
        System.out.println("Đã vào chức năng này");
        var sql = "DELETE FROM dbo.CongThucMonAn WHERE MaMonAn = '" + MaMonAn +"'";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
        var sql3 = "DELETE FROM dbo.DonGiaMonAn WHERE MaMonAn = '" + MaMonAn + "'";
        PreparedStatement preparedStatement3 = conn.prepareStatement(sql3);
        preparedStatement3.executeUpdate();
        var sql1 = "DELETE FROM dbo.MonAn WHERE MaMonAn = '" + MaMonAn + "'";
        PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
        preparedStatement1.executeUpdate();
        var sql2 = "SELECT * FROM dbo.MonAn WHERE MaMonAn = '" + MaMonAn + "'";
        PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
        ResultSet resultSet = preparedStatement2.executeQuery();
        return !resultSet.next();
    }
}