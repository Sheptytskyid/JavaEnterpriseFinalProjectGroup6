<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Add Investment</title>
</head>
<body>
Here you can add you investment
<p>${project}</p>
<p>Total investment needed: ${project.desc.cost-investedAmount}</p>
<p>Minimal investment: ${project.desc.minInvestment}</p>

</body>
</html>
