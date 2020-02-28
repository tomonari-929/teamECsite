package com.internousdev.rosso.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rosso.dao.ProductInfoDAO;
import com.internousdev.rosso.dto.ProductInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ProductDetailsAction extends ActionSupport implements SessionAware {

	private int productId;
	private List<Integer> productCountList;
	private List<ProductInfoDTO> relatedProductsList;
	private ProductInfoDTO productInfoDTO = new ProductInfoDTO();
	private Map<String, Object> session;

	public String execute() {

		// 商品IDを元に商品情報を取得
		ProductInfoDAO productInfoDAO = new ProductInfoDAO();
		productInfoDTO = productInfoDAO.getProductInfo(productId);

		if(productInfoDTO.getProductId() == 0) {
			productInfoDTO = null;
		} else {
			// 購入個数のリストを作成//
			productCountList = new ArrayList<Integer>();
			for (int i=0; i<=4; i++) {
				productCountList.add(i);
			}

			// 関連商品を表示リストを作成//
			relatedProductsList = productInfoDAO.getRelatedProducts(productInfoDTO.getCategoryId(), productInfoDTO.getProductId(), 0, 3);
		}

		return SUCCESS;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<Integer> getProductCountList() {
		return productCountList;
	}

	public void setProductCountList(List<Integer> productCountList) {
		this.productCountList = productCountList;
	}

	public List<ProductInfoDTO> getRelatedProductsList() {
		return relatedProductsList;
	}

	public void setRelatedProductsList(List<ProductInfoDTO> relatedProductsList) {
		this.relatedProductsList = relatedProductsList;
	}

	public ProductInfoDTO getProductInfoDTO() {
		return productInfoDTO;
	}

	public void setProductInfoDTO(ProductInfoDTO productInfoDTO) {
		this.productInfoDTO = productInfoDTO;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
