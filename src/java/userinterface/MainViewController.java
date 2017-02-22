package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by BrownD on 13/02/2017.
 */
public class MainViewController implements Initializable {

    @FXML
    private Pane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void menuClickListener(ActionEvent event) throws IOException {
        if (root != null) {
            Button button = (Button) event.getSource();
            System.out.println(button.getId());
            Parent pane = null;

            switch (button.getId()) {
                case "newBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("newbookview.fxml"));
                    break;

                case "newPatron":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("newpatronview.fxml"));
                    break;

                case "searchBook":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("searchBookView.fxml"));
                    break;

                case "searchPatron":
                    pane = FXMLLoader.load(getClass().getClassLoader().getResource("searchPatronView.fxml"));
                    break;
            }
            if (pane != null) {
                root.getChildren().clear();
                root.getChildren().setAll(pane);
            }
        }
    }
}
