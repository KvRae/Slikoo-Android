package slikoo.kvrae.slikoo.data.datasources.remote

import slikoo.kvrae.slikoo.data.datasources.entities.Area

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