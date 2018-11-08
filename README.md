# autolayout
autolayout不更新了，在项目中用到了，维护了一下

CSDN：https://blog.csdn.net/qq_39154578/article/details/83862602

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


有问题可联系QQ 54751455
