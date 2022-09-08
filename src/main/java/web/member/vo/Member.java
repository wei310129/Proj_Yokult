package web.member.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class Member {
	@Id
	@Column(name = "MEMID")
	private String memID;
	
	@Column(name = "PASSWORD")
	private String memPassword;
	
	@Column(name = "EMAIL")
	private String memEmail;
	
	@Column(name = "FIRSTNAME")
	private String memFirstName;
	
	@Column(name = "LASTNAME")
	private String memLastName;
	
	@Column(name = "BIRTH", nullable = true)
	private Date memBirth;
	
	@Column(name = "CELLPHONE", length = 10)
	private String memCellPhone;
	
	@Column(name = "ADDR", nullable = true)
	private String memAddress;


	@Override
	public String toString() {
		return "Member [memID=" + memID + ", memFirstName=" + memFirstName + ", memLastName=" + memLastName + "]";
	}

	public Member() {

	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	
	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemFirstName() {
		return memFirstName;
	}

	public void setMemFirstName(String memFirstName) {
		this.memFirstName = memFirstName;
	}

	public String getMemLastName() {
		return memLastName;
	}

	public void setMemLastName(String memLastName) {
		this.memLastName = memLastName;
	}

	public Date getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemCellPhone() {
		return memCellPhone;
	}

	public void setMemCellPhone(String memCellPhone) {
		this.memCellPhone = memCellPhone;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

}
