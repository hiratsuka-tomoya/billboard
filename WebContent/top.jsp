<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<script Language="JavaScript">
<!--
	function init() {
		//日付
		dt = new Date();
		y = dt.getFullYear();
		m = dt.getMonth() + 1;
		d = dt.getDate();
		document.refine.startDate.value = y + "/" + m + "/" + d;
		document.refine.endDate.value = y + "/" + m + "/" + d;
	}
// -->
</script>
</head>
<body onLoad="init()">
	<div class="menu">
		管理者メニュー（右に寄せて小さめに表示予定）<br> <a href="post">新規投稿</a> <a
			href="management">ユーザー管理</a><br>
		------------------------------------------------------------(仮)
	</div>
	<div class="refine">
		絞込み<br>
		<br>
		<form action="refine" method="post" name="refine">
			カテゴリー：
			<div class="category">
				<select name="category">
					<c:forEach items="${categories}" var="category">
						<option value="category"><c:out value="${category}" /></option>
					</c:forEach>
				</select>
			</div>
			<br> 投稿日：
			<div class="date">
				<input type="text" name="startDate" size="8"> ～ <input
					type="text" name="endDate" size="8">
			</div>
			<br> <input type="submit" value="絞込み">
		</form>
		------------------------------------------------------------(仮)
	</div>

	<div class="postings">
		<c:forEach items="${userPostings}" var="posting">
			<div class="posting">
				<div class="title"><c:out value="${posting.title}" /></div>
				<div class="information">
					<c:out value="${posting.userName}" />
					<c:out value="${posting.createdDate}" />
					<c:out value="${posting.category}" />
				</div>
				<div class="text"><c:out value="${posting.text}" /></div>
			</div>
			------------------------------------------------------------(仮)
		</c:forEach>
	</div>
	<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>

</body>
</html>
