<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../include.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
    <title>学语者控制台 | 首页</title>
    <link rel="stylesheet" href='<idp:url value="/css/bootstrap.css"/>' />
    <link rel="stylesheet" href='<idp:url value="/css/bootstrap-responsive.css"/>' />
    <link rel="stylesheet" href='<idp:url value="/css/styles.css"/>' />
    <link rel="stylesheet" href='<idp:url value="/css/toastr.css"/>' />
    <link rel="stylesheet" href='<idp:url value="/css/fullcalendar.css"/>' />
    <link rel="stylesheet" href='<idp:url value="/css/base.css"/>' />
    <script src='<idp:url value="/js/jquery-1.8.3.min.js"/>'></script>
    <script src='<idp:url value="/js/bootstrap.js"/>'></script>
    <script src='<idp:url value="/js/jquery.knob.js"/>'></script>
    <script src='<idp:url value="/js/d3.v3.min.js"/>'></script>
    <script src='<idp:url value="/js/jquery.sparkline.min.js"/>'></script>
    <script src='<idp:url value="/js/toastr.js"/>'></script>
    <script src='<idp:url value="/js/jquery.tablesorter.min.js"/>'></script>
    <script src='<idp:url value="/js/jquery.peity.min.js"/>'></script>
    <script src='<idp:url value="/js/fullcalendar.min.js"/>'></script>
    <script src='<idp:url value="/js/gcal.js"/>'></script>
    <script src='<idp:url value="/js/setup.js"/>'></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
  <body>
    <div id="in-nav">
      <div class="container">
        <div class="row">
          <div class="span12">
              <h4><strong>学语者</strong>控制台</h4></a>
          </div>
        </div>
      </div>
    </div>
    <div id="in-sub-nav">
      <div class="container">
        <div class="row">
          <div class="span12">
            <ul>
              <li><a href='<idp:url value="/console/dashboard"/>' class="active"><i class="batch home"></i><br />首页</a></li>
              <li><a href='<idp:url value="/console/cgp"/>'><i class="batch stream"></i><br />类别</a></li>
              <li><a href='<idp:url value="/console/bp"/>'><i class="batch plane"></i><br />书籍</a></li>
              <li><a href='<idp:url value="/console/up"/>'><i class="batch users"></i><br />用户</a></li>
              <li><a href="settings.html"><i class="batch settings"></i><br />系统设置</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>  