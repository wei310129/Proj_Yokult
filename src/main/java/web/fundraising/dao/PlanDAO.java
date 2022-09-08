package web.fundraising.dao;

import java.util.List;

import web.fundraising.vo.OrderBean;
import web.fundraising.vo.PlanBean;
import web.fundraising.vo.ProposalBean;

public interface PlanDAO {
	public abstract PlanBean insert(PlanBean planBean);
	public abstract Boolean delete(Integer id);
	public abstract PlanBean update(Integer id, PlanBean planBean);
	public abstract List<PlanBean> selectAllBeansByProposal(ProposalBean proposalBean);
	public abstract List<PlanBean> selectAll();
	public abstract List<OrderBean> getRelationalOrders(PlanBean planBean);
//	public abstract void SessionClose();
}
