package com.star.mercadolivreteste.di

import com.star.corekotlin.koin.KoinModule
import com.star.corekotlin.mapper.Mapper
import com.star.mercadolivreteste.data.datasource.HomeDataSource
import com.star.mercadolivreteste.data.datasource.HomeRemoteDataSource
import com.star.mercadolivreteste.data.model.response.HomeSearchResponse
import com.star.mercadolivreteste.data.repository.HomeRepository
import com.star.mercadolivreteste.domain.mapper.HomeDataToDomainMapper
import com.star.mercadolivreteste.domain.model.HomeSearchResult
import com.star.mercadolivreteste.domain.usecase.GetDetailsUseCase
import com.star.mercadolivreteste.presentation.viewmodel.DetailsViewModel
import com.star.mercadolivreteste.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val HOME_DATA_TO_DOMAIN_MAPPER = "homeDataToDomainMapper"

class HomeKoinModule : KoinModule {
    val all = module {
        factory<HomeDataSource> { HomeRemoteDataSource(client = get()) }
        factory {
            HomeRepository(dataSource = get(), mapper = get(named(HOME_DATA_TO_DOMAIN_MAPPER)))
        }
        factory {
            GetDetailsUseCase(repository = get())
        }
        factoryOf(::HomeDataToDomainMapper) {
            this.qualifier = named(HOME_DATA_TO_DOMAIN_MAPPER)
        }
        viewModel {
            HomeViewModel(dispatcher = get(), repository = get())
        }
        viewModel {
            DetailsViewModel(dispatcher = get(), getDetails = get())
        }
    }
    override val data: Module = module {
        factory<HomeDataSource> { HomeRemoteDataSource(client = get()) }
        factory {
            HomeRepository(dataSource = get(), mapper = get(named(HOME_DATA_TO_DOMAIN_MAPPER)))
        }
    }

    override val domain: Module = module {
        factoryOf(::HomeDataToDomainMapper)
        }

    override val presentation: Module = module {
        viewModel {
            HomeViewModel(dispatcher = get(), repository = get())
        }
    }

    override val additional: Module
        get() = module {

        }

    override fun load() {
        loadKoinModules(all)
    }

    override fun unload() {
        unloadKoinModules(domain + data + presentation + additional)
    }

    override fun getModules(): List<Module> = listOf(domain, data, presentation, additional)

}