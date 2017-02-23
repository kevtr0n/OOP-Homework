package userinterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import model.Book;
import model.BookCollection;

import java.util.Vector;

/**
 * Created by BrownD on 19/02/2017.
 */
public class SearchBookController extends SearchController {

    @FXML
    private Text alertMessage;

    @Override
    public ObservableList<String> itemsSearchChoiceArray() {
        return FXCollections.observableArrayList(
                "bookId",
                "author",
                "title",
                "pubYear",
                "status");
    }

    @Override
    protected void setTableView() {
        TableColumn column;

        for (String property : properties) {
            column = new TableColumn(property);
            column.setMinWidth(100);
            column.setCellValueFactory(new PropertyValueFactory<Book, String>(property));
            tableView.getColumns().add(column);
        }
    }

    @Override
    protected ObservableList querySelector() {

        switch (searchChoice.getSelectionModel().getSelectedItem()) {

            case "bookId":
                String bookId = searchField.getText();
                if (bookId == null)
                    alertMessage.setText("Please enter a Book ID.");
                if (bookId != null) {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksByBookId(bookId);
                    return FXCollections.observableList(books);
                }
                searchField.clear();
                break;

            case "author":
                String author = searchField.getText();
                if (author == null)
                    alertMessage.setText("Please enter an author.");
                if (author != null) {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksWithAuthorLike(author);
                    return FXCollections.observableList(books);
                }
                searchField.clear();
                break;

            case "title":
                String title = searchField.getText();
                if (title == null)
                    alertMessage.setText("Please enter a title.");
                if (title != null) {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksWithTitleLike(title);
                    return FXCollections.observableList(books);
                }
                searchField.clear();
                break;

            case "pubYear":
                String pubYear = searchField.getText();
                if (pubYear == null)
                    alertMessage.setText("Please enter a year.");
                if ((pubYear.length() > 4 || pubYear.length() < 4) && !isNumeric(pubYear))
                    alertMessage.setText("Please enter year numerically in format: yyyy");
                if (Integer.parseInt(pubYear) < 1800 && Integer.parseInt(pubYear) > 2017)
                    alertMessage.setText("The supplied publication year '" + pubYear + "' is out of bounds!");
                if (isNumeric(pubYear)) {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksByPubYear(pubYear);
                    return FXCollections.observableList(books);
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
