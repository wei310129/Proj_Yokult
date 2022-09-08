package web.fundraising.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;
import web.fundraising.vo.PlanBean;
import web.fundraising.vo.PostBean;
import web.fundraising.vo.ProposalBean;

public class PostDAOhibernateHQL implements PostDAO {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.getCurrentSession();
	
	public PostDAOhibernateHQL() {}
	
	@Override
	public PostBean insert(PostBean postBean) {
		if(postBean != null && postBean.getPostID() == null) {			
			this.session.save(postBean);
			return postBean;				
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		if(id != null && id >0) {
			PostBean delete = this.session.get(PostBean.class, id);
			if(delete != null) {
				this.session.delete(delete);
				return true;
			}
		}
		return null;
	}

	@Override
	public PostBean update(Integer id, PostBean postBean) {
		if(id != null && id > 0 && postBean != null && postBean.getPostID() == null) {			
			PostBean update = this.session.get(PostBean.class, id);
			if(update != null) {
				this.session.save(update);
				return update;				
			}
		}
		return null;
	}
	@Override
	public List<PostBean> selectAllBeansByMemberID(String memID) {
		Query<PostBean> qurey = this.session.createQuery("from PostBean where memID =\'" + memID + "\'", PostBean.class);
		List<PostBean> result = qurey.list();
		return result;
	}

	@Override
	public List<PostBean> selectAll() {
		Query<PostBean> qurey = this.session.createQuery("from PostBean", PostBean.class);
		List<PostBean> result = qurey.list();
		return result;
	}
	
}
