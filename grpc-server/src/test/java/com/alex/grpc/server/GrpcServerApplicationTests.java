package com.alex.grpc.server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.alex.grpc.core.generated.PostRequest;
import com.alex.grpc.core.generated.PostResponse;
import com.alex.grpc.core.generated.PostServiceGrpc.PostServiceBlockingStub;

import net.devh.boot.grpc.client.inject.GrpcClient;

@SpringBootTest(properties = {
		"grpc.server.port=-1",
		"grpc.server.in-process-name=test",
        "grpc.client.in-process.address=in-process:test"})
@SpringJUnitConfig(classes = GrpcServerApplicationTestConfiguration.class)
@DirtiesContext
class GrpcServerApplicationTests {

	@GrpcClient("in-process")
	private PostServiceBlockingStub postServiceBlockingStub;

	@Test
	@DirtiesContext
	public void test_get_post() {
		PostRequest postRequest = PostRequest.newBuilder().setId(1).build();
		PostResponse postResponse = postServiceBlockingStub.getPost(postRequest);

		assertNotNull(postResponse);
		assertEquals("Pet Sematary", postResponse.getTitle());

		assertNotNull(postResponse.getAuthor());
		assertEquals("Stephen King", postResponse.getAuthor().getName());
	}
}
