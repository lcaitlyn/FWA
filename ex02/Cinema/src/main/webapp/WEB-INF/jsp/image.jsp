<%@ page import="java.io.File" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<head>
    <title>Image</title>
</head>
<body>
    <img src="<c:url value='${storagePath}${fileUniquePath}'/>" alt="not found">
</body>
</html>
