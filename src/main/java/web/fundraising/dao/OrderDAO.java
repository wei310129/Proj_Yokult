package web.fundraising.dao;

import java.util.List;

import org.hibernate.Session;

import web.fundraising.vo.OrderBean;

public interface OrderDAO {
	public abstract OrderBean insert(OrderBean orderBean);
	public abstract Boolean delete(Integer id);
	public abstract OrderBean update(Integer id, OrderBean orderBean);
	public abstract OrderBean select(Integer id);
	public abstract List<OrderBean> selectAll();
	public abstract Integer selectLastID();
}
