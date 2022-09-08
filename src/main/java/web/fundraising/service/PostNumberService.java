package web.fundraising.service;

import java.util.List;

import org.hibernate.Session;

import web.fundraising.dao.PostNumberDAO;
import web.fundraising.dao.PostNumberDAOhibernateHQL;
import web.fundraising.vo.PostNumberBean;

public class PostNumberService {
	PostNumberDAO postnumberDAO;
	
	public PostNumberService() {
		this.postnumberDAO = new PostNumberDAOhibernateHQL();
	}
	
	
	public PostNumberBean selectBean(Integer post_SID) {
		return this.postnumberDAO.select(post_SID);
	}
	public List<PostNumberBean> selectAllBeans() {
		return this.postnumberDAO.selectAll();
	}
}
