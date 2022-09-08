package web.fundraising.dao;

import java.util.List;

import web.fundraising.vo.PostNumberBean;

public interface PostNumberDAO {
	public abstract PostNumberBean select(Integer post_SID);
	public abstract List<PostNumberBean> selectAll();
}
