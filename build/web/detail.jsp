<%-- 
    Document   : detail
    Created on : Jun 15, 2024, 10:37:34 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="./css/footer.css"/>
        <link rel="stylesheet" href="./css/detail.css"/>
        <title>Course Detail</title>
    </head>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fff;
        }

        .top-area {
            display: flex;
            align-items: center;
            justify-content: space-between;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 6px 0 rgba(0,0,0,0.1);
        }

        .top-area div, .top-area form {
            flex: 1;
        }

        .search-box {
            display: flex;
            position: relative;
            max-width: 600px; /* Adjust based on your preference */
            margin-left: auto; /* Aligns the search box to the right */
            margin-right: 20px;
        }

        .search-box input[type="text"] {
            width: 100%;
            padding: 10px 20px;
            border: 1px solid #ccc;
            border-radius: 20px;
            font-size: 16px;
            background-color: #fff;
            outline: none;
        }

        .search-box button {
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            background-color: transparent;
            border: none;
            cursor: pointer;
            color: #333;
            outline: none;
        }

    </style>
    <section class="top-area">
        <div>
            <a href="#"><img src="img/360_F_128939133_0WXTVdZ1bv1NXusQsdYYJLIwTVoXHqQ7.jpg" alt="" width="90px"/></a>
        </div>
        <form action="seachtext" method="post">
            <div class="search-box">
                <input name="txt"  value="${txtS}"type="text" aria-label="Small" aria-describedby="inputGroup-sizing-sm" placeholder="Search..." style=" border-radius: 20px;padding-left: 10px;background-color: #fff;margin: 10px 10px;padding-right: 70px;font-size: 16px;">
                <button type="submit"><i class="fa-solid fa-magnifying-glass" style="font-size: 16px;"></i></button>
            </div>
        </form>
    </section>
    <body>
        <div>
            <div >
                <c:set var="c" value="${requestScope.detail}"/>
                <div >
                    <div>
                        <div>
                            <div>
                                <div>                                  
                                    <p>${c.name}
                                    </p>
                                    <a href="" data-toggle="modal"data-target="#courseModal">
                                        <img class="img-fluid" src="${c.image}" alt="error"/>
                                    </a>  
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
