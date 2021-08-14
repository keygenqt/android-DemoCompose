package com.keygenqt.demo_contacts.modules.favorite.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.keygenqt.demo_contacts.base.error
import com.keygenqt.demo_contacts.base.isEmpty
import com.keygenqt.demo_contacts.base.isError
import com.keygenqt.demo_contacts.base.success
import com.keygenqt.demo_contacts.data.models.ProductModel
import com.keygenqt.demo_contacts.modules.favorite.services.ApiServiceFavorite
import com.keygenqt.demo_contacts.modules.favorite.services.DataServiceFavorite
import com.keygenqt.demo_contacts.utils.ConstantsPaging.CACHE_TIMEOUT
import timber.log.Timber

@ExperimentalPagingApi
class FavoriteRemoteMediator(
    private val data: DataServiceFavorite,
    private val apiService: ApiServiceFavorite,
) : RemoteMediator<Int, ProductModel>() {

    override suspend fun initialize(): InitializeAction {
        return if (System.currentTimeMillis() - data.preferences.lastUpdateListFavorite >= CACHE_TIMEOUT) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ProductModel>,
    ): MediatorResult {
        return try {

            val page = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem =
                        state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id.toInt()
                }
            }

            val response = apiService.getListFavorite(
                page = page ?: 0
            )

            response.success { models ->
                data.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        preferences.lastUpdateListFavorite = System.currentTimeMillis()
                        clearFavorite()
                    }
                    insertFavorite(models)
                }
            }.error {
                Timber.e(it)
            }

            MediatorResult.Success(
                endOfPaginationReached = response.isError || response.isEmpty
            )

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}