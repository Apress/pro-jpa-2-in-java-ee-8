<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <title>Edit Employee</title>
  </head>
  <body>

    <h2>Editing Employee with id: ${employee.id}</h2>
    <form action="EmployeeUpdateServlet" method="POST">
      <table border="1">
        <tbody>
          <tr><td>Name: </td><td><input type="text" name="name" value="${employee.name}"> (String) </td></tr>
          <tr><td>Salary: </td><td><input type="text" name="salary" value="${employee.salary}"> (long) </td></tr>
        </tbody>
      </table>
      <input type="submit" name="action" value="Save" /> 
      <input type="submit" name="action" value="Cancel" />
    </form>
  </body>
</html>
