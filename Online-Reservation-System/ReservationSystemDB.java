import java.sql.Connection;
import java.sql.PreparedStatement;

public class ReservationSystemDB {

    public static void insertReservation(String name, String trainNo, String trainName,
                                         String classType, String date, String from, String to) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO reservations VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, trainNo);
            ps.setString(3, trainName);
            ps.setString(4, classType);
            ps.setString(5, date);
            ps.setString(6, from);
            ps.setString(7, to);

            ps.executeUpdate();
            System.out.println("Reservation Successful!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cancelReservation(String name) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM reservations WHERE name=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.executeUpdate();

            System.out.println("Reservation Cancelled!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
