//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


//klash Ticket gia thn emfanish eisithriou krathshs ston xrhsth kai emfanish telikhs timhs
public class Ticket  {
    //dhlwsh metabvlhtwn opws titlos tainias , hmeromhnias , wras , typou eithousas , seira kai arithmos theshs 
    private String movieTitle;
    private String date;
    private int time;
    private String hallType;
    private String seatRow;
    private int seatNumber;
    
    //mnetablhth telikhs timhs krathshs 
    private double totalPrice;
    
    
    //constructor main Ticket
    public Ticket(String movieTitle, String date, int time, String hallType, String seatRow, int seatNumber) {
        this.movieTitle = movieTitle;
        this.date = date;
        this.time = time;
        this.hallType = hallType;
        this.seatRow = seatRow;
        this.seatNumber = seatNumber; 
       
        //kalsesma methodou upologismou timis
        this.totalPrice = calculatePrice();
    }

         
    
    //methodos upologismou timis analoga me to eidos eisitiriou
    private double calculatePrice() {
        double basePrice = 10.0; //basiki timi

        //Auksisi timis analoga me tin epilogi eisitiriou
        switch (hallType) {
            case "2D":         
                break;
            case "IMAX":
                basePrice += 5.0; 
                break;
            case "INDULGE":
                basePrice += 8.0; 
                break;
            case "LUXE":
                basePrice += 10.0; 
                break;
            case "SUPREME":
                basePrice += 12.0; 
                break;
            case "PREMIUM":
                basePrice += 15.0; 
                break;
        }


        return basePrice;
    }
    
    //methodos emfanishs eishthriou
  public void showTicketMenu()
  {
      Stage stage = new Stage();
   
        Label userSelectionsLabel = new Label("User Selections:\n" + this.toString());
        Label totalPriceLabel = new Label("Total Price: $" + this.getTotalPrice());

        
        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(userSelectionsLabel, totalPriceLabel);

      
        Scene scene = new Scene(vBox, 400, 300);

      
        stage.setTitle("Ticket Details");
        stage.setScene(scene);

       
        stage.show();
        

  }

//methodos dedomenwn gia to eisithrio
    public String getMovieTitle() {
        return movieTitle;
    }

    public String getDate() {
        return date;
    }

    public int getTime() {
        return time;
    }

    public String getHallType() {
        return hallType;
    }

    public String getSeatRow() {
        return seatRow;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    
    //methodos toString gia thn emfanish twn dedomenwn
    @Override
    public String toString() {
        return "Movie: " + movieTitle + "\n" +
                "Date: " + date + "\n" +
                "Time: " + time + "\n" +
                "Hall Type: " + hallType + "\n" +
                "Seat: " + seatRow + seatNumber + "\n" +
                "Total Price: $" + totalPrice;
    }
  
    

}