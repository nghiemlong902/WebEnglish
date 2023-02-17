package com.app.model.response;

import java.text.SimpleDateFormat;
import java.util.List;

import com.app.model.DateLearn;

public class RoomResponse {
	private long id;
	private String name;
	private long teacherId;
	private String teacherName;
	private long courseId;
	private String courseName;
	private String dateStr;
	private List<DateLearn> lstDates;


	public RoomResponse() {

	}
	public RoomResponse(long id, String name, long techerId, String techerName, long courseId, String courseName,
			List<DateLearn> lstDates) {

		this.id = id;
		this.name = name;
		this.teacherId = techerId;
		this.teacherName = techerName;
		this.courseId = courseId;
		this.courseName = courseName;

		this.lstDates = lstDates;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public long getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(long teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public List<DateLearn> getLstDates() {
		return lstDates;
	}
	public void setLstDates(List<DateLearn> lstDates) {
		this.lstDates = lstDates;
	}

	public void convert() {
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		this.dateStr = "";
		for(DateLearn dateLearn: lstDates) {
			this.dateStr += dateTimeFormat.format(dateLearn.getDate()) + ", ";
		}

	}

}
