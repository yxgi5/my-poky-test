DESCRIPTION = "Simple Hello World 2 example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = " \ 
    file://main.c \
    file://helloworld.c \
    file://Makefile \
    "

#S = "${WORKDIR}"
#S = "${WORKDIR}/helloworld"
#S = "${UNPACKDIR}"

S = "${WORKDIR}/helloworld"
UNPACKDIR = "${S}"

# inherit autotools
# inherit autotools-brokensep

# 使用 oe_runmake 调用 Makefile
#do_compile() {
#    oe_runmake
#}

do_install() {
    oe_runmake DESTDIR=${D}/${bindir} install
}

RDEPENDS:${PN} += "helloworld2"
