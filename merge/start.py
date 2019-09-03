# -*- coding: utf-8 -*-
# @Time    : 2019-09-03 15:05
# @Author  : xgh
# @File    : start
# @Software: PyCharm
# @Desc    : 

import os
import sys
import shutil


def decode_apk(apk_name):
    decode_apk_sh = 'java -jar ./m/apktool.jar d %s.apk -f' % apk_name
    return os.system(decode_apk_sh)


def copy_res_2_dir(to_dir):
    """复制ForRes到反编译出来的apk目录中"""
    shutil.copytree('ForRes', '%s/ForRes' % to_dir)


if __name__ == '__main__':
    args = sys.argv
    if len(args) < 2:
        print '请输入apk文件名...'
    else:
        apk_name = sys.argv[1]
        state = decode_apk(apk_name)
        if state == 1:
            print '反编译apk出错...'
        else:
            copy_res_2_dir(apk_name)
            os.system('python ./m/merge_file.py %s' % apk_name)
