<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.* "%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
String emp_id_errmsg = (String) request.getAttribute("imp_id_errmsg");
String emp_name_errmsg = (String) request.getAttribute("imp_name_errmsg");
String emp_email_confirm_errmsg = (String) request.getAttribute("imp_email_confirm_errmsg");
String[] dept_list = {"マーケティング部","サポート部","営業部","人事部","開発部"};
pageContext.setAttribute("dept_list", dept_list);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<title>社員管理</title>
<style>
body {
	background: #ecf0f5;
}
.header {
	padding: 3px;
	border-left: 5px solid #0994d8;
	margin-left:3px;
}
.err_msg {
	color:red;
	font-weight:bold;
}

.inside_form {
	border: 1px solid #666666;
}

.form_header {
	padding:10px;
}

.required {
	color: red;
	font-weight: bold;
}

.back {
	text-align: right;
}

hr {
	border-top:1px solid #aaafb5;
}
</style>

</head>
<body>
<div class="header">
<h3>社員登録</h3>
</div>
<hr>
<div class="container">
<p class="back"><a href="/EmployeeAdmin/menu.jsp">トッ プメニューへ戻る</a></p>
	<div class="col-sm-2"></div>
	<div class="col-sm-8 inside_form">
		<p class="form_header">必要事項をご入力の上、送信ボタンを押してください。</p>
		<form method="post" action="EmployeeController" name="frmAddUser" id="myForm">
		<div class="form-group">
			<label>社員番号<span class="required">【必須】</span>：</label>
			<input type="text" class="form-control" name="empid" value="<c:out value="${value_remain.imp_id}" />">
			<c:if test = "${imp_id_errmsg != null}">
				<span class="err_msg"><c:out value="${imp_id_errmsg}" /></span><br>
			</c:if>
		</div>
		<div class="form-group">
			<label>氏名<span class="required">【必須】</span>：</label>
			<input type="text" class="form-control" name="name" value="<c:out value="${value_remain.imp_name}" />">
			<c:if test = "${imp_name_errmsg != null}">
				<span class="err_msg"><c:out value="${imp_name_errmsg}" /></span><br>
			</c:if>
		</div>
		<div class="form-group">
			<label>メールアドレス：</label>
			<input type="text" class="form-control" name="email" value="<c:out value="${value_remain.imp_email}" />">
			(メール確認用)<br>
			<input type="text" class="form-control" name="confirm_mail" value="<c:out value="${value_remain.imp_confirm_email}" />">
			<c:if test = "${imp_email_confirm_errmsg != null}">
				<span class="err_msg"><c:out value="${imp_email_confirm_errmsg}" /></span><br>
			</c:if>
		</div>
		<div class="form-group">
			<label>性別：</label>
			<select name="gender">
			<c:choose>
			<c:when test="${value_remain.imp_gender == 0}">
				<option value="0" selected="selected">---選択してください---</option>
				<option value="1">男性</option>
				<option value="2">女性</option>
			</c:when>
			<c:when test="${value_remain.imp_gender == 1}">
				<option value="0">---選択してください---</option>
				<option value="1" selected="selected">男性</option>
				<option value="2">女性</option>
			</c:when>
			<c:when test="${value_remain.imp_gender == 2}">
				<option value="0">---選択してください---</option>
				<option value="1">男性</option>
				<option value="2" selected="selected">女性</option>
			</c:when>
			<c:otherwise>
				<option value="0">---選択してください---</option>
				<option value="1">男性</option>
				<option value="2">女性</option>
				</c:otherwise>
			</c:choose>
			</select><br>
		</div>
		<div class="form-group">
			<label>部署：</label>
			<select name="dept">
			<c:choose>
			<c:when test="${value_remain.imp_dept == dept_list[0]}">
				<option value="">---選択してください---</option>
				<option value="マーケティング部" selected="selected">マーケティング部</option>
				<option value="サポート部">サポート部</option>
				<option value="営業部">営業部</option>
				<option value="人事部">人事部</option>
				<option value="開発部">開発部</option>
			</c:when>
			<c:when test="${value_remain.imp_dept == dept_list[1]}">
				<option value="">---選択してください---</option>
				<option value="マーケティング部">マーケティング部</option>
				<option value="サポート部" selected="selected">サポート部</option>
				<option value="営業部">営業部</option>
				<option value="人事部">人事部</option>
				<option value="開発部">開発部</option>
			</c:when>
			<c:when test="${value_remain.imp_dept == dept_list[2]}">
				<option value="">---選択してください---</option>
				<option value="マーケティング部">マーケティング部</option>
				<option value="サポート部">サポート部</option>
				<option value="営業部" selected="selected">営業部</option>
				<option value="人事部">人事部</option>
				<option value="開発部">開発部</option>
			</c:when>
			<c:when test="${value_remain.imp_dept == dept_list[3]}">
				<option value="">---選択してください---</option>
				<option value="マーケティング部">マーケティング部</option>
				<option value="サポート部">サポート部</option>
				<option value="営業部">営業部</option>
				<option value="人事部" selected="selected">人事部</option>
				<option value="開発部">開発部</option>
			</c:when>
			<c:when test="${value_remain.imp_dept == dept_list[4]}">
				<option value="">---選択してください---</option>
				<option value="マーケティング部">マーケティング部</option>
				<option value="サポート部">サポート部</option>
				<option value="営業部">営業部</option>
				<option value="人事部">人事部</option>
				<option value="開発部" selected="selected">開発部</option>
			</c:when>
			<c:otherwise>
				<option value="">---選択してください---</option>
				<option value="マーケティング部">マーケティング部</option>
				<option value="サポート部">サポート部</option>
				<option value="営業部">営業部</option>
				<option value="人事部">人事部</option>
				<option value="開発部">開発部</option>
			</c:otherwise>
			</c:choose>
			</select><br>
		</div>
		<div class="form-group col-xs-4">
			<label>入社日：</label>
			<input type="date" class="form-control" name="emp_date" value="<c:out value="${value_remain.imp_date}" />"><br>
			<input type="submit" value="送信">   <input type="reset"><br>
		</div>
		</form>
	</div>
</div>
</body>
</html>