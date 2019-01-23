package com.ndy.island.generator.executer;

import com.ndy.island.generator.abstraction.IGenerate;

import java.util.LinkedList;
import java.util.Queue;

public class Generator {

    private Queue<IGenerate> generateAbleQueue = new LinkedList<>();

    public Generator() {}

    public Generator addGenerateAble(IGenerate generateAble) {
        if(generateAbleQueue.contains(generateAble)) return this;

        generateAbleQueue.add(generateAble);
        return this;
    }

    public void dispose() {
        while(!generateAbleQueue.isEmpty()) {
            try {
                IGenerate able = generateAbleQueue.remove();
                boolean success = able.generate();

                if(success) System.out.println(able.getName() + " Generated!");
                else System.out.println(able.getName() + " Generate Failed!");

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
