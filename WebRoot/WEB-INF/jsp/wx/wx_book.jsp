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
      <h1 class="title text-white">${categoryName}</h1>
      <input type="hidden" id="categoryId" value="${categoryId}"/>
    </header>
    
    <div class="content">
        <ul id="bookList" class="table-view no-border-top leave-header">
          <li class="table-view-cell media table-cell-view-book">
            <a class="item" href='<idp:url value="/wx/book/1/secp"/>'>
              <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语面试技巧</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25<i class="fa fa-download ml20 mr5"></i>已下载：8</p>
              </div>
            </a>
          </li>
          <li class="table-view-cell media table-cell-view-book">
            <a class="item">
              <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语面试技巧</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25<i class="fa fa-download ml20 mr5"></i>已下载：8</p>
              </div>
            </a>
          </li>
          <li class="table-view-cell media table-cell-view-book">
            <a class="item">
              <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语面试技巧</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25<i class="fa fa-download ml20 mr5"></i>已下载：8</p>
              </div>
            </a>
          </li>
          <li class="table-view-cell media table-cell-view-book">
            <a class="item">
              <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语面试技巧</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25<i class="fa fa-download ml20 mr5"></i>已下载：8</p>
              </div>
            </a>
          </li>
        </ul>
    </div>
    
    <a class="downlog-btn"></a>
    
    <script src='<idp:url value="/js/json2.js"/>'></script>
    <script src='<idp:url value="/js/template.js"/>'></script>
    
    <script id="bookListTmpl" type="text/html">
        {{each booklist as book idx}}
            <li class="table-view-cell media table-view-cell-book">
                <a class="item" href='<idp:url value="/wx/book/"/>{{book.id}}/secp'>
                  <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
                  <div class="media-body f14">
                    <span class="fwb">{{book.name}}</span>
                    <p class="mt5 f12">{{book.desc}}</p>
                    <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25<i class="fa fa-download ml20 mr5"></i>已下载：8</p>
                  </div>
                </a>
            </li>
        {{/each}}
    </script>
    
    <script>
        $(document).ready(function(){
            loadBooks();
        });
        
        function loadBooks() {
            $.get(
                '<idp:url value="/wx/category/"/>${categoryId}/books',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var books = result.data;
                        var html = template('bookListTmpl', {"booklist" : books});
                        $('#bookList').append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
     </script>
  </body>
</html>