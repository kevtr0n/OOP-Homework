package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Patron;

import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by BrownD on 14/02/2017.
 */
public class NewPatronController implements Initializable {

    ObservableList<String> statusList = FXCollections.observableArrayList("Active", "Inactive");
    final private String[] patronArray = {"patronId", "name", "address", "city", "stateCode",
            "zip", "email", "dateOfBirth", "status"};
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
    private ComboBox<String> status;
    @FXML
    private Text alertMessage;

    ArrayList<TextField> textFieldList;

    public NewPatronController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textFieldList = new ArrayList<>();
        textFieldList.add(name);
        textFieldList.add(address);
        textFieldList.add(city);
        textFieldList.add(stateCode);
        textFieldList.add(zip);
        textFieldList.add(email);
        textFieldList.add(dateOfBirth);
        status.setValue("Active");
        status.setItems(statusList);
    }

    public void submit(ActionEvent actionEvent) {
        Properties props = new Properties();

        for (TextField textField : textFieldList) {
            if (textField.getText().equals("")) {
                alertMessage.setText("Please complete all text fields to submit a patron.");
                return;
            } else {
                props.put(textField.getId(), textField.getText());
            }
        }

        props.put(status.getId(), status.getSelectionModel().getSelectedItem().toString());
        System.out.println("Props: " + props.toString());
        Patron newPatron = new Patron(props);
        newPatron.update();
        alertMessage.setText("The patron '" + name.getText() + "' has been submitted.");
        for (TextField textField : textFieldList) {
            textField.clear();
        }
    }
}
