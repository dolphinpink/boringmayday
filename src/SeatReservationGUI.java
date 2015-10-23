import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class SeatReservationGUI {

	/*
	 * @param rm the reservation manager for the plane you would like to display
	 * 
	 */
	public static void startGUI(ReservationManager rm) {
		JFrame frame = new JFrame("Reserve seat");
		JPanel container = new JPanel(new FlowLayout());
		JPanel left = new JPanel(new GridLayout(0, 2, 0, 5));
		JPanel mid = new JPanel(new GridLayout(0, 2, 40, 5));
		JPanel right = new JPanel(new GridLayout(0, 2, 0, 5));
		container.add(left);
		container.add(mid);
		container.add(right);
		JScrollPane scrPane = new JScrollPane(container,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.add(scrPane);
		frame.pack();

		// creating JButtons

		// locking the array
		JButton[][] seatButtons = rm.getSeatButtons();

		for (int r = 0; r < ReservationManager.TOTAL_ROWS; r++) {
			for (int c = 0; c < ReservationManager.TOTAL_COLS; c++) {
				String seatName = (r + 1) + " " + (char) (c + 65);
				seatButtons[r][c] = new JButton(seatName);
				seatButtons[r][c].setForeground(Color.BLUE);
				// adds listener for button press events
				seatButtons[r][c].addActionListener(new SeatListener(rm));

				// put the button on the frame
				if (c < 2)
					left.add(seatButtons[r][c]);
				else
					right.add(seatButtons[r][c]);
			}
		}

		// releasing the lock
		rm.releaseSeatButtons();

		// Display the window
		frame.setSize(500, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	private static class SeatListener implements ActionListener {
		private ReservationManager rm;

		public SeatListener(ReservationManager rm) {
			this.rm = rm;
		}

		public void actionPerformed(ActionEvent e) {
			// acquire lock
			rm.getSeatButtons();

			JButton seat = (JButton) e.getSource();
			if (seat.getForeground() == Color.BLUE) {
				String seatName = "(3)";
				seat.setText(seatName);
				seat.setForeground(Color.DARK_GRAY);
			}

			// release lock
			rm.releaseSeatButtons();
		}
	}
}
