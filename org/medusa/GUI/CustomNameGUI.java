package org.medusa.GUI;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.medusa.Main;
import org.medusa.Utils.LoadUsernames;
import org.medusa.Utils.Logger;

import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;

public class CustomNameGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextArea txtPrefix, txtSuffix;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomNameGUI frame = new CustomNameGUI();
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
	public CustomNameGUI() {
		setTitle("Usernames");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{434, 0};
		gbl_contentPane.rowHeights = new int[]{305, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		contentPane.add(tabbedPane, gbc_tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(null);
		
		JCheckBox chckbxEnableCustomUN = new JCheckBox("Enable Custom Usernames");
		chckbxEnableCustomUN.setBounds(6, 7, 219, 23);
		panel.add(chckbxEnableCustomUN);
		
		chckbxEnableCustomUN.setSelected(Main.customUN);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Usernames", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{225, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblUsernamePrefix = new JLabel("Username Prefix:");
		GridBagConstraints gbc_lblUsernamePrefix = new GridBagConstraints();
		gbc_lblUsernamePrefix.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsernamePrefix.gridx = 0;
		gbc_lblUsernamePrefix.gridy = 0;
		panel_1.add(lblUsernamePrefix, gbc_lblUsernamePrefix);
		
		JLabel lblUsernameSuffix = new JLabel("Username Suffix:");
		GridBagConstraints gbc_lblUsernameSuffix = new GridBagConstraints();
		gbc_lblUsernameSuffix.insets = new Insets(0, 0, 5, 0);
		gbc_lblUsernameSuffix.gridx = 1;
		gbc_lblUsernameSuffix.gridy = 0;
		panel_1.add(lblUsernameSuffix, gbc_lblUsernameSuffix);
		
		JButton btnLoadPrefix = new JButton("Load From File");
		GridBagConstraints gbc_btnLoadPrefix = new GridBagConstraints();
		gbc_btnLoadPrefix.insets = new Insets(0, 0, 0, 5);
		gbc_btnLoadPrefix.gridx = 0;
		gbc_btnLoadPrefix.gridy = 2;
		panel_1.add(btnLoadPrefix, gbc_btnLoadPrefix);
		btnLoadPrefix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadUsernames.loadPrefixFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtPrefix = new JTextArea();
		GridBagConstraints gbc_txtPrefix = new GridBagConstraints();
		gbc_txtPrefix.fill = GridBagConstraints.BOTH;
		gbc_txtPrefix.insets = new Insets(0, 0, 5, 5);
		gbc_txtPrefix.gridx = 0;
		gbc_txtPrefix.gridy = 1;
		txtPrefix.setEditable(false);
		panel_1.add(txtPrefix, gbc_txtPrefix);
		txtPrefix.setText(LoadUsernames.prefixText);
		
		JButton btnLoadSuffix = new JButton("Load From File");
		GridBagConstraints gbc_btnLoadSuffix = new GridBagConstraints();
		gbc_btnLoadSuffix.gridx = 1;
		gbc_btnLoadSuffix.gridy = 2;
		panel_1.add(btnLoadSuffix, gbc_btnLoadSuffix);
		btnLoadSuffix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadUsernames.loadSuffixFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txtSuffix = new JTextArea();
		txtSuffix.setEditable(false);
		GridBagConstraints gbc_txtSuffix = new GridBagConstraints();
		gbc_txtSuffix.insets = new Insets(0, 0, 5, 0);
		gbc_txtSuffix.fill = GridBagConstraints.BOTH;
		gbc_txtSuffix.gridx = 1;
		gbc_txtSuffix.gridy = 1;
		panel_1.add(txtSuffix, gbc_txtSuffix);
		txtSuffix.setText(LoadUsernames.suffixText);
		
		
		addWindowListener(new WindowAdapter() {
			
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	 Main.st.username = false;
		    	 Logger.log("Saving username settings");
	             Main.customUN = chckbxEnableCustomUN.isSelected();
		    	 e.getWindow().setVisible(false);
		    	 e.getWindow().dispose();
		    }
		});
	}
}
