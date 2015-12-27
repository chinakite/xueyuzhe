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
    <link rel="stylesheet" href='<idp:url value="/css/wx_profile.css"/>'>
    <link rel="stylesheet" href='<idp:url value="/css/font-awesome.css"/>'>
  
    <script src='<idp:url value="/js/jquery-1.8.3.min.js"/>'></script>
  </head>
  <body style="background: #fff4e3;">
    <!-- Make sure all your bars are the first things in your <body> -->
    <header class="bar bar-nav header-border header-theme">
      <a class="icon fa fa-angle-left pull-left text-white f16" href='<idp:url value="/wx/index"/>'>&nbsp;返回</a>
      <a class="icon pull-right text-white f16" onclick="updateUser();">保存</a>
      <h1 class="title text-white">个人中心</h1>
    </header>
    
    <div class="p10 leave-header f12 text-orange">初次使用下载服务，请您完善个人信息，以便我们为您提供更好的服务。</div>
    
    <form class="input-group">
      <div class="input-row form-item">
        <label>姓名</label>
        <input id="name" type="text" class="text-right" placeholder="请填写">
      </div>
      <div class="input-row form-item">
        <label>邮箱</label>
        <input id="email" type="email" class="text-right" placeholder="请填写">
      </div>
      <div class="input-row form-item">
        <label>手机</label>
        <input id="mobile" type="text" class="text-right" placeholder="请填写">
      </div>
    </form>
    
    <form class="input-group mt20">
      <div class="input-row form-item">
        <label>年龄</label>
        <select id="age">
            <option value="-1">请选择</option>
            <option value="1">3 ~ 6岁</option>
            <option value="2">6 ~ 12岁</option>
            <option value="3">12 ~ 18岁</option>
            <option value="4">18 ~ 24岁</option>
            <option value="5">24+岁</option>
        </select>
      </div>
      <div class="input-row form-item">
        <label>学习意愿</label>
        <select id="category"><option value="-1">请选择</option></select>
      </div>
    </form>
    
    <a class="downlog-btn"></a>
    
    <script src="<idp:url value="/js/json2.js"/>"></script>
    <script src="<idp:url value="/js/template.js"/>"></script>
    
    <script id="cgListTmpl" type="text/html">
        {{each cglist as cg idx}}
            <option value="{{cg.id}}">{{cg.name}}</option>
        {{/each}}
    </script>
    
    <script>
        $(document).ready(function(){
            loadUserInfo();
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
                        $('#category').append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function loadUserInfo() {
            $.get(
                '<idp:url value="/wx/user"/>',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var user = result.data;
                        
                        $('#name').val(user.name);
                        $('#email').val(user.email);
                        $('#mobile').val(user.mobilephone);
                        
                        var ageRange = user.ageRange;
                        var ageEle = document.getElementById('age');
                        if(ageRange) {
                            if(ageRange == '1') {
                                ageEle.selectedIndex = 1;
                            }else if(ageRange == '2') {
                                ageEle.selectedIndex = 2;
                            }else if(ageRange == '3') {
                                ageEle.selectedIndex = 3;
                            }else if(ageRange == '4') {
                                ageEle.selectedIndex = 4;
                            }else if(ageRange == '5') {
                                ageEle.selectedIndex = 5;
                            }
                        }
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function updateUser() {
            $.post(
                '<idp:url value="/wx/updateUser"/>',
                {
                    "name": $('#name').val(),
                    "email": $('#email').val(),
                    "mobile": $('#mobile').val(),
                    "age": $('#age').val(),
                    "category": $('#category').val()
                },
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        alert('保存成功!');
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
     </script>
  </body>
</html>