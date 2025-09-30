DESCRIPTION = "My custom image with helloworld"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#require recipes-core/images/core-image-sato.bb
require recipes-sato/images/core-image-sato.bb
# 告诉 bitbake 这是一个镜像配方
inherit core-image

IMAGE_INSTALL:append = " helloworld"
IMAGE_INSTALL:append = " helloworld2"
IMAGE_INSTALL:append = " hellocmake"
IMAGE_INSTALL:append = " helloauto"
IMAGE_INSTALL:append = " helloauto2"
IMAGE_INSTALL:append = " hellom"
#可以写成一行
#IMAGE_INSTALL:append = " helloworld helloworld2 hellocmake helloauto helloauto2 hellom"

# 在这并没有效果
#KERNEL_MODULE_AUTOLOAD += " hellom"  
# 注意冒号和 append, 在这并没有效果
#KERNEL_MODULE_AUTOLOAD:append = " hellom"  
