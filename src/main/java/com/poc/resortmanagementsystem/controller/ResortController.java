package com.poc.resortmanagementsystem.controller;

import com.poc.resortmanagementsystem.entity.ControllerInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.poc.resortmanagementsystem.entity.Resort;
import com.poc.resortmanagementsystem.repository.ResortRepository;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ResortController {

	@Autowired
	private ResourceLoader resourceLoader;

	private ResortRepository resortRepository;
	@Autowired
	public ResortController(ResortRepository resortRepository) {

		this.resortRepository = resortRepository;
	}
	/*@GetMapping({ "/resort", "/list" })
	public ModelAndView getAllResorts() {
		ModelAndView mav = new ModelAndView("list-resorts");
		mav.addObject("resorts", resortRepository.findAll());
		return mav;
	}*/

	@GetMapping({ "/resort", "/list" })
	public ModelAndView getAllResorts(@RequestParam(name="selectedState", required = false) String selectedState, @RequestParam(name="selectedCity",required=false)String selectedCity) throws IOException {
		ModelAndView mav = new ModelAndView("list-resorts");
		/*mav.addObject("resorts", resortRepository.findAll());*/

		List<Resort> allResorts = resortRepository.findAll();
		List<Resort> filteredResorts = allResorts;

		if(selectedState!=null && !selectedState.isEmpty()){
			filteredResorts=allResorts.stream()
					.filter(resort ->resort.getState().equals((selectedState)))
					.collect(Collectors.toList());
		}
		if(selectedCity!=null && !selectedCity.isEmpty()){
			filteredResorts=filteredResorts.stream()
					.filter(resort -> resort.getCity().equals(selectedCity))
					.collect(Collectors.toList());

		}

		ClassPathResource resource = new ClassPathResource("state_city.json");
		String jsonContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
		JSONObject stateCityJson = new JSONObject(jsonContent);
		mav.addObject("resorts",filteredResorts);
		mav.addObject("stateCityJson",stateCityJson);
		mav.addObject("selectedState",selectedState);


		if(selectedState != null && stateCityJson.has(selectedState)){
			JSONArray citiesForSelectedState= stateCityJson.getJSONArray(selectedState);
			//System.out.println("citiesForSelectedState:"+citiesForSelectedState );
			mav.addObject("citiesForSelectedState", citiesForSelectedState);
			/*mav.addObject("selectedState", selectedState);*/


		}
		mav.addObject("stateCityJson",stateCityJson);
		return mav;
	}

	@GetMapping("/addResortForm")
	public ModelAndView addResortForm(@RequestParam(name = "selectedState", required = false) String selectedState) throws IOException {
		ModelAndView mav = new ModelAndView("add-resort-form");
		//System.out.println("state data fetched" + selectedState);
		Resort newResort = new Resort();
		mav.addObject("resort", newResort);

			ClassPathResource resource = new ClassPathResource("state_city.json");
			String jsonContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
			JSONObject stateCityJson = new JSONObject(jsonContent);


			if(selectedState != null && stateCityJson.has(selectedState)){
				JSONArray citiesForSelectedState= stateCityJson.getJSONArray(selectedState);
				//System.out.println("citiesForSelectedState:"+citiesForSelectedState );
				mav.addObject("citiesForSelectedState", citiesForSelectedState);
				mav.addObject("selectedState", selectedState);


			}
				mav.addObject("stateCityJson",stateCityJson);
				return mav;
	}
	@PostMapping("/saveResort")
	public String saveResort(@ModelAttribute("resort") Resort resort) {

		// Check if a resort with the same name already exists
		if (resortRepository.existsByName(resort.getName())) {
			throw new IllegalArgumentException("A resort with the same name already exists.");
		}
		else{
			resortRepository.save(resort);

			return "redirect:/resort";
		}

	}

	@GetMapping("/updateResortForm/{id}")
	public ModelAndView updateResortForm(@PathVariable Long id){
		ModelAndView mav = new ModelAndView("update-resort-form");
		Resort existingResort = resortRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid resort ID: " + id));
		mav.addObject("resort", existingResort);
		return mav;
	}

	@PostMapping("/updateResort/{id}")
	public String updateResort(@PathVariable Long id, @ModelAttribute Resort updatedResort) {
		Resort existingResort = resortRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid resort ID: " + id));

		// Check if the updated name conflicts with an existing resort's name
		if (!existingResort.getName().equals(updatedResort.getName()) &&
				resortRepository.existsByName(updatedResort.getName())) {
			throw new IllegalArgumentException("A resort with the updated name already exists.");
		}

		// Update the resort's properties
		existingResort.setName(updatedResort.getName());
		existingResort.setAddress(updatedResort.getAddress());

		resortRepository.save(existingResort);
		return "redirect:/resort";
	}


	@GetMapping("/deleteResort/{id}")
	public String deleteResort(@PathVariable Long id) {
		Resort resortToDelete = resortRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid resort ID: " + id));

		resortRepository.delete(resortToDelete);
		return "redirect:/resort";
	}


	@ModelAttribute("controllerInfo")
	public ControllerInfo getControllerInfo() {
		ControllerInfo info = new ControllerInfo();
		info.setControllerName(getClass().getSimpleName());

		RequestMapping controllerMapping = getClass().getAnnotation(RequestMapping.class);
		if (controllerMapping != null) {
			info.setRequestMappingUrls(Arrays.asList(controllerMapping.value()));
		}

		List<String> methodNames = new ArrayList<>();
		for (Method method : getClass().getDeclaredMethods()) {
			if (method.isAnnotationPresent(GetMapping.class) || method.isAnnotationPresent(PostMapping.class)) {
				methodNames.add(method.getName());
			}
		}
		info.setMethodNames(methodNames);

		return info;
	}

}
