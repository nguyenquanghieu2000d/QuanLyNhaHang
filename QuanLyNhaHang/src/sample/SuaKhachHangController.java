package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static sample.DBConnection.SuaKhachHangDB;
import static sample.DBConnection.TaiKhachHangDB;
import static sample.QuanLyKhachHangController.getKhachHang;
import static sample.QuanLyNhanVienController.getNhanVien;

public class SuaKhachHangController implements Initializable {
    @FXML private Label MaKhachHang;
    @FXML private TextField TenKhachHang;
    @FXML private TextField GioiTinh;
    @FXML private TextField DiaChi;
    @FXML private TextField SoDienThoai;
    @FXML private TextField NgaySinh;
    @FXML private Label XacNhan;
    @FXML public void SuaKhachHang(ActionEvent event) throws SQLException {
        XacNhan.setText("Đang xử lý ....");
        if(SuaKhachHangDB(MaKhachHang.getText(),TenKhachHang.getText(),GioiTinh.getText(),DiaChi.getText(),SoDienThoai.getText(),NgaySinh.getText())) XacNhan.setText("Sửa thành công");
        else XacNhan.setText("Sửa thất bại");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KhachHang A = getKhachHang();
        if(A != null) {
            MaKhachHang.setText(A.getMaKhachHang());
            TenKhachHang.setText(A.getTenKhachHang());
            GioiTinh.setText(A.getGioiTinh());
            DiaChi.setText(A.getDiaChi());
            SoDienThoai.setText(A.getSoDienThoai());
            NgaySinh.setText(A.getNgaySinh());
        }
    }
}

