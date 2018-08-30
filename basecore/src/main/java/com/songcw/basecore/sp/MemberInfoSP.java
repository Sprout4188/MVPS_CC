package com.songcw.basecore.sp;

import com.songcw.basecore.sp.items.StringPrefItem;

/**
 * Create by Sprout at 2017/8/15
 * 会员SP, 退出登录时清空
 */
public class MemberInfoSP extends BaseSP {
    public static MemberInfoSP instance = new MemberInfoSP();

    private MemberInfoSP() {
        super("songche_member_info"); //定义SP文件名
    }

    /**
     * 设备唯一编码uuid
     */
    public static final StringPrefItem uuid = new StringPrefItem(instance, "uuid", "");

    /**
     * 会员编号
     */
    public static final StringPrefItem memberNo = new StringPrefItem(instance, "memberNo", "");

    @Override
    public void clear() {
        super.clear();
        memberNo.setValue("");
    }
}
