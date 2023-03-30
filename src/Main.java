import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("WELCOME TO P.P.R BUS BOOKING");
        System.out.println("****************************");
        BusDbData busData = new BusDbData();
        try {
            busData.displayBusInfo();
            String userInput = "y";
            Scanner input = new Scanner(System.in);
            while (userInput.equalsIgnoreCase("y")) {
                System.out.println("Press \"Y\" to Booking \"N\" to Exit");
                userInput = input.next();
                if (userInput.equalsIgnoreCase("Y")) {
                    PassengerBooking booking = new PassengerBooking();
                    booking.selectBusNo();
                    booking.passengerName();
                    booking.selectDate();
                    // booking.selectSeat();
                    booking.detailsConfirmation();
                    if (booking.isAvailable()) {
                        BookingDbData bookingdao = new BookingDbData();
                        bookingdao.addBooking(booking);
                        System.out.println("Your booking is confirmed");
                    } else
                        System.out.println("Sorry. Bus is full. Try another bus or date.");
                } else if (userInput.equalsIgnoreCase("n")) {
                    System.out.println("Thank You Visit Again...");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
