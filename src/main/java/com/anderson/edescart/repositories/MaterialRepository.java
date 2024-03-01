package com.anderson.edescart.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anderson.edescart.entities.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {

}
