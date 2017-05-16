<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<sec:authorize access="isAuthenticated()">--%>
<html>
<head>
	<title>All investments</title>

	<link rel="shortcut icon" href="${contextPath}/resources/img/favicon.png" type="image/x-icon">
	<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/font-awesome.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/main.css" rel="stylesheet">
	<link href="${contextPath}/resources/css/card.css" rel="stylesheet">

	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<h1>${pageName}</h1>
<div class="row masonry" data-columns>
	<c:forEach items="${investmentList}" var="investment">
		<div class="item">
			<div class="thumbnail">
				<div class="caption centered">
					<ul>
						<li>
							<h4>Project name:
								<a href="project/${investment.project.id}">${investment.project.desc.name}</a>
							</h4>
						</li>
						<li>
							<h4>Investor:
								<a href="user/${investment.investor.id}">
										${investment.investor.name} ${investment.investor.lastName}
								</a>
							</h4>
						</li>
						<li>
							Date of investment: ${investment.dateOfInvestment}
						</li>
						<li>
							Sum: ${investment.sum}; Paid: ${investment.paid}; Verified: ${investment.verified}
						</li>
					</ul>
					<a href="investment/${investment.id}/delete" methods="post">
						<button type="button" class="btn btn-sm btn-danger">Delete</button>
					</a>
				</div>
			</div>
		</div>
	</c:forEach>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/salvattore.min.js"></script>

</body>
</html>
<%--</sec:authorize>--%>