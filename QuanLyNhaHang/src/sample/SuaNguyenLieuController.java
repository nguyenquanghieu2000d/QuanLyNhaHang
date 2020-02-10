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

import static sample.DBConnection.SuaNguyenLieuDB;
import static sample.DBConnection.SuaNhanVienDB;
import static sample.QuanLyKhoNguyenLieuController.getNguyenLieu;
import static sample.QuanLyNhanVienController.getNhanVien;

public class SuaNguyenLieuController implements Initializable {
    @FXML private Label MaNguyenLieu;
    @FXML private TextField TenNguyenLieu;
    @FXML private TextField DonViTinh;
    @FXML private Label XacNhan;

    public void SuaNguyenLieu(ActionEvent event) throws SQLException {
        String maNguyenLieu = MaNguyenLieu.getText();
        String tenNguyenLieu = TenNguyenLieu.getText();
        String donViTinh = DonViTinh.getText();
        XacNhan.setText("Đang xử lý .......");
        if (SuaNguyenLieuDB(maNguyenLieu,tenNguyenLieu,donViTinh)) {
            XacNhan.setText("Thêm thành công !!!");
        } else XacNhan.setText("Thêm thất bại !!!");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KhoNguyenLieu A = getNguyenLieu();
        if(A != null) {
            MaNguyenLieu.setText(A.getMaNguyenLieu());
            TenNguyenLieu.setText(A.getTenNguyenLieu());
            DonViTinh.setText(A.getDonViTinh());
        }
    }
}
