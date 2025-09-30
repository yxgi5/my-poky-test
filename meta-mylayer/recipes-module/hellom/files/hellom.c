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
MODULE_AUTHOR("andy");
MODULE_DESCRIPTION("Simple Hello Module");
