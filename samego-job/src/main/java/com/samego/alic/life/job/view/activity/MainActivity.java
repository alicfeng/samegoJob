package com.samego.alic.life.job.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import com.atermenji.android.iconicdroid.IconicFontDrawable;
import com.atermenji.android.iconicdroid.icon.EntypoIcon;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.samego.alic.life.helper.R;
import com.samego.alic.life.job.util.Colors;
import com.samego.alic.life.job.util.StatusBarCompat;
import com.samego.alic.life.job.view.fragment.AccountFragment;
import com.samego.alic.life.job.view.fragment.AnniversaryFragment;
import com.samego.alic.life.job.view.fragment.BirthdayFragment;
import com.samego.alic.life.job.view.fragment.LocationFragment;
import com.samego.alic.life.job.view.fragment.NoteFragment;
import com.samego.alic.life.job.view.fragment.SettingsFragment;
import com.samego.alic.life.job.view.fragment.ToDoFragment;
import com.samego.alic.life.job.view.fragment.TrackFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private MainActivity mContext = this;
    private ResideMenuItem itemToDo;
    private ResideMenuItem itemTrack;
    private ResideMenuItem itemNote;
    private ResideMenuItem itemAccount;
    private ResideMenuItem itemBirthday;
    private ResideMenuItem itemAnniversary;
    private ResideMenuItem itemLocation;
    private ResideMenuItem itemSettings;

    private MaterialMenuView menuAction;

    private IconicFontDrawable todoIcon, trackIcon, noteIcon, birthdayIcon, accountIcon,
            anniversaryIcon, locationIcon, settingsIcon;

    /**
     * **在某些场景下，手势滑动开启/关闭菜单可能与您的某些控件产生冲突
     * 例如viewpager，这时您可以把viewpager添加到ignored view.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_content);
        initUI();
        //初始化菜单界面
        initMenuActivity();
        //设置默认显示为主页
        if (savedInstanceState == null)
            changeFragment(new ToDoFragment());
    }

    //初始化UI
    private void initUI() {
        StatusBarCompat.setStatusBarColor(this, StatusBarCompat.COLOR_ToolBar_HIGHTBLUE);
        menuAction = (MaterialMenuView) findViewById(R.id.main_title_bar_left_menu);

    }

    /**
     * 初始化菜单
     */
    private void initMenuActivity() {
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        //禁止手势操作某个菜单方向
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
//        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
        //是否3D效果
        resideMenu.setUse3D(false);
        //背景图片
//        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.setBackground(R.color.colorPrimary);
        //必设属性
        resideMenu.attachToActivity(this);
        //设置菜单的监听事件
        resideMenu.setMenuListener(menuListener);
        //主页面滑动后占屏幕的百分比 0.0f-1.0f
        resideMenu.setScaleValue(0.72f);

        //创建菜单子项图标
        todoIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        trackIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        noteIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        accountIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        birthdayIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        anniversaryIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        locationIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);
        settingsIcon = new IconicFontDrawable(this, EntypoIcon.SIGNAL, Colors.color_pure_white);

        //创建菜单子项
        itemToDo = new ResideMenuItem(this, todoIcon, "待办事项");
        itemTrack = new ResideMenuItem(this, trackIcon, "日迹记录");
        itemNote = new ResideMenuItem(this, noteIcon, "便签管理");
        itemAccount = new ResideMenuItem(this, accountIcon, "账号信息");
        itemBirthday = new ResideMenuItem(this, birthdayIcon, "生日提醒");
        itemAnniversary = new ResideMenuItem(this, anniversaryIcon, "纪  念  日");
        itemLocation = new ResideMenuItem(this, locationIcon, "位置共享");
        itemSettings = new ResideMenuItem(this, settingsIcon, "应用设置");

        //菜单子项的点击事件
        itemToDo.setOnClickListener(this);
        itemTrack.setOnClickListener(this);
        itemNote.setOnClickListener(this);
        itemAccount.setOnClickListener(this);
        itemBirthday.setOnClickListener(this);
        itemAnniversary.setOnClickListener(this);
        itemLocation.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        //为菜单添加子项 参数(item,Direction)子项 方向ResideMenu.DIRECTION_RIGHT
        resideMenu.addMenuItem(itemToDo, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemTrack, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemNote, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAccount, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemBirthday, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemAnniversary, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLocation, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);


        //工具栏上面的两个按钮 分别定义事件
        menuAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
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
        if (view == itemToDo)
            changeFragment(new ToDoFragment());
        else if (view == itemTrack)
            changeFragment(new TrackFragment());
        else if (view == itemNote)
            changeFragment(new NoteFragment());
        else if (view == itemAccount)
            changeFragment(new AccountFragment());
        else if (view == itemBirthday)
            changeFragment(new BirthdayFragment());
        else if (view == itemAnniversary)
            changeFragment(new AnniversaryFragment());
        else if (view == itemLocation)
            changeFragment(new LocationFragment());
        else if (view == itemSettings)
            changeFragment(new SettingsFragment());
        //关闭主页界面
        resideMenu.closeMenu();
    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        //菜单打开时回调函数
        @Override
        public void openMenu() {
            menuAction.setIconState(MaterialMenuDrawable.IconState.ARROW);
        }

        //菜单关闭时回调函数
        @Override
        public void closeMenu() {
            menuAction.setIconState(MaterialMenuDrawable.IconState.BURGER);
        }
    };

    // 切换targetFragment
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

    //The number of method references in a .dex file cannot exceed 64K.防止安装出现
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this) ;
    }
}
