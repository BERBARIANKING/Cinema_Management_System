//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.geometry.Orientation;

public class MainMenu extends Application {

    private Stage primaryStage;
    private ArrayList<UserSelection> userSelections;

    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.userSelections = new ArrayList<>();
        displayMainMenu();
    }

    //Sunartisi ekkinisis tis efarmogis
    private void displayMainMenu() {
        MainMenuUI mainMenuUI = new MainMenuUI();
        mainMenuUI.displayMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }

    
    //Upoklasi gia tin diaxeirisi tou grafikou peribalontos tou menu
    private class MainMenuUI {

        public void displayMainMenu() {             
             FlowPane seatPane = new FlowPane(Orientation.VERTICAL);
             seatPane.setVgap(10); 
            
            //Koumpia gia tin epilogis gia tis diergaseies tis efarmogis 
            Button session = createMenuButton("Book a Session");
            Button movie = createMenuButton("Today's Movies");
            Button seat = createMenuButton("Seating Maintenance");
            Button party = createMenuButton("Book a Party");
            Button ticket = createMenuButton("Ticket Reservations");
            Button retrieveSeatButton = createMenuButton("Seat layouts");
            
            //Orismos megistou platous gia ta koumpia
            session.setMaxWidth(Double.MAX_VALUE);
            movie.setMaxWidth(Double.MAX_VALUE);
            seat.setMaxWidth(Double.MAX_VALUE);
            retrieveSeatButton.setMaxWidth(Double.MAX_VALUE);
            ticket.setMaxWidth(Double.MAX_VALUE);
            party.setMaxWidth(Double.MAX_VALUE);
            seatPane.getChildren().add(retrieveSeatButton);

            Scene scene = new Scene(seatPane);
            primaryStage.setScene(scene);

            Image image = new Image(getClass().getResourceAsStream("image/wallpaper.jpg"));
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            Background background = new Background(backgroundImage);
            seatPane.setBackground(background);
            seatPane.setAlignment(Pos.CENTER);
            seatPane.getChildren().addAll(session, movie, seat, party,ticket);
            //seatPane.getChildren().addAll(session, movie, seat, ticket, party);

            primaryStage.setTitle("Main Menu(Admin)");
            primaryStage.setWidth(900);
            primaryStage.setHeight(700);
            primaryStage.setResizable(false);
            primaryStage.show();

             
             //Dimiourgia antikeimenon gia tin antistoistixisi kathe koumpiou me ti mathodo emfaniseis tou parathirou dimiourgias probolis
            CreateSession createSession = new CreateSession();
            session.setOnAction(event -> createSession.showSessionMenu());

            RetrieveSeat retrieveSeat = new RetrieveSeat(new boolean[5][30]);
            retrieveSeatButton.setOnAction(event -> retrieveSeat.showVisualizer(userSelections));

            seat.setOnAction(event -> {
                Stage seatingMaintenanceStage = new Stage();
                seatingMaintenanceStage.setTitle("Seating Maintenance");
                FlowPane seatingPane = new FlowPane();
                Button addSeatButton = new Button("Add Seat");
                Button removeSeatButton = new Button("Remove Seat");
                addSeatButton.setOnAction(e -> System.out.println("Add Seat clicked"));
                removeSeatButton.setOnAction(e -> System.out.println("Remove Seat clicked"));
                seatingPane.getChildren().addAll(addSeatButton, removeSeatButton);
                Scene seatingScene = new Scene(seatingPane);
                seatingMaintenanceStage.setScene(seatingScene);
                seatingMaintenanceStage.show();
            });

            Party partymenu = new Party();
            party.setOnAction(event -> partymenu.displayPartyMaintenance());

            MovieMenu movieMenu = new MovieMenu();
            movie.setOnAction(event -> movieMenu.displayMaintenanceMenu());

  
    
        }
        
        //Sinartisi dimiourgias koumpiou 
        private Button createMenuButton(String text) {
            Button button = new Button(text);
            Font font1 = Font.font("Arial", FontWeight.BOLD, 17);
            button.setFont(font1);
            button.setStyle("-fx-background-color: #6495ED; -fx-text-fill: white;");
            return button;
        }
    }
}
