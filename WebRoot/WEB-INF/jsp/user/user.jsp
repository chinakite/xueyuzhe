<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../include.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
    <title>学语者控制台 | 用户</title>
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
    
    <script src='<idp:url value="/js/fileupload/jquery.ui.widget.js"/>'></script>
    <script src='<idp:url value="/js/fileupload/jquery.fileupload.js"/>'></script>
    <script src='<idp:url value="/js/fileupload/jquery.iframe-transport.js"/>'></script>
    
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
              <li><a href='<idp:url value="/console/dashboard"/>'><i class="batch home"></i><br />首页</a></li>
              <li><a href='<idp:url value="/console/cgp"/>'><i class="batch stream"></i><br />类别</a></li>
              <li><a href='<idp:url value="/console/bp"/>'><i class="batch plane"></i><br />书籍</a></li>
              <li><a href='<idp:url value="/console/up"/>' class="active"><i class="batch users"></i><br />用户</a></li>
              <li><a href="settings.html"><i class="batch settings"></i><br />系统设置</a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="page">
      <div class="page-container">
        <div class="container">
          <div class="row">
            <div class="span12">
              <h4 class="header">用户</h4>
              <table id="userTbl" class="table table-striped sortable">
                <thead>
                  <tr>
                    <th>名称</th>
                    <th>微信OpenId</th>
                    <th>加入时间</th>
                    <th>头像</th>
                    <th>状态</th>
                  </tr>
                </thead>
                <tbody>
                  
                </tbody>
              </table>
              <div class="pagination pagination-centered">
                <ul>
                  <li class="disabled"><a href="#">&laquo;</a></li>
                  <li class="active"><a href="#">1</a></li>
                  <li><a href="#">2</a></li>
                  <li><a href="#">3</a></li>
                  <li><a href="#">4</a></li>
                  <li><a href="#">5</a></li>
                  <li><a href="#">&raquo;</a></li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script src="<idp:url value="/js/json2.js"/>"></script>
    <script src="<idp:url value="/js/template.js"/>"></script>
    
    <script id="userTblBodyTmpl" type="text/html">
        {{each userlist as user idx}}
            <tr>
                <td>{{user.name}}</td>
                <td>{{user.wxOpenId}}</td>
                <td>{{user.creator}}</td>
                <td><img width="50" src="<idp:ctx/>{{user.logoUrl}}"/></td>
                <td>
                    <span class="label {{if user.status == 1}}label-success{{else if user.status == 2}}label-important{{/if}}">{{user.statusStr}}</span>
                </td>
                <td>
                  <div class="btn-group">
                    <button class="btn">活动查看</button>
                    <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li><a href="#">编辑</a><a href="#">禁用</a></li>
                    </ul>
                  </div>
                </td>
            </tr>
        {{/each}}
    </script>
    
    <script type="text/javascript">
        $(document).ready(function(){
            loadUsers();
        });
        
        function loadUsers() {
            $.get(
                '<idp:url value="/console/users?curPage=1&pageSize=10"/>',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var users = result.data.data;
                        var html = template('userTblBodyTmpl', {"userlist" : users});
                        $('#userTbl tbody').empty().append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
    </script>   
  </body>
</html>  