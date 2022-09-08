package web.fundraising.service;

import java.util.List;

import org.hibernate.Session;

import web.fundraising.dao.OrderDAO;
import web.fundraising.dao.OrderDAOhibernateHQL;
import web.fundraising.vo.OrderBean;

public class OrderService {
	OrderDAO OrderDAO;
	
	public OrderService() {
		this.OrderDAO = new OrderDAOhibernateHQL();
	}
	
	public OrderBean insertBean(OrderBean orderBean) {
		return this.OrderDAO.insert(orderBean);
	}
	public Boolean deleteBean(int id) {
		return this.OrderDAO.delete(id);
	}
	public OrderBean updateBean(int id, OrderBean orderBean) {
		return this.OrderDAO.update(id, orderBean);
	}
	public OrderBean selectBean(int id) {
		return this.OrderDAO.select(id);
	}
	public List<OrderBean> selectAllBeans() {
		return this.OrderDAO.selectAll();
	}
	public Integer selectLastID() {
		return this.OrderDAO.selectLastID();
	}
}
