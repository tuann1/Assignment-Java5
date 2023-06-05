<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
</head>
<body>
<header>
    <h3 style="text-align: center"> QUẢN LÝ DÒNG SẢN PHẨM</h3>
</header>

<section>
    <div class="container">
        <form action="/dong-san-pham/search/${currentPage}" method="get">
            Mã: <input type="text" name="ma"/>
            <br/>
            <button class="btn btn-success col-1 m-3" type="submit">
                <a style="text-decoration-line: none; color: aliceblue">Search</a>
            </button>
        </form>

        <form:form action="/dong-san-pham/add" method="post" modelAttribute="dongSp">
            <div class="row mt-4">
                <div class="col-6">
                    <label>ID</label>
                    <form:input type="text" class="form-control" path="id"/>
                    <form:errors path="id"/>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <label>Mã</label>
                    <form:input type="text" class="form-control" path="ma"/>
                    <form:errors path="ma"/>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <label>Tên</label>
                    <form:input type="text" class="form-control" path="ten"/>
                    <form:errors path="ten"/>
                </div>
            </div>

            <form:button class="btn btn-success col-1 m-3" type="submit">
                ADD
            </form:button>
            <label style="color: red">${msg}</label>
        </form:form>
    </div>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Mã</th>
            <th scope="col">Tên</th>
            <th scope="col">Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listDongSp}" var="dong" varStatus="viTri">
            <tr>
                <td>${dong.id}</td>
                <td>${dong.ma}</td>
                <td>${dong.ten}</td>
                <td>
                    <a href="/dong-san-pham/view-update/${dong.id}" tabindex="-1"
                       role="button" class="btn btn-primary"
                       aria-disabled="true">Update</a>
                    <a href="/dong-san-pham/remove/${dong.id}" tabindex="-1"
                       role="button" class="btn btn-danger"
                       aria-disabled="true">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div>
        Total Items: ${totalItems} - Page ${currentPage} of ${totalPages}
        &nbsp;
        <c:forEach var="i" begin="1" end="${totalPages}">
        <span>
                <a href="/dong-san-pham/hien-thi/${i}">${i}</a>
        </span> &nbsp;&nbsp;
        </c:forEach>

        <c:choose>
            <c:when test="${currentPage < totalPages}">
                <a href="/dong-san-pham/hien-thi/${currentPage + 1}">Next</a>
            </c:when>
            <c:otherwise>
                <span>Next</span>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${currentPage > 1}">
                <a href="/dong-san-pham/hien-thi/${currentPage - 1}">Previous</a>
            </c:when>
            <c:otherwise>
                <span>Previous</span>
            </c:otherwise>
        </c:choose>
    </div>

</section>

<footer>
    <h3 style="text-align: center"> TUANNV </h3>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"
        integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD"
        crossorigin="anonymous"></script>

</body>
</html>
