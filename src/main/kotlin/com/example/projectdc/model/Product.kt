package com.example.projectdc.model

import java.util.Date
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "product")

class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null
    @NotBlank
    var description : String? = null
    @NotBlank
    var brand: String? = null

    var stock: Long? = null
    var price: Double?=null
}