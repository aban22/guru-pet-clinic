package guru.springframework.sfgpetclinic.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@RequestMapping("owners")
@Controller
public class OwnerController {

	private final OwnerService ownerService;
	
	public OwnerController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@RequestMapping("/find")
	public String findOwners(Model model) {
		model.addAttribute("owner", Owner.builder().build());
		return "owners/findOwners";
	}
	
	@GetMapping
	public String proccessFindForm(Owner owner, BindingResult bindingResult, Model model) {
		if(owner.getLastName() == null) {
			owner.setLastName("");
		}
		List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
		if(results.isEmpty()) {
			bindingResult.rejectValue("lastName", "notFound", "not found");
			return "owners/findOwners";
		} else if(results.size() == 1) {
			owner = results.get(0);
			return "redirect:/owners/" + owner.getId();
		} else {
			model.addAttribute("selections", results);
			return "owners/ownersList";
		}
	}
	
	@GetMapping("/{ownerId}")
	public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
		ModelAndView mav = new ModelAndView("owners/ownerDetails");
		mav.addObject(ownerService.findById(ownerId));
		return mav;
	}
	
	@GetMapping("/new")
	public String initCreationForm(Model model) {
		
		model.addAttribute("owner", Owner.builder().build());
		return "owners/createOrUpdateOwnerForm";
	}
	
	
	@PostMapping("/new")
	public String processCreationForm(@Valid Owner owner, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		} else {
			Owner ownerSaved = ownerService.save(owner);
			return "redirect:/owners/" + ownerSaved.getId();
		}
	}
	
	@GetMapping("{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable("ownerId") Long ownerId, Model model) {
		model.addAttribute("owner", ownerService.findById(ownerId));
		return "owners/createOrUpdateOwnerForm";
	}
	
	@PostMapping("{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Owner owner, BindingResult bindingResult, @PathVariable("ownerId") Long ownerId) {
		if(bindingResult.hasErrors()) {
			return "owners/createOrUpdateOwnerForm";
		} else {
			Owner ownerSaved = ownerService.save(owner);
			return "redirect:/owners/" + ownerSaved.getId();
		}
	}
}
