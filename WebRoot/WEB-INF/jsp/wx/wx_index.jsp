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
    <link rel="stylesheet" href='<idp:url value="/css/wx_index.css"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/font-awesome.css"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/loaders.css"/>'>
    
    <script src='<idp:url value="/js/jquery-1.8.3.min.js"/>'></script>
    <script src='<idp:url value="/js/xyzwx.js"/>'></script>
  </head>
  <body>
    <!-- Make sure all your bars are the first things in your <body> -->
    <header class="bar bar-nav header-border">
      <a class="logo pull-left"></a>
      <a class="icon fa fa-user pull-right iconcolor" href='<idp:url value="/wx/profile"/>'></a>
      <a class="icon fa fa-search pull-right iconcolor" style="margin-right: 0.5em;" onclick="openSearchModal();"></a>
    </header>
    
    <div class="content">
        <div class="banner">
            <img src='<idp:url value="/img/banner/1.jpg"/>' width="100%"/> 
        </div>
        
        <ul id="categoryList" class="table-view no-border-top">
          <li class="table-view-cell media table-cell-view-category">
            <a class="item" href='<idp:url value="/wx/category/1/bp"/>'>
              <img class="media-object pull-left category-logo" src='<idp:url value="/img/banner/c1.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>书目：5</p>
              </div>
            </a>
          </li>
          <li class="table-view-cell media table-cell-view-category">
            <a class="item">
              <img class="media-object pull-left category-logo" src='<idp:url value="/img/banner/c2.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>书目：5</p>
              </div>
            </a>
          </li>
          <li class="table-view-cell media table-cell-view-category">
            <a class="item">
              <img class="media-object pull-left category-logo" src='<idp:url value="/img/banner/c3.png"/>'>
              <div class="media-body f14">
                <span class="fwb">三一英语口语</span>
                <p class="mt5 f12">三一口语（GESE）是经英国文化委员会提议，专门为非英语国家设计的纯英语口语等级考试体系。</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>书目：5</p>
              </div>
            </a>
          </li>
        </ul>
    </div>
    
    <a class="downlog-btn" href='<idp:url value="/wx/dlp"/>'></a>
    
    <div id="searchModal" class="modal" style="display: none;">
        <a class="fa fa-close close-btn pull-right f20" onclick="closeModal();"></a>
        <input type="search" id="searchKey" class="mt20" placeholder="书名关键字">
        <button class="btn btn-positive btn-block" onclick="searchBook();">搜&nbsp;索</button>
    </div>
    
    <script src="<idp:url value="/js/json2.js"/>"></script>
    <script src="<idp:url value="/js/template.js"/>"></script>
    
    <script id="cgListTmpl" type="text/html">
        {{each cglist as cg idx}}
            <li class="table-view-cell media table-cell-view-category">
                <a class="item" href='<idp:url value="/wx/category/"/>{{cg.id}}/bp'>
                  <img class="media-object pull-left category-logo" src='<idp:ctx/>{{cg.logoUrl}}'>
                  <div class="media-body f14">
                    <span class="fwb">{{cg.name}}</span>
                    <p class="mt5 f12">{{cg.desc}}</p>
                    <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>书目：5</p>
                  </div>
                </a>
            </li>
        {{/each}}
    </script>
    
    <script>
        $(document).ready(function(){
            showMaskLayer();
            loadCategories();
        });
        
        function loadCategories() {
            $.get(
                '<idp:url value="/wx/category"/>',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var categories = result.data;
                        var html = template('cgListTmpl', {"cglist" : categories});
                        $('#categoryList').append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                    hideMaskLayer();
                }
            );
        }
        
        function openSearchModal() {
            $('#searchModal').show('slideUp');
        }
        
        function closeModal() {
            $('#searchModal').hide('slideDown');
        }
        
        function searchBook() {
            var bookName = $('#searchKey').val();
            if(bookName && $.trim(bookName) != '') {
                window.location.href = '<idp:url value="/wx/searchBook"/>?key=' + bookName;
            }else{
                alert('关键字不能为空哦');
            }
        }
    </script>
  </body>
</html>