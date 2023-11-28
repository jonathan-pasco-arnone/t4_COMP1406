import javafx.application.Application;
import javafx.scene.Scene;
// Alert is used for error handling
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class DVDCollectionApp1  extends Application {

    DVDCollection model = new DVDCollection();



    public DVDCollectionApp1() {
        model = DVDCollection.example1();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        DVDCollectionAppView1  view = new DVDCollectionAppView1();
        view.update(model, 0);
        aPane.getChildren().add(view);


        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        // The add song button
        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                try {
                    /*

                    This is a way to get the title name and song using the provided TextFields from
                    DVDCollectionAppView2 instead of the three input dialogs

                    String title = view.getTitleField().getText();
                    String yearS = view.getYearField().getText();
                    String lengthS = view.getLengthField().getText();

                    */

                    String title = javax.swing.JOptionPane.showInputDialog("Please enter the song's title: ");
                    String yearS = javax.swing.JOptionPane.showInputDialog("Please enter the year the song was made in: ");
                    String lengthS = javax.swing.JOptionPane.showInputDialog("Please enter the duration of the song: ");

                    int year = Integer.parseInt(yearS);
                    int length = Integer.parseInt(lengthS);

                    DVD newSong = new DVD(title, year, length);
                    model.add(newSong);
                    view.update(model, 0);
                } catch (Exception exception) {
                    // Uses an alert to notify the user that the input was invalid. This was not entirely necessary and
                    // was not asked for in the tutorial, but it is still good to have.
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("An error occurred");
                    alert.setHeaderText("The inputs you provided are invalid.");
                    alert.setContentText("Please try again.");

                    alert.showAndWait();
                }
            }
        });

        // The delete button
        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                // Use this for AppView1
                model.remove(view.getTitleList().getSelectionModel().getSelectedItem());
                // The compiler won't let me implement both in a if else statement because the initial view variable
                // will be what it is set to above.
                // Use this for AppView2
                // model.remove(view.getTitleList().getSelectionModel().getSelectedItem().getTitle());
                view.update(model, 0);
            }
        });

        // Highlights all the 3 sections (title, year, and length) when the title is selected
        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                // Use this for AppView1 because AppView2 is not able to incorporate this
                // Grabs the index of the current song selected
                int index = view.getTitleList().getSelectionModel().getSelectedIndex();
                view.getYearList().getSelectionModel().select(index);
                view.getLengthList().getSelectionModel().select(index);

            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}