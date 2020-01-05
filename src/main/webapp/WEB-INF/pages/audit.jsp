<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>审核</title>
    <link rel="stylesheet" type="text/css" href="../static/easyUI/css/demo.css">
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../static/easyUI/themes/icon.css" />
    <script language="JavaScript" type="text/javascript" src="../static/js/jquery-1.8.3.js"></script>
    <script language="javascript" type="text/javascript" src="../static/js/jquery.easyui.min.js" ></script>
    <script language="javascript" type="text/javascript" src="../static/js/audit.js"></script>

    <script language="javascript" type="text/javascript">
        $(function () {
            $("#dv").datagrid({
                url:'getAudit',
                pagination:true,
                pageList:[10,20,30],
                pageSize:10,
                toolbar:'#ToolBar',
                columns:[[
                    {checkbox:true,width:150,align:'center'},
                    {field:'id',title:'编号',width:100,align:'center'},
                    {field:'title',title:'标题',width:100,align:'center'},
                    {field:'dname',title:'区域',width:100,align:'center'},
                    {field:'sname',title:'街道',width:100,align:'center'},
                    {field:'tname',title:'类型',width:100,align:'center'},
                    {field:'floorage',title:'面积',width:100,align:'center'},
                    {field:'price',title:'价格',width:100,align:'center'},
                    {field:'ispass',title:'审核状态',width:100,align:'center',formatter:function (value,row,index) {
                        if(value==0){
                            return "未审核"
                        }
                        if(value==1){
                            return "审核通过"
                        }
                        if(value==-1){
                            return "审核未通过"
                        }
                    }},
                    {field:'tt',title:'操作',width:200,align:'center',formatter: function(value,row,index){
                        return "<a href='javascript:auditS("+row.id+")'>审核通过</a>&nbsp;&nbsp;&nbsp;" +"<a href='javascript:auditF("+row.id+")'>审核未通过</a>&nbsp;&nbsp;&nbsp;"+
                            " <a href='javascript:goDel("+row.id+")'>详情</a>"
                    }}
                ]]
            })


            $.post("/pcontroller/findType",null,function (data) {
                for (var i=1;i<data.length;i++){
                    var $option=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                    $("#type").append($option)
                }
            },"json")

            $.post("/pcontroller/findDistrict",null,function (data) {
                for (var i=0;i<data.length;i++) {
                    var $option = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                    $("#district").append($option)
                }

            },"json")

            $("#district").change(function () {
                street()
            })

        })

        //审核通过
        function auditS(id) {
            $.messager.confirm("友情提示","确认审核吗？",function (r) {
                if (r){
                    $.post("audit",{"id":id,"ispass":"1"},function (date) {
                        if(date>0){
                            $("#dv").datagrid("reload")
                        }else {
                            $.messager.alert("友情提示","审核失败","Info")
                        }
                    },"json")
                }
            })
        }

        //审核未通过
        function auditF(id) {
            $.messager.confirm("友情提示","确认审核吗？",function (r) {
                if (r){
                    $.post("audit",{"id":id,"ispass":"-1"},function (date) {
                        if(date>0){
                            $("#dv").datagrid("reload")
                        }else {
                            $.messager.alert("友情提示","审核失败","Info")
                        }
                    },"json")
                }
            })
        }


        //批量审核
        function SomeAuditS() {
            var i = $("#dv").datagrid("getSelections")
            if (i.length>0){
                var str=""
                for (var j=0; j<i.length; j++){
                    str+="id="+i[j].id+"&"
                }
                //截取字符串（索引开始，结束）
                str=str.substr(0,str.length-1)
                alert(str)
                $.messager.confirm('友情提示','您确认想要审核吗？',function(r){
                    if (r){
                        $.post(
                            "someauditS",
                            str,
                            function (date) {
                                if (date > 0) {
                                    $.messager.alert('友情提示', '审核成功')
                                    $("#dv").datagrid('reload')
                                } else {
                                    $.messager.alert('友情提示', '删除失败')
                                }
                            },"json"
                        )
                    }
                });
            }else {
                $.messager.alert('友情提示','请至少选择一项','info')
            }
        }

        function SomeAuditF() {
            var i = $("#dv").datagrid("getSelections")
            if (i.length>0){
                var str=""
                for (var j=0; j<i.length; j++){
                    str+="ids="+i[j].id+"&"
                }
                //截取字符串（索引开始，结束）
                str=str.substr(0,str.length-1)
                alert(str)
                $.messager.confirm('友情提示','您确认想要审核吗？',function(r){
                    if (r){
                        $.post(
                            "someauditF",
                            str,
                            function (date) {
                                if (date > 0) {
                                    $.messager.alert('友情提示', '审核成功')
                                    $("#dv").datagrid('reload')
                                } else {
                                    $.messager.alert('友情提示', '删除失败')
                                }
                            },"json"
                        )
                    }
                });
            }else {
                $.messager.alert('友情提示','请至少选择一项','info')
            }
        }

        function street() {
            var $id=$("#district").val()
            $("#street>option:gt(0)").remove()
            $.post("/pcontroller/findStreet",{"did":$id},function (data) {
                for (var i=0;i<data.length;i++) {
                    var $option = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                    $("#street").append($option)
                }

            },"json")
        }

        function toAudit() {
            $("#AuditDialog").dialog("open")
        }
        function CloseDialog() {
            $("#AuditDialog").dialog("close")
        }

        $("#btn").click(function () {
            $.post("getAudit",)
        })
    </script>
</head>
<body>
    <div id="dv"></div>

    <!—定义工具栏-->
    <div id="ToolBar">
        <div style="height: 40px;">
            <a href="javascript:toAudit()" class="easyui-linkbutton"
               iconCls="icon-add" plain="true">批量审核</a>

            <form action="getAudit" method="post" id="f">
                审核状态：<select id="state">
                            <option value="">全部</option>
                            <option value="1">已审核</option>
                            <option value="0">未审核</option>
                        </select>
                标题：<input type="text" name="title" id="">

                区域：<select name="did" id="district"><option value="">请选择</option></select>

                街道：<select name="sid" id="street"><option value="">请选择</option></select>

                类型：<select name="tid" id="type"><option value="">请选择</option></select>

                价格区间：<input type="text" name="startPrice">--<input type="text" name="endPrice">

                <input type="button" value="搜索" id="btn">
            </form>

        </div>
    </div>

    <!--审核窗口-->
    <div id="AuditDialog" class="easyui-dialog"
         style="width: 280px; height: 250px; padding: 10px 20px;" closed="true"  modal="true">

        <div id="AddDialogButtons" align="center">
            <br><br>
            <a href="javascript:SomeAuditS()" class="easyui-linkbutton" iconCls="icon-ok">审核通过</a><br><br><br>
            <a href="javascript:SomeAuditF()" class="easyui-linkbutton" iconCls="icon-no">审核未通过</a><br><br><br>
            <a href="javascript:CloseDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </div>
    </div>

</body>
</html>
