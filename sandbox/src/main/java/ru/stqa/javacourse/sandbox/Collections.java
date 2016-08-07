package ru.stqa.javacourse.sandbox;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Collections  {
	public static void main(String[] args){
		String[] langs = {"C#", "Java", "Python", "php"};
		/*	List<String> languages = new ArrayList<String>();
		languages.add("Java");
		languages.add("C#");
		languages.add("Python");
		languages.add("php");
		*/
		List<String> languages = Arrays.asList("Java", "C#", "Python", "php");
		for (int i = 0; i < langs.length; i++){
			System.out.println("Я хочу выучить "+langs[i]);
		}
		for (String l : langs){
			System.out.println("Я хочу выучить "+l);
		}
		for (String l: languages){
			System.out.println("Я хочу выучить "+l);
		}
		for (int i = 0; i < languages.size(); i++){
			System.out.println("Я хочу выучить "+languages.get(i));
		}
		// Object
		List languagesObj = Arrays.asList("Java", "C#", "Python", "php");
		for (Object l: languagesObj){
			System.out.println("Я хочу выучить "+l);
		}
	}
}
