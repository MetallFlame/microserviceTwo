package com.akriskovets.microservice.service;

import com.akriskovets.grpc.UserServiceGRPCGrpc;
import com.akriskovets.grpc.UserServiceGRPCOuterClass;
import com.akriskovets.microservice.entity.User;
import com.akriskovets.microservice.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
public class UserService extends UserServiceGRPCGrpc.UserServiceGRPCImplBase {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void getUser(UserServiceGRPCOuterClass.UserRequest request,
                        StreamObserver<UserServiceGRPCOuterClass.UserResponse> responseObserver) {
        System.out.println(request);
        Optional<User> userFromDB = userRepository.findById(request.getId());
        if (userFromDB.isPresent()) {
            User user = userFromDB.get();
            UserServiceGRPCOuterClass.UserResponse response = UserServiceGRPCOuterClass
                    .UserResponse
                    .newBuilder()
                    .setId(user.getId())
                    .setName(user.getName())
                    .setPhone(user.getPhone())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
