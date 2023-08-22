package slikoo.kvrae.slikoo.data.repository

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.repository.datasources.local.LocalAreaDataSource
import slikoo.kvrae.slikoo.data.repository.datasources.remote.RemoteAreaDataSource
import slikoo.kvrae.slikoo.data.models.Area

class AreaRepository : ViewModel() {
    private var areas = mutableListOf<Area>()

    private var remoteAreaDataSource = RemoteAreaDataSource()
    private var localAreaDataSource = LocalAreaDataSource()

    suspend fun getAreas() : List<Area> {
        try {
            areas = remoteAreaDataSource.getAreas() as MutableList<Area>
            localAreaDataSource.updateAreas(areas)
        }
        catch (e: Exception) {
            // handle exception
        }
        return areas
    }



}