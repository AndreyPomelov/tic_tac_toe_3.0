package game.enums;

/**
 * Перечисление символов игрока
 */
public enum PlayerSymbol {

    /**
     * Символ X
     */
    X ('X'),

    /**
     * Символ O
     */
    O ('O');

    /**
     * Символьное значение
     */
    private final char VALUE;

    /**
     * Конструктор
     *
     * @param value символьное значение
     */
    PlayerSymbol(char value) {
        this.VALUE = value;
    }

    /**
     * Геттер
     *
     * @return символьное значение
     */
    public char getValue() {
        return VALUE;
    }
}