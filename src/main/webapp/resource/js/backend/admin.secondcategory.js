zblog.register("zblog.secondcategory");


zblog.secondcategory.insert=function(){
 var newCategory=$("#newCategory").val();
 if(!newCategory) return ;
  
 $.ajax({
   type:"POST",
   url:zblog.getDomainLink("secondhandcategory/insert"),
   dataType:"json",
   data:{name:newCategory},
   success:function(msg){
	   if(msg&&msg.success){
	     window.location.reload();
	     zdialog.hide('insert-box');
	   }else{
	      alert(msg.msg); 
		 }
	 }
  });
}

zblog.secondcategory.remove=function(){
  
  if ($('input[type=checkbox][name=id]:checked').length > 1) {
      alert("只能选择一个记录");
      return ;
  }
  var str = "";
      $('input[type=checkbox][name=id]:checked').each(function() {
          str += $(this).val();
      });
  $.ajax({
   type:"DELETE",
   url:zblog.getDomainLink("secondhandcategory/remove/"+str),
   dataType:"json",
   success:function(msg){
	 if(msg&&msg.success){
	   window.location.reload();
	 }else{
	   alert("删除失败"); 
	  }
	}
  });
}