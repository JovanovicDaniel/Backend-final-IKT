package com.schoollog.controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/api/schoollog/download")
public class FileController {
	@Autowired
	private Environment env;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, value="/log")
	public ResponseEntity<?> downloadLog() {
	    String filePath = env.getProperty("logging.file.name");
		Path path = Paths.get(filePath);
		Resource resource = null;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
}
