package com.kyobo.stats.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kyobo.stats.web.dto.MRDParameter;

@Controller
@RequestMapping
public class ReportController extends ApplicationController{
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(Locale locale, @ModelAttribute MRDParameter mrdParameter, Model model) {
		return Forward.report_view;
	}
}
