<%-- 
    Document   : search
    Created on : Jun 9, 2024, 5:47:17 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./css/search.css"/>
        <title>Search page</title>

    </head>
    <style>
        .list-group-item.active {
            background-color: #007bff;
        }
        .list-group-item:hover {
            background-color: pink !important;
        }
        .top-area {
            background-color: #fff;
            color: #fff;
            padding: 20px;
            justify-content: space-between;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: center; /* Đảm bảo các phần tử căn giữa theo chiều dọc */
        }

        .top-area img {
            margin-bottom: 0; /* Loại bỏ khoảng cách dưới */
        }

        .top-area .search-box {
            position: relative;
            max-width: 600px;
            width: 100%;
            padding-right: 20px; /* Điều chỉnh padding để phù hợp với nút tìm kiếm */
            margin: 0 20px; /* Thêm khoảng cách giữa logo và thanh tìm kiếm */
        }

        .top-area .search-box input {
            border: none;
            border-radius: 20px;
            padding: 10px 20px;
            width: calc(100% - 40px); /* Đảm bảo input chiếm toàn bộ chiều rộng trừ nút tìm kiếm */
            transition: width 0.4s ease-in-out;
            font-size: 16px;
            background-color: #fff;
        }

        .top-area .search-box button {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            color: #007bff;
            cursor: pointer;
            font-size: 16px;
        }

        .top-menu1 ul {
            list-style: none;
            padding: 0;
            margin: 0; /* Loại bỏ khoảng cách trên */
            display: flex;
            align-items: center; /* Đảm bảo các phần tử căn giữa theo chiều dọc */
        }

        .top-menu1 ul li {
            margin: 0 10px;
        }

        .top-menu1 ul li a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            padding: 10px 20px;
            border-radius: 20px;
            background-color: #0056b3;
            transition: background-color 0.3s ease;
        }

        .top-menu1 ul li a:hover {
            background-color: #003f7f;
        }

        .top-area a {
            text-decoration: none;
        }
        .card-top {

            border: 1px solid #ddd;
            border-radius: 4px;
            overflow: hidden;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            margin-bottom: 10px;
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            width: 100%;
            margin: 0 auto 10px auto;
        }
        .card-top:hover {
            transform: scale(1.05);
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
        }

        .card-img-top {
            width: 100%;
            height: 150px;
            object-fit: cover;
        }

        .card-body {
            padding: 10px;
        }

        .card-title {
            font-size: 1.25rem;
            margin-bottom: 15px;
        }

        .card-title a {
            text-decoration: none;
            color: #333;
        }

        .card-title a:hover {
            text-decoration: underline;
        }

        .card-text {
            font-size: 1rem;
            color: #555;
        }

        /* Định dạng thông báo lỗi */
        .error-message {
            color: red;
            font-size: 1.5rem;
            text-align: center;
            margin-top: 20px;
        }

        /* Làm cho lưới hiển thị đẹp */
        .row {
            display: flex;
            flex-wrap: wrap;
            margin: 0 -5px;
        }

        .col-12 {
            flex: 0 0 100%;
            max-width: 100%;
            padding: 5px;
        }

        .col-md-6 {
            flex: 0 0 50%;
            max-width: 50%;
            padding: 5px;
        }

        .col-lg-4 {
            flex: 0 0 33.3333%;
            max-width: 33.3333%;
            padding: 5px;
        }

        /* Khoảng cách giữa các thẻ card */
        .mb-2 {
            margin-bottom: 10px;
        }
        .paging-items li{
            list-style-type: none;
        }
        .paging-items-numbers{
            display: inline-block;
            border: 1px solid;
            padding: 5px 10px;
            margin: 10px 3px;
            border-radius: 10px;
        }
        .paging-items{
            padding-left: 0;
        }
        .paging-items-numbers:hover{
            background: chocolate;
        }
        .active1{
            background: chocolate;
        }
    </style>
    <body>
        <section class="top-area">
            <div>
                <a href="#"><img src="img/360_F_128939133_0WXTVdZ1bv1NXusQsdYYJLIwTVoXHqQ7.jpg" alt="" width="90px"/></a>
            </div>
            <form action="search" method="post">
                <div class="search-box">
                    <input name="txt" type="text" placeholder="What do you want to learn?" style=" border-radius: 20px; padding-left: 10px; background-color: #fff; margin: 10px 10px; padding-right: 70px; font-size: 16px;">
                    <button><i class="fa-solid fa-magnifying-glass" style="font-size: 16px;"></i></button>
                </div>
            </form>
            <div class="top-menu1">
                <ul>
                    <li><a href="login.jsp">Log in</a></li>
                    <li><a href="register.jsp">Sign Up</a></li>
                </ul>
            </div>
        </section>
        <div class="container">
            <div class="row">
                <jsp:include page="left.jsp"></jsp:include>
                    <div class="col-sm-9 pt-4">
                        <div class="row">
                        <c:forEach items="${requestScope.listcourses}" var="c">
                            <div class="col-12 col-md-6 col-lg-4 mb-2">
                                <div class="card-top">
                                    <img class="card-img-top" src="${c.image}" alt="Card image cap">
                                    <div class="card-body">
                                        <h4 class="card-title"><a href="detail?pid=${c.id}" title="View Course">${c.name}</a></h4>                                       
                                        <p class="card-text"><b>Skills you'll gain:</b> ${c.title}</p>
                                        <i class="fa-solid fa-star" style="color: yellowgreen;"></i><b> 4.8</b>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="col-12">
                            <c:if test="${requestScope.error != null}">
                                <h3 class="error-message">${requestScope.error}</h3>
                            </c:if>
                        </div>                       
                    </div>
                    <jsp:useBean class="dao.DAO" scope="request" id="a"/>
                    <div class="paging">
                        <ul class="paging-items">
                            <c:forEach begin="${1}" end="${a.numberPage}" var="i">
                                <a href="search?page=${i}" paging-current="${i}" class=""><li class="paging-items-numbers ${page==i?"activel":""}">${i}</li></a>
                                    </c:forEach>
                            <input data-path = "${requestScope.path}" hidden/>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="Footer.jsp"></jsp:include>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
        <script>
            const f1 = document.getElementById("f1");
            const sort = document.getElementById("sort");
            sort.addEventListener("change", (e) => {
                let sortType = sort.value;
                const path = window.location.search;
                if (path.includes("?cidd")) {
                    document.getElementById('formCategory').querySelector("#sortForm").value = sortType;
                    document.getElementById('formCategory').submit();
                } else {
                    document.getElementById('formPrice').querySelector("#sortForm").value = sortType;
                    document.getElementById('formPrice').submit();
                }
            });
            const pages = document.querySelectorAll("[paging-current]");
            const path = document.querySelector("[data-path]");
            pages.forEach(page => {
                page.addEventListener("click", (e) => {
                    let sortType = sort.value;
                    e.preventDefault();
                    const currentPage = page.getAttribute("paging-current");
                    const path = window.location.search;
                    if (path.includes("?cidd")) {
                        document.getElementById('formCategory').querySelector("#paging").value = currentPage;
                        document.getElementById('formCategory').querySelector("#sortForm").value = sortType;
                        document.getElementById('formCategory').submit();
                    } else {
                        document.getElementById('formPrice').querySelector("#paging").value = currentPage;
                        document.getElementById('formPrice').querySelector("#sortForm").value = sortType;
                        document.getElementById('formPrice').submit();
                    }
                });
            });

            function setCheck(Obj) {
                var fries = document.getElementsByName('cidd');
                if ((Obj.id === 'c0') && (fries[0].checked === true)) {
                    for (var i = 1; i < fries.length; i++) {
                        fries[i].checked = false;
                    }
                } else {
                    for (var i = 1; i < fries.length; i++) {
                        if (fries[i].checked === true) {
                            fries[0].checked = false;
                            break;
                        }
                    }
                }
                document.getElementById('formCategory').submit();
            }
            function setCheck1(obj) {
                var fries = document.getElementsByName('price');
                if ((obj.id === 'g0') && (fries[0].checked === true))
                {
                    for (var i = 1; i < fries.length; i++)
                        fries[i].checked = false;
                } else {
                    for (var i = 1; i < fries.length; i++) {
                        if (fries[i].checked === true) {
                            fries[0].checked = false;
                            break;
                        }
                    }
                }
                document.getElementById('formPrice').submit();
            }
        </script>
    </body>
</html>