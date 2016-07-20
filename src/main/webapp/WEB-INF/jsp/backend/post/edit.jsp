<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="z" uri="/WEB-INF/tld/function.tld" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE Html>
<html>
 <head>
  <jsp:include page="../common/bootstrap.jsp" flush="false" />
  <script type="text/javascript" src="${g.domain}/resource/ueditor-1.4.3/ueditor.config.js"></script>
  <script type="text/javascript" src="${g.domain}/resource/ueditor-1.4.3/ueditor.all.js"></script>
  <script type="text/javascript" src="${g.domain}/resource/epiceditor-0.2.3/js/epiceditor.min.js"></script>
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
          <li>文章</li>
          <li class="active">编辑文章</li>
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
            <div class="panel-footer text-success">注:此三种编辑模式相互独立,最终以当前选中标签页内容提交</div>
           </div>
         </div>
         <div class="col-sm-3 .col-md-3">
           <div class="panel panel-default">
             <div class="panel-heading">发布</div>
             <div class="panel-body">
               <div class="form-group" >
	                <shiro:hasAnyRoles name="admin">
	                 <label for="categoty">分类</label>
		                 <select class="form-control" id="category">
		                   <c:forEach items="${categorys}" var="category" begin="0">
		                     <option value="${category.id}" ${post.category.id==category.id?'selected':''}>
		                        ├─<c:if test="${category.level==3}">└─</c:if>${category.name}
		                      </option>
		                   </c:forEach>
		                 </select>
	               </shiro:hasAnyRoles>
	               <shiro:hasAnyRoles name="editor">
	               	<!-- 网友分享 -->
	               	<input id="category" value="ZDmZB31MDM9v3znJCO9" type="hidden"/>
	               </shiro:hasAnyRoles>
               </div>
               <div class="form-group">
                 <label for="pstatus">公开度</label><br/>
                 <label class="radio-inline">
                    <input type="radio" name="pstatus" value="publish" ${post.pstatus=='publish'?'checked':''}>公开
                 </label>
                 <label class="radio-inline">
                    <input type="radio" name="pstatus" value="secret" ${post.pstatus=='secret'?'checked':''}>隐藏
                 </label>
               </div>
               <shiro:hasAnyRoles name="admin">
               <div class="form-group" >
                 <label for="tags">标签</label>
                 <input type="text" class="form-control" id="tags" value="${z:join(post.tags,',')}" />
                 <span class="help-block">多个标签请用英文逗号（,）分开</span>
               </div>
               </shiro:hasAnyRoles>
               <div class="form-group">
                 <label for="cstatus">是否允许评论</label><br>
                 <label class="radio-inline">
                    <input type="radio" name="cstatus" value="open" ${post.cstatus=='open'?'checked':''}>是
                 </label>
                 <label class="radio-inline">
                    <input type="radio" name="cstatus" value="close" ${post.cstatus=='close'?'checked':''}>否
                 </label>
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
  <script type="text/javascript" src="${g.domain}/resource/js/backend/admin.post.js"></script>
 </body>
</html>
