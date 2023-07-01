package com.br.santander.application.business;

import static com.br.santander.application.infraestruture.Message.ACCOUNT_EXISTS;
import static com.br.santander.application.infraestruture.Message.ACCOUNT_NOT_FOUND;
import static com.br.santander.application.infraestruture.Message.ACCOUNT_TYPE_NOT_FOUND;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.br.santander.application.business.exception.BusinessException;
import com.br.santander.application.business.exception.NotFoundException;
import com.br.santander.application.infraestruture.utils.NumberUtils;
import com.br.santander.application.repository.AccountRepository;
import com.br.santander.application.repository.AccountTypeRepository;
import com.br.santander.application.repository.model.external.AccountDTO;
import com.br.santander.application.repository.model.external.AccountTypeDTO;
import com.br.santander.application.repository.model.internal.Account;
import com.br.santander.application.repository.model.internal.AccountType;
import com.br.santander.application.repository.model.internal.Transaction;
import com.br.santander.application.repository.model.internal.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

	private final AccountTypeRepository accountTypeRepository;
	
	private final AccountRepository repository;
	
	private final ClientService clientService;
	
	
	public AccountDTO save(AccountDTO accountDTO ) {
		
		validateAccountCreate(accountDTO);
		
		Account account = Account
				.builder()
					.client(clientService.getBy(accountDTO.clientId()))
					.type(getAccountTypeBy(accountDTO.accountTypeId()))
					.accountNumber(RandomStringUtils.randomNumeric(5))
					.build();
		
		return AccountDTO
				.accountWithBalance(repository.save(account), 
						NumberUtils.formatCurrencyNumber(BigDecimal.ZERO.setScale(2)) );

	}
	
	private void validateAccountCreate(AccountDTO accountDto ) {
		
		
		Optional<Account> account = repository
				.findByClientIdAndTypeId(accountDto.clientId(), accountDto.accountTypeId());
		
		if(account.isPresent())
			throw new BusinessException(ACCOUNT_EXISTS);
	}
	
	private AccountType getAccountTypeBy(Long id) {
		
		return accountTypeRepository
				.findById(id)
				.orElseThrow(() -> new NotFoundException(ACCOUNT_TYPE_NOT_FOUND) );
	}
	
	public AccountDTO getAccountDtoByNumber(String accountNumber) {
		
		Account account = getAccountByNumber(accountNumber);
		
		return AccountDTO.accountWithBalance(account, getBalanceBy(account) );
	}
	
	private String getBalanceBy( Account account ) {
		
		return NumberUtils.formatCurrencyNumber(calculateAccountBalance(account));
	}
	
	public BigDecimal calculateAccountBalance(Account account ) {
		
		return totalizeTransactions(account.getTransactions(),
					TransactionType.DEPOSIT)
				.subtract(totalizeTransactions(account.getTransactions(),
						TransactionType.DEBIT))
				.setScale(2);
	}
	
	private BigDecimal totalizeTransactions(List<Transaction> transactions, TransactionType type ) {
		
		return transactions
			.stream()
				.filter(transaction -> transaction.getType().equals(type))
				.map(Transaction::getCalculatedValue)
			.reduce(BigDecimal::add)
			.orElse(BigDecimal.ZERO);
	}
	
	public Account getAccountByNumber(String accountNumber) {
		
		return repository
				.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new NotFoundException(ACCOUNT_NOT_FOUND));
	}
	
	public List<AccountDTO> getByClientId(Long clientId){

		List<Account> accounts = repository
				.findByClient(clientService.getBy(clientId));
		
		if(accounts.isEmpty()) 
			throw new NotFoundException(ACCOUNT_NOT_FOUND);
		
		return accounts
				.stream()
				.map(account -> AccountDTO.accountWithBalance(account, getBalanceBy(account)))
				.toList();
	}
	
	@Cacheable("accountsType")
	public List<AccountTypeDTO> getAllAccountsType(){
		
		return accountTypeRepository
				.findAll()
					.stream()
					.map(AccountTypeDTO::new)
					.toList();
		
	}
}
