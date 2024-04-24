package com.alex.grpc.server.service;

import com.alex.grpc.core.generated.PostRequest;
import com.alex.grpc.core.generated.PostResponse;
import com.alex.grpc.core.generated.PostServiceGrpc;
import com.alex.grpc.server.data.DataHandler;
import com.alex.grpc.server.model.Author;
import com.alex.grpc.server.model.Post;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PostGrpcServerService extends PostServiceGrpc.PostServiceImplBase {

	private final DataHandler dataHandler;

	public PostGrpcServerService() {
		this.dataHandler = new DataHandler();
	}

	@Override
	public void getPost(PostRequest postRequest, StreamObserver<PostResponse> streamObserver) {
		Post post = dataHandler.getPostById(postRequest.getId());
		Author author = dataHandler.getAuthorById(post.authorId());

		PostResponse postResponse = PostResponse.newBuilder()
				.setId(post.id())
				.setTitle(post.title())
				.setContent(post.content())
				.setReleaseYear(post.releaseYear())
				.setAuthor(com.alex.grpc.core.generated.Author.newBuilder()
						.setId(author.id())
						.setName(author.name())
						.setEmail(author.email()))
			.build();
		streamObserver.onNext(postResponse);
		streamObserver.onCompleted();
	}
}
