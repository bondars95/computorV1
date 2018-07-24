SRC_NAME = computor.java

SRC_PATH = ./src/
OBJ_PATH = ./bin/

CC = javac

OBJ_NAME = $(SRC_NAME:.java=.class)

all : $(SRC_NAME)

$(SRC_NAME):
	mkdir -p $(OBJ_PATH)
	@echo "compiling $@"
	$(CC) -d bin/ -cp $(SRC_PATH) $(SRC_PATH)$@

clean: fclean

fclean:
	rm -rf $(OBJ_PATH)

re: fclean all
