package org.medusa.GUI;


import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.medusa.Main;
import org.medusa.Utils.GetOSBot;
import org.medusa.Utils.Logger;
import org.medusa.Utils.Utilities;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

@SuppressWarnings("serial")
public class AdvancedGUI extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;
	private JTextField script;
	private JTextField scriptParams;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdvancedGUI frame = new AdvancedGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdvancedGUI() {
		setTitle("Advanced Settings");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 444, 271);
		contentPane.add(tabbedPane);
		

		String os = Utilities.getOS();
		if (os.startsWith("Windows")) {
		JPanel panel = new JPanel();
		tabbedPane.addTab("OSBot Settings", null, panel, null);
		panel.setLayout(null);
		
		username = new JTextField();
		username.setBounds(10, 29, 146, 20);
		username.setText(Main.st.osbotUsername);
		panel.add(username);
		username.setColumns(10);
		
		JLabel lblOsbotUsername = new JLabel("OSBot Username");
		lblOsbotUsername.setBounds(10, 11, 157, 14);
		panel.add(lblOsbotUsername);
		
		JLabel lblOsbotPassword = new JLabel("OSBot Password (Plaintext)");
		lblOsbotPassword.setBounds(10, 60, 203, 14);
		panel.add(lblOsbotPassword);
		
		password = new JTextField();
		password.setBounds(10, 85, 146, 20);
		password.setText(Main.st.osbotPassword);
		panel.add(password);
		password.setColumns(10);
		
		JCheckBox chckbxLaunchAScript = new JCheckBox("Launch a script everytime an account is created");
		chckbxLaunchAScript.setBounds(6, 112, 368, 23);
		chckbxLaunchAScript.setSelected(Main.st.useScript);
		panel.add(chckbxLaunchAScript);
		
		JLabel lblCurrentlyOnlyWorks = new JLabel("Currently only works on Windows");
		lblCurrentlyOnlyWorks.setBounds(10, 147, 419, 14);
		panel.add(lblCurrentlyOnlyWorks);
		
		JButton save = new JButton("Save");
		save.setBounds(10, 174, 203, 58);
		panel.add(save);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Script Settings", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblScriptIdname = new JLabel("Script ID/Name");
		lblScriptIdname.setBounds(10, 11, 167, 14);
		panel_1.add(lblScriptIdname);
		
		script = new JTextField();
		script.setBounds(10, 36, 86, 20);
		script.setText(Main.st.scriptNameID);
		panel_1.add(script);
		script.setColumns(10);
		
		JLabel lblOsbotPath = new JLabel("OSBot Path");
		lblOsbotPath.setBounds(10, 67, 125, 14);
		panel_1.add(lblOsbotPath);
		
		JLabel lblNotSet = new JLabel("Not set");
		lblNotSet.setBounds(10, 92, 419, 14);
		lblNotSet.setText(GetOSBot.getPath());
		panel_1.add(lblNotSet);
		
		
		JButton btnUpdated = new JButton("Update");
		btnUpdated.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNotSet.setText(GetOSBot.getPath());
			}
		});
		btnUpdated.setBounds(10, 114, 89, 23);
		panel_1.add(btnUpdated);
		
		JButton btnSetPath = new JButton("Set Path");
		btnSetPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetOSBot preset = new GetOSBot();
				try {
					preset.loadJar();
				} catch (Exception et) {
					return;
				}
			}
		});
		btnSetPath.setBounds(108, 114, 89, 23);
		panel_1.add(btnSetPath);
		
		JLabel lblScriptParameters = new JLabel("Script Parameters");
		lblScriptParameters.setBounds(131, 11, 284, 14);
		panel_1.add(lblScriptParameters);
		
		scriptParams = new JTextField();
		scriptParams.setBounds(131, 36, 145, 20);
		scriptParams.setText(Main.st.scriptParams);
		panel_1.add(scriptParams);
		scriptParams.setColumns(10);
		
		chckbxLaunchAScript.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.st.useScript = chckbxLaunchAScript.isSelected();
				Logger.log("Launch scripts: " + Main.st.useScript);
			}
		});
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.st.scriptNameID = script.getText();
				if (scriptParams.getText().isEmpty()) {
		    	Main.st.scriptParams = "";
				} else {
			    	Main.st.scriptParams = scriptParams.getText();
				}
		    	Main.st.osbotUsername = username.getText();
		    	Main.st.osbotPassword = password.getText();
		    	
		    	Main.st.advanced = false;
		    	setVisible(false);
		    	dispose();
			}
		});
		} else {
			Logger.log("You're not running a Windows OS, so some features could not be loaded.");
		}
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Program Configuration", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel lblCaptchaSiteKey = new JLabel("Captcha site key");
		lblCaptchaSiteKey.setBounds(10, 11, 297, 14);
		panel_2.add(lblCaptchaSiteKey);
		
		textField = new JTextField();
		textField.setText(Main.siteKey);
		textField.setBounds(10, 36, 419, 20);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.st.advanced = false;
				Main.siteKey = textField.getText();
				Logger.log("Updates site key: " + Main.siteKey);
				dispose();
			}
		});
		btnUpdate.setBounds(10, 209, 89, 23);
		panel_2.add(btnUpdate);
		
		
		
		addWindowListener(new WindowAdapter() {
			
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	Main.st.advanced = false;
		    	e.getWindow().setVisible(false);
		    	e.getWindow().dispose();
		    }
		});
	}
}
