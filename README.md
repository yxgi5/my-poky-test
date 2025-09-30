imx和petalinux都基于yocto来构建开发环境, 有必要弄清楚yocto的一些基本概念和使用

---
# 顶置几个参考文档链接

<https://docs.yoctoproject.org/_static/theyoctoproject.pdf>

<https://www.yoctoproject.org/development/releases>

<https://buildroot.org/docs.html>

---
# 本机 host 环境
```
$ lsb_release -a
LSB Version:	core-11.1.0ubuntu4-noarch:printing-11.1.0ubuntu4-noarch:security-11.1.0ubuntu4-noarch
Distributor ID:	Ubuntu
Description:	Ubuntu 22.04.1 LTS
Release:	22.04
Codename:	jammy
```


<https://docs.yoctoproject.org/ref-manual/system-requirements.html#required-git-tar-python-make-and-gcc-versions>

<https://git.yoctoproject.org>

```
sudo apt install gawk wget git diffstat unzip texinfo gcc build-essential chrpath socat cpio python3 python3-pip python3-pexpect xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev pylint xterm python3-subunit mesa-common-dev zstd liblz4-tool
sudo apt install build-essential chrpath cpio debianutils diffstat file gawk gcc git iputils-ping libacl1 liblz4-tool locales python3 python3-git python3-jinja2 python3-pexpect python3-pip python3-subunit socat texinfo unzip wget xz-utils zstd efitools

sudo apt remove oss4-dev

locale --all-locales | grep en_US.utf8

sudo apt install git librsvg2-bin locales make python3-saneyaml python3-saneyaml python3-sphinx-rtd-theme sphinx

下面这些目前没有必要
//sudo apt install fonts-freefont-otf latexmk tex-gyre texlive-fonts-extra texlive-fonts-recommended texlive-lang-all texlive-latex-extra texlive-latex-recommended texlive-xetex
```



---
# qemu 运行命令笔记

<https://www.qemu.org>

<https://www.qemu.org/docs/master/>

```
qemu-system-x86_64
 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02
 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no
 -object rng-random,filename=/dev/urandom,id=rng0
 -device virtio-rng-pci,rng=rng0
 -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250924030647.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd
 -cpu IvyBridge -machine q35,i8042=off -smp 4
 -m 512 -serial mon:vc
 -serial null -display none
 -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage
 -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '


qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250924030647.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu core2duo -m 512 -serial mon:vc -serial null -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '


qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250924030647.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu core2duo -m 512 -serial mon:vc -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '


qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -display default,show-cursor=on -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250924030647.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu core2duo -m 512 -serial mon:vc -serial null -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '


qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -display default,show-cursor=on -vga qxl -enable-kvm -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250924030647.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu IvyBridge -machine q35 -smp 4 -m 512 -serial mon:vc -serial null -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '


qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -display default,show-cursor=on -vga qxl -enable-kvm -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250924030647.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu core2duo -m 512 -serial mon:vc -serial null -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '


```


---
# yocto 有关参考文档

<https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html>

<https://docs.yoctoproject.org/overview-manual/development-environment.html>

<https://docs.yoctoproject.org/bitbake/2.12/>


---
# 下载慢? 一组proxy加速办法

修改下面几个变量
```
FETCHCMD_git
FETCHCMD_svn
FETCHCMD_wget
BB_ENV_PASSTHROUGH_ADDITIONS
```

具体应该是(未测试)
```
export FETCHCMD_wget="/usr/bin/env /usr/bin/tsocks wget -t 2 -T 30 --passive-ftp --no-check-certificate"
export BB_ENV_EXTRAWHITE="FETCHCMD_wget"

FETCHCMD_git="/usr/bin/env /usr/bin/proxychains git co ${SVNCOOPTS} ${SVNROOT}"        #? https://www.mail-archive.com/yocto@yoctoproject.org/msg09379.html
FETCHCMD_svn="/usr/bin/env /usr/bin/proxychains svn -d${CVSROOT} co ${CVSCOOPTS}"        #?
export FETCHCMD_wget="/usr/bin/env /usr/bin/proxychains wget -t 2 -T 30 --passive-ftp --no-check-certificate"
export BB_ENV_PASSTHROUGH_ADDITIONS="$BB_ENV_PASSTHROUGH_ADDITIONS FETCHCMD_wget FETCHCMD_git FETCHCMD_svn"
```

---
# yocto中几个常见变量的意义

${KERNELORG_MIRROR}: 通常这个变量在 Yocto 的配置文件或者环境变量中定义，常见的值可能是 https://kernel.org/pub。
${BPN}: 这是 BitBake 的包名变量，对于 linux-firmware，这个值应该就是 linux-firmware。
${PV}: 这是包的版本，版本应该是 20230625。
${THISDIR}: 表示bb文件所在路径



---
# 拉取仓库
```
$ git clone git://git.yoctoproject.org/poky
$ cd poky
$ git fetch --tags
$ git checkout -t origin/walnascar -b my-walnascar      # 不一定需要 checkout 这个分支, 但是 master (whinlatter) 分支 镜像需要 snapshot 模式来运行, 需要注意
$ git pull
$ source oe-init-build-env
```

其他分支, 没啥鸡巴必要尝试
```
git checkout -t origin/honister -b my-honister
git checkout -t origin/kirkstone -b my-kirkstone #选择kirstone分支
```

---
# 进行一些配置

## downloads 和 sstate-cache 目录位置重定义

方法1:

最好把downloads目录放到top,再软链接进来,最方便

方法2:
编辑conf/local.conf⽂件添加:
```
DL_DIR = "${COREBASE}/downloads"
SSTATE_DIR = "${COREBASE}/sstate-cache"
```
?= 表示 如果没定义过才赋值，而 Yocto 的 DL_DIR 已经在 meta/conf/bitbake.conf 里定义过了（默认就是 ${TOPDIR}/downloads）

方法3:  最好
编辑conf/local.conf⽂件添加:
```
DL_DIR = "${TOPDIR}/../downloads"
SSTATE_DIR = "${TOPDIR}/../sstate-cache"
```
用 TOPDIR 相对路径控制下载和缓存位置，更灵活，更安全。


tips
```
${COREBASE} 指向 poky 的根目录，例如 my-poky-work/poky。
不建议用它来存放 downloads 或 sstate-cache，因为你可能不想污染 poky 源码。
${TOPDIR} 指向当前 build 目录，例如 my-poky-work/build。
建议基于 TOPDIR 放 downloads 和 sstate-cache，这样 build 目录自包含。
```

## 编译和运行
```
$ bitbake core-image-sato

$ runqemu qemux86-64
$ runqemu qemux86-64 nographic
$ runqemu qemux86-64 qemuparams="-enable-kvm"       
$ runqemu qemux86-64 kvm                            # 经常用这个
$ runqemu qemux86-64 core-image-sato ext4 qemuparams="-enable-kvm"
$ runqemu qemux86-64 core-image-sato ext4 kvm
```

## 目录结构规划
后面的内容都按原始 poky为根目录进行.  但是最终应这样

```
my-poky-work/
├── poky/             # 原始 poky
├── meta-mylayer/     # 自定义 layer
└── build/            # 自定义 build/conf
```

按上面的目录规划, 这样加载环境变量
```
cd my-poky-work/poky
source oe-init-build-env ../build
```

## 版本管理方案

最简单就是在poky添加自己的分支
```
git fetch upstream
git checkout my-mods
git merge upstream/master   # 或者 upstream/main
```

最好 把 poky 作为子目录或子模块
```
git init
git submodule add git://git.yoctoproject.org/poky.git
git submodule init
git submodule update
git add meta-mylayer build
git commit -m "Add custom layer and image"
```


## 可以直接用本地 qemu 来运行镜像
```
/home/andy/Downloads/poky/build/tmp/work/x86_64-linux/qemu-helper-native/1.0/recipe-sysroot-native/usr/bin/qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250926073815.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu IvyBridge -machine q35,i8042=off -smp 4 -m 512 -serial mon:vc -serial null -device virtio-vga  -display sdl,show-cursor=on  -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '

qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -display default,show-cursor=on -vga qxl -enable-kvm -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250926073815.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu core2duo -m 512 -serial mon:vc -serial null -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '

上面两个都不好使


qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -enable-kvm -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -drive file=/home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250926073815.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd -cpu core2duo -m 512 -serial mon:vc -serial null -device virtio-vga  -display sdl,show-cursor=on -kernel /home/andy/Downloads/poky/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '
```

查询本地 QEMU 支持的 CPU 类型
```
qemu-system-x86_64 -cpu help
```
确认
```
-cpu IvyBridge -smp 4 -m 512 -machine q35
```
是可以用的


---
# 创建工作 layer

```
cat conf/bblayers.conf

bitbake-layers create-layer ../meta-mylayer # 注意这里是在build目录下执行
bitbake-layers add-layer ../meta-mylayer    # 注意这里是在build目录下执行

cat conf/bblayers.conf
```

---
# 添加内核模块 hellom.ko

前期调试模块时候使用内核源码之外编译模块的方式比较方便

## 创建 recipes
```
cat ../meta-mylayer/conf/layer.conf
cd ../meta-mylayer/
mkdir recipes-module
cd recipes-module/
```

## 创建 module
```
mkdir -p hellom/files
touch hellom/hellom.bb
touch hellom/files/hellom.c
touch hellom/files/Makefile
```

hellom.c

```
#include <linux/init.h>
#include <linux/module.h>

static int __init hellom_init(void)
{
	pr_info("Hello, kernel module loaded!\n");
	return 0;
}

static void __exit hellom_exit(void)
{
	pr_info("Goodbye, kernel module unloaded!\n");
}

module_init(hellom_init);
module_exit(hellom_exit);
MODULE_LICENSE("GPL");
MODULE_AUTHOR("andreas");
MODULE_DESCRIPTION("Simple Hello Module");

```
Makefile

```
obj-m := hellom.o

SRC := $(shell pwd)

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC)

modules_install:
	#$(MAKE) -C $(KERNEL_SRC) M=$(SRC) INSTALL_MOD_PATH=$(DESTDIR) modules_install
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	rm -f *.o *~ core .depend .*.cmd *.ko *.mod.c
	rm -f Module.markers Module.symvers modules.order
	rm -rf .tmp_versions Modules.symvers

```

hellom.bb

```
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
#KERNEL_MODULE_AUTOLOAD:append = " hellom"  
```


方法1

在poky/meta/conf/machine/qemux86-64.conf      # 在hellom.bb文件加 `MACHINE_EXTRA_RRECOMMENDS += "kernel-module-hello"` 没有用

找到MACHINE_EXTRA_RRECOMMENDS字段，在其尾部追加` kernel-module-hello`


方法2

`build/conf/local.conf` 里添加

```
MACHINE_EXTRA_RRECOMMENDS:append = " kernel-module-hello"
```

方法3

qemux86-64.conf.append并且调整layer顺序

tip
```
由于默认layer顺序问题

在
poky/meta-mylayer/conf/machine/qemux86-64.conf.append
给出
MACHINE_EXTRA_RRECOMMENDS += " kernel-module-hello"
是不生效的
```

方法4: 最好

最好不用MACHINE_EXTRA_RRECOMMENDS, 直接在poky/meta-mylayer/recipes-core/images/core-image-sato.bbappend添加

```
IMAGE_INSTALL:append = " hello"
```
就可以把模块打包进fs


tips: yocto支持4中方式在image中添加模块
```
MACHINE_ESSENTIAL_EXTRA_RDEPENDS
MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS
MACHINE_EXTRA_RDEPENDS
MACHINE_EXTRA_RRECOMMENDS
```

<https://docs.yoctoproject.org/ref-manual/variables.html#term-MACHINE_EXTRA_RRECOMMENDS>




## 编译

```
cd poky/build
bitbake core-image-sato
```

检查是否产生了
```
poky/build/tmp/work/qemux86_64-poky-linux/core-image-sato/1.0/rootfs/lib/modules/6.12.47-yocto-standard/updates/hello.ko
```

运行
```
runqemu qemux86-64 qemuparams="-enable-kvm"
or
runqemu qemux86-64 core-image-sato qemuparams="-enable-kvm"
```


qemu里面
```
$ modinfo hello
$ modprobe hello
$ dmesg
$ dmesg | grep Hello
```
最后显示Hello World!


## hellom.ko 的自动加载

如果需要让hello模块自动加载，在hellom.bb文件或meta/conf/machine/qemux86-64.conf中添加以下行：
```
KERNEL_MODULE_AUTOLOAD += "hello"
```
然后编译
```
bitbake -c cleanall core-image-sato
bitbake core-image-sato

```

其实就是在在 rootfs 里生成 `/etc/modules-load.d/hello.conf`，开机自动加载
```
poky/build/tmp/work/qemux86_64-poky-linux/core-image-sato/1.0/rootfs/etc/modules-load.d/
```

运行镜像

```
runqemu qemux86-64 qemuparams="-enable-kvm"
```

qemu里面,不需要 modprobe hello
```
$ dmesg | grep Hello
```
最后显示Hello World!



## 清理缓存
删除该 recipe 的所有工作目录和生成产物
```
bitbake -c clean <recipe> 
```
某个 recipe 出错	`bitbake -c cleansstate <recipe> ` 再重编
```
bitbake -c cleansstate python3-markupsafe-native
```
想完全重做镜像	
```
bitbake -c cleanall core-image-sato
```

有时 bitbake cache 里还留着旧的配置，可以清理：
```
bitbake -p
rm -rf build/cache
```

例子
```
bitbake -c cleanall hellom
bitbake -c cleanall my-image
bitbake my-image
```

bitbake -c clean 和 bitbake -c cleanall 的区别
```
bitbake -c clean <recipe>
删除 ${WORKDIR} 下该配方的构建产物
不会删除下载的源码包（${DL_DIR}）
不会删除 sstate 缓存（${SSTATE_DIR}）。


bitbake -c cleanall <recipe>
删除 ${WORKDIR} 下的构建产物。
删除 ${DL_DIR} 下下载的源码。
删除 ${SSTATE_DIR} 中该配方对应的缓存。
```

---
# 添加内核源码模块 hello

模块调试好了一般都是随内核源码一块编译并插入系统的, 下面讲解这种编译模块的方法

tips

如果相同模块名的ko自动加载了那么就先取消hello.bb, 比如 ../meta-mylayer/recipes-module/hello/hello.bb
```
# KERNEL_MODULE_AUTOLOAD += "hello"
```

## 查看linux源码包全称
```
$ bitbake -s | grep linux 
binutils-crosssdk-x86_64-pokysdk-linux                  :2.44-r0                                                    
core-image-ptest-util-linux                           :1.0-r0                                                    
cryptodev-linux                                      :1.14-r0                                                    
cryptodev-linux-native                               :1.14-r0                                                    
gcc-crosssdk-x86_64-pokysdk-linux                  :14.3.0-r0                                                    
go-crosssdk-x86_64-pokysdk-linux                   :1.24.6-r0                                                    
linux-firmware                                  1:20250311-r0                                                    
linux-libc-headers                                   :6.12-r0                                                    
linux-yocto                                   :6.12.47+git-r0                                                    
nativesdk-cryptodev-linux                            :1.14-r0                                                    
nativesdk-linux-libc-headers                         :6.12-r0                                                    
nativesdk-syslinux                              :6.04-pre2-r0                                                    
nativesdk-util-linux                               :2.40.4-r0                                                    
nativesdk-util-linux-libuuid                       :2.40.4-r0                                                    
syslinux                                        :6.04-pre2-r0                                                    
syslinux-native                                 :6.04-pre2-r0                                                    
util-linux                                         :2.40.4-r0                                                    
util-linux-libuuid                                 :2.40.4-r0                                                    
util-linux-libuuid-native                          :2.40.4-r0                                                    
util-linux-native                                  :2.40.4-r
```
可以看到linux内核源码包全称为linux-yocto

## 借助devtool工具提取内核源码
```
$ devtool modify linux-yocto 
NOTE: Starting bitbake server...
INFO: Creating workspace layer in /home/andy/Downloads/poky/build/workspace
NOTE: Reconnecting to bitbake server...
Loading cache: 100% |                                                                                                                                                      | ETA:  --:--:--
Loaded 0 entries from dependency cache.
Parsing recipes: 100% |#####################################################################################################################################################| Time: 0:00:11
Parsing of 925 .bb files complete (0 cached, 925 parsed). 1886 targets, 49 skipped, 0 masked, 0 errors.
NOTE: Resolving any missing task queue dependencies

Build Configuration:
BB_VERSION           = "2.12.1"
BUILD_SYS            = "x86_64-linux"
NATIVELSBSTRING      = "universal"
TARGET_SYS           = "x86_64-poky-linux"
MACHINE              = "qemux86-64"
DISTRO               = "poky"
DISTRO_VERSION       = "5.2.4"
TUNE_FEATURES        = "m64 core2"
TARGET_FPU           = ""
meta                 
meta-poky            
meta-yocto-bsp       
meta-mylayer         
workspace            = "my-walnascar:d0b46a6624ec9c61c47270745dd0b2d5abbe6ac1"

Sstate summary: Wanted 0 Local 0 Mirrors 0 Missed 0 Current 236 (0% match, 100% complete)####################################################################               | ETA:  0:00:00
Initialising tasks: 100% |##################################################################################################################################################| Time: 0:00:00
NOTE: Executing Tasks
NOTE: Tasks Summary: Attempted 790 tasks of which 790 didn't need to be rerun and all succeeded.
INFO: Copying kernel config to workspace
INFO: Recipe linux-yocto now set up to build from /home/andy/Downloads/poky/build/workspace/sources/linux-yocto
```

上面最后一行就是linux内核源码提取后所在目录
```
poky/build/workspace/sources/linux-yocto
```

更多关于devtool工具的使用方法，可参阅

<https://docs.yoctoproject.org/ref-manual/devtool-reference.html>

```
poky/build/workspace
$ tree -L 2
.
├── appends
│   └── linux-yocto_6.12.bbappend
├── conf
│   └── layer.conf
├── README
└── sources
    └── linux-yocto
```

## 添加 workspace 到 layer
```
$ bitbake-layers add-layer workspace
```

```
$ cat conf/bblayers.conf
# POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
# changes incompatibly
POKY_BBLAYERS_CONF_VERSION = "2"

BBPATH = "${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \
  /home/andy/Downloads/poky/meta \
  /home/andy/Downloads/poky/meta-poky \
  /home/andy/Downloads/poky/meta-yocto-bsp \
  /home/andy/Downloads/poky/meta-mylayer \
  /home/andy/Downloads/poky/build/workspace \
```

## 添加模块代码
```
cd build/workspace/sources/linux-yocto/drivers/char
mkdir hello
cd hello
touch hello.c Kconfig Makefile
```

hello.c
```
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/init.h>

static int __init init_hello( void )
{
	printk("this is my first driver module:    Hello world!\n");
	return 0;//必须返回0
}

static void __exit exit_hello( void )
{
	printk("this is my first driver module:    Goodbey world!\n");
}

module_init(init_hello);
module_exit(exit_hello);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("caodongwang");

```

Kconfig
```
#include <linux/kernel.h>
#include <linux/module.h>
#include <linux/init.h>

static int __init init_hello( void )
{
	printk("this is my first driver module:    Hello world!\n");
	return 0;//必须返回0
}

static void __exit exit_hello( void )
{
	printk("this is my first driver module:    Goodbey world!\n");
}

module_init(init_hello);
module_exit(exit_hello);

MODULE_LICENSE("GPL");
MODULE_AUTHOR("caodongwang");

```

Makefile
```
obj-$(CONFIG_HELLO) += hello.o

```


修改`build/workspace/sources/linux-yocto/drivers/char`目录下的Kconfig文件, 在尾部的endmenu之前添加以下
```
source "drivers/char/hello/Kconfig"
```

修改`build/workspace/sources/linux-yocto/drivers/char`目录下的Makefile文件，在尾部添加以下
```
#注意后面有个 / 符号
obj-$(CONFIG_HELLO) += hello/
```

## 配置内核
返回到yocto顶层目录下执行
```
$ source oe-init-build-env

build$ bitbake virtual/kernel -c menuconfig
```
在Device Drivers->Character devices中找到Create a my hello module选项勾上，保存退出。


## 编译

先单独编译一下linux内核
```
build$ devtool build linux-yocto
```
没问题后，编译整个image 
```
# devtool build <recipe> will use the changes in workdir, while bitbake <recipe> will ignore them
build$ devtool build-image core-image-sato
```

## 运行起来看看
```
build$ runqemu qemux86-64 qemuparams="-enable-kvm"
```

qemu的shell
```
dmesg | grep Hello  #注意Hello首字母是大写
```
最后显示Hello World!


## 出错时的复位方法
如果此时workspace中代码已改乱，或者其他问题，可以通过：
```
devtool reset linux-yocto
```
然后删除源码目录，注意备份之前的修改：
```
rm -rf workspace/sources/linux-yocto
```

## 制作补丁
平常维护代码时（如代码上传到git上），往往都是以补丁形式维护
```
cd build/workspace/sources/linux-yocto

$ git status 
Refresh index: 100% (86883/86883), done.
On branch v6.12/standard/base
Your branch is behind 'origin/v6.12/standard/base' by 144 commits, and can be fast-forwarded.
  (use "git pull" to update your local branch)

Changes not staged for commit:
  (use "git add <file>..." to update what will be committed)
  (use "git restore <file>..." to discard changes in working directory)
	modified:   drivers/char/Kconfig
	modified:   drivers/char/Makefile

Untracked files:
  (use "git add <file>..." to include in what will be committed)
	drivers/char/hello/

no changes added to commit (use "git add" and/or "git commit -a")
```
添加并提交
```
git add drivers/char/hello/

git add drivers/char/Kconfig drivers/char/Makefile

可以 git add ./*

git commit -m "add my hello module"

$ git commit -m "add my hello module"
[v6.12/standard/base 03e272f9ed71] add my hello module
 5 files changed, 35 insertions(+)
 create mode 100644 drivers/char/hello/Kconfig
 create mode 100644 drivers/char/hello/Makefile
 create mode 100644 drivers/char/hello/hello.c
```


产生patch
```
devtool update-recipe [-a <layerpath>] <recipename>
```

```
$ devtool update-recipe linux-yocto
NOTE: Starting bitbake server...
Loading cache: 100% |#######################################################################################################################################################| Time: 0:00:00
Loaded 1886 entries from dependency cache.
Parsing recipes: 100% |#####################################################################################################################################################| Time: 0:00:01
Parsing of 925 .bb files complete (924 cached, 1 parsed). 1886 targets, 49 skipped, 0 masked, 0 errors.
INFO: Adding new patch 0001-add-my-hello-module.patch   (meta/recipes-kernel/linux/linux-yocto/)
INFO: Updating recipe linux-yocto_6.12.bb               (meta/recipes-kernel/linux/linux-yocto_6.12.bb)
```


就算在build/workspace/conf/layer.conf
```
UPSTREAM_CHECK_COMMITS = "1"
```
对于使用补丁时候`Missing Upstream-Status in patch`报错也没啥用



## finish并产生patch
```
$ cd build
build$ devtool finish --force-patch-refresh linux-yocto ../meta-mylayer # --force-patch-refresh也没用,不会给patch添加 Upstream-Status

$ devtool finish linux-yocto ../meta-mylayer
NOTE: Starting bitbake server...
Loading cache: 100% |#######################################################################################################################################################| Time: 0:00:00
Loaded 1885 entries from dependency cache.
Parsing recipes: 100% |#####################################################################################################################################################| Time: 0:00:00
Parsing of 925 .bb files complete (924 cached, 1 parsed). 1886 targets, 49 skipped, 0 masked, 0 errors.
INFO: Would remove config fragment /tmp/devtool8g408af3/tmpzqx9t8na/devtool-fragment.cfg
NOTE: Writing append file /home/andy/Downloads/poky/meta-mylayer/recipes-kernel/linux/linux-yocto_%.bbappend
NOTE: Copying 0001-add-my-hello-module.patch to /home/andy/Downloads/poky/meta-mylayer/recipes-kernel/linux/linux-yocto/0001-add-my-hello-module.patch
INFO: Cleaning sysroot for recipe linux-yocto...
INFO: Preserving source tree in /home/andy/Downloads/poky/build/workspace/attic/sources/linux-yocto.20250926182431
If you no longer need it then please delete it manually.
It is also possible to reuse it via devtool source tree argument.

```

meta-mylayer目录多了recipes-kernel目录及下面的文件

提示手动删除linux内核源码build/workspace/attic/sources/linux-yocto.20250926182431




## 再编译时候报错`Missing Upstream-Status in patch`的处理
```
ERROR: linux-yocto-6.12.47+git-r0 do_patch: QA Issue: Missing Upstream-Status in patch
/home/andy/Downloads/poky/meta-mylayer/recipes-kernel/linux/linux-yocto/0001-add-my-hello-module.patch
Please add according to https://docs.yoctoproject.org/contributor-guide/recipe-style-guide.html#patch-upstream-status . [patch-status]
ERROR: linux-yocto-6.12.47+git-r0 do_patch: Fatal QA errors were found, failing task.
ERROR: Logfile of failure stored in: /home/andy/Downloads/poky/build/tmp/work/qemux86_64-poky-linux/linux-yocto/6.12.47+git/temp/log.do_patch.2023986
ERROR: Task (/home/andy/Downloads/poky/meta/recipes-kernel/linux/linux-yocto_6.12.bb:do_patch) failed with exit code '1'
```
在patch文件头部 `Subject: [PATCH] add my hello module` 之后添加
```
Upstream-Status: Submitted [denglitsch@gmail.com]
```
还可以填
```
Upstream-Status: Inappropriate [oe specific]
Upstream-Status: Pending
```
解释
```
Pending: Patch is submitted upstream but not yet merged.
Accepted: Patch is merged upstream.
Denied: Patch was rejected upstream.
Inappropriate: Patch is not suitable for upstreaming (e.g., project-specific changes).
```



## 总结
如果需要修改某个软件源码包，需要按以下步骤进行。

1）查找包名。提取源码时必须填写正确的软件包名，可以使用bitbake -s命令列出所有包名，然后找到你需要修改的，比如bitbake -s | grep uboot。

2）提取源码。执行devtool modify XXX，XXX是软件包全名。

3）修改源码。去build/workspace/sources/XXX目录下修改源码。

4）提交修改。在build/workspace/sources/XXX目录下依次执行git add ./* 、 git commit -m "YYY"命令提交修改。

5）制作补丁。在build目录下执行devtool finish XXX ../meta-mylayer命令完修改，并创建补丁文件到指定的layer下。






---
# 添加一个应用程序helloworld1

<https://docs.yoctoproject.org/dev-manual/common-tasks.html#writing-a-new-recipe>

~/Downloads/poky/meta-mylayer
$ mkdir recipes-helloworld   # $ mkdir -p recipes-helloworld/files
$ cd recipes-helloworld/
$ mkdir files
$ touch files/helloworld.c
$ touch helloworld.bb
$ tree
.
├── files
│   └── helloworld.c
└── helloworld.bb

1 directory, 2 files


## 目录结构调整后
```
meta-mylayer/
├── conf
│   └── layer.conf
└── recipes-helloworld
    └── helloworld
        ├── helloworld.bb
        └── files
            └── helloworld.c
```



helloworld.c文件
```
#include <stdio.h>

int main(int argc, char *argv[])
{
    printf("Hello world!\n");

    return 0;
}
```

helloworld.bb文件  (参考 recipetool create -o ../meta-mylayer/recipes-helloworld/helloworld.bb ../meta-mylayer/recipes-helloworld/files/)
```
SUMMARY = "Simple helloworld application"
SECTION = "examples"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# FILESPATH 为什么不要？思考一下，参考《bitbake工作流程》文章内容~~~
#FILESPATH := "${THISDIR}/files:"
SRC_URI = "file://helloworld.c"

# 为什么要指定S变量？思考一下，参考《bitbake工作流程》文章内容~~~
S = "${WORKDIR}"

# 重载do_compile任务，编译工作就是下面这条命令，注意使用${CC}
do_compile() {
    ${CC} ${LDFLAGS} helloworld.c -o helloworld
}

# 重载do_instal任务，安装编译成果helloworld
do_install() {
    install -d ${D}${bindir}
    install -m 0755 helloworld ${D}${bindir}
}

# FILES 表示这个软件包，需要打包进映像的文件是helloworld，但决定这个软件包是否参与打包，需要在其他地方配置
# FILES 为什么不要？思考一下，参考《bitbake工作流程》文章内容~~~
#FILES_${PN} += " ${bindir}/helloworld "
```


修正后的helloworld.bb文件
```
DESCRIPTION = "Simple Hello World example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://helloworld.c"

#S = "${WORKDIR}"
#S = "${WORKDIR}/helloworld"
S = "${UNPACKDIR}"

do_compile() {
   ${CC} ${CFLAGS} ${LDFLAGS} ${S}/helloworld.c -o helloworld
}

do_install() {
   install -d ${D}${bindir}
   install -m 0755 helloworld ${D}${bindir}
}
```

编译并且检查是否在镜像的文件系统中

```
$ bitbake -s | grep helloworld
go-helloworld                                         :0.1-r0                                                    

$ bitbake helloworld

$ find . -type d -name "helloworld"


bitbake-layers show-layers
bitbake-layers show-recipes | grep -i helloworld
bitbake -e core-image-sato | grep -n IMAGE_INSTALL
```



helloworld.bb 更新为
```
DESCRIPTION = "Simple Hello World example"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://helloworld.c"

#S = "${WORKDIR}"
#S = "${WORKDIR}/helloworld"
#S = "${UNPACKDIR}"
S = "${WORKDIR}/helloworld"
UNPACKDIR = "${S}"

# inherit autotools

do_compile() {
   ${CC} ${CFLAGS} ${LDFLAGS} -o helloworld helloworld.c
}

do_install() {
   install -d ${D}${bindir}
   install -m 0755 helloworld ${D}${bindir}
}

RDEPENDS:${PN} += "helloworld"

```

## 添加 IMAGE_INSTALL 变量 方法1

poky/meta-mylayer/recipes-core/images/my-image.bb
```
DESCRIPTION = "My custom image with helloworld"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

#require recipes-core/images/core-image-sato.bb
require recipes-sato/images/core-image-sato.bb
# 告诉 bitbake 这是一个镜像配方
inherit core-image

IMAGE_INSTALL:append = " helloworld"

```

之后检查
```
bitbake-layers show-recipes | grep my-image     # 确认系统能找到这个基础镜像
bitbake-layers show-recipes | grep -A 1 my-image
bitbake-layers show-recipes | grep core-image-sato   
bitbake -e my-image | grep IMAGE_INSTALL     # 检查 helloworld 是否真的被打进镜像
bitbake my-image            # 生成的镜像就会包含 /usr/bin/helloworld

runqemu qemux86-64 my-image qemuparams="-enable-kvm" 最保险，确保运行的就是你的 my-image。

bitbake -c cleanall helloworld
bitbake -c cleanall my-image
bitbake my-image
runqemu qemux86-64 my-image qemuparams="-enable-kvm"
```

## 添加 IMAGE_INSTALL 变量 方法2

其实用新建my-image配方并不好, 对原始 .bb 的补充 不是更好. 应该用append

poky/meta-mylayer/recipes-sato/images/core-image-sato.bbappend

```
IMAGE_INSTALL:append = " helloworld"        # .bbappend 不需要写 DESCRIPTION 或 LICENSE，它只是对原始 .bb 的补充
```

之后检查
```
bitbake-layers show-appends
bitbake-layers show-appends | grep core-image-sato
bitbake -e core-image-sato | grep -n IMAGE_INSTALL
bitbake core-image-sato
runqemu qemux86-64 qemuparams="-enable-kvm"
```








---
# 添加一个应用程序helloworld2


## 尝试记录
poky/meta-mylayer/recipes-helloworld/helloworld2
```
$ tree
.
├── files
│   ├── helloworld.c
│   ├── main.c
│   └── Makefile
└── helloworld2.bb
```

helloworld2.bb
```
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
do_compile() {
    oe_runmake
}

do_install() {
    oe_runmake install DESTDIR=${D}
}

RDEPENDS:${PN} += "helloworld2"

```

main.c
```
#include <stdio.h>

extern void myhello(void);

int main(int argc, char *argv[])
{
    myhello();
	return 0;
}

```


helloworld.c
```
#include <stdio.h>

void myhello(void)
{
    printf("Hello world!\n");
}
```

Makefile文件
```
#CC ?= $(CC)
#CFLAGS ?= -O2 -Wall

OBJS=main.o helloworld.o
TARGET=helloworld2

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ $^
	#$(CC) $(CFLAGS) $(LDFLAGS) $^ -o $(TARGET)

%.o:%.c
	$(CC) -c -o $@ $<
    
install:
	install -d $(DESTDIR)/usr/bin/
	install -m 0755 $(TARGET) $(DESTDIR)/usr/bin/

uninstall:
	${RM} $(DESTDIR)/usr/bin/$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
	#$(RM) $(TARGET) $(OBJS)

.PHONY: install uninstall clean

```






## 更新的记录
tree
```
poky/meta-mylayer/recipes-helloworld/helloworld2
├── files
│   └── helloworld
│       ├── helloworld.c
│       ├── main.c
│       └── Makefile
└── helloworld2.bb
```

更新的 helloworld2.bb
```
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
# inherit autotools pkgconfig
# inherit autotools-brokensep

# 使用 oe_runmake 调用 Makefile
#do_compile() {
#    oe_runmake
#}

do_install() {
    oe_runmake DESTDIR=${D}/${bindir} install
}

RDEPENDS:${PN} += "helloworld2"
```

更新的 Makefile 文件
```
#CC ?= $(CC)
#CFLAGS ?= -O2 -Wall

OBJS=main.o helloworld.o
TARGET=helloworld2

all: $(TARGET)

$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ $^
	#$(CC) $(CFLAGS) $(LDFLAGS) $^ -o $(TARGET)

%.o:%.c
	$(CC) -c -o $@ $<
    
install:
	install -d $(DESTDIR)
	install -m 0755 $(TARGET) $(DESTDIR)

uninstall:
	${RM} $(DESTDIR)$(TARGET)

clean:
	rm -f $(OBJS) $(TARGET)
	#$(RM) $(TARGET) $(OBJS)

.PHONY: install uninstall clean

```

poky/meta-mylayer/recipes-sato/images/core-image-sato.bbappend 添加
```
IMAGE_INSTALL:append = " helloworld2"
```

## 执行
```
bitbake helloworld2
bitbake core-image-sato

runqemu qemux86-64 qemuparams="-enable-kvm"
```





---
# yocto通过cmake编译构建hellocmake程序

目录结构
```
poky/meta-mylayer/recipes-helloworld/hellocmake
$ tree
.
├── files
│   └── hellocmake
│       ├── CMakeLists.txt
│       └── src
│           ├── helloworld.c
│           └── main.c
└── hellocmake.bb

```

poky/meta-mylayer/recipes-core/images/core-image-sato.bbappend中这样添加
```
IMAGE_INSTALL:append = " hellocmake"
```

hellocmake.bb
```
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

```

CMakeLists.txt
```
cmake_minimum_required(VERSION 3.0)
project(hellocmake C)

add_executable(hellocmake
    src/main.c
    src/helloworld.c
)
```

main.c
```
#include <stdio.h>

extern void helloworld(void);

int main() {
    helloworld();
    return 0;
}
```

helloworld.c
```
#include <stdio.h>

void helloworld(void) {
    printf("Hello from CMake + Yocto!\n");
}
```

执行
```
bitbake -s | grep  hellocmake
bitbake-layers show-recipes | grep -A 1 hellocmake
bitbake hellocmake
bitbake core-image-sato

runqemu qemux86-64 qemuparams="-enable-kvm"
```







---
# yocto通过Autotools编译构建hellocauto程序
目录结构
```
poky/meta-mylayer/recipes-helloworld/helloauto
$ tree
.
├── files
│   └── helloauto-src
│       ├── configure.ac
│       ├── Makefile.am
│       └── src
│           ├── main.c
│           └── Makefile.am
└── helloauto.bb
```


poky/meta-mylayer/recipes-core/images/core-image-sato.bbappend中这样添加
```
IMAGE_INSTALL:append = " helloauto"
```

helloauto.bb
```
DESCRIPTION = "Hello C program built with Autotools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

# 使用本地源码子目录
SRC_URI = "file://helloauto-src"

# 禁止 checksum 校验（适合本地测试）
SRC_URI[md5sum] = "00000000000000000000000000000000"
SRC_URI[sha256sum] = "0000000000000000000000000000000000000000000000000000000000000000"

# 指向解压后的源码目录
S = "${WORKDIR}/helloauto-src"

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

```

files/helloauto-src/configure.ac
```
AC_INIT([helloauto], [1.0], [you@example.com])
AM_INIT_AUTOMAKE([-Wall -Werror foreign])
AC_PROG_CC
AC_CONFIG_FILES([Makefile src/Makefile])
AC_OUTPUT
```

files/helloauto-src/Makefile.am
```
SUBDIRS = src
```

files/helloauto-src/src/Makefile.am
```
bin_PROGRAMS = helloauto
helloauto_SOURCES = main.c
```

files/helloauto-src/src/main.c 
```
#include <stdio.h>

int main() {
    printf("Hello Autotools world!\n");
    return 0;
}
```



用 `bitbake -c install helloauto` 安装到目标镜像中

用 `bitbake -c populate_sysroot helloauto` 检查安装包是否正确生成

如果要加入镜像，可在 core-image 配方里添加 IMAGE_INSTALL += "helloauto"

poky/meta-mylayer/recipes-core/images/core-image-sato.bbappend中这样添加
```
IMAGE_INSTALL:append = " helloauto"
```


执行
```
bitbake -s | grep helloauto
bitbake-layers show-recipes | grep -A 1 helloauto
bitbake helloauto
bitbake core-image-sato

runqemu qemux86-64 qemuparams="-enable-kvm"
```







---
# yocto通过Autotools编译构建hellocauto2程序

目录结构
```
poky/meta-mylayer/recipes-helloworld/helloauto2
$ tree
.
├── files
│   └── helloauto2-src
│       ├── configure.ac
│       ├── Makefile.am
│       └── src
│           ├── helloauto.c
│           ├── main.c
│           └── Makefile.am
└── helloauto2.bb
```

poky/meta-mylayer/recipes-core/images/core-image-sato.bbappend中这样添加
```
IMAGE_INSTALL:append = " helloauto2"
```

helloauto2.bb 
```
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

```

files/helloauto2-src/Makefile.am
```
SUBDIRS = src
```

files/helloauto2-src/configure.ac 
```
AC_INIT([helloauto2], [1.0], [you@example.com])
AM_INIT_AUTOMAKE([-Wall -Werror foreign])
AC_PROG_CC
AC_CONFIG_FILES([Makefile src/Makefile])
AC_OUTPUT
```


files/helloauto2-src/src/Makefile.am
```
bin_PROGRAMS = helloauto2
helloauto2_SOURCES = main.c helloauto.c
```

files/helloauto2-src/src/main.c 
```
#include <stdio.h>

extern void helloworld(void);

int main() {
    helloworld();
    return 0;
}
```

files/helloauto2-src/src/helloauto.c 
```
#include <stdio.h>

void helloworld(void) {
    printf("Hello Autotools world 2!\n");
}
```


执行
```
bitbake -s | grep  helloauto2
bitbake-layers show-recipes | grep -A 1 helloauto2
bitbake helloauto2
bitbake core-image-sato

runqemu qemux86-64 qemuparams="-enable-kvm"
```










---
# yocto 的 master分支 runqemu qemux86-64命令会报错 `images are only supported with snapshot mode`
```
runqemu - ERROR - .zst images are only supported with snapshot mode. You can either use the "snapshot" option or use an uncompressed image.
```

## 方案1：使用 snapshot 模式

直接在运行 runqemu 时加上 `snapshot` 参数
```
runqemu qemux86-64 snapshot
```
或者
```
runqemu qemux86-64 qemuparams="-enable-kvm" snapshot
runqemu qemux86-64 kvm snapshot
```



## 方案2：改用非压缩镜像

在 conf/local.conf 里禁用 .zst 压缩：
```
IMAGE_FSTYPES:remove = "wic.zst"
IMAGE_FSTYPES += "wic"
```

然后重新 bitbake：
```
bitbake core-image-sato
```

这样会得到一个未压缩的 .wic 文件，runqemu 就可以直接用：
```
runqemu qemux86-64
```


## 方案3：手动解压镜像

如果你已经有 .wic.zst 文件，可以先解压：
```
unzstd tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.wic.zst
```

得到 .wic 文件，再执行：
```
runqemu qemux86-64 tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.wic
```








---
# 创建,安装,使用 SDK


## 创建 SDK
```
$ source oe-init-build-env                      # to build folder
$ export http_proxy="127.0.0.1:8118"            # 要下载
$ export https_proxy="127.0.0.1:8118"


$ bitbake core-image-sato -c populate_sdk       # 标准SDK, 目前就看这个

$ bitbake core-image-sato -c populate_sdk_ext   # 扩展SDK
```

创建的SDK安装包在 `build/tmp/deploy/sdk`
```
$ tree
.
├── poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.host.manifest
├── poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.sh
├── poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.spdx.json
├── poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.target.manifest
└── poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.testdata.json
```

如果要重新生成
```
bitbake -c clean core-image-sato
bitbake -c populate_sdk core-image-sato


bitbake buildtools-tarball                      # 可先clean
bitbake buildtools-extended-tarball
bitbake -c populate_sdk_ext core-image-sato
```

## 安装SDK, 先`unset LD_LIBRARY_PATH`
```

$ ./poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.sh 
Poky (Yocto Project Reference Distro) SDK installer version 5.2.99+snapshot
===========================================================================
Enter target directory for SDK (default: /opt/poky/5.2.99+snapshot): 
You are about to install the SDK to "/opt/poky/5.2.99+snapshot". Proceed [Y/n]? y
Extracting SDK..........................................................................................................................................................................................done
Setting it up...done
Your environment is misconfigured, you probably need to 'unset LD_LIBRARY_PATH'
but please check why this was set in the first place and that it's safe to unset.
The SDK will not operate correctly in most cases when LD_LIBRARY_PATH is set.
For more references see:
  http://tldp.org/HOWTO/Program-Library-HOWTO/shared-libraries.html#AEN80
  http://xahlee.info/UnixResource_dir/_/ldpath.html
/opt/poky/5.2.99+snapshot/post-relocate-setup.sh: Failed to source /opt/poky/5.2.99+snapshot/environment-setup-x86-64-v3-poky-linux with status 1
Executing /opt/poky/5.2.99+snapshot/post-relocate-setup.sh failed


$ unset LD_LIBRARY_PATH

$ ./poky-glibc-x86_64-core-image-sato-x86-64-v3-qemux86-64-toolchain-5.2.99+snapshot.sh 
Poky (Yocto Project Reference Distro) SDK installer version 5.2.99+snapshot
===========================================================================
Enter target directory for SDK (default: /opt/poky/5.2.99+snapshot): 
You are about to install the SDK to "/opt/poky/5.2.99+snapshot". Proceed [Y/n]? y
Extracting SDK..........................................................................................................................................................................................done
Setting it up...done
SDK has been successfully set up and is ready to be used.
Each time you wish to use the SDK in a new shell session, you need to source the environment setup script e.g.
 $ . /opt/poky/5.2.99+snapshot/environment-setup-x86-64-v3-poky-linux
```


## 加载SDK环境变量, 先`unset LD_LIBRARY_PATH`
```
unset LD_LIBRARY_PATH
source /opt/poky/5.2.99+snapshot/environment-setup-x86-64-v3-poky-linux
```



## SDK环境的编译器用$CC

CC版本

```
$ $CC -v
Using built-in specs.
COLLECT_GCC=x86_64-poky-linux-gcc
COLLECT_LTO_WRAPPER=/opt-shadow/poky/5.2.99+snapshot/sysroots/x86_64-pokysdk-linux/usr/libexec/x86_64-poky-linux/gcc/x86_64-poky-linux/15.2.0/lto-wrapper
Target: x86_64-poky-linux
Configured with: ../../../../../../work-shared/gcc-15.2.0-r0/sources/gcc-15.2.0/configure --build=x86_64-linux --host=x86_64-pokysdk-linux --target=x86_64-poky-linux --prefix=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr --exec_prefix=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr --bindir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/bin/x86_64-poky-linux --sbindir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/bin/x86_64-poky-linux --libexecdir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/libexec/x86_64-poky-linux --datadir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/share --sysconfdir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/etc --sharedstatedir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/com --localstatedir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/var --libdir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/lib/x86_64-poky-linux --includedir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/include --oldincludedir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/include --infodir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/share/info --mandir=/usr/local/oe-sdk-hardcoded-buildpath/sysroots/x86_64-pokysdk-linux/usr/share/man --disable-silent-rules --disable-dependency-tracking --with-gnu-ld --enable-shared --enable-languages=c,c++ --enable-threads=posix --enable-multilib --enable-default-pie --enable-c99 --enable-long-long --enable-symvers=gnu --enable-libstdcxx-pch --program-prefix=x86_64-poky-linux- --without-local-prefix --disable-install-libiberty --disable-libssp --enable-libitm --enable-lto --disable-bootstrap --with-system-zlib --with-linker-hash-style=gnu --enable-linker-build-id --with-ppl=no --with-cloog=no --enable-checking=release --enable-cheaders=c_global --without-isl --with-gxx-include-dir=/not/exist/usr/include/c++/15.2.0 --with-gxx-libcxx-include-dir=/not/exist/usr/include/c++/v1 --with-build-time-tools=/host-native/usr/x86_64-poky-linux/bin --with-sysroot=/not/exist --with-build-sysroot=/host --with-plugin-ld=ld --enable-poison-system-directories --disable-static --enable-nls --with-glibc-version=2.28 --enable-initfini-array --enable-__cxa_atexit
Thread model: posix
Supported LTO compression algorithms: zlib zstd
gcc version 15.2.0 (GCC) 

```


app/hello_word.c
```
#include <stdio.h>

int main(void)
{
	printf("hello world!\r\n");
	return 0;
}
```

编译需要--sysroot, 直接用 $CC 即可
```
$ x86_64-poky-linux-gcc hello_word.c -o hello_world

hello_word.c:2:10: fatal error: stdio.h: No such file or directory
    2 | #include <stdio.h>
      |          ^~~~~~~
```


$CC 已经包含了 --sysroot 等必要参数，不需要手动写
```
$ echo $CC
x86_64-poky-linux-gcc -m64 -march=x86-64-v3 -fstack-protector-strong -O2 -D_FORTIFY_SOURCE=2 -Wformat -Wformat-security -Werror=format-security --sysroot=/opt/poky/5.2.99+snapshot/sysroots/x86-64-v3-poky-linux
```

正常的编译成功
```
$ $CC hello_word.c -o hello_word
```


静态编译不能
```
$ $CC -static hello_word.c -o hello_word 
/opt-shadow/poky/5.2.99+snapshot/sysroots/x86_64-pokysdk-linux/usr/bin/x86_64-poky-linux/../../libexec/x86_64-poky-linux/gcc/x86_64-poky-linux/15.2.0/ld: cannot find -lc: No such file or directory
/opt-shadow/poky/5.2.99+snapshot/sysroots/x86_64-pokysdk-linux/usr/bin/x86_64-poky-linux/../../libexec/x86_64-poky-linux/gcc/x86_64-poky-linux/15.2.0/ld: have you installed the static version of the c library ?
collect2: error: ld returned 1 exit status
```


宿主机运行不能
```
$ file hello_word
hello_word: ELF 64-bit LSB pie executable, x86-64, version 1 (SYSV), dynamically linked, interpreter /lib/ld-linux-x86-64.so.2, BuildID[sha1]=803e122fd6fc133499cd4d9109af7ae95f3f0d8c, for GNU/Linux 5.15.0, with debug_info, not stripped

$ ./hello_word 
bash: ./hello_word: No such file or directory
```


## ssh发文件方法1

runqemu 默认用的是 tap0 直连方式, QEMU 里 guest IP 是 192.168.7.2
```
//$ runqemu qemux86-64 qemuparams="-enable-kvm" snapshot
$ runqemu qemux86-64 kvm snapshot
runqemu - INFO - Running MACHINE=qemux86-64 bitbake -e  ...
runqemu - WARNING - Found existing decompressed image: /home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.ext4, Using it directly.
runqemu - INFO - Continuing with the following parameters:
KERNEL: [/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/bzImage]
MACHINE: [qemux86-64]
FSTYPE: [ext4]
ROOTFS: [/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.ext4]
SNAPSHOT: [Enabled. Changes on rootfs won't be kept after QEMU shutdown.]
CONFFILE: [/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.qemuboot.conf]

runqemu - INFO - Using preconfigured tap device tap0
runqemu - INFO - If this is not intended, touch /tmp/qemu-tap-locks/tap0.skip to make runqemu skip tap0.
runqemu - INFO - Network configuration: ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0
runqemu - INFO - Running /home/andy/Downloads/mywork/build/tmp/work/x86_64-linux/qemu-helper-native/1.0/recipe-sysroot-native/usr/bin/qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:34:02 -netdev tap,id=net0,ifname=tap0,script=no,downscript=no -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -drive file=/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd   -cpu Skylake-Client -machine q35,i8042=off -smp 4 -m 512  -enable-kvm -snapshot -serial mon:vc -serial null -device virtio-vga  -display sdl,show-cursor=on  -kernel /home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=192.168.7.2::192.168.7.1:255.255.255.0::eth0:off:8.8.8.8 net.ifnames=0 oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '

runqemu - INFO - Host uptime: 126712.64
```


主机端登录 QEMU
```
andy@andy-kuangshi16:~/Downloads/app
$ ssh root@192.168.7.2
The authenticity of host '192.168.7.2 (192.168.7.2)' can't be established.
RSA key fingerprint is SHA256:PMBbj3jhGOQAESoiZr8p3Y3kqxiAwKcsbvjvyKRu/lI.
This key is not known by any other names
Are you sure you want to continue connecting (yes/no/[fingerprint])? yes
Warning: Permanently added '192.168.7.2' (RSA) to the list of known hosts.

WARNING: Poky is a reference Yocto Project distribution that should be used for
testing and development purposes only. It is recommended that you create your
own distribution for production use.

xauth:  file /home/root/.Xauthority does not exist
root@qemux86-64:~# 
```


宿主机给QEMU发文件
```
$ scp hello_word root@192.168.7.2:/home/root
```

## ssh发文件方法2

slirp 模式运行, 有 127.0.0.1:2222 → guest:22 的转发
```
//$ runqemu qemux86-64 qemuparams="-enable-kvm" snapshot slirp
$ runqemu qemux86-64 kvm snapshot slirp
runqemu - INFO - Running MACHINE=qemux86-64 bitbake -e  ...
runqemu - WARNING - Found existing decompressed image: /home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.ext4, Using it directly.
runqemu - INFO - Continuing with the following parameters:
KERNEL: [/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/bzImage]
MACHINE: [qemux86-64]
FSTYPE: [ext4]
ROOTFS: [/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.ext4]
SNAPSHOT: [Enabled. Changes on rootfs won't be kept after QEMU shutdown.]
CONFFILE: [/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.qemuboot.conf]

runqemu - INFO - Network configuration: ip=dhcp
runqemu - INFO - Port forward: hostfwd=tcp:127.0.0.1:2222-:22 hostfwd=tcp:127.0.0.1:2323-:23
runqemu - INFO - Running /home/andy/Downloads/mywork/build/tmp/work/x86_64-linux/qemu-helper-native/1.0/recipe-sysroot-native/usr/bin/qemu-system-x86_64 -device virtio-net-pci,netdev=net0,mac=52:54:00:12:35:02 -netdev user,id=net0,hostfwd=tcp:127.0.0.1:2222-:22,hostfwd=tcp:127.0.0.1:2323-:23,tftp=/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64 -object rng-random,filename=/dev/urandom,id=rng0 -device virtio-rng-pci,rng=rng0 -drive file=/home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/core-image-sato-qemux86-64.rootfs-20250930135346.ext4,if=virtio,format=raw -usb -device usb-tablet -usb -device usb-kbd   -cpu Skylake-Client -machine q35,i8042=off -smp 4 -m 512  -enable-kvm -snapshot -serial mon:vc -serial null -device virtio-vga  -display sdl,show-cursor=on  -kernel /home/andy/Downloads/mywork/build/tmp/deploy/images/qemux86-64/bzImage -append 'root=/dev/vda rw  ip=dhcp oprofile.timer=1 tsc=reliable no_timer_check rcupdate.rcu_expedited=1 swiotlb=0 '

runqemu - INFO - Host uptime: 127047.97

```


测试宿主机ssh登录QEMU
```
ssh -p 2222 root@127.0.0.1
```



宿主机给QEMU发文件
```

scp -P 2222 hello_word root@127.0.0.1:/home/root/
```




## NFS挂载

QEMU客户机
```
mkdir -p /mnt/hostfs
mount -t nfs 192.168.7.1:/home/andy/targetfs /mnt/hostfs
```


tips: 镜像没有 net-tools 包（所以没有 ifconfig），只有 iproute2，可以用：
```
ip addr
ip route
```
来查看网络状态


tips: 宿主机配置 NFS 导出
```
sudo apt install nfs-kernel-server
编辑 /etc/exports，添加一行：
/home/andy/targetfs 192.168.7.0/24(rw,sync,no_subtree_check,no_root_squash)
然后刷新配置：
sudo exportfs -ra
确认 NFS 服务正常：
showmount -e localhost
输出里应该有 /home/andy/targetfs.
```





## NFS自动挂载

如果想启动后自动挂载，可以在 guest 里 /etc/fstab 添加. 重新构建镜像后失效
```
192.168.7.1:/home/andy/targetfs   /mnt/hostfs   nfs   defaults   0  0
```


如果要重新构建镜像后也自动挂载

meta-mylayer/recipes-core/base-files/base-files_%.bbappend
```
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://fstab"
```

meta-mycustom/recipes-core/base-files/files/fstab 写完整的 fstab
```
# /etc/fstab: static file system information
/dev/root      /               auto       defaults           1  1
proc           /proc           proc       defaults           0  0
devpts         /dev/pts        devpts     mode=0620,gid=5    0  0
tmpfs          /dev/shm        tmpfs      defaults           0  0
192.168.7.1:/home/andy/targetfs   /mnt/hostfs   nfs   defaults   0  0
```

然后重新构建镜像
```
bitbake core-image-sato
```





