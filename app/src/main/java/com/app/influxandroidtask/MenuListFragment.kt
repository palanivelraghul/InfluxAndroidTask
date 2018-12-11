package com.app.influxandroidtask

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class MenuListFragment : Fragment(), MenuListView {

    private lateinit var rvMenuList: RecyclerView
    private lateinit var presenter: MenuListPresenter
    private var callBackListener: CallBackListener? = null

    fun newInstance(menuCategory: String): MenuListFragment {
        val args = Bundle()
        args.putString("MENU_CATEGORY", menuCategory)
        val fragment = MenuListFragment()
        fragment.arguments = args
        return fragment
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.menu_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rvMenuList = view.findViewById(R.id.rv_menu_list)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity is CallBackListener)
            callBackListener = activity as CallBackListener?
        presenter = MenuListPresenter(this)
        var menuCategory = arguments?.getString("MENU_CATEGORY")
        presenter.initializeMenuList(menuCategory)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView() {
        rvMenuList.layoutManager = LinearLayoutManager(activity)
        rvMenuList.adapter = MenuListAdapter(presenter.getModel().combosMenuList, this, this!!.activity!!)

    }

    override fun onItemCountAdjust(item: ItemModel) {
        callBackListener?.showBottomSummaryView(item)
    }

}
