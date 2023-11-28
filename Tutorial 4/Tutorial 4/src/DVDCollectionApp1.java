import javafx.application.Application;
import javafx.scene.Scene;
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
                    // Placeholder for an error so that the program will not just crash
                    System.out.println("Something went wrong");
                }
            }
        });

        // The delete button
        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                // Checks the selected value of the TitleList of the DVDCollectionAppView (1 or 2)
                model.remove(view.getTitleList().getSelectionModel().getSelectedItem());
                view.update(model, 0);
            }
        });

        // Highlights all the 3 sections (title, year, and length) when one is selected
        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                int index = view.getTitleList().getSelectionModel().getSelectedIndex();
                view.getYearList().getSelectionModel().select(index);
                view.update(model, 0);

            }
        });


        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}