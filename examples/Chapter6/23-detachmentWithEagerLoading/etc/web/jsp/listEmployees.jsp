<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>All Employees</title>
  </head>
  <body>
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Department</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${employees}" var="emp">
          <tr>
            <td><c:out value="${emp.name}"/></td>
            <td><c:out value="${emp.department.name}"/></td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
