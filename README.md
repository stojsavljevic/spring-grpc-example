# Spring gRPC Example Project ![build status](https://github.com/stojsavljevic/spring-grpc-example/actions/workflows/maven.yml/badge.svg)

This project demonstrates the integration of gRPC (Google Remote Procedure Call) with the Spring ecosystem. gRPC is a high-performance, open-source RPC framework developed by Google that enables efficient communication between services.

## gRPC in General

gRPC is based on HTTP/2, offering features such as bidirectional streaming, flow control, header compression, and multiplexing. It uses Protocol Buffers (protobuf) as its interface definition language (IDL) to define services and message types. With gRPC, you can define methods and their parameters in a .proto file, and gRPC generates client and server code in various languages to handle communication.

## Example Project Structure

This example project consists of three modules:

### grpc-core

The `grpc-core` module contains the gRPC protobuf file (`*.proto`) that defines the service and message types. It utilizes the `os72/protoc-jar-maven-plugin` (or alternatively `xolstice/protobuf-maven-plugin`) Maven plugin to generate server and client code from the protobuf file.

### grpc-server

The `grpc-server` module implements the server-side logic generated from the `grpc-core` module. It exposes a simple single service that returns a `Post` object for a provided ID. This module includes integration tests that utilize the gRPC client to test the server's functionality. 

### grpc-client

The `grpc-client` module implements the client-side logic generated from the `grpc-core` module. It is a console application that makes calls to the gRPC server implemented in the `grpc-server` module. Upon completing the call to the server, the client application exits.

## Getting Started

To run the example project:

* Clone the repository:

```
git clone https://github.com/stojsavljevic/spring-grpc-example.git
```

* Navigate to the project directory:

```
cd spring-grpc-example
```

* Build the project:

```
mvn clean install
```

* Run the gRPC server, it will listen on port `8085`:

```
java -jar grpc-server/target/grpc-server-1.0.0.jar
```

* Run the gRPC client:

```
java -jar grpc-client/target/grpc-client-1.0.0.jar
```

* Confirm client-server communication and expected data retrieval - check the log for `Call ended successfully` message.

* Alternatevly, execute gRPC call from Postman to `localhost:8085` and `GetPost` service with following message:

```
{
    "id": 1
}
```

and expect following response:


```
{
    "id": 1,
    "title": "Pet Sematary",
    "content": "Television! Teacher, mother, secret lover.",
    "releaseYear": 1983,
    "author": {
        "id": 1,
        "name": "Stephen King",
        "email": "king@gmail.com"
    }
}

```
