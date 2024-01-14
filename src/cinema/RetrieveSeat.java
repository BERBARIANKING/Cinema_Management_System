//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class RetrieveSeat {

   private boolean[][] reservedSeats;
    private boolean[][] seats;
    private ArrayList<UserSelection> userSelections;

    public RetrieveSeat(boolean[][] reservedSeats) {
        this.reservedSeats = reservedSeats;
        this.seats = new boolean[5][30]; //Dhlwsh pinaka gia tis theseis
    }


    // Other methods...

    public void markReservedSeats() {
        if (userSelections == null || reservedSeats == null) {
            // Ensure userSelections and reservedSeats are initialized
            return;
        }

        for (UserSelection userSelection : userSelections) {
            int row = getRowFromUserSelection(userSelection);
            int seatNo = getSeatNoFromUserSelection(userSelection);

            if (row >= 0 && row < reservedSeats.length && seatNo >= 0 && seatNo < reservedSeats[row].length) {
                reservedSeats[row][seatNo] = true;
            }
        }
    }

    

    
private void markSeatAsReserved(int row, int seatNo) {
    if (row >= 0 && row < seats.length && seatNo >= 0 && seatNo < seats[row].length) {
        seats[row][seatNo] = true;
    }
}
       
 //emfanisi diamorfosi theseon 
 public void showVisualizer(ArrayList<UserSelection> userSelections) {
    // Call markReservedSeats to update the reserved seats
    this.userSelections = userSelections;
    markReservedSeats();
    Stage seatVisualizerStage = new Stage();
    seatVisualizerStage.setTitle("Seat Visualizer");

    GridPane grid = new GridPane();
    grid.setHgap(5);
    grid.setVgap(5);
    grid.setPadding(new Insets(10));
    

    // Create labels for seat rows (A-E)
    for (int i = 0; i < 5; i++) {
        Label label = new Label("Row " + (char) ('A' + i));
        grid.add(label, 0, i + 1);
    }

    // Create labels for seat numbers (1-30)
    for (int j = 0; j < 30; j++) {
        Label label = new Label(Integer.toString(j + 1));
        grid.add(label, j + 1, 0);
    }

        //allagi xromatos gia klismenes theseis 
    for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 30; j++) {
            Rectangle seat = new Rectangle(20, 20);

          
        if (reservedSeats[i][j]) {
            seat.setFill(Color.RED); //Kleismenes theseis exoun xrwma kokkino
        } else {
            seat.setFill(seats[i][j] ? Color.RED : Color.GREEN); //Epishs prosfata kleismenes theseis exoun xrwma kokkino
        }

        grid.add(seat, j + 1, i + 1);
        }
    }

    Scene scene = new Scene(grid, 830, 200);
    seatVisualizerStage.setScene(scene);
    seatVisualizerStage.show();
}

 //sinartisi gi aenimerosi eisitiriou (den einaai litourgiko)
public void updateSeats(UserSelection userSelection) {
    int row = getRowFromUserSelection(userSelection);
    int seatNo = getSeatNoFromUserSelection(userSelection);

    if (row >= 0 && row < seats.length && seatNo >= 0 && seatNo < seats[row].length) {
        seats[row][seatNo] = true;
    }
}

  //sinartisi anagnorisie san einai poiasmeni i thesi
        private boolean isSeatReserved(ArrayList<UserSelection> userSelections, char rowInitial, int seatNo) {
        for (UserSelection userSelection : userSelections) {
            if (userSelection.getRowInitial().charAt(0) == rowInitial && userSelection.getSeatNo() == seatNo) {
                return true;
            }
        }
        return false;
    }

        //methodos gia enhmerwsh krathmenwn thesewn
    public void updateReservedSeats(UserSelection userSelection) {
        
        int row = getRowFromUserSelection(userSelection);
        int seatNo = getSeatNoFromUserSelection(userSelection);
        if (row >= 0 && row < reservedSeats.length && seatNo >= 0 && seatNo < reservedSeats[row].length) {
            reservedSeats[row][seatNo] = true;
        }
    }
    
    private int getRowFromUserSelection(UserSelection userSelection) {
        char rowInitial = userSelection.getRowInitial().charAt(0);
        return rowInitial - 'A';
    }

    private int getSeatNoFromUserSelection(UserSelection userSelection) {
        return userSelection.getSeatNo() - 1;
    }


    
}
