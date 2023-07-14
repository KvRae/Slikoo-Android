package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.models.Notification



class NotificationViewModel: ViewModel() {


    fun getNotifications(): List<Notification> {
        val notifications = listOf(
            Notification(profileImage = R.drawable.avatar,"karam Mannai", "hello world", "Time"),
            Notification(profileImage = R.drawable.avatar,"Ahmed Mannai", "hello world", "Time"),
            Notification(profileImage = R.drawable.avatar,"Morad Mannai", "hello world", "Time"),
            Notification(profileImage = R.drawable.avatar,"Asma Mannai", "hello world", "Time"),
            Notification(profileImage = R.drawable.avatar,"Sirine Mannai", "hello world", "Time")
        )
        return notifications
    }
}
