package com.app.influxandroidtask

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.app.influxandroidtask.application.InfluxApplication


class OrderPageParentActivity : AppCompatActivity(), OrderPageParentView, CallBackListener {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var bottomSummaryView: ConstraintLayout
    private lateinit var productCount: TextView
    private lateinit var proceedPay: TextView
    private lateinit var bottomSheet: ConstraintLayout
    private var bottomSheetBehavior: BottomSheetBehavior<*>? = null
    private lateinit var rvFinalProductList: RecyclerView
    private lateinit var tvTotalAmount: TextView
    private lateinit var presenter: OrderPageParentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initialize()
    }

    override fun initialize() {
        tabLayout = findViewById(R.id.category_tab_layout)
        viewPager = findViewById(R.id.menu_view_pager)
        bottomSummaryView = findViewById(R.id.cl_bottom_summary)
        productCount = findViewById(R.id.tv_product_count)
        proceedPay = findViewById(R.id.tv_pay_order)
        rvFinalProductList = findViewById(R.id.rv_final_product_list)
        tvTotalAmount = findViewById(R.id.tv_total_value)
        bottomSheet = findViewById(R.id.design_bottom_sheet)
        tabTitleDivider()
        setBottomSheetBehaviour()
        initializeViewPager()
        presenter = OrderPageParentPresenter(this)
    }


    private fun tabTitleDivider() {
        val root = tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.gradientType = GradientDrawable.LINEAR_GRADIENT
            drawable.shape = GradientDrawable.RECTANGLE
            drawable.setColor(Color.WHITE)
            drawable.setSize(2, 1)
            root.dividerPadding = 10
            root.dividerDrawable = drawable
        }
    }

    private fun setBottomSheetBehaviour() {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(view: View, p1: Float) {}
            override fun onStateChanged(view: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        productCount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down_arrow, 0)
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        productCount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up_arrow, 0)
                    }
                }
            }
        })
    }

    private fun initializeViewPager() {
        val menuViewPagerAdapter = MenuViewPagerAdapter(supportFragmentManager)
        menuViewPagerAdapter.addItem(MenuListFragment().newInstance(getString(R.string.combos)), R.string.combos)
        menuViewPagerAdapter.addItem(MenuListFragment().newInstance(getString(R.string.drinks)), R.string.drinks)
        menuViewPagerAdapter.addItem(MenuListFragment().newInstance(getString(R.string.crepes)), R.string.crepes)
        viewPager.adapter = menuViewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun showBottomSummaryView(item: ItemModel) {
        presenter.addProductToCart(item)
        var finalProductListMap = presenter.getModel().finalProductListMap
        if (finalProductListMap.isNotEmpty()) {
            var totalProductCount = finalProductListMap.size
            var totalProductPrize = 0
            finalProductListMap.forEach {
                var itemModel = it.value
                totalProductPrize += (itemModel.itemQuantity * itemModel.itemPrice)
            }
            bottomSummaryView.visibility = View.VISIBLE
            productCount.text = totalProductCount.toString() + " Item"
            tvTotalAmount.text = totalProductPrize.toString()
            initiateProductListInBottomSheet(finalProductListMap)
            bottomSummaryView.setOnClickListener {
                if (bottomSheetBehavior?.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior?.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        } else {
            bottomSummaryView.visibility = View.GONE
            bottomSheetBehavior?.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    private fun initiateProductListInBottomSheet(finalProductListMap: MutableMap<String, ItemModel>) {
        var productList = mutableListOf<ItemModel>()
        productList.addAll(finalProductListMap.values)
        rvFinalProductList.layoutManager = LinearLayoutManager(this)
        rvFinalProductList.adapter = SummaryOrderAdapter(productList, this)
    }

    override fun onLaunchOrderSummery() {

    }


    class MenuViewPagerAdapter(supportFragmentManager: FragmentManager?) : FragmentPagerAdapter(supportFragmentManager) {

        private var fragmentList = mutableListOf<Fragment>()
        private var fragmentTitleList = mutableListOf<String>()


        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitleList[position]
        }

        fun addItem(fragment: Fragment, pageTitle: Int) {
            fragmentList.add(fragment)
            fragmentTitleList.add(InfluxApplication.instance.resources.getString(pageTitle))
        }

    }


}


