SRC_NAME = computor.java \
						Complex.java \
						Monome.java \
						Polynome.java \
						ParseException.java \
						Pol_deg_2.java


SRC_PATH = ./src/
OBJ_PATH = ./bin/

NAME = computor.jar

MANIFEST = $(SRC_PATH)manifest.txt

CC = javac

OBJ_NAME = $(SRC_NAME:.java=.class)

SRC = $(addprefix $(SRC_PATH),$(SRC_NAME))
OBJ = $(addprefix $(OBJ_PATH),$(OBJ_NAME))

all : $(NAME)


$(NAME) : $(OBJ)

$(OBJ_PATH)%.class: $(SRC_PATH)%.java
	mkdir $(OBJ_PATH) 2> /dev/null || true
	echo "compiling $<"
	$(CC) -d bin/ -cp $(SRC_PATH) $<

clean: fclean

fclean:
	/bin/rm -fv $(OBJ)
	rm -rf $(OBJ_PATH) 2> /dev/null || true
	rm -fv $(NAME)


re: fclean all

.PHONY : all clean fclean re
