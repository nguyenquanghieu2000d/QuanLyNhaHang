package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.DBConnection.*;

public class ThemMonAnController implements Initializable {
    @FXML private TextField TextTenMonAn;
    @FXML private TextField TextDonViTinh;
    @FXML private TextField TextDonGia;
    @FXML private TableView<KhoNguyenLieu> table;
    @FXML private TableColumn<KhoNguyenLieu,String> MaNguyenLieu;
    @FXML private TableColumn<KhoNguyenLieu,String> TenNguyenLieu;
    @FXML private TableColumn<KhoNguyenLieu,String> SoLuong;
    @FXML private TableColumn<KhoNguyenLieu,String> DonViTinh;
    @FXML private TableColumn<KhoNguyenLieu,String> TinhTrang;
    @FXML private Label XacNhan;

    @FXML private TableView<DanhSachNguyenLieuCuaMonAn> table_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> MaNguyenLieu_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> TenNguyenLieu_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> SoLuong_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> DonViTinh_;
    @FXML private TableColumn<DanhSachNguyenLieuCuaMonAn,String> TinhTrang_;
    ObservableList<DanhSachNguyenLieuCuaMonAn> list2 = FXCollections.observableArrayList();
    ArrayList<DanhSachNguyenLieuCuaMonAn> temp = new ArrayList<>();
    @FXML public void ThemMonAn(ActionEvent event) throws SQLException {
        XacNhan.setText("Đang xử lý");
        if (temp.isEmpty()) {
            XacNhan.setText("Hãy thêm nguyên liệu vào");
            return;
        }
        if(ThemMonAnDB(TextTenMonAn.getText(), TextDonViTinh.getText(),TextDonGia.getText(), temp)) XacNhan.setText("Thêm thành công");
        else XacNhan.setText("Thêm thất bại");


    }



    @FXML public void ThemNguyenLieu(ActionEvent event) throws IOException {
        KhoNguyenLieu a = table.getSelectionModel().getSelectedItem();
        if(temp.size() != 0){
            for(int i=0;i<temp.size();i++){
                if(a.getMaNguyenLieu().equals(temp.get(i).getMaNguyenLieu())) {
                    XacNhan.setText("Không lấy 2 nguyên liệu trùng, nếu muốn thì hãy tăng số lượng");
                    return;
                }
            }
        }
        DanhSachNguyenLieuCuaMonAn b = new DanhSachNguyenLieuCuaMonAn(a.getMaNguyenLieu(),a.getTenNguyenLieu(),a.getSoLuong(),a.getDonViTinh(),a.getTinhTrang(),"0");
        list2.add(b);
        temp.add(b);
        MaNguyenLieu_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("MaNguyenLieu"));
        TenNguyenLieu_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("TenNguyenLieu"));
        SoLuong_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("SoLuongCan"));
        DonViTinh_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("DonViTinh"));
        TinhTrang_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("TinhTrang"));
        table_.setItems(list2);
    }

    @FXML public void HuyBo(ActionEvent event){
        DanhSachNguyenLieuCuaMonAn b = table_.getSelectionModel().getSelectedItem();
        list2.remove(b);
        temp.remove(b);
        MaNguyenLieu_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("MaNguyenLieu"));
        TenNguyenLieu_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("TenNguyenLieu"));
        SoLuong_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("SoLuongCan"));
        DonViTinh_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("DonViTinh"));
        TinhTrang_.setCellValueFactory(new PropertyValueFactory<DanhSachNguyenLieuCuaMonAn,String>("TinhTrang"));
        table_.setItems(list2);
    }

    @FXML public void editCommit(CellEditEvent editEvent){
        DanhSachNguyenLieuCuaMonAn b = table_.getSelectionModel().getSelectedItem();
        b.setSoLuongCan(editEvent.getNewValue().toString());
        for(int i=0;i<temp.size();i++){
            if(b.getMaNguyenLieu().equals(temp.get(i).getMaNguyenLieu())){
                temp.get(i).setSoLuongCan(b.getSoLuongCan());
            }
        }
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
        SoLuong_.setCellFactory(TextFieldTableCell.forTableColumn());
        table_.setEditable(true);
    }
}
