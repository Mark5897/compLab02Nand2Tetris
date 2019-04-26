import java.text.ParseException;
import java.util.StringTokenizer;

/**
 * CodeWriter class is dedicated to translating expressions from a high level language and compiling them directly
 * to stack-machine code / to the virtual machine.
 *
 *
 *
 * Algorithm for evaluating an expression:
 *
 * 1: Exp is analyzed to decide if it meets on of the following possible semantics:
 *    a: exp is a number: output "push n"
 *    b: exp is a variable: output "push var"
 *    * op = operator such as + - / * =         nl = newLine
 *    c: exp is in following format: "exp1 op exp2", output: codeWrite(exp1) nl codeWrite(exp2) nl output "op"
 *    d: exp is in following format: "op exp1", output: codeWrite(exp1) nl output "op"
 *    e: exp is in following format: "f(exp1, exp2 ...)", output: codeWrite(exp1) nl codeWrite(exp2) nl output "call f"
 *
 * 2: Write translated VM code to the console as the output.
 *
 * @author mark vincent ii
 * @version 1.0
 */
public class CodeWriter {

    // instanced variables.
    private String exp;

    /**
     * Constructor for a CodeWriter object.
     * pre: Valid expression.
     * post: CodeWriter object that can print stack-machine code to the console.
     * @param exp the expression you want to place in the codeWriter object.
     */
    public CodeWriter(String exp) {
        this.exp = exp;
    }


    /**
     * Prints the stack-machine code to the console.
     * pre: Valid expression has been passed such as a number, variable, etc.
     * post: Prints to the console, the output of the expression.
     * @param exp the express you want to pass.
     */
    public void writeCode(String exp) {
        // checks if the token is an expression, if not -> checks for number or variable or operator.
        if (exp.indexOf('(') == -1 && exp.indexOf(')') == -1 && exp.indexOf(',') == -1) {
            System.out.println("I may be an op, number or variable:\t" + exp);

            // will push number if exp == number.
            if (isNumber(exp)) {
                System.out.println("push " + Integer.parseInt(exp));
                return;
            }

            // checks for operator
            switch (exp) {
                case "+":
                    // write op
                    System.out.println('+');
                    return;
                case "-":
                    // write op
                    System.out.println('-');
                    return;
                case "*":
                    // write op
                    System.out.println('*');
                    return;
                case "/":
                    // write op
                    System.out.println('/');
                    return;
                default:
                    // checks for negative op, else nothing.
                    if (exp.indexOf('-') != -1 && exp.length() > 1) {

                        // checks for parentheses at the end of the value.
                        if (exp.indexOf(')') != -1) {
                            // write neg -> variable or number
                            System.out.println("push " + exp.substring(exp.indexOf('-'), exp.indexOf(')')));
                            System.out.println("neg");
                        } else {
                            // write neg -> variable or number
                            System.out.println("push " + exp.substring(1));
                            System.out.println("neg");
                        }

                    } else {
                        System.out.println("error handling string: " + exp);
                    }
                    return;
            }


        }
    }


    // Main Method
    public static void main(String[] args) {

        // test Strings
        String testExp1 = "x + y - z + 5";
        String testExp2 = "f(x,-y) * z + 2";
        String testExp3 = "-z / f(g(x+2), h(y-5)) * j(2)";

        // current test expression.
        String test = "x";

        // build CodeWriter object.
        CodeWriter writer = new CodeWriter("null");

        // token's an expression.
        StringTokenizer tokens = new StringTokenizer(testExp3);

        System.out.println("test string: " + testExp3);

        // prints tokens
        while (tokens.hasMoreTokens()) {
            String currentToken = tokens.nextToken();
            writer.writeCode(currentToken);
        }

    }

    /**
     * Checks if a string can be converted to a number.
     * @param value
     * @return
     */
    private boolean isNumber(String value) {
        // checks for operator, number, and lastly assume variable.
        try {
            Integer.parseInt(exp);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
