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

public class QuanLyKhoNguyenLieuController implements Initializable {
    @FXML private BorderPane mainPane;
    @FXML private TableView<KhoNguyenLieu> table;
    @FXML private TableColumn<KhoNguyenLieu,String> MaNguyenLieu;
    @FXML private TableColumn<KhoNguyenLieu,String> TenNguyenLieu;
    @FXML private TableColumn<KhoNguyenLieu,String> SoLuong;
    @FXML private TableColumn<KhoNguyenLieu,String> DonViTinh;
    @FXML private TableColumn<KhoNguyenLieu,String> TinhTrang;
    @FXML private TextField xoaNguyenLieu;
    @FXML private TextField suaNguyenLieu;
    @FXML private Label XoaNguyenLieuThongBao;
    @FXML private Label ThongBao;
    public static KhoNguyenLieu NguyenLieuCanSua;


    @FXML
    public void QuayLai(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("FormQuanLyChung.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    public void Refresh(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("QuanLyKhoNguyenLieu.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    public void ThemNguyenLieu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ThemNguyenLieu.fxml"));
        Scene view = new Scene(root);
        Stage themKhachHang = new Stage();
        themKhachHang.setScene(view);
        themKhachHang.show();
    }

    @FXML
    public void suaNguyenLieu(ActionEvent event) throws SQLException, IOException {
        NguyenLieuCanSua = TaiNguyenLieuDB(suaNguyenLieu.getText());
        if (NguyenLieuCanSua == null) ThongBao.setText("Mã nhập không hợp lệ");
        else {
            Stage window = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("SuaNguyenLieu.fxml"));
            Scene SuaKhachHangForm = new Scene(root);
            window.setScene(SuaKhachHangForm);
            window.show();
        }
    }


    @FXML
    public static KhoNguyenLieu getNguyenLieu(){
        return NguyenLieuCanSua;
    }

    @FXML
    public void xoaNguyenLieu(ActionEvent event) throws SQLException {
        String MaNguyenLieu = xoaNguyenLieu.getText();
        if(XoaNguyenLieuDB(MaNguyenLieu)) XoaNguyenLieuThongBao.setText("Xóa thành công");
        else XoaNguyenLieuThongBao.setText("Xóa thất bại");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<KhoNguyenLieu> list = null;
        try {
            list = XemKhoNguyenLieuDB();
        } catch (SQLException e) {
            e.printStackTrace();
        };
        MaNguyenLieu.setCellValueFactory(new PropertyValueFactory<KhoNguyenLieu,String>("MaNguyenLieu"));
        TenNguyenLieu.setCellValueFactory(new PropertyValueFactory<KhoNguyenLieu,String>("TenNguyenLieu"));
        SoLuong.setCellValueFactory(new PropertyValueFactory<KhoNguyenLieu,String>("SoLuong"));
        DonViTinh.setCellValueFactory(new PropertyValueFactory<KhoNguyenLieu,String>("DonViTinh"));
        TinhTrang.setCellValueFactory(new PropertyValueFactory<KhoNguyenLieu,String>("TinhTrang"));
        table.setItems(list);
    }
}
