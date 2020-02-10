package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

import static sample.DBConnection.ThemKhachHangDB;
import static sample.DBConnection.ThemNguyenLieuDB;

public class ThemNguyenLieuController {
    @FXML private TextField TenNguyenLieu;
    @FXML private TextField DonViTinh;
    @FXML private Label XacNhan;




    @FXML public void ThemNguyenLieu(ActionEvent event) throws SQLException {
        XacNhan.setText("Đang xử lý");
        if(ThemNguyenLieuDB(TenNguyenLieu.getText(), DonViTinh.getText())) XacNhan.setText("Thêm thành công");
        else XacNhan.setText("Thêm thất bại");
    }


}
