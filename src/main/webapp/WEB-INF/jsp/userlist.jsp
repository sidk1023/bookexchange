<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML>
<html>
    <head>
        <title>
            Usere
        </title>
    </head>
    <body>
        <h1>User List</h1>
        <table>
        <c:forEach items="${userList}" var="user">
                    <tr>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.wallet}</td>
                    </tr>
                </c:forEach>
            </table>
    </body>
</html>
