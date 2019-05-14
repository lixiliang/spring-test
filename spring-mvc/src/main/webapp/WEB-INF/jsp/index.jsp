<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <meta charset="UTF-8" />
    <title>Test20</title>
</head>
<body>
<h1>index page</h1>
<%--<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">访问Model</h3>
    </div>
    <div class="panel-body">
        <span th:text="${singlePerson.name}"></span>
    </div>
</div>
<div th:if="${not #lists.isEmpty(people)}">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">列表</h3>
        </div>
        <div class="panel-body">
            <ul class="list-group">
                <li class="list-group-item" th:each="person:${people}">
                    <span th:text="${person.name}"></span>
                    <span th:text="${person.age}"></span>
                    <button class="btn" th:onclick="'getName(\''+${person.name}+'\');'">获得名字</button>
                </li>
            </ul>
        </div>
    </div>
</div>--%>
<script type="text/javascript" src="https://test.sakyone.com/demo/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    $.get("<%=path%>/index/ajax","{data:'1'}",function (response) {
        alert(response)
    });
</script>
</body>
</html>
