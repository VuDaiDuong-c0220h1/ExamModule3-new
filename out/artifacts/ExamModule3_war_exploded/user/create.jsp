<%--
  Created by IntelliJ IDEA.
  User: Vu Dai Duong
  Date: 5/26/2020
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h1>Create product</h1>
<p>
    <a href="/home">Back to homepage</a>
</p>
<form action="/home?action=delete" method="post">
    <fieldset>
        <legend>Add Product</legend>
        <table>
            <tr>
                <td>Product ID: </td>
                <td><input type="text" name="id" id="id"></td>
            </tr>
            <tr>
                <td>Product Name: </td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Price: </td>
                <td><input type="text" name="price" id="price" ></td>
            </tr>
            <tr>
                <td>Quantity: </td>
                <td><input type="text" name="quantity" id="quantity" ></td>
            </tr>
            <tr>
                <td>Color: </td>
                <td><input type="text" name="color" id="color" ></td>
            </tr>
            <tr>
                <td>Category: </td>
                <td><input type="text" name="category" id="category" ></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit">Create Product</button></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>

