package br.com.antunes.gustavo.carrentproject.model;

import java.util.Objects;
import java.util.logging.Logger;

import br.com.antunes.gustavo.carrentproject.security.UserEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;

@MappedSuperclass
public abstract class Person {
	
	private static final Logger logger = Logger.getLogger(Person.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	private String firstName;

	private String lastName;
	
	@Column(unique = true)
    private String identificationNumber;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserEntity userEntity;
	
	public Person() {}

	public Person(Long id, String email, String firstName, String lastName, String identificationNumber, Address address) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.identificationNumber = identificationNumber;
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, email, firstName, id, identificationNumber, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		logger.info("Entering equals method in Person class");
		if (this == obj) {
			logger.info("Exiting equals method in Person class with true value");
			return true;
		}
		if (obj == null) {
			logger.info("Exiting equals method in Person class with false value");
			return false;
		}
		if (getClass() != obj.getClass()) {
			logger.info("Exiting equals method in Person class with false value");
			return false;
		}
		logger.info("Exiting equals method in Person class with true value");
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(identificationNumber, other.identificationNumber)
				&& Objects.equals(lastName, other.lastName);
	}
	
}
