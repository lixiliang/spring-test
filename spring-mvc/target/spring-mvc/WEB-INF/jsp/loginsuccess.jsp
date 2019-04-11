<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <script src="<%=request.getContextPath()%>/static/js/jquery-3.2.1.min.js"></script>
</head>
<body>
username: ${userName}
<input type="button" id="btn-logout" value="logout" onclick="logout()"/>
</body>
<script type="text/javascript">
    function logout() {
        $.ajax({
            type: "post",
            url: "<%=path%>" + "/logout.json",
            dataType: "json",
            success: function(data) {
                if(data.success == false){
                    alert(data.errorMsg);
                }else{
                    //登出成功
                    window.location.href = "<%=path%>" +  "/login.html";
                }
            },
            error: function(data) {
                alert("logout失败...."+JSON.stringify(data));
            }
        });
    }
</script>
</html>
