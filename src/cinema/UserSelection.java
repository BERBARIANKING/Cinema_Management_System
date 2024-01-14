//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;

import java.util.concurrent.atomic.AtomicInteger;
 

//Klasi gia apothikeusi kai probli dedomenons
class UserSelection {
    private static final AtomicInteger ID_COUNTER = new AtomicInteger(1); 

    private  int id;
    private String movieTitle;
    private String movieDate;
    private int userTime;
    private String userType;
    private String userStatus;
    
    
   private String RowInitial;
    private int seatNo;
   // private Sring seatRow;
 
   //  UserSelection userSelection = new UserSelection(userMovie, userDate, userTime, userType, userStatus, seatRow, seatNo);

//dhmiourgia constructor
    public UserSelection(/*int id ,*/ String movieTitle, String movieDate, int userTime, String userType, String userStatus , String RowInitial , int seatNo) {
       //this.id = ID_COUNTER.getAndIncrement();
        this.movieTitle = movieTitle;
        this.movieDate = movieDate;
        this.userTime = userTime;
        this.userType = userType;
        this.userStatus = userStatus;
        this.RowInitial = RowInitial;
        this.seatNo = seatNo;
        
    }
    
    //constructor gia thn apofugh lathous (apo to ide)
    UserSelection(String hallType, String string, int seatNo, String seatQuality, boolean trayTable, int price) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //methodoi gia ta stoixeia twn tainiwn kai thesewn
        public String getMovieTitle() {
            return movieTitle;
        }

        public String getMovieDate() {
            return movieDate;
        }

        public int getUserTime() {
            return userTime;
        }

        public String getUserType() {
            return userType;
        }

        public String getUserStatus() {
            return userStatus;
        }
        
          public int getId() {
        return id;
    }
          
          public String getRowInitial()
          {
              return RowInitial;
          }
          public int getSeatNo(){
              return seatNo;
          }
        
        public String getSeatRow()
        {
            return RowInitial;
        }
       
        
        //toString gia thn emfanish stoixeiwn
        
      @Override
    public String toString() {
        return "ID: " + id +
                ", Movie Title: " + movieTitle +
                ", Movie Date: " + movieDate +
                ", Movie Time: " + userTime +
                ", Hall Type: " + userType +
                ", Status: " + userStatus+
                ", Seat Row: "+ RowInitial +
                ", Seat No: "+ seatNo;
                
    }
}