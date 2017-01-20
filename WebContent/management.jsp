<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー管理</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/blitzer/jquery-ui.css" >
<!--[if lt IE 9]>
<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->


<script type= text/javascript>
function stopCheck(target) {
	var result = confirm("[" + target + "]を停止しますか？" );
	return result;
}
function recoverCheck(target) {
	var result = confirm("[" + target + "]を復帰しますか？" );
	return result;
}
</script>

</head>
<body>
<h2>ユーザー管理</h2>
自分は停止できないようにする▲
パス以外の全データ表示する
<br>
<a href="newuser">ユーザー新規登録</a>
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
	<div class="container">
		<table class="table table-bordered">
		   <thead>
		      <tr>
		        <th>ID</th>
		        <th>ユーザー名</th>
		        <th>状態</th>
		        <th>停止/復帰</th>
		      </tr>
		    </thead>
		    <tbody>
			<c:forEach items="${ users }" var="user">
			  <tr>
			    <td class="col-md-1"><c:out value="${user.id}" /></td>
			    <td class="col-md-4"><a href="userEdit?editUserId=${user.id}"><c:out value="${user.name}" /></a></td>
			    <td class="col-md-4">
			    	<c:if test="${user.isStopped() == false}">
						<c:out value="停止中" />
				    </c:if>
				    <c:if test="${user.isStopped() == true}">
			    		<c:out value="稼働中" />
				    </c:if>
			    </td>
		      	<td class="col-md-4">
				    <form action="./top" method="post">
					    <c:if test="${user.isStopped() == false}">
					    	<input type="hidden" name="stopUserId" value="${user.id}">
					    	<input type="submit" value="停止" onClick="return stopCheck('${user.name}')"></input>
					    </c:if>
					    <c:if test="${user.isStopped() == true}">
							<input type="hidden" name="recoverUserId" value="${user.id}">
					    	<input type="submit" value="復帰" onClick="return recoverCheck('${user.name}')"></input>
					    </c:if>
				    </form>
			    </td>
			  </tr>
			</c:forEach>
			</tbody>
		</table>
	</div>

	<a href="../"><c:out value="ホーム"></c:out></a>
	<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>
