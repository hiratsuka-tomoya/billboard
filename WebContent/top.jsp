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
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/blitzer/jquery-ui.css" >

<!--[if lt IE 9]>
<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>

<script type= text/javascript>
function clearRefine(){
	document.refine.refineCategory.value = "";
	document.refine.refineStartDate.value = "";
	document.refine.refineEndDate.value = "";
}

function myConfirm(target) {
	var result = confirm(target + "を削除しますか？" );
	return result;
}

</script>
<script>
  $(function() {
    $( "#datepicker1" ).datepicker();
  });

  $(function() {
    $( "#datepicker2" ).datepicker();
  });
</script>

</head>
<body>
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
	<hr>
	<div class="managementMenu">
		管理者メニュー（右に寄せて小さめに表示予定）
		<br>
		<a href="management">ユーザー管理</a>
	</div>
	<hr>
	<div class="refine">
		絞込みメニュー<br>
		<br>
		<form action="./" method="get" name="refine">
			カテゴリー：
			<div class="category">
				<select name="refineCategory">
					<c:forEach items="${categories}" var="category">
						<c:if test="${ category == refineCategory }">
							<option value="${category}" selected><c:out value="${category}" /></option>
						</c:if>
						<c:if test="${ category != refineCategory }">
							<option value="${category}"><c:out value="${category}" /></option>
						</c:if>
					</c:forEach>
				</select>
			</div>
			<br>
			投稿日：
			<div class="date">
				<input type="text" id="datepicker1" name="refineStartDate" value="${ refineStartDate }" size="8"> ～
				<input type="text" id="datepicker2" name="refineEndDate" value="${ refineEndDate }"  size="8">
			</div>
			<br>
			<input type="submit" value="絞込み">
			<input type="button" value="クリア" onClick="clearRefine( )">
		</form>
		<hr>
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
				<form action="deleteposting" method="post" name="deleteposting" onSubmit="return myConfirm('投稿')">
					<div class="mainText"><c:out value="${ comment.text }" /></div>
					<span class="information">
						<c:out value="${ posting.userName }" />
						<c:out value="${ posting.createdDate }" />
					</span>
					<input type="hidden" name="postingUserId" value="${posting.userId}">
					<input type="hidden" name="postingId" value="${posting.id}">
					<input type="submit" value="投稿削除"> (投稿者のみ可　管理者はパスで消せるようにしたい)
				</form>
				<hr>
				<form action="newcomment" method="post" name="newcomment">
					<div class="item">コメント入力欄</div>
					<textarea name="text" class="textarea" rows="5" cols="100" maxlength="500"></textarea><br>
					<input type="hidden" name="postingId" value="${posting.id}">
					<input type="submit" value="投稿">(500字まで)
				</form>
				<hr>

				<c:forEach items="${ userComments }" var="comment">
					<c:if test="${ posting.id == comment.postingId }">
						<div class="comment">
							<form action="deletecomment" method="post" name="deletecomment" onSubmit="return myConfirm('コメント')">
								<div class="mainText"><c:out value="${ comment.text }" /></div>
								<span class="information">
									<c:out value="${ comment.userName }" />
									<c:out value="${ comment.createdDate }" />
								</span>
								<input type="hidden" name="commentUserId" value="${comment.userId}">
								<input type="hidden" name="commentId" value="${comment.id}">
								<input type="submit" value="コメント削除"> (投稿者のみ可　管理者はパスで消せるようにしたい)
							</form>
							<hr>
						</div>
					</c:if>
				</c:forEach>

			</div>
			<hr>
		</c:forEach>
	</div>
	<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>

</body>
</html>
