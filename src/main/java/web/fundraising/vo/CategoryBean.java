package web.fundraising.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Fund_CATEGORY")
public class CategoryBean implements Serializable {
	@Id
	private String categoryID;
	private String categoryName;
	
	public CategoryBean() {}
	
	public CategoryBean(String categoryID, String categoryName) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "CategoryBean [categoryID=" + categoryID + ", categoryName=" + categoryName + "]";
	}
	
	public String getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
