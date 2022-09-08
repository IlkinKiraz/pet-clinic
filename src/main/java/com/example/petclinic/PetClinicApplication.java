package com.example.petclinic;

import com.example.petclinic.model.Owner;
import com.example.petclinic.model.Pet;
import com.example.petclinic.repository.OwnerRepository;
import com.example.petclinic.repository.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetClinicApplication implements CommandLineRunner {

	private final OwnerRepository ownerRepository;
	private final PetRepository petRepository;

	public PetClinicApplication(OwnerRepository ownerRepository, PetRepository petRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PetClinicApplication.class, args);
	}


	@Override
	public void run(String... args) {
		Owner owner = ownerRepository.save(new Owner("ali", "veli", "0000000000"));
		System.out.println(owner);
	}
}
