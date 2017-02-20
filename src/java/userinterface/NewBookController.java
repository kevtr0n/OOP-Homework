package userinterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by BrownD on 14/02/2017.
 */
public class NewBookController implements Initializable {

    @FXML
    private TextField bookId;
    @FXML
    private TextField author;
    @FXML
    private TextField title;
    @FXML
    private TextField pubYear;
    @FXML
    private TextField status;
    @FXML
    private Text alertMessage;

    ArrayList<TextField> textFieldList;

    public NewBookController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(bookId);
        textFieldList.add(author);
        textFieldList.add(title);
        textFieldList.add(pubYear);
        textFieldList.add(status);
    }

    public void submit(ActionEvent actionEvent) {
        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText("Please fill all text field to submit a book.");
                return;
            }
        }

        alertMessage.setText("The book " + title.getText() + " have been submit.");
        for (TextField textField : textFieldList) {
            textField.clear();
        }
    }
}
