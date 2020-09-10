package com.iiht.evaluation.coronokit.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.coronokit.dao.KitDao;
import com.iiht.evaluation.coronokit.dao.ProductMasterDao;
import com.iiht.evaluation.coronokit.model.ProductMaster;
import com.iiht.evaluation.coronokit.model.UserProfile;

@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KitDao kitDAO;
	private ProductMasterDao productMasterDao;
	private HttpSession session = null;

	public HttpSession getSession(HttpServletRequest request) {
		if(session==null) session = request.getSession();
		else session = request.getSession(false);
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public void setKitDAO(KitDao kitDAO) {
		this.kitDAO = kitDAO;
	}

	public void setProductMasterDao(ProductMasterDao productMasterDao) {
		this.productMasterDao = productMasterDao;
	}

	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config. getServletContext().getInitParameter("jdbcPassword");
		
		this.kitDAO = new KitDao(jdbcURL, jdbcUsername, jdbcPassword);
		this.productMasterDao = new ProductMasterDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "newuser":
				viewName = showNewUserForm(request, response);
				break;
			case "insertuser":
				viewName = insertNewUser(request, response);
				break;
			case "showproducts":
				viewName = showAllProducts(request, response);
				break;	
			case "addnewitem":
				viewName = addNewItemToKit(request, response);
				break;
			case "deleteitem":
				viewName = deleteItemFromKit(request, response);
				break;
			case "showkit":
				viewName = showKitDetails(request, response);
				break;
			case "placeorder":
				viewName = showPlaceOrderForm(request, response);
				break;
			case "ordersummary":
				viewName = showOrderSummary(request, response);
				break;	
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	
	}

	private String showOrderSummary(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String contact = (String) this.getSession(request).getAttribute("contact");
		UserProfile userProfile =this.kitDAO.getUserDetails(contact);
		request.setAttribute("userprofile", userProfile);
		return "ordersummary.jsp";
	}

	private String showPlaceOrderForm(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String viewPage = "";
		String contact = "";
		HashMap<String, HashMap<String,String>> pdetails = (HashMap<String, HashMap<String,String>>)session.getAttribute("pdetails");
		if(this.getSession(request).getAttribute("pdetails") == null || pdetails.isEmpty())
		{
			viewPage = "emptykit.jsp";
		}
		else
		{
			contact = (String) this.getSession(request).getAttribute("contact");
			UserProfile userProfile =this.kitDAO.getUserDetails(contact);
			request.setAttribute("userprofile", userProfile);
			viewPage = "placeorder.jsp";
		}
		return viewPage;
		
	}

	private String showKitDetails(HttpServletRequest request, HttpServletResponse response) {
		String viewPage = "";
		@SuppressWarnings("unchecked")
		HashMap<String, HashMap<String,String>> pdetails = (HashMap<String, HashMap<String,String>>)session.getAttribute("pdetails");
		if(this.getSession(request).getAttribute("pdetails") == null || pdetails.isEmpty())
		{
			viewPage = "emptykit.jsp";
		}
		else
		{
			viewPage = "showkit.jsp";
		}
		
		return viewPage;
	}

	@SuppressWarnings("unlikely-arg-type")
	private String deleteItemFromKit(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, HashMap<String,String>> pdetails = (HashMap<String, HashMap<String,String>>)session.getAttribute("pdetails");
		pdetails.remove(request.getParameter("pid"));
		return "user?action=showkit";
	}

	@SuppressWarnings("unchecked")
	private String addNewItemToKit(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		HashMap<String,String> pmap = new HashMap<String,String>();
		HashMap<String, HashMap<String,String>> pdetails; 
		
		String ele = "";
		String quantity = "0";
		String pid = "";
		
		
		if(this.getSession(request).getAttribute("pdetails") == null)
		{
			pdetails = new HashMap<String, HashMap<String,String>>();
		}
		else
		{
			pdetails = (HashMap<String, HashMap<String,String>>) this.getSession(request).getAttribute("pdetails");
		}
		Enumeration<String> plist=request.getParameterNames();
		while(plist.hasMoreElements())
		{
			ele=plist.nextElement();
			if(ele.contains("btn")) {

				pid = ele.substring(4);

				quantity=request.getParameter("qty_"+pid);

				break;			  
			}
		}
		
		//If the product id is not in the pdetials map and the quantity is greater than zero then add the details to pdetails map.
		if(!pdetails.containsKey(pid) && Integer.parseInt(quantity)>0)
		{
			ProductMaster productMaster= this.productMasterDao.getProductDetails(pid);
			pmap.put("quantity", quantity);
			pmap.put("name", productMaster.getProductName());
			pmap.put("cost", productMaster.getCost());
			pmap.put("description", productMaster.getProductDescription());
			pdetails.put(pid, pmap);
		}
		else
		{
			//If the product id is already present in the map and quantity is set to 0 then remove it from map, otherwise update the quantity of the product.
			if(quantity.equals("0") || Integer.parseInt(quantity)<0)
			{
				pdetails.remove(pid);
			}
			else if(!pdetails.get(pid).equals(quantity))
			{
				pdetails.get(pid).replace("quantity", quantity);
			}
		}
		
		this.getSession(request).setAttribute("pdetails", pdetails);
		List<ProductMaster> productlist=this.productMasterDao.getProducts();
		request.setAttribute("productlist", productlist);
		return "showproductstoadd.jsp";
	}

	private String showAllProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		List<ProductMaster> productlist=this.productMasterDao.getProducts();
		/*
		 * String name =request.getParameter("cname");
		 * 
		 * String email =request.getParameter("cemail");
		 * 
		 * String contact =request.getParameter("pnum");
		 * 
		 * String address =request.getParameter("doorno") + "," + "\n" +
		 * request.getParameter("addressline1") + "," + "\n" +
		 * request.getParameter("addressline2") + "," + "\n" +
		 * request.getParameter("lmark") + "," + "\n" + request.getParameter("city") +
		 * "," + "\n" + request.getParameter("zcode") + "," + "\n" +
		 * request.getParameter("state") + "," + "\n" + request.getParameter("country");
		 * 
		 * this.kitDAO.insertUser(name, email, contact, address);
		 */
		this.getSession(request).setAttribute("pdetails",null);
		//this.getSession(request).setAttribute("contact", contact);
		request.setAttribute("productlist", productlist);

		return "showproductstoadd.jsp";
	}

	private String insertNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String name =request.getParameter("cname");

		String email =request.getParameter("cemail");

		String contact =request.getParameter("pnum");
		
		String address =request.getParameter("doorno") + "," + 
				"\n" + request.getParameter("addressline1") + "," + 
				"\n" + request.getParameter("addressline2") + "," + 
				"\n" + request.getParameter("lmark") + "," + 
				"\n" + request.getParameter("city") + "," + 
				"\n" + request.getParameter("zcode") + "," + 
				"\n" + request.getParameter("state") + "," + 
				"\n" + request.getParameter("country");

		this.kitDAO.insertUser(name, email, contact, address);
		this.getSession(request).setAttribute("contact", contact);
		return "user?action=showproducts";
	}

	private String showNewUserForm(HttpServletRequest request, HttpServletResponse response) {
		return "newuser.jsp";
	}
}