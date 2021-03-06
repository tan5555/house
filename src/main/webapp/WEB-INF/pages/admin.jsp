<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2019/12/20
  Time: 19:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head id="Head1">
    <title>中房后台管理系统</title>
    <link rel="stylesheet" type="text/css" href="static/easyUI/css/demo.css">
    <link rel="stylesheet" type="text/css" href="static/easyUI/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="static/easyUI/themes/icon.css" />
    <script language="JavaScript" type="text/javascript" src="static/js/jquery-1.8.3.js"></script>
    <script language="javascript" type="text/javascript" src="static/js/jquery.easyui.min.js" ></script>
    <script type="text/javascript" src='static/js/outlook2.js'> </script>
    <script type="text/javascript">

            var _menus = {"menus":[
                {"menuid":"1","icon":"icon-sys","menuname":"常规设置",
                    "menus":[
                        {"menuid":"12","menuname":"学生管理","icon":"icon-add","url":"controller/stumanjsp"},
                        {"menuid":"13","menuname":"用户管理","icon":"icon-users","url":"demo2.html"},
                        {"menuid":"14","menuname":"区域管理","icon":"icon-role","url":"controller/districtjsp"},
                        {"menuid":"15","menuname":"街道管理","icon":"icon-set","url":"controller/streetjsp"},
                        {"menuid":"16","menuname":"类型管理","icon":"icon-log","url":"controller/typejsp"}
                    ]
                },{"menuid":"8","icon":"icon-sys","menuname":"房子管理",
                    "menus":[{"menuid":"21","menuname":"审核列表","icon":"icon-nav","url":"controller/auditjsp"},
                        {"menuid":"22","menuname":"已审核","icon":"icon-nav","url":"demo1.html"},
                        {"menuid":"22","menuname":"未审核","icon":"icon-nav","url":"demo1.html"}
                    ]
                },{"menuid":"56","icon":"icon-sys","menuname":"区域管理",
                    "menus":[{"menuid":"31","menuname":"区域列表","icon":"icon-nav","url":"controller/districtjsp"},
                        {"menuid":"32","menuname":"添加区域","icon":"icon-nav","url":"demo2.html"}
                    ]
                },{"menuid":"28","icon":"icon-sys","menuname":"街道管理",
                    "menus":[{"menuid":"41","menuname":"街道列表","icon":"icon-nav","url":"controller/streetjsp"},
                        {"menuid":"42","menuname":"添加街道","icon":"icon-nav","url":"demo1.html"}
                    ]
                },{"menuid":"39","icon":"icon-sys","menuname":"类型管理",
                    "menus":[{"menuid":"51","menuname":"类型列表","icon":"icon-nav","url":"controller/typejsp"},
                        {"menuid":"52","menuname":"添加类型","icon":"icon-nav","url":"demo1.html"}
                    ]
                }, {
                    "menuid": "39", "icon": "icon-sys", "menuname": "房子管理",
                    "menus": [{"menuid": "51", "menuname": "房子列表", "icon": "icon-nav", "url": "controller/usersjsp"},
                        {"menuid": "52", "menuname": "添加房子", "icon": "icon-nav", "url": "demo1.html"}
                    ]
                }
            ]};
            //设置登录窗口
            function openPwd() {
                $('#w').window({
                    title: '修改密码',
                    width: 300,
                    modal: true,
                    shadow: true,
                    closed: true,
                    height: 160,
                    resizable:false
                });
            }
            //关闭登录窗口
            function closePwd() {
                $('#w').window('close');
            }



            //修改密码
            function serverLogin() {
                var $newpass = $('#txtNewPass');
                var $rePass = $('#txtRePass');

                if ($newpass.val() == '') {
                    msgShow('系统提示', '请输入密码！', 'warning');
                    return false;
                }
                if ($rePass.val() == '') {
                    msgShow('系统提示', '请在一次输入密码！', 'warning');
                    return false;
                }

                if ($newpass.val() != $rePass.val()) {
                    msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
                    return false;
                }

                $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                    msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                    $newpass.val('');
                    $rePass.val('');
                    close();
                })

            }

            $(function() {

                openPwd();

                $('#editpass').click(function() {
                    $('#w').window('open');
                });

                $('#btnEp').click(function() {
                    serverLogin();
                })

                $('#btnCancel').click(function(){closePwd();})

                $('#loginOut').click(function() {
                    $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {

                        if (r) {
                            location.href = '/ajax/loginout.ashx';
                        }
                    });
                })
            });


    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="static/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
    </div></noscript>
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(static/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head">欢迎 某某 <a href="#" id="editpass" style="color:#fff;">修改密码</a> <a href="#" id="loginOut" style="color:#fff;">安全退出</a></span>
    <span style="padding-left:10px; font-size: 16px; "><img src="static/images/blocks.gif" width="20" height="20" align="absmiddle" />中房后台管理系统</span>
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">后台管理系统  版权归老王所有  联系方式:132xxxxxx</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->

    </div>

</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
        <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
            <center>欢迎使用后台管理系统</center>
        </div>
    </div>
</div>


<!--修改密码窗口-->
<div id="w" class="easyui-window" title="修改密码" collapsible="false" minimizable="false"
     maximizable="false" icon="icon-save"  style="width: 300px; height: 150px; padding: 5px;
        background: #fafafa;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table cellpadding=3>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="password" class="txt01" /></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="password" class="txt01" /></td>
                </tr>
            </table>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
            <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" >
                确定</a> <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>
<div id="mm" class="easyui-menu" style="width:150px;">
    <div id="mm-tabupdate">刷新</div>
    <div class="menu-sep"></div>
    <div id="mm-tabclose">关闭</div>
    <div id="mm-tabcloseall">全部关闭</div>
    <div id="mm-tabcloseother">除此之外全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-tabcloseright">当前页右侧全部关闭</div>
    <div id="mm-tabcloseleft">当前页左侧全部关闭</div>
    <div class="menu-sep"></div>
    <div id="mm-exit">退出</div>
</div>
</body>
</html>
