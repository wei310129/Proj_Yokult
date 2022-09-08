package web.fundraising.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import web.fundraising.dao.PlanDAO;
import web.fundraising.dao.PlanDAOhibernateHQL;
import web.fundraising.vo.OrderBean;
import web.fundraising.vo.PlanBean;
import web.fundraising.vo.ProposalBean;

public class PlanService {
	private PlanDAO planDAO;
	private HttpServletResponse res;
	
	public PlanService(HttpServletResponse res) {
		this.planDAO = new PlanDAOhibernateHQL();
		this.res = res;
	}

	public PlanBean insertBean(PlanBean planBean) {
		return this.planDAO.insert(planBean);
	}
	public Boolean deleteBean(int id) {
		return this.planDAO.delete(id);
	}
	public PlanBean updateBean(int id, PlanBean planBean) {
		return this.planDAO.update(id, planBean);
	}
	public List<PlanBean> selectBeansByProposal(ProposalBean proposalBean) {
		List<PlanBean> list_planBean = this.planDAO.selectAllBeansByProposal(proposalBean);
		for(PlanBean planBean : list_planBean) {
			planBean.renewBean(planDAO);
		}
		return list_planBean;
	}
	public List<PlanBean> selectAllBeans() {
		return this.planDAO.selectAll();
	}
}
