<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../../include.jsp"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />
    <meta name="description" content="description of your site" />
    <meta name="author" content="author of the site" />
    <title>学语者控制台 | 书籍章节</title>
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
              <li><a href='<idp:url value="/console/bp"/>' class="active"><i class="batch plane"></i><br />书籍</a></li>
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
            <div class="span12"><a href="#newSectionModal" data-toggle="modal" class="btn pull-right">新建章节</a>
              <h4 class="header">${bookName}  书籍章节</h4>
              <table id="secTbl" class="table table-striped sortable">
                <thead>
                  <tr>
                    <th>名称</th>
                    <th>描述</th>
                    <th>音频文件</th>
                    <th>状态</th>
                  </tr>
                </thead>
                <tbody>
                  
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div id="newSectionModal" class="modal hide fade">
        <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close">&times;</button>
            <h3 id="dialogTitle">新建章节</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal">
                <input type="hidden" id="id" name="id"/>
                <input type="hidden" id="bookId" name="bookId" value="${bookId}"/>
                <input type="hidden" id="audioUrl" name="audioUrl"/>
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
                    <label for="logoUrl" class="control-label">音频 </label>
                    <div class="controls">
                        <input type="file" id="importFile" name="importFile" multiple>
                    </div>
                    <div class="controls">
                        <div id="uploadedFile"></div>
                    </div>
                </div>
            </form>
        </div>
        <div class="modal-footer"><a href="javascript:void(0);" data-dismiss="modal" class="btn" onclick="clearNewCategoryForm();">取消</a><a href="javascript:void(0);" data-dismiss="modal" class="btn btn-primary" onclick="saveSection();">保存</a></div>
        </div>
      </div>
    </div>
    <div id="playcon" style="position: fixed; bottom: 50px; left: 50%; margin-left:-150px; display: none;"><audio id="aplayer" controls>您的浏览器不支持 audio 标签。</audio></div>
    
    <script src="<idp:url value="/js/json2.js"/>"></script>
    <script src="<idp:url value="/js/template.js"/>"></script>
    
    <script id="secTblBodyTmpl" type="text/html">
        {{each seclist as sec idx}}
            <tr>
                <td>{{sec.name}}</td>
                <td>{{sec.desc}}</td>
                <td>{{sec.audioes[0].audioUrl}}</td>
                <td>
                    <span class="label {{if sec.status == 1}}label-success{{else if sec.status == 2}}label-important{{/if}}">{{sec.statusStr}}</span>
                </td>
                <td>
                  <div class="btn-group">
                    <button class="btn" onclick="playaudio('{{sec.audioes[0].audioUrl}}')">试听</button>
                    <button data-toggle="dropdown" class="btn dropdown-toggle"><span class="caret"></span></button>
                    <ul class="dropdown-menu">
                      <li><a href="#newSectionModal" data-toggle="modal" onclick="findSection('{{sec.id}}');">编辑</a>{{if sec.status == 1}}<a href="javascript:void(0);" onclick="disSection('{{sec.id}}', '{{sec.name}}');">下架</a>{{else}}<a href="javascript:void(0);" onclick="enSection('{{sec.id}}', '{{sec.name}}');">上架</a>{{/if}}<a href="javascript:void(0);" onclick="delSection('{{sec.id}}', '{{sec.name}}');">删除</a></li>
                    </ul>
                  </div>
                </td>
            </tr>
        {{/each}}
    </script>
    
    <script type="text/javascript">
        $(document).ready(function(){
            $('#importFile').fileupload({
                url: '<idp:url value="/console/uploadAudio"/>',
                dataType: 'json',
                done: function (e, data) {
                         var fileUrl = data['result']['data'][0]['fileUrl'];
                         $('#uploadedFile').html(fileUrl);
                         $('#audioUrl').val(fileUrl);
                     }
            });
            
            loadSections();
        });
        
        function loadSections() {
            $.get(
                '<idp:url value="/console/book"/>/' + '${bookId}' + '/sec',
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    
                    if(result.type == 'success') {
                        var secs = result.data;
                        var html = template('secTblBodyTmpl', {"seclist" : secs});
                        $('#secTbl tbody').empty().append(html);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function clearNewSectionForm() {
            $('#uploadedFile').hide();
            $('#name').val('')
            $('#desc').val('');
            $('#id').val('');
            $('#audioUrl').val('');
        }
        
        function saveSection() {
            var id = $('#id').val();
            var name = $('#name').val();
            var desc = $('#desc').val();
            var fileUrl = $('#audioUrl').val();
            var bookId = $('#bookId').val();
            
            if(id && id != '') {
                $.post(
                    '<idp:url value="/console/editBook"/>',
                    {
                        "id":id,
                        "name":name,
                        "desc":desc,
                        "logoUrl":fileUrl
                    },
                    function(data) {
                        clearNewSectionForm();
                    }
                );
            }else{
                $.post(
                    '<idp:url value="/console/book/"/>' + '${bookId}' + '/addsec',
                    {
                        "name":name,
                        "desc":desc,
                        "audioUrl":fileUrl,
                        "bookId": bookId
                    },
                    function(data) {
                        alert(data);
                        clearNewSectionForm();
                        loadSections();
                    }
                );
            }
        }
        
        function playaudio(audioUrl) {
            $('#playcon').show();
            $('#aplayer').attr('src', '<idp:ctx/>'+ audioUrl);
            document.getElementById('aplayer').play();
        }
        
        function findSection(id) {
            $('#dialogTitle').text('编辑章节');
            $.get(
                '<idp:url value="/console/section/"/>' + id,
                {},
                function(data) {
                    var result = $.parseJSON(data);
                    if(result.type == 'success') {
                        var section = result.data;
                        $('#id').val(section.id);
                        $('#name').val(section.name);
                        $('#desc').val(section.desc);
                        $('#audioUrl').val(section.audioes[0].audioUrl);
                    }else{
                        alert(result.code + ":" + result.message);                    
                    }
                }
            );
        }
        
        function delSection(id, name) {
            var r = confirm('您确定要删除章节【' + name + '】吗？');
            if(r) {
                $.post(
                    '<idp:url value="/console/delSection"/>',
                    {"id": id},
                    function(data) {
                        alert(data);
                        loadSections();
                    }
                );
            }
        }
        
        function disSection(id, name) {
            var r = confirm('您确定要下架章节【' + name + '】吗？');
            if(r) {
                $.post(
                    '<idp:url value="/console/disSection"/>',
                    {"id": id},
                    function(data) {
                        alert(data);
                        loadSections();
                    }
                );
            }
        }
        
        function enSection(id, name) {
            var r = confirm('您确定要上架章节【' + name + '】吗？');
            if(r) {
                $.post(
                    '<idp:url value="/console/enSection"/>',
                    {"id": id},
                    function(data) {
                        alert(data);
                        loadSections();
                    }
                );
            }
        }
    </script>
  </body>
</html>  