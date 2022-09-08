package model.hibernate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@WebFilter(urlPatterns = {"/*"})
public class OpenSessionInViewFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			chain.doFilter(request, response);
			transaction.commit();
			System.out.println("OpenSessionInViewFilter id opened.");
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			chain.doFilter(request, response);
//			request.getRequestDispatcher("#").forward(request, response);
		} finally {
			session.close();
			System.out.println("OpenSessionInViewFilter id closed.");
		}
	}
}
