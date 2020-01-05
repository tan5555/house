<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/12/17
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>房子</title>
    <link rel="stylesheet" type="text/css" href="../static/easyUI/css/demo.css">
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/icon.css" />
    <script language="JavaScript" type="text/javascript" src="../static/js/jquery-1.8.3.js"></script>
    <script language="javascript" type="text/javascript" src="../static/js/jquery.easyui.min.js" ></script>

    <script language="javascript" type="text/javascript">
        $(function () {
            $("#dv").datagrid("")
        })
    </script>
</head>
<body>
    <div id="dv"></div>
</body>
</html>
