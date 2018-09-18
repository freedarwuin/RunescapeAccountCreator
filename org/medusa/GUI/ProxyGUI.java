package org.medusa.GUI;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.medusa.Main;
import org.medusa.Utils.LoadProxies;
import org.medusa.Utils.Logger;

import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProxyGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public static JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProxyGUI frame = new ProxyGUI();
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
	public ProxyGUI() {
		setTitle("Proxies");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 305);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(null);
		
		JCheckBox chckbxEnableProxies = new JCheckBox("Enable Proxies");
		chckbxEnableProxies.setBounds(6, 7, 219, 23);
		panel.add(chckbxEnableProxies);
		
		chckbxEnableProxies.setSelected(Main.proxies);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Add Proxies", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Load From File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					LoadProxies.loadProxyFile();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(0, 208, 428, 31);
		panel_1.add(btnNewButton);
		
		textArea = new JTextArea();
		textArea.setBounds(1, 1, 428, 206);
		textArea.setEditable(false);
		panel_1.add(textArea);
		textArea.setText(LoadProxies.content);
		
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(0, 0, 430, 210);
		panel_1.add(sp);
		
		
		addWindowListener(new WindowAdapter() {
			
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	 Main.st.proxy = false;
		    	 Logger.log("Saving proxy settings");
	             Main.proxies = chckbxEnableProxies.isSelected();
		    	 e.getWindow().setVisible(false);
		    	 e.getWindow().dispose();
		    }
		});
	}
}
