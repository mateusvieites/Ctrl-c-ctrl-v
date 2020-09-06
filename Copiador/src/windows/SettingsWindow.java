package windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utility.Actions;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class SettingsWindow extends JFrame{
	private ArrayList observers = new ArrayList();
	private JPanel contentPane;
	private JTextField backupUrl_txf;
	private JTextField email_textField;
	private JTextField password_textfield;
	
	boolean flagNull = true;
	String urlBackup = null;
	
	public SettingsWindow() {
		String txt = System.getProperty("user.home") + File.separator + "Documents" + File.separator
				+ "HardszVick" + File.separator + "copy" + File.separator + "settings.txt";
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,107);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel backupUrl_lbl = new JLabel("Backup Url:");
		backupUrl_lbl.setHorizontalAlignment(SwingConstants.RIGHT);
		backupUrl_lbl.setBounds(0, 11, 73, 14);
		contentPane.add(backupUrl_lbl);
		
		JButton confirm_button = new JButton("confirm");
		confirm_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		confirm_button.setBounds(324, 39, 88, 23);
		contentPane.add(confirm_button);
		
		
		backupUrl_txf = new JTextField();
		backupUrl_txf.setBounds(83, 8, 329, 20);
		contentPane.add(backupUrl_txf);
		backupUrl_txf.setColumns(10);
		backupUrl_txf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				backupUrl_txf.setText("");
				flagNull = false;
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(backupUrl_txf.getText().equals(null) || backupUrl_txf.getText().equals("")) {
					backupUrl_txf.setText(urlBackup);
					flagNull = true;
				}
			}
		});
		
		File file = new File(txt);
				
				if(file.exists()) {
					urlBackup = Actions.read(txt);
					backupUrl_txf.setText(urlBackup);
				};
		
		JButton bk_button = new JButton("Select Diretory");
		bk_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(file.mkdirs()) {
					try {
						FileWriter arq = new FileWriter(txt);
					    PrintWriter gravarArq = new PrintWriter(arq);
					    
					    gravarArq.printf("BackupPath="+ urlBackup);
					    arq.close();
						}catch(IOException e1) {
							new ErrorWindow("Error creating settings path", 5);
						}
				}
			}
		});
		bk_button.setBounds(10, 36, 130, 23);
		contentPane.add(bk_button);
	}
}
