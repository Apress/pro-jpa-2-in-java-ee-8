<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>All Employees</title>
  </head>
  <body>
    <h3>Chapter 5: Conversation Example</h3>
     This example shows how to use an extended unsynchronized persistence context to model a conversation.

    <form action="EmployeeEditServlet" method="POST">
      <table border="1">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Salary</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${employees}" var="emp">
            <tr>
              <td><c:out value="${emp.id}"/> </td>
              <td><c:out value="${emp.name}"/> </td>
              <td><c:out value="${emp.salary}"/> </td>
              <td><input type="radio" name="id" value="${emp.id}"> </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
      <td><input type="submit" value="Edit Employee" /></td>
      <td><input type="submit" name="saveAction" value="Save All" /></td>
      <td><input type="submit" name="saveAction" value="Abandon All" /></td>
    </form>
  </body>
</html>
