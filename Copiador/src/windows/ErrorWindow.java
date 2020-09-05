package windows;

import java.awt.BorderLayout;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class errorWindow extends JFrame {

	private JPanel contentPane;

	public errorWindow(String error, int option) {
		int fontSize = 0;
		String errorCode = "0";
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
				break;
			default:
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
