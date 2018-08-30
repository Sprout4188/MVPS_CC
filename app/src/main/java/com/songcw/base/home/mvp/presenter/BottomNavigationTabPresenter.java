package com.songcw.base.home.mvp.presenter;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;

import com.songcw.base.R;
import com.songcw.base.home.fragment.CarOwnerFragment;
import com.songcw.base.home.fragment.HomeFragment;
import com.songcw.base.home.fragment.MeFragment;
import com.songcw.base.home.fragment.TravelFragment;
import com.songcw.basecore.mvp.BaseFragment;
import com.songcw.basecore.mvp.BasePresenter;
import com.songcw.basecore.mvp.IController;

import java.util.ArrayList;
import java.util.List;

import devlight.io.library.ntb.NavigationTabBar;

/**
 * Created by Sprout on 2018/8/28
 */
public class BottomNavigationTabPresenter extends BasePresenter {

    public BottomNavigationTabPresenter(IController.IView view) {
        super(view);
    }

    @NonNull
    public ArrayList<NavigationTabBar.Model> getModels() {
        Resources resources = getContext().getResources();
        String[] colors = resources.getStringArray(R.array.default_preview);
        ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        models.add(new NavigationTabBar.Model.Builder(
                resources.getDrawable(R.mipmap.logo), Color.parseColor(colors[0]))
                .selectedIcon(resources.getDrawable(R.mipmap.logo))
                .title("首页").badgeTitle("Home").build());
        models.add(new NavigationTabBar.Model.Builder(
                resources.getDrawable(R.mipmap.logo), Color.parseColor(colors[1]))
//                .selectedIcon(resources.getDrawable(R.mipmap.logo))
                .title("出行").badgeTitle("Travel").build());
        models.add(new NavigationTabBar.Model.Builder(
                resources.getDrawable(R.mipmap.logo), Color.parseColor(colors[2]))
                .selectedIcon(resources.getDrawable(R.mipmap.logo))
                .title("车主").badgeTitle("Owner").build());
        models.add(new NavigationTabBar.Model.Builder(
                resources.getDrawable(R.mipmap.logo), Color.parseColor(colors[3]))
                .selectedIcon(resources.getDrawable(R.mipmap.logo))
                .title("我的").badgeTitle("Me").build());
        return models;
    }

    /**
     * 获取四大模块Fragment
     */
    @NonNull
    public List<BaseFragment> getMainViewPagerFragments() {
        List<BaseFragment> fragments = new ArrayList<>();
        HomeFragment homeFragment = new HomeFragment();
        TravelFragment travelFragment = new TravelFragment();
        CarOwnerFragment carOwnerFragment = new CarOwnerFragment();
        MeFragment meFragment = new MeFragment();
        fragments.add(homeFragment);
        fragments.add(travelFragment);
        fragments.add(carOwnerFragment);
        fragments.add(meFragment);
        return fragments;
    }
}
