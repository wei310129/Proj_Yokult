package web.fundraising.service;

import java.util.List;

import org.hibernate.Session;

import web.fundraising.dao.PostDAO;
import web.fundraising.dao.PostDAOhibernateHQL;
import web.fundraising.vo.PlanBean;
import web.fundraising.vo.PostBean;

public class PostService {
	PostDAO postDAO;
	
	public PostService() {
		this.postDAO = new PostDAOhibernateHQL();
	}
	
	public PostBean insertBean(PostBean postBean) {
		return this.postDAO.insert(postBean);
	}
	public Boolean deleteBean(Integer id) {
		return this.postDAO.delete(id);
	}
	public PostBean updateBean(Integer id, PostBean postBean) {
		return this.postDAO.update(id, postBean);
	}
	public List<PostBean> selectAllBeansByMemberID(String memID) {
		return this.postDAO.selectAllBeansByMemberID(memID);
	}
	public List<PostBean> selectAllBeans() {
		return this.postDAO.selectAll();
	}
}
