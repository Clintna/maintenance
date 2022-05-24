<%--解决页面乱码--%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>
</head>

<body>
<h1>系统主页</h1>
<a href="${pageContext.request.contextPath}/stUserInfo/logout">退出用户</a>
<ul>
    <%-- admin角色的用户能同时拥有用户管理和订单管理的权限，user角色的用户只拥有订单管理的权限 --%>
    <shiro:hasRole name="admin">
        <li>
            <a href="">用户管理</a>
        </li>
    </shiro:hasRole>

    <shiro:hasAnyRoles name="admin,user">
        <li>
            <a href="">订单管理</a>
            <ul>
                <shiro:hasPermission name="order:add">
                    <li><a href="">新增</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="order:del">
                    <li><a href="">删除</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="order:upd">
                    <li><a href="">修改</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="order:find">
                    <li><a href="">查询</a></li>
                </shiro:hasPermission>

            </ul>
        </li>
    </shiro:hasAnyRoles>
</ul>
</body>
</html>
