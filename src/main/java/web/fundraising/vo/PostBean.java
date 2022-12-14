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
import web.fundraising.service.PostNumberService;

@Entity
@Table(name="Fund_POST")
public class PostBean implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	private String postFisrtName;
	private String postLastName;
	private String postCellphone;
	private Integer post_SID;
	private String postAddress;
	private String memID;
	@Transient
	private String postCity;
	@Transient
	private String postArea;
	@Transient
	private String postNumber;
	
	public void renew(PostNumberService postnumberService) {
		PostNumberBean postnumberBean = postnumberService.selectBean(this.post_SID);
		this.postCity = postnumberBean.getPostCity();
		this.postArea = postnumberBean.getPostArea();
		this.postNumber = postnumberBean.getPostNumber();
		
	}
	
	public PostBean() {}
	
	public PostBean(String postFisrtName, String postLastName, String postCellphone, Integer post_SID,
			String postAddress, String memID) {
		super();
		this.postFisrtName = postFisrtName;
		this.postLastName = postLastName;
		this.postCellphone = postCellphone;
		this.post_SID = post_SID;
		this.postAddress = postAddress;
		this.memID = memID;
	}

	public PostBean(Integer postID, String postFisrtName, String postLastName, String postCellphone, Integer post_SID,
			String postAddress, String memID) {
		super();
		this.postID = postID;
		this.postFisrtName = postFisrtName;
		this.postLastName = postLastName;
		this.postCellphone = postCellphone;
		this.post_SID = post_SID;
		this.postAddress = postAddress;
		this.memID = memID;
	}

	@Override
	public String toString() {
		return "PostBean [postID=" + postID + ", postFisrtName=" + postFisrtName + ", postLastName=" + postLastName
				+ ", postCellphone=" + postCellphone + ", post_SID=" + post_SID + ", postAddress=" + postAddress
				+ ", memID=" + memID + "]";
	}

	public Integer getPostID() {
		return postID;
	}

	public String getPostFisrtName() {
		return postFisrtName;
	}

	public void setPostFisrtName(String postFisrtName) {
		this.postFisrtName = postFisrtName;
	}

	public String getPostLastName() {
		return postLastName;
	}

	public void setPostLastName(String postLastName) {
		this.postLastName = postLastName;
	}

	public String getPostCellphone() {
		return postCellphone;
	}

	public void setPostCellphone(String postCellphone) {
		this.postCellphone = postCellphone;
	}

	public Integer getPost_SID() {
		return post_SID;
	}

	public void setPost_SID(Integer post_SID) {
		this.post_SID = post_SID;
	}

	public String getPostAddress() {
		return postAddress;
	}

	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}
	

	
	
}
