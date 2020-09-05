package windows;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utility.CopyFiles;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
public class MainWindow extends JFrame {

	private String version = "V 0.2";
	private JPanel contentPane;
	private JTextField url_txtf;
	boolean flagNull = false;

	public MainWindow() {
		setTitle("HardszVick " + version);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		url_txtf = new JTextField();
		url_txtf.setBounds(66, 11, 299, 20);
		contentPane.add(url_txtf);
		url_txtf.setColumns(10);
		url_txtf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				url_txtf.setText("");
				flagNull = false;
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(url_txtf.getText().equals(null) || url_txtf.getText().equals("")) {
					url_txtf.setText("Paste URL here");
					flagNull = true;
				}
			}
		});
		
		JLabel url_label = new JLabel("Url:");
		url_label.setHorizontalAlignment(SwingConstants.RIGHT);
		url_label.setBounds(0, 14, 46, 14);
		contentPane.add(url_label);
		
		JButton copy_button = new JButton("Copy");
		copy_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					checkError();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		copy_button.setBounds(28, 42, 89, 23);
		contentPane.add(copy_button);
		
		
		InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"forward");
		this.getRootPane().getActionMap().put("forward", new AbstractAction(){
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        try {
					checkError();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		});
	}
	
	private void checkError() throws IOException {
		
		if(url_txtf.getText() == null || url_txtf.getText().trim().equals("") || flagNull) {
			new errorWindow("Please, insert a URL",1).setVisible(true);
		}else {
			CopyFiles.Copy(url_txtf.getText());
		}
	}
}