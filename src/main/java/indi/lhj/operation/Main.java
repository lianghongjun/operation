package indi.lhj.operation;

import indi.lhj.operation.pojo.Equation;
import indi.lhj.operation.utils.FileUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author 郑耿杭，梁鸿俊
 */
public class Main {
    private static Integer r = null;
    private static Integer n = null;
    private static String e = null;
    private static String a = null;

    public static void main(String[] args) {
        processArgs(args);
        System.out.println(r);
        System.out.println(n);
        System.out.println(e);
        System.out.println(a);
        if (e != null && a != null && r != null && n != null) {

            // 生成题目
            generateExercise(n, r);

            // 分析答案
            analyse(e, a, "Grade.txt");


        } else {
            System.out.println("参数错误,请重新启动并输入正确参数");
        }
    }

    /**
     * @param exercisePath 内含表达式的文件路径
     * @param answerPath   存放计算结果的文件路径
     * @param gradePath    统计对错情况及对应题目序号的文件路径
     */
    public static void analyse(String exercisePath, String answerPath, String gradePath) {
        List<Equation> exercises = FileUtils.readAsEquation(exercisePath);
        List<Float> answers = FileUtils.readAsAnswer(answerPath);
        if (answers != null && exercises != null && exercises.size() != answers.size()) {
            System.out.println("练习与答案数量不相同,只分析写了答案的题目");
        }

        // 记录对错情况
        List<String> corrects = new ArrayList<>();
        List<String> wrongs = new ArrayList<>();
        if (answers != null) {
            for (int i = 0; i < answers.size(); i++) {
                if (exercises != null && Math.abs(answers.get(i) - exercises.get(i).getResult()) < 0.000001) {
                    corrects.add(i + "");
                }
            }
        }
        String sb1 = "Correct:" + corrects.size() + "(" +
                String.join(",", corrects) + ")";
        String sb2 = "Wrong:" + wrongs.size() + "(" +
                String.join(",", wrongs) + ")";
        FileUtils.write(gradePath, Arrays.asList(sb1, sb2));
    }

    /**
     * @param exerciseNum     所需生成的题目数量
     * @param valueLimitation 指定范围参数,操作数及操作数分母＜valueLimitation
     */
    public static void generateExercise(int exerciseNum, int valueLimitation) {
        List<Equation> list = new ArrayList<>();
        Random r = new Random();
        // 随机生成表达式,并筛掉重复的,直到数量达到目标
        while (list.size() < exerciseNum) {
            int operandNo = r.nextInt(3) + 2;
            int operatorNo = operandNo - 1;
            int bracketsNo = 1;
            list.add(Equation.generate(operandNo, operatorNo, bracketsNo, 1, valueLimitation));
            Equation.filter(list);
        }
        // 写入文件
        FileUtils.write("Exercises.txt", list.stream().map(Equation::toString).toList());
        FileUtils.write("Answers.txt", list.stream().map(e -> e.getResult() + "").toList());
    }

    /**
     * @param args 命令行参数数组
     */
    public static void processArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                // 可能有输入错误
                case "-r" -> r = Integer.parseInt(args[i + 1]);
                case "-n" -> n = Integer.parseInt(args[i + 1]);
                case "-e" -> e = args[i + 1];
                case "-a" -> a = args[i + 1];
            }
        }

    }

    /**
     * @param equation1 表达式1
     * @param equation2 表达式2
     * @return 返回两表达式是否重复
     */
    public static boolean checkRepetition(Equation equation1, Equation equation2) {
        return true;
    }
}