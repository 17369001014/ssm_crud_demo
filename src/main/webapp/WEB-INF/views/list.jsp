<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSM_CRUD</title>
    <%--引入Jquery--%>
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.12.4.min.js"></script>
    <%--引入bootstrap的样式--%>
    <link href="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <%--引入bootstrap的js样式--%>
    <script src="${pageContext.request.contextPath}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <%--标题行--%>
    <div class="row">
        <%--  col-md-12 的意思是让他把12列占完--%>
        <div class="col-md-12">
            <h1>SSM_CRUD</h1>
        </div>
    </div>
    <%--按钮行--%>
    <div class="row">
        <%--按钮列占4列，偏移8列，那就位于最右边了--%>
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">添加</button>
            <button class="btn bg-danger">删除</button>
        </div>
    </div>
    <%--显示表格数据的行--%>
    <div class="row">
        <%--表格数据就占12列把一整行占完--%>
        <div class="col-md-12">
            <table class="table table-hover table-bordered">
                <tr>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <%--取出员工信息--%>
                <c:forEach items="${pageInfo.list}" var="emp">
                    <tr>
                        <td>${emp.empId}</td>
                        <td>${emp.empName}</td>
                        <td>${emp.gender=="M"?"男":"女"}</td>
                        <td>${emp.email}</td>
                        <td>${emp.department.deptName}</td>
                        <td>
                                <%-- btn-sm 表示按钮的尺寸是小一点的按钮--%>
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash"></span>
                                删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>
    <%--显示分页按钮--%>
    <div class="row">
        <%--分页文字信息占6列--%>
        <div class="col-md-6">
            当前第 ${pageInfo.pageNum} 页/总页数 ${pageInfo.pages} 页 总记录数 ${pageInfo.total} 条
        </div>
        <%--分页按钮占6列--%>
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="${pageContext.request.contextPath}/emp?pageNum=1">首页</a></li>
                    <%--判断如果还有上一页按钮，再显示上一页的按钮--%>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/emps?pageNum=${pageInfo.pageNum-1}"
                               aria-label="Previous">
                                <span aria-hidden="true">上一页</span>
                            </a>
                        </li>
                    </c:if>
                    <%--取连续的页码--%>

                    <c:forEach items="${pageInfo.navigatepageNums}" var="page">
                        <%--判断如果页码是当前页就高亮显示 class="active" 这个样式 bootstrap 可以高亮显示--%>
                        <c:if test="${pageInfo.pageNum==page}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/emps?pageNum=${page}">${page}</a>
                            </li>
                        </c:if>
                        <c:if test="${pageInfo.pageNum!=page}">
                            <li>
                                <a href="${pageContext.request.contextPath}/emps?pageNum=${page}">${page}</a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <%--判断如果还有下一页，再显示下一页的按钮--%>
                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/emps?pageNum=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">下一页</span>
                            </a>
                        </li>
                    </c:if>
                    <li>
                        <a href="${pageContext.request.contextPath}/emps?pageNum=${pageInfo.pages}">末页</a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>
