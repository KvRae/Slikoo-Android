package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.models.Notification


class NotificationViewModel : ViewModel() {
    private val notifications = mutableListOf<Notification>(
        Notification(
            id = 1,
            profileImage = R.drawable.avatar,
            "karam Mannai",
            "hello world",
            "Time"
        ),
        Notification(
            id = 2,
            profileImage = R.drawable.avatar,
            "Ahmed Mannai",
            "hello world",
            "Time"
        ),
        Notification(
            id = 3,
            profileImage = R.drawable.avatar,
            "Morad Mannai",
            "hello world",
            "Time"
        ),
        Notification(
            id = 4,
            profileImage = R.drawable.avatar,
            "Asma Mannai",
            "hello world",
            "Time"
        ),
        Notification(
            id = 5,
            profileImage = R.drawable.avatar,
            "Sirine Mannai",
            "hello world",
            "Time"
        )
    )

    fun getNotifications(): List<Notification> {
        return notifications
    }

    fun removeNotification(notification: Notification) {
        notifications.remove(notification)
    }


}
