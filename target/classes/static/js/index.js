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
                    lat: key[1].lat
                }

                drivings.push(workerInfo);

                axios({
                    method:'POST',
                    url:this.locationUpdated,
                    data:drivings
                }).then(function(routes) {
                    // 更新所有信息后，还要重新进行路径的搜索
                    // var workersInfo = routes.data;
                    // for(var i = 0; i < workersInfo.length; i++){
                    //     var route = workerInfo[i].data.schedule;
                    //     //console.log(route);
                    //     var points = [];
                    //     var len = route.length;
                    //
                    //     var start = new BMap.Point(route[0].lng, route[0].lat);
                    //     var end = new BMap.Point(route[len-1].lng, route[len-1].lat);
                    //
                    //     for(var i = 1; i < route.length - 1; i++){
                    //         points.push(new BMap.Point(route[i].lng, route[i].lat));
                    //     }
                    //
                    //     var print = drivingRoutes.get(workerInfo.data.workerId);
                    //
                    //     print.search(start, end,{waypoints:points});
                    // }
                });


                console.log(workerInfo);
            }
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

            axios({
                method:'POST',
                url:this.taskQuery,
                data:task
            }).then(function(workerInfo){

                var route = workerInfo.data.schedule;
                //console.log(route);
                var points = [];
                var len = route.length;

                var start = new BMap.Point(route[0].lng, route[0].lat);
                var end = new BMap.Point(route[len-1].lng, route[len-1].lat);

                for(var i = 1; i < route.length - 1; i++){
                     points.push(new BMap.Point(route[i].lng, route[i].lat));
                }

                var print = drivingRoutes.get(workerInfo.data.workerId);

                print.search(start, end,{waypoints:points});
            });
        }
    }
});

