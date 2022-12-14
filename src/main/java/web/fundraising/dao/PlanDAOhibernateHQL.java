package web.fundraising.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;
import web.fundraising.vo.OrderBean;
import web.fundraising.vo.PlanBean;
import web.fundraising.vo.ProposalBean;

public class PlanDAOhibernateHQL implements PlanDAO {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.getCurrentSession();
	
	public PlanDAOhibernateHQL() {}

	
	@Override
	public PlanBean insert(PlanBean planBean) {
		if(planBean != null && planBean.getPlanID() == null) {			
			this.session.save(planBean);
			return planBean;				
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		if(id != null && id >0) {
			PlanBean delete = this.session.get(PlanBean.class, id);
			if(delete != null) {
				this.session.delete(delete);
				return true;
			}
		}
		return null;
	}

	@Override
	public PlanBean update(Integer id, PlanBean planBean) {
		if(id != null && id > 0 && planBean != null && planBean.getPlanID() == null) {			
			PlanBean update = this.session.get(PlanBean.class, id);
			if(update != null) {
				this.session.save(update);
				return update;				
			}
		}
		return null;
	}

	@Override
	public List<PlanBean> selectAllBeansByProposal(ProposalBean proposalBean) {
		Query<PlanBean> qurey = this.session.createQuery("from PlanBean where proposalID =" + proposalBean.getProposalID(), PlanBean.class);
		List<PlanBean> result = qurey.list();
		return result;
	}

	@Override
	public List<PlanBean> selectAll() {
		Query<PlanBean> qurey = this.session.createQuery("from PlanBean", PlanBean.class);
		List<PlanBean> result = qurey.list();
		return result;
	}
	
	
	
	//新增取得所有該plan相關的訂單
	public List<OrderBean> getRelationalOrders(PlanBean planBean) {
		Query<OrderBean> qurey = this.session.createQuery("from OrderBean where planID =" + planBean.getPlanID(), OrderBean.class);
		List<OrderBean> result = qurey.list();
		return result;
	}
	
}
