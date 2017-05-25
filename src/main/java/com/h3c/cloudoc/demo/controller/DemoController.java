package com.h3c.cloudoc.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "第一个Spring Boot应用",tags = {"Hello World Controller"},description = "描述信息")
@RestController
@RequestMapping("cloudoc/demo")
public class DemoController {

	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

	@ApiOperation(value = "你好世界", notes = "Hello World!")
	@RequestMapping(value = "hello", method = RequestMethod.GET)
	@ResponseBody
	String hello () {
		logger.info("here i come!");
		return "Hello World";
	}
}
