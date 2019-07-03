package com.bujidao.concurrency.AQS.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class ForkJoinTaskExample extends RecursiveTask<Integer> {
    public static final int threshold = 2;
    private int start;
    private int end;

    public ForkJoinTaskExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        //当任务量足够小计算任务
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinTaskExample forkJoinTaskExample1 = new ForkJoinTaskExample(start, middle);
            ForkJoinTaskExample forkJoinTaskExample2 = new ForkJoinTaskExample(middle + 1, end);
            //执行子任务
            forkJoinTaskExample1.fork();
            forkJoinTaskExample2.fork();

            //等待任务完成并合并其结果
            int result1 = forkJoinTaskExample1.join();
            int result2 = forkJoinTaskExample2.join();

            //合并子任务
            sum = result1 + result2;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTaskExample forkJoinTaskExample=new ForkJoinTaskExample(2,100);
        Future<Integer> result=forkJoinPool.submit(forkJoinTaskExample);

        try{
            log.info("result:{}",result.get());
        }catch (Exception e){
            log.error("exp",e);
        }
    }
}
