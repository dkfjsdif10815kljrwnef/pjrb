<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
  <link rel="shortcut icon" href="/images/user/favicon.ico">
  <title>DEFAULT</title>
  <style>
    .wrap {
      position: relative;
      width: 100%;
      min-width: 320px;
      height: 100%;
      background: #fff;
    }

    .wrap_bg {
      position: absolute;
      left: 0;
      bottom: 0;
      width: 100%;
      height: 40%;
      background: #006bce;
    }

    .content {
      position: relative;
      width: 700px;
      height: 420px;
      margin: 220px auto 60px auto;
      background: url(/images/user/browser01.png) no-repeat left top, url(/images/user/browser02.png) no-repeat right top;
      background-color: #fff;
      box-shadow: 6px 10px 49px -23px rgba(0, 0, 0, 0.2);
      -webkit-border-radius: 14px 14px 0 0;
      border-radius: 14px 14px 0 0;
      z-index: 10;
      overflow: hidden;
      z-index: 1000;
    }

    .content::after {
      content: '';
      display: block;
      clear: both;
    }

    .txt {
      position: relative;
      width: 100%;
      top: 100px;
      text-align: center;
    }

    h4 {
      font-size: 30px;
      letter-spacing: -0.8px;
      margin: 20px 0 20px 0;
    }

    p {
      font-size: 18px;
      letter-spacing: -0.4px;
    }

    ul {
      margin-top: 33px;
    }

    ul li {
      display: inline-block;
    }

    ul li:first-child a {
      background: #004585;
    }

    ul li:last-child a {
      background: #006bce;
    }

    ul li a {
      display: block;
      color: #fff !important;
      padding: 7px 22px;
    }

    ul li a:hover {
      text-decoration: underline;
    }

    .bg_img {
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 60%;
    }

    .bg_img img {
      position: absolute;
      display: block;
    }

    .bg_img img.bg01 {
      left: 7%;
      bottom: 0;
    }

    .bg_img img.bg02 {
      right: 9%;
      bottom: -2px;
    }

    .bg_img img.bg03 {
      left: 25%;
      top: 12%;
      width: 89px;
      height: 89px;
      animation: sun 6s linear infinite;
      z-index: 1;
    }

    .bg_img img.bg04 {
      right: 20%;
      top: 35%;
      animation: balloon 1.5s ease infinite alternate;
    }

    .bg_img .bg05_wrap {
      position: absolute;
      left: 13%;
      bottom: 0;
      width: 55px;
      height: 94px;
    }

    .bg_img .bg06_wrap {
      position: absolute;
      right: 13%;
      bottom: 0;
      width: 103px;
      height: 174px;
    }

    .bg_img img.bg05 {
      left: 0;
      top: 0;
      width: 55px;
      height: 55px;
      animation: sun 3s linear infinite;
      z-index: 10;
    }

    .bg_img img.bg06 {
      right: 0;
      top: 0;
      width: 103px;
      height: 103px;
      animation: sun 5s linear infinite;
      z-index: 10;
    }

    .bg_img img.bg07 {
      left: 50%;
      bottom: 0;
      margin-left: -3px;
    }

    .bg_img img.bg08 {
      right: 50%;
      bottom: 0;
      margin-right: -4px;
    }

    @-webkit-keyframes sun {
      0% {
        transform: rotate(0deg);
      }

      100% {
        transform: rotate(360deg);
      }
    }

    @keyframes sun {
      0% {
        transform: rotate(0deg);
      }

      100% {
        transform: rotate(360deg);
      }
    }

    @-webkit-keyframes balloon {
      0% {
        transform: translateY(0);
      }

      100% {
        transform: translateY(20px);
      }
    }

    @keyframes balloon {
      0% {
        transform: translateY(0);
      }

      100% {
        transform: translateY(20px);
      }
    }


    @media only screen and (max-width:1280px) {
      .content {
        width: 600px;
      }
    }

    @media only screen and (max-width:768px) {
      .content {
        width: 400px;
      }
    }

    @media only screen and (max-width:600px) {
      .content {
        width: 90%;
        height: 350px;
      }

      h4 {
        font-size: 20px;
      }

      p {
        font-size: 15px;
      }
    }

    @media only screen and (max-width:400px) {
      .content {
        margin-top: 80px !important;
        background: url(/images/user/browser02.png) no-repeat right top #fff;
      }
    }
  </style>

  <script language="javascript">
    function fncGoAfterErrorPage() {
      history.back(-2);
    }
  </script>
</head>

<body>
  <div class="content">
    <div class="txt">
      <h4>페이지를 찾을 수 없습니다.</h4>
      <p>찾으시는 페이지의 주소가 잘못 입력되었거나<br>페이지의 주소 변경 혹은<br>서버에서 삭제되었을 수 있습니다.</p>
      <ul>
        <li><a href="javascript:fncGoAfterErrorPage();">뒤로가기</a></li>
		<li><a href="/main.do">메인으로</a></li>
      </ul>
    </div>
  </div>
  <div class="bg_img">
    <img src="/images/user/error_bg01.png" alt="" class="bg01">
    <img src="/images/user/error_bg02.png" alt="" class="bg02">
    <img src="/images/user/error_bg03.png" alt="" class="bg03">
    <img src="/images/user/error_bg04.png" alt="" class="bg04">
  </div>
  <div class="wrap_bg"></div>
</body>

</html>