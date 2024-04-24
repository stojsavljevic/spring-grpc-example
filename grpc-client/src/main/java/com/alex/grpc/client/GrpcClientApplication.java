package com.alex.grpc.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alex.grpc.client.service.PostGrpcClientService;

@SpringBootApplication
public class GrpcClientApplication implements CommandLineRunner {

	private final PostGrpcClientService postGrpcClientService;

	public GrpcClientApplication(PostGrpcClientService postGrpcClientService) {
		this.postGrpcClientService = postGrpcClientService;
	}

	public static void main(String[] args) {
		SpringApplication.run(GrpcClientApplication.class, args);
	}

	@Override
	public void run(String... args) {
		postGrpcClientService.getPost(1);
	}
}
