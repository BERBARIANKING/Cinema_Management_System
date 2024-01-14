//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;



//dhmiourgia interface gia prosthkh kai afairesh thesshs
interface SeatInterface{
     void addSeat();
     void removeSeat();
}

public class Seat /*implements SeatInterface*/{
    
    //Dhmiourgia listas gia tis theseis
        private static List<Seat> seatList = new ArrayList<>();

    //dhlwsh dedomenwn gia tis theseis
    private String hallType;//typos aithousasa
    private String rowInitial;//seira
    private int seatNo;//arithmos theshs
    private String seatQuality;//typos theshs (poiothta)
    private boolean trayTable;//an tha exei trapezaki (mellontikh ulopoihsh gia therino)
    private int price;//timh theshs
    private String f_hallType;
   private String f_rowInitial;
    private int f_seatNo;
    private String availability;
private String userType; //metablhth gia thn sundesh me to session
    public static String temp_rowInitial = "A";
    public static int temp_seatNo = 1;

    
    //kyrios constructor
    public Seat(String hallType, String rowInitial, int seatNo, String seatQuality, boolean trayTable, int price, String availability){
        this.hallType = hallType;
        this.rowInitial = rowInitial;
        this.seatNo = seatNo;
        this.seatQuality = seatQuality;
        this.trayTable = trayTable;
        this.price = price;
        this.availability = availability;
    }
    

              //deutereuon constructor
    public Seat(String rowInitial, int seatNo){
     
        this.rowInitial = rowInitial;
        this.seatNo = seatNo;
        
   

    }
    
  
    //Default constructor
    public Seat(){
        this.hallType = "Normal";
        this.rowInitial = "A";
        this.seatNo = 1;
        this.seatQuality = "Normal";
        this.trayTable = false;
        this.price = 10;
        this.availability = "Available";
    }
    

    public void setUserType(String userType) {
    this.userType = userType;
}

    
 //mellontikh ulopoihsh
public void showSeatingMaintenanceMenu(Stage primaryStage, ArrayList<UserSelection> userSelections) {
    Stage seatingMaintenanceStage = new Stage();
    seatingMaintenanceStage.setTitle("Seating Maintenance");

    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(20));
    gridPane.setVgap(10);
    gridPane.setHgap(10);



    Label rowInitialLabel = new Label("Row Initial:");
    TextField rowInitialTextField = new TextField();

    Label seatNoLabel = new Label("Seat Number:");
    TextField seatNoTextField = new TextField();
    Button addSeatButton = new Button("Add Seat");

    gridPane.add(rowInitialLabel, 0, 1);
    gridPane.add(rowInitialTextField, 1, 1);
    gridPane.add(seatNoLabel, 0, 2);
    gridPane.add(seatNoTextField, 1, 2);

    gridPane.add(addSeatButton, 0, 7, 2, 1);

    addSeatButton.setOnAction(event -> {
 
        // these typo aithousas me bash thn epilogh tou xrrhsth
        if (!userSelections.isEmpty()) {
            UserSelection userSelection = userSelections.get(0);
            setUserType(userSelection.getUserType());
        }

        //Dhmiourgia intance gia thn klash Seat
        Seat seat = new Seat(rowInitial, seatNo);
        seat.setUserType(userType);

        //Kalesma methodou apo thn  klash Thesh
        seat.addSeat();

        //Emfanish thesewn
        System.out.println("All Seats:");
        for (Seat s : getSeatList()) {
            System.out.println(s.toString());
        }

        UserSelection seatChoice = new UserSelection(seat.getHallType(), seat.getRowInitial(), seat.getSeatNo(), seat.getSeatQuality(), seat.isTrayTable(), seat.getPrice());
        userSelections.add(seatChoice);
        seatingMaintenanceStage.close();

    
        showSeatingMaintenanceMenu(primaryStage, userSelections);
    });

    Scene seatingScene = new Scene(gridPane);
    seatingMaintenanceStage.setScene(seatingScene);
    seatingMaintenanceStage.show();
}


  
   
           // Methodos prosthklhs theshs
    public void addSeat() {
 
 
//elegxos gia ton tupo aithousas
           if ("2D".equals(userType)) {
        setAvailability("Unavailable");
    }
           else if ("IMAX".equals(userType)) {
        setAvailability("Unavailable");
    }
           else if ("INDULGE".equals(userType)) {
        setAvailability("Unavailable");
    }
           else   if ("LUXE".equals(userType)) {
        setAvailability("Unavailable");
    }
             else if ("SUPREME".equals(userType)) {
        setAvailability("Unavailable");
    }
              else   if ("PREMIUM".equals(userType)) {
        setAvailability("Unavailable");
    }else  setAvailability("Unavailable");
           
           //prosthhkh theshs
        seatList.add(this);
    }
    
      //Epistrofh listas thesewn
    public static List<Seat> getSeatList() {
        return seatList;
    }
  
    //methodoi gia tis plhrofories ths theshs
    	public String getHallType() {

		return hallType;
	}

	public void setHallType(String hallType) {
		this.hallType = hallType;
	}



	public void setRowInitial(String rowInitial) {
		this.rowInitial = rowInitial;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public String getF_hallType() {
		return f_hallType;
	}

	public void setF_hallType(String f_hallType) {
		this.f_hallType = f_hallType;
	}

	public String getF_rowInitial() {
		return f_rowInitial;
	}

	public void setF_rowInitial(String f_rowInitial) {
		this.f_rowInitial = f_rowInitial;
	}

	public int getF_seatNo() {
		return f_seatNo;
	}

	public void setF_seatNo(int f_seatNo) {
		this.f_seatNo = f_seatNo;
	}

	public String getSeatQuality() {
		return seatQuality;
	}

	public void setSeatQuality(String seatQuality) {
		this.seatQuality = seatQuality;
	}

	public boolean isTrayTable() {
		return trayTable;
	}

	public void setTrayTable(boolean trayTable) {
		this.trayTable = trayTable;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getAvailability() {
		return availability;

	}
	public void setAvailability(String availability) {
		this.availability = availability;

	}

        //methodoi epistrofhs plhroforiwbn theshs gia to grafiko periballon

    public String getRowInitial() {
        return this.rowInitial;
    }
    public int getSeatNo() {
        return this.seatNo;
    }
    
  
  
  
}



    
