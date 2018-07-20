##针对当前项目的Gradle插件
####1、删除build.gradle里面的内容，添加下面内容
```
apply plugin: 'groovy'
dependencies { 
    compile gradleApi()//gradle sdk
    compile localGroovy()//groovy sdk
}
```

####2、创建一个Java library 项目命名为BuildSrc，删除src/main下所有目录文件

- 在main 目录下创建groovy目录，创建包名com.home.plugin,创建MyPlugin.groovy 内容如下
```
package com.home.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project

public class MyPlugin implements Plugin<Project> {
    void apply(Project project) {
        System.out.println("========================");
        System.out.println("这是个插件!");
        System.out.println("========================");
    }
}
```
- 在main目录下创建resources目录然后在resources目录里面再新建META-INF目录，<br>
    再在META-INF里面新建gradle-plugins目录。最后在gradle-plugins目录里面新建properties文件，<br>
    注意这个文件的命名，你可以随意取名，但是后面使用这个插件的时候，会用到这个名字。<br>
    比如，你取名为com.home.plugin.properties，而在其他build.gradle文件中使用自定义的插件时候则需写成：<br>
    apply plugin: 'com.home.plugin'
    <br>com.home.plugin.properties文件内容为：implementation-class=com.home.plugin.MyPlugin
    
- 工程目录结构如下：

    
####3、在app下的build.gradle添加引用 apply plugin: 'com.home.plugin'

参考连接：http://kvh.io/cn/tags/EmbraceAndroidStudio/