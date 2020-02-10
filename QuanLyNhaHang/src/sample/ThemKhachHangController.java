package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static sample.DBConnection.ThemKhachHangDB;

public class ThemKhachHangController {
    @FXML private TextField TenKhachHang;
    @FXML private TextField GioiTinh;
    @FXML private TextField SoDienThoai;
    @FXML private TextField DiaChi;
    @FXML private TextField NgaySinh;
    @FXML private Label XacNhan;


    @FXML public void ThemKhachHang(ActionEvent event) throws SQLException {
        XacNhan.setText("Đang xử lý");
        if(ThemKhachHangDB(TenKhachHang.getText(), GioiTinh.getText(), SoDienThoai.getText(), DiaChi.getText(), NgaySinh.getText())) XacNhan.setText("Thêm thành công");
        else XacNhan.setText("Thêm thất bại");
    }


}
