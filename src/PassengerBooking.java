import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PassengerBooking {
    String passengerName;

    int userBusNo;
    Date date;
    short inputSeat;
    boolean validInput;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public Scanner input = new Scanner(System.in);

    public void selectBusNo() {
        validInput = false;
        do {
            System.out.println("Select Bus Number[1 To 5] : ");
            userBusNo = input.nextInt();

            try {
                if (userBusNo >= 1 && userBusNo <= 5) {
                    validInput = true;
                }
            } catch (InputMismatchException mismatch) {
                System.out.println("Incorrect Input Format Try Again....");
                selectBusNo();
            }
        }
        while (!validInput);
    }
    public void passengerName() {
        System.out.println("Enter Passenger Name : ");
        input.nextLine();
        this.passengerName = input.nextLine().toUpperCase();
    }

    public void selectDate() {
        System.out.println("Enter Date of journey as : DD-MM-YYYY");
        String dateInput = input.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(dateInput);
        } catch (ParseException e) {
            System.out.println("Invalid Date Format TryAgain....");
            selectDate();
        }
    }

    public void selectSeat() {
        System.out.println("Enter Number of Seats To Book : ");
        inputSeat = input.nextShort();
        System.out.println();
    }

    public void detailsConfirmation() {

        System.out.println("Confirm You Details");
        System.out.println("~~~~~~~~~~~~~~~~~~~");
        System.out.println(" NAME                       : "+passengerName);
        System.out.println(" DATE OF JOURNEY            : "+dateFormat.format(date));
        System.out.println(" BUS NO (verify for routes) : "+userBusNo);
        System.out.println(" NUMBER OF SEATS            : 1");
        System.out.println("\n\nPlease Wait to Confirm your Booking.....");
    }

    public boolean isAvailable() throws SQLException {
        BusDbData busData = new BusDbData();
        BookingDbData bookingData = new BookingDbData();
        int capacity = busData.getCapacity(userBusNo);
        int booked = bookingData.getBookedCount(userBusNo,date);
        //int result1 = bookingData.seatsBooked;
        //System.out.println("Available Seats to Book : "+ capacity);
        //System.out.println("booked seat in isAvailable Method :"+ result1);
        return booked<capacity;
    }








}
