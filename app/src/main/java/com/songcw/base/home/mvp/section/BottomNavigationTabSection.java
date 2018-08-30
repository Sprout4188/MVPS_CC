package com.songcw.base.home.mvp.section;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.songcw.base.R;
import com.songcw.base.home.adapter.MainViewPagerAdapter;
import com.songcw.base.home.mvp.presenter.BottomNavigationTabPresenter;
import com.songcw.basecore.mvp.BaseActivity;
import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.basecore.mvp.BaseSection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Sprout on 2018/8/28
 * 底部导航栏切片
 */
public class BottomNavigationTabSection extends BaseSection<BottomNavigationTabPresenter> {
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.ntb_main)
    NavigationTabBar ntbMain;

    public BottomNavigationTabSection(Object source) {
        super(source);
    }

    @Override
    public BottomNavigationTabPresenter onCreatePresenter() {
        return new BottomNavigationTabPresenter(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    public void initUI() {
        List<BaseFragment> fragments = mPresenter.getMainViewPagerFragments();
        vpMain.setOffscreenPageLimit(5);
        vpMain.setAdapter(new MainViewPagerAdapter(((BaseActivity)getContext()).getSupportFragmentManager(), fragments));
        ArrayList<NavigationTabBar.Model> models = mPresenter.getModels();
        ntbMain.setModels(models);
        ntbMain.setViewPager(vpMain, 0);
        ntbMain.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                ntbMain.getModels().get(position).hideBadge();
            }

            @Override
            public void onPageScrollStateChanged(final int state) {

            }
        });

        ntbMain.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < ntbMain.getModels().size(); i++) {
                    final NavigationTabBar.Model model = ntbMain.getModels().get(i);
                    ntbMain.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            model.showBadge();
                        }
                    }, i * 100);
                }
            }
        }, 500);
    }
}
