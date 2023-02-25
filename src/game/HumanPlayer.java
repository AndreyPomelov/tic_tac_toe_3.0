package game;

import game.enums.PlayerSymbol;
import game.interfaces.Player;

import java.util.Scanner;

/**
 * Игрок-человек
 */
public class HumanPlayer implements Player {

    /**
     * Имя игрока
     */
    private final String NAME;

    /**
     * Символ игрока, Х или О
     */
    private final PlayerSymbol SYMBOL;

    /**
     * Конструктор
     *
     * @param name      имя игрока
     * @param symbol    символ игрока
     */
    public HumanPlayer(String name, PlayerSymbol symbol) {
        this.NAME = name;
        this.SYMBOL = symbol;
    }

    /**
     * Геттер
     *
     * @return символ игрока
     */
    @Override
    public PlayerSymbol getSymbol() {
        return SYMBOL;
    }

    /**
     * Геттер
     *
     * @return имя игрока
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * Сделать ход
     *
     * @return координаты в виде строки с разделителем-пробелом, например - "2 3"
     */
    @Override
    public String makeMove() {
        System.out.printf("%s, введите номер строки и столбца через пробел\n", NAME);
        return new Scanner(System.in).nextLine();
    }
}