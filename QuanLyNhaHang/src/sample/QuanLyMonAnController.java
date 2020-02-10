package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.DBConnection.*;

public class QuanLyMonAnController implements Initializable {
    @FXML BorderPane mainPane;
    @FXML private TableView<MonAn> table;
    @FXML private TableColumn<MonAn,String> MaMonAn;
    @FXML private TableColumn<MonAn,String> TenMonAn;
    @FXML private TableColumn<MonAn,String> DonViTinh;
    @FXML private TableColumn<MonAn,String> TinhTrang;
    @FXML private TableColumn<MonAn,String> DonGia;
    @FXML private TextField XoaMonAn;
    @FXML private TextField SuaMonAn;
    @FXML private Label XoaMonAnThongBao;
    @FXML private Label ThongBao;
    public static MonAn MonAnCanSua;
    public static ArrayList<DanhSachNguyenLieuCuaMonAn> DanhSachNguyenLieuCanSua;
    @FXML TableView<DanhSachNguyenLieuCuaMonAn> table2;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> MaNguyenLieu;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> TenNguyenLieu;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> DonViTinh_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> TinhTrang_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> SoLuongCan;

    @FXML public void ThemMonAn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ThemMonAn.fxml"));
        Scene view = new Scene(root);
        Stage themMonAn = new Stage();
        themMonAn.setScene(view);
        themMonAn.show();
    }

    @FXML public void suaMonAn(ActionEvent event) throws IOException, SQLException {
        if(SuaMonAn.getText().equals("")) {
            ThongBao.setText("Nhập mã để sửa !!!!!!!!");
        }
        MonAnCanSua = TaiMonAnDB(SuaMonAn.getText());
        if(MonAnCanSua == null) {
            System.out.println("Không tải được món ăn");
            return;
        }
        DanhSachNguyenLieuCanSua = TaiNguyenLieuMonAnDB(SuaMonAn.getText());
        if(DanhSachNguyenLieuCanSua == null){
            System.out.println("Không tải được danh sách món ăn");
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("SuaMonAn.fxml"));
        Scene view = new Scene(root);
        Stage suaMonAn = new Stage();
        suaMonAn.setScene(view);
        suaMonAn.show();
    }


    @FXML public void xoaMonAn(ActionEvent event) throws SQLException {
        if(XoaMonAnDB(XoaMonAn.getText())) XoaMonAnThongBao.setText("Xoá thành công");
        else XoaMonAnThongBao.setText("Xóa thất bại");
    }


    public static MonAn getMonAn(){
        return MonAnCanSua;
    }

    public static ArrayList<DanhSachNguyenLieuCuaMonAn> getNguyenLieuMonAnDB(){
        return DanhSachNguyenLieuCanSua;
    }


    @FXML
    public void QuayLai(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("FormQuanLyChung.fxml"));
        mainPane.setCenter(view);
    }

    @FXML
    public void Refresh(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("QuanLyMonAn.fxml"));
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<MonAn> list = null;

        try {
            list = XemMonAnDB();
        } catch (SQLException e) {
            e.printStackTrace();
        };
        MaMonAn.setCellValueFactory(new PropertyValueFactory<MonAn,String>("MaMonAn"));
        TenMonAn.setCellValueFactory(new PropertyValueFactory<MonAn,String>("TenMonAn"));
        DonViTinh.setCellValueFactory(new PropertyValueFactory<MonAn,String>("DonViTinh"));
        TinhTrang.setCellValueFactory(new PropertyValueFactory<MonAn,String>("TinhTrang"));
        DonGia.setCellValueFactory(new PropertyValueFactory<MonAn,String>("DonGia"));
        table.setItems(list);
        table.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MonAn a;
                a = table.getSelectionModel().getSelectedItem();
                ObservableList<DanhSachNguyenLieuCuaMonAn> list1 = null;
                try {
                    list1 = XemNguyenLieuCanDB(a.getMaMonAn());
                } catch (SQLException e) {
                    e.printStackTrace();
                };
                MaNguyenLieu.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("MaNguyenLieu"));
                TenNguyenLieu.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("TenNguyenLieu"));
                SoLuongCan.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("SoLuongCan"));
                DonViTinh_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("DonViTinh"));
                TinhTrang_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("TinhTrang"));
                table2.setItems(list1);
            }
        });
    }
}
