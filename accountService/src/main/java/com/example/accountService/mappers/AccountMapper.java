package com.example.accountService.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.example.accountService.dtos.AccountDetail;
import com.example.accountService.dtos.AccountTransferResponse;
import com.example.accountService.dtos.AccountCreation;
import com.example.accountService.dtos.AccountResponse;
import com.example.accountService.model.Accounts;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    Accounts toAccount(AccountCreation request);

    AccountResponse toAccountResponse(Accounts account);

    AccountDetail toAccountDetail(Accounts account);

    List<AccountDetail> toAccountDetail(List<Accounts> accounts);

    AccountTransferResponse toAccountTransferResponse(String message);
}
