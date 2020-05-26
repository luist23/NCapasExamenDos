package com.uca.capas.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.ContribuyenteDAO;
import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.dao.ImportanciaDAO;
import com.uca.capas.domain.Categoria;
import com.uca.capas.domain.Contribuyente;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.domain.Importancia;
import com.uca.capas.domain.Libro;
import com.uca.capas.service.CategoriaService;
import com.uca.capas.service.ContribuyenteService;
import com.uca.capas.service.ImportanciaService;
import com.uca.capas.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	ContribuyenteService contribuyenteService;
	
	@Autowired
	ImportanciaService importanciaService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	LibroService libroService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/ingresarCategoria")
	public ModelAndView ingresarCAtegoria() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("categoria", new Categoria());
		mav.setViewName("ingresarCategoria");
		return mav;
	}
	
	@PostMapping("/newCategoria")
	public ModelAndView newCategoria(@Valid @ModelAttribute Categoria categoria, BindingResult result) {
		
		ModelAndView mav = new ModelAndView(); 
		if(!result.hasErrors()) {
			try {
				categoriaService.insert(categoria);
				mav.setViewName("exito");
				
			}catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("index");
			}
			
			categoria = new Categoria();
			mav.addObject("categoria", categoria);
			
		}
		return mav;
	}
	
	@RequestMapping("/igresarLibro")
	public ModelAndView ingresarLibro() {
		ModelAndView mav = new ModelAndView();
		List<Categoria> categorias = null;
		try {
			categorias = categoriaService.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		mav.addObject("ListCategoria", categorias);
		mav.addObject("libro", new Libro());
		mav.setViewName("ingresarLibro");
		return mav;
	}
	
	@PostMapping("/newLibro")
	public ModelAndView newLibro(@Valid @ModelAttribute Libro libro, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
	    Date date = new Date();  
		
		if(!result.hasErrors()) {
			try {
				libro.setFecha(date);
				libroService.insert(libro);
				mav.setViewName("exito");
				
			}catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("index");
			}
			
			libro = new Libro();
			mav.addObject("libro", libro);
			
		}		
		return mav;
	}
	
	@RequestMapping("/deleteLibro")
	public String deleteLibro(@RequestParam Integer codigo) {
		Libro libro = libroService.findOne(codigo);
			try {
				
				libroService.delete(libro);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		return "redirect:/listado";
		
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		ModelAndView mav = new ModelAndView();
		
		List<Libro> libros = null;
		try {
			
			libros = libroService.findAll();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mav.addObject("libros", libros);
		mav.setViewName("listado");
		
		return mav;
	}
	
	@RequestMapping("/inicio")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		List<Importancia> importancias = null;
		try {
			importancias = importanciaService.findAll();
		}catch (Exception e){
			e.printStackTrace();
		}
		mav.addObject("ListImportancia", importancias);
		mav.addObject("contribuyente", new Contribuyente());
		mav.setViewName("index");
		return mav;
	}
	
	@PostMapping("/saveContribuyente")
	public ModelAndView formProducto(@Valid @ModelAttribute Contribuyente contribuyente, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
	    Date date = new Date();  
		
		if(!result.hasErrors()) {
			try {
				contribuyente.setFecha(date);
				System.out.print(contribuyente);
				contribuyenteService.insert(contribuyente);
				mav.setViewName("exito");
				
			}catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("index");
			}
			
			contribuyente = new Contribuyente();
			mav.addObject("contribuyente", contribuyente);
			
		}
		
		
		
		return mav;
		
	}
	
	/*@RequestMapping("/formEstudiante")
	public ModelAndView formProducto(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		if(!result.hasErrors()) {
			try {
				
				estudianteDAO.insert(estudiante);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			estudiante = new Estudiante();
			mav.addObject("estudiante", estudiante);
			
		}
		
		mav.setViewName("index");
		
		return mav;
		
	}*/
	
	@RequestMapping("/deleteContribuyente")
	public String delete(@RequestParam Integer codigo) {
		Contribuyente estudiante = contribuyenteService.findOne(codigo);
			try {
				
				contribuyenteService.delete(estudiante);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		return "redirect:/listado";
		
	}

}