package com.product.service.controller;

import java.util.List;

import com.product.service.config.ApplicationProperties;
import com.product.service.events.Event;
import com.product.service.model.Product;
import com.product.service.service.ProductService;
import com.product.service.streaming.ProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author nhlamulo
 */
@Controller
public class AppController {

	@Autowired
	private ProductService service;

	public ProductProducer productProducer = new ProductProducer();

	@Autowired
	ApplicationProperties properties;

	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<Product> listProducts = service.listAll();
		model.addAttribute("listProducts", listProducts);
		
		return "index";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		
		return "new_product";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		service.save(product);
		productProducer.EventMessage("Product"+" "+"With"+" "+"ID:"+" "+product.getId()
						              +" "+(Event.events.CREATED).toString(), "event-topic", properties.getKafkaUrl());
		return "redirect:/";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
		ModelAndView mav = new ModelAndView("edit_product");
		Product product = service.get(id);
		mav.addObject("product", product);
		productProducer.EventMessage("Product"+" "+"With"+" "+"ID:"+" "+product.getId()
				+" "+(Event.events.MODIFIED).toString(), "event-topic", properties.getKafkaUrl());
		return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
		service.delete(id);
		productProducer.EventMessage((Event.events.DELETED).toString(), "event-topic", properties.getKafkaUrl());
		return "redirect:/";
	}
}
