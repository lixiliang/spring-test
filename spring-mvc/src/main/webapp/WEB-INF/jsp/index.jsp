<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8" />
    <title>Test20</title>
    <link rel="stylesheet" type="text/css" href="@{bootstrap/css/bootstrap.min.css}">
    <link rel="stylesheet" type="text/css" href="@{bootstrap/css/bootstrap-theme.min.css}">

</head>
<body>
<div class="panel panel-primary">
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
</div>
<script th:src="@{jquery-3.2.1.min.js}" type="text/javascript"></script>
<script th:src="@{bootstrap/js/bootstrap.min.js}" type="text/javascript"></script>
<script th:inline="javascript">
    var single = [[${singlePerson}]];
    console.log(single.name+"/"+single.age);
    function getName(name) {
        console.log(name);
    }
</script>
</body>
</html>