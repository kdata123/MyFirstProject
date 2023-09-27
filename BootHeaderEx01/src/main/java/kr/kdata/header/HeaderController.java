package kr.kdata.header;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class HeaderController {

	@GetMapping("/")
	public String home(@RequestHeader HttpHeaders headers, Model model) {
		MultiValueMap<String, String> headersMap = headers;
		StringBuffer sb = new StringBuffer();
		for(String key : headersMap.keySet()) {
			sb.append("키 : " + key + "<br>");
			sb.append("값 : " + headersMap.get(key) + "<br>");
			sb.append("<hr>");
		}
		model.addAttribute("header", sb.toString());
		return "index";
	}
	@GetMapping("/index2")
	public String home() {
		return "index2";
	}

}
