package game;

import game.enums.PlayerSymbol;

/**
 * Игровое поле
 */
public class GameField {

    /**
     * Массив, содержащий игровое поле
     */
    private char[][] field;

    /**
     * Значение для пустой ячейки
     */
    private final char EMPTY_CELL = '.';

    /**
     * Размер игрового поля
     */
    private final int FIELD_SIZE;

    /**
     * Длина выигрышной комбинации
     */
    private final int WIN_LENGTH;

    /**
     * Конструктор
     *
     * @param fieldSize размер игрового поля
     * @param winLength длина выигрышной комбинации
     */
    public GameField(int fieldSize, int winLength) {
        this.FIELD_SIZE = fieldSize;
        this.WIN_LENGTH = winLength;
        initialize();
    }

    /**
     * Первоначальное заполнение игрового поля
     */
    public void initialize() {
        field = new char[FIELD_SIZE][FIELD_SIZE];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = EMPTY_CELL;
            }
        }
    }

    /**
     * Вывести игровое поле в консоль
     */
    public void repaint() {
        for (char[] row : field) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    /**
     * Проставить символ игрока в нужное поле
     *
     * @param symbol      символ игрока (Х или О)
     * @param coordinates координаты в виде строки
     *                    Координаты должны передаваться в виде строки с разделителем-пробелом, пример - "2 3".
     * @return            true, если ход выполнен успешно, символ игрока проставлен в поле
     */
    public boolean setSymbol(PlayerSymbol symbol, String coordinates) {

        // Получаем массив, содержащий отдельно координату строки и столбца.
        String[] coordinatesValues = coordinates.split(" ");

        // Проверка. Если размер массива не 2, значит переданы некорректные координаты.
        if (coordinatesValues.length != 2) {
            return false;
        }

        // Парсим координаты в числовой тип. Если в процессе парсинга возникает ошибка,
        // значит переданы некорректные координаты.
        int coordinateX, coordinateY;
        try {
            coordinateX = Integer.parseInt(coordinatesValues[0]) - 1;
            coordinateY = Integer.parseInt(coordinatesValues[1]) - 1;
        } catch (Exception e) {
            return false;
        }

        // Проверяем, не занята ли уже указанная ячейка.
        try {
            if (field[coordinateX][coordinateY] != EMPTY_CELL) {
                return false;
            }
            // Заполняем указанную ячейку символом игрока.
            field[coordinateX][coordinateY] = symbol.getValue();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Проверка, заполнено ли поле
     *
     * @return true, если поле полностью заполнено символами игроков
     */
    public boolean isFieldFull() {
        for (char[] row : field) {
            for (char cell : row) {
                if (cell == EMPTY_CELL) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Проверка, присутствует ли на поле выигрышная комбинация
     *
     * @param symbol    символ игрока
     * @return          true, если выигрышная комбинация присутствует на поле
     */
    public boolean isWin(char symbol) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (checkUpRightDiagonal(i, j, symbol) || checkDownRightDiagonal(i, j, symbol)
                        || checkRightDirection(i, j, symbol) || checkDownDirection(i, j, symbol)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяем наличие выигрышной комбинации по направлению вверх и вправо от указанной ячейки
     *
     * @param x         координата ячейки x
     * @param y         координата ячейки y
     * @param symbol    символ игрока
     * @return          true, если нашли выигрышную комбинацию
     */
    private boolean checkUpRightDiagonal(int x, int y, char symbol) {
        int symbolCounter = 0;
        for (int i = 0; i < WIN_LENGTH; i++) {
            try {
                if (field[x++][y++] == symbol) {
                    symbolCounter++;
                }
            } catch (Exception e) {
                // Если вылетела ошибка - значит вышли за пределы поля,
                // выигрышной комбинации нет в этом направлении
                return false;
            }
        }
        return symbolCounter == WIN_LENGTH;
    }

    /**
     * Проверяем наличие выигрышной комбинации по направлению вниз и вправо от указанной ячейки
     *
     * @param x         координата ячейки x
     * @param y         координата ячейки y
     * @param symbol    символ игрока
     * @return          true, если нашли выигрышную комбинацию
     */
    private boolean checkDownRightDiagonal(int x, int y, char symbol) {
        int symbolCounter = 0;
        for (int i = 0; i < WIN_LENGTH; i++) {
            try {
                if (field[x++][y--] == symbol) {
                    symbolCounter++;
                }
            } catch (Exception e) {
                // Если вылетела ошибка - значит вышли за пределы поля,
                // выигрышной комбинации нет в этом направлении
                return false;
            }
        }
        return symbolCounter == WIN_LENGTH;
    }

    /**
     * Проверяем наличие выигрышной комбинации по направлению вправо от указанной ячейки
     *
     * @param x         координата ячейки x
     * @param y         координата ячейки y
     * @param symbol    символ игрока
     * @return          true, если нашли выигрышную комбинацию
     */
    private boolean checkRightDirection(int x, int y, char symbol) {
        int symbolCounter = 0;
        for (int i = 0; i < WIN_LENGTH; i++) {
            try {
                if (field[x++][y] == symbol) {
                    symbolCounter++;
                }
            } catch (Exception e) {
                // Если вылетела ошибка - значит вышли за пределы поля,
                // выигрышной комбинации нет в этом направлении
                return false;
            }
        }
        return symbolCounter == WIN_LENGTH;
    }

    /**
     * Проверяем наличие выигрышной комбинации по направлению вниз от указанной ячейки
     *
     * @param x         координата ячейки x
     * @param y         координата ячейки y
     * @param symbol    символ игрока
     * @return          true, если нашли выигрышную комбинацию
     */
    private boolean checkDownDirection(int x, int y, char symbol) {
        int symbolCounter = 0;
        for (int i = 0; i < WIN_LENGTH; i++) {
            try {
                if (field[x][y--] == symbol) {
                    symbolCounter++;
                }
            } catch (Exception e) {
                // Если вылетела ошибка - значит вышли за пределы поля,
                // выигрышной комбинации нет в этом направлении
                return false;
            }
        }
        return symbolCounter == WIN_LENGTH;
    }
}