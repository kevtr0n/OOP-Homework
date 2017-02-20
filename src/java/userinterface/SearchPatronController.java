package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Patron;

/**
 * Created by BrownD on 19/02/2017.
 */
public class SearchPatronController extends SearchController {

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
}
