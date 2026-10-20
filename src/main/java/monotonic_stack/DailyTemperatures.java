package monotonic_stack;

import java.util.Stack;

// https://leetcode.com/problems/daily-temperatures/description
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        // Stack will store all the elements for which a greater temp have not been found yet
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                result[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
}
