package web.fundraising.dao;

import java.util.List;

import web.fundraising.vo.StatusBean;

public interface StatusDAO {
	public abstract StatusBean insert(StatusBean statusBean);
	public abstract Boolean delete(String id);
	public abstract StatusBean update(String id, StatusBean statusBean);
	public abstract StatusBean select(String id);
	public abstract List<StatusBean> selectAll();
}
