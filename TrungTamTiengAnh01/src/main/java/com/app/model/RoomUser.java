package com.app.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="room_user")
public class RoomUser extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	private long roomId;

	@Column(name = "point",  columnDefinition = "Decimal(10,1)")
	private BigDecimal point;

	@Column(name = "price",  columnDefinition = "Decimal(10,2)")
	private BigDecimal price;

	@Column(name = "description", length = 255)
	private String description;




	public RoomUser() {
		super();
	}

	public RoomUser(Users users, long roomId,   BigDecimal price, int activeFlag) {
		super();
		super.setActiveFlag(activeFlag);
		this.users = users;
		this.roomId = roomId;
		this.price = price;
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

	public long getRoomId() {
		return roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	public BigDecimal getPoint() {
		return point;
	}

	public void setPoint(BigDecimal point) {
		this.point = point;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


}
