//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;


//klash party gia tis krathseis twn party tou poluxwrou
public class Party {
    private static ArrayList<Party> partyList = new ArrayList<>();
//dhlwsh mewtablhtwn gia thn krathsh tou party
    //onoma,hmeromhnia,arithmosatomwn,eidiko menu,koph tourtas , clown kai onoma party
    private String eventName;
    private Date eventDate;
    private int numberOfChildren;
    private String specialMenu;
    private boolean cakeCutting;
    private boolean clownOrAnimator;
    private String partyName;

    //timh krathshs party
    private double eventPrice;
//constructor main gia thn Party
   public Party(String eventName, Date eventDate, int numberOfChildren, String specialMenu,
             boolean cakeCutting, boolean clownOrAnimator, String partyName) {
   
    this.eventName = eventName;
    this.eventDate = eventDate;
    this.numberOfChildren = numberOfChildren;
    this.specialMenu = specialMenu;
    this.cakeCutting = cakeCutting;
    this.clownOrAnimator = clownOrAnimator;
    this.partyName = partyName;
    calculateEventPrice();

        partyList.add(this);
    }
    // Default times gia ta xaraktiristika tou party
    public Party() {
       
        this.eventName = "Default Event";
        this.eventDate = new Date();
        this.numberOfChildren = 0;
        this.specialMenu = "No special Menu";
        this.cakeCutting = false;
        this.clownOrAnimator = false;
        this.partyName = "Default Party Name";
        
        //teliko kostos tou party
        calculateEventPrice();
        
        //prosthiki tou antikimenou stin lista
        partyList.add(this);
    }



    public String getPartyName() {
        return partyName;
    }
  
    public void displayPartyMaintenance() {
            Stage partyStage = new Stage();
    StackPane root = new StackPane();

    Label titleLabel = new Label("Party Maintenance Window");
    CheckBox cakeCheckBox = new CheckBox("Include Cake Cutting");

    //Label gia gia epilogi plithos atomo pou tha parebrethoun 
    //Einai 5 katigories gia to plithos atomon me antistixes times to kathena
    Label numberOfPeopleLabel = new Label("Number of People:");
    ObservableList<Integer> options = FXCollections.observableArrayList(10, 20, 30, 40, 50);
    ComboBox<Integer> numberOfPeopleComboBox = new ComboBox<>(options);
    numberOfPeopleComboBox.setValue(options.get(0)); 

      //Geniki epilogi gia special menou (yes  no) 
      CheckBox specialMenuCheckBox = new CheckBox("Special Menu");

        //sigkekrimeni epilogi specia menou 
         VBox specialMenuVBox = new VBox(10);
        Label specialMenuLabel = new Label("Special Menu Choices:");

        ToggleGroup foodToggleGroup = new ToggleGroup();

        RadioButton pizzaRadioButton = new RadioButton("Pizza");
        pizzaRadioButton.setToggleGroup(foodToggleGroup);

        RadioButton sandwichesRadioButton = new RadioButton("Sandwiches");
        sandwichesRadioButton.setToggleGroup(foodToggleGroup);

        RadioButton snacksRadioButton = new RadioButton("Snacks");
        snacksRadioButton.setToggleGroup(foodToggleGroup);

        specialMenuVBox.getChildren().addAll(specialMenuLabel, pizzaRadioButton, sandwichesRadioButton, snacksRadioButton);


        
        CheckBox clownAnimatorCheckBox = new CheckBox("Clown or Animator");

        //Onoma krsatiseis Party
        Label nameLabel = new Label("Party Name:");
        TextField nameTextField = new TextField();    



//
    Label dateLabel = new Label("Choose Date:");
    DatePicker datePicker = new DatePicker();
    datePicker.setValue(LocalDate.now()); //orismos defolt imerominias tin simerini 
    //https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html

    //Koumpi gia emfanisi kratiseon 
    Button showListButton = new Button("Show Party List");
    showListButton.setOnAction(event -> showPartyList(null));
    
    

    
    Button confirmButton = new Button("Confirm");
    confirmButton.setOnAction(event -> {

 Party newParty;
Party latestParty;

  try {
        if (partyList.isEmpty()) {
            latestParty = new Party();
        } else {
            latestParty = partyList.get(partyList.size() - 1);
        }

        newParty = new Party(
                latestParty.getEventName(),
                Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),  
                numberOfPeopleComboBox.getValue(), 
                specialMenuCheckBox.isSelected() ? getSelectedFoodChoice(foodToggleGroup) : "No Special Menu",
                cakeCheckBox.isSelected(),  
                clownAnimatorCheckBox.isSelected(),  
                nameTextField.getText()  
        );

        newParty.calculateEventPrice();
      
       // showPartyList(null);
         // emfanisi parathirou me tin teliki timi tis kratisis
    newParty.calculateEventPrice();

    //emfanisis tis listas kartiseon 
    showPartyList(null);

    newParty.confirmPartyBooking();

        partyStage.close();

    } catch (Exception e) {
        if (e instanceof DateTimeException) {           
            e.printStackTrace();          
        } else {       
            e.printStackTrace();
        }
    }
  
  

    });


    FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10);
     flowPane.getChildren().addAll(
                titleLabel, cakeCheckBox, numberOfPeopleLabel, numberOfPeopleComboBox,
                specialMenuCheckBox, specialMenuVBox, clownAnimatorCheckBox, nameLabel, nameTextField,
                dateLabel, datePicker, confirmButton, showListButton
        );
    flowPane.setPadding(new Insets(10));

    root.getChildren().add(flowPane);

    Scene scene = new Scene(root, 600, 300);
    partyStage.setScene(scene);
    partyStage.setTitle("Party Maintenance");
    partyStage.show();
    }

    //Methodos gia na epilakse fagito sto special menou
    private String getSelectedFoodChoice(ToggleGroup toggleGroup) {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if (selectedRadioButton != null) {
            return selectedRadioButton.getText();
        } else {
            return "No Special Menu";
        }
    }
    //Sunartisi emfaniseis tis listas karatiseon
    private void showPartyList(Stage parentStage) {
     Stage partyListStage = new Stage();
    StackPane root = new StackPane();

  
    Label partyListLabel = new Label("List of Created Parties:");
    ListView<String> listView = new ListView<>();

    listView.getItems().clear();

   
for (Party party : partyList) {

    String formattedDate = party.getEventDate().toString();
    
  
  String partyInfo = String.format("%s - Children: %d - Special Menu: %s - Cake Cutting: %s - Clown/Animator: %s - Date: %s - Party Name: %s - Price: $%.2f",
        party.getEventName(), party.getNumberOfChildren(), party.isSpecialMenu(), party.isCakeCutting() ? "Yes" : "No",
        party.isClownOrAnimator() ? "Yes" : "No", formattedDate, party.getPartyName(), party.getEventPrice());

    listView.getItems().add(partyInfo);
}
   //Koumpi diagrafis engrafis llistas
Button deleteReservationButton = new Button("Delete Reservation");
deleteReservationButton.setOnAction(event -> {
    int selectedIndex = listView.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Party selectedParty = partyList.get(selectedIndex);

        //elenxos tou periorismou gia tin diagrafi engrafis
        LocalDate currentDate = LocalDate.now();
        LocalDate partyDate = selectedParty.getEventDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long daysUntilParty = ChronoUnit.DAYS.between(currentDate, partyDate);

        if (daysUntilParty <= 1) {
            //Minima error an i engrafi den mpori na diagraftei
            Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot delete reservation. Party is within a day from the current date.", ButtonType.OK);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.showAndWait();
        } else {
            //Diagrafi tis engrafis
            partyList.remove(selectedIndex);
            showPartyList(null);
        }
    }
    });

    // koumpi gia enimerosi engrafis (den kanei kapoia allagi sti lista)
    Button updatePartyButton = new Button("Update Party Details");
    updatePartyButton.setOnAction(event -> {
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Party selectedParty = partyList.get(selectedIndex);

            //Periorisomos gia na gini enimerosi 
            LocalDate currentDate = LocalDate.now();
            LocalDate partyDate = selectedParty.getEventDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long daysUntilParty = ChronoUnit.DAYS.between(currentDate, partyDate);

            if (daysUntilParty <= 1) {
                //emfanisi minimatos error an den plirite o periorismos
                Alert alert = new Alert(Alert.AlertType.ERROR, "Cannot update party details. Party is within a day from the current date.", ButtonType.OK);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.showAndWait();
            } else {               
                updatePartyDetails(selectedParty);
            }
        }
});


        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(partyListLabel, listView, deleteReservationButton, updatePartyButton);
        vbox.setPadding(new Insets(10));

    root.getChildren().add(vbox);

    Scene scene = new Scene(root, 500, 300);
    partyListStage.setScene(scene);
    partyListStage.setTitle("Party List");
    
    
    if (parentStage != null) {
        partyListStage.setX(parentStage.getX() + 50);
        partyListStage.setY(parentStage.getY() + 50);
    }

    partyListStage.initModality(Modality.WINDOW_MODAL);
    partyListStage.show();
        // Create and show the scene
       // Scene scene = new Scene(root, 300, 200);
       // partyListStage.setScene(scene);
        //partyListStage.setTitle("Party List");
        //partyListStage.initOwner(parentStage);
       // partyListStage.initModality(Modality.WINDOW_MODAL);
        //partyListStage.show();
    }

        private void calculateEventPrice() {
    //basiki timi tou party
    double basePrice = 100.0;

    // extra timi ana atomo
    double pricePerPerson = 5.0;

    //Upologismos telikis timis
    eventPrice = basePrice + (numberOfChildren * pricePerPerson);

    
}
        //methodos enhmerwshs party
        private void updatePartyDetails(Party partyToUpdate) {
    
    System.out.println("Updating details for party: " + partyToUpdate.getPartyName());
}
        
        //methodos gia ton kathorismo arithmou atomwn sto party
public void setNumberOfChildren(int numberOfChildren) {
    this.numberOfChildren = numberOfChildren;
}
   
    public String getEventName() {
        return eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public String isSpecialMenu() {
        return specialMenu;
    }

    public boolean isCakeCutting() {
        return cakeCutting;
    }

    public boolean isClownOrAnimator() {
        return clownOrAnimator;
    }

    public double getEventPrice() {
        return eventPrice;
    }

    public static ArrayList<Party> getPartyList() {
        return partyList;
    }
    

    
    
    //Emfanisi parathirou gia tin xreosi
    private void displayFinalPrice() {
        Stage priceStage = new Stage();
        VBox root = new VBox();

        
        Label priceLabel = new Label("Final Price: $" + getEventPrice());

       
        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> priceStage.close());

      
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(priceLabel, closeButton);
        vbox.setPadding(new Insets(10));

    
        root.getChildren().add(vbox);

     
        Scene scene = new Scene(root, 300, 150);
        priceStage.setScene(scene);
        priceStage.setTitle("Final Price");
        priceStage.show();
    }
    
       //methodos epibebaiwshs krathshs party
    public void confirmPartyBooking() {
     
        calculateEventPrice();
      
        displayFinalPrice();
    }
    
  
}
