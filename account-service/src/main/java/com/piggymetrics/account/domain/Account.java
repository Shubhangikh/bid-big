package com.piggymetrics.account.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {

    @Id
	private String name;

	private Date lastSeen;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL, optional = false)
	private Profile profile;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
}
