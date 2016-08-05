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
                      .append($('<td/>').text(file.fileSize))
                      .append($('<td/>').text(file.fileType))
                      .append($('<td/>').html("<a href='/backend/secondhand/get/"+index+"'>Click</a>"))
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

  var postid=$("#postid").val();
  var data={title : title,
        content : _getText(),
        tags : $("#tags").val(),
        categoryid : $("#category").val(),
        pstatus : $("input:radio[name=pstatus]:checked").val(),
        cstatus : $("input:radio[name=cstatus]:checked").val()
      };
  if(postid.length>0) data.id=postid;
  
  $.ajax({
    type:postid.length>0?"PUT":"POST",
    url:zblog.getDomainLink("posts"),
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

zblog.secondhand.remove=function(postid){
 $.ajax({
   type:"DELETE",
   url:zblog.getDomainLink("posts/"+postid),
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




zblog.secondhand.edit=function(postid){
  window.location.href=zblog.getDomainLink("posts/edit?pid="+postid);
}