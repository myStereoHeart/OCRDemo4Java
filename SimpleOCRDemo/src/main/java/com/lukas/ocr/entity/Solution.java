package com.lukas.ocr.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    public int getMaxValue (int[] nums, int[] values) {
        // write code here
        List<Integer> numsList = new ArrayList();
        List<Integer> valuesList = new ArrayList();
        for(int i=0;i<nums.length;i++){
            numsList.add(nums[i]);
        }
        for(int i=0;i<nums.length;i++){
            valuesList.add(values[i]);
        }
        Collections.sort(numsList);
        Collections.sort(valuesList);
        int maxValue = 0;
        for(int i=0;i<numsList.size();i++){
            maxValue += numsList.get(i) * valuesList.get(i);
        }
        return maxValue;
    }
}
