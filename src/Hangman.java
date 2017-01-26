import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Hangman extends JFrame {
	static int attempts = 0;
	final static int ATTEMPTS_BEFORE_HUNG = 6;
	static String city = "";
	static char[] cityLetters = new char[0];
	static char[] cityNameWithBlankSpaces = new char[0];
	static boolean isTheLetterIncludedInTheCityName = false;
	static boolean isEndOfTheGame = false;
	static char letterToCheck = ' ';

	public void main(String[] args) throws IOException {
		gamePreparation();
		
		printCityNameThatIncludesBlankSpaces();
		runGame();
		winOrLoss();
	}

	public void runGame() throws IOException {
		while (attempts < ATTEMPTS_BEFORE_HUNG && isEndOfTheGame == false) {
			inputLetter();
			checkIsLetter();
		}
	}

	public void checkIsLetter() throws IOException {
		if (Character.isLetter(letterToCheck)) {
			isTheLetterCyrillic();
		} else {
			System.out.println("Не въвеждайте символи! Опитайте с буква на кирилица!");
		}
	}

	public void isTheLetterCyrillic() throws IOException {
		int asciiCodeOfTheLetter = 0;
		asciiCodeOfTheLetter = (int) letterToCheck;
		if (asciiCodeOfTheLetter >= 1040 && asciiCodeOfTheLetter <= 1103) {
			doThisIfTheInputIsCorrect();
		} else {
			System.out.println("Въведете буква на кирилица!");
		}
	}

	public void doThisIfTheInputIsCorrect() throws IOException {
		convertCapitalLettersToLowerCases();
		checkIsTheLetterIncluded();
		fillWithLettersOrIncreaceAttempts();
		printCityNameThatIncludesBlankSpaces();
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

	public void attemptsCases() throws IOException {
		switch (attempts) {
		case 1:
			JLabel picLabel_1 = new JLabel(new ImageIcon("src/hangman_pictures/1.jpg"));
			add(picLabel_1);
			break;
		case 2:
			JLabel picLabel_2 = new JLabel(new ImageIcon("src/hangman_pictures/2.jpg"));
			add(picLabel_2);
			break;
		case 3:
			JLabel picLabel_3 = new JLabel(new ImageIcon("src/hangman_pictures/3.jpg"));
			add(picLabel_3);
			break;
		case 4:
			JLabel picLabel_4 = new JLabel(new ImageIcon("src/hangman_pictures/4.jpg"));
			add(picLabel_4);
			break;
		case 5:
			JLabel picLabel_5 = new JLabel(new ImageIcon("src/hangman_pictures/5.jpg"));
			add(picLabel_5);
			break;
		case 6:
			JLabel picLabel_6 = new JLabel(new ImageIcon("src/hangman_pictures/6.jpg"));
			add(picLabel_6);
			break;
		default:
			JLabel default_picLabel = new JLabel(new ImageIcon("src/hangman_pictures/default.jpg"));
			add(default_picLabel);
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

	public static void inputLetter() {
		Scanner input = new Scanner(System.in);
		System.out.println("Въведете буква: ");
		letterToCheck = input.next(".").charAt(0);
	}

	public static String printCityNameThatIncludesBlankSpaces() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cityNameWithBlankSpaces.length; i++) {
			sb.append(cityNameWithBlankSpaces[i]);
		}
		System.out.println(sb.toString());
		return sb.toString();
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

}
