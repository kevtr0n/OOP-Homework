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
                if (bookId == null || bookId.equals("") || !isNumeric(bookId)) {
                    alertMessage.setText("Please enter a numeric Book ID in the search field.");
                    searchField.clear();
                    break;
                } else {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksByBookId(bookId);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }

            case "author":
                String author = searchField.getText();
                if (author == null || author.equals("")) {
                    alertMessage.setText("Please enter an author in the search field.");
                    searchField.clear();
                    break;
                } else {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksWithAuthorLike(author);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }

            case "title":
                String title = searchField.getText();
                if (title == null || title.equals("")) {
                    alertMessage.setText("Please enter a title in the search field.");
                    searchField.clear();
                    break;
                } else {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksWithTitleLike(title);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }

            case "pubYear":
                String pubYear = searchField.getText();
                if (pubYear == null || pubYear.equals("")) {
                    alertMessage.setText("Please enter a year in the search field.");
                    searchField.clear();
                    break;
                }
                if (pubYear.length() != 4 && !isNumeric(pubYear)) {
                    alertMessage.setText("Publication year '" + pubYear + "' not in format: yyyy");
                    searchField.clear();
                    break;
                }
                if (Integer.parseInt(pubYear) < 1800 || Integer.parseInt(pubYear) > 2017) {
                    alertMessage.setText("Publication year '" + pubYear + "' not between 1800 and 2017.");
                    searchField.clear();
                    break;
                }
                if (isNumeric(pubYear)) {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksByPubYear(pubYear);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }

            case "status":
                String status = searchField.getText();
                if (status == null || status.equals("")) {
                    alertMessage.setText("Please enter either Active/Inactive in search field.");
                    searchField.clear();
                    break;
                } else {
                    BookCollection bookCol = new BookCollection();
                    Vector books = bookCol.findBooksByStatus(status);
                    searchField.clear();
                    return FXCollections.observableList(books);
                }
        }
        searchField.clear();
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
