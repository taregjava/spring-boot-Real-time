package com.halfacode.spring_real_time_learning.controller;
import com.halfacode.spring_real_time_learning.dto.ContractResponse;
import com.halfacode.spring_real_time_learning.entity.Contract;
import com.halfacode.spring_real_time_learning.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @PostMapping
    public ResponseEntity<?> createContract(@RequestBody Contract contractRequest) {
        Contract savedContract = contractService.createContract(contractRequest);

        // Return only the needed fields in response
        return ResponseEntity.ok(new ContractResponse(
                savedContract.getContractNumber(),
                savedContract.getOwnerIdNumber(),
                savedContract.getOwnerIdType()
        ));
    }

}
