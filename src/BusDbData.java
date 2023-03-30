import java.sql.*;

public class BusDbData {
    public void displayBusInfo() {
        String query = "Select * from bus_details";
        Connection con = DbConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()) {
                //System.out.println("Bus No: " + rs.getInt(1));
                //System.out.println("Bus Coach: "+rs.getInt(2));
                //  System.out.println("Capacity: " + rs.getInt(3));
                System.out.println("Bus No ->"+rs.getInt(1)+" "+ "BusRoute  :" +rs.getString(2)  +" "+ " Coach:" + rs.getString(3)  + " "+" Seats Available: " + rs.getInt(4));
            }

            System.out.println("---------------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                assert rs != null;
                rs.close();
                st.close();
            } catch (SQLException e) {
                //throw new RuntimeException(e);
            }
        }




    }

    public int getCapacity(int id)  {
        String query = "Select seat_capacity from bus_details where bus_no=" + id;
        Connection con = DbConnection.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            System.out.println("Total Number Of Seats Available : "+rs.getInt(1));
            return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }finally {
            try {
                assert rs != null;
                rs.close();
                st.close();
            } catch (SQLException e) {
               // throw new RuntimeException(e);
            }
        }


    }
}