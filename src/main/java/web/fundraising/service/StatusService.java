package web.fundraising.service;

import java.util.List;

import org.hibernate.Session;

import web.fundraising.dao.StatusDAO;
import web.fundraising.dao.StatusDAOhibernateHQL;
import web.fundraising.vo.StatusBean;

public class StatusService {
	StatusDAO statusDAO;
	
	public StatusService() {
		this.statusDAO = new StatusDAOhibernateHQL();
	}
	
	public StatusBean insertBean(StatusBean statusBean) {
		return this.statusDAO.insert(statusBean);
	}
	public Boolean deleteBean(String id) {
		return this.statusDAO.delete(id);
	}
	public StatusBean updateBean(String id, StatusBean statusBean) {
		return this.statusDAO.update(id, statusBean);
	}
	public StatusBean selectBean(String id) {
		return this.statusDAO.select(id);
	}
	public List<StatusBean> selectAllBeans() {
		return this.statusDAO.selectAll();
	}
}
