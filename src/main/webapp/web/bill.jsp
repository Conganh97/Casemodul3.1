<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 7/8/2022
  Time: 5:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>Create dish</h1>
    <h2>
        <a href="sanpham?action=sanpham">Back to admin</a>
    </h2>
</center>
<div align="center">
    <form method="post" action="">
        <table border="1" cellpadding="5">
            <caption>
                <tr>
                    <th>ProductName:</th>
                    <td>
                        <input type="text" name="tensp" id="tensp" size="45" placeholder="Enter name"/>
                    </td>
                </tr>
                <tr>
                    <th>Unit:</th>
                    <td>
                        <input type="text" name="dvt" id="dvt" size="45" placeholder="Enter unit"/>
                    </td>
                </tr>
                <tr>
                    <th>Description:</th>
                    <td>
                        <input type="text" name="mota" id="mota" size="45" placeholder="Enter Description"/>
                    </td>
                </tr>
                <tr>
                    <th>Price:</th>
                    <td>
                        <input type="text" name="gia" id="gia" size="45" placeholder="Enter price"/>
                    </td>
                </tr>
                <tr>
                    <th>Image:</th>
                    <td>
                        <input type="text" name="img" id="img" size="45" placeholder="Enter image"/>
                    </td>
                </tr>
                <tr>
                    <th>Kind of product:</th>
                    <td>
                        <input type="text" name="loaisp" id="loaisp" size="45" placeholder="Enter kind of product"/>

                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Create"/>
                    </td>
                </tr>

            </caption>
        </table>
    </form>


</div>
</body>
</html>
