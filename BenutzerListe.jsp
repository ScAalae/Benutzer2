<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BenutzerListe</title>
</head>
<body>
  <div align="center">
        <table border="1">
            <caption>List of Users</caption>
            <tr>
                <th>ID</th>
                <th>Vorname</th>
                <th>Nachname</th>
                <th>Email</th>
                <th>CreatedOn</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="benutzer" items="${listBenutzer}">
                <tr>
                    <td><c:out value="${benutzer.id}" /></td>
                    <td><c:out value="${benutzer.vorname}" /></td>
                    <td><c:out value="${benutzer.nachname}" /></td>
                    <td><c:out value="${benutzer.email}" /></td>
                    <td><c:out value="${benutzer.createdOn}" /></td>
                    <td>
                        <a href="/Benutzer2/edit?id=<c:out value='${benutzer.id}' />">Edit</a>
                            
                        <a href="/Benutzer2/delete?id=<c:out value='${benutzer.id}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="/Benutzer2/new">Neue Benutzer</a>

    </div>   
</body>
</html>