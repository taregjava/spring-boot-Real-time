package com.halfacode.spring_real_time_learning.service;

import com.halfacode.spring_real_time_learning.entity.Contract;
import com.halfacode.spring_real_time_learning.repository.ContractRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    public Contract createContract(Contract contract) {
        // Generate contract number and other response data
        contract.setContractNumber("CN-" + UUID.randomUUID().toString().substring(0, 8));
        contract.setOwnerIdNumber(contract.getBeneficiaryIdNumber());
        contract.setOwnerIdType(
                contract.getBeneficiaryIdType() != null ? contract.getBeneficiaryIdType().toString() : "N/A"
        );

        return contractRepository.save(contract);
    }
}