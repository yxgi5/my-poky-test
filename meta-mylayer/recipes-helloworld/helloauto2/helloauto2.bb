DESCRIPTION = "Hello C program built with Autotools (main.c + helloauto.c)"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# 使用本地源码子目录
SRC_URI = "file://helloauto2-src"

# 禁止 checksum 校验（适合本地测试）
SRC_URI[md5sum] = "00000000000000000000000000000000"
SRC_URI[sha256sum] = "0000000000000000000000000000000000000000000000000000000000000000"

# 指向解压后的源码目录
S = "${WORKDIR}/helloauto2-src"

# 继承 Autotools 类
inherit autotools pkgconfig

# 新版 BitBake 直接启用 autoreconf
AUTORECONF = "1"

# 安装到目标文件系统
do_install() {
    oe_runmake install DESTDIR=${D}
}

# 可选依赖
DEPENDS = "pkgconfig"

