/*
 * Hay!
 * Hello
 * I'm Htet Oakkar
 */
package library.system.editbook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import library.system.dao.BookDAO;
import library.system.model.Book;
import library.system.model.Member;
import library.system.util.Message;

/**
 * FXML Controller class
 *
 * @author Htet-Oakkar
 */
public class EditbookController implements Initializable {

    @FXML
    private JFXTextField idField;
    @FXML
    private JFXTextField titleField;
    @FXML
    private JFXTextField authorField;
    @FXML
    private JFXTextField publisherField;
    @FXML
    private JFXButton saveBtn;
    @FXML
    private JFXButton cancelBtn;

    private BookDAO bookDAO;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO = new BookDAO();
    }

    @FXML
    private void editBookInfo(ActionEvent event) {

        int id = Integer.parseInt(idField.getText());
        String title = titleField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();

        if (title.isEmpty() || author.isEmpty() || publisher.isEmpty()) {
            Message.showWarningMessage("Warning", "Please input all the required fields");
            return;
        }

        Book book = new Book(id, title, author, publisher);
        try {
            bookDAO.updateBooks(book);
            System.out.println("Updated!");
            Stage stage = (Stage) saveBtn.getScene().getWindow();
            stage.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditbookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    public void setInitData(Book book) {
        idField.setDisable(true);
        idField.setText(Integer.toString(book.getId()));
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        publisherField.setText(book.getPublisher());
    }

}
