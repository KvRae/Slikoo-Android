package slikoo.kvrae.slikoo.data.datasources.local

import androidx.room.Update
import slikoo.kvrae.slikoo.data.datasources.entities.Area

interface AreaDao {

    @Update
    fun updateAreas( areas: List<Area>) {}
}