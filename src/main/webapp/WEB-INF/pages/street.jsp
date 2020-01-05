<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/12/21
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>街道</title>
    <link rel="stylesheet" type="text/css" href="../static/easyUI/css/demo.css">
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/icon.css" />
    <script language="JavaScript" type="text/javascript" src="../static/js/jquery-1.8.3.js"></script>
    <script language="javascript" type="text/javascript" src="../static/js/jquery.easyui.min.js" ></script>
    <script language="JavaScript" type="text/javascript" src="../static/js/street.js"></script>

    <script language="javascript" type="text/javascript">
        $(function () {
            $("#dv").datagrid({
                url:'street',
                pagination:true,
                pageList:[5,7,10],
                pageSize:5,
                toolbar:'#ToolBar',
                columns:[[
                    {checkbox:true,width:150,align:'center'},
                    {field:'id',title:'编号',width:100,align:'center'},
                    {field:'name',title:'街道名',width:100,align:'center'},
                    {field:'districtName',title:'区域名',width:100,align:'center'},
                    {field:'tt',title:'操作',width:200,align:'center',formatter: function(value,row,index){
                        return "<a href='javascript:goUpdate("+row.id+")'>修改</a> <a href='javascript:goDel("+row.id+")'>删除</a>"
                    }}
                ]]
            })
        })
    </script>
</head>
<body>
    <div id="dv"></div>

    <!—定义工具栏-->
    <div id="ToolBar">
        <div style="height: 40px;">
            <a href="javascript:toadd()" class="easyui-linkbutton"
               iconCls="icon-add" plain="true">添加</a>
            <a
                    href="javascript:toupdate()" class="easyui-linkbutton"
                    iconCls="icon-edit" plain="true">修改</a>
            <a
                    href="javascript:todel()" class="easyui-linkbutton"
                    iconCls="icon-remove" plain="true">删除</a>
        </div>
    </div>

    <%--添加--%>
    <!--添加窗口-->
    <div id="AddDialog" class="easyui-dialog" buttons="#AddDialogButtons"
         style="width: 280px; height: 250px; padding: 10px 20px;" closed="true"  modal="true">
        <form id="addDialogForm" method="post">
            <table>
                <tr>
                    <td>街道名称:</td>
                    <td><input type="text" class="easyui-validatebox" required
                               name="name" id="tname" /></td>
                </tr>
            </table>
        </form>
    </div>
    <!--添加窗口的按钮-->
    <div id="AddDialogButtons">
        <a href="javascript:SaveDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:CloseDialog('AddDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>

    <%--修改--%>
    <!--修改窗口-->
    <div id="UpdateDialog" class="easyui-dialog" buttons="#AddDialogButtons"
         style="width: 280px; height: 250px; padding: 10px 20px;" closed="true"  modal="true">
        <form id="updateDialogForm" method="post">
            <input type="hidden" name="id" id="id">
            <table>
                <tr>
                    <td>街道名称:</td>
                    <td><input type="text" class="easyui-validatebox" required
                               name="name"/></td>
                </tr>
            </table>
        </form>
    </div>
    <!--修改窗口的按钮-->
    <div id="AddDialogButtons">
        <a href="javascript:EideDialog()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
        <a href="javascript:CloseDialog('UpdateDialog')" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
    </div>
</body>
</html>
