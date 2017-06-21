package it.polimi.ingsw.cg30.model.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Util {
	/**
	 * the logger
	 */
	private static final Logger LOGGER = Logger.getLogger(Util.class.getName());
	/**
	 * choice one string
	 */
	public static final String CHOICE_ONE = "1";
	/**
	 * choice two string
	 */
	public static final String CHOICE_TWO = "2";
	/**
	 * choice three string
	 */
	public static final String CHOICE_THREE = "3";
	/**
	 * choice four string
	 */
	public static final String CHOICE_FOUR = "4";
	/**
	 * not legit action string
	 */
	public static final String NOT_LEGIT_ACTION = "Not legit action!";
	/**
	 * private constructor
	 */
	private Util() {

	}
	/**
	 * Shows the exception in logger.
	 *
	 * @param e the error 
	 */
	public static void exception(Throwable e) {
		LOGGER.log(Level.WARNING, e.getMessage(), e);
	}
	/**
	 * Shows a message and the exception message surrounded by parenthesis.
	 *
	 * @param e
	 *            the e
	 * @param message
	 *            the message
	 */
	public static void exception(Throwable e, String message) {
		println(message + " (" + e.getMessage() + ")");
	}
	/**
	 * Prints a String and then terminate the line.
	 * @param message the message
	 */
	public static void println(String message) {
		System.out.println(message);
	}
	/**
	 * Prints a string.
	 * @param message the message
	 */
	public static void print(String message) {
		System.out.print(message);
	}
	/**
	 * print a string with printf function
	 * @param message the string to print
	 * @param object the object 
	 */
	public static void printf(String message, Object object) {
		System.out.printf(message, object);
	}
	/**
	 * return true if the string cointains an integer, catch the
	 * exception if is not an integer an return false
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException nfe) {
			exception(nfe);
		}
		return false;
	}
	/**
	 * this method return true if the string has duplicates
	 * false if not
	 * @param list
	 * @return
	 */
	public static boolean containsDuplicate(List<String> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (i != j && list.get(i) == list.get(j)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @param choice    
	 * @param length of the considered array
	 * @return false if the choice is out of bound or null
	 */
	public static boolean isLegitChoice(String choice, int length) {

		if (Util.isInteger(choice) && (Integer.parseInt(choice) < length + 1 && Integer.parseInt(choice) > 0)) {
			return true;
		}
		return false;
	}
	/**
	 * this method returns true if all list a is contained in b
	 * @param a
	 * @param b
	 * @return
	 */
	public static <T> boolean aIsCointainedInB(List<T> a, List<T> b) {
		
		if(a.size()>b.size()) {
			return false;
		}
		ArrayList<T> aCopy = new ArrayList<>(a);
			
			for (T t1 : b) {
				for (T t2 : aCopy) {
					if (t2.equals(t1)) {
						aCopy.remove(t2);
						break;
					}
				}
			} 
		return aCopy.isEmpty();
	}
	/**
	 * this methos return a list with all the names of the files 
	 * in the given path
	 * @param path
	 * @return
	 */
	public static List<String> getFilesInPath(String path){
		
		File curDir = new File(path);
		List<String> toReturn = new ArrayList<>();
		
		File[] filesList = curDir.listFiles();
        for(File f : filesList){
            if(f.isFile()){
                toReturn.add(f.getName());
            }
        }
        return toReturn;
	}
}