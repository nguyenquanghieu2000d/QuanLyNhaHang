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
import static sample.DBConnection.SuaNhanVienDB;
import static sample.QuanLyNhanVienController.getNhanVien;

public class SuaNhanVienController implements Initializable {
    @FXML private Label MaNhanVien;
    @FXML private TextField Username;
    @FXML private TextField password;
    @FXML private TextField TenNhanVien;
    @FXML private TextField GioiTinh;
    @FXML private TextField DiaChi;
    @FXML private TextField SoDienThoai;
    @FXML private TextField NgaySinh;
    @FXML private ComboBox<String> MaChucVu;
    @FXML private Label chucVu;
    @FXML private Label XacNhan;

    ObservableList<String> list = FXCollections.observableArrayList("1. Quản lý nhà hàng","2. Quản lý bán hàng","3. Quản lý nhà bếp");

    public void SuaNhanVien(ActionEvent event) throws SQLException {
        String maNhanVien = MaNhanVien.getText();
        String taiKhoan = Username.getText();
        String matKhau = password.getText();
        String tenNhanVien = TenNhanVien.getText();
        String gioiTinh = GioiTinh.getText();
        String diaChi = DiaChi.getText();
        String soDienThoai = SoDienThoai.getText();
        String ngaySinh = NgaySinh.getText();
        String a = MaChucVu.getValue();
        Character maChucVu = a.charAt(0);
        System.out.println(maNhanVien);
        System.out.println(taiKhoan);
        System.out.println(maChucVu);
        XacNhan.setText("Đang xử lý .......");
        if (SuaNhanVienDB(maNhanVien,taiKhoan,matKhau,tenNhanVien,gioiTinh,diaChi,soDienThoai,ngaySinh,maChucVu)) {
            XacNhan.setText("Thêm thành công !!!");
        } else XacNhan.setText("Thêm thất bại !!!");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MaChucVu.setItems(list);
        NhanVien A = getNhanVien();
        if(A != null) {
            MaNhanVien.setText(A.getMaNhanVien());
            Username.setText(A.getUsername());
            password.setText(A.getPassword());
            TenNhanVien.setText(A.getTenNhanVien());
            GioiTinh.setText(A.getGioiTinh());
            DiaChi.setText(A.getDiaChi());
            SoDienThoai.setText(A.getDienThoai());
            NgaySinh.setText(A.getNgaySinh());
            if (A.getMaChucVu().equals("1")) MaChucVu.getSelectionModel().select("1. Quản lý nhà hàng");
            else if (A.getMaChucVu().equals("2")) MaChucVu.getSelectionModel().select("2. Quản lý bán hàng");
            else if (A.getMaChucVu().equals("3")) MaChucVu.getSelectionModel().select("3. Quản lý bán hàng");
            else chucVu.setText("Chưa xác định");
        }
    }
}
