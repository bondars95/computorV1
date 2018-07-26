SRC_NAME = computor.java \
						Exponent.java \
						Polynom.java \
						PolynomPow2.java

SRC_PATH = ./src/
OBJ_PATH = ./bin/

CC = javac

OBJ_NAME = $(SRC_NAME:.java=.class)

OBJ = $(addprefix $(OBJ_PATH),$(OBJ_NAME))

all : $(OBJ)

$(OBJ_PATH)%.class: $(SRC_PATH)%.java
	@mkdir -p $(OBJ_PATH)
	@echo "compiling $<"
	@$(CC) -d bin/ -cp $(SRC_PATH) $<

clean: fclean

fclean:
	rm -rf $(OBJ_PATH)

re: fclean all