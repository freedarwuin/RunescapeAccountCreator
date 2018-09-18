package org.medusa.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.medusa.Main;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, String type, String title) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NotificationGUI frame = new NotificationGUI(type, title);
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
	public NotificationGUI(String type, String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 294, 201);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAmount = new JLabel("");
		lblAmount.setBounds(10, 11, 258, 23);
		contentPane.add(lblAmount);

		JButton btnAwesome = new JButton("Done");

		
		JLabel label = new JLabel("");
		label.setBounds(10, 36, 258, 23);
		contentPane.add(label);
		if (type == "complete") {
		lblAmount.setText(Main.currentNumber + "/" + Main.accountsWanted + " accounts created");
		
		btnAwesome.setText("Awesome!");
		btnAwesome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		} else if (type == "error") {
		lblAmount.setText("An error ocurred!");
		btnAwesome.setText(";-;");
		btnAwesome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		} else if (type == "note") {
			lblAmount.setText("I've added a new logger.");
			label.setText("Please open it to see the log.");
			btnAwesome.setText("Got it!");
			btnAwesome.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
		}
		btnAwesome.setBounds(77, 102, 123, 49);
		contentPane.add(btnAwesome);
	}
}
