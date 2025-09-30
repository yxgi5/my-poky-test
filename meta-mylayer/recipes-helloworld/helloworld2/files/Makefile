#Makefile文件

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

