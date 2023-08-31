package com.Starky.codes.controllers;




import com.Starky.codes.response.*;
import com.Starky.codes.service.UserService;
import com.Starky.codes.userRequest.TransferRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole(USER)")
public class UserController {
    private final UserService service;
    @PutMapping(path = "/transfer/{userId}")
    public ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequest request, @PathVariable String userId) {
        return ResponseEntity.ok(service.transfer(request, userId));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<AuthenticationResponse> getUser(@PathVariable String userId){
        return ResponseEntity.ok(service.getUser(userId));
    }
    @DeleteMapping (path = "/{userId}")
    public ResponseEntity<DeleteResponse> deleteUser(@PathVariable String userId){
        return ResponseEntity.ok(service.deleteUser(userId));
    }
    @GetMapping
    public ResponseEntity<List<PageModel>> getUsers(@RequestParam (value ="page", defaultValue = "0") int page,
                                                    @RequestParam(value = "limit", defaultValue = "25") int limit, Pageable pageable){
        return ResponseEntity.ok(service.getUsers(page,limit, pageable));
    }

    @GetMapping(path = "/{userId}/transactions")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable String userId){
        return ResponseEntity.ok(service.getTransactions(userId));
    }
    @GetMapping(path = "/{userId}/transactions/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable String userId, @PathVariable String transactionId ){
        return ResponseEntity.ok(service.getTransaction(userId,transactionId));
    }

    @GetMapping(path = "/{userId}/addresses")
    public ResponseEntity<List<AddressResponse>> getAddresses(@PathVariable String userId){
        return ResponseEntity.ok(service.getAddresses(userId));
    }

    @GetMapping(path = "/{userId}/addresses/{addressId}")
    public ResponseEntity<AddressResponse> getAddress(@PathVariable String userId, @PathVariable String addressId ){
        return ResponseEntity.ok(service.getAddress(userId,addressId));
    }
}