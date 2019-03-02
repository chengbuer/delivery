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
    },

    methods:{
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


var locationUpdate = new Vue({
    el: '#app',
    data: {
        workersInfo:[]
    },
    filters: {

    },
    created: function () {
        setInterval(this.updateWorkerLocations, 1000);
    },
    methods: {
        updateWorkerLocations: function (drivingRoutes) {
            // 需要以下两个信息
            // workerId
            // lng & lat
            console.log(drivingRoutes[1])
            for(var workerId in drivingRoutes){
                loc = drivingRoutes[key];
                loc.lng;
                loc.lat;

                var workerInfo = {
                    workerId : workerId,
                    lng : lng,
                    lat : lat
                }

                console.log(workerInfo);
            }


        }
    }
});


