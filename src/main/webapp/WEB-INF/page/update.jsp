<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0044)http://localhost:8080/HouseRent/page/add.jsp -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 -发布房屋信息</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../static/easyUI/css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../static/js/jquery-1.8.3.js"></script>

  <script>
    $(function () {
        $.post("findType",null,function (data) {
            for (var i=1;i<data.length;i++){
                var $option=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                $("#type").append($option)
            }
            $("#type").val(${house.typeId})
        },"json")

        $.post("findDistrict",null,function (data) {
            for (var i=0;i<data.length;i++) {
                var $option = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                $("#districtId").append($option)
            }
            $("#districtId").val(${house.districtId})

            street()
        },"json")

        $("#districtId").change(function () {
            street()
        })

        function street() {
            var $id=$("#districtId").val()
            $("#streetId>option:gt(0)").remove()
            $.post("findStreet",{"did":$id},function (data) {
                for (var i=0;i<data.length;i++) {
                    var $option = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                    $("#streetId").append($option)
                }
                $("#streetId").val(${house.streetId})
            },"json")
        }
    })
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../static/images/logo.gif"></DIV></DIV>
<DIV id=regLogin class=wrap>
<DIV class=dialog>
<DL class=clearfix>
  <DT>新房屋信息发布</DT>
  <DD class=past>填写房屋信息</DD></DL>
<DIV class=box>
<FORM id=add_action method=post action="updateHouse" enctype="multipart/form-data">
<DIV class=infos>
<TABLE class=field>
  <input type="hidden" name="id" value="${house.id}">
  <input type="hidden" name="path" value="${house.path}">
  <TBODY>
  <TR>
    <TD class=field>标　　题：</TD>
    <TD><INPUT id=add_action_title class=text type=text name=title value="${house.title}"> </TD></TR>
  <TR>
    <TD class=field>户　　型：</TD>
    <TD>
      <SELECT class=text name=typeId id="type">
        <option value="">请选择</option>
      </SELECT>
    </TD></TR>
  <TR>
    <TD class=field>面　　积：</TD>
    <TD><INPUT id=add_action_floorage class=text type=text name=floorage value="${house.floorage}"></TD></TR>
  <TR>
    <TD class=field>价　　格：</TD>
    <TD><INPUT id=add_action_price class=text type=text name=price value="${house.price}"> </TD></TR>
  <TR>
    <TD class=field>发布日期：</TD>
    <TD><INPUT class=text type=date name=pubdate value="<fmt:formatDate value="${house.pubdate}"></fmt:formatDate>"></TD></TR>
  <TR>
    <TD class=field>位　　置：</TD>
    <TD>区：<SELECT class=text name=districtId id="districtId">
              <option value="">请选择</option>
            </SELECT>
        街：<SELECT class=text name=streetId id="streetId">
              <option value="">请选择</option>
          </SELECT> </TD></TR>
  <TR>
    <TD class=field>联 系 方式：</TD>
    <TD><INPUT id=add_action_contact class=text type=text name=contact value="${house.contact}"> </TD></TR>
  <TR>
    <TD class=field>详细信息：</TD>
    <TD><TEXTAREA name=description>${house.description}</TEXTAREA></TD>
  </TR>
  <tr>
    <td>图　　片：</td>
    <td>
      <img src="http://localhost:85/${house.path}">
      <input type="file" name="pfile" >
    </td>
  </tr>
  </TBODY></TABLE>
<DIV class=buttons><INPUT value=立即发布 type=submit>
</DIV></DIV></FORM></DIV></DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
