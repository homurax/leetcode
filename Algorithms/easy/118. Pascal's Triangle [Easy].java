package com.homurax.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. Pascal's Triangle
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 *
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {


    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> ans = new ArrayList<>(numRows);
        if (numRows <= 0) return ans;

        ans.add(new ArrayList<>());
        ans.get(0).add(1);

        for (int index = 1; index < numRows; index++) {

            List<Integer> prev = ans.get(index - 1);
            List<Integer> row = new ArrayList<>(prev.size() + 1);
            ans.add(row);

            row.add(1);
            for (int j = 1; j < index; j++) {
                row.add(prev.get(j - 1) + prev.get(j));
            }
            row.add(1);
        }

        return ans;
    }
}
