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
        <form:form action="/ctsp/update" method="post" modelAttribute="ctsp">
            <div class="row mt-4">
                <div class="col-6">
                    <label>ID:</label>
                    <form:input type="text" class="form-control" path="id"/>
                    <form:errors path="id"/>
                </div>
            </div>
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
                Update
            </form:button>
            <label style="color: red">${msg1}</label>
        </form:form>
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
