<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0030)http://localhost:8080/House-2/ -->
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD><TITLE>青鸟租房 - 首页</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../static/easyUI/css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../static/js/jquery-1.8.3.js"></script>
  <script>
    $(function () {
        $.post("/pcontroller/findType",null,function (data) {
            for (var i=1;i<data.length;i++){
                var $option=$("<option value='"+data[i].id+"'>"+data[i].name+"</option>")
                $("#type").append($option)
            }
            $("#type").val(${serchCondition.tid})
        },"json")

        $.post("/pcontroller/findDistrict",null,function (data) {
            for (var i=0;i<data.length;i++) {
                var $option = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                $("#district").append($option)
            }
            $("#district").val(${serchCondition.did})
            street()
        },"json")

        $("#district").change(function () {
            street()
        })
    })

    function street() {
        var $id=$("#district").val()
        $("#street>option:gt(0)").remove()
        if($id!=""){
            $.post("findStreet",{"did":$id},function (data) {
                for (var i=0;i<data.length;i++) {
                    var $option = $("<option value='" + data[i].id + "'>" + data[i].name + "</option>")
                    $("#street").append($option)
                }
                $("#street").val(${serchCondition.sid})
            },"json")
        }
    }

    function topage(p) {
        $("#page").val(p)
        $("#sform").submit()
    }

  </script>

</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../static/images/logo.gif"></DIV>
  <DIV class=search>
    <LABEL class=ui-green><INPUT onclick='document.location="house"' value="我的房子" type=button name=search></LABEL>
  </DIV>
</DIV>
<DIV id=navbar class=wrap>
<DL class="search clearfix">
  <FORM id=sform method=post action="list">
    <input type="hidden" name="page" id="page">
    <input type="hidden" name="ispass" value="1">
    标题：<input type="text" name="title" value="${serchCondition.title}">

    区域：<select name="did" id="district"><option value="">不限</option></select>

    街道：<select name="sid" id="street"><option value="">不限</option></select>

    类型：<select name="tid" id="type"><option value="">不限</option></select>

    价格区间：<input type="text" name="startPrice" size="10px" value="${serchCondition.startPrice}">--<input type="text" name="endPrice" size="10px" value="${serchCondition.endPrice}">


    <LABEL class=ui-blue><INPUT value="搜索房屋" type="submit" name=search></LABEL>
  </FORM></DL></DIV>
<DIV class="main wrap">
<TABLE class=house-list>
  <TBODY>

  <c:forEach items="${pageInfo.list}" var="house">
    <TR>
      <TD class=house-thumb><span><A href="details.jsp" target="_blank"><img src="http:localhost:85/${house.path}" width="100" height="75" alt=""></a></span></TD>
      <TD>
        <DL>
          <DT><A href="details.jsp" target="_blank">${house.title}</A></DT>
          <DD>${house.dname}${house.sname},${house.floorage}平米<BR>联系方式：3${house.contact} </DD></DL></TD>
      <TD class=house-type>${house.tname}</TD>
      <TD class=house-price><SPAN>${house.price}</SPAN>元/月</TD>
    </TR>
  </c:forEach>

  <TR>无租房信息</TR>
  </TBODY>
</TABLE>
<DIV class=pager>
<UL>
  <LI class=current><A href="javascript:topage(${pageInfo.firstPage})">首页</A></LI>
  <LI><A href="javascript:topage(${pageInfo.prePage})">上一页</A></LI>
  <LI><A href="javascript:topage(${pageInfo.nextPage==0?pageInfo.pages:pageInfo.nextPage})">下一页</A></LI>
  <LI><A href="javascript:topage(${pageInfo.lastPage})">末页</A></LI></UL><SPAN
class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV></BODY></HTML>
