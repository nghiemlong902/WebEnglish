package com.app.model.response;

import java.math.BigDecimal;

import com.app.model.Users;

public class RoomUserResponse {
	private long id;
	private Users users;
	private BigDecimal point;

	private RoomResponse room;


	public RoomUserResponse() {

	}
	public RoomUserResponse(long id, Users users, BigDecimal point,  RoomResponse room) {

		this.id = id;
		this.users = users;
		this.point = point;

		this.room = room;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public BigDecimal getPoint() {
		return point;
	}
	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public RoomResponse getRoom() {
		return room;
	}
	public void setRoom(RoomResponse room) {
		this.room = room;
	}


}
