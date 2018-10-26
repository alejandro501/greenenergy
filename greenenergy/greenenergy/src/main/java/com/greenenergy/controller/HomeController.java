package com.greenenergy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greenenergy.repository.StoryRepository;
import com.greenenergy.service.StoryService;

/* 
 * Controller: Rest controller methods look for a VIEW with the String that it returns eg. 
 * return "terms" wants to return the view "terms".
 */
@Controller
public class HomeController {

	private StoryService storyService;

	@Autowired
	public void setStoryService(StoryService storyService) {
		this.storyService = storyService;
	}

	@Autowired
	StoryRepository storyRepository;

	@RequestMapping("/")
	public String stories(Model model) {
		model.addAttribute("pageTitle", "Greenenergy title");
		model.addAttribute("stories", storyService.getStories());
		return "stories";
	}
	
//	@RequestMapping("/story")
//	public String story(Model model) {
//		model.addAttribute("pageTitle", "Greenenergy title");
//		model.addAttribute("story", storyService.getStory());
//		return "story";
//	}
//
//	@RequestMapping("/title/{title}")
//	public String searchForUser(@PathVariable(value="title") String title, Model model) throws Exception {
//		if (title == null) {
//			throw new Exception("No story with this title.");
//		}
//		model.addAttribute("story", storyService.getSpecificStory(title));
//		return "story";
//	}

	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest rA, Exception ex, Model model) {
		model.addAttribute("errMessage", ex.getMessage());
		return "exceptionHandler";
}

}
