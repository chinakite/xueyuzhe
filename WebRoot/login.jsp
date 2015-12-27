<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../include.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
    <title>学语者控制台 | 登录</title>
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
    <div class="container">
      <div class="row">
        <div class="span6 offset2">
          <div class="login">
            <form id="loginForm" class="form-horizontal" action='<idp:url value="/console/userlogin"/>' method="POST"/>
              <div class="control-group">
                <div class="controls">
                  <h4>管理员登录</h4>
                </div>
              </div>
              <div class="control-group">
                <label for="inputAccount" class="control-label">用户名 </label>
                <div class="controls">
                  <input id="inputAccount" type="text" />
                </div>
              </div>
              <div class="control-group">
                <label for="inputPassword" class="control-label">密码 </label>
                <div class="controls">
                  <input id="inputPassword" type="password" />
                </div>
              </div>
              <div class="control-group"> 
                <div class="controls"><a href="javascript:void(0);" onclick="login();" class="btn">登录</a></div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    
    <script type="text/javascript">
        function login() {
            var account = $('#inputAccount').val();
            var password = $('#inputPassword').val();
            
            $.post(
                '<idp:url value="/console/userlogin"/>',
                {
                    "account": account,
                    "password": password
                },
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.code == 200) {
                        window.location.href = '<idp:url value="/console/dashboard"/>';
                    }else{
                        alert('error');
                    }
                }
            )
        }
    </script>
  </body>
</html>