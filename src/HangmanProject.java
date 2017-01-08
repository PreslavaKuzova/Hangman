import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanProject {

	public static void main(String[] args) throws FileNotFoundException {
		int attempts = 0;
		final int ATTEMPTS_BEFORE_HUNG = 6;

		Scanner s = new Scanner(new File("C:/Users/pres/Desktop/places.txt"));
		ArrayList<String> populatedPlaces = new ArrayList<String>();
		while (s.hasNextLine()) {
			populatedPlaces.add(s.nextLine());
		}
		s.close();

		Random randomCity = new Random();
		int index = randomCity.nextInt(populatedPlaces.size());
		String city = populatedPlaces.get(index);

		char[] cityLetters = city.toCharArray();

		char[] cityNameWithBlankSpaces = new char[cityLetters.length];
		cityNameWithBlankSpaces[0] = cityLetters[0];

		for (int i = 1; i < cityLetters.length; i++) {
			if (cityLetters[i] == ' ') {
				cityNameWithBlankSpaces[i] = ' ';
				cityNameWithBlankSpaces[i + 1] = cityLetters[i + 1];
				i++;
			} else {
				cityNameWithBlankSpaces[i] = '_';
			}

		}

		Scanner input = new Scanner(System.in);
		boolean isTheLetterIncludedInTheCityName = false;
		boolean isEndOfTheGame = false;
		int br = 1, k = 1;
		
		printCityNameWithBlankSpaces(cityNameWithBlankSpaces);
		//check if the symbol is between ascii code of 'a' to ascii code of 'я'
		while (attempts < ATTEMPTS_BEFORE_HUNG && isEndOfTheGame == false) {
			System.out.println("Въведете буква: ");
			char letterToCheck = input.next(".").charAt(0);
			
			while (br < cityLetters.length) {
				if (cityLetters[br] == letterToCheck) {
					isTheLetterIncludedInTheCityName = true;
				}
				br++;
			}
			
			//fill blank gaps or add one to the condition for end of the game
			if (isTheLetterIncludedInTheCityName) {
				for (int i = 1; i < cityNameWithBlankSpaces.length; i++) {
					if (cityLetters[i] == letterToCheck) {
						cityNameWithBlankSpaces[i] = cityLetters[i];
					}
				}
			} else {
				attempts++;
			}
			
			printCityNameWithBlankSpaces(cityNameWithBlankSpaces);
			
			//check whether it is the end of the game 
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
		 
		
		if (isEndOfTheGame) {
			System.out.println("You win!");
		} else {
			System.out.println("You were hung! Your word to guess was " + city);
		} 

	}

	public static void printCityNameWithBlankSpaces(char[] cityNameWithBlankSpaces) {
		for (int i = 0; i < cityNameWithBlankSpaces.length; i++) {
			System.out.print(cityNameWithBlankSpaces[i]);
		}
		System.out.println();
	}
}
