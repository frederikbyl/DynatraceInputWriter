package com.probit.tool.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * A Rest controller that takes input from Dynatrace and writes out a file on
 * disk
 */
@RestController
public class DynatraceInputWriterController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DynatraceInputWriterController.class);

	@Value("${probit.filename}")
	private String filename;

	@Value("${probit.separator}")
	private String separator;

	@RequestMapping(method = RequestMethod.POST, value = "${probit.dynatrace.endpoint}")
	public void createSession(@Valid @RequestBody String inputString) throws IOException {
		LOGGER.info("{} - POST REQUEST from dynatrace", Thread.currentThread().getId());
		LOGGER.debug(inputString);
		StringBuffer sb = new StringBuffer(inputString).append(System.lineSeparator()).append(separator)
				.append(System.lineSeparator());
		Path path = Paths.get(filename);
		Files.write(path, sb.toString().getBytes(), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
		LOGGER.info("{} - Request processed and written into the file", Thread.currentThread().getId());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dynatrace/*", produces = "application/json")
	public void getSession(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.debug("GET REQUEST");
	}

}
