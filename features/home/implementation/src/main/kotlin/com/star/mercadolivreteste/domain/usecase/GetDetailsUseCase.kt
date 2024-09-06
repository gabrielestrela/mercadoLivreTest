package com.star.mercadolivreteste.domain.usecase

import com.star.mercadolivreteste.data.repository.HomeRepository

// 403 Forbidden
class GetDetailsUseCase(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(id: String) = repository.details(id)
}