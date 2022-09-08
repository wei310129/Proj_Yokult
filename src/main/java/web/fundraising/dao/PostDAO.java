package web.fundraising.dao;

import java.util.List;

import web.fundraising.vo.PostBean;

public interface PostDAO {
	public abstract PostBean insert(PostBean postBean);
	public abstract Boolean delete(Integer id);
	public abstract PostBean update(Integer id, PostBean postBean);
	public List<PostBean> selectAllBeansByMemberID(String memID);
	public abstract List<PostBean> selectAll();
}
