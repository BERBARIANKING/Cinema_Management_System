//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;





//interface gia thn krathsh sesssion tainiwn
//functional interface
interface Bookings{
    void showCreateSessionMenu();
  //  void showTicketMenu();
}

//api gia thn epilogh hmeromhnias
//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/DatePicker.html
//klasi gia agora eisitiriou
public class CreateSession {
    //dhlwsh stoixeiwn gia thn dhmiourgia mias krathshs tainias
    private ComboBox<String> movieBox;
    private ComboBox<String> statusBox;
    private ComboBox<String> timeStartBox;
    private DatePicker datepicker;//metablhth gia to hmerologio
    private ArrayList<UserSelection> userSelections;//metablhth gia tis epiloges tou xrhsth
    private ArrayList<UserSelection> seatSelections = new ArrayList<>();//metablhth gia tis theseis tou xrhsth
    private RetrieveSeat retrieveSeat;//metablhth gia thn epistrofh theshs

    //constructor
    public CreateSession() {
        this.userSelections = new ArrayList<>();
        this.retrieveSeat = new RetrieveSeat(new boolean[5][30]);
    }

    //methodos gia thn emfanish menu krathshs
public void showSessionMenu() {
    Stage stage = new Stage();
    stage.setTitle("Create a session");
    GridPane gridPane = new GridPane();
    gridPane.setMinWidth(900);
    gridPane.setMinHeight(700);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(10);
    gridPane.setHgap(10);

     //koumpia epiloghs gia ton xrhsth
    Button btCreateSDone = new Button("DONE");
    Button btShowUserSelections = new Button("Show User Selections");
    Button btCreateSBack = new Button("RETURN TO MAIN MENU");
    Label lbCreateSTitle = new Label("Please Select A Movie:   ");
    Label lbCreateSDate = new Label("Please Select Movie Screening Date:   ");
    Label lbCreateSTime = new Label("Please Select Movie Screening time:   ");
    Label lbCreatesType = new Label("Please Select Hall Type:         ");
    Label lbCreateSStatus = new Label("Select Movie Status");
    Label seatRowLabel = new Label("Select Seat Row:");
    Label seatNoLabel = new Label("Select Seat Number:");
    

    movieBox = new ComboBox<>();

    MovieMenu movieMenu = new MovieMenu();
    ArrayList<Movie> movieArray = movieMenu.getAllRecords();

    for (int i = 0; i < movieArray.size(); i++) {
        Movie movie = movieArray.get(i);
        String movieTitle = movie.getTitle();
        movieBox.getItems().add(movieTitle);
    }

    movieBox.setPromptText("Select Movie");

    datepicker = new DatePicker();
    datepicker.setPromptText("Select Date");

    timeStartBox = new ComboBox<>();
    timeStartBox.setPromptText("Select Time");

     //Basei ti katalili ora gia tin tainia pou th aepilegi  
    movieBox.setOnAction(event -> {
        String selectedMovie = movieBox.getValue();
        ArrayList<String> availableTimes = movieMenu.getAvailableScreeningTimes(selectedMovie);
        timeStartBox.getItems().clear();
        timeStartBox.getItems().addAll(availableTimes);
    });
    
    //epiloges gia to eidos aithousas (halltype)

    RadioButton rb2D = new RadioButton("2D");
    RadioButton rbIMAX = new RadioButton("IMAX");
    RadioButton rbIND = new RadioButton("INDULGE");
    RadioButton rbLUXE = new RadioButton("LUXE");
    RadioButton rbSUP = new RadioButton("SUPREME");
    RadioButton rbPRE = new RadioButton("PREMIUM");
    ToggleGroup group = new ToggleGroup();
    rb2D.setToggleGroup(group);
    rbIMAX.setToggleGroup(group);
    rbIND.setToggleGroup(group);
    rbLUXE.setToggleGroup(group);
    rbSUP.setToggleGroup(group);
    rbPRE.setToggleGroup(group);

    FlowPane typePane = new FlowPane(Orientation.HORIZONTAL);
    typePane.getChildren().addAll(rb2D, rbIMAX, rbIND, rbLUXE, rbSUP, rbPRE);
    typePane.setHgap(15);

    
    //epiloges gia thn katastash tainias(proeraitiko kai gia mellontikh ulopoihsh)
    statusBox = new ComboBox<>();
    statusBox.getItems().addAll("Now Screening", "Now Seating", "Selling Fast", "On sale", "Sold Out");
    statusBox.setPromptText("Set Status");

    //Epilogh theshs
    ComboBox<String> seatRowBox = new ComboBox<>();
    seatRowBox.getItems().addAll("A", "B", "C", "D", "E");
    seatRowBox.setPromptText("Select Row");

    //O xrhsths mporei na dialexei opoiadhpote apo tis 30 theseis 
    //(kathorismos arithmoou thesewn)
    ComboBox<Integer> seatNoBox = new ComboBox<>();
    for (int i = 1; i <= 30; i++) {
        seatNoBox.getItems().add(i);
    }
    seatNoBox.setPromptText("Select Seat Number");

    FlowPane statusPane = new FlowPane(Orientation.HORIZONTAL);
    statusPane.getChildren().add(statusBox);

    gridPane.getChildren().clear();
    gridPane.add(lbCreateSTitle, 0, 0);
    gridPane.add(lbCreateSDate, 0, 1);
    gridPane.add(lbCreateSTime, 0, 2);
    gridPane.add(lbCreatesType, 0, 3);
    gridPane.add(lbCreateSStatus, 0, 4);
    gridPane.add(btCreateSDone, 0, 5);
    gridPane.add(btCreateSBack, 0, 6);
    gridPane.add(movieBox, 1, 0);
    gridPane.add(datepicker, 1, 1);
    gridPane.add(timeStartBox, 1, 2);
    gridPane.add(typePane, 1, 3);
    gridPane.add(statusPane, 1, 4);
    gridPane.add(seatRowLabel, 1, 7);
    gridPane.add(seatRowBox, 2, 7);
    gridPane.add(seatNoLabel, 3, 7);
    gridPane.add(seatNoBox, 4, 7);
    gridPane.add(btShowUserSelections, 0, 7);


    btCreateSDone.setOnAction(e -> {
        String userMovie = movieBox.getValue();
        String userDate = null;
        int userTime =0 ;
        try{
            
            userTime = Integer.parseInt(timeStartBox.getValue());//
        }
        catch(Exception ex)
        {
            System.out.println("TEST1");
            JOptionPane.showMessageDialog(null, "Please enter a time from the list!");
            return;
        }
        try{
            userDate= datepicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
        }
        catch(Exception ex)
        {
            System.out.println("TEST2");
            JOptionPane.showMessageDialog(null, "Please enter a date!");
            return;
        }
        String RowInitial = null;
        int seatNo = 0;
        try{
            RowInitial = seatRowBox.getValue();
            seatNo = seatNoBox.getValue();
        }
        catch(Exception ex)
        {
            System.out.println("TEST3");
            JOptionPane.showMessageDialog(null, "Please select seats!");
            return;
        }
        

        String userType = null;
        
        if (rb2D.isSelected()) {
            userType = "2D";
        } else if (rbIMAX.isSelected()) {
            userType = "IMAX";
        } else if (rbIND.isSelected()) {
            userType = "INDULGE";
        } else if (rbLUXE.isSelected()) {
            userType = "LUXE";
        } else if (rbSUP.isSelected()) {
            userType = "SUPREME";
        } else if (rbPRE.isSelected()) {
            userType = "PREMIUM";
        }
        
//try catch se periptwsh pou o xrhsths den epilexei ola ta dedomena apo to menu
        String userStatus = null;
        try{
            userStatus = statusBox.getValue();
        }
        catch(Exception ex)
        {
            System.out.println("TEST4");
            JOptionPane.showMessageDialog(null, "Please select a status!");//mhnuma lanthasmenhs epiloghs(den exoun paththei )
            return;
        }

        UserSelection userSelection = new UserSelection(userMovie, userDate, userTime, userType, userStatus, RowInitial, seatNo);
        userSelections.add(userSelection);

        retrieveSeat.updateSeats(userSelection);

            //anoixe parathuro eisithriou
    openTicketDetailsWindow(userSelection);
          //emfanise theseis
            retrieveSeat.showVisualizer(userSelections);
        
        gridPane.getChildren().clear();
        stage.close();
    });

    btShowUserSelections.setOnAction(e -> displayUserSelectionsInFX(userSelections, seatSelections));

    btCreateSBack.setOnAction(e -> stage.close());

    Scene scene = new Scene(gridPane);
    stage.setScene(scene);
    stage.show();
}


//methodos emfanishs menu epilogwn xrhsth
 void displayUserSelectionsInFX(ArrayList<UserSelection> userSelections, ArrayList<UserSelection> seatSelections) {
    Stage stage = new Stage();
    stage.setTitle("User Selections");

    //Sunduamsos epilogwn xrhsth kai thesewn se mia lista
    ArrayList<UserSelection> allSelections = new ArrayList<>();
    allSelections.addAll(userSelections);
    allSelections.addAll(seatSelections);

    //Dhmiourgia Listview etsi wste na emfanizontai ta stoixeia ston xrhsth
    ListView<String> allSelectionsListView = new ListView<>();
    ObservableList<String> allSelectionsItems = FXCollections.observableArrayList();

    for (UserSelection selection : allSelections) {
        allSelectionsItems.add(selection.toString());
    }

    allSelectionsListView.setItems(allSelectionsItems);

    Scene scene = new Scene(allSelectionsListView, 750, 300);
    stage.setScene(scene);

    stage.show();
}

    
    //methodos gia thn emfanish twn stoixeiwn krathshs eisithriou
    private void openTicketDetailsWindow(UserSelection userSelection) {
    //Dhmiourgia antikeimenou ticket 
    Ticket ticket = new Ticket(
            userSelection.getMovieTitle(),
            userSelection.getMovieDate(),
            userSelection.getUserTime(),
            userSelection.getUserType(),
            userSelection.getSeatRow(),
            userSelection.getSeatNo()
    );

    //Emfanisi leptomeries eisitiriou
    ticket.showTicketMenu();
}
    
    

}