package spittr.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import spittr.data.SpittleRepository;
import spittr.web.HomeController;
import spittr.Spittle;

public class SpittleControllerTest {

	@Test
	public void shoudlShowRecentSpittles() throws Exception {
		
		List<Spittle> expectedSpittles = createSpittleList(20);
		
		SpittleRepository mockRepository = 
				mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20))
			.thenReturn(expectedSpittles);
		SpittleController controller = 
				new SpittleController(mockRepository);
		
		
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(
						new InternalResourceView("/Web-INF/views/spittles.jsp"))
				.build();
		
		mockMvc.perform(get("/spittles"))
		.andExpect(view().name("spittles"))
		.andExpect(model().attributeExists("spittlesList"))
		.andExpect(model().attribute("spittleList",
				hasItems(expectedSpittles.toArray())));
		
	}
	
}

