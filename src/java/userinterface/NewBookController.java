package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Book;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by BrownD on 14/02/2017.
 */
public class NewBookController implements Initializable {

    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    @FXML
    private TextField author;
    @FXML
    private TextField title;
    @FXML
    private TextField pubYear;
    @FXML
    private ComboBox<String> status;
    @FXML
    private Text alertMessage;

    ArrayList<TextField> textFieldList;

    public NewBookController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(author);
        textFieldList.add(title);
        textFieldList.add(pubYear);
        status.setValue("Active");
        status.setItems(statusList);
    }

    public void submit(ActionEvent actionEvent) {
        Properties props = new Properties();

        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText("Please complete all text fields to submit a book.");
                return;
            } else {
                props.put(textField.getId(), textField.getText());
            }
        }
        props.put(status.getId(), status.getSelectionModel().getSelectedItem().toString());
        Book newBook = new Book(props);
        newBook.update();
        alertMessage.setText("The book '" + title.getText() + "' has been submitted.");
        for (TextField textField : textFieldList) {
            textField.clear();
        }
    }
}
