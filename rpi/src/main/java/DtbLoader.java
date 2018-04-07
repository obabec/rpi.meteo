import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;

public class DtbLoader {

    public void loadAndPush(Double temp,Double hum){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            Connection connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/testDtb?"
                            + "user=root&password=95123");
            String query = " insert into tableTest (temp,hum,dates)"
                    + " values (?,?,?)";

            PreparedStatement preparedStmt = connect.prepareStatement(query);
            preparedStmt.setDouble(1,temp);
            preparedStmt.setDouble(2,hum);
            preparedStmt.setTimestamp(3,currentDate());

            preparedStmt.execute();
            connect.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
    private Timestamp currentDate(){

        long currentTime = Calendar.getInstance().getTimeInMillis();
        Timestamp ts = new Timestamp(currentTime);

        return ts;
    }


}
