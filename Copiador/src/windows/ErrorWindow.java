package windows;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class errorWindow extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;

	public errorWindow(String error, int option) {
		int fontSize = 0;
		String errorCode = "0";
		boolean saveCrashReport = false;
		
		switch(option) {
			case 1:
				errorCode = "0001";
				fontSize = 25;
				break;
			case 2:
				errorCode = "0002";
				fontSize = 18;
				break;
			case 3:
				errorCode = "0003";
				fontSize = 18;
				break;
			case 4:
				errorCode = "0004";
				fontSize = 18;
				break;
			case 5:
				errorCode = "0005";
				fontSize = 18;
			break;
			default:
				break;
		}
		
		File document = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator
				+ "HardszVick" + File.separator + "copy" + File.separator + "crash-report");
		if (document.exists()) {
			saveCrashReport = true;
		}else {
				document.mkdirs();
				saveCrashReport = true;
		}
		
		if(saveCrashReport) {
			Calendar dateTime = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
			System.out.println("C: " + sdf.format(dateTime.getTime()));
			
			try {
				FileWriter arq = new FileWriter(document + File.separator +  "crash-" + sdf.format(dateTime.getTime())+ ".txt");
			    PrintWriter gravarArq = new PrintWriter(arq);
			    
			    gravarArq.printf("------Crash Report----- \n "
			    	+ "Time: " + sdf.format(dateTime.getTime()) + "\n"
			    	+ "Error Code: " + errorCode + "\n" + 
			    	"Description: " + error);
			    arq.close();
				}catch(IOException e) {
					new errorWindow("Error creating settings path", 5);
				}
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 176);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Error Code: " + errorCode);
		
		JLabel errorMessage_txtf = new JLabel(error);
		errorMessage_txtf.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage_txtf.setForeground(Color.RED);
		errorMessage_txtf.setBounds(10, 11, 394, 85);
		contentPane.add(errorMessage_txtf);
		
		InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"forward");
		this.getRootPane().getActionMap().put("forward", new AbstractAction(){
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        dispose();
		    }
		});
		
		
		errorMessage_txtf.setFont(new Font("Courier", Font.BOLD,fontSize));
		errorMessage_txtf.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		
		JButton btnNewButton = new JButton("Okay");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(158, 103, 89, 23);
		contentPane.add(btnNewButton);
	}
}
