import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;
public class SeatReserver {

	private static JFrame frame;
	private static int TOTAL_SEATS = 200;  // total number of seats
	private static int TOTAL_ROWS = 50;  // total number of rows
	private static int TOTAL_COLS = 4;  // total number of rows
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	
    private static void createAndShowGUI() {
        //Create and set up the scrollable window
        frame = new JFrame("Airplane Seat Reservation");
        JPanel container = new JPanel(new FlowLayout());
        JPanel left = new JPanel(new GridLayout(0,2,0,5));
        JPanel mid = new JPanel(new GridLayout(0,2,40,5));
        JPanel right = new JPanel(new GridLayout(0,2,0,5));
        container.add(left);
        container.add(mid);
        container.add(right);
        JScrollPane scrPane = new JScrollPane(
        		container,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrPane);
        frame.pack();
        
        // create JButtons
        JButton[] seatButtons = new JButton[TOTAL_SEATS];
        for(int i=0;i<TOTAL_ROWS;i++){
        	for(int j=0;j<TOTAL_COLS;j++){
        		String seatName = (i+1)+" "+(char)(j+65);
        		seatButtons[j+i]= new JButton(seatName);
        		seatButtons[j+i].setForeground(Color.BLUE);
        		// add the listener to the JButtons to handle the "pressed" event
        		seatButtons[j+i].addActionListener(new BListener());
        		
        		// put the button on the frame
        		if(j<2)
        			left.add(seatButtons[j+i]);
        		else
        			right.add(seatButtons[j+i]);
        	}
        }

        //Display the window
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);    
    }
    
    private static class BListener implements ActionListener
    {
		public void actionPerformed(ActionEvent e)
		{		
			  JButton seat = (JButton) e.getSource();
			  String seatName = seat.getText()+" (P3)";
			  seat.setText(seatName);
			  seat.setForeground(Color.DARK_GRAY);
		}
    }
}
