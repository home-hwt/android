、、、
  aapt package简单说明：
  -f 如果编译出来的文件已经存在，强制覆盖。
  -m 使生成的包的目录放在-J参数指定的目录。
  -J 指定生成的R.Java的输出目录
  -S res文件夹路径(可以添加多个资源)
  -A assert文件夹的路径
  -M AndroidManifest.xml的路径
  -I 某个版本平台的android.jar/依赖库的路径
  -F 具体指定apk文件的输出
  将工程的资源编译R.java文件
  aapt package -m -J <R.java目录> -S <res目录> -I <android.jar目录>  -M <AndroidManifest.xml目录>
  将工程的资源编译编译到一个包里
  aapt package -f  -S <res目录> -I <android.jar目录> -A<assert目录>  -M <AndroidManifest.xml目录> -F <输出的包目录>

  多资源整合例子：
  res:资源目录
  ForRes:资源目录
  ./aapt package -f -S res -S ForRes -M AndroidManifest.xml --auto-add-overlay  -I android.jar -F ./myres.apk
  两个资源整合进myres.apk

  --auto-add-overlay：整合覆盖重复资源  res覆盖ForRes资源
  value下的attrs.xml不会自动整合覆盖,需要另外处理

  依赖其它库资源例子：
  使用constraint-layout-1.0.2.aar依赖库(-I为添加依赖库)
  aapt package -f -S res -S ForRes -M AndroidManifest.xml --auto-add-overlay  -I android.jar -I constraint-layout-1.0.2.aar -F ./myres.apk
  当引用v7/v4等其它库的资源时，使用aapt编译时需要添加为依赖基础库，否则会出现以下错误
  res/values-v26/styles.xml:14: error: Error: No resource found that matches the given name: attr 'android:keyboardNavigationCluster
、、、
