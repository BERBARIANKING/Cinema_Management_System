//Nikolaos-Christos Zacharias icsd20062
//Nikolaos Bermparis icsd20146

package cinema;
import java.util.ArrayList;


public class Session {
	String title;
	int timeStart ; //In minutes form
	String type;
	String Status;
	String Date;
	final int duration = 240;
	int timeEnd = timeStart+duration;
        
        
        private ArrayList<Session> sessionList;
        
        
            public Session(String title, String date, int timeStart, String type, String status) {
            this.title = title;
            this.Date = date;
            this.timeStart = timeStart;
            this.type = type;
            this.Status = status;
        }
	
	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String Status) {
		this.Status = Status;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String Date) {
		this.Date = Date;
	}

        /*
	public Session(String title, String Date,int timeStart, String type, String Status) {
		this.title = title;
		this.Date = Date;
		this.timeStart = timeStart;
		this.type = type;
		this.Status = Status;

	}


	public Session(String title, String Date, String timeStart) {
		this.title = title;
		this.Date = Date;
		this.timeStart = Integer.parseInt(timeStart);
		
	}
        */
        
        
        
    //---------------------------------TEST SECTION FOR THE SESSION CREATION (FINAL TRY CATCH)---------------------    
   
        /*
        
        
        	public boolean addNewSession(Session session){
		boolean success = true;

		String sql = "INSERT INTO `Session`(`Title`,`Date`, `TimeStart`, `Type`, `Status`)VALUES('"+session.getTitle()+"','"+session.getDate()+"','"+session.getTimeStart()+"','"
				+session.getType()+"','"+session.getStatus()+"')";
	
		try{
			int num = statement.executeUpdate(sql);
			if(num<1)
				success = false;
		}catch(Exception e){
			success = false;
			
		}
		return success;
	}
        

        
                
                    private boolean insertSessionData(Session session) {
        
        sessionList.add(session);
        return true;  
    }
                    
       */             

 //---------------------------------END OF TEST SECTION FOR THE SESSION CREATION (FINAL TRY CATCH)--------------------- 
                    
                    
	@Override
	public String toString() {
		return "Session{" + "title=" + title + ", timeStart=" + timeStart + ", type=" + type + ", Status=" + Status + ", Date=" + Date + '}'+"\n";
	}








}