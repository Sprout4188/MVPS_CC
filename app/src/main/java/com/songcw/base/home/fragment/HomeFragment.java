package com.songcw.base.home.fragment;

import com.songcw.base.R;
import com.songcw.base.home.mvp.section.HomeDispatchSection;
import com.songcw.basecore.mvp.BaseFragment;

/**
 * Created by Sprout on 2018/7/30
 */

public class HomeFragment extends BaseFragment {

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void addSections() {
        addSection(new HomeDispatchSection(this));
    }
}
