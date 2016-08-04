<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="page" uri="/WEB-INF/tld/pagination.tld" %>
<%@ taglib prefix="z" uri="/WEB-INF/tld/function.tld" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE Html>
<html>
 <head>
  <jsp:include page="../common/bootstrap.jsp" flush="false" />
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
          <li class="active">二手交易</li>
        </ol>
        <div class="panel panel-default">
          <div class="panel-heading"><span class="icon glyphicon glyphicon-list"></span>发布历史</div>
          <div class="panel-body">
           <table id="post-table" class="table table-striped list-table">
               <thead><tr>
                 <th style="width: 35%;">标题</th>
                 <th>作者</th>
                 <th>日期</th>
                 <th class="center">操作</th>
               </tr></thead>
              <tbody>
               <c:forEach items="${page.content}" var="post">
                 <tr id="edit-${post.id}">
                    <td>
	                     <strong><a class="post-title" target="_blank" href="../../posts/${post.id}">${post.title}</a>
	                     </strong>
                     </td><td class="post-author">${post.user.nickName}</td>
                     <td class="post-ctime"><fmt:formatDate value="${post.createTime}" pattern="yyyy-MM-dd" /></td>
                     <td class="center"><span class="icon glyphicon glyphicon-pencil pointer" onclick="zblog.post.edit('${post.id}')"></span>
                       <span class="glyphicon glyphicon-trash pointer" onclick="zblog.post.remove('${post.id}')"></span></td>
                  </tr>
               </c:forEach>
              </tbody>
            </table>
            <div class="row-fulid">
              <div class="col-sm-6 col-md-6">
                <div class="page-info">第 ${page.pageIndex}页, 共 ${page.totalPage}页, ${page.totalCount} 项</div>
              </div>
              <div class="col-sm-6 col-md-6">
                <div id="pager">
                 <jsp:include page="../common/pagination.jsp" flush="false" />
                </div>
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
