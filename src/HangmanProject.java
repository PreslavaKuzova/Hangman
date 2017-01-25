import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanProject {
	static int attempts = 0;
	static boolean isTheLetterIncludedInTheCityName = false;
	static boolean isEndOfTheGame;
	static char[] cityNameWithBlankSpaces;
	public static void main(String[] args) throws FileNotFoundException {
		final int ATTEMPTS_BEFORE_HUNG = 6;

		Scanner s = new Scanner(new File("src/places.txt"), "UTF-8");
		ArrayList<String> populatedPlaces = new ArrayList<String>();
		while (s.hasNextLine()) {
			populatedPlaces.add(s.nextLine());
		}
		s.close();

		Random randomCity = new Random();
		int index = randomCity.nextInt(populatedPlaces.size());
		String city = populatedPlaces.get(index);

		char[] cityLetters = city.toCharArray();

		cityNameWithBlankSpaces = new char[cityLetters.length];
		cityNameWithBlankSpaces[0] = cityLetters[0];

		fillWithBlankSpaces(cityLetters, cityNameWithBlankSpaces);

		boolean isEndOfTheGame = false;

		printCityNameWithBlankSpaces(cityNameWithBlankSpaces);

		while (attempts < ATTEMPTS_BEFORE_HUNG && isEndOfTheGame == false) {
			isTheLetterIncludedInTheCityName = false;
			attempts = isTheInputCorrect(cityLetters, cityNameWithBlankSpaces);
		}

		endOfTheGame(city);

	}

	public static void fillWithBlankSpaces(char[] cityLetters, char[] cityNameWithBlankSpaces) {
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

	public static void endOfTheGame(String city) {
		if (isEndOfTheGame) {
			System.out.println("Печелите!");
		} else {
			System.out.println("Бяхте обесени! Думата, която трябваше да познаете, беше " + city);
		}
	}

	public static int isTheInputCorrect( char[] cityLetters, char[] cityNameWithBlankSpaces) {
		Scanner input = new Scanner(System.in);
		System.out.println("Въведете буква: ");
		char letterToCheck = input.next(".").charAt(0);
		if (Character.isLetter(letterToCheck)) {

			isCyrillic(cityLetters, cityNameWithBlankSpaces, letterToCheck);
		} else {
			System.out.println("Не въвеждайте символи! Опитайте с буква на кирилица!");
		}
		return attempts;
	}

	public static int isCyrillic(char[] cityLetters, char[] cityNameWithBlankSpaces, char letterToCheck) {
		int asciiCodeOfTheLetter = 0;
		asciiCodeOfTheLetter = (int) letterToCheck;
		if (asciiCodeOfTheLetter >= 1040 && asciiCodeOfTheLetter <= 1103) {
			converToLowerLetters(letterToCheck, cityLetters);
			fillBlankSpacesOrAddOneInConditionForTheEnd(cityLetters,
					cityNameWithBlankSpaces, letterToCheck);
			isTheLetterIncludedInTheCityName = false;
			printCityNameWithBlankSpaces(cityNameWithBlankSpaces);
			attemptsCases();

			isTheEnd();
		} else {
			System.out.println("Въведете буква на кирилица!");
		}
		return attempts;
	}

	public static int fillBlankSpacesOrAddOneInConditionForTheEnd(char[] cityLetters,
			char[] cityNameWithBlankSpaces, char letterToCheck) {
		if (isTheLetterIncludedInTheCityName) {
			for (int i = 1; i < cityNameWithBlankSpaces.length; i++) {
				if (cityLetters[i] == letterToCheck) {
					cityNameWithBlankSpaces[i] = cityLetters[i];
				}
			}
		} else {
			attempts++;
		}
		return attempts;
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
	
	public static void isTheEnd() {
		int k = 1;
		isEndOfTheGame = false;
		while (k < cityNameWithBlankSpaces.length) {
			if (cityNameWithBlankSpaces[k] == '_') {
				isEndOfTheGame = false;
				break;
			} else {
				isEndOfTheGame = true;
			}
			k++;
		}
	}

	public static void printCityNameWithBlankSpaces(char[] cityNameWithBlankSpaces) {
		for (int i = 0; i < cityNameWithBlankSpaces.length; i++) {
			System.out.print(cityNameWithBlankSpaces[i]);
		}
		System.out.println();
	}
	
	public static void converToLowerLetters(char letterToCheck, char[] cityLetters) {
		letterToCheck = Character.toLowerCase(letterToCheck);
		int br = 1;
		while (br < cityLetters.length) {
			if (cityLetters[br] == letterToCheck) {
				isTheLetterIncludedInTheCityName = true;
			}
			br++;
		}
	}
}
