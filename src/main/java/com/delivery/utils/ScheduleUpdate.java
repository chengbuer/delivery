package com.delivery.utils;

import java.util.List;

/**
 * @author Administrator
 * @ClassName: ScheduleUdate
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/3/7 11:01
 */
public class ScheduleUpdate {
    private Task task;
    private List<WorkerInfo> workerInfos;

    @Override
    public String toString() {
        return "ScheduleUpdate{" +
                "task=" + task +
                ", workerInfos=" + workerInfos +
                '}';
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<WorkerInfo> getWorkerInfos() {
        return workerInfos;
    }

    public void setWorkerInfos(List<WorkerInfo> workerInfos) {
        this.workerInfos = workerInfos;
    }
}
