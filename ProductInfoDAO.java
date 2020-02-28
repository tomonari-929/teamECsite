package com.internousdev.rosso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.rosso.dto.ProductInfoDTO;
import com.internousdev.rosso.util.DBConnector;

public class ProductInfoDAO {

	public ProductInfoDTO getProductInfo(int product_id) {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ProductInfoDTO productInfoDTO = new ProductInfoDTO();

		String sql = "SELECT id, product_id,product_name,product_name_kana,"
				+ "product_description,category_id,price,image_file_path,image_file_name,"
				+ "release_date,release_company FROM product_info WHERE product_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs;
			ps.setInt(1, product_id);
			rs = ps.executeQuery();

			if (rs.next()) {
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTO;
	}

	//関連商品の情報を取得
	public List<ProductInfoDTO> getRelatedProducts(int categoryId, int productId, int limitOffset,int limitRowCount) {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT * FROM product_info WHERE category_id=? and product_id not in(?) order by rand() limit ?,?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ps.setInt(2, productId);
			ps.setInt(3, limitOffset);
			ps.setInt(4, limitRowCount);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTOList.add(productInfoDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	public List<ProductInfoDTO> getProductInfoByKeyword(String[] keywordsList) {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT * FROM product_info";

		if (!"".equals(keywordsList[0])) {

			for (int i=0; i<keywordsList.length; i++) {
				if (i == 0) {
					sql += " where (product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%" + keywordsList[i] + "%')";
				} else {
					sql += " or (product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%" + keywordsList[i] + "%')";
				}
			}

		}

		sql += " ORDER BY product_id asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTOList.add(productInfoDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

	//カテゴリーIDとキーワードを元に商品の情報を取得
	public List<ProductInfoDTO> getProductInfoByKeywordAndCategoryId(int categoryId, String[] keywordsList) {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<ProductInfoDTO> productInfoDTOList = new ArrayList<ProductInfoDTO>();

		String sql = "SELECT * FROM product_info WHERE category_id=" + categoryId;

		if (!"".equals(keywordsList[0])) {

			for (int i=0; i<keywordsList.length; i++) {
				if (i == 0) {
					sql += " and ((product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%" + keywordsList[i] + "%')";
				} else {
					sql += " or (product_name like '%" + keywordsList[i] + "%' or product_name_kana like '%" + keywordsList[i] + "%')";
				}
			}
			sql += ")";

		}

		sql += " ORDER BY product_id asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProductInfoDTO productInfoDTO = new ProductInfoDTO();
				productInfoDTO.setId(rs.getInt("id"));
				productInfoDTO.setProductId(rs.getInt("product_id"));
				productInfoDTO.setProductName(rs.getString("product_name"));
				productInfoDTO.setProductNameKana(rs.getString("product_name_kana"));
				productInfoDTO.setProductDescription(rs.getString("product_description"));
				productInfoDTO.setCategoryId(rs.getInt("category_id"));
				productInfoDTO.setPrice(rs.getInt("price"));
				productInfoDTO.setImageFilePath(rs.getString("image_file_path"));
				productInfoDTO.setImageFileName(rs.getString("image_file_name"));
				productInfoDTO.setReleaseDate(rs.getDate("release_date"));
				productInfoDTO.setReleaseCompany(rs.getString("release_company"));
				productInfoDTOList.add(productInfoDTO);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productInfoDTOList;
	}

}
