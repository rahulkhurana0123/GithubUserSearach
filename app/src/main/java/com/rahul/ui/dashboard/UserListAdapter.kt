package com.rahul.ui.dashboard

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.rahul.ui.util.DataBindingViewHolder
import com.rahul.BR.item
import com.rahul.data.model.UserModel
import com.rahul.databinding.UserItemRowBinding

/**
 * Created by rahul khurana on 16.05.2020.
 */

class UserListAdapter(
    private var userList: MutableList<UserModel> = arrayListOf<com.rahul.data.model.UserModel>()
) : RecyclerView.Adapter<UserListAdapter.SimpleHolder>() {
    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: SimpleHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleHolder {
        val binding  = UserItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SimpleHolder(binding)
    }

    inner class SimpleHolder(dataBinding: ViewDataBinding)
        : DataBindingViewHolder<UserModel>(dataBinding)  {
        override fun onBind(t: UserModel): Unit = with(t) {
            dataBinding.setVariable(item, t)
        }
    }

    fun add(list: MutableList<UserModel>) {
        userList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        userList.clear()
        notifyDataSetChanged()
    }
}