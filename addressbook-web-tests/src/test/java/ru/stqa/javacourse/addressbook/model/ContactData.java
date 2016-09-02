package ru.stqa.javacourse.addressbook.model;

import com.google.gson.annotations.Expose;

import java.io.File;

public class ContactData {
	@Expose // поле помеченное для добавления в json
	private String firstname;
	private String middlename;
	@Expose
	private String lastname;
	private String nickname;
	@Expose
	private String address;
	private String phone;
	@Expose
	private String email;
	@Expose
	private String email2;
	@Expose
	private String email3;
	private String group;
	@Expose
	private String homePhone;
	@Expose
	private String mobilePhone;
	@Expose
	private String workPhone;
	private String allPhones;
	private String allEmails;
	private String allDataFromInfoPage;
	private File photo;
	int id = Integer.MAX_VALUE;

	public String getAllPhones() {return allPhones;}

	public String getAllEmails() {return allEmails;}

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

	public String getAddress() {return address;}

	public String getPhone() {return phone;}

	public String getEmail() {return email;}

	public String getEmail2() {return email2;}

	public String getEmail3() {return email3;}

	public String getGroup() {return group;}

	public int getId() {
		return id;
	}

	public String getWorkPhone(){return workPhone;}

	public String getHomePhone() {return homePhone;}

	public String getMobilePhone() {return mobilePhone;}

	public String getAllDataFromInfoPage() {return allDataFromInfoPage;}

	public File getPhoto() {return photo;}

	public ContactData withLastname(String lastname) {
		this.lastname = lastname;
		return this;
	}

	public ContactData withFirstname(String firstname) {
		this.firstname = firstname;
		return this;
	}

	public ContactData withMiddlename(String middlename) {
		this.middlename = middlename;
		return this;
	}

	public ContactData withNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}

	public ContactData withAddress(String address) {
		this.address = address;
		return this;
	}

	public ContactData withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}

	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withEmail3(String email3) {
		this.email3 = email3;
		return this;
	}

	public ContactData withGroup(String group) {
		this.group = group;
		return this;
	}

	public ContactData withId(int id) {
		this.id = id;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withWorkPhone(String workPhone){
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withHomePhone(String homePhone) {
		this.homePhone = homePhone;
		return this;
	}

	public ContactData withAllPhones(String allPhones) {
		this.allPhones = allPhones;
		return this;
	}

	public ContactData withAllEmails(String allEmail) {
		this.allEmails = allEmail;
		return this;
	}

	public ContactData withAllDataFromInfoPage(String allDataFromInfoPage) {
		this.allDataFromInfoPage = allDataFromInfoPage;
		return this;
	}
	public ContactData withPhoto(File photo) {
		this.photo = photo;
		return this;
	}

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ContactData that = (ContactData) o;

		if (id != that.id) return false;
		if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
		return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

	}

	@Override
	public int hashCode() {
		int result = firstname != null ? firstname.hashCode() : 0;
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + id;
		return result;
	}

}
