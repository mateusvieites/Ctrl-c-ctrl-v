package windows;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utility.Actions;
import utility.CopyFiles;

import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
public class MainWindow extends JFrame{

	private String version = "V 0.2";
	private JPanel contentPane;
	private JTextField url_txtf;
	boolean flagNull = false;
	

	public MainWindow() {
		File document = new File(System.getProperty("user.home") + File.separator + "Documents" + File.separator
				+ "HardszVick" + File.separator + "copy");
			
		String to = System.getProperty("user.home") + File.separator + "Documents" + File.separator
				+ "HardszVick" + File.separator + "copy" + File.separator + "settings.txt";
		if (document.exists()) {
					Actions.read(to);
			}else if (document.mkdirs()) {
				Actions.createSettings(to);	
			} else if (document.mkdirs()) {
				try {
				FileWriter arq = new FileWriter(System.getProperty("user.home") + File.separator + "Documents" + File.separator
						+ "HardszVick" + File.separator + "copy" + File.separator + "settings.txt");
			    PrintWriter gravarArq = new PrintWriter(arq);
			    destination = "D:\\Pastas\\Backups";
			    gravarArq.printf("BackupPath="+ destination);
			    arq.close();
				}catch(IOException e) {
					new ErrorWindow("Error creating settings path", 5);
				}
			} else {
				System.out.println(document + " was not created");
				new ErrorWindow("Error creating directory", 3);
				FlagError = true;
			}
		
		setTitle("HardszVick " + version);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(386,127);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		url_txtf = new JTextField();
		url_txtf.setBounds(66, 29, 299, 20);
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
		url_label.setBounds(10, 32, 46, 14);
		contentPane.add(url_label);
		
		JButton copy_button = new JButton("Copy");
		copy_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actions.checkError(url_txtf.getText(),flagNull);
			}
		});
		copy_button.setBounds(276, 60, 89, 23);
		contentPane.add(copy_button);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 370, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("System");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Settings");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SettingsWindow().setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JButton selectD_Button = new JButton("Select Directory");
		selectD_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				url_txtf.setText(Actions.SelectFile("Select Directory"));
				flagNull = false;
				Actions.checkError(url_txtf.getText(),flagNull);
				
			}
		});
		selectD_Button.setBounds(20, 60, 145, 23);
		contentPane.add(selectD_Button);
		
		
		InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),"forward");
		this.getRootPane().getActionMap().put("forward", new AbstractAction(){
		    public void actionPerformed(ActionEvent arg0) {
		    	Actions.checkError(url_txtf.getText(),flagNull);
		    }
		});
	}
}