package userinterface;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by BrownD on 19/02/2017.
 */
public abstract class SearchController implements Initializable {

    @FXML
    private ChoiceBox<String> searchChoice;

    @FXML
    protected TableView tableView;

    ObservableList<String> properties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        properties = itemsSearchChoiceArray();
        searchChoice.setItems(properties);
        searchChoice.getSelectionModel().selectFirst();
        setTableView();
    }

    public void search(ActionEvent event) {
    }


    protected abstract ObservableList<String> itemsSearchChoiceArray();

    protected abstract void setTableView();
}
