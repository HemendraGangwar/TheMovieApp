package com.movie.app.data.roomDb.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.movie.app.data.responseBean.NetworkMapper
import com.movie.app.data.model.NetworkResponseModel
import com.movie.app.data.model.Resource
import com.movie.app.data.webApi.ApiResponse


abstract class NetworkRepository<ResultType,
  RequestType : NetworkResponseModel,
  Mapper : NetworkMapper<RequestType>>
internal constructor(sortBy : Int) {

  private val result: MediatorLiveData<Resource<ResultType>> = MediatorLiveData()

  init {
    val loadedFromDB = this.loadFromDb()
    result.addSource(loadedFromDB) { data ->
      result.removeSource(loadedFromDB)
      if (shouldFetch(data)) {
        result.postValue(Resource.loading(null))
        fetchFromNetwork(loadedFromDB,sortBy)
      } else {
        result.addSource<ResultType>(loadedFromDB) { newData ->
          setValue(Resource.success(newData, false))
        }
      }
    }
  }

  private fun fetchFromNetwork(loadedFromDB: LiveData<ResultType>, sortBy : Int) {
    val apiResponse = fetchService(sortBy)
    result.addSource(apiResponse) { response ->
      response?.let {
        when (response.isSuccessful) {
          true -> {
            response.body?.let {
              saveFetchData(it)
              val loaded = loadFromDb()
              result.addSource(loaded) { newData ->
                newData?.let {
                  setValue(Resource.success(newData, mapper().onLastPage(response.body)))
                }
              }
            }
          }
          false -> {
            result.removeSource(loadedFromDB)
            onFetchFailed(response.message)
            response.message?.let {
              result.addSource<ResultType>(loadedFromDB) { newData ->
                setValue(Resource.error(it, newData))
              }
            }
          }
        }
      }
    }
  }

  @MainThread
  private fun setValue(newValue: Resource<ResultType>) {
    result.value = newValue
  }

  fun asLiveData(): LiveData<Resource<ResultType>> {
    return result
  }

  @WorkerThread
  protected abstract fun saveFetchData(items: RequestType)

  @MainThread
  protected abstract fun shouldFetch(data: ResultType?): Boolean

  @MainThread
  protected abstract fun loadFromDb(): LiveData<ResultType>

  @MainThread
  protected abstract fun fetchService(sortBy : Int): LiveData<ApiResponse<RequestType>>

  @MainThread
  protected abstract fun mapper(): Mapper

  @MainThread
  protected abstract fun onFetchFailed(message: String?)
}
