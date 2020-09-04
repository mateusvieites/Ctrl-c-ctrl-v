package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField url_txtf;

	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 116);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		url_txtf = new JTextField();
		url_txtf.setToolTipText("");
		url_txtf.setText("Copy URL here");
		url_txtf.setBounds(66, 11, 299, 20);
		contentPane.add(url_txtf);
		url_txtf.setColumns(10);
		
		JLabel url_label = new JLabel("Url:");
		url_label.setHorizontalAlignment(SwingConstants.RIGHT);
		url_label.setBounds(0, 14, 46, 14);
		contentPane.add(url_label);
		
		JButton copy_button = new JButton("Copy");
		copy_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkError();
			}
		});
		copy_button.setBounds(92, 38, 89, 23);
		contentPane.add(copy_button);
		
		InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"forward");
		this.getRootPane().getActionMap().put("forward", new AbstractAction(){
		    @Override
		    public void actionPerformed(ActionEvent arg0) {
		        checkError();
		    }
		});
		
		JCheckBox allFiles_CheckBox = new JCheckBox("All files");
		allFiles_CheckBox.setBounds(10, 38, 76, 23);
		contentPane.add(allFiles_CheckBox);
	}
	
	private void checkError() {
		
		if(url_txtf.getText() == null || url_txtf.getText().trim().equals("")) {
			new errorWindow("Please, insert a URL",1).setVisible(true);
		}else {
			/* copyFile(); */
		}
	}
}