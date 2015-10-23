import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JButton;

public class ReservationManager {

	public static int TOTAL_SEATS = 200; // total number of seats
	public static int TOTAL_ROWS = 50; // total number of rows
	public static int TOTAL_COLS = 4; // total number of rows
	private Lock seatLock = new ReentrantLock();

	// keeps track of which seats are taken
	private JButton[][] seatButtons = new JButton[TOTAL_ROWS][TOTAL_COLS];

	private ReservationManager() {
	}

	public static void main(String[] args) {
		ReservationManager r = new ReservationManager();
		SeatReservationGUI.startGUI(r);

		AutoReserver auto1 = new AutoReserver(r, 1);
		AutoReserver auto2 = new AutoReserver(r, 2);

		Thread thread1 = new Thread(auto1);
		Thread thread2 = new Thread(auto2);

		thread1.start();
		thread2.start();
	}

	// locks and returns seat buttons to one person at a time. ensures thread safe
	public JButton[][] getSeatButtons() {
		seatLock.lock();
		return seatButtons;
	}
	
	// releases lock on seatButtons
	public void releaseSeatButtons() {
		seatLock.unlock();
	}

}
