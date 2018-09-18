package org.medusa.GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import org.medusa.Main;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class LoggerGUI extends JFrame {
	
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
					LoggerGUI frame = new LoggerGUI();
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
	public LoggerGUI() {
		setTitle("Log");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 434, 261);
		textArea.setEditable(false);
		contentPane.add(textArea);
		
		JScrollPane sp = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setBounds(0, 0, 430, 210);
		contentPane.add(sp);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(10, 219, 89, 23);
		contentPane.add(btnClear);
		
		JLabel lblClosingThisWindow = new JLabel("Closing this window will remove all content logged");
		lblClosingThisWindow.setBounds(127, 223, 301, 14);
		contentPane.add(lblClosingThisWindow);
		
		DefaultCaret caret = (DefaultCaret)textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		addWindowListener(new WindowAdapter() {
			
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	Main.st.logger = false;
		    	e.getWindow().setVisible(false);
		    	e.getWindow().dispose();
		    }
		});
	}
}
