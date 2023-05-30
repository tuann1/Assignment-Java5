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
    <h3 style="text-align: center"> QUẢN LÝ MÀU SẮC</h3>
</header>

<section>
    <div class="container">
        <form action="/mau-sac/search" method="get">
            Mã: <input type="text" name="ma"/>
            <br/>
            <button class="btn btn-success col-1 m-3" type="submit">
                <a style="text-decoration-line: none; color: aliceblue">Search</a>
            </button>
        </form>

        <form:form action="/mau-sac/add" method="post" modelAttribute="ms">
            <div class="row mt-4">
                <div class="col-6">
                    <label>ID</label>
                    <form:input type="text" class="form-control" path="id"/>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <label>Mã</label>
                    <form:input type="text" class="form-control" path="ma"/>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <label>Tên</label>
                    <form:input type="text" class="form-control" path="ten"/>
                </div>
            </div>

            <form:button class="btn btn-success col-1 m-3" type="submit">
                ADD
            </form:button>
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
        <c:forEach items="${listMauSac}" var="mau" varStatus="viTri">
            <tr>
                <td>${mau.id}</td>
                <td>${mau.ma}</td>
                <td>${mau.ten}</td>
                <td>
                    <a class="btn" href="/mau-sac/detail/${mau.id}" tabindex="-1" role="button" aria-disabled="true">Detail</a>

                    <a class="btn" href="/mau-sac/view-update?${mau.id}" tabindex="-1"
                       role="button"
                       aria-disabled="true">Update</a>
                    <a class="btn" href="/mau-sac/remove/${mau.id}" tabindex="-1"
                       role="button"
                       aria-disabled="true">Remove</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

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