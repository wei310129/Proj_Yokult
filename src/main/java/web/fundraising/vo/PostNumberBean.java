package web.fundraising.vo;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import web.fundraising.dao.PlanDAO;
import web.fundraising.dao.PostDAO;

@Entity
@Table(name="Fund_POSTNUMBER")
public class PostNumberBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_SID;
	private String postCity;
	private String postArea;
	private String postNumber;
	
	
	public PostNumberBean() {}


	@Override
	public String toString() {
		return "PostNumberBean [post_SID=" + post_SID + ", postCity=" + postCity + ", postArea=" + postArea
				+ ", postNumber=" + postNumber + "]";
	}


	public Integer getPost_SID() {
		return post_SID;
	}

	public String getPostCity() {
		return postCity;
	}

	public String getPostArea() {
		return postArea;
	}

	public String getPostNumber() {
		return postNumber;
	}



	
	
}
