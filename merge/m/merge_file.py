# coding=utf-8
import xml.dom.minidom as xdom
import os
import shutil
import sys


def mergeXMLDocuments(inputfiles, outputfile):
    overal_content_dic = {}
    for files in inputfiles:
        doc = xdom.parse(files)
        collection = doc.documentElement
        attr_tags = collection.getElementsByTagName("attr")
        for attr in attr_tags:
            key = "%s_%s" % (attr.nodeName, attr.getAttribute('name'))
            overal_content_dic[key] = attr

    impl = xdom.getDOMImplementation()
    dom = impl.createDocument(None, 'resources', None)
    dom.actualEncoding = 'utf-8'
    root = dom.documentElement

    for key, value in overal_content_dic.items():
        root.appendChild(value)
    if outputfile is None:
        return dom
    else:
        f = open(outputfile, 'w')
        f.write('<?xml version="1.0" encoding="utf-8"?>' + root.toprettyxml(encoding="utf-8"))
        print 'xml document merge completed'
        f.close()
        return dom


def get_apk_dir_resource_path(res):
    """获取apk目录下的相对资源路径"""
    apk_dir = sys.argv[1]
    return '%s/%s' % (apk_dir, res)


if __name__ == '__main__':
    # root_path = '.'

    apk_dir = sys.argv[1]

    origin_res = get_apk_dir_resource_path('res')
    # 母包资源备份目录
    tmp_origin_res_path = get_apk_dir_resource_path('tmp_origin')

    origin_values_path = '%s/values' % (origin_res)
    origin_attr_path = '%s/attrs.xml' % origin_values_path

    sdk_res = get_apk_dir_resource_path('ForRes')  # sdk资源目录
    # sdk资源备份
    tmp_sdk_res_path = get_apk_dir_resource_path('tmp_sdk')
    tmp_sdk_attr_path = '%s/values/attrs.xml' % tmp_sdk_res_path

    if os.path.exists(tmp_origin_res_path):
        shutil.rmtree(tmp_origin_res_path)

    if os.path.exists(tmp_sdk_res_path):
        shutil.rmtree(tmp_sdk_res_path)

    # 将母包资源复制到tmp_origin
    shutil.copytree(origin_res, tmp_origin_res_path)
    # 将sdk资源复制到tmp_sdk
    shutil.copytree(sdk_res, tmp_sdk_res_path)

    input_files = [origin_attr_path, tmp_sdk_attr_path]
    output_file = origin_attr_path
    # 两边都存在attrs.xml时才进行整合覆盖
    if os.path.exists(origin_attr_path) and os.path.exists(tmp_sdk_attr_path):
        mergeXMLDocuments(input_files, output_file)
        # 删除sdk中attrs资源，防止后面资源整合时重复定义
        os.remove(tmp_sdk_attr_path)

    origin_public_xml_path = '%s/public.xml' % origin_values_path
    if os.path.exists(origin_public_xml_path):
        os.remove(origin_public_xml_path)

    merge_res_file_name = get_apk_dir_resource_path('merge_res.apk')

    merge_res_sh = './m/aapt package -f -S %s -S %s -M ./m/AndroidManifest.xml --auto-add-overlay  -I ./m/android.jar -I ./m/constraint-layout-1.0.2.aar -F %s' % (
            origin_res, tmp_sdk_res_path, merge_res_file_name)

    print merge_res_sh

    decode_apk_sh = 'java -jar ./m/apktool.jar d %s -o %s/merge_res' % (merge_res_file_name, apk_dir)

    # 资源整合覆盖命令
    res_state = os.system('%s && %s' % (merge_res_sh, decode_apk_sh))

    print res_state
    if res_state == 1 :
        print '资源整合错误'
    else:
        # 获取到整合的资源
        # decode_res_apk = os.system('java -jar apktool.jar d %s' % merge_res_file_name)

        merge_res_path = get_apk_dir_resource_path('merge_res/res')

        # 将整合的资源替换到res中  之后进行回编译整合后的母包
        shutil.rmtree(origin_res)
        shutil.copytree(merge_res_path, origin_res)
        # 删除整合的资源文件
        os.remove(merge_res_file_name)
        shutil.rmtree(get_apk_dir_resource_path('merge_res'))

        # 当前目录名称
        base_dir_name = os.path.split(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
        print base_dir_name
        encode_apk_sh = 'java -jar ./m/apktool.jar b %s' % apk_dir
        print encode_apk_sh
        os.system(encode_apk_sh)

        # 将整合后的res替换回之前原母包资源
        shutil.rmtree(origin_res)
        shutil.copytree(tmp_origin_res_path, origin_res)
        shutil.rmtree(tmp_sdk_res_path)
        shutil.rmtree(tmp_origin_res_path)
        print 'operator success'
