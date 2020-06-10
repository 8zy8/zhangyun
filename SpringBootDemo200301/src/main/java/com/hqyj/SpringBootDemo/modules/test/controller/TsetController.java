package com.hqyj.SpringBootDemo.modules.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.SpringBootDemo.config.ResourceConfigBean;
import com.hqyj.SpringBootDemo.modules.test.entity.City;
import com.hqyj.SpringBootDemo.modules.test.entity.Country;
import com.hqyj.SpringBootDemo.modules.test.service.CityService;
import com.hqyj.SpringBootDemo.modules.test.service.CountryService;
import com.hqyj.SpringBootDemo.modules.test.vo.ApplicationTest;

@Controller
@RequestMapping("/test")
public class TsetController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(TsetController.class);

	@Value("${server.port}")
	private int port;
	
	@Value("${com.thornbird.name}")
	private String name;
	
	@Value("${com.thornbird.age}")
	private int age;
	
	@Value("${com.thornbird.desc}")
	private String desc;
	
	@Value("${com.thornbird.random}")
	private String random;
	
	@Autowired
	private ApplicationTest applicationTest;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private ResourceConfigBean resourceConfigBean;
	
	/**
	 * 127.0.0.1/test/index
	 */
	@RequestMapping("/index")
	public String indexPage(ModelMap modelmap) {
		int countryId = 522;
		List<City> cities = cityService.getCitiesByCountryId2(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		Country country = countryService.getCountryByCountryId(countryId);
		
		modelmap.addAttribute("thymeleafTitle", "scdscsadcsacd");
		modelmap.addAttribute("checked", false);
		modelmap.addAttribute("currentNumber", 99);
		modelmap.addAttribute("changeType", "button");
		modelmap.addAttribute("baiduUrl", "/test/log");
		modelmap.addAttribute("city", cities.get(0));
//		modelmap.addAttribute("shopLogo", 
//				"http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelmap.addAttribute("country", country);
		modelmap.addAttribute("cities", cities);
		modelmap.addAttribute("updateCityUri", "/api/city");
//		modelmap.addAttribute("template", "test/index");
		
		return "index";
	}
	
	
	/**
	 * 127.0.0.1/test/log
	 */
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		// TRACE<DEBUG<INFO<WARN<ERROR
		
		LOGGER.trace("This is trace log");
		LOGGER.debug("This is DEBUG log");
		LOGGER.info("This is INFO log");
		LOGGER.warn("This is WARN log");
		LOGGER.error("This is ERROR log");
		
		return "This is log test.";
	}
	
	@RequestMapping("/config")
	@ResponseBody
	public String config() {
		StringBuffer sb =new StringBuffer();
		sb.append(port).append("-------")
			.append(name).append("-------")
			.append(age).append("-------")
			.append(desc).append("-------")
			.append(random).append("<br>");
		
		sb.append(applicationTest.getName1()).append("----")
			.append(applicationTest.getAge1()).append("----")
			.append(applicationTest.getDesc1()).append("----")
			.append(applicationTest.getRandom1()).append("<br>");
		
		
		return sb.toString();
	}
	
	@RequestMapping("/desc1")
	@ResponseBody
	public String run(){
		return "this is a some page.";
	}
	
	
	
	/**
	 * 127.0.0.1/test/desc?key=fuck
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc(HttpServletRequest request, @RequestParam String key) {
		String key2 = request.getParameter("key");
		return "This is test module desc.112233" + key + "==" + key2;
	}
	
	
	@PostMapping(value="/file",consumes="multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file,RedirectAttributes redirectAttributes){
		if(file.isEmpty()){
			redirectAttributes.addFlashAttribute("message","please select file");
			return "redirect:/test/index";
		}
		String resourcePath = resourceConfigBean.getResourcePath() + file.getOriginalFilename();
//		System.out.println("****************************************"+resourcePath+"******************************");
		try {
			String resourceLocalPath = "";
			String systemName = System.getProperty("os.name");			//获取windows、Linux
			if (systemName.toLowerCase().startsWith("win")) {
				resourceLocalPath = resourceConfigBean.getLocalPathForWindow() + file.getOriginalFilename();
				System.out.println("****************************************"+resourceLocalPath+"******************************");
			} else  {
				resourceLocalPath = resourceConfigBean.getLocalPathForLinux() + file.getOriginalFilename();
			}
			File destFile = new File(resourceLocalPath);
//			System.out.println("****************************************"+destFile+"******************************");
			file.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message","upload fail");
			return "redirect:/test/index";
		}
		redirectAttributes.addFlashAttribute("message","upload success");
		redirectAttributes.addFlashAttribute("resourcePath",resourcePath);
		return "redirect:/test/index";
	}
	
	
	
	@PostMapping(value="/files",consumes="multipart/form-data")
	public String uploadFiles(@RequestParam MultipartFile[] files,RedirectAttributes redirectAttributes){
		boolean isEmpty = true;

		List<String> resourcePath = new ArrayList<>();
		
		for (int i=0;i<files.length;i++) {
			MultipartFile file = files[i];
			if(file.isEmpty()){
				continue;
			}
			
			resourcePath.add(resourceConfigBean.getResourcePath() + file.getOriginalFilename());
			
			try {
				String destFilePath = resourceConfigBean.getLocalPathForWindow() + file.getOriginalFilename();
				File destFile = new File(destFilePath);
				file.transferTo(destFile);
				
				
				isEmpty=false;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message","upload fail");
				break;
			}
		}
		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "Please select file");
		} else  {
			redirectAttributes.addFlashAttribute("resourcePath0",resourcePath.get(0));
			redirectAttributes.addFlashAttribute("resourcePath1",resourcePath.get(1));
			redirectAttributes.addFlashAttribute("resourcePath2",resourcePath.get(2));
			redirectAttributes.addFlashAttribute("message", "Upload success.");
		}
		return "redirect:/test/index";
	}
	
	
	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<Resource> download(@RequestParam String fileName) {
		try {
			String resourcePath = resourceConfigBean.getResourcePath() + fileName;
//			Resource resource = new UrlResource(Paths.get("D:\\upload\\" + fileName).toUri());
			Resource resource = new UrlResource(ResourceUtils.getURL(resourcePath));
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
					.body(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request,HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "D:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);
		
		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, 
					String.format("attachment; filename=\"%s\"", fileName));
			
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		
		String filePath = resourceConfigBean.getLocalPathForWindow() + fileName;
		String filePath1 = "G:/upload" + File.separator + fileName;
		LOGGER.debug("------------------------------------"+filePath+"-------------------------"+filePath1+"--------------");
		File downloadFile = new File(filePath);
		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int)downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName));
				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}
	
}











