package web.fundraising.service;

import java.util.List;

import org.hibernate.Session;

import web.fundraising.dao.CategoryDAO;
import web.fundraising.dao.CategoryDAOhibernateHQL;
import web.fundraising.vo.CategoryBean;

public class CategoryService {
	CategoryDAO categoryDAO;
	
	public CategoryService() {
		this.categoryDAO = new CategoryDAOhibernateHQL();
	}
	
	public CategoryBean insertBean(CategoryBean categoryBean) {
		return this.categoryDAO.insert(categoryBean);
	}
	public Boolean deleteBean(String id) {
		return this.categoryDAO.delete(id);
	}
	public CategoryBean updateBean(String id, CategoryBean categoryBean) {
		return this.categoryDAO.update(id, categoryBean);
	}
	public CategoryBean selectBean(String id) {
		return this.categoryDAO.select(id);
	}
	public List<CategoryBean> selectAllBeans() {
		return this.categoryDAO.selectAll();
	}
}
