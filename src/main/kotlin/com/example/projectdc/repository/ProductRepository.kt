package com.example.projectdc.repository

import com.example.projectdc.model.Client
import com.example.projectdc.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long?> {
    fun findById(id: Long?): Product?
}