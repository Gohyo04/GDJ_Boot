package com.winter.app.ajax;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@Slf4j
public class RestTemplateTest {

	
	public void flux() throws Exception{
		WebClient webClient = 
				WebClient.builder()
					.baseUrl("https://jsonplaceholder.typicode.com/")
					.build();
		
		int num = 1;
//		toEntity		
//		Mono<ResponseEntity<RestVO>> response = webClient.get()
//													.uri("posts/1")
//													.retrieve()
//													.toEntity(RestVO.class);
//		
//		RestVO restVO = response.block().getBody();
		
//		bodyToMono	
//		Mono<RestVO> response = webClient.get()
//				.uri("posts/1")
//				.retrieve()
//				.bodyToMono(RestVO.class);
//
//		RestVO restVO = response.block();
		
		Flux<RestVO> response = webClient.get()
		.uri("posts")
		.retrieve()
		.bodyToFlux(RestVO.class);

		response.subscribe((r) -> {
			RestVO restVO = r;
//			log.info("Flux sub ==> {}", restVO);
		});
		
//		log.info("Flux RestVO ==> {}",restVO);
	}
	

	public void rest() throws Exception{
		log.info("Rest Template");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null,null);
		
		ResponseEntity<List> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1", List.class);
		
		List<RestVO> result = response.getBody();
		log.info("restVO ===> {}",result);
	}

}
