zblog.register("zblog.secondhand");
$(function(){
  $('#editor-nav a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
    zblog.secondhand.editType="ue";
  });
  
  if(!document.getElementById("ueditor")) return ;
  
  zblog.secondhand.editType='ue';
  zblog.secondhand.ueditor = UE.getEditor('ueditor',{
	  toolbars: [
	             ['fullscreen', 'source', 'undo', 'redo','bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc']
	         ],
    /* 阻止div标签自动转换为p标签 */
	  allowDivTransToP: false,
	  autoHeightEnabled: true,
	  autoFloatEnabled: true
  });
  
  zblog.secondhand.ueditor.ready(function(){
    zblog.secondhand.ueditor.execCommand('serverparam',{'CSRFToken': zblog.newCsrf()});
  });
  
  $('#fileupload').fileupload({
      dataType: 'json',

      done: function (e, data) {
          $("tr:has(td)").remove();
          $.each(data.result, function (index, file) {
              $("#uploaded-files").append(
                      $('<tr/>')
                      .append($('<td/>').text(file.fileName))
                      .append($('<td/>').text(file.fileType))
                      .append($('<td/>').html("<a href='/backend/secondhand/delimg/"+index+"'>删除</a>"))
                      )//end $("#uploaded-files").append()
          }); 
      },

      progressall: function (e, data) {
          var progress = parseInt(data.loaded / data.total * 100, 10);
          $('#progress .bar').css(
              'width',
              progress + '%'
          );
      },

      dropZone: $('#dropzone')
  });
  
});

zblog.secondhand.insert=function(){
  var title = $.trim($("#title").val());
  if(title==""){
	  $("#title").focus();
	  return ;
  }
  
  var _getText=function(){
    var result = zblog.secondhand.ueditor.getContent();
    
    return result;
  }

  var secondhandid=$("#secondhandid").val();
  var data={
    	id:secondhandid,
		title : title,
        content : _getText(),
        originalPrice : $("#originalPrice").val(),
        presentPrice : $("#presentPrice").val(),
        province : $("#province").val(),
        city : $("#city").val(),
        district : $("#district").val(),        
        categorys : $("#category").val().join(",")
      };
  
  $.ajax({
    type:"POST",
    url:zblog.getDomainLink("secondhand/insert"),
    data:data,
    dataType:"json",
    success:function(data){
	    if(data&&data.success){
	      window.location.href=zblog.getDomainLink("secondhand/edit");
      }else{
    	 alert(data.msg);
      }
     }
  });
}

zblog.secondhand.remove=function(postid){
 $.ajax({
   type:"DELETE",
   url:zblog.getDomainLink("posts/"+postid),
   dataType:"json",
   success:function(data){
	   if(data&&data.success){
		 alert(data.msg);
	     window.location.reload();
     }else{
       alert(data.msg);
     }
   }
 });
}




zblog.secondhand.edit=function(postid){
  window.location.href=zblog.getDomainLink("posts/edit?pid="+postid);
}