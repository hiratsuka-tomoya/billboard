<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>掲示板</title>
<link href="./css/style.css" rel="stylesheet" type="text/css">
<!--[if lt IE 9]>
<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
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

	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<li><c:out value="${message}" />
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session"/>
	</c:if>

	<div class="menu">
	<a href="newpost">新規投稿</a>
	<a href="logout">ログアウト</a>
	</div>
	------------------------------------------------------------(仮)<br>
	<div class="managementMenu">
		管理者メニュー（右に寄せて小さめに表示予定）
		<br>
		<a href="management">ユーザー管理</a>
	</div>
	------------------------------------------------------------(仮)<br>
	<div class="refine">
		絞込みメニュー<br>
		どっちかだけ絞り込むこともある
		<br>
		<form action="./" method="get" name="refine"> <%-- ▲パラメータを付与して呼び出したいだけなのでGET --%>
			カテゴリー：
			<div class="category">
				<select name="refineCategory">
					<c:forEach items="${categories}" var="category">
						<option value="${category}"><c:out value="${category}" /></option>
					</c:forEach>
				</select>
			</div>
			<br>
			投稿日：
			<div class="date">
				<input type="text" name="startDate" size="8"> ～ <input
					type="text" name="endDate" size="8">
			</div>
			<br> <input type="submit" value="絞込み">
		</form>
		------------------------------------------------------------(仮)
	</div>

	<div class="postings">
		<c:forEach items="${ userPostings }" var="posting">
			<div class="posting">
				<div class="title"><c:out value="${posting.title}" /></div>
				<div class="information">
					<c:out value="${ posting.userName }" />
					<c:out value="${ posting.createdDate }" />
					<c:out value="${ posting.category }" />
				</div>
				<div class="mainText"><c:out value="${posting.text}" /></div>
				-----------------------------(仮)
				<form action="newcomment" method="post" name="newcomment">
					<div class="item">コメント入力欄</div>
					<textarea name="text" class="textarea" rows="5" cols="100" maxlength="500"></textarea><br>
					<input type="hidden" name="postingId" value="${posting.id}">
					<input type="submit" value="投稿">(500字まで)
				</form>
				-----------------------------(仮)
				<div class="comment">
				<c:forEach items="${ userComments }" var="comment">
					<c:if test="${ posting.id == comment.postingId }">
						<div class="mainText"><c:out value="${ comment.text }" /></div>
						<div class="information">
							<c:out value="${ comment.userName }" />
							<c:out value="${ comment.createdDate }" />
						</div>
						-----------------------------(仮)
					</c:if>
				</c:forEach>
				</div>
			</div>
			------------------------------------------------------------(仮)
		</c:forEach>
	</div>
	<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>

</body>
</html>
