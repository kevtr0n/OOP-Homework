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
 * Created by BrownD on 19/02/2017.
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
                if (patronId == null)
                    alertMessage.setText("Please enter a Patron ID in the search field.");
                if (patronId != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByPatronId(patronId);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "name":
                String name = searchField.getText();
                if (name == null)
                    alertMessage.setText("Please enter a name in the search field.");
                if (name != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsWithNameLike(name);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "address":
                String address = searchField.getText();
                if (address == null)
                    alertMessage.setText("Please enter address in search field.");
                if (address != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByAddress(address);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "city":
                String city = searchField.getText();
                if (city == null)
                    alertMessage.setText("Please enter city in search field.");
                if (city != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsAtCity(city);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "stateCode":
                String stateCode = searchField.getText();
                if (stateCode == null || stateCode.length() != 2)
                    alertMessage.setText("Please enter state code in format: --.");
                if (stateCode != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsAtStateCode(stateCode);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "zip":
                String zip = searchField.getText();
                if (zip == null)
                    alertMessage.setText("Please enter a zip code.");
                if (!isNumeric(zip) || zip.length() != 5)
                    alertMessage.setText("Zip must be in the format: -----.");
                if (isNumeric(zip) && zip != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsAtZipCode(zip);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "email":
                String email = searchField.getText();
                if (email == null)
                    alertMessage.setText("Please enter an email address.");
                if (email != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByEmail(email);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "dateOfBirth":
                String dateOfBirth = searchField.getText();
                if (dateOfBirth == null || dateOfBirth.length() != 10 || dateOfBirth.charAt(4) != '-' || dateOfBirth.charAt(7) != '-')
                    alertMessage.setText("Please enter date in format in 'yyyy-mm-dd'.");
                if (dateOfBirth != null && dateOfBirth.length() == 10) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByDateOfBirth(dateOfBirth);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;

            case "status":
                String status = searchField.getText();
                if (status == null)
                    alertMessage.setText("Please enter either 'Active' or 'Inactive' in the search field");
                if (status != null) {
                    PatronCollection patronCol = new PatronCollection();
                    Vector patrons = patronCol.findPatronsByStatus(status);
                    return FXCollections.observableList(patrons);
                }
                searchField.clear();
                break;
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