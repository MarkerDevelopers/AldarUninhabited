package com.ndy.island.generator.executer;

import com.ndy.island.generator.abstraction.GenerateAble;

import java.util.LinkedList;
import java.util.Queue;

public class Generator {

    private Queue<GenerateAble> generateAbleQueue = new LinkedList<>();
    private boolean isExecute = false;

    public Generator() {}

    public Generator addGenerateAble(GenerateAble generateAble) {
        if(generateAbleQueue.contains(generateAble)) return this;

        generateAbleQueue.add(generateAble);
        return this;
    }

    private void setExecute(boolean execute) {
        this.isExecute = execute;
    }

    public boolean isExecute() { return isExecute; }

    public void dispose() {
        while(!generateAbleQueue.isEmpty()) {
            try {
                GenerateAble able = generateAbleQueue.remove();
                boolean success = able.generate();

                if(success) System.out.println(able.getName() + " Generated!");
                else System.out.println(able.getName() + " Generate Failed!");

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
