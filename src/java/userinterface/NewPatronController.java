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
public class NewPatronController implements Initializable {

    @FXML
    private TextField patronId;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField city;
    @FXML
    private TextField stateCode;
    @FXML
    private TextField zip;
    @FXML
    private TextField email;
    @FXML
    private TextField dateOfBirth;
    @FXML
    private TextField status;
    @FXML
    private Text alertMessage;

    ArrayList<TextField> textFieldList;

    public NewPatronController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(patronId);
        textFieldList.add(name);
        textFieldList.add(address);
        textFieldList.add(city);
        textFieldList.add(stateCode);
        textFieldList.add(zip);
        textFieldList.add(email);
        textFieldList.add(dateOfBirth);
        textFieldList.add(status);
    }

    public void submit(ActionEvent actionEvent) {
        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText("Please fill all text field to submit a patron.");
                return;
            }
        }
        alertMessage.setText("The patron " + address.getText() + " have been submit.");
        for (TextField textField : textFieldList) {
            textField.clear();
        }
    }
}
