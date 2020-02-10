package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class MainController implements Initializable{
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private Label label1;
    @FXML private BorderPane mainPane;
    public void login(ActionEvent event) throws SQLException, IOException {
        DBConnection dbconnect = new DBConnection();
        if(dbconnect.KiemTraDangNhap(username.getText(),password.getText())) {
            label1.setText("Đăng nhập thành công");
            Pane view = FXMLLoader.load(getClass().getResource("FormQuanLyChung.fxml"));
            mainPane.setCenter(view);
        }
        else {
            label1.setText("Đăng nhập thất bại");
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
