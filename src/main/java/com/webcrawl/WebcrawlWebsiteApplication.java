package com.webcrawl;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.webcrawl.dao.SavedArticlesRepository;
import com.webcrawl.dao.UserRepository;
import com.webcrawl.model.SavedArticle;

@RestController
@SpringBootApplication
public class WebcrawlWebsiteApplication {

	@Autowired
	UserRepository userArticles;
	@Autowired
	SavedArticlesRepository savedArticles;
	
	
	public static void main(String[] args) {
		SpringApplication.run(WebcrawlWebsiteApplication.class, args);
	}
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		mav.addObject("usatoday", userArticles.findTop10ByWebsite("USA Today"));
		mav.addObject("nytimes", userArticles.findTop10ByWebsite("NY Times"));
		mav.addObject("foxnews", userArticles.findTop10ByWebsite("Fox News"));
		return mav;
	}
	
	@GetMapping("/user")
	public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal){
		return Collections.singletonMap("name", principal.getAttribute("name"));
	}
	
	@RequestMapping("/profile")
	public ModelAndView profile(@AuthenticationPrincipal OAuth2User principal) {
		ModelAndView mav = new ModelAndView("profile");
		mav.addObject("userAttributes", principal.getAttributes());
		mav.addObject("myArticles", savedArticles.findByEmail(principal.getAttribute("email")));
		return mav;
	}
	
	@RequestMapping("/save")
	public RedirectView save(@RequestParam("articleTitle") String title, @RequestParam("articleUrl") String url,
			@AuthenticationPrincipal OAuth2User principal) {
		SavedArticle s = new SavedArticle();
		s.setAuthor("n/a");
		s.setDateposted("n/a");
		s.setEmail(principal.getAttribute("email"));
		s.setTitle(title);
		s.setUrl(url);
		s.setWebsite("n/a");
		savedArticles.save(s);
		return new RedirectView("/");
	}
	
	@RequestMapping("/delete")
	public RedirectView delete(@RequestParam("articleid") Integer id) {
		savedArticles.deleteById(id);
		return new RedirectView("/profile");
	}
	
	/*@RequestMapping("/saveSamePage")
	@ResponseBody
	public RedirectView save(@RequestParam("articleTitle") String title, @RequestParam("articleUrl") String url,
			@AuthenticationPrincipal OAuth2User principal) {
		SavedArticle s = new SavedArticle();
		s.setAuthor("n/a");
		s.setDateposted("n/a");
		s.setEmail(principal.getAttribute("email"));
		s.setTitle(title);
		s.setUrl(url);
		s.setWebsite("n/a");
		savedArticles.save(s);
		return "done";
	}
	*/
	
}
