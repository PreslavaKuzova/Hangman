import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.Button;

public class GUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	public JTextField textField_1;
	
	private final Button button_1 = new Button("Start Game");
	//Hangman hangman = new Hangman();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setLocationRelativeTo(null);
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
	public GUI() {
		setResizable(false);
		setTitle("Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 418, 411);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(new Color(255, 255, 255));

		textField = new JTextField();
		textField.setBounds(478, 123, 199, 40);
		textField.setFont(new Font("Calibri Light", Font.BOLD, 25));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);

		// input from the text field
		String obtainLetterFromTheTextField = textField.getText();
		obtainLetterFromTheTextField.matches("[А-Яа-я]{1}");

		JTextArea textArea = new JTextArea();
		textArea.setBounds(453, 82, 251, 35);
		textArea.setEditable(false);
		textArea.setFont(new Font("Calibri Light", Font.BOLD, 25));
		textArea.setBackground(new Color(102, 153, 153));
		textArea.setText("ВЪВЕДЕТЕ БУКВА ТУК:");
		contentPane.setLayout(null);
		panel.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(10, 305, 398, 95);
		panel.add(textField_1);
		textField_1.setColumns(10);
		contentPane.add(panel);
		contentPane.add(textArea);
		contentPane.add(textField);
		button_1.setBounds(478, 187, 199, 47);
		contentPane.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
			
		});
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
