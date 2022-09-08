package web.fundraising.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.google.gson.Gson;

import web.fundraising.dao.ProposalDAO;
import web.fundraising.dao.ProposalDAOhibernateHQL;
import web.fundraising.vo.ProposalBean;

public class ProposalService {
	private ProposalDAO proposalDAO;
	private HttpServletResponse res;
	
	public ProposalService(HttpServletResponse res) {
		this.res = res;
		this.proposalDAO = new ProposalDAOhibernateHQL();
	}
	
	public ProposalBean insertBean(ProposalBean proposalBean) {
		return this.proposalDAO.insert(proposalBean);
	}
	public Boolean deleteBean(Integer id) {
		return this.proposalDAO.delete(id);
	}
	public ProposalBean updateBean(Integer id, ProposalBean proposalBean) {
		ProposalBean bean = this.proposalDAO.update(id, proposalBean).renewBean(this.proposalDAO);
		return bean;
	}
	public ProposalBean selectBean(Integer id) {
		ProposalBean bean = this.proposalDAO.select(id).renewBean(this.proposalDAO);
		return bean;
	}
	
	public List<ProposalBean> selectMyAllBeans(String memID) throws IOException {
		try {
			List<ProposalBean> list_Proposal = this.proposalDAO.selectAll();
			List<ProposalBean> myProposalBeans = new ArrayList<ProposalBean>();
	    	for(ProposalBean b : list_Proposal) {
	    		if(memID.equals(b.getMemID())) {
	    			b.renewBean(this.proposalDAO);
	    			myProposalBeans.add(b);
	    		}
	    	}
	    	return myProposalBeans;
		} catch (Exception e) {
			System.out.println("false");
			return null;
		}
	}
	
	public List<ProposalBean> selectAllBeans() throws IOException {
		try {
			List<ProposalBean> list_Proposal = this.proposalDAO.selectAll();
	    	for(ProposalBean b : list_Proposal) {
	    		b.renewBean(this.proposalDAO);
	    	}
	    	return list_Proposal;
		} catch (Exception e) {
			System.out.println("false");
			return null;
		}
	}

}
