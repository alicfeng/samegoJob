package com.special.ResideMenuDemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MenuActivity extends FragmentActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private MenuActivity mContext = this;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemProfile;
    private ResideMenuItem itemCalendar;
    private ResideMenuItem itemSettings;

    /**
     * **在某些场景下，手势滑动开启/关闭菜单可能与您的某些控件产生冲突
     * 例如viewpager，这时您可以把viewpager添加到ignored view.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //初始化菜单界面
        initMenuActivity();
        //设置默认显示为主页
        if (savedInstanceState == null)
            changeFragment(new HomeFragment());
    }

    /**
     * 初始化菜单
     */
    private void initMenuActivity() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        //禁止手势操作某个菜单方向
//        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        //是否3D效果
        resideMenu.setUse3D(false);
        //背景图片
        resideMenu.setBackground(R.drawable.menu_background);
        //必设属性
        resideMenu.attachToActivity(this);
        //设置菜单的监听事件
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip.
        //主页面滑动后占屏幕的百分比 0.0f-1.0f
        resideMenu.setScaleValue(0.6f);

        //创建菜单子项
        itemHome = new ResideMenuItem(this, R.drawable.icon_home, "Home");
        itemProfile = new ResideMenuItem(this, R.drawable.icon_profile, "Profile");
        itemCalendar = new ResideMenuItem(this, R.drawable.icon_calendar, "Calendar");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, "Settings");

        //菜单子项的点击事件
        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        //为菜单添加子项 参数(item,Direction)子项 方向ResideMenu.DIRECTION_RIGHT
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);


        //工具栏上面的两个按钮 分别定义事件
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
        findViewById(R.id.title_bar_right_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
            }
        });
    }

    //如果您需要使用手势滑动开启|关闭菜单，请复写activity的dispatchTouchEvent()
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {
        //菜单子项的事件处理
        if (view == itemHome)
            changeFragment(new HomeFragment());
        else if (view == itemProfile)
            changeFragment(new ProfileFragment());
        else if (view == itemCalendar)
            changeFragment(new CalendarFragment());
        else if (view == itemSettings)
            changeFragment(new SettingsFragment());
        //关闭主页界面
        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        //菜单打开时回调函数
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        //菜单关闭时回调函数
        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu() {
        return resideMenu;
    }
}
