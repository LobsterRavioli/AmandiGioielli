package utils;

import java.io.File;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Utility {

    public static void print(String... messages) {
	String message = "";
	for (String s : messages) {
	    message += s + "\n";
	}
	System.out.printf("%s", message);
    }

    public static void print(Exception exception) {
	Utility.print("Exception: " + exception.getMessage());
	exception.printStackTrace();
    }

    public static String createRandomCode(int codeLength, String id) {
	List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
	Collections.shuffle(temp, new SecureRandom());
	return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
    }

    public static boolean isUnique(String fileName, String path) {

	File[] files = new File(path).listFiles();
	if (files == null || files.length == 0)
	    return true;
	for (File file : files) {
	    System.out.println(file.getName());
	    if (file.getName().equals(fileName))
		return false;
	}
	return true;
    }

}