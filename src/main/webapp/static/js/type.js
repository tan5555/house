/**
 * Created by Admin on 2019/12/24.
 */
//开启添加窗口
function toadd() {
    $("#AddDialog").dialog("open").dialog('setTitle',"添加类型")
}
//关闭窗口
function CloseDialog(Dialog) {
    $("#"+Dialog).dialog("close")
}

function SaveDialog() {
    /*  var param=$("#addDialogForm").serialize();//将表单序列化参数数据,与get请求参数一样
     $.post(
     "addType",
     "name="+$("#dname").val(),
     function (date) {
     if (date>0){
     $("#AddDialog").dialog("close")

     }  else {
     $.messager.alert('友情提示','添加失败，请联系管理员:13260601227!','info')
     }
     },"json"
     )*/

    /*easyUI请求*/
    $("#addDialogForm").form('submit',{
        url:"addType",
        success:function (data) {
            var obj=$.parseJSON(data);   //将json字符串转化为json对象
            if (obj>0){
                $("#AddDialog").dialog("close")
                $("#dv").datagrid("reload")
            }  else {
                $.messager.alert('友情提示','添加失败，请联系管理员:132xxxxxx!','info')
            }
        }
    })
//            window.location.reload(true)刷新数据


}

function toupdate() {
    /*以数组形式返回所有被选中的行数据*/
    var i = $("#dv").datagrid("getSelections")
    if (i.length==1){
        /*$("#UpdateDialog").dialog("open").dialog('setTitle',"修改区域")
         /!* 读取数据填充到表单中，i[0]是选中的第一个数据*!/
         $("#updateDialogForm").form('load',i[0])*/

        goUpdate(i[0].id)//调用修改窗口回显
    }else {
        $.messager.alert('友情提示','请选择一项','info')
    }

}

//修改
function goUpdate(id) {
    $("#UpdateDialog").dialog("open").dialog('setTitle',"修改类型")
    $.post("getType",{"id":id},function (date) {
        $("#updateDialogForm").form('load',date)
    },"json")
}

function EideDialog() {
    $("#updateDialogForm").form('submit',{
        url:"EideType",
        success:function (data) {
            var obj=$.parseJSON(data);   //将json字符串转化为json对象
            if (obj>0){
                $("#UpdateDialog").dialog("close")
                $("#dv").datagrid("reload")//刷新数据
            }  else {
                $.messager.alert('友情提示','修改失败，请联系管理员:132xxxxxx!','info')
            }
        }
    })
//            window.location.reload(true)刷新数据
}

//删除
function goDel(id) {
    $.messager.confirm('友情提示','您确认想要删除记录吗？',function(r){
        if (r){
            $.post("removeType",{"id":id},function (date) {
                if (date>0){
                    $.messager.alert('友情提示','删除成功')
                    $("#dv").datagrid('reload')
                }else {
                    $.messager.alert('友情提示','删除失败')
                }
            },"json")
        }
    });
}
//批量删除
function todel() {
    var i = $("#dv").datagrid("getSelections")
    if (i.length>0){
        var str=""
        for (var j=0; j<i.length; j++){
            str+="ids="+i[j].id+"&"
        }
        /*var arr=[]
         for (var j=0; j<i.length; j++){
         arr[j]=i[j].id
         }*/
        //截取字符串（索引开始，结束）
        str=str.substr(0,str.length-1)
        $.messager.confirm('友情提示','您确认想要删除记录吗？',function(r){
            if (r){
                $.post(
                    "removeTypeAll",
                    str,
                    function (date) {
                        if (date > 0) {
                            $.messager.alert('友情提示', '删除成功')
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