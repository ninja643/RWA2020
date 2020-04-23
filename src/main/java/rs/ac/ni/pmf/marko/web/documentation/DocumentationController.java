package rs.ac.ni.pmf.marko.web.documentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DocumentationController {
	@RequestMapping("/api")
	public String redirectToDocumentation() {
//		return "redirect:/services/rest/swagger-ui.html";
		return "redirect:/api.html";
	}
}
