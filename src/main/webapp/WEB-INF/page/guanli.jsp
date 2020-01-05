<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML xmlns="http://www.w3.org/1999/xhtml"><HEAD>
<TITLE>青鸟租房 - 用户管理</TITLE>
  <META content="text/html; charset=utf-8" http-equiv=Content-Type>
  <LINK rel=stylesheet type=text/css href="../static/easyUI/css/style.css">
  <META name=GENERATOR content="MSHTML 8.00.7601.17514">
  <script src="../static/js/jquery-1.8.3.js"></script>

  <script>

    function todel(id) {
        if(confirm("确认删除吗？")){
            location.href="delHouse?state=1&id="+id
        }
    }
  </script>
</HEAD>
<BODY>
<DIV id=header class=wrap>
<DIV id=logo><IMG src="../static/images/logo.gif"></DIV>
  <div class=search>
    <LABEL class="ui-green searchs"><a href="list">去租房</a></LABEL>
  </div>
<DIV class=search>
  <LABEL class="ui-green searchs"><a href="fabu">发布房屋信息</a></LABEL>
  <LABEL class=ui-green><INPUT onclick='document.location="exit"' value="退       出" type=button name=search></LABEL>
欢迎，${user.name}
</DIV></DIV>
<DIV class="main wrap">
<DIV id=houseArea>
<TABLE class=house-list>
  <TBODY>

    <c:forEach items="${pageInfo.list}" var="house">
      <TR class=odd>
        <TD class=house-thumb><SPAN>${house.path}<A href="details.jsp" target="_blank"><img src="http://localhost:85/${house.path}" width="100" height="75" alt=""></A></SPAN></TD>
        <TD>
          <DL>
            <DT><A href="details.jsp" target="_blank">${house.title}</A></DT>
            <DD>${house.dname}${house.sname},${house.floorage}平米,${house.tname}<BR>联系方式：${house.contact} </DD></DL></TD>
        <TD class=house-type>
          <c:if test="${house.ispass==0}">未审核</c:if>
          <c:if test="${house.ispass==1}">审核通过</c:if>
          <c:if test="${house.ispass==-1}">审核未通过</c:if>
        </TD>
        <TD class=house-type><LABEL class=ui-green><INPUT onclick='document.location="update?id=${house.id}"' value="修    改" type=button name=search></LABEL></TD>
        <TD class=house-price><LABEL class=ui-green><INPUT value="删    除" type=button onclick="todel(${house.id})"></LABEL></TD>
      </TR>
    </c:forEach>

  </TBODY></TABLE></DIV>
<DIV class=pager>
<UL>
  <c:forEach begin="1" end="${pageInfo.pages}" var="i">
    <LI class=current><A id=widget_338868862 href="house?page=${i}"
                         parseContent="true" showError="true" targets="houseArea"
                         ajaxAfterValidation="false" validate="false"
                         dojoType="struts:BindAnchor">${i}</A>
    </LI>
  </c:forEach>
</UL><SPAN class=total>${pageInfo.pageNum}/${pageInfo.pages}页</SPAN> </DIV></DIV>
<DIV id=footer class=wrap>
<DL>
  <DT>青鸟租房 © 2018 北大青鸟 京ICP证1000001号</DT>
  <DD>关于我们 · 联系方式 · 意见反馈 · 帮助中心</DD></DL></DIV>
</BODY>
  <script>
    <c:if test="${insert=='success'}">
      alert("发布成功")
    </c:if>

    <c:if test="${update=='success'}">
      alert("修改成功")
    </c:if>
    <c:if test="${update=='fail'}">
      alert("修改失败")
    </c:if>
  </script>
</HTML>
