package com.delivery.utils;

import com.delivery.constant.CapacityEnum;
import com.delivery.constant.LabelEnum;
import com.delivery.constant.RadiusEnum;
import com.delivery.entity.PointOfInterest;
import com.delivery.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @ClassName: TaskAlEvent
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 20:28
 */
public class TaskAllocation {
        private List<Schedule> scheduleWorkers;
        private Task task;
        private List<PointOfInterest> pointInterests;

        public TaskAllocation() {

        }

        // 构造器，接收的参数 workers， task，point of interests
        public TaskAllocation(List<Schedule> eventSchedules, List<PointOfInterest> pointInterests, Task task) {
            this.scheduleWorkers = getValidWorkers(eventSchedules, task);
            System.out.println(this.scheduleWorkers);
            this.pointInterests = getValidPointInterest(pointInterests, task);
            System.out.println(this.pointInterests);
            this.task = task;
        }

        // 找出插入时候获得最大收益的配对

        private static class ResultComponent {
            private double increTime = Double.MAX_VALUE;
            List<Event> resList;

            public double getIncreTime() {
                return increTime;
            }

            public void setIncreTime(double increTime) {
                this.increTime = increTime;
            }

            public List<Event> getResList() {
                return resList;
            }

            public void setResList(List<Event> resList) {
                this.resList = resList;
            }

            public boolean isEmpty() {
                return resList == null ? true : false;
            }
        }


        // 还要进行筛选

        public List<PointOfInterest> getValidPointInterest(List<PointOfInterest> pointInterests, Task task) {
            // 种类筛选
            List<PointOfInterest> res = new ArrayList<>();
            for (PointOfInterest p : pointInterests) {
                String types = p.getTypes();
                System.out.println();
                if (!types.contains(task.getType() + "")) continue;
                if (Computation.timeCalculate(p.getLng(), p.getLat(), task.getLng(), task.getLat()) > RadiusEnum.TWENTY
                        .getRaidus())
                    continue;

                res.add(p);
            }
            // 范围筛选

            return res;
        }


        public List<Schedule> getValidWorkers(List<Schedule> eventSchedules, Task task) {
            // 通过半径进
            List<Schedule> res = new ArrayList<>();


            for (Schedule es : eventSchedules) {
                if (Computation.timeCalculate(es.getLng(), es.getLat(), task.getLng(), task.getLat()) > RadiusEnum.TWENTY
                        .getRaidus())
                    continue;

                res.add(es);
            }

            return res;
        }
        // 通过直径范围进行筛选

        // 通过物品种类进行筛选


        // 找到最好的 序列， 插入
        public Schedule getBestPair() {
            double timeConsumeMin = Double.MAX_VALUE;
            Schedule bestWorker = null;
            PointOfInterest bestPointInterest = null;

            for (int esIdx = 0; esIdx < this.scheduleWorkers.size(); esIdx++) {
                Schedule es = this.scheduleWorkers.get(esIdx);
                for (int pIdx = 0; pIdx < this.pointInterests.size(); pIdx++) {
                    PointOfInterest poI = this.pointInterests.get(pIdx);
                    double timeIncrease = getIncrementFactor(es, poI, this.task);

                    if (timeConsumeMin > timeIncrease) {
                        bestWorker = es;
                        bestPointInterest = poI;
                    }
                }
            }


            Schedule res = null;
            if(bestWorker != null)
                res = insertTaskPoIPair(bestWorker, bestPointInterest, this.task);
            return res;
        }


        // 判断能否插入，得出增长 factor 的代价
        public double getIncrementFactor(Schedule eventSchedule, PointOfInterest pointInterest, Task task) {
            ResultComponent rc = insertPoIEvent(eventSchedule, pointInterest, task);
            return rc.isEmpty() ? Double.MAX_VALUE : rc.getIncreTime();
        }


        //-------------------------------------------------------------------------------------------------------------
        // 将task 和 Point Interest 插入到该schedule
        public Schedule insertTaskPoIPair(Schedule eventSchedule, PointOfInterest pointInterest, Task task) {

            ResultComponent rc = insertPoIEvent(eventSchedule, pointInterest, task);
            if (!rc.isEmpty()) {
                eventSchedule.setSchedule(rc.getResList());
                return eventSchedule;
            }

            return null;
        }

        // 约束条件

        public ResultComponent insertPoIEvent(Schedule eventSchedule,
                                              PointOfInterest pointInterest,
                                              Task task) {
            // 如果当前没有任务序列
            System.out.println(eventSchedule.getSchedule());

            if (eventSchedule.getSchedule().size() == 1) {

            }

            List<Event> schedule = eventSchedule.getSchedule();
            Event poIEvent = new Event(
                    LabelEnum.PointInterest.getName(),
                    pointInterest.getLng(),
                    pointInterest.getLat(),
                    0, 0  /*减去 二者间的时间消耗 todo*/);

            Event taskEvent = new Event( LabelEnum.Task.getName(),task.getLng(), task.getLat(), task.getBeginTime()
                    , task.getEndTIme());
            poIEvent.setLatestTime(task.getEndTIme() - Computation.timeCalculate(poIEvent, taskEvent));


            ResultComponent rc = new ResultComponent();

            for (int pIdx = 0; pIdx < schedule.size(); pIdx++) {
                // 检查是否能够将poIEvent 插入到这里面来

                if (schedule.get(pIdx).getEarliestTime() > poIEvent.getLatestTime()) break;

                if (!inserted(schedule, pIdx, poIEvent)) continue;

                double poIIncre = getIncreaseTime(schedule, pIdx, poIEvent);

                List<Event> schedulePoI = copySchedule(schedule);

                schedulePoI = insert(schedulePoI, pIdx, poIEvent);
                // 还要记下增加的代价

                schedulePoI = updateSchedule(schedulePoI);
                // 更新 schedule 的各个指标。
                rc = insertTaskEvent(schedulePoI, pIdx + 1, taskEvent, poIIncre, rc);
                // 插入 TaskEvent了
            }


            return rc;
        }

        private ResultComponent insertTaskEvent(List<Event> schedule,
                                                int pIdx, Event taskEvent,
                                                double poIIncre,
                                                ResultComponent rc) {

            for (int tIdx = pIdx; tIdx < schedule.size(); tIdx++) {
                if (schedule.get(tIdx).getLatestTime() > taskEvent.getLatestTime() || schedule.get(tIdx).getLoad() >=
                        CapacityEnum.FIVE.getCapacity()) {
                    break;
                }
                if (!inserted(schedule, tIdx, taskEvent)) continue;
                double taskIncre = getIncreaseTime(schedule, pIdx, taskEvent);

                if (taskIncre + poIIncre < rc.getIncreTime()) {
                    List<Event> scheduleTask = copySchedule(schedule);
                    scheduleTask = insert(scheduleTask, tIdx, taskEvent);
                    rc.setIncreTime(taskIncre + poIIncre);
                    updateSchedule(scheduleTask);
                    rc.setResList(scheduleTask);
                }
            }

            return rc;
        }

        private double getIncreaseTime(List<Event> schedule, int curIdx, Event eventInserted) {
            if (curIdx == schedule.size() - 1) {
                Event pre = schedule.get(curIdx);
                double timeConsume = Computation.timeCalculate(pre, eventInserted);
                return timeConsume;
            }

            Event pre = schedule.get(curIdx);
            Event next = schedule.get(curIdx + 1);
            double timeConsume = Computation.timeCalculate(pre, eventInserted)
                    + Computation.timeCalculate(eventInserted, next)
                    - Computation.timeCalculate(pre, next);
            return timeConsume;
        }


        private boolean inserted(List<Event> schedule, int curIdx, Event insertedEvent) {
            // 如果是最后一个位置
            if (curIdx == schedule.size() - 1) {
                Event preEvent = schedule.get(curIdx);
                double timeConsume = Computation.timeCalculate(preEvent, insertedEvent);
                if (timeConsume + preEvent.getEarliestTime() > insertedEvent.getLatestTime()) return false;
                if (preEvent.getLoad() + 1 > CapacityEnum.FIVE.getCapacity()) return false;
                return true;
            }

            // 如果不是最后一个位置
            Event preEvent = schedule.get(curIdx);
            Event nextEvent = schedule.get(curIdx + 1);

            // 检验插入结点是否可达
            double timePreToIns = Computation.timeCalculate(preEvent, insertedEvent);
            if (timePreToIns + preEvent.getEarliestTime() > insertedEvent.getLatestTime()) return false;

            // 检验是否能够插入
            double timeTotal = timePreToIns + Computation.timeCalculate(insertedEvent, nextEvent);
            if (timeTotal > nextEvent.getLatestTime() - preEvent.getEarliestTime()) return false;

            // 检验座位是否足够
            if (preEvent.getLoad() + 1 > CapacityEnum.FIVE.getCapacity()) return false;

            return true;
        }

//--------------------------------------------------------------------------------------------------------


        // ------------------------------------------------------------------------------------------------------
        public List<Event> updateSchedule(List<Event> schedule) {
            schedule = updateEarliestTime(schedule);
            schedule = updateLatestTime(schedule);
            schedule = updateCapacity(schedule);
            return schedule;
        }

        private List<Event> updateEarliestTime(List<Event> schedule) {
            Event pre = schedule.get(0);
            double curTime = pre.getEarliestTime();

            for (int i = 1; i < schedule.size(); i++) {
                Event next = schedule.get(i);
                double timeConsume = Computation.timeCalculate(pre, next);
                curTime += timeConsume;
                next.setEarliestTime(curTime);

                pre = next;
            }

            return schedule;
        }

        private List<Event> updateLatestTime(List<Event> schedule) {
            Event next = schedule.get(schedule.size() - 1);

            for (int i = schedule.size() - 2; i >= 0; i--) {
                Event pre = schedule.get(i);
                double preLastTime = next.getLatestTime() - Computation.timeCalculate(pre, next);
                double res = Math.min(pre.getLatestTime(), preLastTime);
                pre.setLatestTime(res);

                next = pre;
            }

            return schedule;
        }

        private List<Event> updateCapacity(List<Event> schedule) {
            Event next = schedule.get(schedule.size() - 1);

            for (int pIdx = schedule.size() - 2; pIdx >= 0; pIdx--) {
                Event pre = schedule.get(pIdx);

                if (next.getLabel().equals(LabelEnum.Task.getName())) {
                    pre.setLoad(next.getLoad() + 1);
                } else if (next.getLabel().equals(LabelEnum.PointInterest.getName())) {
                    pre.setLoad(next.getLoad() - 1);
                }
                next = pre;
            }

            return schedule;
        }

        private List<Event> insert(List<Event> schedule, int curIdx, Event insertedEvent) {
            Event insertedClone = insertedEvent.copy();
            schedule.add(curIdx + 1, insertedClone);
            return schedule;
        }


        private List<Event> copySchedule(List<Event> schedule) {
            List<Event> newSchedule = new ArrayList<>();
            for (Event Event : schedule) {
                newSchedule.add(Event.copy());
            }

            return newSchedule;
        }
}
