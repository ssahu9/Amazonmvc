package com.project.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.bean.Product;
import com.project.bean.User;
import com.project.bl.CustomerBl;

@Controller

@SessionAttributes("proList")
public class GetProducts  {
	
	private static Logger logger=Logger.getLogger(GetProducts.class);
	@RequestMapping(value="/getProducts")
	
	public String getProducts( @RequestParam("category") String pCategory, ModelMap model)
	{	
		CustomerBl customerBl = new CustomerBl();
		ArrayList<Product> proList =null;
		
		try {
			
			proList = (ArrayList<Product>)customerBl.viewProduct(pCategory);
			model.addAttribute("currentCategory", proList);
			model.addAttribute("user", new User());	
		} catch (ClassNotFoundException | SQLException e) {
			model.addAttribute("errorMessage", "Invalid Entry, please retry again");
			return("error404page");
		}		
		return "women";
	}
	

}
