package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static sample.DBConnection.*;


public class Controller implements Initializable {


    // LOGIN
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label label1;

    // THEM NHAN VIEN
    @FXML
    private TextField themmanhanvien;
    @FXML
    private TextField themtaikhoan;
    @FXML
    private TextField themmatkhau;
    @FXML
    private TextField themtennhanvien;
    @FXML
    private ComboBox<String> chonchucvu;
    ObservableList<String> list = FXCollections.observableArrayList("1", "2", "3");

    @FXML
    private Label xacnhan;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chonchucvu.setItems(list);
    }

    public void login(ActionEvent event) throws SQLException, IOException {
        DBConnection dbconnect = new DBConnection();
        if(dbconnect.KiemTraDangNhap(username.getText(),password.getText())) {
            label1.setText("Đăng nhập thành công");
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("General.fxml"));
            Scene generalform = new Scene(root);
            window.setScene(generalform);
            window.show();
        }
        else {
            label1.setText("Đăng nhập thất bại");
        }
    }


    public void QuanLyNhanVien(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("QuanLyNhanVien.fxml"));
        Scene QuanLyNhanVienForm = new Scene(root);
        window.setScene(QuanLyNhanVienForm);
        window.show();
    }


    public void ThemNhanVien(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ThemNhanVien.fxml"));
        Scene ThemNhanVienForm = new Scene(root);
        window.setScene(ThemNhanVienForm);
        window.show();
    }
    public void ThemNhanVien1(ActionEvent event) throws SQLException {
        if(ThemNhanVienDB(themmanhanvien.getText(), themtaikhoan.getText(), themmatkhau.getText(), themtennhanvien.getText())){
            xacnhan.setText("Thêm thành công !!!");
        }
        else xacnhan.setText("Thêm thất bại !!!");
    }


}
