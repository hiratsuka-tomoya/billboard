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
<title>ユーザー管理</title>
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
ユーザー管理<br>
自分は停止できないようにする▲
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
	<div class="users">
		<c:forEach items="${ users }" var="user">
			<table class="posting">
			  <tr>
			    <td><c:out value="${user.id}" /></td>
			    <td><a href="management"><c:out value="${user.name}" /></a></td>
			    	<c:if test="${user.isStopped() == false}">
						<td><c:out value="停止中" /></td>
				    </c:if>
				    <c:if test="${user.isStopped() == true}">
			    		<td><c:out value="▲仮▲" /></td>
				    </c:if>
		      	<td>
				    <form action="management" method="post">
					    <c:if test="${user.isStopped() == false}">
					    	<input type="hidden" name="stopUserId" value="${user.id}">
					    	<input type="submit" value="停止" onClick="return stopCheck('${user.name}')"></input>
					    </c:if>
					    <c:if test="${user.isStopped() == true}">
							<input type="hidden" name="recoverUserId" value="${user.id}">
					    	<input type="submit" value="復帰" onClick="return recoverCheck('${user.name}')"></input>
					    </c:if>
				    </form>
			    <td>
			  </tr>
			</table>
		</c:forEach>
	</div>

	<a href="./"><c:out value="ホーム"></c:out></a>
	<div class="copyright">Copyright(c)Tomoya Hiratsuka</div>

</body>
</html>
