<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../include.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>学语者</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <link rel="stylesheet" href='<idp:url value="/css/base.css"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/wx_book.css"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/font-awesome.css"/>'>
    <script src='<idp:url value="/js/jquery-1.8.3.min.js"/>'></script>
  </head>
  <body>
    <!-- Make sure all your bars are the first things in your <body> -->
    <header class="bar bar-nav header-border header-theme">
      <a class="icon fa fa-angle-left pull-left text-white f16" href='<idp:url value="/wx/index"/>'>&nbsp;返回</a>
      <h1 class="title text-white">搜索</h1>
    </header>
    
    <div class="content">
        <ul id="bookList" class="table-view no-border-top leave-header">
          
          <c:forEach var="book" items="${books}" varStatus="status">     
              <li class="table-view-cell media table-cell-view-book">
                <a class="item" href='<idp:url value="/wx/book/"/>${book.id}/secp'>
                  <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
                  <div class="media-body f14">
                    <span class="fwb">${book.name}</span>
                    <p class="mt5 f12">${book.desc}</p>
                    <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25<i class="fa fa-download ml20 mr5"></i>已下载：8</p>
                  </div>
                </a>
              </li>    
          </c:forEach>   
        </ul>
    </div>
    
    <a class="downlog-btn"></a>
  </body>
</html>