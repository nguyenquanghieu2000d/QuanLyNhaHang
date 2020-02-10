package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.invoke.MutableCallSite;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

public class FormQuanLyChungController implements Initializable {
    @FXML
    private BorderPane mainPane;
    public Pane ChonChucNang(String fileName) throws IOException { ;
        Pane panechinh = new Pane();
        try{
            URL fileURL = Main.class.getResource("/sample/" + fileName + ".fxml");
            System.out.println(fileURL.toString());
            if(fileURL == null){
                throw new java.io.FileNotFoundException("FXML not found");
            }
            panechinh = FXMLLoader.load(fileURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return panechinh;
    }
    @FXML
    public void button1(ActionEvent event) throws IOException {
        System.out.println("Click button1");
        Pane view = ChonChucNang("QuanLyNhanVien");
        mainPane.setCenter(view);
    }

    @FXML
    public void QuanLyKhachHang(ActionEvent event) throws IOException {
        Pane view = ChonChucNang("QuanLyKhachHang");
        mainPane.setCenter(view);
    }

    @FXML
    public void QuanLyMonAn(ActionEvent event) throws IOException {
        Pane view = ChonChucNang("QuanLyMonAn");
        mainPane.setCenter(view);
    }

    @FXML
    public void QuanLyKhoNguyenLieu(ActionEvent event) throws IOException {
        Pane view = ChonChucNang("QuanLyKhoNguyenLieu");
        mainPane.setCenter(view);
    }



    @FXML
    public void dangxuat(ActionEvent event) throws IOException {
        Pane view = FXMLLoader.load(getClass().getResource("fxmlFile.fxml"));
        mainPane.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
