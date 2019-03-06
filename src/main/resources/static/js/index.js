var task = new Vue({
    el:"#task",
    data:{
        id:"1",
        lng:116.404,
        lat:39.915,
        beginTime: 0,
        endTime: 20,
        radius: 20,
        type:"1",
        reward:0.5,
        taskQuery:"/schedule/taskQuery",
        locationUpdated:"/schedule/locationUpdated"
    },

    methods:{

        updateWorkerLocations: function (drivingRoutes) {
            // 需要以下两个信息
            // workerId
            // lng & lat
            var drivings = [];
            for (var key of drivingRoutes) {
                console.log(key[1])
                console.log(key[1].lng)

                var workerInfo = {
                    workerId: key[0],
                    lng: key[1].lng,
                    lat: key[1].lat,
                    eventCompleted : key[1].passedEvent
                }

                drivings.push(workerInfo);
                console.log(workerInfo);
            }

            // 提交信息后，在后台更新所有worker 的位置信息
            axios({
                method:'POST',
                url:this.locationUpdated,
                data:drivings
            }).then(function(routes) {
                // 这里可以返回一个状态值
                // 这里获得routes 的信息后，再调用查询，这样查询得到的位置是最新的。
                // 提交信息后，在后台
                // 这里会调用 arrangeTask 的函数，找到合适的worker，并且前端只更新该worker 的路径信息

                // 提交task 的信息
                // 返回合适的worker，然后进行操作希望读写操作快一点
                this.methods.arrangeTask(drivingRoutes);
                console.log("Hello world");
            });
        },

        arrangeTask:function(drivingRoutes){

            var task={
                id : this.id,
                lng: this.lng,
                lat: this.lat,
                beginTime:this.beginTime,
                endTime:this.endTime,
                radius:this.radius,
                type:this.type,
                reward:this.reward
            }

            console.log(task);

            // axios({
            //     method:'POST',
            //     url:this.taskQuery,
            //     data:task
            // }).then(function(workerInfo){
            //
            //     var route = workerInfo.data.schedule;
            //     //console.log(route);
            //     var points = [];
            //     var len = route.length;
            //
            //     var start = new BMap.Point(route[0].lng, route[0].lat);
            //     var end = new BMap.Point(route[len-1].lng, route[len-1].lat);
            //
            //     for(var i = 1; i < route.length - 1; i++){
            //          points.push(new BMap.Point(route[i].lng, route[i].lat));
            //     }
            //
            //     var print = drivingRoutes.get(workerInfo.data.workerId);
            //
            //     print.search(start, end,{waypoints:points});
            // });
        }
    }
});

