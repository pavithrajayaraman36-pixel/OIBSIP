import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReservationPage extends JFrame implements ActionListener {

    JTextField name, trainNo, trainName, classType, date, from, to;
    JButton bookBtn, cancelBtn;

    public ReservationPage() {

        setTitle("Online Reservation System");
        setSize(500, 550);
        setLocationRelativeTo(null); // center screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🌈 Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(30, 144, 255)); // blue background
        add(panel);

        // 🧾 Title
        JLabel title = new JLabel("Train Reservation System");
        title.setBounds(80, 20, 350, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        panel.add(title);

        // Common Font
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        // Labels & Fields
        JLabel l1 = new JLabel("Name:");
        l1.setBounds(50, 80, 120, 25);
        l1.setFont(labelFont);
        l1.setForeground(Color.WHITE);
        panel.add(l1);

        name = new JTextField();
        name.setBounds(200, 80, 200, 25);
        panel.add(name);

        JLabel l2 = new JLabel("Train No:");
        l2.setBounds(50, 120, 120, 25);
        l2.setFont(labelFont);
        l2.setForeground(Color.WHITE);
        panel.add(l2);

        trainNo = new JTextField();
        trainNo.setBounds(200, 120, 200, 25);
        panel.add(trainNo);

        JLabel l3 = new JLabel("Train Name:");
        l3.setBounds(50, 160, 120, 25);
        l3.setFont(labelFont);
        l3.setForeground(Color.WHITE);
        panel.add(l3);

        trainName = new JTextField();
        trainName.setBounds(200, 160, 200, 25);
        panel.add(trainName);

        JLabel l4 = new JLabel("Class Type:");
        l4.setBounds(50, 200, 120, 25);
        l4.setFont(labelFont);
        l4.setForeground(Color.WHITE);
        panel.add(l4);

        classType = new JTextField();
        classType.setBounds(200, 200, 200, 25);
        panel.add(classType);

        JLabel l5 = new JLabel("Date:");
        l5.setBounds(50, 240, 120, 25);
        l5.setFont(labelFont);
        l5.setForeground(Color.WHITE);
        panel.add(l5);

        date = new JTextField();
        date.setBounds(200, 240, 200, 25);
        panel.add(date);

        JLabel l6 = new JLabel("From:");
        l6.setBounds(50, 280, 120, 25);
        l6.setFont(labelFont);
        l6.setForeground(Color.WHITE);
        panel.add(l6);

        from = new JTextField();
        from.setBounds(200, 280, 200, 25);
        panel.add(from);

        JLabel l7 = new JLabel("To:");
        l7.setBounds(50, 320, 120, 25);
        l7.setFont(labelFont);
        l7.setForeground(Color.WHITE);
        panel.add(l7);

        to = new JTextField();
        to.setBounds(200, 320, 200, 25);
        panel.add(to);

        // 🟢 Book Button
        bookBtn = new JButton("Book Ticket");
        bookBtn.setBounds(80, 400, 150, 40);
        bookBtn.setBackground(new Color(34, 139, 34)); // green
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFocusPainted(false);
        panel.add(bookBtn);

        // 🔴 Cancel Button
        cancelBtn = new JButton("Cancel Ticket");
        cancelBtn.setBounds(260, 400, 150, 40);
        cancelBtn.setBackground(new Color(220, 20, 60)); // red
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        panel.add(cancelBtn);

        bookBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        String n = name.getText();

        if (e.getSource() == bookBtn) {
            ReservationSystemDB.insertReservation(
                    n,
                    trainNo.getText(),
                    trainName.getText(),
                    classType.getText(),
                    date.getText(),
                    from.getText(),
                    to.getText()
            );

            JOptionPane.showMessageDialog(this, "✅ Reservation Successful!");
        }

        if (e.getSource() == cancelBtn) {
            ReservationSystemDB.cancelReservation(n);
            JOptionPane.showMessageDialog(this, "❌ Reservation Cancelled!");
        }
    }
}