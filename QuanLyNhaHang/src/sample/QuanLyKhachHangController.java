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

public class QuanLyKhachHangController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private TableView<KhachHang> table;
    @FXML private TableColumn<KhachHang,String> MaKhachHang;
    @FXML private TableColumn<KhachHang,String> TenKhachHang;
    @FXML private TableColumn<KhachHang,String> GioiTinh;
    @FXML private TableColumn<KhachHang,String> DiaChi;
    @FXML private TableColumn<KhachHang,String> SoDienThoai;
    @FXML private TableColumn<KhachHang,String> NgaySinh;
    @FXML private TableColumn<KhachHang,String> Tuoi;
    @FXML private TextField XoaKhachHang;
    @FXML private TextField SuaKhachHang;
    @FXML private Label XoaKhachHangThongBao;
    @FXML private Label ThongBao;
    public static KhachHang KhachHangCanSua;


    @FXML
    public void QuayLai(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("FormQuanLyChung.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    public void Refresh(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("QuanLyKhachHang.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    public void ThemKhachHang(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ThemKhachHang.fxml"));
        Scene view = new Scene(root);
        Stage themKhachHang = new Stage();
        themKhachHang.setScene(view);
        themKhachHang.show();
    }

    @FXML
    public void suaKhachHang(ActionEvent event) throws SQLException, IOException {
        System.out.println("Ok123"
        );
        KhachHangCanSua = TaiKhachHangDB(SuaKhachHang.getText());
        if (KhachHangCanSua == null) ThongBao.setText("Mã nhập không hợp lệ");
        else {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SuaKhachHang.fxml"));
            Scene SuaKhachHangForm = new Scene(root);
            window.setScene(SuaKhachHangForm);
            window.show();
        }
    }


    @FXML
    public static KhachHang getKhachHang(){
        return KhachHangCanSua;
    }

    @FXML
    public void xoaKhachHang(ActionEvent event) throws SQLException {
        String MaKhachHang = XoaKhachHang.getText();
        if(XoaKhachHangDB(MaKhachHang)) XoaKhachHangThongBao.setText("Xóa thành công");
        else XoaKhachHangThongBao.setText("Xóa thất bại");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<KhachHang> list = null;
        try {
            list = XemKhachHangDB();
        } catch (SQLException e) {
            e.printStackTrace();
        };
        MaKhachHang.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("MaKhachHang"));
        TenKhachHang.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("TenKhachHang"));
        GioiTinh.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("GioiTinh"));
        DiaChi.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("DiaChi"));
        SoDienThoai.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("SoDienThoai"));
        NgaySinh.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("NgaySinh"));
        Tuoi.setCellValueFactory(new PropertyValueFactory<KhachHang,String>("Tuoi"));
        table.setItems(list);
    }
}
