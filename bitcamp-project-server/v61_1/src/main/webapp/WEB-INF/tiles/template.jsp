<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>LMS 시스템</title>
<link rel='stylesheet' href='${pageContext.getServletContext().getContextPath()}/node_modules/bootstrap/dist/css/bootstrap.min.css'>

	<style> 
		body {
		  background-color: #6c7b95;
		}
		
		input, textarea {
		 background-color: #97a7c2;"
		}
		
		div.container {
		  color: black;
		  width: 1000px;
		}
	
		a:link, a:active { text-decoration:none; color:#450045;}
		a:visited {text-decoration:none; color:#800080;}
		a:hover { text-decoration:none; }
		
		footer {
		 margin-top : 100px;
		 padding : 100px;
		 background-color: navy;
		 color:white;
		 text-align: center;
		}
	
	</style>
	<script src='${pageContext.getServletContext().getContextPath()}/node_modules/jquery/dist/jquery.slim.min.js'>
	</script>
</head>
<body>

<tiles:insertAttribute name="header"/>

<tiles:insertAttribute name="body"/>

<tiles:insertAttribute name="footer"/>

</body>
</html>
