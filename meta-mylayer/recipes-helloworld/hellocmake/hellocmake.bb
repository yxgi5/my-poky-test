DESCRIPTION = "Hello World program built with CMake"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# 把整个 hellocmake 目录当源码
SRC_URI = "file://hellocmake"

S = "${WORKDIR}/hellocmake"

inherit cmake

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/hellocmake ${D}${bindir}
}

