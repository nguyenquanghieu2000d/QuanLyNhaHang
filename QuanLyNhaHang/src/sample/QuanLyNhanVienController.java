package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static sample.DBConnection.*;

public class QuanLyNhanVienController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private TableView<NhanVien> table;
    @FXML private TableColumn<NhanVien,String> MaNhanVien;
    @FXML private TableColumn<NhanVien,String> Username;
    @FXML private TableColumn<NhanVien,String> password;
    @FXML private TableColumn<NhanVien,String> TenNhanVien;
    @FXML private TableColumn<NhanVien,String> GioiTinh;
    @FXML private TableColumn<NhanVien,String> DiaChi;
    @FXML private TableColumn<NhanVien,String> DienThoai;
    @FXML private TableColumn<NhanVien,String> NgaySinh;
    @FXML private TableColumn<NhanVien,String> Tuoi;
    @FXML private TableColumn<NhanVien,String> MaChucVu;
    @FXML private TextField XoaNhanVien;
    @FXML private TextField SuaNhanVien;
    @FXML private Label XoaNhanVienThongBao;
    @FXML private Label ThongBao;
    public static NhanVien NhanVienCanSua;
    public void ThemNhanVien(ActionEvent event) throws IOException {
        Stage window = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ThemNhanVien.fxml"));
        Scene ThemNhanVienForm = new Scene(root);
        window.setScene(ThemNhanVienForm);
        window.show();
    }
    @FXML
    public void SuaNhanVien(ActionEvent event) throws IOException, SQLException {
        NhanVienCanSua = TaiNhanVien(SuaNhanVien.getText());
        if (NhanVienCanSua == null) ThongBao.setText("Mã nhập không hợp lệ");
        else {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SuaNhanVienForm.fxml"));
            Scene SuaNhanVienForm = new Scene(root);
            window.setScene(SuaNhanVienForm);
            window.show();
        }
    }

    @FXML
    public static NhanVien getNhanVien(){
        return NhanVienCanSua;
    }




    @FXML
    public void XoaNhanVien(ActionEvent event) throws SQLException {
        String MaNhanVien = XoaNhanVien.getText();
        if(XoaNhanVienDB(MaNhanVien)) XoaNhanVienThongBao.setText("Xóa thành công");
        else XoaNhanVienThongBao.setText("Xóa thất bại");
    }















    @FXML
    public void QuayLai(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("FormQuanLyChung.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    public void Refresh(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("QuanLyNhanVien.fxml"));
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<NhanVien> list = null;
        try {
            list = XemNhanVienDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MaNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("MaNhanVien"));
        Username.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("Username"));
        password.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("password"));
        TenNhanVien.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("TenNhanVien"));
        GioiTinh.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("GioiTinh"));
        DiaChi.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("DiaChi"));
        DienThoai.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("DienThoai"));
        NgaySinh.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("NgaySinh"));
        Tuoi.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("Tuoi"));
        MaChucVu.setCellValueFactory(new PropertyValueFactory<NhanVien,String>("MaChucVu"));
        table.setItems(list);
    }
}
