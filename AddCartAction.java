package com.internousdev.rosso.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rosso.dao.CartInfoDAO;
import com.internousdev.rosso.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware {

	private String userId;
	private int totalPrice;
	private int productId;
	private int productCount;
	private int price;
	private List<CartInfoDTO> cartList;
	private Map<String, Object> session;

	public String execute() throws SQLException {

		if(!session.containsKey("tmpUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}

		String result = ERROR;
		String tmpLogined = String.valueOf(session.get("logined"));
		int logined = "null".equals(tmpLogined) ? 0 : Integer.parseInt(tmpLogined);

		//ログイン済みか、未ログインかを判別
		if(logined == 1) {
			userId = session.get("userId").toString();
		} else {
			userId = session.get("tmpUserId").toString();
		}

		CartInfoDAO cartInfoDAO = new CartInfoDAO();
		int cart = 0 ;

		//カート情報をチェックし、追加する商品IDと一致するデータがある場合は、個数を足した値で更新
		if(cartInfoDAO.isCartInfoCheckId(userId,productId)) {
			cart = cartInfoDAO.cartInfoUpdateAdd(userId,productId,productCount);
		} else {
			cart = cartInfoDAO.cartInfoInsert(userId,productId,productCount);
		}

		if(cart > 0) {
			cartList = cartInfoDAO.getCartInfo(userId);
			setTotalPrice(cartInfoDAO.getTotalPrice(userId));
			result = SUCCESS;
		}

		return result;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<CartInfoDTO> getCartList() {
		return cartList;
	}

	public void setCartList(List<CartInfoDTO> cartList) {
		this.cartList = cartList;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
