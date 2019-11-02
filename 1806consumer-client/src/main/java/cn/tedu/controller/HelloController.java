package cn.tedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
	@Autowired	//通过模板对象去远程发起对另外一个服务器请求，结构和httpclient相似
	private RestTemplate restTemplate;
	
	//调用服务提供者
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name){
		String url = "http://localhost:7900/hello/"+name;	//服务提供者访问地址
		//通过Eureka获取链接信息，而且实现负载均衡，默认：轮询
		url = "http://provider-user/hello/"+name;
		return restTemplate.getForObject(url, String.class);
	}
}
