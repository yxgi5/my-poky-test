DESCRIPTION = "Simple Hello World example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#SRC_URI = "file://helloworld.c"
SRC_URI = "file://helloworld"

#S = "${WORKDIR}"
#S = "${WORKDIR}/helloworld"
#S = "${UNPACKDIR}"

#S = "${WORKDIR}/helloworld"
#UNPACKDIR = "${S}"

S = "${UNPACKDIR}/helloworld"


# inherit autotools

do_compile() {
   ${CC} ${CFLAGS} ${LDFLAGS} -o helloworld helloworld.c
}

do_install() {
   install -d ${D}${bindir}
   install -m 0755 helloworld ${D}${bindir}
}

#RDEPENDS:${PN} += "helloworld"
