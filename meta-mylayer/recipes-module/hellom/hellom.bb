SUMMARY = "Example of how to build an external Linux kernel module"
DESCRIPTION = "${SUMMARY}"
# LICENSE = "GPL-2.0-only"
LICENSE = "MIT"
#注意下面的license校验，如果报错的话，请去其他meta目录下搜素COMMON_LICENSE_DIR变量，并将后面的md5值填入下面即可
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "file://Makefile \
           file://hellom.c \
          "

#S = "${WORKDIR}"
#S = "${WORKDIR}/hellom"
S = "${UNPACKDIR}"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.

# 注意下面的名字，后续要让模块加到整个系统构建就需要使用
RPROVIDES:${PN} += "kernel-module-hellom"

# KERNEL_MODULE_AUTOLOAD += " hellom"

# 这样也是有效的, 注意冒号和 append
KERNEL_MODULE_AUTOLOAD:append = " hellom"  

