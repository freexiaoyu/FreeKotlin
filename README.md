# cloudKotlin

#### 项目介绍
cloud的开源项目转化kotlin语言的项目

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明

1. 所有activity继承于BaseActivity(ui.base包)
2. 所有fragement继承于BaseFragment(ui.base包)
3. 所有adapter继承于BaseCloudAdapter(ui.base包)
4. Toast 统一使用方式ToastUtil.show(mContext,"")
5. 图片载使用方法  FreeImageLoader.get().display(iv_photo, "")
6. 

### 动态获取权限使用 
```
private PermissionsChecker mPermissionsChecker; // 权限检测器
  String[] perms = {"android.permission.RECORD_AUDIO"
            , "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_FINE_LOCATION"};
  mPermissionsChecker = new PermissionsChecker(this);
           // 缺少权限时, 进入权限配置页面
           if (mPermissionsChecker.lacksPermissions(perms)) {
               ActivityCompat.requestPermissions(MainActivity.this, perms, RESULT_CODE_PERMISSIONS);
           } else {
                //执行你的方法
           }
    @Override
  public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults) {
          switch (permsRequestCode) {
              case RESULT_CODE_PERMISSIONS:
                  boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                  if (!cameraAccepted) {
                      ToastUtil.show(mContext, "缺少主要权限, 无法运行");
                  } else {
                    //执行你的方法
                  }
                  break;
          }
      }
```

#### 参与贡献

1. Fork 本项目
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request

#### 使用了第三方框架

1. 图片轮播carouselview   https://github.com/sayyam/carouselview
2. 图片加载glide  https://github.com/bumptech/glide
3. 网络请求okhttp   https://github.com/square/okhttp
          retrofit   https://github.com/square/retrofit
4. 组件通信 eventbus https://github.com/greenrobot/EventBus
5. 生成二维码 zxing
6. 列表刷新控件使用SmartRefreshLayout https://github.com/scwang90/SmartRefreshLayout

