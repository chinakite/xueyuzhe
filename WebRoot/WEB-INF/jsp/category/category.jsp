<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../include.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
    <title>学语者控制台 | 类别</title>
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
              <li><a href='<idp:url value="/console/cgp"/>' class="active"><i class="batch stream"></i><br />类别</a></li>
              <li><a href='<idp:url value="/console/bp"/>'><i class="batch plane"></i><br />书籍</a></li>
              <li><a href='<idp:url value="/console/up"/>'><i class="batch users"></i><br />用户</a></li>
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
            <div class="span12"><a href="#newCategoryModal" data-toggle="modal" class="btn pull-right">新建类别</a>
              <h4 class="header">类别</h4>
              <table id="categoryTbl" class="table table-striped sortable">
                <thead>
                  <tr>
                    <th>名称</th>
                    <th>描述</th>
                    <th>图片</th>
                    <th>状态</th>
                  </tr>
                </thead>
                <tbody>
                  
                </tbody>
              </table>
              <!--
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
              -->
            </div>
          </div>
        </div>
      </div>
      <div id="newCategoryModal" class="modal hide fade">
        <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h3 id="dialogTitle">新建类别</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal">
                <input type="hidden" id="id" name="id"/>
                <input type="hidden" id="logoUrl" name="logoUrl"/>
                <div class="control-group">
                    <label for="name" class="control-label">名称 </label>
                    <div class="controls">
                        <input id="name" type="text" placeholder="名称" />
                    </div>
                </div>
                <div class="control-group">
                    <label for="desc" class="control-label">描述 </label>
                    <div class="controls">
                        <textarea id="desc" type="text" placeholder="描述" ></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label for="logoUrl" class="control-label">图片 </label>
                    <div class="controls">
                        <input type="file" id="importFile" name="importFile" multiple>
                    </div>
                    <div class="controls">
                        <img id="uploadedFile" width="200" style="display: none;"/>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer"><a href="javascript:void(0);" data-dismiss="modal" class="btn" onclick="clearNewCategoryForm();">取消</a><a href="javascript:void(0);" data-dismiss="modal" class="btn btn-primary" onclick="saveCategory();">保存</a></div>
        </div>
      </div>
    </div>
    <script src="<idp:url value="/js/json2.js"/>"></script>
    <script src="<idp:url value="/js/template.js"/>"></script>
    
    <script id="cgTblBodyTmp" type="text/html">
        {{each cglist as cg idx}}
            <tr>
                <td>{{cg.name}}</td>
                <td>{{cg.desc}}</td>
                <td><img width="80" src="<idp:ctx/>{{cg.logoUrl}}"/></td>
                <td>
                    <span class="label {{if cg.status == 1}}label-success{{else if cg.status == 2}}label-important{{/if}}">{{cg.statusStr}}</span>
                </td>
                <td>
                  <div class="btn-group">
                    <button class="btn" onclick="manageBook('{{cg.id}}');">管理书籍</button>
                    <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li><a href="#newCategoryModal" data-toggle="modal" onclick="findCategory('{{cg.id}}');">编辑</a>{{if cg.status == 1}}<a href="javascript:void(0);" onclick="disCategory('{{cg.id}}', '{{cg.name}}');">下架</a>{{else}}<a href="javascript:void(0);" onclick="enCategory('{{cg.id}}', '{{cg.name}}');">上架</a>{{/if}}<a href="javascript:void(0);" onclick="delCategory('{{cg.id}}', '{{cg.name}}');">删除</a></li>
                    </ul>
                  </div>
                </td>
            </tr>
        {{/each}}
    </script>
    
    <script type="text/javascript">
        $(document).ready(function(){
            $('#importFile').fileupload({
                url: '<idp:url value="/console/uploadPic"/>',
                dataType: 'json',
                done: function (e, data) {
                         var fileUrl = data['result']['data'][0]['fileUrl'];
                         $('#uploadedFile').attr('src', fileUrl).show();
                         $('#logoUrl').val(fileUrl);
                     }
            });
            
            loadCategories();
        });
        
        function loadCategories() {
            $.get(
                '<idp:url value="/console/cg"/>',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var categories = result.data;
                        var html = template('cgTblBodyTmp', {"cglist" : categories});
                        $('#categoryTbl tbody').empty().append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function clearNewCategoryForm() {
            $('#uploadedFile').hide();
            $('#name').val('');
            $('#desc').val('');
            $('#id').val('');
        }
        
        function saveCategory() {
            var id = $('#id').val();
            var name = $('#name').val();
            var desc = $('#desc').val();
            var fileUrl = $('#logoUrl').val();
            
            if(id && id != '') {
                $.post(
                    '<idp:url value="/console/editCategory"/>',
                    {
                        "id":id,
                        "name":name,
                        "desc":desc,
                        "logoUrl":fileUrl
                    },
                    function(data) {
                        alert(data);
                        clearNewCategoryForm();
                        loadCategories();
                    }
                );
            }else{
                $.post(
                    '<idp:url value="/console/newCategory"/>',
                    {
                        "name":name,
                        "desc":desc,
                        "logoUrl":fileUrl
                    },
                    function(data) {
                        alert(data);
                        clearNewCategoryForm();
                        loadCategories();
                    }
                );
            }
        }
        
        function findCategory(id) {
            $('#dialogTitle').text('编辑类别');
            $.get(
                '<idp:url value="/console/category/"/>' + id,
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var category = result.data;
                        $('#id').val(category.id);
                        $('#name').val(category.name);
                        $('#desc').val(category.desc);
                        $('#logoUrl').val(category.logoUrl);
                        $('#uploadedFile').attr('src', '<idp:ctx/>'+category.logoUrl).show();
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function delCategory(id, name) {
            var r = confirm('您确定要删除类别【' + name + '】吗？');
            if(r) {
                $.post(
                    '<idp:url value="/console/delCategory"/>',
                    {"id": id},
                    function(data) {
                        alert(data);
                        loadCategories();
                    }
                );
            }
        }
        
        function disCategory(id, name) {
            var r = confirm('您确定要下架类别【' + name + '】吗？');
            if(r) {
                $.post(
                    '<idp:url value="/console/disCategory"/>',
                    {"id": id},
                    function(data) {
                        alert(data);
                        loadCategories();
                    }
                );
            }
        }
        
        function enCategory(id, name) {
            var r = confirm('您确定要上架类别【' + name + '】吗？');
            if(r) {
                $.post(
                    '<idp:url value="/console/enCategory"/>',
                    {"id": id},
                    function(data) {
                        alert(data);
                        loadCategories();
                    }
                );
            }
        }
        
        function manageBook(id) {
            window.location.href = '<idp:url value="/console/bp?categoryId="/>' + id;
        }
    </script>   
  </body>
</html>  