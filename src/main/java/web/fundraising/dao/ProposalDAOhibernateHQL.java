package web.fundraising.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;
import web.fundraising.vo.OrderBean;
import web.fundraising.vo.ProposalBean;

public class ProposalDAOhibernateHQL implements ProposalDAO {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.getCurrentSession();
	
	public ProposalDAOhibernateHQL() {}
	
	@Override
	public ProposalBean insert(ProposalBean proposalBean) {
		if(proposalBean != null && proposalBean.getProposalID() == null) {			
			this.session.save(proposalBean);
			return proposalBean;				
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		if(id != null && id >0) {
			ProposalBean delete = this.session.get(ProposalBean.class, id);
			if(delete != null) {
				this.session.delete(delete);
				return true;
			}
		}
		return null;
	}

	@Override
	public ProposalBean update(Integer id, ProposalBean proposalBean) {
		if(id != null && id > 0 && proposalBean != null && proposalBean.getProposalID() == null) {			
			ProposalBean update = this.session.get(ProposalBean.class, id);
			if(update != null) {
				this.session.save(update);
				return update;				
			}
		}
		return null;
	}

	@Override
	public ProposalBean select(Integer id) {
		if(id != null && id >0) {
			return this.session.get(ProposalBean.class, id);
		}
		return null;
	}

	@Override
	public List<ProposalBean> selectAll() {
		Query<ProposalBean> qurey = this.session.createQuery("from ProposalBean", ProposalBean.class);
		List<ProposalBean> result = qurey.list();
		return result;
	}
	
	@Override
	public Integer currentTotalAmount(ProposalBean proposalBean) {
		Query<Integer> qurey = this.session.createQuery("select orderAmount from OrderBean where proposalID = "+ proposalBean.getProposalID(), Integer.class);
		List<Integer> orderAmount_list = qurey.list();
		Integer total = 0;
		for(Integer a: orderAmount_list) {
			total += a;
		}
		return total;
	}
	
}
