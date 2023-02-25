package game;

import game.enums.PlayerSymbol;
import game.interfaces.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Игра "Крестики-нолики"
 *
 * @version 2.0
 * @author Andrey Pomelov
 */
public class TicTacToe {

    private static final List<Player> PLAYERS = new ArrayList<>();
    private static boolean isGameOver;
    private static GameField field;

    public static void main(String[] args) {

        // Создаём игровое поле.
        init();

        // Игровой цикл продолжается до тех пор, пока isGameOver == false.
        while (!isGameOver) {
            for (Player player : PLAYERS) {
                PlayerSymbol symbol = player.getSymbol();
                String coordinates;
                boolean isMoveSuccess;

                // Цикл выбора координат.
                // Продолжает работу до тех пор, пока игрок не введёт корректные координаты.
                do {
                    coordinates = player.makeMove();

                    // Проверяем, успешно ли сделан ход.
                    isMoveSuccess = field.setSymbol(symbol, coordinates);
                } while (!isMoveSuccess);

                // Отрисовываем игровое поле.
                field.repaint();

                // Проверяем, не выиграл ли игрок в результате своего хода.
                if (field.isWin(symbol.getValue())) {
                    isGameOver = true;
                    System.out.printf("Конец игры. Побеждает %s.\n", player.getName());
                    break;
                }

                // Проверяем, не заполнено ли игровое поле после хода игрока.
                if (field.isFieldFull()) {
                    isGameOver = true;
                    System.out.println("Конец игры. Ничья.");
                    break;
                }
            }
        }
    }

    /**
     * Первоначальная инициализация игры, создание игрового поля.
     */
    private static void init() {
        System.out.println("Игра \"Крестики-нолики\".");

        // Создаём поле для игры.
        field = new GameField(getFieldSize());

        // Создаём и добавляем в игру двоих игроков.
        PLAYERS.add(new HumanPlayer("Игрок 1", PlayerSymbol.X));
        PLAYERS.add(new HumanPlayer("Игрок 2", PlayerSymbol.O));

        // Отрисовываем игровое поле.
        field.repaint();
    }

    /**
     * Выбор игроком размера игрового поля.
     *
     * @return размер игрового поля.
     */
    private static int getFieldSize() {
        int fieldSize = 0;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Введите размер игрового поля (от 3 до 8).");

            try {
                fieldSize = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Игнорируем ошибку парсинга введённой строки в число.
            }
        } while (fieldSize < 3 || fieldSize > 8);

        return fieldSize;
    }
}