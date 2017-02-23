package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patron;
import javafx.scene.text.Text;
import model.PatronCollection;

import java.util.Vector;

/**
 * Created by BrownD and Kevin Hayden on 19/02/2017.
 */
public class SearchPatronController extends SearchController {

    @FXML
    private Text alertMessage;

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                "patronId",
                "name",
                "address",
                "city",
                "stateCode",
                "zip",
                "email",
                "dateOfBirth",
                "status");
    }

    @Override
    protected void setTableView() {
        TableColumn column;

        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(
                    new PropertyValueFactory<Patron, String>(property));

            tableView.getColumns().add(column);
        }
    }

    @Override
    protected ObservableList querySelector() {

        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "patronId":
                String patronId = searchField.getText();
                if (patronId == null || patronId.equals("") || !isNumeric(patronId)) {
                    alertMessage.setText("Please enter a numeric Patron ID in the search field.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByPatronId(patronId);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "name":
                String name = searchField.getText();
                if (name == null || name.equals("")) {
                    alertMessage.setText("Please enter a name in the search field.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsWithNameLike(name);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "address":
                String address = searchField.getText();
                if (address == null || address.equals("")) {
                    alertMessage.setText("Please enter an address in search field.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByAddress(address);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "city":
                String city = searchField.getText();
                if (city == null || city.equals("")) {
                    alertMessage.setText("Please enter a city in search field.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsAtCity(city);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "stateCode":
                String stateCode = searchField.getText();
                if (stateCode == null || stateCode.equals("") || stateCode.length() != 2) {
                    alertMessage.setText("Please enter a state code in format: --.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsAtStateCode(stateCode);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "zip":
                String zip = searchField.getText();
                if (zip == null || zip.equals("") || !isNumeric(zip) || zip.length() != 5) {
                    alertMessage.setText("Please enter a zip code in format: -----");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsAtZipCode(zip);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "email":
                String email = searchField.getText();
                if (email == null || email.equals("")) {
                    alertMessage.setText("Please enter an email address in search field.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByEmail(email);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "dateOfBirth":
                String dateOfBirth = searchField.getText();
                if (dateOfBirth == null || dateOfBirth.equals("")) {
                    alertMessage.setText("Please enter a date in the search field.");
                }
                if (dateOfBirth.length() != 10 || dateOfBirth.charAt(4) != '-' || dateOfBirth.charAt(7) != '-') {
                    alertMessage.setText("Date '" + dateOfBirth + "' not in format: 'yyyy-mm-dd'.");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByDateOfBirth(dateOfBirth);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }

            case "status":
                String status = searchField.getText();
                if (status == null || status.equals("")) {
                    alertMessage.setText("Please enter either 'Active' or 'Inactive' in the search field");
                    searchField.clear();
                    break;
                } else {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByStatus(status);
                    searchField.clear();
                    return FXCollections.observableList(patrons);
                }
        }
        return null;
    }

    private static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}