package ru.stqa.javacourse.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@XStreamAlias("group")
@Entity//объявляет GroupData привязанной к базе
@Table(name="group_list")//привязка к таблице // если бы название столбца совпадало с названием атрибута, то доп привязка бы была не нужна
public class GroupData {

	@XStreamOmitField // - убрать следующее поле из xml
	@Id // так как это id, присваивается особая аннотация
	@Column(name = "group_id")//привязка к столбцу таблицы // если бы название столбца совпадало с названием атрибута, то доп привязка бы была не нужна // id vs group_id
	private int id = Integer.MAX_VALUE;

	@Expose // поле помеченное для добавления в json
	@Column(name = "group_name")
	private  String name;

	@Expose
	@Column(name = "group_header")
	private  String header;

	@Expose
	@Column(name = "group_footer")
	private  String footer;

	public int getId() {return id;}

	public String getName() {
		return name;
	}

	public String getHeder() {
		return header;
	}

	public String getFooter() {
		return footer;
	}

	public GroupData withId(int id) {
		this.id = id;
		return this;
	}

	public GroupData withName(String name) {
		this.name = name;
		return this;
	}

	public GroupData withHeader(String header) {
		this.header = header;
		return this;
	}

	public GroupData withFooter(String footer) {
		this.footer = footer;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GroupData groupData = (GroupData) o;

		if (id != groupData.id) return false;
		return name != null ? name.equals(groupData.name) : groupData.name == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}


	@Override
	public String toString() {
		return "GroupData{" +
				"name='" + name + '\'' +
				", id=" + id +
				'}';
	}
}
