package web.fundraising.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Fund_STATUS")
public class StatusBean implements Serializable {
	@Id
	private String statusID;
	private String statusName;
	
	public StatusBean() {}

	public StatusBean(String statusID, String statusName) {
		super();
		this.statusID = statusID;
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "ProposalStatus [statusID=" + statusID + ", statusName=" + statusName + "]";
	}
	
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
}
