###app bundle编译
- bundletool下载:
https://github.com/google/bundletool/releases

注意点：
**请勿使用 Android 构建工具包中包含的 AAPT2 版本，因为它不支持 bundletool**
可以到此地址找对应版本名称:
https://maven.google.com/web/index.html#com.android.tools.build:aapt2
替换下面下载地址的名称(aapt2-3.2.0-alpha18-4804415-windows)
https://dl.google.com/dl/android/maven2/com/android/tools/build/aapt2/3.2.0-alpha18-4804415/aapt2-3.2.0-alpha18-4804415-windows.jar



解压下载下来的aapt2对应的Jar包，使用里面的aapt2
aapt2 compile资源
aapt2 compile /Users/guangka123/Documents/test/app_bundle/aapt2/res/image1.png /Users/guangka123/Documents/test/app_bundle/aapt2/res/image2.png -o compiled_resources

# 要加drawable路径，不然不成功
aapt2 compile /Users/guangka123/Documents/test/app_bundle/aapt2/res/drawable/image1.png -o compiled_resources

# 编译整个目录
aapt2 compile --dir ./oumei/res/ -o omRes.zip

# link 要加上--proto-format，后面要将AndroidManifest,res，resources.pb资源放进base.zip中
aapt2 link --proto-format -o output.apk -I android.jar --manifest ./AndroidManifest.xml -R ./compiled_resources/*.flat --auto-add-overlay


# aapt2 link (res,assets,AndroidManifest.xml资源)出apk包
aapt2 link --proto-format -o output.zip -I android.jar -A ./oumei/assets --manifest ./oumei/AndroidManifest.xml -R ./omRes/*.flat --auto-add-overlay

- 将需要的资源整合进base.zip  base.zip结构如下
manifest/AndroidManifest.xml	模块的清单，采用 protobuf 格式。
dex/...	此目录包含应用的一个或多个经过编译的 DEX 文件。这些文件应按如下方式命名：classes.dex、classes2.dex、classes3.dex，依此类推。
res/...	包含模块的资源，这些资源采用 protobuf 格式，适用于所有设备配置。子目录和文件的组织方式应与典型的 APK 类似。
root/...、assets/...和lib/...	这些目录与关于 Android App Bundle 格式的部分中介绍的目录完全相同。
resources.pb	应用的资源表，采用 protobuf 格式

- 生成aab
# output路径要用绝对路径，不然无效
java -jar bundletool-all-1.4.0.jar build-bundle --modules=base.zip --output=/Users/guangka123/Documents/test/app_bundle/aapt2/myBundle.aab
# output路径要用绝对路径，不然无效
java -jar bundletool-all-1.4.0.jar build-bundle --modules=mybase.zip --output=/Users/guangka123/Documents/test/app_bundle/aapt2/first.aab
 
Google官方文档：
https://developer.android.com/studio/build/building-cmdline#build_bundle
