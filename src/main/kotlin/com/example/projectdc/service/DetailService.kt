package com.example.projectdc.service


import com.example.projectdc.model.Detail
import com.example.projectdc.model.Invoice
import com.example.projectdc.model.Product
import com.example.projectdc.repository.DetailRepository
import com.example.projectdc.repository.InvoiceRepository
import com.example.projectdc.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class DetailService {
    @Autowired
    lateinit var detailRepository: DetailRepository

    @Autowired
    lateinit var invoiceRepository: InvoiceRepository

    @Autowired
    lateinit var productRepository: ProductRepository

    fun save(detail: Detail): Detail {
        try{
            val response=detailRepository.save(detail)
            val responseProduct:Product = productRepository.findById(response.productId)
            responseProduct.apply {
                stock = stock?.minus(detail.quantity!!)
            }
            productRepository.save(responseProduct)

            calculateAndUpdateTotal(response)

            return response
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun list(): List<Detail> {
        return detailRepository.findAll()
    }

    fun update(detail: Detail): Detail {
        try {
            detailRepository.findById(detail.id)
                ?: throw Exception("ID no existe")
            return detailRepository.save(detail)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }

    }

    fun updateQuantity(detail: Detail): Detail {
        try {
            val response = detailRepository.findById(detail.id)
                ?: throw Exception("ID no existe")
            response.apply {
                quantity = detail.quantity
            }
            return detailRepository.save(response)
        } catch (ex: Exception) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
        }
    }
    fun calculateAndUpdateTotal (detail : Detail){
        val totalCalculated = detailRepository.sumTotal(detail.invoiceId)
        val invoiceResponse = invoiceRepository.findById(detail.invoiceId)
        invoiceResponse.apply {
            total=totalCalculated
        }
        invoiceRepository.save(invoiceResponse)
    }
}