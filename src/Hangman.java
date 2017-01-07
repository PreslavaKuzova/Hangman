import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
		int attempts = 0;
		final int ATTEMPTS_BEFORE_HANGED = 6;
		char [] cityBlankSpaces = new char [30];
		
		ArrayList<String> populatedPlaces = createCityCatalogue();
		String city = chooseARandomCity(populatedPlaces);
		char[] cityLetters = separateCityLetters(city);		
		cityNameWithBlankSpaces(cityLetters, cityBlankSpaces);
		
		while (attempts < ATTEMPTS_BEFORE_HANGED) {
			Scanner input = new Scanner(System.in);
			System.out.println("Въведете буква: ");
			char letterToCheck = input.next(".").charAt(0);
			for (int i = 1; i < cityLetters.length; i++) {
				if (cityLetters[i] == letterToCheck) {
					cityBlankSpaces[i] = cityLetters[i];
				} else {
					attempts++;
				}
			}
			printCityName(cityBlankSpaces);
		}
		
	}



	public static void cityNameWithBlankSpaces(char[] cityLetters, char[] cityBlankSpaces) {
		cityBlankSpaces = new char [cityLetters.length];
		cityBlankSpaces[0] = cityLetters[0];
		for (int i = 1; i < cityLetters.length; i++) {
			i = fillBlankSpaces(cityLetters, cityBlankSpaces, i);
		}
		printCityName(cityBlankSpaces);
	}



	public static int fillBlankSpaces(char[] cityLetters, char[] cityBlackSpaces, int i) {
		if (cityLetters[i] == ' ') {
			cityBlackSpaces[i] = ' ';
			cityBlackSpaces[i+1] = cityLetters[i+1];
			i++;
		} else
			cityBlackSpaces[i] = '_';
		return i;
	}

	public static void printCityName(char[] cityBlankSpaces) {
		for (int i = 0; i < cityBlankSpaces.length; i++) {
			System.out.print(cityBlankSpaces[i]);
		}
		System.out.println();
	}

	public static char[] separateCityLetters(String city) {
		char[] cityLetters = city.toCharArray();
		return cityLetters;
	}

	public static String chooseARandomCity(ArrayList<String> populatedPlaces) {
		Random randomCity = new Random();
		int index = randomCity.nextInt(populatedPlaces.size());
		String city = populatedPlaces.get(index);
		return city;
	}

	public static ArrayList<String> createCityCatalogue() throws FileNotFoundException {
		Scanner s = new Scanner(new File("C:/Users/pres/Desktop/places.txt"));
		ArrayList<String> populatedPlaces = new ArrayList<String>();
		while (s.hasNextLine()) {
			populatedPlaces.add(s.nextLine());
		}
		s.close();
		return populatedPlaces;
	}


}