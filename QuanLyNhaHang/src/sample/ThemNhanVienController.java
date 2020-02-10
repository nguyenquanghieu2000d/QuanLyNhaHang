package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.DBConnection.ThemNhanVienDB;

public class ThemNhanVienController implements Initializable
{
    @FXML private TextField themtaikhoan;
    @FXML private TextField themmatkhau;
    @FXML private TextField themtennhanvien;
    @FXML private TextField themgioitinh;
    @FXML private TextField themdiachi;
    @FXML private TextField themsodienthoai;
    @FXML private TextField themngaysinh;
    @FXML private ComboBox<String> chonchucvu;
    @FXML private Label xacnhan;
    ObservableList<String> list = FXCollections.observableArrayList("1. Quản lý nhà hàng","2. Quản lý bán hàng","3. Quản lý nhà bếp");




    public void ThemNhanVien(ActionEvent event) throws SQLException {
        String TaiKhoan = themtaikhoan.getText();
        String MatKhau = themmatkhau.getText();
        String HoTen = themtennhanvien.getText();
        String GioiTinh = themgioitinh.getText();
        String DiaChi = themdiachi.getText();
        String SoDienThoai = themsodienthoai.getText();
        String NgaySinh = themngaysinh.getText();
        String a = chonchucvu.getValue();
        Character MaChucVu = a.charAt(0);
        xacnhan.setText("Đang xử lý .......");
        if (ThemNhanVienDB(TaiKhoan,MatKhau,HoTen,GioiTinh,DiaChi,SoDienThoai,NgaySinh,MaChucVu)) {
            xacnhan.setText("Thêm thành công !!!");
        } else xacnhan.setText("Thêm thất bại !!!");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chonchucvu.setItems(list);

    }
}
