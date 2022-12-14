package web.fundraising.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Member;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import model.hibernate.FundraisingUtil;
import model.hibernate.HibernateUtil;
import web.fundraising.common.HtmlDatetimeLocalToSQLDateTimeUtil;
import web.fundraising.common.HtmlPostJsonGetter;
import web.fundraising.service.CategoryService;
import web.fundraising.service.OrderService;
import web.fundraising.service.PlanService;
import web.fundraising.service.PostNumberService;
import web.fundraising.service.PostService;
import web.fundraising.service.ProposalService;
import web.fundraising.service.StatusService;
import web.fundraising.vo.CategoryBean;
import web.fundraising.vo.OrderBean;
import web.fundraising.vo.PlanBean;
import web.fundraising.vo.PostBean;
import web.fundraising.vo.ProposalBean;
import web.fundraising.vo.StatusBean;
import web.member.dao.MemberDao;
import web.member.dao.impl.MemberDaoHibernate;
import web.member.service.MemberService;
import web.member.service.impl.MemberServiceImpl;


@WebServlet("/fundraising")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//??????????????????fileSizeThreshold?????????????????????????????????
//??????????????????????????????????????????maxFileSize?????????????????????????????????maxRequestSize ???????????????IllegalStateException ??????
public class FundraisingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    
	    res.addHeader("Access-Control-Allow-Origin", "*");
//	    res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//	    res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//	    res.addHeader("Access-Control-Max-Age", "1728000");


//	    ????????????request?????????table????????????
	    String table = "";
//	    ????????????request?????????action????????????
	    String action = "";
//	    ????????????request?????????page????????????
	    String page = "";
//	    ????????????????????????url
	    String destination = "";
//	    ??????request?????????
	    String template = "ParamName: %s,		ParamValue: %s";
	    
	    
//	    ??????HttpSession??????????????????
	    HttpSession httpSession = req.getSession();
//	    ????????????????????????????????????
	    httpSession.setAttribute("memID", "TGA001");
	    String memberID = "TGA001";
	    String password = "123";
	    Integer proposalID = 1;

//	    ?????????????????????
	    Map<String, String> errors = new HashMap<String, String>();
	    req.setAttribute("errors", errors);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
	    MemberDaoHibernate memberDao = new MemberDaoHibernate(sessionFactory);
	    MemberService memberService = new MemberServiceImpl(memberDao);
	    web.member.vo.Member member = new web.member.vo.Member();
	    member.setMemID(memberID);
	    member.setMemPassword(password);
	    member = memberService.login(member);
	    if(null != member) {
	    	System.out.println("Login success.");
	    }
	    
	    
//	    == ????????????form?????? ==
//	   	??????????????????????????????template?????????????????????????????????upload file?????????
	    Enumeration<String> names = req.getParameterNames();
	    while(names.hasMoreElements()) {
	    	String key = names.nextElement();
	    	System.out.println(String.format(template, key, req.getParameter(key)));				
	    }
	    
	    
	    
//	    =========================================  ???????????????  =========================================
	    
//	    =======================================   ?????????????????????   ========================================
////	    ?????????JSON???????????????????????????????????????????????????
	    try {
			HtmlPostJsonGetter htmlPostJsonGetter = new HtmlPostJsonGetter(req);
			table = htmlPostJsonGetter.getString("table");
			action = htmlPostJsonGetter.getString("action");
			page = htmlPostJsonGetter.getString("page");
			System.out.println("table : " + table);
			System.out.println("action : " + action);
		} catch (Exception e1) {}
	    
	    
////    =======================================  ????????????proposal  =====================================
//	    fundraising_AllProposal
	    if("Proposal".equals(table) && "GetAll".equals(action)){	
	    	res.setContentType("application/json; charset=utf-8");
	    	ProposalService proposalService = new ProposalService(res);
	    	List<ProposalBean> queryResult = proposalService.selectAllBeans();
	    	
	    	PrintWriter out = res.getWriter();
	    	String jsonString = new Gson().toJson(queryResult);
	    	out.write(jsonString);
	    	System.out.println("Succeeded to get proposalListJsonString : \n" + jsonString);
	    	
	    	out.flush();
	    	res.flushBuffer();
	    	System.out.println();    //????????????
	    }
	    
////    ===================================  ???????????????proposal + ??????plan + ???????????? =================================
//	    fundraising_propsal-n
	    if("Proposal".equals(table) && "GetOne".equals(action)){	
	    	res.setContentType("application/json; charset=utf-8");
	    	ProposalService proposalService = new ProposalService(res);
	    	ProposalBean proposalQueryResult = proposalService.selectBean(Integer.parseInt(page));
	    	System.out.println("proposalBean : " + proposalQueryResult);
	    	
	    	PlanService planService = new PlanService(res);
	    	List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalQueryResult);
	    	for(PlanBean bean : planQueryResult) {
				System.out.println("planBean : " + bean);
	    	}
	    	
	    	PostService postService = new PostService();
	    	List<PostBean> postQueryResult = postService.selectAllBeansByMemberID(member.getMemID());
	    	PostNumberService postnumberService = new PostNumberService();
	    	for(PostBean bean : postQueryResult) {
	    		bean.renew(postnumberService);
				System.out.println("postBean : " + bean);
	    	}
	    	
	    	List<Object> sum = new ArrayList<Object>();
	    	sum.add(proposalQueryResult);
	    	sum.add(planQueryResult);
	    	sum.add(postQueryResult);
	    	
	    	PrintWriter out = res.getWriter();
	    	String jsonString = new Gson().toJson(sum);
	    	out.write(jsonString);
//	    	System.out.println("Succeeded to get proposalJsonString + plansJsonString : \n" + jsonString);
	    	
	    	out.flush();
	    	res.flushBuffer();
	    	System.out.println();    //????????????
	    }
	    
	    
////    ===================================  ??????????????????proposal + plan + ???????????? =================================
//	    fundraising_propsal-n
	    if("Proposal".equals(table) && "GetMine".equals(action)){	
	    	res.setContentType("application/json; charset=utf-8");
	    	ProposalService proposalService = new ProposalService(res);
	    	
	    	List<ProposalBean> allMyProposalBeans = proposalService.selectMyAllBeans(member.getMemID());
	    	System.out.println("allMyProposalBeans.size : " + allMyProposalBeans.size());
	    	List<ProposalBean> proposalList = new ArrayList<ProposalBean>();
	    	List<Object> proposalPlanList = new ArrayList<Object>();
	    	for(ProposalBean proposalBean : allMyProposalBeans) {
	    		proposalList.add(proposalBean);
	    		System.out.println("proposalBean : " + proposalBean);
	    		
	    		PlanService planService = new PlanService(res);
	    		List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalBean);
	    		proposalPlanList.add(planQueryResult);
	    		for(PlanBean bean : planQueryResult) {
	    			System.out.println("planBean : " + bean);
	    		}
	    	}
	    	
	    	List<Object> sum = new ArrayList<Object>();
	    	sum.add(proposalList);
	    	sum.add(proposalPlanList);
	    	
	    	PrintWriter out = res.getWriter();
	    	String jsonString = new Gson().toJson(sum);
	    	out.write(jsonString);
//	    	System.out.println("Succeeded to get proposalJsonString + plansJsonString : \n" + jsonString);
	    	
	    	out.flush();
	    	res.flushBuffer();
	    	System.out.println();    //????????????
	    }
	    
	    
//    	res.setContentType("application/json; charset=utf-8");
//    	
//    	ProposalService proposalService = new ProposalService();
//    	long currentMillis =  System.currentTimeMillis();
//    	List<ProposalBean> list_Proposal = proposalService.selectAllBeans();
//    	for(ProposalBean b : list_Proposal) {
//    		b.renewBean(proposalService);
//    	}
//    	PrintWriter out = res.getWriter();
//    	String proposalJsonString = new Gson().toJson(list_Proposal);
//    	System.out.println("proposalJsonString : " + proposalJsonString);
//    	out.write(proposalJsonString);
//    	out.flush();
//    	res.flushBuffer();
//    	System.out.println();
	    
////    ???????????????
//    table = "Order";
//    action = "Insert";
////    =========================================  ????????????order  =========================================
//    if("Order".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
////    if("Order".equals(table) && "Insert".equals(action)){	    
//		res.setContentType("text/html; charset=UTF-8");
////    	????????????
//    	FundraisingUtil.getTableActionStart(req, table, action);
//    	
//    	OrderService orderService = new OrderService();
//    	OrderBean bean = new OrderBean();
//    	
//    	Integer newID = 100000 + orderService.selectLastID() + 1;
//    	bean.setOrderInvoiceNumber("TG-" + newID.toString());
//    	
//    	java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
//    	bean.setOrderDateTime(now);
//    	
//    	bean.setOrderAmount(Integer.parseInt(req.getParameter("orderAmount")));
//    	bean.setProposalID(Integer.parseInt(req.getParameter("proposalID")));
//    	bean.setPlanID(Integer.parseInt(req.getParameter("planID")));
//    	bean.setPostID(Integer.parseInt(req.getParameter("postID")));
//    	bean.setMemID("TGA001");
//    	
//    	System.out.println("print bean");
//    	System.out.println(bean);
//    	
//    	
////    	????????????(MySQL)
//    	System.out.println("new orderService");
//    	try {
////	    	== ????????? newOrder?????? form?????????????????????bean?????? ==
//    		orderService.insertBean(bean);
//		} catch (Exception e) {
//		} finally {
//			System.out.println("insertBean");
////	    	????????????
//			FundraisingUtil.getTableActionEnd(req, table, action);
//		}
//    }
	    
	    
	    
	    
////    ???????????????
//    table = "Post";
//    action = "Insert";
//    =========================================  ????????????post  =========================================
////    if("Post".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
//    if("Post".equals(table) && "Insert".equals(action)){	    
//		res.setContentType("text/html; charset=UTF-8");
////    	????????????
//    	FundraisingUtil.getTableActionStart(req, table, action);
//    	
//    	PostBean bean = new PostBean();
//    	bean.setPostFisrtName(req.getParameter("postFisrtName"));
//    	bean.setPostLastName(req.getParameter("postLastName"));
//    	bean.setPostCellphone(req.getParameter("postCellphone"));
//    	bean.setPost_SID(Integer.parseInt(req.getParameter("post_SID")));
//    	bean.setPostAddress(req.getParameter("postAddress"));
//    	bean.setMemID("TGA001");
//    	
//    	System.out.println("print bean");
//    	System.out.println(bean);
//    	
//    	
////    	????????????(MySQL)
//    	PostService postService = new PostService();
//    	System.out.println("new postService");
//    	try {
////	    	== ????????? newPost?????? form?????????????????????bean?????? ==
//    		postService.insertBean(bean);
//		} catch (Exception e) {
//		} finally {
//			System.out.println("insertBean");
////	    	????????????
//			FundraisingUtil.getTableActionEnd(req, table, action);
//		}
//    }

	    
	    
////	    =========================================  ????????????proposal  =========================================
//	    if("Proposal".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
//    		res.setContentType("text/html; charset=UTF-8");
////    		????????????newProposal??????upload file??????
//	    	System.out.println("=== proposalFile ===");
//	    	ProposalBean bean = new ProposalBean();
////	    	??????????????????TGA001
////	    	????????????
//	    	FundraisingUtil.getTableActionStart(req, table, action);
////	    	???????????????????????????
//	    	for (Part part : req.getParts()) {
////	    		newProposal??????
//		    	if("proposalPicture".equals(part.getName())  ) {
//		    		InputStream is = part.getInputStream();
//		    		byte[] sourceBytes = IOUtils.toByteArray(is);
//		    		bean.setProposalPicture(sourceBytes);		    		
//		    	}else if("proposalPictureZip".equals(part.getName())) {
//		    		InputStream is = part.getInputStream();
//		    		byte[] sourceBytes = IOUtils.toByteArray(is);
//		    		bean.setProposalPictureZip(sourceBytes);
//		    	}
//	    	}
//	    	
//	    	bean.setProposalName(req.getParameter("proposalName"));
//	    	bean.setProposalHostName(req.getParameter("proposalHostName"));
//	    	bean.setProposalGoal(Integer.parseInt(req.getParameter("proposalGoal")));
//	    	bean.setProposalCategoryID(req.getParameter("proposalCategory"));
//	    	
//	    	java.sql.Timestamp startDate = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("proposalStartedDateTime"));
//		    bean.setProposalStartedDateTime(startDate);
//		    java.sql.Timestamp endDate = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("proposalEndedDateTime"));
//		    bean.setProposalEndedDateTime(endDate);
//		    
//	    	bean.setStatusID("3");
//	    	bean.setProposalEmail(req.getParameter("proposalEmail"));
//	    	bean.setProposalCellphone(req.getParameter("proposalCellphone"));
//	    	bean.setProposalSummary(req.getParameter("proposalContent"));
//	    	bean.setMemID("TGA001");
//	    	
//	    	System.out.println("print bean");
//	    	System.out.println(bean);
//	    	
//	    	
////	    	????????????(MySQL)
//	    	ProposalService proposalService = new ProposalService();
//	    	System.out.println("new proposalService");
//	    	try {
////		    	== ????????? newProposal?????? form?????????????????????bean?????? ==
//	    		proposalService.insertBean(bean);
//			} catch (Exception e) {
//			} finally {
//				System.out.println("insertBean");
////		    	????????????
//				FundraisingUtil.getTableActionEnd(req, table, action);
//			}
//	    }

	    
	    
//	    =========================================  ????????????plan  =========================================
//	    if("Plan".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))) {
//	    	
////    		????????????newPlan??????upload file??????
//	    	System.out.println("=== planPicture ===");
//	    	String pictureName = req.getPart("planPicture").getSubmittedFileName();
//	    	System.out.println(String.format(template, "planPicture", pictureName));
////	    	??????????????????TGA001
//	    	String memberUploadPath = getServletContext().getRealPath("/") + "uploaded\\proposal\\plan" + "\\" + memberID;
//	    	String memberUploadPicturePath = memberUploadPath + "\\" + pictureName;
////	    	???????????????????????????
//	    	for (Part part : req.getParts()) {
////	    		newPlan??????
//		    	if("planPicture".equals(part.getName())  ) {
//		    		File theDir = new File(memberUploadPath);
//		    		if (!theDir.exists()){
//		    			theDir.mkdirs();
//		    			System.out.println("Directory created : " + memberUploadPath);	    			
//		    		}else {
//		    			System.out.println("Directory is exist.");					
//					}
//		    		part.write(memberUploadPicturePath);
//		    	    System.out.println("The uploaded picture uploaded and writed sucessfully.\n");
//		    	}
//	    	}
//	    	
////	    	????????????
//	    	FundraisingUtil.getTableActionStart(req, table, action);
////	    	
//	    	PlanBean bean = new PlanBean();
//	    	bean.setPlanName(req.getParameter("planName"));
//	    	bean.setPlanAmount(Integer.parseInt(req.getParameter("planAmount")));
//	    	bean.setPlanContent(req.getParameter("planContent"));
//	    	bean.setPlanPostNote(req.getParameter("planPostNote"));
//	    	
//	    	java.sql.Timestamp planStartedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("planStartedDateTime"));
//	    	bean.setPlanStartedDate(planStartedDateTime);
//	    	java.sql.Timestamp planEndedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("planEndedDateTime"));
//	    	bean.setPlanEndedDate(planEndedDateTime);
//	    	
//	    	bean.setStatusID(req.getParameter(memberID));
//	    	bean.setProposalID(proposalID);
//	    	
//	    	System.out.println(bean);
//	    	
//	    	
////	    	????????????(MySQL)
//	    	PlanService planService = new PlanService();
//	    	System.out.println("new planService");
//	    	try {
//	    		planService.insertBean(bean);
//			} catch (Exception e) {
//			} finally {
//				System.out.println("insertBean");
////		    	????????????
//				FundraisingUtil.getTableActionEnd(req, table, action);
//			}
//	    	
//	    }
	    
	    
////	    =========================================  ??????(??????)  =========================================
//	    
////	    if(true) {
//	    CategoryService categoryService = new CategoryService();
//	    List<CategoryBean> list_Category = categoryService.selectAllBeans();
//	    for(CategoryBean b : list_Category) {
//	    	System.out.println(b);
//	    }
//	    System.out.println();
////	    	httpSession.setAttribute("list_Category", list_Category);
////	    }
//	    System.out.println("-------------- command ------- end -------\n");
//	    
//	    
////	    if(true) {
//	    OrderService orderService = new OrderService();
//	    List<OrderBean> list_Order = orderService.selectAllBeans();
//	    for(OrderBean b : list_Order) {
//	    	System.out.println(b);
//	    }
//	    System.out.println();
////	    	httpSession.setAttribute("list_Order", list_Order);
////	    }
//	    System.out.println("-------------- command :  ------- end -------\n");
//
//	    
////	    if(true) {
//	    PlanService planService = new PlanService();
//	    List<PlanBean> list_Plan = planService.selectAllBeans();
//	    for(PlanBean b : list_Plan) {
//	    	System.out.println(b);
//	    }
//	    System.out.println();
////	    	httpSession.setAttribute("list_Plan", list_Plan);
////		}
//	    System.out.println("-------------- command :  ------- end -------\n");
//
//	    
////	    if(true) {
//	    PostService postService = new PostService();
//	    List<PostBean> list_Post = postService.selectAllBeans();
//	    for(PostBean b : list_Post) {
//	    	System.out.println(b);
//	    }
//	    System.out.println();
////	    	httpSession.setAttribute("list_Post", list_Post);
////		}
//	    System.out.println("-------------- command :  ------- end -------\n");
//	    
////////////////////////////////////////////////////////////////////
//	    if(true) {
//    	res.setContentType("application/json; charset=utf-8");
//    	
//    	ProposalService proposalService = new ProposalService();
//    	long currentMillis =  System.currentTimeMillis();
//    	List<ProposalBean> list_Proposal = proposalService.selectAllBeans();
//    	for(ProposalBean b : list_Proposal) {
//    		b.renewBean(proposalService);
//    	}
//    	PrintWriter out = res.getWriter();
//    	String proposalJsonString = new Gson().toJson(list_Proposal);
//    	System.out.println("proposalJsonString : " + proposalJsonString);
//    	out.write(proposalJsonString);
//    	out.flush();
//    	res.flushBuffer();
//    	System.out.println();
//	}
//	System.out.println("-------------- command :  ------- end -------\n");
////////////////////////////////////////////////////////////////////////////////
//	    ??????????????????proposal??????
//	    if(true) {
//	    	res.setContentType("application/json; charset=utf-8");
//	    	
//	    	ProposalService proposalService = new ProposalService();
//	    	long currentMillis =  System.currentTimeMillis();
//	    	List<ProposalBean> list_Proposal = proposalService.selectAllBeans();
//	    	for(ProposalBean b : list_Proposal) {
//	    		b.renewBean(proposalService);
//	    	}
//	    	PrintWriter out = res.getWriter();
//	    	String proposalJsonString = new Gson().toJson(list_Proposal);
//	    	System.out.println("proposalJsonString : " + proposalJsonString);
//	    	out.write(proposalJsonString);
//	    	out.flush();
//	    	res.flushBuffer();
//	    	System.out.println();
//		}
//		System.out.println("-------------- command :  ------- end -------\n");
////////////////////////////////////////////////////////////////////////////////
//		    
////	    if(true) {
//	    	StatusService statusService = new StatusService();
//	    	List<StatusBean> list_Status = statusService.selectAllBeans();
//	    	for(StatusBean b : list_Status) {
//	    		System.out.println(b);
//	    	}
//	    	System.out.println();
////	    	httpSession.setAttribute("list_Status", list_Status);
////	    }
//	    System.out.println("-------------- command ------- end -------\n");
//	    
	    
	    


	}

}
