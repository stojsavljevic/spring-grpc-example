package com.alex.grpc.server.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.alex.grpc.server.model.Author;
import com.alex.grpc.server.model.Post;

public class DataHandler {

	ArrayList<Post> posts = new ArrayList<>(
			Arrays.asList(
				new Post(1, "Pet Sematary", "Television! Teacher, mother, secret lover.", 1983, 1),
				new Post(2, "Animal Farm", "Even communism works… in theory.", 1945, 2),
				new Post(3, "The Brothers Karamazov", "Don’t eat me. I have a wife and kids. Eat them.", 1880, 3),
				new Post(4, "The Trial", "Trying is the first step towards failure", 1925, 4),
				new Post(5, "Murder on the Orient Express", "To alcohol! The cause of, and solution to, all of life’s problems.", 1934, 5)));

		ArrayList<Author> authors = new ArrayList<>(
			Arrays.asList(
				new Author(1, "Stephen King", "king@gmail.com"),
				new Author(2, "George Orwell", "orwell@gmail.com"),
				new Author(3, "Fyodor Dostoevsky", "dostoevsky@gmail.com"), 
				new Author(4, "Franz Kafka", "kafka@gmail.com"),
				new Author(5, "Agatha Christie", "christie@gmail.com")));

	public List<Post> getAllPosts() {
		return this.posts;
	}

	public List<Author> getAllAuthors() {
		return this.authors;
	}

	public Author getAuthorById(int authorId) {
		Optional<Author> author = this.authors.stream().filter(a -> a.id() == authorId).findAny();
		return author.get();
	}

	public Post getPostById(int postId) {
		Optional<Post> post = this.posts.stream().filter(p -> p.id() == postId).findAny();
		return post.get();
	}
}
