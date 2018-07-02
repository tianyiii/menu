package abc.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

    @GetMapping
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @PostMapping(path="/{merchantId}/upload", consumes="application/json")
    public ResponseEntity<?> uploadMenu(@PathVariable String merchantId, @RequestBody String input, Model model) {
    	return new ResponseEntity<String>(HttpStatus.OK);
    }

}
