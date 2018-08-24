<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show All Users</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
<style type="text/css">

body {
	background-color:#ecf0f5;
}

.header {
	padding: 2px;
	border-left: 5px solid #0994d8;
}

.to_menu {
	text-align: right;
}

.to_menu a:hover {
	color:#0994d8;
}

th {
	background-color: #8eb9ce;
}

</style>
</head>
<body>
<div class="header">
<h3>社員一覧</h3>
</div>
<hr>
<p class="to_menu"><a href="/EmployeeAdmin/menu.jsp">トッ プメニューへ戻る</a></p>
	<table border=1 id="employee_table" class="display">
		<thead>
			<tr>
				<th>社員番号</th>
				<th>名前</th>
				<th>メールアドレス</th>
				<th>性別</th>
				<th>部署</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td><c:out value="${employee.empID}" /></td>
					<td><c:out value="${employee.name}" /></td>
					<td><c:out value="${employee.email}" /></td>
					<c:if test = "${employee.gender == 0}">
					<td>未選択</td>
					</c:if>
					<c:if test = "${employee.gender == 1}">
					<td>男性</td>
					</c:if>
					<c:if test = "${employee.gender == 2}">
					<td>女性</td>
					</c:if>
					<td><c:out value="${employee.dept}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
<script>
$(document).ready( function () {
	$.extend( $.fn.dataTable.defaults, { 
		language: {
			url: "http://cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
		}
	});

	$('#employee_table').DataTable({
		"searching": false
	});
});
</script>
</body>
</html>