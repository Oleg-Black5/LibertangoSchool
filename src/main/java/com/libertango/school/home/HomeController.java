package com.libertango.school.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.libertango.school.shared.domain.ToolbarDomain;
import com.libertango.school.shared.domain.ToolbarBuilder;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(Model model) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Home);
		return "home/home";
	}
}
