import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;

public class BookingDbData extends Thread {
   // int seatsBooked;
    public int getBookedCount(int busNo, Date date) {
        String query = "SELECT COUNT(*)\n" +
                "FROM passenger_bookings\n" +
                "WHERE bus_no = ? AND journey_date = ?;";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(query);
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
            pst.setInt(1, busNo);
            pst.setString(2, dateFormat1.format(sqldate));
            rs = pst.executeQuery();
            rs.next();
            System.out.println("Number of Seats Booked : " + rs.getInt(1));
           // seatsBooked = rs.getInt(1);
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert pst != null;
                pst.close();
                con.close();
            } catch (SQLException e) {
               // throw new RuntimeException(e);
            }
        }

    }

    public void addBooking(PassengerBooking booking)  {
        String query = "Insert into passenger_bookings (bus_no, passenger_name, journey_date ) values(?,?,?)";
        Connection con = DbConnection.getConnection();
        // java.sql.Date sqldate = new java.sql.Date(booking.date.getTime());
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(query);
            pst.setString(2, booking.passengerName);
            pst.setInt(1, booking.userBusNo);
            java.sql.Date dateFormatted = new java.sql.Date(booking.date.getTime());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            pst.setString(3,dateFormat.format(dateFormatted) );
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert pst != null;
                pst.close();
                con.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
            }
        }



    }

    public void run() {
        BookingDbData book = new BookingDbData();
        PassengerBooking book2 = new PassengerBooking();
        try {
            book.addBooking(book2);
            book.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


