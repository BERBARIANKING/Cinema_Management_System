//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javafx.stage.Stage;

public class Movie {
	//Dhlwsh basikwn dedomenwn tainias
	private int movieId;
	private String title;
	private int runtime;
	private int year;
	private String rating;
	private String plot;
	private String poster;
	private String video;
        
        private ArrayList<String> screeningDates;
        private ArrayList<String> screeningTimes;
        private ArrayList<String> hallTypes;

        //dhlwsh constructor me basikes parametrous gia thn tainia
	Movie(int movieId, String title, int runtime, int year, String rating, String plot, String poster,
			String video ) {
		this.movieId = movieId;
		this.title = title;
		this.runtime = runtime;
		this.year = year;
		this.rating = rating;
		this.plot = plot;
		this.poster = poster;
		this.video = video;
               
	}
        
        
        	//Dhlwsh deuterevontois constructor gia to menu ths krathshs
	Movie(int movieId, String title, int runtime, int year, String rating, String plot, String poster,
			String video ,  ArrayList<String> screeningDates ,   ArrayList<String> screeningTimes,ArrayList<String> hallTypes) {
		this.movieId = movieId;
		this.title = title;
		this.runtime = runtime;
		this.year = year;
		this.rating = rating;
		this.plot = plot;
		this.poster = poster;
		this.video = video;
                
                 this.screeningDates = screeningDates;
                 this.screeningTimes = screeningTimes;
                 this.hallTypes = hallTypes;
	}

	//Dhlwsh statikhs metablhths gia thn apothhkeush instance ths klashs
	private static Movie instance;

	// Methodos gia to instance ths klashs
	public static Movie getInstance(int movieId, String title, int runtime, int year, String rating, String plot, String poster,
			String video) {
		//An to instance einai null dhmiourghse neo constructor
		if (instance == null) {
			instance = new Movie(movieId, title, runtime, year, rating, plot, poster, video );
		}
		//epistrofh instance
		return instance;
	}
        

//Methodoi gia na paroume ta dedomena ths tainias
  public int getRuntime() {
        return runtime;
    }
  
    public String getTitle() {
        return title;
    }
        
      public int getYear() {
        return year;
    }
        
        public String getRating() {
        return rating;
    }
        
          public String getPlot() {
        return plot;
    }
        
        
	//Methodos gia thn dhmiourgia trailer(mellontika)
	public void playVideo() {

	}

        //methodos emfanishs poster
	public void showPoster() {
 //emfanisi tis afisas tis tainias
        Stage posterStage = new Stage();
        posterStage.setTitle("Movie Poster");
        ImageView posterImageView = new ImageView(new Image(getClass().getResourceAsStream("image/" + poster)));//kathorismos pathing gia thn eikona
        posterImageView.setFitWidth(400); 
        posterImageView.setPreserveRatio(true);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(posterImageView);

        Scene scene = new Scene(stackPane, 400, 600); 
        posterStage.setScene(scene);
        posterStage.show();

	}
        
           public String getPosterPath() {
        return "image/" + poster;
    }
           
               public String getVideo() {
        return video;
    }
               
              
               public void showTrailer() {//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/media/MediaView.html

        Stage trailerStage = new Stage();
        trailerStage.setTitle("Movie Trailer");

 
        Media media = new Media(getClass().getResource("video/" + video).toString());

  
        MediaPlayer mediaPlayer = new MediaPlayer(media);

    
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(600); 
        mediaView.setPreserveRatio(true);


        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(mediaView);


        Scene scene = new Scene(stackPane, 600, 400);
        trailerStage.setScene(scene);


        trailerStage.show();


        mediaPlayer.play();
    }


           

    
//Methodoi emfanishs leptomereies probolhs
    public ArrayList<String> getScreeningDates() {
        return screeningDates;
    }

    public ArrayList<String> getScreeningTimes() {
        return screeningTimes;
    }

    public ArrayList<String> getHallTypes() {
        return hallTypes;
    }

    //Methodoi emfanishs leptomereies probolhs
    public void setScreeningDates(ArrayList<String> screeningDates) {
        this.screeningDates = screeningDates;
    }

    public void setScreeningTimes(ArrayList<String> screeningTimes) {
        this.screeningTimes = screeningTimes;
    }

    public void setHallTypes(ArrayList<String> hallTypes) {
        this.hallTypes = hallTypes;
    }
    
        

	public String getMovieInfo() {
		//Epistrofh string me ton titlo tainias
		return  title +"\n";
	}

	//Override ths methodou tostring gia thn epistrofh stoixeiwn tainias
	@Override
	public String toString() {
		return getMovieInfo();
	}

}

