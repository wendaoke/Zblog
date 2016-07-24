zblog.register("zblog.page");
$(function(){
  $('#editor-nav a').click(function (e) {
    e.preventDefault();//阻止a链接的跳转行为
    $(this).tab('show');//显示当前选中的链接及关联的content
    zblog.page.editType=$(this).attr("href").substring(8);
  });
  
  if(!document.getElementById("ueditor")) return ;
  
  zblog.page.editType='ue';
  zblog.page.ueditor = UE.getEditor('ueditor',{
    /* 阻止div标签自动转换为p标签 */
    allowDivTransToP: false,
	  autoHeightEnabled: true,
	  autoFloatEnabled: true
  });
  zblog.page.ueditor.ready(function(){
    zblog.page.ueditor.execCommand('serverparam',{'CSRFToken': zblog.newCsrf()});
  });
  
});

zblog.page.insert=function(){
  var title=$.trim($("#title").val());
  if(title==""){
	 $("#title").focus();
	 return ;
  }
  
  var _getText=function(){
    var result = zblog.page.ueditor.getContent();
    return result;
  };

  console.info(_getText());
  var postid=$("#postid").val();
  var data={title : title,
	     content : _getText(),
	     parent : $("#parent").val(),
	     cstatus : $("input:radio[name=cstatus]:checked").val()
	   };
  
  if(postid.length>0) data.id=postid;
  
  $.ajax({
    type:postid.length>0?"PUT":"POST",
    url:zblog.getDomainLink("pages"),
    data:data,
    dataType:"json",
    success:function(data){
	    if(data&&data.success){
	       window.location.href=".";
      }else{
    	  alert(data.msg);
      }
     }
  });
}

zblog.page.remove=function(postid){
 $.ajax({
   type:"DELETE",
   url:zblog.getDomainLink("pages/"+postid),
   dataType:"json",
   success:function(data){
	  if(data&&data.success){
	    window.location.reload();
    }else{
      alert(data.msg);
     }
   }
 });
}

zblog.page.edit=function(postid){
  window.location.href=zblog.getDomainLink("pages/edit?pid="+postid);
}