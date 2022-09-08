package web.member.dao.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.hibernate.HibernateUtil;
import web.member.dao.MemberDao;
import web.member.vo.Member;

public class MemberDaoHibernate implements MemberDao {

	private SessionFactory sessionFactory;

	public MemberDaoHibernate(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Integer insert(Member member) {
		Member temp = (Member)this.getSession().get(Member.class, member.getMemID());
		if (temp == null) {
			this.getSession().save(member);
			return 1;
		}
		return -1;
	}

	@Override
	public Member selectByMemberIdAndPassword(Member member) {
		Member resultMember = (Member)this.getSession().get(Member.class, member.getMemID());
		if (resultMember != null) {
			if (resultMember.getMemPassword().equals(member.getMemPassword())) {
				return resultMember;
			}
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		return (List<Member>) this.getSession().createQuery("from Member", Member.class).list();
	}

	@Override
	public Integer update(Member member) {
		Member update = (Member)this.getSession().get(Member.class, member.getMemID());
		if (update != null) {
			update.setMemEmail(member.getMemEmail());
			update.setMemFirstName(member.getMemFirstName());
			update.setMemLastName(member.getMemLastName());
			update.setMemCellPhone(member.getMemCellPhone());
			update.setMemBirth(member.getMemBirth());
			update.setMemAddress(member.getMemAddress());
			return 1;
		}
		return -1;
	}

	@Override
	public Integer delete(Member member) {
		Member delete = (Member)this.getSession().get(Member.class, member.getMemID());
		if (delete != null) {
			this.getSession().delete(delete);
			return 1;
		}
		return 0;
	}

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		MemberDaoHibernate dao = new MemberDaoHibernate(sessionFactory);
		Session session = dao.getSession();

		try {
			session.beginTransaction();
			switch ("delete") {
			case "insert":
				// Insert practice.
				Member insert = new Member();
				insert.setMemID("TGA1993");
				insert.setMemPassword("123");
				insert.setMemEmail("abc@gmail.com");
				insert.setMemFirstName("Kevin");
				insert.setMemLastName("Hou");
				insert.setMemCellPhone("0912345678");
				Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());	
				insert.setMemBirth(sqlDate);
				Integer result = dao.insert(insert);
				session.getTransaction().commit();
				System.out.println(result);
				break;
			case "selectAll":
				// Search all
				List<Member> list = dao.selectAll();
				for (Member m : list) {
					System.out.println(m);
				}
				break;
			case "selectbyIDandPassword":
				Member selectbyIDandPassword = new Member();
				selectbyIDandPassword.setMemID("TGA001");
				selectbyIDandPassword.setMemPassword("123");
				Member selectMember = dao.selectByMemberIdAndPassword(selectbyIDandPassword);
				System.out.println(selectMember);
			case "update":
				Member update = new Member();
				update.setMemID("TGA001");
				update.setMemPassword("123");
				update = dao.selectByMemberIdAndPassword(update);
				update.setMemCellPhone("0987777777");
				dao.update(update);
				session.getTransaction().commit();
				break;
			case "delete":
				Member delete = new Member();
				delete.setMemID("TGA001");
				dao.delete(delete);
				session.getTransaction().commit();
			default:
				break;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("Commit failure");
			e.printStackTrace();
		}
		finally {
			session.close();
			HibernateUtil.closeSessionFactory();
		}
	}

}
