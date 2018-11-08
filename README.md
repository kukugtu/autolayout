# autolayout
autolayout不更新了，在项目中用到了，维护了一下

原博客   https://blog.csdn.net/lmj623565791/article/details/49990941
原git地址  https://github.com/hongyangAndroid/AndroidAutoLayouthttps://github.com/hongyangAndroid/AndroidAutoLayout

使用前请参考原博客，下边的留言问题有很大一部分都在这里解决了

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
         implementation 'com.github.kukugtu:autolayout:1.3'
 }


解决了控件变形问题，适配全面屏。
提供了代码中添加的View的计算方法
适配虚拟按键，包括华为可变的虚拟按键
理论上在任何大小屏幕上的显示比例都是一样的


总体还是原来autolayout的思路，只是原项目不维护了，现在维护一下解决了平时项目中遇到的大部分问题。
有问题可联系QQ 54751455
