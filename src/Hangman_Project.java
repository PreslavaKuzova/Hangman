import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman_Project {
	static int attempts = 0;
	final static int ATTEMPTS_BEFORE_HUNG = 6;
	static String city = "";
	static char[] cityLetters = new char[0];
	static char[] cityNameWithBlankSpaces = new char[0];

	static boolean isTheLetterIncludedInTheCityName = false;
	static boolean isEndOfTheGame = false;
	static char letterToCheck = ' ';

	public static void main(String[] args) throws FileNotFoundException {
		gamePreparation();
		printCityNameThatIncludesBlankSpaces();
		runGame();
		winOrLoss();
	}

	public static void runGame() {
		while (attempts < ATTEMPTS_BEFORE_HUNG && isEndOfTheGame == false) {
			inputLetter();
			checkIsLetter();
		}
	} 
	
	public static void checkIsLetter() {
		if (Character.isLetter(letterToCheck)) {
			isTheLetterCyrillic();
		} else {
			System.out.println("Не въвеждайте символи! Опитайте с буква на кирилица!");
		}
	}

	public static void isTheLetterCyrillic() {
		int asciiCodeOfTheLetter = 0;
		asciiCodeOfTheLetter = (int) letterToCheck;
		if (asciiCodeOfTheLetter >= 1040 && asciiCodeOfTheLetter <= 1103) {
			doThisIfTheInputIsCorrect();
		} else {
			System.out.println("Въведете буква на кирилица!");
		}
	}

	public static void doThisIfTheInputIsCorrect() {
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

	public static void attemptsCases() {
		switch (attempts) {
		case 1:
			System.out.println("\n_________" + "\n|        |" + "\n|        O" + "\n|" + "\n|" + "\n|" + "\n|_______________________\n");
			break;
		case 2:
			System.out.println("\n_________" + "\n|        |" + "\n|        O" + "\n|        |" + "\n|" + "\n|" + "\n|_______________________\n");
			break;
		case 3:
			System.out.println("\n_________" + "\n|        |" + "\n|        O" + "\n|     ---|" + "\n|" + "\n|" + "\n|_______________________\n");
			break;
		case 4:
			System.out.println("\n_________" + "\n|        |" + "\n|        O" + "\n|     ---|---" + "\n|" + "\n|" + "\n|_______________________\n");
			break;
		case 5:
			System.out.println("\n_________" + "\n|        |" + "\n|        O" + "\n|     ---|---" + "\n|       /" + "\n|      /" + "\n|_______________________\n");
			break;
		case 6:
			System.out.println("\n_________" + "\n|        |" + "\n|        O" + "\n|     ---|---" + " \n|       /\\" + "\n|      /  \\" + "\n|_______________________");
			break;
		default:
			System.out.println("\n_________" + "\n|        |" + "\n|" + "\n|" + "\n|" + "\n|" + "\n|_______________________\n");
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

	public static void printCityNameThatIncludesBlankSpaces() {
		for (int i = 0; i < cityNameWithBlankSpaces.length; i++) {
			System.out.print(cityNameWithBlankSpaces[i]);
		}
		System.out.println();
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