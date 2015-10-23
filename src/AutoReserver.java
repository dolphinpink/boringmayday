import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;

public class AutoReserver implements Runnable {

	private int ID;
	private ReservationManager rm;

	public AutoReserver(ReservationManager rm, int ID) {
		this.ID = ID;
		this.rm = rm;
	}

	public void run() {
		try {
			while (true) {
				JButton[][] seatButtons = rm.getSeatButtons();

				int randomRow = randInt(0, ReservationManager.TOTAL_ROWS - 1);
				int randomLetter = randInt(0, ReservationManager.TOTAL_COLS - 1);
				JButton seat = seatButtons[randomRow][randomLetter];
				if (seat.getForeground() == Color.BLUE) {
					seat.setText("(" + String.valueOf(ID) + ")");
					seat.setForeground(Color.DARK_GRAY);
				}

				// release lock
				rm.releaseSeatButtons();
				Thread.sleep(randInt(1, 10) * 1000);
			}
		} catch (InterruptedException exception) {

		}

	}

	/*
	 * @param min the minimum value of random integer
	 * 
	 * @param max the maximum value of random integer
	 * 
	 * @post the returned value must be between min and max
	 */
	private static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		if (randomNum < min || randomNum > max)
			throw new ArithmeticException();
		return randomNum;
	}

}
