package indi.lhj.operation.pojo;


/**
 * 算术类（子类：运算符类Operator，操作数类Operand，括号类Bracket）
 *
 * @author 郑耿杭，梁鸿俊
 */
public class Arithmetic {

    /**
     * 值
     */
    protected String value;

    /**
     * 优先级
     */
    protected int priority;

    public String toString() {
        return this.value;
    }
}
