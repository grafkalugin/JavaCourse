package ru.stqa.javacourse.addressbook.model;

public class ContactData {
	private final String firstname;
	private final String middlename;
	private final String lastname;
	private final String nickname;
	private final String address;
	private final String phone;
	private final String email;
	private  String group;
	private int id;


	public ContactData(int id, String lastname, String firstname, String middlename, String nickname, String address, String phone, String email, String group) {
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.nickname = nickname;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.group = group;
		this.id = id;
	}

/*	public ContactData(int id, String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
	} */

	@Override
	public String toString() {
		return "ContactData{" +
				"firstname='" + firstname + '\'' +
				", middlename='" + middlename + '\'' +
				", lastname='" + lastname + '\'' +
				", nickname='" + nickname + '\'' +
				", address='" + address + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", group='" + group + '\'' +
				", id=" + id +
				'}';
	}


	public String getFirstname() {
		return firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public String getNickname() {
		return nickname;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public String getGroup() { return group; }

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

	}

	@Override
	public int hashCode() {
		int result = firstname != null ? firstname.hashCode() : 0;
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		return result;
	}
}
