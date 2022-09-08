package web.fundraising.dao;

import java.util.List;

import web.fundraising.vo.ProposalBean;

public interface ProposalDAO {
	public abstract ProposalBean insert(ProposalBean proposalBean);
	public abstract Boolean delete(Integer id);
	public abstract ProposalBean update(Integer id, ProposalBean proposalBean);
	public abstract ProposalBean select(Integer id);
	public abstract List<ProposalBean> selectAll();
	public abstract Integer currentTotalAmount(ProposalBean proposalBean);
}
