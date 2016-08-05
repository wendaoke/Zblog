<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" uri="/WEB-INF/tld/function.tld" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE Html>
<html>
 <head>
  <jsp:include page="../common/bootstrap.jsp" flush="false" />
  <link rel="stylesheet" href="${g.domain}/resource/css/chosen.min.css">
  <link rel="stylesheet" href="${g.domain}/resource/css/dropzone.css">  
  <link rel="stylesheet" href="${g.domain}/resource/css/jquery.fileupload.css">   
  <script type="text/javascript" src="${g.domain}/resource/js/distpicker.data.min.js"></script>
  <script type="text/javascript" src="${g.domain}/resource/js/distpicker.min.js"></script>
  <script type="text/javascript" src="${g.domain}/resource/js/chosen.jquery.min.js"></script>  
  <script type="text/javascript" src="${g.domain}/resource/js/vendor/jquery.ui.widget.js"></script>  
  <script type="text/javascript" src="${g.domain}/resource/js/jquery.iframe-transport.js"></script>
  <script type="text/javascript" src="${g.domain}/resource/js/jquery.fileupload.js"></script>          
  <script type="text/javascript" src="${g.domain}/resource/ueditor-1.4.3/ueditor.config.js"></script>
  <script type="text/javascript" src="${g.domain}/resource/ueditor-1.4.3/ueditor.all.js"></script>
 </head>
 <body style="margin-top: 50px;">
  <jsp:include page="../common/navbar.jsp" flush="false" />
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2" id="sidebar" style="padding: 0;">
        <jsp:include page="../common/sidebar.jsp" flush="false" />
      </div>
      <div class="col-sm-9 col-md-10">
        <ol class="breadcrumb header">
          <li><span class="icon glyphicon glyphicon-home"></span>主菜单</li>
          <li>二手交易</li>
          <li class="active">发布物品</li>
        </ol>
       <div class="row">
         <div class="col-sm-9 col-md-9">
          <div class="panel panel-default">
            <div class="panel-heading"><span class="icon glyphicon glyphicon-edit"></span>标题/内容</div>
            <div class="panel-body">
              <input type="hidden" id="postid" value="${post.id}" />
              <input type="text" id="title" class="form-control input-md" placeholder="输入标题" value="${post.title}"><br/>
                <div class="tab-pane" id="editor-ue">
                  <!-- 必须要添加width:100% -->
                  <script id="ueditor" style="width: 100%; height: 350px;" type="text/plain">${post.content}</script>
                </div>
            </div>
            <div class="panel-footer text-success">&nbsp;</div>
           </div>
           <div  class="panel panel-default">
			    <span class="btn btn-success fileinput-button" style="margin-bottom:20px">
                    <i class="glyphicon glyphicon-plus"></i>
                    <span>添加图片</span>
                    <input type="file" id="fileupload" name="files[]" data-url="${g.domain}/backend/secondhand/upload" multiple>
                </span>
			    <div id="progress">
			        <div style="width: 0%;"></div>
			    </div>
			    <table role="presentation" class="table table-striped" id="uploaded-files">
			    	<tbody class="files">
			    		<tr class="template-download fade in">
        					<td>
        						<span class="preview">                
                    				<a href="https://jquery-file-upload.appspot.com/image%2Fjpeg/2901432121/4f707624bad44.jpg" title="4f707624bad44.jpg" download="4f707624bad44.jpg" data-gallery=""><img src="https://jquery-file-upload.appspot.com/image%2Fjpeg/2901432121/4f707624bad44.jpg.80x80.jpg"></a>
               					</span>
        					</td>
        					<td>
            					<p class="name">
                    			<a href="https://jquery-file-upload.appspot.com/image%2Fjpeg/2901432121/4f707624bad44.jpg" title="4f707624bad44.jpg" download="4f707624bad44.jpg" data-gallery="">4f707624bad44.jpg</a>
					            </p>
        					</td>
        					<td>
            					<span class="size">86.58 KB</span>
        					</td>
        					<td>
                				<button class="btn btn-danger delete" data-type="DELETE" data-url="https://jquery-file-upload.appspot.com/image%2Fjpeg/2901432121/4f707624bad44.jpg">
                    			<i class="glyphicon glyphicon-trash"></i>
                    			<span>Delete</span>
                				</button>
        					</td>
    					</tr>
    				</tbody>
    			</table>
			</div>
         </div>
         <div class="col-sm-3 .col-md-3">
           <div class="panel panel-default">
             <div class="panel-heading">发布</div>
             <div class="panel-body">
               <div class="form-group" >
                 <label for="tags">原价</label>
                 <input type="text" class="form-control" id="tags" value="${z:join(post.tags,',')}" />
               </div>
               <div class="form-group" >
                 <label for="tags">现价</label>
                 <input type="text" class="form-control" id="tags" value="${z:join(post.tags,',')}" />
               </div>               
               <div class="form-group">
                 <label for="pstatus">地区</label><br/>
					<div data-toggle="distpicker">
					  <select class="form-control"  data-province="---- 选择省 ----"></select>
					  <select class="form-control"  data-city="---- 选择市 ----"></select>
					  <select class="form-control"  data-district="---- 选择区 ----"></select>
					</div>
               </div>
               <div class="form-group" >
	                 <label for="categoty">分类</label>
		                 <select data-placeholder="---- 选择分类 ----" class="chosen-select form-control" multiple id="category" >
		                   <c:forEach items="${categorys}" var="category" begin="0">
		                     <option value="${category.id}" ${post.category.id==category.id?'selected':''}>
		                        ├─<c:if test="${category.level==3}">└─</c:if>${category.name}
		                      </option>
		                   </c:forEach>
		                    <option value=""></option>
				            <option value="1" >United States</option>
				            <option value="2" >United Kingdom</option>
				            <option value="3">Afghanistan</option>
		                 </select>
               </div>

             </div>
             <div class="panel-footer">
               <button type="button" class="btn btn-info btn-block" onclick="zblog.post.insert();">发布</button>
             </div>
           </div>
         </div>
       </div>

      </div>
    </div>
  </div>
  <script type="text/javascript" src="${g.domain}/resource/js/backend/admin.secondhand.js"></script>
  <script type="text/javascript" >
  
  // 多选 select 数据初始化
  function chose_mult_set_ini(select, values) {
      var arr = values.split(',');
      var length = arr.length;
      var value = '';
      for (i = 0; i < length; i++) {
          value = arr[i];
          $(select + " option[value='" + value + "']").attr('selected', 'selected');
      }
      $(select).trigger("liszt:updated");
  }

  
  $(function () {
	  'use strict';
    $('#distpicker').distpicker({
    	  province: "---- 所在省 ----",
    	  city: "---- 所在市 ----",
    	  district: "---- 所在区 ----"
      });
    
    // 如果要初始化已选中的项，需要在调用chosen()函数之前调用chose_mult_set_ini()函数
    // 设置<select>的<option>属性selected='selected'，这样chosen()函数被调用时，相应项会显示在框中
    chose_mult_set_ini('.chosen-select', '1,3');
    $(".chosen-select").chosen({display_selected_options:true});
    $('.chosen-select').on('change', function(evt, params) {
    	console.log(evt);
    	console.log(params);
      });

  });
  </script>
 </body>
</html>
