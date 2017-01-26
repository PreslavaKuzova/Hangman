import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
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
import javax.swing.JLabel;

public class GUI extends JFrame {
	//unsuccessful attempt to make the graphic
	//interface interact with the code
	static int attempts = 0;
	final static int ATTEMPTS_BEFORE_HUNG = 6;
	static String city = "";
	static char[] cityLetters = new char[0];
	static char[] cityNameWithBlankSpaces = new char[0];

	static boolean isTheLetterIncludedInTheCityName = false;
	static boolean isEndOfTheGame = false;
	static char letterToCheck = ' ';

	private JPanel contentPane;
	private JTextField textFieldObtainLetter;
	
	private final Button button_1 = new Button("ПРОВЕРИ БУКВА");
	private JLabel lblNewLabel;

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

		textFieldObtainLetter = new JTextField();
		textFieldObtainLetter.setBounds(478, 123, 199, 40);
		textFieldObtainLetter.setFont(new Font("Calibri Light", Font.BOLD, 25));
		textFieldObtainLetter.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldObtainLetter.setColumns(10);
		
		JButton btnNewButton = new JButton("СТАРТИРАЙ ИГРА");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						gamePreparation();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				lblNewLabel.setText(String.valueOf(cityNameWithBlankSpaces));
			}
		});
		btnNewButton.setBounds(513, 381, 150, 35);
		contentPane.add(btnNewButton);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(453, 82, 251, 35);
		textArea.setEditable(false);
		textArea.setFont(new Font("Calibri Light", Font.BOLD, 25));
		textArea.setBackground(new Color(102, 153, 153));
		textArea.setText("ВЪВЕДЕТЕ БУКВА ТУК:");
		contentPane.setLayout(null);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 40));
		lblNewLabel.setBounds(10, 315, 398, 85);
		panel.add(lblNewLabel);
		contentPane.add(textArea);
		contentPane.add(textFieldObtainLetter);
		
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String letter = textFieldObtainLetter.getText();
				letter.matches("[А-Яа-я]{1}");
				if (letter.length() != 1) {
					JOptionPane.showMessageDialog(null, "Въведете само една буква");
					System.exit(0);
				}
				char inputtedLetter = letter.charAt(0);
				runGame();
			}
		});
		button_1.setBounds(478, 169, 199, 47);
		contentPane.add(button_1);
		
	}
	
	public static void gamePreparation() throws FileNotFoundException {
		ArrayList<String> populatedPlaces = createArrayListWithCities();
		chooseRandomCity(populatedPlaces);
		createArrayWithLetters();
		createArrayToBeModified();
		fillArrayWithBlankSpaces();
	}

	public static void fillArrayWithBlankSpaces() {
		for (int i = 1; i < cityLetters.length; i++) {
			if (cityLetters[i] == ' ') {
				cityNameWithBlankSpaces[i] = ' ';
				cityNameWithBlankSpaces[i + 1] = cityLetters[i + 1];
				i++;
			} else {
				cityNameWithBlankSpaces[i] = '_';
			}

		}
	}

	public static void createArrayToBeModified() {
		cityNameWithBlankSpaces = new char[cityLetters.length];
		cityNameWithBlankSpaces[0] = cityLetters[0];
	}

	public static void createArrayWithLetters() {
		cityLetters = city.toCharArray();
		cityLetters.toString();
	}

	public static void chooseRandomCity(ArrayList<String> populatedPlaces) {
		Random randomCity = new Random();
		int index = randomCity.nextInt(populatedPlaces.size());
		city = populatedPlaces.get(index);
	}

	public static ArrayList<String> createArrayListWithCities() throws FileNotFoundException {
		Scanner s = new Scanner(new File("src/places.txt"), "UTF-8");
		ArrayList<String> populatedPlaces = new ArrayList<String>();
		while (s.hasNextLine()) {
			populatedPlaces.add(s.nextLine());
		}
		s.close();
		return populatedPlaces;
	}
	public static void runGame() {
		while (attempts < ATTEMPTS_BEFORE_HUNG && isEndOfTheGame == false) {
			checkIsLetter();
		}
	}

	public static void checkIsLetter() {
		if (Character.isLetter(letterToCheck)) {
			isTheLetterCyrillic();
		} else {
			JOptionPane.showMessageDialog(null, "Не въвеждайте символи! Опитайте с буква на кирилица!");
		}
	}

	public static void isTheLetterCyrillic() {
		int asciiCodeOfTheLetter = 0;
		asciiCodeOfTheLetter = (int) letterToCheck;
		if (asciiCodeOfTheLetter >= 1040 && asciiCodeOfTheLetter <= 1103) {
			doThisIfTheInputIsCorrect();
		} else {
			JOptionPane.showMessageDialog(null, "Въведете буква на кирилица!");
		}
	}

	public static void doThisIfTheInputIsCorrect() {
		convertCapitalLettersToLowerCases();
		checkIsTheLetterIncluded();
		fillWithLettersOrIncreaceAttempts();
		attemptsCases();
		checkIsEndOfTheGame();
	}

	public static void winOrLoss() {
		if (isEndOfTheGame) {
			System.out.println("Печелите!");
		} else {
			System.out.println("Бяхте обесени! Думата, която трябваше да познаете, беше " + city);
		}
	}

	public static void checkIsEndOfTheGame() {
		int j = 1;
		while (j < cityNameWithBlankSpaces.length) {
			if (cityNameWithBlankSpaces[j] == '_') {
				isEndOfTheGame = false;
				break;
			} else {
				isEndOfTheGame = true;
			}
			j++;
		}
	}

	public static void attemptsCases() {
		switch (attempts) {
		case 1:
			
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		default:
		}
	}

	public static void fillWithLettersOrIncreaceAttempts() {
		if (isTheLetterIncludedInTheCityName) {
			for (int i = 1; i < cityNameWithBlankSpaces.length; i++) {
				if (cityLetters[i] == letterToCheck) {
					cityNameWithBlankSpaces[i] = cityLetters[i];
				}
			}
		} else {
			attempts++;
		}
		isTheLetterIncludedInTheCityName = false;
	}

	public static void checkIsTheLetterIncluded() {
		int i = 1;
		while (i < cityLetters.length) {
			if (cityLetters[i] == letterToCheck) {
				isTheLetterIncludedInTheCityName = true;
			}
			i++;
		}
	}

	public static void convertCapitalLettersToLowerCases() {
		letterToCheck = Character.toLowerCase(letterToCheck);
	}

}
