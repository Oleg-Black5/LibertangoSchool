package com.libertango.school.classroom;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libertango.school.classroom.domains.ClassType;
import com.libertango.school.classroom.services.ClassTypeService;
import com.libertango.school.shared.domain.DomainIterator;
import com.libertango.school.shared.domain.ToolbarBuilder;
import com.libertango.school.shared.domain.ToolbarDomain;
import com.libertango.school.shared.web.ExceptionController;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping(value="/classtype")
@AllArgsConstructor
public class ClassTypeList {
	
	private @NotNull final ClassTypeService classTypeService;

	@GetMapping(value="/list")
	public String getClassTypeList(Model model) {
		FillModel(model, classTypeService.getAll());
		return "classroom/classtype-list";
	}
	
	@GetMapping(value="/{id}")
	public String getClassTypeById(@PathVariable Integer id, Model model) {
		ClassType classType = this.classTypeService.getClassTypeById(id);
		FillModel(model, classType);
		return "classroom/classtype-list";
	}
	
	@ExceptionController(
			viewName="redirect:/classtype/list",
			messages="409;No se puede eliminar porque existen configuraciones de clases asignadas"
	)
	@GetMapping(value="/delete/{id}")
	public String delete(@PathVariable Integer id, RedirectAttributes redirectAttr) {
		classTypeService.delete(id);
		return "redirect:/classtype/list";
	}

	private static void FillModel(Model model, ClassType classTypes) {
		FillModel(model, Arrays.asList(classTypes));
	}
	
	private static void FillModel(Model model, List<ClassType> classTypes) {
		ToolbarBuilder.Fill(model, ToolbarDomain.EnumMenuOption.Classroom);
		ClassroomMenuBuilder.Fill(model, ClassRoomMenuDomain.EnumMenuOption.ClassType);
		model.addAttribute("classtypesRowsCols", DomainIterator.Create(classTypes, 3));
	}
}
