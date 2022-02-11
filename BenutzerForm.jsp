
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Benutzer App</title>
</head>
<body>
    <h1>Benutzer Management</h1>
    <h2>


      <a href="/Benutzer2/list">zurück zur Liste</a>

    </h2>
    <div align="center">
        <c:if test="${benutzer != null}">
            <form action="update" method="post">


                <table border="1" >
                    <caption>
                        <c:if test="${benutzer != null}">
                            <h2>Edit Benutzer</h2>
                        </c:if>
                        <c:if test="${benutzer == null}">
                              <h2>Insert neuen Benutzer</h2>
                        </c:if>
                    </caption>
                    <c:if test="${benutzer != null}">
                        <input type="hidden" name="id" value="<c:out value='${benutzer.id}' />" />
                    </c:if>
                        <tr>
                            <th>Veorname: </th>
                            <td>
                                <input type="text" name="vorname" size="45"
                                        value="<c:out value='${benutzer.vorname}' />"
                                    />
                            </td>
                        </tr>
                        <tr>
                            <th>Nachname: </th>
                            <td>
                                <input type="text" name="nachname" size="45"
                                        value="<c:out value='${benutzer.nachname}' />"
                                />
                            </td>
                        </tr>
                        <tr>
                            <th>Email: </th>
                            <td>
                                <input type="text" name="email" size="45"
                                        value="<c:out value='${benutzer.email}' />"
                                />
                            </td>
                        </tr>

                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" value="Save" />
                            </td>
                        </tr>
                   </table>



               </form>
        </c:if>
        <c:if test="${benutzer == null}">
            <form action="insert" method="post">

                <table border="1" cellpadding="5">
                <caption>
                <h2>
                    <c:if test="${benutzer != null}">
                        Edit Benutzer
                    </c:if>
                    <c:if test="${benutzer == null}">
                        Insert neuen Benutzer
                    </c:if>
                </h2>
                </caption>
                    <c:if test="${benutzer != null}">
                        <input type="hidden" name="id" value="<c:out value='${benutzer.id}' />" />
                    </c:if>
                <tr>
                <th>Vorname: </th>
                <td>
                    <input type="text" name="vorname" size="45"
                            value="<c:out value='${benutzer.vorname}' />"
                        />
                </td>
                </tr>
                <tr>
                    <th>Nachname: </th>
                    <td>
                        <input type="text" name="nachname" size="45"
                                value="<c:out value='${benutzer.nachname}' />"
                        />
                    </td>
                </tr>
                <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="email" size="45"
                            value="<c:out value='${benutzer.email}' />"
                    />
                </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
            </form>
        </c:if>



    </div>
</body>
</html>

