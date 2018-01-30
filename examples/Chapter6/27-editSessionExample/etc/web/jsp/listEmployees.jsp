<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>All Employees</title>
  </head>
  <body>
    <h3>Chapter 5: Employee Edit Session Example</h3>
     This example shows how to use a SFSB and an extended EntityManager to 
     avoid detaching/merging entities.

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
      <td><input type="submit" value="Edit" /></td>
    </form>
  </body>
</html>
