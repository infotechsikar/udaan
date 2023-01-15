package com.dr.udaan.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dr.udaan.MyApp
import com.dr.udaan.R
import com.dr.udaan.base.BaseFragment
import com.dr.udaan.databinding.FragmentNotificationBinding
import com.dr.udaan.databinding.RowItemNotificationBinding
import com.dr.udaan.room.NotificationEntity
import com.dr.udaan.util.AppFunctions
import java.text.SimpleDateFormat
import java.util.*

class Notification : BaseFragment<FragmentNotificationBinding>() {

    private var _isNotificationsEnabled = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadNotifications()
        actions()

        _isNotificationsEnabled = AppFunctions.isNotificationEnabled(mContext)

    }

    private fun actions() {

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.more.setOnClickListener {
            menu()
        }

    }

    private fun menu() {

        val popupMenu = PopupMenu(mContext, binding.more)

        popupMenu.menuInflater.inflate(R.menu.notification_menu, popupMenu.menu)

        if (_isNotificationsEnabled) {
            popupMenu.menu[0].title = "Turn off notifications"
        } else {
            popupMenu.menu[0].title = "Turn on notifications"
        }

        popupMenu.setOnMenuItemClickListener {
            if (it.itemId == R.id.clearNotifications) {
                clearNotifications()
            } else if (it.itemId == R.id.turnNotification) {
                switchNotificationSettings(it)
            }
            true
        }
        popupMenu.show()

    }

    private fun clearNotifications() {
        MyApp.myDatabase?.notificationDao()?.deleteAll()
    }

    private fun switchNotificationSettings(menuItem: MenuItem) {
        _isNotificationsEnabled = !_isNotificationsEnabled
        AppFunctions.setNotificationEnabled(mContext, _isNotificationsEnabled)
    }

    private fun loadNotifications() {

        if (MyApp.myDatabase?.notificationDao()?.getNotifications()?.isEmpty() == true) {
            binding.emptyGhost.visibility = View.VISIBLE
        }

        MyApp.myDatabase?.notificationDao()?.getNotificationsLive()?.observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty()) {
                binding.emptyGhost.visibility = View.VISIBLE
                return@observe
            }
            binding.rv.adapter = AdapterNotifications(it)
        }

    }

    override fun getViewBinding() = FragmentNotificationBinding.inflate(layoutInflater)

}

class AdapterNotifications(val list: List<NotificationEntity>): RecyclerView.Adapter<AdapterNotifications.NotificationHolder>() {

    class NotificationHolder(itemView: View, val dBinding: RowItemNotificationBinding) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotificationHolder {
        val dBinding = RowItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotificationHolder(dBinding.root, dBinding)
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {

        holder.dBinding.content.text = list[position].content
        holder.dBinding.dateTime.text = SimpleDateFormat("hh:mm a dd MMM y", Locale.ENGLISH).format(Date(list[position].dateTime))
        holder.dBinding.title.text = list[position].title
        holder.dBinding.logo.load(list[position].image)

    }

    override fun getItemCount() = list.size


}

