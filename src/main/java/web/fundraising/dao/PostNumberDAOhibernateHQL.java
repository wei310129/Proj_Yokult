package web.fundraising.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import model.hibernate.HibernateUtil;
import web.fundraising.vo.PostNumberBean;

public class PostNumberDAOhibernateHQL implements PostNumberDAO {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private Session session = sessionFactory.getCurrentSession();
	
	public PostNumberDAOhibernateHQL() {}
	
	@Override
	public PostNumberBean select(Integer post_SID) {
		if(post_SID != null && post_SID != 0) {
			return this.session.get(PostNumberBean.class, post_SID);
		}
		return null;
	}

	@Override
	public List<PostNumberBean> selectAll() {
		Query<PostNumberBean> qurey = this.session.createQuery("from PostNumberBean", PostNumberBean.class);
		List<PostNumberBean> result = qurey.list();
		return result;
	}
	
}
