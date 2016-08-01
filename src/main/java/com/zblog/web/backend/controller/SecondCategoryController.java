package com.zblog.web.backend.controller;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zblog.biz.SecondCategoryManager;
import com.zblog.core.dal.entity.SecondCategory;
import com.zblog.core.plugin.MapContainer;
import com.zblog.core.util.CollectionUtils;
import com.zblog.core.util.IdGenerator;
import com.zblog.service.SecondCategoryService;
import com.zblog.web.backend.form.validator.SecondCategoryFormValidator;
import com.zblog.web.support.WebContextFactory;

@Controller("adminSecondCategoryController")
@RequestMapping("/backend/secondhandcategory")
@RequiresRoles(value = { "admin", "editor" }, logical = Logical.OR)
public class SecondCategoryController{
  @Autowired
  private SecondCategoryService service;
  @Autowired
  private SecondCategoryManager categoryManager;


  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String data(Model model){
    List<SecondCategory> list = service.list();
    model.addAttribute("list", list);
    return "backend/secondhand/category";
  }

  @ResponseBody
  @RequestMapping(value = "/insert",method = RequestMethod.POST)
  public Object insert(SecondCategory category, String parent){
    MapContainer form = SecondCategoryFormValidator.validateInsert(category);
    if(!form.isEmpty()){
      return form.put("success", false);
    }

    category.setId(IdGenerator.uuid19());
    category.setCreateTime(new Date());
    category.setCreator(WebContextFactory.get().getUser().getId());
    category.setLastUpdate(category.getCreateTime());
    return new MapContainer("success", service.insert(category));
  }

  @ResponseBody
  @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
  public Object remove(@PathVariable String id){
    return new MapContainer("success",  service.deleteById(id));
  }

}
