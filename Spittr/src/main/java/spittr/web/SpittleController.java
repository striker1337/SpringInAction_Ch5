package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.Spittle;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {


	private static final String MAX_LONG_AS_STRING = "9223372036854775807";
	
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

	@RequestMapping(params={"max","count"},method=RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value="max",
				defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value="count", defaultValue="20") int count){
		return spittleRepository.findSpittles(max, count);
	}
	

	
	
	// Use dynamic placeholders to extract the ID out of the HTTP Path for the mapping /spittles/12345 via GET
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittle( @PathVariable("spittleId") long spittleId, Model model){
		//Pass the spittle to the model, such that it can be accessed when constructing the view
		model.addAttribute(spittleRepository.findOne(spittleId));
		// return the name of the view that should be loaded
		return "spittle";
	}
	
	
	
}



