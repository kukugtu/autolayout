# autolayout
autolayout不更新了，在项目中用到了，维护了一下

Step 1. Add the JitPack repository to your build file 
Add it in your root build.gradle at the end of repositories:

allprojects {
  repositories {
   ...
   maven { url 'https://jitpack.io' }
  }
 }

Step 2. Add the dependency

 dependencies {
         implementation 'com.github.kukugtu:autolayout:1.0'
 }


解决了控件变形问题，适配全面屏。
提供了代码中添加的View的计算方法
适配虚拟按键，包括华为可变的虚拟按键
理论上在任何大小屏幕上的显示比例都是一样的
