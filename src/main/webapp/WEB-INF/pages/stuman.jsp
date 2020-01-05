<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/12/22
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" type="text/css" href="../static/easyUI/css/demo.css">
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/icon.css" />
    <script language="JavaScript" type="text/javascript" src="../static/js/jquery-1.8.3.js"></script>
    <script language="javascript" type="text/javascript" src="../static/js/jquery.easyui.min.js" ></script>

</head>
<body>
<table id="data" title="信息" class="easyui-datagrid"
       url="../../pages/studentData.json" toolbar="#ToolBar" pagination="true"
       fitColumns="true" fit="true" rownumbers="true">
    <thead>
    <tr>
        <th field="cb" checkbox="true"></th>
        <th field="xh" width="100px">学号</th>
        <th field="name" width="100px">姓名</th>
        <th field="sex" width="100px">性别</th>
        <th field="birthday" width="100px">生日</th>
        <th field="gid" width="100px">年级</th>
    </tr>
    </thead>
</table>

<div id="ToolBar">
    <div style="height: 40px;">
        <a href="javascript:Add()" class="easyui-linkbutton"
           iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:ModifyBySelect()" class="easyui-linkbutton"
            iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:DeleteByFruitName()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
    </div>
</div>

<div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
     style="width: 280px; height: 250px; padding: 10px 20px;" closed="true">
    <form id="ModiyDialogForm" method="post">
        <table>
            <tr>
                <td>学号:</td>
                <td><input type="text" class="easyui-validatebox" required
                           name="xh" id="bname" /></td>
            </tr>
            <tr>
                <td>姓名:</td>
                <td><input type="text" name="name" id="author" /></td>
            </tr>
            <tr>
                <td>性别:</td>
                <td><input type="text" name="sex" id="buytime"></td>
            </tr>
            <tr>
                <td>类别:</td>
                <td>
                    <select name="gid" id="sid">
                        <option value="1">年级一</option>
                        <option value="2">年级二</option>
                        <option value="3">年级三</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="AddDialogButtons">
    <a href="javascript:SaveDialog()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a> <a href="javascript:CloseDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<script type="text/javascript" src="../static/js/MyEasyUICRUD.js"></script>
</body>
</html>
