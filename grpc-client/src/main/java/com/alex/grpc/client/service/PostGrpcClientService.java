package com.alex.grpc.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alex.grpc.core.generated.PostRequest;
import com.alex.grpc.core.generated.PostResponse;
import com.alex.grpc.core.generated.PostServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class PostGrpcClientService {

	private static final Logger logger = LoggerFactory.getLogger(PostGrpcClientService.class);

	@GrpcClient("grpc-post-client")
	private PostServiceGrpc.PostServiceBlockingStub postServiceBlockingStub;

	public void getPost(int id) {
		PostRequest postRequest = PostRequest.newBuilder().setId(id).build();
		PostResponse postResponse = this.postServiceBlockingStub.getPost(postRequest);

		Assert.isTrue("Pet Sematary".equals(postResponse.getTitle()), "Response is not as expected!");
		logger.info(String.format("Received response from the server: %s", postResponse.getTitle()));
		logger.info("Call ended successfully");
	}
}
