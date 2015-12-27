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

    <link rel="stylesheet" href='<idp:url value="/css/base.css?v=123"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/wx_book.css"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/font-awesome.css"/>'>
    <script src='<idp:url value="/js/jquery-1.8.3.min.js"/>'></script>
    <script src='<idp:url value="/js/audio/audio.min.js"/>'></script>
  </head>
  <body>
    <!-- Make sure all your bars are the first things in your <body> -->
    <header class="bar bar-nav header-border header-theme">
      <a class="icon fa fa-angle-left pull-left text-white f16" href='<idp:url value="/wx/category/"/>${categoryId}/bp'>&nbsp;返回</a>
      <h1 class="title text-white">${categoryName}</h1>
    </header>
    <div class="content">
        <ul class="table-view no-border-top leave-header mb0">
          <li class="table-view-cell media table-cell-view-book">
            <a class="item">
              <img class="media-object pull-left book-logo" src='<idp:url value="/img/banner/b1.png"/>'>
              <div class="media-body f14">
                <span class="fwb">${bookName}</span>
                <p class="mt5 f12">${bookDesc}</p>
                <p class="mt5 f12"><i class="fa fa-list-ul mr5"></i>章节：25</p>
              </div>
            </a>
          </li>
        </ul>
        <div class="toolbar">
            <a class="fa fa-navicon pull-left btn-black-text f12">&nbsp;章节</a>
            <a class="fa fa-share-square-o pull-right btn-black-text f12">&nbsp;分享</a>
            <a class="fa fa-download pull-right btn-black-text f12" style="margin-right: 1em;">&nbsp;批量下载</a>
        </div>
        <ul id="secList" class="table-view no-border-top">
          <li class="table-view-cell table-cell-view-section light-border f20"><span class="f14">第一章</span><i class="fa fa-play-circle-o pull-right text-green"></i><i class="fa fa-download pull-right text-green"  style="margin-right: 1em;"></i></li>
          <li class="table-view-cell table-cell-view-section light-border f20"><span class="f14">第二章</span><i class="fa fa-play-circle-o pull-right text-green"></i><i class="fa fa-download pull-right text-green"  style="margin-right: 1em;"></i></li>
          <li class="table-view-cell table-cell-view-section light-border f20"><span class="f14">第三章</span><i class="fa fa-play-circle-o pull-right text-green"></i><i class="fa fa-download pull-right text-green"  style="margin-right: 1em;"></i></li>
          <li class="table-view-cell table-cell-view-section light-border f20"><span class="f14">第四章</span><i class="fa fa-play-circle-o pull-right text-green"></i><i class="fa fa-download pull-right text-green"  style="margin-right: 1em;"></i></li>
        </ul>
        
        <audio id="aplayer" preload="false" controls style="display: none;"></audio>
        
    </div>
    <script src='<idp:url value="/js/json2.js"/>'></script>
    <script src='<idp:url value="/js/template.js"/>'></script>
    
    <script id="secListTmpl" type="text/html">
        {{each seclist as sec idx}}
            <li class="table-view-cell table-cell-view-section light-border f20"><span class="f14">{{sec.name}}</span><i class="fa fa-play-circle-o pull-right text-green" onclick="playaudio('{{sec.audioes[0].audioUrl}}')"></i><i class="fa fa-download pull-right text-green" onclick="download('{{sec.id}}');" style="margin-right: 1em;"></i></li>
        {{/each}}
    </script>
    
    <script>
        $(document).ready(function(){
            loadSections();
            
            //audiojs.events.ready(function() {
            //    var as = audiojs.createAll();
            //});
        });
        
        function loadSections() {
            $.get(
                '<idp:url value="/console/book/"/>${bookId}/sec',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var secs = result.data;
                        var html = template('secListTmpl', {"seclist" : secs});
                        $('#secList').append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function download(secid) {
            window.location.href = '<idp:url value="/wx/downloadAudio"/>?id=' + secid;
        }
        
        function playaudio(audioUrl) {
            document.getElementById('aplayer').pause();
            $('#aplayer').show();
            $('#aplayer').attr('src', '<idp:ctx/>'+ audioUrl);
            document.getElementById('aplayer').play();
        }
     </script>
  </body>
</html>