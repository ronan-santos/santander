package com.br.santander.application.repository.model.internal;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import static com.br.santander.application.infraestruture.Message.ACCOUNT_EXISTS;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter 
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "account_type_id", referencedColumnName = "id" )
	private AccountType type;
	
	@ManyToOne
	@JoinColumn(name = "client_id",  referencedColumnName = "id")
	private Client client;

	private String accountNumber;
	
	@OneToMany(mappedBy = "account")
	private List<Transaction> transactions; 
	
	

}
