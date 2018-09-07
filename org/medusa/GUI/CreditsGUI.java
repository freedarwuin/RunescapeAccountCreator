package org.medusa.GUI;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.event.ActionEvent;

public class CreditsGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditsGUI frame = new CreditsGUI();
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
	public CreditsGUI() {
		setTitle("Credits");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodeByMedusaa = new JLabel("Code by: Medusaa");
		lblCodeByMedusaa.setBounds(18, 22, 216, 16);
		contentPane.add(lblCodeByMedusaa);
		
		JButton btnTwitter = new JButton("Twitter");
		btnTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openWebsite(new URL("https://osbot.medusa.icu/?analytics=actwitter"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTwitter.setBounds(6, 80, 117, 29);
		contentPane.add(btnTwitter);
		
		JButton btnOsbot = new JButton("OSBot");
		btnOsbot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openWebsite(new URL("https://osbot.medusa.icu/?analytics=acosbot"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOsbot.setBounds(135, 80, 117, 29);
		contentPane.add(btnOsbot);
		
		JButton btnGithub = new JButton("Github");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					openWebsite(new URL("https://osbot.medusa.icu/?analytics=acgithub"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGithub.setBounds(264, 80, 117, 29);
		contentPane.add(btnGithub);
	}
	
	public void openWebsite(URL url) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(url.toURI());
			} catch (IOException e) {e.printStackTrace();} catch (URISyntaxException e) {e.printStackTrace();}
		}
	}
}
