package spittr.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private SpittleRepository spittleRepository;
	
	// Inject the spittleRepository automatically
	@Autowired
	public SpittleController(SpittleRepository spittleRepository){
		this.spittleRepository = spittleRepository;
	}

	// Upon GET access to the /spittles resource of the class, access the model and add the attributes
	// for the 20 spittles from the Repository
	@RequestMapping(method=RequestMethod.GET)
	public String spittles(Model model){
		//Specify the key and the value that will be added to the model mapping
		model.addAttribute("spittleList",spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		// return the name of the view for the JSP file
		return "spittles";
	}
	
	
}



