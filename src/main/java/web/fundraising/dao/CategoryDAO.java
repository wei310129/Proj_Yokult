package web.fundraising.dao;

import java.util.List;

import web.fundraising.vo.CategoryBean;

public interface CategoryDAO {
	public abstract CategoryBean insert(CategoryBean categoryBean);
	public abstract Boolean delete(String id);
	public abstract CategoryBean update(String id, CategoryBean categoryBean);
	public abstract CategoryBean select(String id);
	public abstract List<CategoryBean> selectAll();
}
