package ru.stqa.javacourse.addressbook;

public class GroupData {
	private final String name;
	private final String heder;
	private final String footer;

	public GroupData(String name, String heder, String footer) {
		this.name = name;
		this.heder = heder;
		this.footer = footer;
	}

	public String getName() {
		return name;
	}

	public String getHeder() {
		return heder;
	}

	public String getFooter() {
		return footer;
	}
}
