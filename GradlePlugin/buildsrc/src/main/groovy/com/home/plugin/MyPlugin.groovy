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