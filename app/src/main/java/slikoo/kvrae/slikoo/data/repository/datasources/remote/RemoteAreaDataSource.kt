package slikoo.kvrae.slikoo.data.repository.datasources.remote

import slikoo.kvrae.slikoo.data.models.Area

class RemoteAreaDataSource {
    private var remoteAreaDataSource: RemoteAreaDataSource? = null


   fun getInstance(): RemoteAreaDataSource? {
        if (remoteAreaDataSource == null) {
            remoteAreaDataSource = RemoteAreaDataSource()
        }
        return remoteAreaDataSource
    }

    suspend fun getAreas() : List<Area> {
        return listOf<Area>()
    }






}