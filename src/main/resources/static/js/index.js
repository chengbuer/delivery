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
        hello:function () {
            alert("Hello world")
        },

        arrangeTask:function(drivingRoutes){

            console.log(drivingRoutes)


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

            console.log(task)

            axios({
                method:'POST',
                url:this.taskQuery,
                data:task
            }).then(function(workerInfo){
                console.log(workerInfo)
                // 取出这段序列
                var route = workerInfo.data.schedule;
                //console.log(route);
                var points = [];
                var len = route.length;

                 var start = new BMap.Point(route[0].lng, route[0].lat);
                 var end = new BMap.Point(route[len-1].lng, route[len-1].lat);

                 for(var i = 1; i < route.length - 1; i++){
                     points.push(new BMap.Point(route[i].lng, route[i].lat));
                 }
                // console.log(start);
                // console.log(points);
                // console.log(end);


                console.log(drivingRoutes.get(workerInfo.data.workerId))
                var print = drivingRoutes.get(workerInfo.data.workerId);

                 print.search(start, end,{waypoints:points});//waypoints表示途经点
            });
        }
    }
});
