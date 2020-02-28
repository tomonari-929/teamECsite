<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/product.css">
<link rel="stylesheet" type="text/css" href="./css/table.css">
<title>商品詳細</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div id="main">
		<h1>商品詳細画面</h1>
		<div class="2column-container">
			<s:if test="productInfoDTO != null">
				<s:form action="AddCartAction">
					<div class="left">
						<img
							src='<s:property value="productInfoDTO.imageFilePath"/>/<s:property value="productInfoDTO.imageFileName"/>'
							class="filePath" /><br>
					</div>
					<div class="right">
						<table class="vertical-list-table-mini">
							<tr>
								<th scope="row"><s:label value="商品名" /></th>
								<td><s:property value="ProductInfoDTO.productName" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品名ふりがな" /></th>
								<td><s:property value="ProductInfoDTO.productNameKana" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="値段" /></th>
								<td><s:property value="ProductInfoDTO.price" /> <span>円</span></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="購入個数" /></th>
								<td><select name="productCount">
										<option value="1" selected="selected">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
								</select></td>
							<tr>
								<th scope="row"><s:label value="発売会社名" /></th>
								<td><s:property value="ProductInfoDTO.releaseCompany" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="発売年月日" /></th>
								<td><s:property value="ProductInfoDTO.releaseDate" /></td>
							</tr>
							<tr>
								<th scope="row"><s:label value="商品詳細情報" /></th>
								<td><s:property value="ProductInfoDTO.productDescription" /></td>
							</tr>
						</table>
					</div>
					<s:hidden name="productId" value="%{productInfoDTO.productId}" />
					<div class="submit_btn_box">
						<s:submit value="カートに追加" class="submit_btn" />
					</div>
				</s:form>
			</s:if>
		</div>
		<s:if
			test="relatedProductsList != null && relatedProductsList.size() > 0">
			<div id="sub-contents">
				<h2>【関連商品】</h2>
				<div class="recommend-box">
					<s:iterator value="relatedProductsList">
						<div class="recommend-box-mini">
							<s:property value="product_id" />
							<s:property value="category_id" />
							<img src="<s:property value = "image_file_path"/>"> <a
								href='<s:url action="ProductDetailsAction"><s:param name="productId" value="%{productId}"/></s:url>'>
								<img
								src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
								class="relatedPath" /><br> <s:property value="productName" /><br>
							</a>
						</div>
					</s:iterator>
				</div>
			</div>
		</s:if>
		<s:else>
			<div class="info">商品の詳細情報がありません。</div>
		</s:else>
	</div>
</body>
</html>