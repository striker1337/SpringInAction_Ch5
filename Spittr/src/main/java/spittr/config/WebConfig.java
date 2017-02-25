package spittr.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
@EnableWebMvc
//Enable Component Scanning for the packet Spitter.web
@ComponentScan("spittr.web")
public class WebConfig extends WebMvcConfigurerAdapter{

	
	//Configure web.xml to resolve JSP-Views and in which Folder
	@Bean
	public ViewResolver viewResolver(){
		// ViewResolver wraps view names with a specific prefix and suffix
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		// Path where to find the corresponding views
		resolver.setPrefix("/WEB-INF/views/");
		// Suffix of the corresponding views
		resolver.setSuffix(".jsp");
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	// Configure static content handling
	@Override
	public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer){
		// This asks the DispatchServlet to forward requests for static resources 
		// to the servlet containers default servlet, and not handle them itself
		configurer.enable();
	}
	
	
	
}
