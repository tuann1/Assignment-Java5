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
    <h3 style="text-align: center; padding-bottom: 60px"> QUẢN LÝ CHI TIẾT SẢN PHẨM</h3>
</header>

<section>
    <div>
        <form:form action="/ctsp/add" method="post" modelAttribute="ctsp">
            <div class="row mt-4">
                <div class="col-6">
                    <label>Sản phẩm</label>
                    <form:select path="sanPham">
                        <form:options items="${listSanPham}" itemValue="id" itemLabel="ten"/>
                    </form:select>
                </div>
                <div class="col-6">
                    <label>NSX</label>
                    <form:select path="nsx">
                        <form:options items="${listNsx}" itemValue="id" itemLabel="ten"/>
                    </form:select>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <label>Màu sắc</label>
                    <form:select path="mauSac">
                        <form:options items="${listMauSac}" itemValue="id" itemLabel="ten"/>
                    </form:select>
                </div>
                <div class="col-6">
                    <label>Dòng sản phẩm</label>
                    <form:select path="dongSanPham">
                        <form:options items="${listDongSp}" itemValue="id" itemLabel="ten"/>
                    </form:select>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-6">
                    <label>Năm bảo hành:</label>
                    <form:input type="text" class="form-control" path="namBaoHanh"/>
                    <form:errors path="namBaoHanh"/>
                </div>
                <div class="col-6">
                    <label>Mô tả:</label>
                    <form:input type="text" class="form-control" path="moTa"/>
                    <form:errors path="moTa"/>
                </div>
            </div>
            <div class="row mt-4">
                <div class="col-4">
                    <label>Số lượng tồn:</label>
                    <form:input type="text" class="form-control" path="soLuongTon"/>
                    <form:errors path="soLuongTon"/>
                </div>
                <div class="col-4">
                    <label>Giá nhập:</label>
                    <form:input type="text" class="form-control" path="giaNhap"/>
                    <form:errors path="giaNhap"/>
                </div>
                <div class="col-4">
                    <label>Giá bán:</label>
                    <form:input type="text" class="form-control" path="giaBan"/>
                    <form:errors path="giaBan"/>
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
            <th scope="col">#</th>
            <th scope="col">Tên sản phẩm</th>
            <th scope="col">Tên NSX</th>
            <th scope="col">Màu sắc</th>
            <th scope="col">Dòng sản phẩm</th>
            <th scope="col">Số lượng tồn</th>
            <th scope="col">Giá nhập</th>
            <th scope="col">Mô tả</th>
            <th scope="col">Chức năng</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCtsp}" var="ct" varStatus="viTri">
            <tr>
                <td>${viTri.index + 1}</td>
                <td>${ct.sanPham.ten}</td>
                <td>${ct.nsx.ten}</td>
                <td>${ct.mauSac.ten}</td>
                <td>${ct.dongSanPham.ten}</td>
                <td>${ct.soLuongTon}</td>
                <td>${ct.giaNhap}</td>
                <td>${ct.moTa}</td>
                <td>
                    <a href="/ctsp/view-update/${ct.id}" tabindex="-1"
                       role="button" class="btn btn-primary"
                       aria-disabled="true">Update</a>
                    <a href="/ctsp/remove/${ct.id}" tabindex="-1"
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
                <a href="/ctsp/hien-thi/${i}">${i}</a>
        </span> &nbsp;&nbsp;
        </c:forEach>

        <c:choose>
            <c:when test="${currentPage < totalPages}">
                <a href="/ctsp/hien-thi/${currentPage + 1}">Next</a>
            </c:when>
            <c:otherwise>
                <span>Next</span>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${currentPage > 1}">
                <a href="/ctsp/hien-thi/${currentPage - 1}">Previous</a>
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
