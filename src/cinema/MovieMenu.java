//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MovieMenu { 
    


        //Lista gia tis tainies
       private ArrayList<Movie> MovieList;
   

    public MovieMenu() {
  
        //arxikopoihsh listas twn session 
        this.MovieList = new ArrayList<>();
    }

    
    //Methodos gia ta dedomena ths tainias
public ArrayList<Movie> getAllRecords() {
    
    //dhmiourgies listwn gia thn apothkeush dedomenwn tainiwn
    ArrayList<Movie> movieList = new ArrayList<>();//lista tainiwn
    ArrayList<String> movie1Dates = new ArrayList<>(); //lista hmeromhniwn ana tainia
    ArrayList<String> movie1Times = new ArrayList<>(); //wres ana tainia
    ArrayList<String> movie1HallTypes = new ArrayList<>();//aithouses ana tainia
    movie1Dates.add("2024-01-15");
    movie1Times.add("21:00");
    movie1HallTypes.add("IMAX");

    
    
    ArrayList<String> movie2Dates = new ArrayList<>(); 
    ArrayList<String> movie2Times = new ArrayList<>(); 
    ArrayList<String> movie2HallTypes = new ArrayList<>();
    movie2Dates.add("2024-01-16");
    movie2Times.add("18:30");
    movie2HallTypes.add("LUXE");

    //Dhmiourgies instances tainiwn
    Movie movie1 = new Movie(1, "Oppenheimer", 180, 2023, "R", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", "Oppenheimer.jpg", "Oppenheimer.mp4", movie1Dates, movie1Times, movie1HallTypes);

    Movie movie2 = new Movie(2, "Fight Club", 139, 1999, "R", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", "Fightclub.jpg", "matrix.mp4", movie2Dates, movie2Times, movie2HallTypes);

    //Prosthkh tainiwn sthn lista
    movieList.add(movie1);
    movieList.add(movie2);

    return movieList;
}



//Prokathorismeni lista tainion
     List<Movie> movies = Arrays.asList(
            new Movie(1, "Oppenheimer", 180, 2023, "R", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", "Oppenheimer.jpg", "Oppenheimer.mp4"),
            new Movie(2, "Fight Club", 139, 1999, "R", "An insomniac office worker and a devil-may-care soap maker form an underground fight club that evolves into much more.", "Fightclub.jpg", "matrix.mp4")
        //    new Movie(3, "Seven", 127, 1995, "R", "Two detectives, a rookie and a veteran, hunt a serial killer who uses the seven deadly sins as his motives.", "Seven.jpg", "inception.mp4"),
       //     new Movie(4, "Interstellar", 169, 2014, "PG-13", "When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.", "Interstellar.jpg", "inception.mp4")
    );
       //Prosthiki tainion

     
      //dokismastiko memnou gia prosuiki tainias sto sustima
    public void displayMovieSelectionMenu() {
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setTitle("Movie Selection");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        ComboBox<Movie> movieComboBox = new ComboBox<>(FXCollections.observableArrayList(movies));
        movieComboBox.setPromptText("Select a Movie");

        Label selectedMovieLabel = new Label();

        Button selectMovieButton = new Button("Select Movie");
        selectMovieButton.setOnAction(event -> {
            Movie selectedMovie = movieComboBox.getValue();
            if (selectedMovie != null) {
                selectedMovieLabel.setText(selectedMovie.getMovieInfo());
            } else {
                selectedMovieLabel.setText("Please select a movie.");
            }
        });
        
        Button viewMoreButton = new Button("View More");
        viewMoreButton.setOnAction(event -> {
            Movie selectedMovie = movieComboBox.getValue();
            if (selectedMovie != null) {

                displayMovieAnalytics(selectedMovie);
            }
        });


        gridPane.add(movieComboBox, 0, 0);
        gridPane.add(selectMovieButton, 1, 0);
        gridPane.add(viewMoreButton, 2, 0); 
        gridPane.add(selectedMovieLabel, 0, 1, 3, 1);
        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    
    //menu stoiexiwn tainiwn
    public void displayAddMovieMenu() {
        Stage addMovieStage = new Stage();
        addMovieStage.initModality(Modality.APPLICATION_MODAL);
        addMovieStage.setTitle("Add Movie");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        //Dhmiourgia label kai field gia ta stoixeia twn tainiwn
        Label titleLabel = new Label("Title:");
        TextField titleTextField = new TextField();

        Label durationLabel = new Label("Duration (minutes):");
        TextField durationTextField = new TextField();

        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleTextField, 1, 0);
        gridPane.add(durationLabel, 0, 1);
        gridPane.add(durationTextField, 1, 1);

        //Koumpi epibebaiwshs
        Button addButton = new Button("Add Movie");
        addButton.setOnAction(event -> {
            //Eisagwgh stoixeiwn
            String title = titleTextField.getText();
            int duration = Integer.parseInt(durationTextField.getText());

            //Neo instance tainias
            Movie newMovie = Movie.getInstance(1, title, duration, 2022, "PG", "Description", "poster.jpg", "video.mp4");

            //Emfanish stoixeiwn tainias
            System.out.println(newMovie.getMovieInfo());

            //kleisimo menu
            addMovieStage.close();
        });
        gridPane.add(addButton, 1, 2);

        Scene addMovieScene = new Scene(gridPane, 400, 200);
        addMovieStage.setScene(addMovieScene);
        addMovieStage.showAndWait();
    }

    
    //methodos gia emfanish stoixeiwn tainias
    public void displayMaintenanceMenu() {
        Stage movieMaintenanceStage = new Stage();
        movieMaintenanceStage.initModality(Modality.APPLICATION_MODAL);
        movieMaintenanceStage.setTitle("Movie Maintenance");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        Button selectMovieButton = new Button("Select Movie");
        Button addMovieButton = new Button("Add Movie");

        gridPane.add(selectMovieButton, 0, 0);
        gridPane.add(addMovieButton, 1, 0);

        selectMovieButton.setOnAction(event -> displayMovieSelectionMenu());
        addMovieButton.setOnAction(event -> displayAddMovieMenu());

        Scene movieMaintenanceScene = new Scene(gridPane, 400, 100);
        movieMaintenanceStage.setScene(movieMaintenanceScene);
        movieMaintenanceStage.showAndWait();
    }
    private void displayMovieAnalytics(Movie movie) {

    Stage analyticsStage = new Stage();
    analyticsStage.initModality(Modality.APPLICATION_MODAL);
    analyticsStage.setTitle("Movie Analytics");

    GridPane analyticsGrid = new GridPane();
    analyticsGrid.setAlignment(Pos.CENTER);
    analyticsGrid.setHgap(10);
    analyticsGrid.setVgap(10);
    analyticsGrid.setPadding(new Insets(20, 20, 20, 20));
    
    HBox leftContent = new HBox(20); 
    leftContent.setAlignment(Pos.CENTER_LEFT);

//Labels gia thn emfanish stoixeiwn
    Label titleLabel = createLabelWithStyle("Title: " + movie.getTitle());
    Label runtimeLabel = createLabelWithStyle("Runtime: " + movie.getRuntime() + " minutes");
    Label yearLabel = createLabelWithStyle("Year: " + movie.getYear());
    Label ratingLabel = createLabelWithStyle("Rating: " + movie.getRating());
    Label plotLabel = createLabelWithStyle("Plot: " + movie.getPlot());

    ImageView posterImageView = new ImageView(new Image(getClass().getResourceAsStream(movie.getPosterPath())));//kathorismos path gia to poster
    posterImageView.setFitWidth(200); 
    posterImageView.setPreserveRatio(true);

    analyticsGrid.add(titleLabel, 0, 0, 2, 1);
    analyticsGrid.add(runtimeLabel, 0, 1, 2, 1);
    analyticsGrid.add(yearLabel, 0, 2, 2, 1);
    analyticsGrid.add(ratingLabel, 0, 3, 2, 1);
    analyticsGrid.add(plotLabel, 0, 4, 2, 1);
    analyticsGrid.add(posterImageView, 0, 5, 2, 1);

    Label trailerLabel = createLabelWithStyle("Trailer: " + movie.getVideo());
    analyticsGrid.add(trailerLabel, 0, 6, 2, 1);

    Scene analyticsScene = new Scene(analyticsGrid, 500, 600); 
    analyticsStage.setScene(analyticsScene);
 

    analyticsStage.setScene(analyticsScene);

    //Koumpi gia to trailer
    Button trailerButton = new Button("Watch Trailer");
    trailerButton.setOnAction(e -> {
            movie.showTrailer(); //Kalesma methodou gia to traielr
    });

    analyticsGrid.add(trailerButton, 0, 7, 2, 1);
    analyticsStage.showAndWait();
}

    
    
private Label createLabelWithStyle(String text) {
    Label label = new Label(text);
    label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-text-fill: #333333;");
    return label;
}

    
    //Methodos gia thn lista twn tainiwn
    public List<Movie> getMovies() {
        return movies;
    }
    
    //Dhmiourgia methodou gia ton prokathorismo wrwn pou paizoun oi tainies
    //Etsi wste otan epilegoume tainia na allazoun oi wres me bash ta dedomena pou exoume prosthesei
public ArrayList<String> getAvailableScreeningTimes(String movieTitle) {
    ArrayList<String> availableTimes = new ArrayList<>();


    //gia thn tainai Oppenheimer prosthetoume tis exhs wres:
    if ("Oppenheimer".equals(movieTitle)) {
        availableTimes.add("1030");
        availableTimes.add("1230");
        availableTimes.add("1330");
        availableTimes.add("1700");
        availableTimes.add("2100");
        //Alliws prosthese gia to fightclub
    } else if ("Fight Club".equals(movieTitle)) {
        availableTimes.add("1100");
        availableTimes.add("1300");
        availableTimes.add("1500");
        //Ama theloume mporoume na prosthesoume kai alles tainies
    }

    return availableTimes; //Epistrofh wrwn pou paizoun tainies
}



 
    

}
