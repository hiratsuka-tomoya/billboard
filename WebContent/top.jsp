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
<title>BBS-ホーム</title>
<link rel="stylesheet" type="text/css" media="screen" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/v4.0.0/build/css/bootstrap-datetimepicker.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
<script src="moment-ja.js"></script>
<script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/v4.0.0/src/js/bootstrap-datetimepicker.js"></script>
<!--[if lt IE 9]>
<script src="//cdn.jsdelivr.net/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<style type="text/css">
<!--
html, body{
padding-top: 40px;
height: 100%;
margin: 0;
}
.wrapper {
  min-height: 100%;
  margin-bottom: -50px;
}
.footer,
.push {
  height: 50px;
}
span#type1_right {
	float: right;
}
-->
</style>
<script type="text/javascript">
function clearRefine(){
	document.refineForm.refineCategory.value = "";
	document.refineForm.refineStartDate.value = "";
	document.refineForm.refineEndDate.value = "";
}
function myConfirm(target) {
	var result = confirm(target + "を削除しますか？" );
	return result;
}
$(function () {
	  $('.datepicker').datetimepicker({
	    locale : 'ja',
	    format : 'YYYY/MM/DD',
	  });
	});
$(function (str) {
	return str.split([separator["\n"]]);
	})
</script>
</head>
<body>
<div class="wrapper">
<div id="navbar-main">
  <div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
			</div>
	<div class="container">
		<div class="row">
		    <div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
				<li class="active"><a href="/billboard/">ホーム</a></li>
				<li> <a href="/billboard/newpost">新規投稿</a></li>
				<c:if test="${ loginUser.departmentId == 1 }">
				<li><a href="/billboard/management/top">ユーザー管理</a></li>
				<li><a href="/billboard/management/newuser">ユーザー新規登録</a></li>
				</c:if>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				<li> <a href="/billboard/logout">ログアウト</a></li>
				</ul>
			<form action="./" method="get" name="refineForm" class="navbar-form navbar-right">
				<div class="form-group">
					<div class="category">
						<select name="refineCategory" class="form-control">
							<option value='' disabled selected style='display:none;'>カテゴリー</option>
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
				</div>
				<div class="form-group">
				<div class="input-group datepicker">
					<input type="text" id="datepicker1" name="refineStartDate" class="form-control" value="${ refineStartDate }" size="8" autocomplete="off" placeholder="from">
				    <span class="input-group-btn">
				        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
				    </span>
                </div>
                <div class="input-group datepicker">
					<input type="text" id="datepicker2" name="refineEndDate" class="form-control" value="${ refineEndDate }"  size="8" autocomplete="off" placeholder="to">
				    <span class="input-group-btn">
				        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
				    </span>
				</div>
				</div>
				<div class="btn-group btn-group-sm" role="group">
					<button type="submit" class="btn btn-default">絞込み</button>
					<button type="submit" class="btn btn-default" onClick="clearRefine()">クリア</button>
				</div>
			</form>
			</div>
	<!--/.nav-collapse -->
		</div>
    </div>
    </div>
</div>
<div class="container">
		<div class="row">
		<div class="col-md-10">
			<c:if test="${ not empty errorMessages }">
			<div class="alert alert-warning" role="alert">
				<div class="errorMessages">
					<ul>
						<c:forEach items="${errorMessages}" var="message">
							<li><c:out value="${message}" />
						</c:forEach>
					</ul>
				</div>
				<c:remove var="errorMessages" scope="session"/>
			</div>
			</c:if>
			<div class="postings">
				<c:forEach items="${ userPostings }" var="posting">
				<div class="panel panel-primary">
					<div class="panel-heading"><h4><c:out value="${posting.title}" /></h4>
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span><c:out value="${ posting.userName }" />
						<c:out value="【Category】${ posting.category } " />
						<c:out value="【Date】" /><fmt:formatDate value="${ posting.createdDate }" pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
		 			<div class="panel-body">
					<div class="posting">
							<div class="mainText">
							<c:forEach items="${ posting.getTextLines() }" var="postingTextLine">

								<div class="text" style="word-break:break-all"><c:out value="${ postingTextLine }" /></div>
							</c:forEach>
							</div>
							<div align="right">
								<form action="deleteposting" method="post" name="deleteposting" onSubmit="return myConfirm('投稿')">
								<input type="hidden" name="postingUserId" value="${posting.userId}">
								<input type="hidden" name="postingId" value="${posting.id}">
								<c:if test="${ loginUser.departmentId == 2}">
								<button type="submit" class="btn btn-default btn-sm">削除</button>
								</c:if>
								<c:if test="${ loginUser.departmentId == 3 && loginUser.branchId == posting.userBranchId}">
								<button type="submit" class="btn btn-default btn-sm">削除</button>
								</c:if>
								</form>
							</div>
						<hr>
							<div class="row">
								<div class="col-md-9">
								<c:forEach items="${ userComments }" var="comment">
									<c:if test="${ posting.id == comment.postingId }">
										<div class="well">
												<div class="mainText">
												<c:forEach items="${ comment.getTextLines() }" var="commentTextLine">
													<div class="text" style="word-break:break-all"><c:out value="${ commentTextLine }" /></div>
												</c:forEach>
												</div><br>
												<form action="deletecomment" method="post" name="deletecomment" onSubmit="return myConfirm('コメント')">
													<input type="hidden" name="commentUserId" value="${comment.userId}">
													<input type="hidden" name="commentId" value="${comment.id}">
													<c:if test="${ loginUser.departmentId == 2}">
													<span id="type1_right"><button type="submit" class="btn btn-default btn-sm">削除</button></span>
													</c:if>
													<c:if test="${ loginUser.departmentId == 3 && loginUser.branchId == comment.userBranchId}">
													<span id="type1_right"><button type="submit" class="btn btn-default btn-sm">削除</button></span>
													</c:if>
													<span class="glyphicon glyphicon-user" aria-hidden="true"></span><c:out value="${ comment.userName }" />
													Date:<fmt:formatDate value="${ comment.createdDate }" pattern="yyyy/MM/dd HH:mm:ss" />
												</form>
										</div>
									</c:if>
								</c:forEach>
								<form action="newcomment" method="post" name="newcomment">
									<div class="item">コメント</div>
									<textarea class="form-control" id="InputTextarea" name="text" rows="5" cols="100" maxlength="500" placeholder="（500字まで）" required></textarea><br>
									<input type="hidden" name="postingId" value="${posting.id}">
									<button type="submit" class="btn btn-default btn-sm">投稿</button>
								</form>
							</div>
						</div>
					</div>
					</div>
					</div>
				</c:forEach>
		</div>
	</div>
	</div>
</div>
</div>
<div class="push"></div>
<footer class="footer"><div class="copyright"><p class="text-center">Copyright(c)Tomoya Hiratsuka</p></div></footer>
</body>
</html>